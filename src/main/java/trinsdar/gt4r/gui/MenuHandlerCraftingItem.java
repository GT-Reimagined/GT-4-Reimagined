package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import trinsdar.gt4r.data.client.ScreenFactories;

public class MenuHandlerCraftingItem extends MenuHandler<GTWorkbenchContainer> {
    public MenuHandlerCraftingItem(String domain, String id) {
        super(domain, id);
    }

    @Override
    public GTWorkbenchContainer onContainerCreate(int windowId, PlayerInventory inv, PacketBuffer data) {
        //TODO
        return menu((IGuiHandler) inv.player, inv, windowId);
    }

    @Override
    public GTWorkbenchContainer getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
        return tile instanceof PlayerEntity ? new ContainerCraftingItem(windowId, playerInv, IWorldPosCallable.create(playerInv.player.level, playerInv.player.blockPosition())) : null;
    }

    @Override
    public Object screen() {
        return ScreenFactories.SCREEN_CRAFTING_TABLE;
    }
}
