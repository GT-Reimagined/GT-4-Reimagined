package org.gtreimagined.gt4r.gui;

import muramasa.antimatter.cover.ICover;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ContainerCraftingCover extends GTWorkbenchContainer {
    private final ContainerLevelAccess worldPosCallable;
    ICover cover;
    public ContainerCraftingCover(int id, Inventory playerInventory, ContainerLevelAccess p_i50090_3_, ICover cover) {
        super(cover, id, playerInventory, p_i50090_3_);
        this.worldPosCallable = p_i50090_3_;
        this.cover = cover;
    }

    public boolean stillValid(Player playerIn) {
        return stillValid(this.worldPosCallable, playerIn, cover.source().getTile().getLevel().getBlockState(cover.source().getTile().getBlockPos()).getBlock());
    }
}
