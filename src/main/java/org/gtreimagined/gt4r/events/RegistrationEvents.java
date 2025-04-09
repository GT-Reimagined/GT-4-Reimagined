package org.gtreimagined.gt4r.events;

import org.gtreimagined.gtlib.event.GTCraftingEvent;
import org.gtreimagined.gtlib.event.GTProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.gtreimagined.gt4r.GT4RRef;

@Mod.EventBusSubscriber(modid = GT4RRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationEvents {

    @SubscribeEvent
    public static void onProviders(GTProvidersEvent event){
        AntimatterEvents.onProviders(event);
    }

    @SubscribeEvent
    public static void registerCraftingLoaders(GTCraftingEvent event){
        AntimatterEvents.registerCraftingLoaders(event);
    }
}
