package trinsdar.gt4r.gui;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.container.AntimatterContainer;
import muramasa.antimatter.gui.container.IAntimatterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

import java.util.Set;

public class GTWorkbenchContainer extends WorkbenchContainer implements IAntimatterContainer {

    private final GuiInstance instance;
    public final Set<IContainerListener> listeners = new ObjectOpenHashSet<>();

    public GTWorkbenchContainer(IGuiHandler handler, int id, PlayerInventory playerInventory) {
        super(id, playerInventory);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    public GTWorkbenchContainer(IGuiHandler handler, int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_) {
        super(id, playerInventory, p_i50090_3_);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    @Override
    public void addSlotListener(IContainerListener listener) {
        this.listeners.add(listener);
        super.addSlotListener(listener);
    }

    @Override
    public void removeSlotListener(IContainerListener listener) {
        super.removeSlotListener(listener);
        this.listeners.remove(listener);
    }

    @Override
    public Set<IContainerListener> listeners() {
        return listeners;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        instance.update();
    }

    @Override
    public GuiInstance source() {
        return instance;
    }
}
