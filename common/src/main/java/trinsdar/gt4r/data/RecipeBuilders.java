package trinsdar.gt4r.data;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class RecipeBuilders {

    public static class SmeltingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_SMELTING, super.add(domain, id), domain, id);
        }
    }

    public static class MaceratingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_MACERATING, super.add(domain, id), domain, id);
        }
    }

    public static class UniversalMaceratingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSingleOutputMap(RecipeMaps.MACERATING, super.add(domain, id), domain, id);
        }
    }

    public static class ExtractingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_EXTRACTING, super.add(domain, id), domain, id);
        }
    }

    public static class HammeringBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_HAMMERING, super.add(domain, id), domain, id);
        }
    }

    public static class CompressingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_COMPRESSING, super.add(domain, id), domain, id);
        }
    }

    public static class AlloySmeltingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_ALLOY_SMELTING, super.add(domain, id), domain, id);
        }
    }

    public static class GasFuelBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            IRecipe recipe = super.add(domain, id);
            if (recipe.hasInputFluids()) {
                RecipeMaps.LARGE_GAS_FUELS.RB().fi(recipe.getInputFluids().get(0).copyMB(50)).add(domain, id,1, recipe.getDuration() * recipe.getPower() * 50L, recipe.getSpecialValue(), recipe.getAmps());
            }
            return recipe;
        }
    }

    public static IRecipe addRecipeToSteamMap(RecipeMap map, IRecipe recipe, String domain, String id) {
        try {
            if (recipe.getPower() <= Tier.LV.getVoltage()) {
                map.RB().ii(recipe.getInputItems()).io(recipe.getFlatOutputItems()).add(domain, id,recipe.getDuration()* 3L, recipe.getPower() * 2, 0, 1);
            }
        } catch (Exception e) {
            System.out.println("bleh");
        }
        return recipe;
    }

    public static IRecipe addRecipeToSingleOutputMap(RecipeMap map, IRecipe recipe, String domain, String id) {
        try {
            if (recipe.getPower() <= Tier.LV.getVoltage()) {
                map.RB().ii(recipe.getInputItems()).io(Objects.requireNonNull(recipe.getOutputItems())[0]).add(domain, id, recipe.getDuration(), recipe.getPower(), recipe.getSpecialValue(), recipe.getAmps());
            }
        } catch (Exception e) {
            Antimatter.LOGGER.info("Recipe " + recipe.toString() + " Failed");
        }
        return recipe;
    }

    public static class BasicBlastingBuilder extends RecipeBuilder {

        public static ItemStack[] FUELS = new ItemStack[0];

        static {
//            AntimatterAPI.onEvent(RegistrationEvent.DATA_READY, () -> FUELS = new ItemStack[] {
//                MaterialType.GEM.get(Materials.Coal, 1),
//                MaterialType.DUST.get(Materials.Coal, 1),
//                MaterialType.GEM.get(Materials.Charcoal, 1),
//                MaterialType.DUST.get(Materials.Charcoal, 1),
//                MaterialType.GEM.get(Materials.CoalCoke, 1),
//                MaterialType.GEM.get(Materials.LigniteCoke, 1),
//            });
        }

        public void add(ItemStack[] inputs, ItemStack[] outputs, int coal, int duration) {
            duration = 20;//TODO temp
            ItemStack[] inputsCpy = Arrays.copyOf(inputs, inputs.length + 1);
            for (int i = 0; i < FUELS.length; i++) {
                inputsCpy[inputsCpy.length - 1] = Utils.ca(coal, FUELS[i]);
                //TODO uncomment when it would compile
                //ii(AntimatterIngredient.fromStacksList(inputsCpy)).io(outputs).add(duration);
            }
        }
    }
}
