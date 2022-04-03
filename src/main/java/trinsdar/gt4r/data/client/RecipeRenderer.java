package trinsdar.gt4r.data.client;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.integration.jei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jei.renderer.InfoRenderers;
import muramasa.antimatter.recipe.Recipe;
import net.minecraft.client.gui.Font;
import trinsdar.gt4r.data.RecipeMaps;

import java.util.Objects;

public class RecipeRenderer {
    static final IRecipeInfoRenderer FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, Recipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String fuelPerMb = "Fuel content(mb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount());
            String fuelPerB = "Fuel content(bb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount()) * 1000;
            renderString(stack, fuelPerMb, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, fuelPerB, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, "Ticks: " + recipe.getDuration(), fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
        }
    };

    static final IRecipeInfoRenderer INT_CIRCUIT_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, Recipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            renderString(stack, "Right click to cycle", fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
        }
    };

    static final IRecipeInfoRenderer LARGE_FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, Recipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String fuelPerMb = "Fuel content(mb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount());
            String fuelPerB = "Fuel content(bb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount()) * 1000;
            renderString(stack, fuelPerMb, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, fuelPerB, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, "Base total eu/tick generated: " + recipe.getPower(), fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, "Ticks: " + recipe.getDuration(), fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
        }
    };

    public static final IRecipeInfoRenderer HOT_FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, Recipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0) return;
            String power = "Duration: " + recipe.getDuration() + " ticks";
            String temperature = "HU: " + recipe.getSpecialValue();
            renderString(stack, power,fontRenderer, 5, 0,guiOffsetX,guiOffsetY);
            renderString(stack, temperature,fontRenderer, 5, 10,guiOffsetX,guiOffsetY);
        }
    };

    public static void clientMaps() {
        RecipeMaps.ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        RecipeMaps.INT_CIRCUITS.setInfoRenderer(INT_CIRCUIT_RENDERER);
        RecipeMaps.THERMAL_BOILER_FUELS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        RecipeMaps.STEAM_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.GAS_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.LARGE_STEAM_FUELS.setInfoRenderer(LARGE_FUEL_RENDERER);
        RecipeMaps.LARGE_GAS_FUELS.setInfoRenderer(LARGE_FUEL_RENDERER);
        RecipeMaps.DIESEL_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.HOT_FUELS.setInfoRenderer(HOT_FUEL_RENDERER);
        RecipeMaps.SEMIFLUID_FUELS.setInfoRenderer(FUEL_RENDERER);

        RecipeMaps.BLASTING.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        RecipeMaps.STEAM_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_COMPRESSING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_ALLOY_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_EXTRACTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_MACERATING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_HAMMERING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
    }
}
