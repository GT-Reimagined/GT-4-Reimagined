package org.gtreimagined.gt4r.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ContainerCraftingItem extends GTWorkbenchContainer {
    public ContainerCraftingItem(int id, Inventory playerInventory, ContainerLevelAccess p_i50090_3_) {
        super(new PlayerGuiHandler(playerInventory.player), id, playerInventory, p_i50090_3_);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }


}
