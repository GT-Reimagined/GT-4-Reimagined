package trinsdar.gt4r.gui;

import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.material.Material;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import static muramasa.antimatter.Data.Gold;
import static trinsdar.gt4r.data.Materials.*;

public class ContainerCabinet<T extends TileEntityMaterial<T>> extends ContainerBasicMachine<T> {
    public ContainerCabinet(T tile, PlayerInventory playerInv, MenuHandlerMachine handler, int windowId) {
        super(tile, playerInv, handler, windowId);
    }

    @Override
    protected void addPlayerSlots() {
        if (playerInv == null) return;
        int playerOffset = getOffset();
        for (int i = 0; i < 3; ++i) { //Inventory Slots
            for (int j = 0; j < 9; ++j) {
                int y = 140  + playerOffset;
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 12 + j * 18, (y) + (i * 18)));
            }
        }
        for (int k = 0; k < 9; ++k) { //HotBar Slots
            this.addSlot(new Slot(playerInv, k, 12 + k * 18, 198 + playerOffset));
        }
    }

    protected int getOffset(){
        Material m = tile.getMaterial();
        return m == Electrum || m == Gold || m == Silver || m == Magnalium ? 18 : m == Platinum ? 36 : m == Osmium ? 54 : 0;
    }
}
