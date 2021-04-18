package trinsdar.gt4r.data;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.integration.jei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jei.renderer.InfoRenderers;
import muramasa.antimatter.recipe.Recipe;
import net.minecraft.client.gui.FontRenderer;

import java.util.Objects;

public class RecipeRenderer {
    static final IRecipeInfoRenderer FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(MatrixStack stack, Recipe recipe, FontRenderer fontRenderer, int guiOffsetX, int guiOffsetY) {
            String fuelPerMb = "Fuel content(mb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) Objects.requireNonNull(recipe.getInputFluids())[0].getAmount());
            String fuelPerB = "Fuel content(bb): " + ((double) (recipe.getPower() * recipe.getDuration()) / (double) Objects.requireNonNull(recipe.getInputFluids())[0].getAmount()) * 1000;
            renderString(stack, fuelPerMb, fontRenderer, 5, 5, guiOffsetX, guiOffsetY);
            renderString(stack, fuelPerB, fontRenderer, 5, 15, guiOffsetX, guiOffsetY);
            renderString(stack, "Ticks: " + recipe.getDuration(), fontRenderer, 5, 25, guiOffsetX, guiOffsetY);
        }
    };

    public static void clientMaps() {
        RecipeMaps.ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        RecipeMaps.STEAM_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.GAS_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.DIESEL_FUELS.setInfoRenderer(FUEL_RENDERER);
        RecipeMaps.LAVA_FUELS.setInfoRenderer(FUEL_RENDERER);
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
