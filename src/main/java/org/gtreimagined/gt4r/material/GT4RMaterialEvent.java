package org.gtreimagined.gt4r.material;

import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.event.MaterialEvent;
import org.gtreimagined.gtlib.material.IMaterialTag;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;

import static org.gtreimagined.gtlib.material.MaterialTags.HAS_CUSTOM_SMELTING;
import static org.gtreimagined.gtlib.material.MaterialTags.METAL;

public class GT4RMaterialEvent extends MaterialEvent<GT4RMaterialEvent> {
    public GT4RMaterialEvent asSolid(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        asSolid(meltingPoint, tags);
        GT4RMaterialTags.BLAST_FURNACE_TEMP.add(material, blastFurnaceTemp);
        if (blastFurnaceTemp >= 1000){
            flags(GT4RMaterialTags.NEEDS_BLAST_FURNACE, HAS_CUSTOM_SMELTING);
        }
        if (blastFurnaceTemp > 1750) {
            flags(GTMaterialTypes.HOT_INGOT);
        }
        return this;
    }

    public GT4RMaterialEvent asMetal(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        flags(METAL);
        asSolid(meltingPoint, blastFurnaceTemp, tags);
        return this;
    }
}
