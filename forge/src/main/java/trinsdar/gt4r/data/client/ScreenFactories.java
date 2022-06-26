package trinsdar.gt4r.data.client;

import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.gui.screen.ScreenCabinet;
import trinsdar.gt4r.gui.screen.ScreenChargingMaterialBlock;
import trinsdar.gt4r.gui.screen.ScreenDigitalChest;
import trinsdar.gt4r.gui.screen.ScreenFusionReactor;

public class ScreenFactories {
    public final static MenuScreens.ScreenConstructor SCREEN_FUSION_REACTOR = (a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_DIGITAL_CHEST = (a, b, c) -> new ScreenDigitalChest((ContainerDigitalChest) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_SIX = (a, b, c) -> new ScreenCabinet.ScreenCabinetSix<>((ContainerCabinet) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_SEVEN = (a, b, c) -> new ScreenCabinet.ScreenCabinetSeven<>((ContainerCabinet) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_EIGHT = (a, b, c) -> new ScreenCabinet.ScreenCabinetEight<>((ContainerCabinet) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_NINE = (a, b, c) -> new ScreenCabinet.ScreenCabinetNine<>((ContainerCabinet) a,b,c);
    public final static MenuScreens.ScreenConstructor SCREEN_WORKBENCH = (a, b, c) -> new ScreenChargingMaterialBlock<>((ContainerWorkbench) a,b,c, "workbench");
    public final static MenuScreens.ScreenConstructor SCREEN_LOCKER = (a, b, c) -> new ScreenChargingMaterialBlock<>((ContainerBasicMachine) a,b,c, "locker");
    public final static MenuScreens.ScreenConstructor SCREEN_CRAFTING_TABLE = (a, b, c) -> new CraftingScreen((CraftingMenu) a, b, c);

}
