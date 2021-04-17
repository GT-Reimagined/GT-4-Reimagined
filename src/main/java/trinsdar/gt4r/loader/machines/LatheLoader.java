package trinsdar.gt4r.loader.machines;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.RecipeMaps.LATHING;

public class LatheLoader {
    public static void init(){
        ROD.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1) * 80;
            if (m.has(INGOT)) {
                LATHING.RB().ii(INGOT.getMaterialIngredient(m, 1)).io(ROD.get(m,2)).add(duration, 16);
            } else if (m.has(GEM)){
                LATHING.RB().ii(GEM.getMaterialIngredient(m, 1)).io(ROD.get(m,2)).add(duration, 16);
            }
        });
        SCREW.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            if (m.has(BOLT)) {
                LATHING.RB().ii(BOLT.getMaterialIngredient(m, 1)).io(SCREW.get(m,1)).add(duration, 16);
            }
        });
    }
}
