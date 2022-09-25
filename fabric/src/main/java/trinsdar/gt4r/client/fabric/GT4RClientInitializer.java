package trinsdar.gt4r.client.fabric;

import io.github.fabricators_of_create.porting_lib.event.client.TextureStitchCallback;
import muramasa.antimatter.client.fabric.IAntimatterClientInitializer;
import net.fabricmc.api.ClientModInitializer;
import trinsdar.gt4r.events.ClientEvents;
import trinsdar.gt4r.proxy.ClientHandler;

public class GT4RClientInitializer implements IAntimatterClientInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.setup();
        TextureStitchCallback.PRE.register((ClientEvents::onStitch));
    }
}
