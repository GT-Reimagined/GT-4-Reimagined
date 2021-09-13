package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.gui.slot.IClickableSlot;
import muramasa.antimatter.gui.slot.SlotFake;
import muramasa.antimatter.gui.slot.SlotFakeFluid;
import muramasa.antimatter.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class SlotFluidDisplaySettable extends SlotFake {
    public SlotFluidDisplaySettable(SlotType<SlotFake> type, IGuiHandler tile, IItemHandler stackHandler, int index, int x, int y) {
        super(type, tile, stackHandler, index, x, y, true);
    }

    @Override
    public ItemStack clickSlot(int clickedButton, ClickType clickType, PlayerEntity playerEntity, Container container) {
        if (playerEntity.inventory.getItemStack().isEmpty() || playerEntity.inventory.getItemStack().getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).map(f -> !f.getFluidInTank(0).isEmpty()).orElse(false)){
            return super.clickSlot(clickedButton, clickType, playerEntity, container);
        }
        return ItemStack.EMPTY;
    }
}
