package trinsdar.gt4r.data.client;

import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerHatch;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenHatch;
import net.minecraft.client.gui.ScreenManager;
import trinsdar.gt4r.gui.ScreenCoalBoiler;
import trinsdar.gt4r.gui.ScreenDistillationTower;
import trinsdar.gt4r.gui.ScreenFusionReactor;
import trinsdar.gt4r.gui.ScreenIBF;

public class ScreenFactories {
    public final static ScreenManager.IScreenFactory SCREEN_IBF = (a, b, c) -> new ScreenIBF((ContainerBasicMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_DISTILLATION_TOWER = (a, b, c) -> new ScreenDistillationTower((ContainerBasicMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_FUSION_REACTOR = (a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_COAL_BOILER = (a, b, c) -> new ScreenCoalBoiler<>((ContainerBasicMachine) a,b,c);
}
