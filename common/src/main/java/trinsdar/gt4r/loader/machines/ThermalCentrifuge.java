package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;

import static muramasa.antimatter.data.AntimatterMaterialTypes.CRUSHED_REFINED;
import static muramasa.antimatter.data.AntimatterMaterialTypes.CRUSHED_PURIFIED;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.RecipeMaps.THERMAL_CENTRIFUGE;

public class ThermalCentrifuge {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m;
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;

            THERMAL_CENTRIFUGE.RB().ii(of(CRUSHED_PURIFIED.get(m),1)).io(CRUSHED_REFINED.get(m, 1), DUST_TINY.get(aOreByProduct2, 1)).add(m.getId() + "_purified_ore",500, 48,0,2);
        });
    }
}
