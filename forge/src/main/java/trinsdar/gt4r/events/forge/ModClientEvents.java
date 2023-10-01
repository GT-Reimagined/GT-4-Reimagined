package trinsdar.gt4r.events.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.events.ClientEvents;


@Mod.EventBusSubscriber(modid = GT4RRef.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {
    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        ClientEvents.onStitch(event.getAtlas(), event::addSprite);
    }
}
