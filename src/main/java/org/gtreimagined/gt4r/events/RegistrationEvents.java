package org.gtreimagined.gt4r.events;

import muramasa.antimatter.event.AntimatterCraftingEvent;
import muramasa.antimatter.event.AntimatterProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.gtreimagined.gt4r.GT4RRef;

@Mod.EventBusSubscriber(modid = GT4RRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationEvents {

    @SubscribeEvent
    public static void onProviders(AntimatterProvidersEvent event){
        AntimatterEvents.onProviders(event);
    }

    @SubscribeEvent
    public static void registerCraftingLoaders(AntimatterCraftingEvent event){
        AntimatterEvents.registerCraftingLoaders(event);
    }
}
