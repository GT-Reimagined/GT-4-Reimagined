package trinsdar.gt4r.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_SMALL;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static trinsdar.gt4r.data.RecipeMaps.DUSTBIN;

public class DustbinLoader {
    public static void init(){
        DUST.all().forEach(m -> {
            if (m.has(DUST_TINY)){
                DUSTBIN.RB().ii(DUST_TINY.getMaterialIngredient(m, 9)).io(DUST.get(m, 1)).add(1);
            }
            if (m.has(DUST_SMALL)){
                DUSTBIN.RB().ii(DUST_SMALL.getMaterialIngredient(m, 4)).io(DUST.get(m, 1)).add(1);
            }
        });
    }
}
