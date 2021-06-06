package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.gui.slot.IAntimatterSlot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotCrafting extends SlotItemHandler implements IAntimatterSlot {
    public SlotCrafting(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
}
