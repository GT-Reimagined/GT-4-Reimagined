package trinsdar.gt4r.gui;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.container.AntimatterContainer;
import muramasa.antimatter.gui.container.IAntimatterContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;

import java.util.Set;

public class GTWorkbenchContainer extends CraftingMenu implements IAntimatterContainer {

    private final GuiInstance instance;
    public final Set<ContainerListener> listeners = new ObjectOpenHashSet<>();

    public GTWorkbenchContainer(IGuiHandler handler, int id, Inventory playerInventory) {
        super(id, playerInventory);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    public GTWorkbenchContainer(IGuiHandler handler, int id, Inventory playerInventory, ContainerLevelAccess p_i50090_3_) {
        super(id, playerInventory, p_i50090_3_);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    @Override
    public void addSlotListener(ContainerListener listener) {
        this.listeners.add(listener);
        super.addSlotListener(listener);
    }

    @Override
    public void removeSlotListener(ContainerListener listener) {
        super.removeSlotListener(listener);
        this.listeners.remove(listener);
    }

    @Override
    public Set<ContainerListener> listeners() {
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
