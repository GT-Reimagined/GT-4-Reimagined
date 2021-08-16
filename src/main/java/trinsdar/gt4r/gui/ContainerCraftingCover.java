package trinsdar.gt4r.gui;

import muramasa.antimatter.cover.CoverStack;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class ContainerCraftingCover extends GTWorkbenchContainer {
    private final IWorldPosCallable worldPosCallable;
    CoverStack<?> cover;
    public ContainerCraftingCover(int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_, CoverStack<?> cover) {
        super(cover, id, playerInventory, p_i50090_3_);
        this.worldPosCallable = p_i50090_3_;
        this.cover = cover;
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, cover.getTile().getWorld().getBlockState(cover.getTile().getPos()).getBlock());
    }
}
