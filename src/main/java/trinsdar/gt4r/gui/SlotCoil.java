package trinsdar.gt4r.gui;

import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import trinsdar.gt4r.GT4Renewed;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.multi.TileEntityElectricBlastFurnace;

import javax.annotation.Nonnull;

public class SlotCoil extends SlotItemHandler {
    TileEntityMachine tile;
    public SlotCoil(TileEntityMachine tile, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.tile = tile;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem() == GT4RData.KanthalHeatingCoil || stack.getItem() == GT4RData.NichromeHeatingCoil;
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public int getSlotStackLimit() {
        return 6;
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return 6;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        tile.onMachineEvent(TileEntityElectricBlastFurnace.BFEvent.SLOT_COIL_CHANGED, this.getStack());
    }
}
