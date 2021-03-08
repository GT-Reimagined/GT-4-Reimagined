package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import trinsdar.gt4r.data.Materials;

import static trinsdar.gt4r.data.RecipeMaps.HAMMERING;
import static muramasa.antimatter.Data.*;

public class ForgeHammerLoader {
    public static void init(){
        HAMMERING.RB().ii(AntimatterIngredient.of(INGOT.get(Materials.Aluminium, 1))).io(PLATE.get(Materials.Aluminium, 1)).add(200, 2);
    }
}
