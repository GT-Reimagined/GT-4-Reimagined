package trinsdar.gt4r;

import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.Data.Flint;
import static muramasa.antimatter.Data.NULL;
import static muramasa.antimatter.Data.Wood;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;

public class GT4RLateRegistrar extends AntimatterMod {
    @Override
    public String getId() {
        return "gt4r-late";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        MaterialTags.TOOLS.all().forEach(m -> {
            if (m != Flint && m != NULL && !m.has(RUBBERTOOLS) && m != Wood){
                event.setMaterial(m).flags(GT4RMaterialTags.PICKAXE_HEAD, GT4RMaterialTags.AXE_HEAD, GT4RMaterialTags.SHOVEL_HEAD, GT4RMaterialTags.SWORD_HEAD, GT4RMaterialTags.HOE_HEAD, GT4RMaterialTags.HAMMER_HEAD, GT4RMaterialTags.FILE_HEAD, GT4RMaterialTags.SAW_HEAD);
            }
        });
    }

    @Override
    public int getPriority() {
        return  Integer.MIN_VALUE;
    }
}
