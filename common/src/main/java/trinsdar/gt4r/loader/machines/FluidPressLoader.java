package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import muramasa.antimatter.data.ForgeCTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static trinsdar.gt4r.data.Materials.FishOil;
import static trinsdar.gt4r.data.Materials.Honey;
import static trinsdar.gt4r.data.Materials.SeedOil;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_PRESS;

public class FluidPressLoader {
    public static void init() {
        FLUID_PRESS.RB()
                .ii(RecipeIngredient.of(ForgeCTags.SEEDS,1))
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
