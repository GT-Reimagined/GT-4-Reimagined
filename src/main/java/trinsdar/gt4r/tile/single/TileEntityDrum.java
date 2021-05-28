package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import trinsdar.gt4r.machine.FluidHandlerNullSideWrapper;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static muramasa.antimatter.Data.ELECTRIC_WRENCH;
import static muramasa.antimatter.Data.WRENCH;
import static net.minecraft.util.Direction.DOWN;
import static net.minecraft.util.Direction.UP;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static trinsdar.gt4r.data.Materials.*;

public class TileEntityDrum extends TileEntityMaterial<TileEntityDrum> {
    FluidStack drop = FluidStack.EMPTY;
    boolean output = false;
    public TileEntityDrum(MaterialMachine type) {
        super(type);
        this.fluidHandler.set(() -> new DrumFluidHandler(this));
    }

    @Override
    public ActionResultType onInteract(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit, @Nullable AntimatterToolType type) {
        boolean[] success = new boolean[1];
        this.fluidHandler.ifPresent(f -> {
            DrumFluidHandler dF = (DrumFluidHandler) f;
            if ((type == WRENCH || type == ELECTRIC_WRENCH) && !player.isSneaking()){
                dF.setOutput(!dF.isOutput());
                success[0] = true;
                player.playSound(Ref.WRENCH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                // TODO: Replace by new TranslationTextComponent()
                player.sendMessage(new StringTextComponent((dF.isOutput() ? "Will" : "Won't") + " fill adjacent Tanks"), player.getUniqueID());
            }
        });
        if (success[0]){
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (blocksCapability(cap, side)) return LazyOptional.empty();
        else if (cap == FLUID_HANDLER_CAPABILITY && fluidHandler.isPresent()) return fluidHandler.map(f -> ((DrumFluidHandler)f).getCapability(cap, side)).orElse(LazyOptional.empty());
        return super.getCapability(cap, side);
    }

    @Override
    public void onRemove() {
        this.fluidHandler.ifPresent(f -> {
            this.drop = f.getFluidInTank(0);
            this.output = ((DrumFluidHandler)f).isOutput();
        });
       super.onRemove();
    }

    public FluidStack getDrop() {
        return drop;
    }

    public boolean isOutput() {
        return output;
    }

    public static class DrumFluidHandler extends MachineFluidHandler<TileEntityDrum> {
        boolean output = false;
        Map<Direction, LazyOptional<IFluidHandler>> sidedCaps = new LinkedHashMap<>();
        LazyOptional<IFluidHandler> nullCap;
        public DrumFluidHandler(TileEntityDrum tile) {
            super(tile);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                b.tank(getCapacity(tile.material));
                return b;
            }));
            for (Direction dir : Direction.values()){
                sidedCaps.put(dir, LazyOptional.of(() -> new FluidHandlerSidedWrapper(this, dir)));
            }
            nullCap = LazyOptional.of(() -> new FluidHandlerNullSideWrapper(this));
        }

        public void setOutput(boolean output) {
            this.output = output;
        }

        public boolean isOutput() {
            return output;
        }

        int getCapacity(Material mat){
            if (mat == Netherite) return 128000;
            if (mat == Tungsten || mat == TungstenSteel) return 256000;
            if (mat == Steel) return 32000;
            if (mat == Invar) return 48000;
            if (mat == Bronze) return 16000;
            return 64000;
        }

        @Nullable
        @Override
        public FluidTanks getOutputTanks() {
            return super.getInputTanks();
        }

        @Override
        protected FluidTank getTank(int tank) {
            return getInputTanks().getTank(tank);
        }

        @Override
        public FluidTanks getTanks(int tank) {
            return getInputTanks();
        }

        @Override
        public void onUpdate() {
            super.onUpdate();
            if (tile.getWorld().getGameTime() % 20 == 0 && output){
                Direction dir = getTank(0).getFluid().getFluid().getAttributes().isGaseous() ? UP : Direction.DOWN;
                if (getTank(0).getFluidAmount() > 0){
                    TileEntity adjacent = tile.getWorld().getTileEntity(tile.getPos().offset(dir));
                    if (adjacent != null){
                        LazyOptional<IFluidHandler> cap = adjacent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir.getOpposite());
                        cap.ifPresent(other -> Utils.transferFluids(this, other, 1000));
                    }
                }
            }
        }

        @Override
        public void onRemove() {
            this.nullCap.invalidate();
            this.sidedCaps.values().forEach(LazyOptional::invalidate);
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT nbt = super.serializeNBT();
            nbt.putBoolean("Output", output);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            super.deserializeNBT(nbt);
            this.output = nbt.getBoolean("Output");
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
            DrumFluidHandler fluidHandler;
            Direction side;
            public FluidHandlerSidedWrapper(DrumFluidHandler fluidHandler, Direction side){
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
                if (fluidHandler.isOutput() && (side == UP && resource.getFluid().getAttributes().isGaseous()) || (side == DOWN && !resource.getFluid().getAttributes().isGaseous())){
                    return 0;
                }
                return fluidHandler.fill(resource, action);
            }

            @Nonnull
            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                return fluidHandler.drain(resource, action);
            }

            @Nonnull
            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return fluidHandler.drain(maxDrain, action);
            }
        }
    }
}
