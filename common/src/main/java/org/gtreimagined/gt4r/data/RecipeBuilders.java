package org.gtreimagined.gt4r.data;

import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.map.RecipeBuilder;

public class RecipeBuilders {

    public static class GasFuelBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            IRecipe recipe = super.add(domain, id);
            if (recipe.hasInputFluids()) {
                RecipeMaps.LARGE_GAS_FUELS.RB().fi(recipe.getInputFluids().get(0).copy(50)).add(domain, id,1, recipe.getDuration() * recipe.getPower() * 50L, recipe.getSpecialValue(), recipe.getAmps());
            }
            return recipe;
        }
    }
}
