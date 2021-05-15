package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Steam;

public class TileEntityHeatExchanger extends TileEntityMachine {
    public TileEntityHeatExchanger(Machine<?> type) {
        super(type);
        this.recipeHandler = LazyOptional.of(() -> new MachineRecipeHandler<TileEntityHeatExchanger>(this){
            int heat = 0;
            final int maxHeat = 500;
            boolean consumedWater = false;

            @Override
            public boolean canOutput() {
                List<FluidStack> output = new ArrayList<>();
                output.add(Steam.getGas(160));
                if (activeRecipe.hasOutputFluids()){
                    output.addAll(Arrays.asList(activeRecipe.getOutputFluids()));
                }
                return super.canOutput() && tile.fluidHandler.map(t -> t.canOutputsFit(output.toArray(new FluidStack[0]))).orElse(false);
            }

            @Override
            protected void addOutputs() {
                super.addOutputs();
                heat += activeRecipe.getSpecialValue();
                if (heat > maxHeat){
                    tile.getWorld().createExplosion(null, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 4.0F, Explosion.Mode.DESTROY);
                    tile.getWorld().setBlockState(tile.getPos(), Blocks.AIR.getDefaultState());
                    return;
                }
                if (heat >= 80 && consumedWater){
                    tile.fluidHandler.ifPresent(h -> {
                        h.addOutputs(Steam.getGas(160));
                        tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                    });
                    heat -= 80;
                }
            }

            @Override
            public void consumeInputs() {
                super.consumeInputs();
                consumedWater = false;
                if (this.heat + activeRecipe.getSpecialValue() >= 80){
                    tile.fluidHandler.ifPresent(h -> {
                        FluidStack stack = h.drainInput(new FluidStack(h.getInputTanks().getFluidInTank(0).getFluid(), 1), IFluidHandler.FluidAction.EXECUTE);
                        if (!stack.isEmpty()){
                            consumedWater = true;
                        }
                    });
                }
            }

            @Override
            public CompoundNBT serializeNBT() {
                CompoundNBT nbt = super.serializeNBT();
                nbt.putInt("heat", this.heat);
                return nbt;
            }

            @Override
            public void deserializeNBT(CompoundNBT nbt) {
                super.deserializeNBT(nbt);
                this.heat = nbt.getInt("heat");
            }

            @Override
            public boolean accepts(FluidStack stack) {
                return super.accepts(stack) || stack.getFluid() == Fluids.WATER || stack.getFluid() == DistilledWater.getLiquid();
            }
        });

        this.fluidHandler = LazyOptional.of(() -> new HeatExchangerFluidHandler(this));
    }

    public static class HeatExchangerFluidHandler extends MachineFluidHandler<TileEntityHeatExchanger>{

        public HeatExchangerFluidHandler(TileEntityHeatExchanger tile) {
            this(tile, 8000 * (1 + tile.getMachineTier().getIntegerId()), 1000 * (250 + tile.getMachineTier().getIntegerId()));
        }

        public HeatExchangerFluidHandler(TileEntityHeatExchanger tile, int capacity, int pressure) {
            super(tile, capacity, pressure);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                for (int i = 0; i < 2; i++) {
                    Predicate<FluidStack> validator = f -> {
                        boolean check = f.getFluid() != Fluids.WATER && f.getFluid() != DistilledWater.getLiquid();
                        return check;
                    };
                    if (i == 0){
                        validator = f -> {
                            boolean check = f.getFluid() == Fluids.WATER || f.getFluid() == DistilledWater.getLiquid();
                            return check;
                        };
                    }

                    b.tank(validator, capacity);
                }
                return b;
            }));
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
                for (int i = 0; i < 2; i++) {
                    b.tank(capacity);
                }
                return b;
            }));
        }

        @Override
        public boolean canInput(Direction direction) {
            return super.canInput();
        }

        @Override
        public int fill(FluidStack stack, FluidAction action) {
            if (stack.getFluid() == Fluids.WATER || stack.getFluid() == DistilledWater.getLiquid()){
                int fillSim = super.fill(stack, FluidAction.SIMULATE);
                if (fillSim > 0 && tile.recipeHandler.map(h -> h.serializeNBT().getInt("heat") >= 80).orElse(false)){
                    tile.getWorld().createExplosion(null, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 4.0F, Explosion.Mode.DESTROY);
                    tile.getWorld().setBlockState(tile.getPos(), Blocks.AIR.getDefaultState());
                    return 0;
                }
            }
            return super.fill(stack, action);
        }
    }
}
