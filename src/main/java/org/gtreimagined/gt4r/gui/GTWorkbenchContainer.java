package org.gtreimagined.gt4r.gui;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.gtreimagined.gtlib.capability.IGuiHandler;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.container.IGTContainer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

import java.util.Set;

public class GTWorkbenchContainer extends CraftingMenu implements IGTContainer {

    private final GuiInstance instance;
    public final Set<ServerPlayer> listeners = new ObjectOpenHashSet<>();

    public GTWorkbenchContainer(IGuiHandler handler, int id, Inventory playerInventory) {
        super(id, playerInventory);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    public GTWorkbenchContainer(IGuiHandler handler, int id, Inventory playerInventory, ContainerLevelAccess p_i50090_3_) {
        super(id, playerInventory, p_i50090_3_);
        this.instance = new GuiInstance(handler, this, handler.isRemote());
    }

    @Override
    public Set<ServerPlayer> listeners() {
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
