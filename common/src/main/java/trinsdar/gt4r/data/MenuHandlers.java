package trinsdar.gt4r.data;

import io.github.gregtechintergalactical.gtutility.blockentity.BlockEntityMaterial;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.world.entity.player.Inventory;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.gui.ContainerQuantumChest;
import trinsdar.gt4r.gui.MenuHandlerCrafting;
import trinsdar.gt4r.gui.MenuHandlerCraftingItem;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;
import trinsdar.gt4r.tile.single.TileEntityDigitalChest;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;

public class MenuHandlers {
    public static MenuHandlerMachine<TileEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "fusion_reactor";
        }
    };
    public static MenuHandlerMachine<? extends BlockEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SIX = new MenuHandlerMachine(Ref.ID, "container_cabinet_six") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMaterial ? new ContainerCabinet((BlockEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "cabinet_six";
        }
    };
    public static MenuHandlerMachine<? extends BlockEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SEVEN = new MenuHandlerMachine(Ref.ID, "container_cabinet_seven") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMaterial ? new ContainerCabinet((BlockEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "cabinet_seven";
        }
    };
    public static MenuHandlerMachine<? extends BlockEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_EIGHT = new MenuHandlerMachine(Ref.ID, "container_cabinet_eight") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMaterial ? new ContainerCabinet((BlockEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "cabinet_eight";
        }
    };
    public static MenuHandlerMachine<? extends BlockEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_NINE = new MenuHandlerMachine(Ref.ID, "container_cabinet_nine") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMaterial ? new ContainerCabinet((BlockEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenDomain() {
            return Ref.ID;
        }

        @Override
        public String screenID() {
            return "cabinet_nine";
        }
    };

    public static MenuHandlerMachine<TileEntityQuantumChest, ContainerQuantumChest> QUANTUM_CHEST_HANDLER = new MenuHandlerMachine(Ref.ID, "container_quantum_chest") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof TileEntityQuantumChest ? new ContainerQuantumChest((TileEntityQuantumChest) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenID() {
            return "basic";
        }
    };

    public static MenuHandlerMachine<TileEntityDigitalChest, ContainerDigitalChest> DIGITAL_CHEST_HANDLER = new MenuHandlerMachine(Ref.ID, "container_digital_chest") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof TileEntityDigitalChest ? new ContainerDigitalChest((TileEntityDigitalChest) tile, playerInv, this, windowId) : null;
        }

        @Override
        public String screenID() {
            return "digital_chest";
        }

        @Override
        public String screenDomain() {
            return Ref.ID;
        }
    };
    public static MenuHandlerCrafting COVER_CRAFTING_HANDLER = new MenuHandlerCrafting(Ref.ID, "crafting_grid");
    public static MenuHandlerCraftingItem ITEM_CRAFTING_HANDLER = new MenuHandlerCraftingItem(Ref.ID, "crafting_item");

    public static void init(){

    }
}
