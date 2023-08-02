package trinsdar.gt4r.tile.single;

import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
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
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static net.minecraft.core.Direction.DOWN;
import static net.minecraft.core.Direction.UP;
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
                List<FluidHolder> output = new ArrayList<>();
                output.add(Steam.getGas(160));
                if (activeRecipe != null && activeRecipe.hasOutputFluids()){
                    output.addAll(Arrays.asList(activeRecipe.getOutputFluids()));
                }
                return super.canOutput() && tile.fluidHandler.map(t -> t.canOutputsFit(output.toArray(new FluidHolder[0]))).orElse(false);
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
                        FluidHolder stack = h.drainInput(FluidPlatformUtils.createFluidStack(h.getInputTanks().getFluidInTank(0).getFluid(), TesseractGraphWrappers.dropletMultiplier), false);
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
            public boolean accepts(FluidHolder stack) {
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
                    Predicate<FluidHolder> validator = f -> {
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
            tile.fluidHandler.side(right).ifPresent(f -> TesseractCapUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(right), right.getOpposite()).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            tile.fluidHandler.side(DOWN).ifPresent(f -> TesseractCapUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(DOWN), UP).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            tile.fluidHandler.side(left).ifPresent(t -> TesseractCapUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(left), left.getOpposite()).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
            tile.fluidHandler.side(UP).ifPresent(t -> TesseractCapUtils.getFluidHandler(tile.level, tile.getBlockPos().relative(UP), DOWN).ifPresent(f -> transferFluids(f, ((HeatExchangerFluidHandlerSidedWrapper)t), 1000)));
        }

        public static void transferFluids(PlatformFluidHandler from, HeatExchangerFluidHandlerSidedWrapper to, int cap) {
            for (int i = 0; i < to.getSize(); i++) {
                //if (i >= from.getTanks()) break;
                FluidHolder toInsert = FluidHooks.emptyFluid();
                for (int j = 0; j < from.getTankAmount(); j++) {
                    if (cap > 0) {
                        FluidHolder fluid = from.getFluidInTank(j);
                        if (fluid.isEmpty()) {
                            continue;
                        }
                        fluid = fluid.copyHolder();
                        long toDrain = Math.min(cap, fluid.getFluidAmount());
                        fluid.setAmount(toDrain);
                        toInsert = from.extractFluid(fluid, true);
                    } else {
                        toInsert = from.extractFluid(from.getFluidInTank(j), true);
                    }
                    long filled = to.fillInternal(toInsert, true);
                    if (filled > 0) {
                        toInsert.setAmount(filled);
                        to.fillInternal(from.extractFluid(toInsert, false), false);
                    }
                }
            }
        }

        @Override
        public Optional<FluidContainer> forSide(Direction side) {
            return Optional.of(new HeatExchangerFluidHandlerSidedWrapper(this, tile.coverHandler.map(c -> c).orElse(null), side));
        }

        @Override
        public boolean canInput(Direction direction) {
            return super.allowsInsertion();
        }

        @Override
        public long insertFluid(FluidHolder stack, boolean simulate) {
            if (stack.getFluid() == Fluids.WATER || stack.getFluid() == DistilledWater.getLiquid()){
                long fillSim = super.insertFluid(stack, true);
                boolean hasWater = this.getInputTanks().getFluidInTank(0).getFluid() == Fluids.WATER || this.getInputTanks().getFluidInTank(1).getFluid() == Fluids.WATER || this.getInputTanks().getFluidInTank(0).getFluid() == DistilledWater.getLiquid() || this.getInputTanks().getFluidInTank(1).getFluid() == DistilledWater.getLiquid();
                if (fillSim > 0 && !hasWater && tile.recipeHandler.map(h -> h.serialize().getInt("heat") >= 80).orElse(false)){
                    tile.getLevel().explode(null, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), 4.0F, Explosion.BlockInteraction.DESTROY);
                    tile.getLevel().setBlockAndUpdate(tile.getBlockPos(), Blocks.AIR.defaultBlockState());
                    return 0;
                }
            }
            return super.insertFluid(stack, simulate);
        }

        public static class HeatExchangerFluidHandlerSidedWrapper extends FluidHandlerSidedWrapper {
            public HeatExchangerFluidHandlerSidedWrapper(HeatExchangerFluidHandler fluidHandler, CoverHandler<?> coverHandler, Direction side){
                super(fluidHandler, coverHandler, side);
            }

            @Override
            public long insertFluid(FluidHolder resource, boolean simulate) {
                if (side == DOWN || side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return 0;
                /*if (side == UP) {
                    int fill = fluidHandler.tanks.get(FluidDirection.INPUT).getTank(0).fill(resource, action);
                    return fill;
                }
                if (side == fluidHandler.tile.getFacing().rotateY()) {
                    int fill = fluidHandler.tanks.get(FluidDirection.INPUT).getTank(1).fill(resource, action);
                    return fill;
                }*/
                return fluidHandler.insertFluid(resource, simulate);
            }

            public long fillInternal(FluidHolder resource, boolean simulate) {
                if (side == DOWN || side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return 0;
                if (side == UP) {
                    long fill = ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(0).insertFluid(resource, simulate);
                    return fill;
                }
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getClockWise()) {
                    long fill = ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.INPUT).getTank(1).insertFluid(resource, simulate);
                    return fill;
                }
                return fluidHandler.insertFluid(resource, simulate);
            }

            @Nonnull
            @Override
            public FluidHolder extractFluid(FluidHolder resource, boolean simulate) {
                if (side == DOWN) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(1).extractFluid(resource, simulate);
                if (side == ((HeatExchangerFluidHandler)fluidHandler).tile.getFacing().getCounterClockWise()) return ((HeatExchangerFluidHandler)fluidHandler).tanks.get(FluidDirection.OUTPUT).getTank(0).extractFluid(resource, simulate);
                return FluidHooks.emptyFluid();
            }
        }
    }
}
