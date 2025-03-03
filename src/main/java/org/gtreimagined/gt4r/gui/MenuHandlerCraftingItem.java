package org.gtreimagined.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.gtreimagined.gt4r.GT4RRef;

public class MenuHandlerCraftingItem extends MenuHandler<GTWorkbenchContainer> {
    public MenuHandlerCraftingItem(String domain, String id) {
        super(domain, id);
    }

    @Override
    public GTWorkbenchContainer onContainerCreate(int windowId, Inventory inv, FriendlyByteBuf data) {
        return menu(null, inv, windowId);
    }

    @Override
    public GTWorkbenchContainer getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
        return new ContainerCraftingItem(windowId, playerInv, ContainerLevelAccess.create(playerInv.player.level, playerInv.player.blockPosition()));
    }

    @Override
    public String screenID() {
        return "crafting_table";
    }

    @Override
    public String screenDomain() {
        return GT4RRef.ID;
    }
}
