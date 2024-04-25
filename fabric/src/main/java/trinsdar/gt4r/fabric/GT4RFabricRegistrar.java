package trinsdar.gt4r.fabric;

import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.proxy.CommonHandler;

public class GT4RFabricRegistrar extends AntimatterMod {

    @Override
    public String getId() {
        return GT4RRef.ID + "_fabric";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
    }

    @Override
    public int getPriority() {
        return 700;
    }
}
