package org.gtreimagined.gt4r.loader.machines;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.DUSTBIN;

public class DustbinLoader {
    public static void init(){
        DUST.all().forEach(m -> {
            if (m.has(TINY_DUST)){
                DUSTBIN.RB().ii(TINY_DUST.getMaterialIngredient(m, 9)).io(DUST.get(m, 1)).add(m.getId() + "_dust_tiny",1);
            }
            if (m.has(SMALL_DUST)){
                DUSTBIN.RB().ii(SMALL_DUST.getMaterialIngredient(m, 4)).io(DUST.get(m, 1)).add(m.getId() + "_dust_small",1);
            }
        });
    }
}
