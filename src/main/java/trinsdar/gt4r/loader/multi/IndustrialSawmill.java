package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.DUST;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Lubricant;
import static trinsdar.gt4r.data.Materials.Wood;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_SAWMILLING;

public class IndustrialSawmill {
    public static void init(){
        addWoodRecipe(ItemTags.OAK_LOGS, Items.OAK_PLANKS);
        addWoodRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
        addWoodRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
        addWoodRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
        addWoodRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
        addWoodRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
        addWoodRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
        addWoodRecipe(ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GT4RData.RUBBER_LOG, 1)).fi(new FluidStack(Fluids.WATER, 40)).io(new ItemStack(GT4RData.StickyResin), DUST.get(Wood, 8), new ItemStack(Items.JUNGLE_PLANKS, 9)).add(200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GT4RData.RUBBER_LOG, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(GT4RData.StickyResin), DUST.get(Wood, 8), new ItemStack(Items.JUNGLE_PLANKS, 9)).add(100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GT4RData.RUBBER_LOG, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(GT4RData.StickyResin), DUST.get(Wood, 8), new ItemStack(Items.JUNGLE_PLANKS, 9)).add(200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(new FluidStack(Fluids.WATER, 40)).io(new ItemStack(Items.MELON_SLICE, 7)).add(200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(Items.MELON_SLICE, 7)).add(200, 30);
    }


    private static void addWoodRecipe(ITag.INamedTag<Item> log, IItemProvider wood){
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(new FluidStack(Fluids.WATER, 40)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(200, 30);
    }
}
