package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tree.RubberTree;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.NUGGET;
import static trinsdar.gt4r.data.Materials.Iron;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.data.Materials.Tetrahedrite;
import static trinsdar.gt4r.data.Materials.WroughtIron;
import static trinsdar.gt4r.data.RecipeMaps.SMELTING;
import static trinsdar.gt4r.data.RecipeMaps.STEAM_SMELTING;

public class FurnaceLoader {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        CookingRecipeBuilder.blastingRecipe(INGOT.getMaterialIngredient(Iron, 1).get(), INGOT.get(WroughtIron), 0.5F, 100).addCriterion("has_iron_ingot", provider.hasSafeItem(INGOT.getMaterialTag(Iron)).get()).build(output, "iron_to_wrought_bl");
        CookingRecipeBuilder.smeltingRecipe(INGOT.getMaterialIngredient(Iron, 1).get(), INGOT.get(WroughtIron), 0.5F, 200).addCriterion("has_iron_ingot", provider.hasSafeItem(INGOT.getMaterialTag(Iron)).get()).build(output, "iron_to_wrought");
        CookingRecipeBuilder.smeltingRecipe(AntimatterIngredient.of(GT4RData.CompressedFireClay, 1).get(), GT4RData.FireBrick, 0.5F, 200).addCriterion("has_compressed_fire_clay", provider.hasSafeItem(GT4RData.CompressedFireClay).get()).build(output, "firebrick");
        DUST.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(INGOT) || m == Tetrahedrite) return;
            CookingRecipeBuilder.blastingRecipe(DUST.getMaterialIngredient(m, 1).get(), INGOT.get(m.getDirectSmeltInto()), 0.5F, 100).addCriterion("has_" + m.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(m)).get()).build(output, m.getId() + "_dust_to_ingot_bl");
            CookingRecipeBuilder.smeltingRecipe(DUST.getMaterialIngredient(m, 1).get(), INGOT.get(m.getDirectSmeltInto()), 0.5F, 200).addCriterion("has_" + m.getId() + "_dust", provider.hasSafeItem(DUST.getMaterialTag(m)).get()).build(output, m.getId() + "_dust_to_ingot");
        });
    }

    public static void init(){
        CRUSHED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            SMELTING.RB().ii(CRUSHED.getMaterialIngredient(m, 1)).io(NUGGET.get(m.getDirectSmeltInto(), 10)).add(60, 4);
        });
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            SMELTING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(m, 1)).io(NUGGET.get(m.getDirectSmeltInto(), 10)).add(60, 4);
        });
    }
}
