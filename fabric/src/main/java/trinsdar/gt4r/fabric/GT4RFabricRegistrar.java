package trinsdar.gt4r.fabric;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.proxy.CommonHandler;

public class GT4RFabricRegistrar extends AntimatterMod {

    @Override
    public String getId() {
        return Ref.ID + "_fabric";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_READY){
            CommonHandler.setup();
        }
    }

    @Override
    public int getPriority() {
        return 700;
    }
}
