package trinsdar.gt4r.loader;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.RecipeMaps;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class IntCircuitJeiLoader {
    public static void init(){
        Ingredient all = Ingredient.merge((new ArrayList<>(GT4RData.INT_CIRCUITS.values())));
        for (int i = 0; i < 25; i++){
            RecipeMaps.INT_CIRCUITS.RB().ii(all).io(new ItemStack(GT4RData.INT_CIRCUITS_ITEMS.get(i))).add(1);
        }
    }
}
