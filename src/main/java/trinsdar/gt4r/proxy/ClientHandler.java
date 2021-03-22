package trinsdar.gt4r.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.client.ScreenSetup;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerCover;
import muramasa.antimatter.gui.container.ContainerHatch;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenBasicMachine;
import muramasa.antimatter.gui.screen.ScreenHatch;
import muramasa.antimatter.gui.screen.ScreenMultiMachine;
import muramasa.antimatter.gui.screen.ScreenSteamMachine;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.data.GT4RData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import trinsdar.gt4r.data.Guis;
import trinsdar.gt4r.gui.ScreenButtonBackgroundCover;
import trinsdar.gt4r.gui.ScreenCoalBoiler;
import trinsdar.gt4r.gui.ScreenDistillationTower;
import trinsdar.gt4r.gui.ScreenFusionReactor;
import trinsdar.gt4r.gui.ScreenHatchCustom;

public class ClientHandler {

    @SuppressWarnings("RedundantTypeArguments")
    public static void setup(FMLClientSetupEvent e) {
        AntimatterAPI.runLaterClient(() -> {
            RenderTypeLookup.setRenderLayer(GT4RData.RUBBER_SAPLING, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(GT4RData.RUBBER_LEAVES, RenderType.getCutout());
            //AntimatterAPI.all(BlockCasingMachine.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
            AntimatterAPI.all(BlockCasing.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
        });

        ScreenSetup.<ContainerCover, ScreenButtonBackgroundCover<ContainerCover>>setScreenMapping(Data.COVER_MENU_HANDLER, ScreenButtonBackgroundCover::new);
        ScreenSetup.<ContainerHatch, ScreenHatchCustom<ContainerHatch>>setScreenMapping(Guis.HATCH_MENU_HANDLER_CUSTOM, ScreenHatchCustom::new);
        ScreenSetup.<ContainerBasicMachine, ScreenCoalBoiler<ContainerBasicMachine>>setScreenMapping(Guis.COAL_BOILER_MENU_HANDLER, ScreenCoalBoiler::new);
        ScreenSetup.<ContainerMultiMachine, ScreenFusionReactor<ContainerMultiMachine>>setScreenMapping(Guis.FUSION_MENU_HANDLER, ScreenFusionReactor::new);
        ScreenSetup.<ContainerMultiMachine, ScreenDistillationTower>setScreenMapping(Guis.DISTILLATION_MENU_HANDLER, ScreenDistillationTower::new);
    }
}
