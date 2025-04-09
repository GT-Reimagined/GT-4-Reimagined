package org.gtreimagined.gt4r.blockentity.single;

import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.CoverHandler;
import org.gtreimagined.gtlib.capability.fluid.FluidHandlerSidedWrapper;
import org.gtreimagined.gtlib.capability.fluid.FluidTanks;
import org.gtreimagined.gtlib.capability.machine.MachineFluidHandler;
import org.gtreimagined.gtlib.capability.machine.MachineRecipeHandler;
import org.gtreimagined.gtlib.gui.SlotType;
import org.gtreimagined.gtlib.machine.event.MachineEvent;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gtlib.util.FluidUtils;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.core.Direction.DOWN;
import static net.minecraft.core.Direction.UP;
import static org.gtreimagined.gt4r.data.Materials.DistilledWater;
import static org.gtreimagined.gt4r.data.Materials.Steam;

public class BlockEntityHeatExchanger extends BlockEntityMachine<BlockEntityHeatExchanger> {
    public BlockEntityHeatExchanger(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<BlockEntityHeatExchanger>(this){
            int heat = 0;
            final int maxHeat = 500;
            boolean consumedWater = false;

            @Override
            public boolean canOutput() {
                if (heat > maxHeat) return false;
                List<FluidStack> output = new ArrayList<>();
                output.add(Steam.getGas(160));
                if (activeRecipe != null && activeRecipe.hasOutputFluids()){
                    output.addAll(Arrays.asList(activeRecipe.getOutputFluids()));
                }
                return super.canOutput() && tile.fluidHandler.map(t -> t.canOutputsFit(output.toArray(new FluidStack[0]))).orElse(false);
            }

            @Override
            protected void addOutputs() {
                super.addOutputs();
                heat += activeRecipe.getSpecialValue();
                /*if (heat > maxHeat){
                    GT4Reimagined.LOGGER.info("Heat Exchanger Exploded Active heat: " + heat + " Max heat: "+ maxHeat);
                    Utils.createExplosion(this.tile.getLevel(), tile.getBlockPos(), 4.0F, Explosion.Mode.DESTROY);
                    return;
                }*/
                if (heat >= 80 && consumedWater){
                    tile.fluidHandler.ifPresent(h -> {
                        h.addOutputs(Steam.getGas(160));
                        tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                    });
                    heat -= 80;
                }
            }

            @Override
            public boolean consumeInputs() {
                boolean flag = super.consumeInputs();
                consumedWater = false;
                if (flag && this.heat + activeRecipe.getSpecialValue() >= 80){
                    tile.fluidHandler.ifPresent(h -> {
                        FluidStack stack = h.drainInput(new FluidStack(h.getInputTanks().getFluidInTank(0).getFluid(), 1), FluidAction.EXECUTE);
                        if (!stack.isEmpty()){
                            consumedWater = true;
                        }
                    });
                }
                return flag;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putInt("heat", this.heat);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                this.heat = nbt.getInt("heat");
            }

            @Override
            public boolean accepts(FluidStack stack) {
                return super.accepts(stack) || stack.getFluid() == Fluids.WATER || stack.getFluid() == DistilledWater.getLiquid();
            }

            @Override
            public void onServerUpdate() {
                super.onServerUpdate();
                if (activeRecipe == null && heat > 0){
                    heat--;
                }
            }
        });

