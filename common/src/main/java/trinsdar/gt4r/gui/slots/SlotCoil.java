package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.capability.IMachineHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import tesseract.api.item.ExtendedItemContainer;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;
import trinsdar.gt4r.tile.single.TileEntityFluidExtractor;

import javax.annotation.Nonnull;

public class SlotCoil extends AbstractSlot<SlotCoil> {
    public SlotCoil(SlotType<SlotCoil> type, IGuiHandler tile, ExtendedItemContainer stackHandler, int index, int xPosition, int yPosition) {
        super(type, tile, stackHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (holder instanceof TileEntityFluidExtractor){
            return RecipeMaps.FLUID_EXTRACTOR_COILS.acceptsItem(stack);
        }
        TileEntityMachine<?> m = (TileEntityMachine<?>) holder;
        return stack.getItem() == GT4RData.KanthalHeatingCoil || stack.getItem() == GT4RData.NichromeHeatingCoil || (m.getMachineType() == Machines.PYROLYSIS_OVEN && stack.getItem() == GT4RData.CupronickelHeatingCoil);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return true;
    }

    @Override
    public int getMaxStackSize() {
        if (holder instanceof TileEntityFluidExtractor){
            return 6;
        }
        return 4;
    }

    @Override
    public int getMaxStackSize(@Nonnull ItemStack stack) {
        if (holder instanceof TileEntityFluidExtractor){
            return 6;
        }
        return 4;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (holder instanceof IMachineHandler) ((IMachineHandler)holder).onMachineEvent(TileEntityIndustrialBlastFurnace.BFEvent.SLOT_COIL_CHANGED, this.getItem());
    }
}
