package trinsdar.gt4r;

import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.data.AntimatterMaterials.Flint;
import static muramasa.antimatter.material.Material.NULL;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;

public class GT4RLateRegistrar extends AntimatterMod {
    public GT4RLateRegistrar(){
        super();
        if (AntimatterPlatformUtils.isFabric()){
            this.onRegistrarInit();
        }
    }
    @Override
    public String getId() {
        return "gt4r-late";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
    }

    @Override
    public int getPriority() {
        return  Integer.MIN_VALUE;
    }
}
