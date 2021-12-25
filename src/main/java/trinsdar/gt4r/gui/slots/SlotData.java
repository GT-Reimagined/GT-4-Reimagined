package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class SlotData extends AbstractSlot<SlotData> {
    public SlotData(SlotType<SlotData> type, IGuiHandler tile, IItemHandler stackHandler, int index, int x, int y) {
        super(type, tile, stackHandler, index, x, y);
    }

    @Override
    public int getMaxStackSize(@Nonnull ItemStack stack) {
        return 1;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
