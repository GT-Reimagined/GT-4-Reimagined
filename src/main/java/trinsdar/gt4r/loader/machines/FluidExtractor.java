package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Item;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
    }

    private static void add(Material m, Item i, int amount) {
        if (!m.has(LIQUID)) return;
        FLUID_EXTRACTING.RB()
                .ii(AntimatterIngredient.of(i,1))
                .fo(m.getLiquid(amount))
                .add(m.getMass()*amount/144, 64);
    }
}
