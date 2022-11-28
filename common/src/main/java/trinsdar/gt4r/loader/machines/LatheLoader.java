package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;

import static trinsdar.gt4r.data.RecipeMaps.LATHING;

public class LatheLoader {
    public static void init(){
        AntimatterMaterialTypes.ROD.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1) * 80;
            if (m.has(AntimatterMaterialTypes.INGOT)) {
                LATHING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(m, 1)).io(AntimatterMaterialTypes.ROD.get(m,2)).add(duration, 16);
            } else if (m.has(AntimatterMaterialTypes.GEM)){
                LATHING.RB().ii(AntimatterMaterialTypes.GEM.getMaterialIngredient(m, 1)).io(AntimatterMaterialTypes.ROD.get(m,2)).add(duration, 16);
            }
        });
        AntimatterMaterialTypes.SCREW.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            if (m.has(AntimatterMaterialTypes.BOLT)) {
                LATHING.RB().ii(AntimatterMaterialTypes.BOLT.getMaterialIngredient(m, 1)).io(AntimatterMaterialTypes.SCREW.get(m,1)).add(duration, 16);
            }
        });
    }
}
