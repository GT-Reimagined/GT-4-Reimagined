package trinsdar.gt4r.data;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.entity.player.PlayerInventory;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.client.ScreenFactories;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.gui.MenuHandlerCrafting;
import trinsdar.gt4r.gui.MenuHandlerCraftingItem;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

public class MenuHandlers {
    public static MenuHandlerMachine<TileEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_FUSION_REACTOR;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SIX = new MenuHandlerMachine(Ref.ID, "container_cabinet_six") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SIX;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SEVEN = new MenuHandlerMachine(Ref.ID, "container_cabinet_seven") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SEVEN;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_EIGHT = new MenuHandlerMachine(Ref.ID, "container_cabinet_eight") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_EIGHT;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_NINE = new MenuHandlerMachine(Ref.ID, "container_cabinet_nine") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_NINE;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerWorkbench> WORKBENCH_HANDLER = new MenuHandlerMachine(Ref.ID, "container_workbench") {
        @Override
        public ContainerWorkbench getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerWorkbench((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_WORKBENCH;
        }
    };
    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerBasicMachine> LOCKER_HANDLER = new MenuHandlerMachine(Ref.ID, "container_locker") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerBasicMachine<>((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_LOCKER;
        }
    };
    public static MenuHandlerCrafting COVER_CRAFTING_HANDLER = new MenuHandlerCrafting(Ref.ID, "crafting_grid");
    public static MenuHandlerCraftingItem ITEM_CRAFTING_HANDLER = new MenuHandlerCraftingItem(Ref.ID, "crafting_item");

    public static void init(){

    }
}
