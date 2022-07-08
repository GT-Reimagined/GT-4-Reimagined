package trinsdar.gt4r.fabric;

import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.event.fabric.MaterialEvents;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.GT4RMaterialEvents;

public class GT4RFabricRegistrar extends AntimatterMod {
    @Override
    public void onRegistrarInit() {
        super.onRegistrarInit();
        MaterialEvents.MATERIAL.register(GT4RMaterialEvents::onMaterialEvent);
    }

    @Override
    public String getId() {
        return Ref.ID + "_fabric";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_READY){
            FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
            flammableRegistry.add(GT4RData.RUBBER_LOG, 5, 5);
            flammableRegistry.add(GT4RData.RUBBER_LEAVES, 60, 30);
        }
    }

    @Override
    public int getPriority() {
        return 700;
    }
}
