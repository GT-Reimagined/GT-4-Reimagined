package trinsdar.gt4r.events.forge;

import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.events.AntimatterEvents;

@Mod.EventBusSubscriber(modid = Ref.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationEvents {

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent e) {
        GT4RConfig.onModConfigEvent(e.getConfig());
    }

    @SubscribeEvent
    public static void onProviders(AntimatterProvidersEvent event){
        AntimatterEvents.onProviders(event.getEvent());
    }

    @SubscribeEvent
    public static void registerCraftingLoaders(AntimatterCraftingEvent event){
        AntimatterEvents.registerCraftingLoaders(event.getEvent());
    }
}
