package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.CoverHandler;
import muramasa.antimatter.capability.fluid.FluidHandlerSidedWrapper;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.core.Direction.DOWN;
import static net.minecraft.core.Direction.UP;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Steam;

public class TileEntityHeatExchanger extends TileEntityMachine<TileEntityHeatExchanger> {
    public TileEntityHeatExchanger(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<TileEntityHeatExchanger>(this){
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
                        FluidStack stack = h.drainInput(new FluidStack(h.getInputTanks().getFluidInTank(0).getFluid(), 1), IFluidHandler.FluidAction.EXECUTE);
                        if (!stack.isEmpty()){
                            consumedWater = true;
                        }
                    });
                }
                return flag;
            }

            @Override
            public CompoundTag serializeNBT() {
                CompoundTag nbt = super.serializeNBT();
                nbt.putInt("heat", this.heat);
                return nbt;
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                super.deserializeNBT(nbt);
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
        public void onUpdate() {
            super.onUpdate();
            Direction right = tile.getFacing().getCounterClockWise();
            Direction left = tile.getFacing().getClockWise();
            BlockEntity rightTile = tile.level.getBlockEntity(tile.getBlockPos().relative(right));
            BlockEntity downTile = tile.level.getBlockEntity(tile.getBlockPos().relative(DOWN));
            if (rightTile != null) {
                tile.fluidHandler.side(right).ifPresent(f -> rightTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, right.getOpposite()).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            }
            if (downTile != null) {
                tile.fluidHandler.side(DOWN).ifPresent(f -> downTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, UP).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            }
            BlockEntity leftTile = tile.level.getBlockEntity(tile.getBlockPos().relative(left));
            BlockEntity upTile = tile.level.getBlockEntity(tile.getBlockPos().relative(UP));
            if (leftTile != null) {
                tile.fluidHandler.side(left).ifPresent(t -> leftTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, left.getOpposite()).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
            }
            if (upTile != null) {
                tile.fluidHandler.side(UP).ifPresent(t -> upTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, DOWN).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
            }
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
                        toInsert = from.drain(fluid, SIMULATE);
                    } else {
                        toInsert = from.drain(from.getFluidInTank(j), SIMULATE);
                    }
                    int filled = to.fillInternal(toInsert, SIMULATE);
                    if (filled > 0) {
                        toInsert.setAmount(filled);
                        to.fillInternal(from.drain(toInsert, EXECUTE), EXECUTE);
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
                if (fillSim > 0 && !hasWater && tile.recipeHandler.map(h -> h.serializeNBT().getInt("heat") >= 80).orElse(false)){
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
                    int fill = ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(0).fill(resource, action);
                    return fill;
                }
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getClockWise()) {
                    int fill = ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(1).fill(resource, action);
                    return fill;
                }
                return fluidHandler.fill(resource, action);
            }

            @Nonnull
            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                if (side == DOWN) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(1).drain(resource, action);
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(0).drain(resource, action);
                return FluidStack.EMPTY;
            }

            @Nonnull
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                if (side == DOWN) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(1).drain(maxDrain, action);
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(0).drain(maxDrain, action);
                return FluidStack.EMPTY;
            }
        }
    }
}
