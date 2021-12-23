package trinsdar.gt4r.events;

import net.minecraft.client.renderer.Atlases;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.client.MaterialChestRenderer;


@Mod.EventBusSubscriber(modid = Ref.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Atlases.CHEST_LOCATION)) {
            return;
        }

        event.addSprite(MaterialChestRenderer.MATERIAL_CHEST_BASE);
        event.addSprite(MaterialChestRenderer.MATERIAL_CHEST_OVERLAY);
    }
}
