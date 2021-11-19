package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

import muramasa.antimatter.capability.FluidHandler.FluidDirection;

public class CoalBoilerFluidHandler extends MachineFluidHandler<TileEntityCoalBoiler> {

    private boolean fillingCell = false;

    public CoalBoilerFluidHandler(TileEntityCoalBoiler tile) {
        super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
        tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
            b.tank(16000);
            return b;
        }));
        tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
            b.tank(16000);
            return b;
        }));
    }

    @Override
    public void fillCell(int cellSlot, int maxFill) {
        if (fillingCell) return;
        fillingCell = true;
        if (getInputTanks() != null) {
            tile.itemHandler.ifPresent(ih -> {
                if (ih.getCellInputHandler() == null) return;
                ItemStack cell = ih.getCellInputHandler().getStackInSlot(cellSlot);
                if (cell.isEmpty()) return;
                ItemStack toActOn = cell.copy();
                toActOn.setCount(1);
                toActOn.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(cfh -> {
                    ItemStack checkContainer = toActOn.copy().getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).map(t -> {
                        if (t.getFluidInTank(0).isEmpty()) {
                            t.fill(FluidUtil.tryFluidTransfer(t,this.getInputTanks(), maxFill, false), EXECUTE);
                        } else {
                            t.drain(maxFill, EXECUTE);
                        }
                        return t.getContainer();
                    }).orElse(null/* throw exception */);
                    if (!ih.getCellOutputHandler().insertItem(cellSlot,checkContainer,true).isEmpty()) return;

                    FluidStack stack;
                    if (cfh.getFluidInTank(0).isEmpty()) {
                        stack = FluidUtil.tryFluidTransfer(cfh,this.getInputTanks(), maxFill, true);
                    } else {
                        stack = FluidUtil.tryFluidTransfer(this.getInputTanks(),cfh, maxFill, true);
                    }
                    if (!stack.isEmpty()) {
                        ItemStack insert = cfh.getContainer();
                        insert.setCount(1);
                        ih.getCellOutputHandler().insertItem(cellSlot, insert, false);
                        ih.getCellInputHandler().extractItem(cellSlot, 1, false);
                    }
                });
            });
        }
        fillingCell = false;
    }
}
