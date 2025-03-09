package org.gtreimagined.gt4r.data.client;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.integration.jeirei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jeirei.renderer.InfoRenderers;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import net.minecraft.client.gui.Font;
import org.gtreimagined.gt4r.data.RecipeMaps;

public class RecipeRenderer {
    static final IRecipeInfoRenderer FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String fuelPerMb = "Fuel content(mb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount());
            String fuelPerB = "Fuel content(bb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) recipe.getInputFluids().get(0).getAmount()) * 1000;
            renderString(stack, fuelPerMb, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, fuelPerB, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, "Ticks: " + recipe.getDuration(), fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
        }
    };

    static final IRecipeInfoRenderer INT_CIRCUIT_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            renderString(stack, "Right click to cycle", fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
        }
    };

    static final IRecipeInfoRenderer FLUID_EXTRACTOR_COILS_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            renderString(stack, "Heat capacity provided by coil: " + recipe.getSpecialValue(), fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
        }
    };

    static final IRecipeInfoRenderer LARGE_FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
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
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0) return;
            String power = "Duration: " + recipe.getDuration() + " ticks";
            String temperature = "HU: " + recipe.getSpecialValue();
            renderString(stack, power,fontRenderer, 5, 0,guiOffsetX,guiOffsetY);
            renderString(stack, temperature,fontRenderer, 5, 10,guiOffsetX,guiOffsetY);
        }
    };

    public static final IRecipeInfoRenderer STEAM_RENDERER = new IRecipeInfoRenderer() {
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0 && recipe.getPower() == 0) return;
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String power = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String euT = "EU/t: " + recipe.getPower();
            String amps = "Amps: " + recipe.getAmps();
            String total = "Total: " + recipe.getPower() * recipe.getDuration() + " EU";
            long steamDuration = recipe.getDuration() * (recipe.getMapId().equals("plate_cutter") ? 4L : 2L);
            long steamPower = recipe.getMapId().equals("plate_cutter") ? recipe.getPower() : recipe.getPower() * 2L;
            String steamT = "Steam: " + steamPower + " mb/t";
            String steamAdditional = steamDuration < 1200 ? "" : steamDuration < 36000 ? " (" + (steamDuration / 20.0f) + " secs)" : " (" + (steamDuration / 1200.0f) + " mins)";
            String steamLength = "Steam Duration: " + steamDuration + " ticks" + steamAdditional;
            Tier tier = Tier.getTier((recipe.getPower() / recipe.getAmps()));
            String formattedText = " (" + tier.getId().toUpperCase() + ")";
            renderString(stack, power, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, euT, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText, fontRenderer, 5 + stringWidth(euT, fontRenderer), 10, Tier.EV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, amps, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, total, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
            if (steamPower <= Tier.LV.getVoltage()){
                renderString(stack, "Steam Info:", fontRenderer, 5, 40, guiOffsetX, guiOffsetY);
                renderString(stack, steamT, fontRenderer, 5, 50, guiOffsetX, guiOffsetY);
                renderString(stack, steamLength, fontRenderer, 5, 60, guiOffsetX, guiOffsetY);
            } else {
                renderString(stack, "Not runnable in Steam machines", fontRenderer, 5, 40, guiOffsetX, guiOffsetY);
            }
        }

        @Override
        public int getRows() {
            return 7;
        }
    };

    public static void clientMaps() {
        RecipeMaps.ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        RecipeMaps.INT_CIRCUITS.setInfoRenderer(INT_CIRCUIT_RENDERER);
        RecipeMaps.SMELTER_COILS.setInfoRenderer(FLUID_EXTRACTOR_COILS_RENDERER);
        RecipeMaps.THERMAL_BOILER_FUELS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        RecipeMaps.STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        RecipeMaps.GAS_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        RecipeMaps.LARGE_STEAM_FUELS.setInfoRenderer(LARGE_FUEL_RENDERER);
        RecipeMaps.LARGE_GAS_FUELS.setInfoRenderer(LARGE_FUEL_RENDERER);
        RecipeMaps.DIESEL_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        RecipeMaps.HOT_FUELS.setInfoRenderer(HOT_FUEL_RENDERER);
        RecipeMaps.SEMIFLUID_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        RecipeMaps.THERMAL_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);

        RecipeMaps.BLASTING.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        RecipeMaps.STEAM_FURNACE.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_COMPRESSOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_ALLOY_SMELTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_EXTRACTOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_MACERATOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_CUTTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_SIFTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.STEAM_FORGE_HAMMER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        RecipeMaps.COMPRESSOR.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.ALLOY_SMELTER.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.EXTRACTOR.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.MACERATOR.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.PLATE_CUTTER.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.SIFTER.setInfoRenderer(STEAM_RENDERER);
        RecipeMaps.FORGE_HAMMER.setInfoRenderer(STEAM_RENDERER);
    }
}
