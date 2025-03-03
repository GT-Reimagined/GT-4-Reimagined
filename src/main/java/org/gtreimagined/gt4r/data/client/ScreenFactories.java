package org.gtreimagined.gt4r.data.client;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.gui.screen.ScreenFusionReactor;

public class ScreenFactories {
    public final static MenuScreens.ScreenConstructor SCREEN_FUSION_REACTOR = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "fusion_reactor", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c));
    public final static MenuScreens.ScreenConstructor SCREEN_CRAFTING_TABLE = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "crafting_table", GT4RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new CraftingScreen((CraftingMenu) a, b, c));

    public static void init(){

    }
}
