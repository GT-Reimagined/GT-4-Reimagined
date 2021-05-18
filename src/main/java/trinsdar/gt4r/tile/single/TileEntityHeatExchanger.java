package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.machine.FluidHandlerNullSideWrapper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static net.minecraft.util.Direction.DOWN;
import static net.minecraft.util.Direction.UP;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (blocksCapability(cap, side)) return LazyOptional.empty();
        else if (cap == FLUID_HANDLER_CAPABILITY && fluidHandler.isPresent()) {
            return fluidHandler.map(f -> ((HeatExchangerFluidHandler)f).getCapability(cap, side)).orElse(LazyOptional.empty());
        }
        return super.getCapability(cap, side);
    }

    public static class HeatExchangerFluidHandler extends MachineFluidHandler<TileEntityHeatExchanger>{

        Map<Direction, LazyOptional<IFluidHandler>> sidedCaps = new LinkedHashMap<>();
        LazyOptional<IFluidHandler> nullCap;

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
            for (Direction dir : Direction.values()){
                sidedCaps.put(dir, LazyOptional.of(() -> new FluidHandlerSidedWrapper(this, dir)));
            }
            nullCap = LazyOptional.of(() -> new FluidHandlerNullSideWrapper(this));
        }

        @Override
        public void onRemove() {
            this.nullCap.invalidate();
            this.sidedCaps.values().forEach(LazyOptional::invalidate);
        }

        @Override
        public void onUpdate() {
            super.onUpdate();
            Direction right = tile.getFacing().rotateYCCW();
            Direction left = tile.getFacing().rotateY();
            TileEntity rightTile = tile.world.getTileEntity(tile.getPos().offset(right));
            TileEntity downTile = tile.world.getTileEntity(tile.getPos().offset(DOWN));
            if (rightTile != null) {
                this.sidedCaps.get(right).ifPresent(f -> rightTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, right.getOpposite()).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            }
            if (downTile != null) {
                this.sidedCaps.get(DOWN).ifPresent(f -> downTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, UP).ifPresent(t -> Utils.transferFluids(f, t, 1000)));
            }
            TileEntity leftTile = tile.world.getTileEntity(tile.getPos().offset(left));
            TileEntity upTile = tile.world.getTileEntity(tile.getPos().offset(UP));
            if (leftTile != null) {
                this.sidedCaps.get(left).ifPresent(t -> leftTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, left.getOpposite()).ifPresent(f -> Utils.transferFluids(f, t, 1000)));
            }
            if (upTile != null) {
                this.sidedCaps.get(UP).ifPresent(t -> upTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, DOWN).ifPresent(f -> Utils.transferFluids(f, t, 1000)));
            }
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

        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
            if (side == null){
                if (nullCap.isPresent()) return nullCap.cast();
                return LazyOptional.empty();
            }
            if (sidedCaps.get(side).isPresent()) return sidedCaps.get(side).cast();
            return LazyOptional.empty();
        }

        public static class FluidHandlerSidedWrapper implements IFluidHandler{
            HeatExchangerFluidHandler fluidHandler;
            Direction side;
            public FluidHandlerSidedWrapper(HeatExchangerFluidHandler fluidHandler, Direction side){
                this.fluidHandler = fluidHandler;
                this.side = side;
            }

            @Override
            public int getTanks() {
                return fluidHandler.getTanks();
            }

            @Nonnull
            @Override
            public FluidStack getFluidInTank(int tank) {
                return fluidHandler.getFluidInTank(tank);
            }

            @Override
            public int getTankCapacity(int tank) {
                return fluidHandler.getTankCapacity(tank);
            }

            @Override
            public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
                return fluidHandler.isFluidValid(tank, stack);
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {
                if (side == DOWN || side == fluidHandler.tile.getFacing().rotateYCCW()) return 0;
                if (side == UP) return fluidHandler.tanks.get(FluidDirection.INPUT).getTank(0).fill(resource, action);
                if (side == fluidHandler.tile.getFacing().rotateY()) return fluidHandler.tanks.get(FluidDirection.INPUT).getTank(1).fill(resource, action);
                return fluidHandler.fill(resource, action);
            }

            @Nonnull
            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                if (side == DOWN) return fluidHandler.tanks.get(FluidDirection.OUTPUT).getTank(0).drain(resource, action);
                if (side == fluidHandler.tile.getFacing().rotateYCCW()) return fluidHandler.tanks.get(FluidDirection.OUTPUT).getTank(1).drain(resource, action);
                return FluidStack.EMPTY;
            }

            @Nonnull
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                if (side == DOWN) return fluidHandler.tanks.get(FluidDirection.OUTPUT).getTank(0).drain(maxDrain, action);
                if (side == fluidHandler.tile.getFacing().rotateYCCW()) return fluidHandler.tanks.get(FluidDirection.OUTPUT).getTank(1).drain(maxDrain, action);
                return FluidStack.EMPTY;
            }
        }
    }
}
