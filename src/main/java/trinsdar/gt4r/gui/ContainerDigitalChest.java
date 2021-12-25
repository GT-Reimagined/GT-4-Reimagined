package trinsdar.gt4r.gui;

import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import trinsdar.gt4r.tile.single.TileEntityDigitalChest;

public class ContainerDigitalChest extends ContainerBasicMachine<TileEntityDigitalChest> {
    public ContainerDigitalChest(TileEntityDigitalChest tile, PlayerInventory playerInv, MenuHandlerMachine<TileEntityDigitalChest, ContainerMachine<TileEntityDigitalChest>> handler, int windowId) {
        super(tile, playerInv, handler, windowId);
    }

    @Override
    protected void addPlayerSlots() {
        if (playerInv == null) return;
        for (int i = 0; i < 3; ++i) { //Inventory Slots
            for (int j = 0; j < 9; ++j) {
                int y = 140;
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, (y) + (i * 18)));
            }
        }
        for (int k = 0; k < 9; ++k) { //HotBar Slots
            this.addSlot(new Slot(playerInv, k, 8 + k * 18, 198));
        }
    }
}
