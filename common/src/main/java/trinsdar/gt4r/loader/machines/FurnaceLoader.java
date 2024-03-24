package trinsdar.gt4r.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.function.Consumer;

import static trinsdar.gt4r.data.Materials.*;

public class FurnaceLoader {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        SimpleCookingRecipeBuilder.blasting(AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1), AntimatterMaterialTypes.INGOT.get(WroughtIron), 0.5F, 100).unlockedBy("has_iron_ingot", provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron))).save(output, GT4RRef.ID + ":iron_to_wrought_bl");
        SimpleCookingRecipeBuilder.smelting(AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1), AntimatterMaterialTypes.INGOT.get(WroughtIron), 0.5F, 200).unlockedBy("has_iron_ingot", provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron))).save(output, GT4RRef.ID + ":iron_to_wrought");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GTCoreItems.CompressedFireClay, 1), GTCoreItems.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GTCoreItems.CompressedFireClay)).save(output, GT4RRef.ID + ":firebrick");
        AntimatterCookingRecipeBuilder.blastingRecipe(AntimatterMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), AntimatterMaterialTypes.NUGGET.get(AntimatterMaterials.Copper, 6), 0.5F, 100).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(AntimatterMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, GT4RRef.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets_bl");
        AntimatterCookingRecipeBuilder.smeltingRecipe(AntimatterMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), AntimatterMaterialTypes.NUGGET.get(AntimatterMaterials.Copper, 6), 0.5F, 200).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(AntimatterMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, GT4RRef.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets");
    }

    public static void init(){
    }
}
