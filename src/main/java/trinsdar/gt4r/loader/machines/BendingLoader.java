package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.GregTechData.INT_CIRCUITS;
import static trinsdar.gt4r.data.RecipeMaps.BENDING;

public class BendingLoader {
    public static void init() {
        PLATE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            BENDING.RB().ii(INGOT.getMaterialIngredient(t,1)).io(PLATE.get(t,1)).add(duration, 24);
        });
    }
}
