package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.FERMENTER;

public class FermentingLoader {
    public static void init(){
        FERMENTER.RB().fi(Water.getLiquid(1000)).ii(RecipeIngredient.of(GTCoreItems.Biochaff, 1)).fo(Biomass.getLiquid(1000)).add("biomass",128, 16);
        FERMENTER.RB().fi(Honey.getLiquid(1000)).ii(RecipeIngredient.of(GTCoreItems.Biochaff, 1)).fo(Biomass.getLiquid(1500)).add("biomass_1",128, 16);
        FERMENTER.RB().fi(Biomass.getLiquid(6)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(0)).fo(Methane.getGas(4)).add("methane",20, 16);
    }
}
