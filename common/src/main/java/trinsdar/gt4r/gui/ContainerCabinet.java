package trinsdar.gt4r.gui;

import io.github.gregtechintergalactical.gtutility.blockentity.BlockEntityMaterial;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.material.Material;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

import static muramasa.antimatter.data.AntimatterMaterials.Gold;
import static trinsdar.gt4r.data.Materials.*;

public class ContainerCabinet<T extends BlockEntityMaterial<T>> extends ContainerBasicMachine<T> {
    public ContainerCabinet(T tile, Inventory playerInv, MenuHandlerMachine handler, int windowId) {
        super(tile, playerInv, handler, windowId);
        if (tile instanceof Container){
            ((Container)tile).startOpen(playerInv.player);
        }

    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        if (tile instanceof Container){
            ((Container)tile).stopOpen(playerIn);
        }
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
