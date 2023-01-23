package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.SlotFake;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import tesseract.TesseractCapUtils;

public class SlotFluidDisplaySettable extends SlotFake {
    public SlotFluidDisplaySettable(SlotType<SlotFake> type, IGuiHandler tile, IItemHandler stackHandler, int index, int x, int y) {
        super(type, tile, stackHandler, index, x, y, true);
    }

    @Override
    public ItemStack clickSlot(int clickedButton, ClickType clickType, Player playerEntity, AbstractContainerMenu container) {
        if (container.getCarried().isEmpty() || TesseractCapUtils.getFluidHandlerItem(container.getCarried()).map(f -> !f.getFluidInTank(0).isEmpty()).orElse(false)){
            return super.clickSlot(clickedButton, clickType, playerEntity, container);
        }
        return ItemStack.EMPTY;
    }
}
