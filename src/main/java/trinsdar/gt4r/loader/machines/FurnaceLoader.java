package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;

public class FurnaceLoader {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        SimpleCookingRecipeBuilder.blasting(INGOT.getMaterialIngredient(Iron, 1).get(), INGOT.get(WroughtIron), 0.5F, 100).unlockedBy("has_iron_ingot", provider.hasSafeItem(INGOT.getMaterialTag(Iron))).save(output, "iron_to_wrought_bl");
        SimpleCookingRecipeBuilder.smelting(INGOT.getMaterialIngredient(Iron, 1).get(), INGOT.get(WroughtIron), 0.5F, 200).unlockedBy("has_iron_ingot", provider.hasSafeItem(INGOT.getMaterialTag(Iron))).save(output, "iron_to_wrought");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GT4RData.CompressedFireClay, 1).get(), GT4RData.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GT4RData.CompressedFireClay)).save(output, "firebrick");
        DUST.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(INGOT) || m == Tetrahedrite) return;
            SimpleCookingRecipeBuilder.blasting(DUST.getMaterialIngredient(m, 1).get(), INGOT.get(m.getDirectSmeltInto()), 0.5F, 100).unlockedBy("has_" + m.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(m))).save(output, m.getId() + "_dust_to_ingot_bl");
            SimpleCookingRecipeBuilder.smelting(DUST.getMaterialIngredient(m, 1).get(), INGOT.get(m.getDirectSmeltInto()), 0.5F, 200).unlockedBy("has_" + m.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(m))).save(output, m.getId() + "_dust_to_ingot");
        });
        AntimatterCookingRecipeBuilder.blastingRecipe(DUST.getMaterialIngredient(Tetrahedrite, 1).get(), NUGGET.get(Copper, 6), 0.5F, 100).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(Tetrahedrite))).build(output, Tetrahedrite.getId() + "_dust_to_nuggets_bl");
        AntimatterCookingRecipeBuilder.smeltingRecipe(DUST.getMaterialIngredient(Tetrahedrite, 1).get(), NUGGET.get(Copper, 6), 0.5F, 200).addCriterion("has_" + Tetrahedrite.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(Tetrahedrite))).build(output, Tetrahedrite.getId() + "_dust_to_nuggets");
        CRUSHED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            AntimatterCookingRecipeBuilder.blastingRecipe(CRUSHED.getMaterialIngredient(m, 1).get(),NUGGET.get(m.getDirectSmeltInto(), 10), 0.5F, 100).addCriterion("has_" + m.getId() + "_crushed", provider.hasSafeItem(CRUSHED.getMaterialTag(m))).build(output, m.getId() + "_crushed_to_nuggets_bl");
            AntimatterCookingRecipeBuilder.smeltingRecipe(CRUSHED.getMaterialIngredient(m, 1).get(),NUGGET.get(m.getDirectSmeltInto(), 10), 0.5F, 200).addCriterion("has_" + m.getId() + "_crushed", provider.hasSafeItem(CRUSHED.getMaterialTag(m))).build(output, m.getId() + "_crushed_to_nuggets");
        });
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            AntimatterCookingRecipeBuilder.blastingRecipe(CRUSHED_PURIFIED.getMaterialIngredient(m, 1).get(),NUGGET.get(m.getDirectSmeltInto(), 10), 0.5F, 100).addCriterion("has_" + m.getId() + "_crushed_purified", provider.hasSafeItem(CRUSHED.getMaterialTag(m))).build(output, m.getId() + "_crushed_purified_to_nuggets_bl");
            AntimatterCookingRecipeBuilder.smeltingRecipe(CRUSHED_PURIFIED.getMaterialIngredient(m, 1).get(),NUGGET.get(m.getDirectSmeltInto(), 10), 0.5F, 200).addCriterion("has_" + m.getId() + "_crushed_purified", provider.hasSafeItem(CRUSHED.getMaterialTag(m))).build(output, m.getId() + "_crushed_purified_to_nuggets");
        });
    }

    public static void init(){
    }
}
