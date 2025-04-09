package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.datagen.builder.GTCookingRecipeBuilder;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;

import java.util.function.Consumer;

import static org.gtreimagined.gt4r.data.Materials.Tetrahedrite;
import static org.gtreimagined.gt4r.data.Materials.WroughtIron;

public class FurnaceLoader {
    public static void loadRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider){
        SimpleCookingRecipeBuilder.blasting(GTMaterialTypes.INGOT.getMaterialIngredient(GTLibMaterials.Iron, 1), GTMaterialTypes.INGOT.get(WroughtIron), 0.5F, 100).unlockedBy("has_iron_ingot", provider.hasSafeItem(GTMaterialTypes.INGOT.getMaterialTag(GTLibMaterials.Iron))).save(output, GT4RRef.ID + ":iron_to_wrought_bl");
        SimpleCookingRecipeBuilder.smelting(GTMaterialTypes.INGOT.getMaterialIngredient(GTLibMaterials.Iron, 1), GTMaterialTypes.INGOT.get(WroughtIron), 0.5F, 200).unlockedBy("has_iron_ingot", provider.hasSafeItem(GTMaterialTypes.INGOT.getMaterialTag(GTLibMaterials.Iron))).save(output, GT4RRef.ID + ":iron_to_wrought");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GTCoreItems.CompressedFireClay, 1), GTCoreItems.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GTCoreItems.CompressedFireClay)).save(output, GT4RRef.ID + ":firebrick");
        GTCookingRecipeBuilder.blastingRecipe(GTMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), GTMaterialTypes.NUGGET.get(GTLibMaterials.Copper, 6), 0.5F, 100).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(GTMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, GT4RRef.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets_bl");
        GTCookingRecipeBuilder.smeltingRecipe(GTMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), GTMaterialTypes.NUGGET.get(GTLibMaterials.Copper, 6), 0.5F, 200).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(GTMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, GT4RRef.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets");
    }

    public static void init(){
    }
}