        this.fluidHandler.set(() -> new HeatExchangerFluidHandler(this));
    }

    public static class HeatExchangerFluidHandler extends MachineFluidHandler<BlockEntityHeatExchanger>{

        public HeatExchangerFluidHandler(BlockEntityHeatExchanger tile) {
            this(tile, 8000 * (1 + tile.getMachineTier().getIntegerId()));
        }

        public HeatExchangerFluidHandler(BlockEntityHeatExchanger tile, int capacity) {
            super(tile, capacity);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
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
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                for (int i = 0; i < 2; i++) {
                    b.tank(capacity);
                }
                return b;
            }));
        }

        @Override
        public void onUpdate() {
            super.onUpdate();
            Direction right = tile.getFacing().getCounterClockWise();
            Direction left = tile.getFacing().getClockWise();
            tile.fluidHandler.ifPresent(f -> FluidUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(right), right.getOpposite()).ifPresent(t -> Utils.transferFluids(f.getOutputTanks().getTank(0), t, 1000)));
            tile.fluidHandler.ifPresent(f -> FluidUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(DOWN), UP).ifPresent(t -> Utils.transferFluids(f.getOutputTanks().getTank(1), t, 1000)));
            tile.fluidHandler.side(left).ifPresent(t -> FluidUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(left), left.getOpposite()).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
            tile.fluidHandler.side(UP).ifPresent(t -> FluidUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(UP), DOWN).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
        }

        public static void transferFluids(IFluidHandler from, HeatExchangerFluidHandlerSidedWrapper to, int cap) {
            for (int i = 0; i < to.getTanks(); i++) {
                //if (i >= from.getTanks()) break;
                FluidStack toInsert = FluidStack.EMPTY;
                for (int j = 0; j < from.getTanks(); j++) {
                    if (cap > 0) {
                        FluidStack fluid = from.getFluidInTank(j);
                        if (fluid.isEmpty()) {
                            continue;
                        }
                        fluid = fluid.copy();
                        int toDrain = Math.min(cap, fluid.getAmount());
                        fluid.setAmount(toDrain);
                        toInsert = from.drain(fluid, FluidAction.SIMULATE);
                    } else {
                        toInsert = from.drain(from.getFluidInTank(j), FluidAction.SIMULATE);
                    }
                    int filled = to.fillInternal(toInsert, FluidAction.SIMULATE);
                    if (filled > 0) {
                        toInsert.setAmount(filled);
                        to.fillInternal(from.drain(toInsert, FluidAction.EXECUTE), FluidAction.EXECUTE);
                    }
                }
            }
        }

        @Override
        public LazyOptional<IFluidHandler> forSide(Direction side) {
            return LazyOptional.of(() -> new HeatExchangerFluidHandlerSidedWrapper(this, tile.coverHandler.map(c -> c).orElse(null), side));
        }

        @Override
        public boolean canInput(Direction direction) {
            return super.canInput();
        }

        @Override
        public int fill(FluidStack stack, FluidAction action) {
            if (stack.getFluid() == Fluids.WATER || stack.getFluid() == DistilledWater.getLiquid()){
                int fillSim = super.fill(stack, FluidAction.SIMULATE);
                boolean hasWater = this.getInputTanks().getFluidInTank(0).getFluid() == Fluids.WATER || this.getInputTanks().getFluidInTank(1).getFluid() == Fluids.WATER || this.getInputTanks().getFluidInTank(0).getFluid() == DistilledWater.getLiquid() || this.getInputTanks().getFluidInTank(1).getFluid() == DistilledWater.getLiquid();
                if (fillSim > 0 && !hasWater && tile.recipeHandler.map(h -> h.serialize().getInt("heat") >= 80).orElse(false)){
                    tile.getLevel().explode(null, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), 4.0F, Explosion.BlockInteraction.DESTROY);
                    tile.getLevel().setBlockAndUpdate(tile.getBlockPos(), Blocks.AIR.defaultBlockState());
                    return 0;
                }
            }
            return super.fill(stack, action);
        }

        public static class HeatExchangerFluidHandlerSidedWrapper extends FluidHandlerSidedWrapper {
            public HeatExchangerFluidHandlerSidedWrapper(HeatExchangerFluidHandler fluidHandler, CoverHandler<?> coverHandler, Direction side){
                super(fluidHandler, coverHandler, side);
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {
                if (side == DOWN || side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return 0;
                /*if (side == UP) {
                    int fill = fluidHandler.tanks.get(FluidDirection.INPUT).getTank(0).fill(resource, action);
                    return fill;
                }
                if (side == fluidHandler.tile.getFacing().rotateY()) {
                    int fill = fluidHandler.tanks.get(FluidDirection.INPUT).getTank(1).fill(resource, action);
                    return fill;
                }*/
                return fluidHandler.fill(resource, action);
            }

            public int fillInternal(FluidStack resource, FluidAction action) {
                if (side == DOWN || side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return 0;
                if (side == UP) {
                    return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(0).fill(resource, action);
                }
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getClockWise()) {
                    return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(1).fill(resource, action);
                }
                return fluidHandler.fill(resource, action);
            }

            @NotNull
            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                if (side == DOWN) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(1).drain(resource, action);
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(0).drain(resource, action);
                return FluidStack.EMPTY;
            }
        }
    }
}
