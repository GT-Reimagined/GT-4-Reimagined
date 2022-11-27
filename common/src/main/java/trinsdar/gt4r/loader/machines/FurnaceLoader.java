package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static trinsdar.gt4r.data.Materials.*;

public class FurnaceLoader {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        SimpleCookingRecipeBuilder.blasting(AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1), AntimatterMaterialTypes.INGOT.get(WroughtIron), 0.5F, 100).unlockedBy("has_iron_ingot", provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron))).save(output, Ref.ID + ":iron_to_wrought_bl");
        SimpleCookingRecipeBuilder.smelting(AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1), AntimatterMaterialTypes.INGOT.get(WroughtIron), 0.5F, 200).unlockedBy("has_iron_ingot", provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron))).save(output, Ref.ID + ":iron_to_wrought");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GT4RData.CompressedFireClay, 1), GT4RData.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GT4RData.CompressedFireClay)).save(output, Ref.ID + ":firebrick");
        AntimatterCookingRecipeBuilder.blastingRecipe(AntimatterMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), AntimatterMaterialTypes.NUGGET.get(AntimatterMaterials.Copper, 6), 0.5F, 100).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(AntimatterMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, Ref.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets_bl");
        AntimatterCookingRecipeBuilder.smeltingRecipe(AntimatterMaterialTypes.DUST.getMaterialIngredient(Tetrahedrite, 1), AntimatterMaterialTypes.NUGGET.get(AntimatterMaterials.Copper, 6), 0.5F, 200).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(AntimatterMaterialTypes.DUST.getMaterialTag(Tetrahedrite))).build(output, Ref.ID + ":" + Tetrahedrite.getId() + "_dust_to_nuggets");
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            if (m.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!MaterialTags.DIRECT_SMELT_INTO.getMapping(m).has(AntimatterMaterialTypes.NUGGET)) return;
            AntimatterCookingRecipeBuilder.blastingRecipe(AntimatterMaterialTypes.CRUSHED.getMaterialIngredient(m, 1), AntimatterMaterialTypes.NUGGET.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 10), 0.5F, 100).addCriterion("has_" + m.getId() + "_crushed", provider.hasSafeItem(AntimatterMaterialTypes.CRUSHED.getMaterialTag(m))).build(output, Ref.ID + ":" + m.getId() + "_crushed_to_nuggets_bl");
            AntimatterCookingRecipeBuilder.smeltingRecipe(AntimatterMaterialTypes.CRUSHED.getMaterialIngredient(m, 1), AntimatterMaterialTypes.NUGGET.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 10), 0.5F, 200).addCriterion("has_" + m.getId() + "_crushed", provider.hasSafeItem(AntimatterMaterialTypes.CRUSHED.getMaterialTag(m))).build(output, Ref.ID + ":" + m.getId() + "_crushed_to_nuggets");
        });
        AntimatterMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            if (m.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!MaterialTags.DIRECT_SMELT_INTO.getMapping(m).has(AntimatterMaterialTypes.NUGGET)) return;
            AntimatterCookingRecipeBuilder.blastingRecipe(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialIngredient(m, 1), AntimatterMaterialTypes.NUGGET.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 10), 0.5F, 100).addCriterion("has_" + m.getId() + "_crushed_purified", provider.hasSafeItem(AntimatterMaterialTypes.CRUSHED.getMaterialTag(m))).build(output, Ref.ID + ":" + m.getId() + "_crushed_purified_to_nuggets_bl");
            AntimatterCookingRecipeBuilder.smeltingRecipe(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialIngredient(m, 1), AntimatterMaterialTypes.NUGGET.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 10), 0.5F, 200).addCriterion("has_" + m.getId() + "_crushed_purified", provider.hasSafeItem(AntimatterMaterialTypes.CRUSHED.getMaterialTag(m))).build(output, Ref.ID + ":" + m.getId() + "_crushed_purified_to_nuggets");
        });
    }

    public static void init(){
    }
}
