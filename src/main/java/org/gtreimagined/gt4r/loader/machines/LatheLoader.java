package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.LATHE;

public class LatheLoader {
    public static void init(){
        ROD.all().stream().filter(m -> m.has(INGOT) || m.has(GEM)).forEach(m -> {
            long duration = Math.max(m.getMass(), 1) * 80;
            RecipeIngredient input = m.has(INGOT) ? INGOT.getMaterialIngredient(m, 1) : GEM.getMaterialIngredient(m , 1);
            LATHE.RB().ii(input).io(ROD.get(m,2)).add(m.getId() + "_rod", duration, 16);
        });
        SCREW.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            LATHE.RB().ii(BOLT.getMaterialIngredient(m, 1)).io(SCREW.get(m,1)).add(m.getId() + "_screw", duration, 16);
        });
    }
}
