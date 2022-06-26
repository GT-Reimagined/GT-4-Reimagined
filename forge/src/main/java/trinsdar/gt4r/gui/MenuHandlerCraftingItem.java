package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.ContainerLevelAccess;
import trinsdar.gt4r.data.client.ScreenFactories;

public class MenuHandlerCraftingItem extends MenuHandler<GTWorkbenchContainer> {
    public MenuHandlerCraftingItem(String domain, String id) {
        super(domain, id);
    }

    @Override
    public GTWorkbenchContainer onContainerCreate(int windowId, Inventory inv, FriendlyByteBuf data) {
        //TODO
        return menu((IGuiHandler) inv.player, inv, windowId);
    }

    @Override
    public GTWorkbenchContainer getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
        return tile instanceof Player ? new ContainerCraftingItem(windowId, playerInv, ContainerLevelAccess.create(playerInv.player.level, playerInv.player.blockPosition())) : null;
    }

    @Override
    public Object screen() {
        return ScreenFactories.SCREEN_CRAFTING_TABLE;
    }
}
