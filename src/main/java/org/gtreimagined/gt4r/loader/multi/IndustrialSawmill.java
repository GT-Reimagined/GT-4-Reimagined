package org.gtreimagined.gt4r.loader.multi;

import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gtlib.util.RegistryUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreTags;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.DUST;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Water;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Wood;
import static org.gtreimagined.gt4r.data.Materials.DistilledWater;
import static org.gtreimagined.gt4r.data.Materials.Lubricant;
import static org.gtreimagined.gt4r.data.RecipeMaps.*;

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
        addWoodRecipe(GTCoreTags.RUBBER_LOGS, GTCoreBlocks.RUBBER_PLANKS, new ItemStack(GTCoreItems.StickyResin));
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(Water.getLiquid(40)).io(new ItemStack(Items.MELON_SLICE, 7)).add("melon",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(Items.MELON_SLICE, 7)).add("melon_1",200, 30);
    }


    private static void addWoodRecipe(TagKey<Item> log, ItemLike wood){
        String woodID = RegistryUtils.getIdFromItem(wood.asItem()).getPath().replace("_planks", "");
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Water.getLiquid(40)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log_1",100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log_2",200, 30);
        PLATE_CUTTER.RB().ii(RecipeIngredient.of(log, 1)).fi(Water.getLiquid(3)).io(new ItemStack(wood, 4), DUST.get(Wood, 1)).add(woodID + "_log",200, 30);
    }

    private static void addWoodRecipe(TagKey<Item> log, ItemLike wood, ItemStack output){
        String woodID = RegistryUtils.getIdFromItem(wood.asItem()).getPath().replace("_planks", "");
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Water.getLiquid(40)).io(new ItemStack(wood, 6), DUST.get(Wood, 1), output).add(woodID + "_log",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(wood, 6), DUST.get(Wood, 1), output).add(woodID + "_log_1",100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(wood, 6), DUST.get(Wood, 1), output).add(woodID + "_log_2",200, 30);
        PLATE_CUTTER.RB().ii(RecipeIngredient.of(log, 1)).fi(Water.getLiquid(3)).io(new ItemStack(wood, 4), DUST.get(Wood, 1)).add(woodID + "_log",200, 30);
    }
}
