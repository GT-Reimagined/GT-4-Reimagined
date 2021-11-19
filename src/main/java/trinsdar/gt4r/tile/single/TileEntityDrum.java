package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.material.Material;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nullable;
import java.util.List;

import static muramasa.antimatter.Data.ELECTRIC_WRENCH;
import static muramasa.antimatter.Data.Netherite;
import static muramasa.antimatter.Data.WRENCH;
import static net.minecraft.util.Direction.DOWN;
import static net.minecraft.util.Direction.UP;
import static trinsdar.gt4r.data.Materials.*;

import muramasa.antimatter.capability.FluidHandler.FluidDirection;

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
            if ((type == WRENCH || type == ELECTRIC_WRENCH) && !player.isShiftKeyDown()){
                dF.setOutput(!dF.isOutput());
                success[0] = true;
                player.playNotifySound(Ref.WRENCH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                // TODO: Replace by new TranslationTextComponent()
                player.sendMessage(new StringTextComponent((dF.isOutput() ? "Will" : "Won't") + " fill adjacent Tanks"), player.getUUID());
            }
        });
        if (success[0]){
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
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

    @Override
    public List<String> getInfo() {
        List<String> list = super.getInfo();
        fluidHandler.ifPresent(f -> {
            FluidStack stack = f.getInputTanks().getFluidInTank(0);
            list.add("Fluid: " + (stack.isEmpty() ? "Empty" : stack.getAmount() + "mb of " + stack.getFluid().getAttributes().getDisplayName(stack)));
        });
        return list;
    }

    public static class DrumFluidHandler extends MachineFluidHandler<TileEntityDrum> {
        boolean output = false;
        public DrumFluidHandler(TileEntityDrum tile) {
            super(tile);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                b.tank(getCapacity(tile.material));
                return b;
            }));
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
            if (tile.getLevel().getGameTime() % 20 == 0 && output){
                Direction dir = getTank(0).getFluid().getFluid().getAttributes().isGaseous() ? UP : Direction.DOWN;
                if (getTank(0).getFluidAmount() > 0){
                    TileEntity adjacent = tile.getLevel().getBlockEntity(tile.getBlockPos().relative(dir));
                    if (adjacent != null){
                        LazyOptional<IFluidHandler> cap = adjacent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir.getOpposite());
                        cap.ifPresent(other -> Utils.transferFluids(this, other, 1000));
                    }
                }
            }
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

        @Override
        public boolean canInput(FluidStack fluid, Direction direction) {
            if (output && (direction == UP && fluid.getFluid().getAttributes().isGaseous()) || (direction == DOWN && !fluid.getFluid().getAttributes().isGaseous())) return false;
            return super.canInput(fluid, direction);
        }
    }
}
