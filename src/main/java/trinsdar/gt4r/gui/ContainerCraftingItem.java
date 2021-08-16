package trinsdar.gt4r.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class ContainerCraftingItem extends GTWorkbenchContainer {
    public ContainerCraftingItem(int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_) {
    //TODO
        super(null, id, playerInventory, p_i50090_3_);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
