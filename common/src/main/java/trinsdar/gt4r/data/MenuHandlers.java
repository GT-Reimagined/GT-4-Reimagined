package trinsdar.gt4r.data;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import net.minecraft.world.entity.player.Inventory;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.gui.ContainerQuantumChest;
import trinsdar.gt4r.gui.MenuHandlerCrafting;
import trinsdar.gt4r.gui.MenuHandlerCraftingItem;
import trinsdar.gt4r.blockentity.multi.BlockEntityFusionReactor;
import trinsdar.gt4r.blockentity.single.BlockEntityDigitalChest;
import trinsdar.gt4r.blockentity.single.BlockEntityQuantumChest;

public class MenuHandlers {

    public static MenuHandlerMachine<BlockEntityQuantumChest, ContainerQuantumChest> QUANTUM_CHEST_HANDLER = new MenuHandlerMachine(GT4RRef.ID, "container_quantum_chest") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityQuantumChest ? new ContainerQuantumChest((BlockEntityQuantumChest) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenID() {
            return "basic";
        }
    };

    public static MenuHandlerCrafting COVER_CRAFTING_HANDLER = new MenuHandlerCrafting(GT4RRef.ID, "crafting_grid");
    public static MenuHandlerCraftingItem ITEM_CRAFTING_HANDLER = new MenuHandlerCraftingItem(GT4RRef.ID, "crafting_item");

    public static void init(){

    }
}
