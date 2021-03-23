package trinsdar.gt4r.loader.machines;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import trinsdar.gt4r.tree.RubberTree;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static trinsdar.gt4r.data.Materials.Rubber;

public class FurnaceLoader {
    public static void init(){
        CookingRecipeBuilder.smeltingRecipe(DUST.getIngredient(Rubber, 1).get(), INGOT.get(Rubber), 0.5F, 200);
    }
}
