package org.gtreimagined.gt4r.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.DUSTBIN;

public class DustbinLoader {
    public static void init(){
        DUST.all().forEach(m -> {
            if (m.has(DUST_TINY)){
                DUSTBIN.RB().ii(DUST_TINY.getMaterialIngredient(m, 9)).io(DUST.get(m, 1)).add(m.getId() + "_dust_tiny",1);
            }
            if (m.has(DUST_SMALL)){
                DUSTBIN.RB().ii(DUST_SMALL.getMaterialIngredient(m, 4)).io(DUST.get(m, 1)).add(m.getId() + "_dust_small",1);
            }
        });
    }
}
