package trinsdar.gt4r.events.forge;

import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterWorldGenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.events.AntimatterEvents;

@Mod.EventBusSubscriber(modid = GT4RRef.ID)
public class ForgeAntimatterEvents {
    @SubscribeEvent
    public static void registerWorldgen(AntimatterWorldGenEvent event){
        AntimatterEvents.registerWorldgen(event.getEvent());
    }

    @SubscribeEvent
    public static void registerRecipeLoaders(AntimatterLoaderEvent event) {
        AntimatterEvents.registerRecipeLoaders(event.sender, event.registrat);
    }
}
