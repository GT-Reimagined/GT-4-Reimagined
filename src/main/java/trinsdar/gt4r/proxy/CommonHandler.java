package trinsdar.gt4r.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import trinsdar.gt4r.events.CommonEvents;

public class CommonHandler {

    public static void setup(FMLCommonSetupEvent e){
        //MinecraftForge.EVENT_BUS.register(CommonEvents.class);
    }
}
