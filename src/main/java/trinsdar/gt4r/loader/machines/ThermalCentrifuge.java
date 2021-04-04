package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;

import static muramasa.antimatter.Data.CRUSHED_CENTRIFUGED;
import static muramasa.antimatter.Data.CRUSHED_PURIFIED;
import static muramasa.antimatter.Data.DUST_TINY;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;
import static trinsdar.gt4r.data.RecipeMaps.THERMAL_CENTRIFUGING;

public class ThermalCentrifuge {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;

            THERMAL_CENTRIFUGING.RB().ii(of(CRUSHED_PURIFIED.get(m),1)).io(CRUSHED_CENTRIFUGED.get(m, 1), DUST_TINY.get(aOreByProduct2, 1)).add(500, 48,0,2);
        });
    }
}
