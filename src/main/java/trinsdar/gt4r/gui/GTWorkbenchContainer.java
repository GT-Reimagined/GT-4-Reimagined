package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.container.IAntimatterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class GTWorkbenchContainer extends WorkbenchContainer implements IAntimatterContainer {

    private final GuiInstance instance;

    public GTWorkbenchContainer(IGuiHandler handler, int id, PlayerInventory playerInventory) {
        super(id, playerInventory);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    public GTWorkbenchContainer(IGuiHandler handler, int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_) {
        super(id, playerInventory, p_i50090_3_);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        instance.update();
    }

    @Override
    public GuiInstance source() {
        return instance;
    }
}
