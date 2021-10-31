package trinsdar.gt4r.loader;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.RecipeMaps;

import java.util.stream.Collectors;

public class IntCircuitJeiLoader {
    public static void init(){
        RecipeIngredient all = RecipeIngredient.of(Ingredient.merge((GT4RData.INT_CIRCUITS.values().stream().map(RecipeIngredient::get).collect(Collectors.toList()))), 1);
        for (int i = 0; i < 25; i++){
            RecipeMaps.INT_CIRCUITS.RB().ii(all).io(new ItemStack(GT4RData.INT_CIRCUITS_ITEMS.get(i))).add(1);
        }
    }
}
