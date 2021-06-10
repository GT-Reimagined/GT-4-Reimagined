package trinsdar.gt4r.data.client;

import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import net.minecraft.client.gui.ScreenManager;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.gui.screen.ScreenCabinet;
import trinsdar.gt4r.gui.screen.ScreenChargingMaterialBlock;
import trinsdar.gt4r.gui.screen.ScreenCoalBoiler;
import trinsdar.gt4r.gui.screen.ScreenDistillationTower;
import trinsdar.gt4r.gui.screen.ScreenFusionReactor;
import trinsdar.gt4r.gui.screen.ScreenIBF;

public class ScreenFactories {
    public final static ScreenManager.IScreenFactory SCREEN_IBF = (a, b, c) -> new ScreenIBF((ContainerBasicMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_DISTILLATION_TOWER = (a, b, c) -> new ScreenDistillationTower((ContainerBasicMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_FUSION_REACTOR = (a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_COAL_BOILER = (a, b, c) -> new ScreenCoalBoiler<>((ContainerBasicMachine) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_CABINET_SIX = (a, b, c) -> new ScreenCabinet.ScreenCabinetSix<>((ContainerCabinet) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_CABINET_SEVEN = (a, b, c) -> new ScreenCabinet.ScreenCabinetSeven<>((ContainerCabinet) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_CABINET_EIGHT = (a, b, c) -> new ScreenCabinet.ScreenCabinetEight<>((ContainerCabinet) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_CABINET_NINE = (a, b, c) -> new ScreenCabinet.ScreenCabinetNine<>((ContainerCabinet) a,b,c);
    public final static ScreenManager.IScreenFactory SCREEN_WORKBENCH = (a, b, c) -> new ScreenChargingMaterialBlock<>((ContainerWorkbench) a,b,c, "workbench");
    public final static ScreenManager.IScreenFactory SCREEN_LOCKER = (a, b, c) -> new ScreenChargingMaterialBlock<>((ContainerWorkbench) a,b,c, "locker");

}
