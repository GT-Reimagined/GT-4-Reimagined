package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.ICover;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IWorldPosCallable;

public class ContainerCraftingCover extends GTWorkbenchContainer {
    private final IWorldPosCallable worldPosCallable;
    ICover cover;
    public ContainerCraftingCover(int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_, ICover cover) {
        super(cover, id, playerInventory, p_i50090_3_);
        this.worldPosCallable = p_i50090_3_;
        this.cover = cover;
    }

    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(this.worldPosCallable, playerIn, cover.source().getTile().getLevel().getBlockState(cover.source().getTile().getBlockPos()).getBlock());
    }
}
