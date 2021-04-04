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
import net.minecraftforge.fluids.capability.templates.FluidTank;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nullable;

import static muramasa.antimatter.Data.ELECTRIC_WRENCH;
import static muramasa.antimatter.Data.WRENCH;
import static trinsdar.gt4r.data.Materials.*;

public class TileEntityDrum extends TileEntityMachine {
    Material material;
    public TileEntityDrum(MaterialMachine type) {
        super(type);
        material = type.getMaterial();
        this.fluidHandler = LazyOptional.of(() -> new DrumFluidHandler(this));
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

    public static class DrumFluidHandler extends MachineFluidHandler<TileEntityDrum>{
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
            if (tile.getWorld().getGameTime() % 20 == 0 && output){
                Direction dir = getTank(0).getFluid().getFluid().getAttributes().isGaseous() ? Direction.UP : Direction.DOWN;
                if (getTank(0).getFluidAmount() > 0){
                    TileEntity adjacent = tile.getWorld().getTileEntity(tile.getPos().offset(dir));
                    if (adjacent != null){
                        Utils.transferFluidsOnCap(tile, adjacent, 1000);
                    }
                }
            }
        }
    }
}
