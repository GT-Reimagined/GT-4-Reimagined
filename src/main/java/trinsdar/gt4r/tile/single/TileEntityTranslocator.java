package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import trinsdar.gt4r.data.SlotTypes;

public abstract class TileEntityTranslocator extends TileEntityMachine<TileEntityTranslocator> {
    Capability<?> cap;
    public TileEntityTranslocator(Machine<?> type, Capability<?> cap) {
        super(type);
        this.cap = cap;
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        if (coverHandler.map(c -> !c.get(this.getFacing()).getCover().blocksOutput(c.get(this.getFacing()), cap, this.getFacing()) && !c.get(this.getFacing().getOpposite()).getCover().blocksInput(c.get(this.getFacing().getOpposite()), cap, this.getFacing().getOpposite())).orElse(true)){
            this.processOutput();
        }
    }

    protected abstract boolean processOutput();

    public static class TileEntityItemTranslocator extends TileEntityTranslocator{
        public TileEntityItemTranslocator(Machine<?> type) {
            super(type, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing();
            Direction inputDir = this.getFacing().getOpposite();
            TileEntity outputTile = Utils.getTile(this.getWorld(), this.getPos().offset(outputDir));
            if (outputTile == null) return false;
            TileEntity inputTile = Utils.getTile(this.getWorld(), this.getPos().offset(inputDir));
            if (inputTile == null) return false;
            boolean[] booleans = new boolean[1];
            booleans[0] = false;
            outputTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                inputTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inputDir.getOpposite()).ifPresent(in -> {
                    booleans[0] = Utils.transferItems(in, out,true);
                });
            });
            return booleans[0];
        }
    }

    public static class TileEntityFluidTranslocator extends TileEntityTranslocator {
        public TileEntityFluidTranslocator(Machine<?> type) {
            super(type, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing();
            Direction inputDir = this.getFacing().getOpposite();
            TileEntity outputTile = Utils.getTile(this.getWorld(), this.getPos().offset(outputDir));
            if (outputTile == null) return false;
            TileEntity inputTile = Utils.getTile(this.getWorld(), this.getPos().offset(inputDir));
            if (inputTile == null) {
                FluidState state = world.getFluidState(this.getPos().offset(inputDir));
                if (!state.isEmpty() && !((state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER) && world.getBlockState(this.getPos().offset(inputDir)).getBlock() != Blocks.WATER)){
                    boolean[] booleans = new boolean[1];
                    booleans[0] = false;
                    outputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                        int fill = out.fill(new FluidStack(state.getFluid(), 1000), IFluidHandler.FluidAction.SIMULATE);
                        if (fill == 1000){
                            out.fill(new FluidStack(state.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                            booleans[0] = true;
                            world.setBlockState(this.getPos().offset(inputDir), Blocks.AIR.getDefaultState());
                        }
                    });
                    return booleans[0];
                }
                return false;
            }
            boolean[] booleans = new boolean[1];
            booleans[0] = false;
            outputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                inputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, inputDir.getOpposite()).ifPresent(in -> {
                    booleans[0] = Utils.transferFluids(in, out,1000);
                });
            });
            return booleans[0];
        }
    }
}
