package trinsdar.gt4r.data.client;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.gui.screen.ScreenCabinet;
import trinsdar.gt4r.gui.screen.ScreenDigitalChest;
import trinsdar.gt4r.gui.screen.ScreenFusionReactor;

public class ScreenFactories {
    public final static MenuScreens.ScreenConstructor SCREEN_FUSION_REACTOR = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "fusion_reactor", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_DIGITAL_CHEST = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "digital_chest", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenDigitalChest((ContainerDigitalChest) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_SIX = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "cabinet_six", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCabinet.ScreenCabinetSix<>((ContainerCabinet) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_SEVEN = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "cabinet_seven", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCabinet.ScreenCabinetSeven<>((ContainerCabinet) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_EIGHT = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "cabinet_eight", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCabinet.ScreenCabinetEight<>((ContainerCabinet) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CABINET_NINE = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "cabinet_nine", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCabinet.ScreenCabinetNine<>((ContainerCabinet) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CRAFTING_TABLE = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "crafting_table", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new CraftingScreen((CraftingMenu) a, b, c));

    public static void init(){

    }
}
