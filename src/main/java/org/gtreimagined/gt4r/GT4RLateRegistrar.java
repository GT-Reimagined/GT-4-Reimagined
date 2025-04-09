package org.gtreimagined.gt4r;

import org.gtreimagined.gtlib.GTMod;
import org.gtreimagined.gtlib.event.MaterialEvent;
import org.gtreimagined.gtlib.registration.RegistrationEvent;
import net.minecraftforge.api.distmarker.Dist;

public class GT4RLateRegistrar extends GTMod {
    @Override
    public String getId() {
        return "gt4r-late";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Dist side) {

    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
    }

    @Override
    public int getPriority() {
        return  Integer.MIN_VALUE;
    }
}
