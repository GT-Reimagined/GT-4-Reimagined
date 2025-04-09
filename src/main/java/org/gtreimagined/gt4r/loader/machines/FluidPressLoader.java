package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.FLUID_PRESS;

public class FluidPressLoader {
    public static void init() {
        FLUID_PRESS.RB()
                .ii(RecipeIngredient.of(Tags.Items.SEEDS,1))
                .fo(SeedOil.getLiquid(10))
                .add("seed_oil",32, 2);
        FLUID_PRESS.RB()
                .ii(RecipeIngredient.of(ItemTags.FISHES,1))
                .fo(FishOil.getLiquid(10))
                .add("fish_oil",32, 2);
        FLUID_PRESS.RB()
                .ii(RecipeIngredient.of(Items.HONEY_BOTTLE,1))
                .fo(Honey.getLiquid(250))
                .io(new ItemStack(Items.GLASS_BOTTLE))
                .add("honey",32, 2);
    }
}
