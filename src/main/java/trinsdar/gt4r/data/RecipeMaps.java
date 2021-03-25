package trinsdar.gt4r.data;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.integration.jei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jei.renderer.InfoRenderers;
import muramasa.antimatter.integration.jei.renderer.InternalInfoRenderers;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.RecipeMap;
import muramasa.antimatter.recipe.RecipeProxies;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.LazyValue;

import java.util.Objects;

import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY;
import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY_MORE_FLUID;
import static trinsdar.gt4r.data.RecipeBuilders.*;

public class RecipeMaps {

    static final IRecipeInfoRenderer FUEL_RENDERER_INTERNAL = new IRecipeInfoRenderer() {
        @Override
        public void render(MatrixStack stack, Recipe recipe, FontRenderer fontRenderer, int guiOffsetX, int guiOffsetY) {
            String fuelPerMb =  "Fuel content(mb): "+ ((double)(recipe.getPower() * recipe.getDuration()) / (double) Objects.requireNonNull(recipe.getInputFluids())[0].getAmount());
            String fuelPerB =  "Fuel content(bb): "+ ((double)(recipe.getPower() * recipe.getDuration()) / (double) Objects.requireNonNull(recipe.getInputFluids())[0].getAmount()) *1000;
            renderString(stack, fuelPerMb,fontRenderer, 5, 5,guiOffsetX,guiOffsetY);
            renderString(stack, fuelPerB,fontRenderer, 5, 15,guiOffsetX,guiOffsetY);
            renderString(stack, "Ticks: " + recipe.getDuration(),fontRenderer, 5, 15,guiOffsetX,guiOffsetY);
        }
    };

    public static final LazyValue<IRecipeInfoRenderer> FUEL_RENDERER = new LazyValue<>(() -> FUEL_RENDERER_INTERNAL);

    public static RecipeMap ORE_BYPRODUCTS = new RecipeMap<>("ore_byproducts", new RecipeBuilder(), InfoRenderers.EMPTY_RENDERER);
    public static RecipeMap STEAM_FUELS = new RecipeMap<>("steam_fuels", new RecipeBuilder(), FUEL_RENDERER);
    public static RecipeMap GAS_FUELS = new RecipeMap<>("gas_fuels", new RecipeBuilder(), FUEL_RENDERER);
    public static RecipeMap DIESEL_FUELS = new RecipeMap<>("diesel_fuels", new RecipeBuilder(), FUEL_RENDERER);
    public static RecipeMap LAVA_FUELS = new RecipeMap<>("lava_fuels", new RecipeBuilder(), FUEL_RENDERER);
    public static RecipeMap SEMIFLUID_FUELS = new RecipeMap<>("semifluid_fuels", new RecipeBuilder(), FUEL_RENDERER);

    public static RecipeMap COAL_BOILERS = new RecipeMap<>("coal_boilers", new RecipeBuilder());

    public static RecipeMap ALLOY_SMELTING = new RecipeMap<>("alloy_smelting", new AlloySmeltingBuilder());
    public static RecipeMap ASSEMBLING = new RecipeMap<>("assembling", new RecipeBuilder());
    public static RecipeMap AUTOCLAVING = new RecipeMap<>("autoclaving", new RecipeBuilder());
    public static RecipeMap BATHING = new RecipeMap<>("bathing", new RecipeBuilder());
    public static RecipeMap BENDING = new RecipeMap<>("plate_bending", new RecipeBuilder());
    public static RecipeMap CANNING = new RecipeMap<>("canning", new RecipeBuilder());
    public static RecipeMap CENTRIFUGING = new RecipeMap<>("centrifuging", new RecipeBuilder());
    public static RecipeMap COMPRESSING = new RecipeMap<>("compressing", new CompressingBuilder());
    public static RecipeMap CUTTING = new RecipeMap<>("plate_cutting", new RecipeBuilder());
    public static RecipeMap SMELTING = new RecipeMap<>("smelting", new SmeltingBuilder(), RecipeProxies.FURNACE_PROXY.apply(4, 60));
    public static RecipeMap EXTRACTING = new RecipeMap<>("extracting", new ExtractingBuilder());
    public static RecipeMap EXTRUDING = new RecipeMap<>("extruding", new RecipeBuilder());
    public static RecipeMap LATHING = new RecipeMap<>("lathing", new RecipeBuilder());
    public static RecipeMap UNIVERSAL_MACERATING = new RecipeMap<>("universal_macerating", new UniversalMaceratingBuilder(), Tier.MV);
    public static RecipeMap MACERATING = new RecipeMap<>("macerating", new MaceratingBuilder());
    public static RecipeMap RECYCLING = new RecipeMap<>("recycling", new RecipeBuilder());
    public static RecipeMap WIRE_MILLING = new RecipeMap<>("wire_milling", new RecipeBuilder());
    public static RecipeMap ELECTROLYZING = new RecipeMap<>("electrolyzing", new RecipeBuilder());
    public static RecipeMap THERMAL_CENTRIFUGING = new RecipeMap<>("thermal_centrifuging", new RecipeBuilder());
    public static RecipeMap ORE_WASHING = new RecipeMap<>("ore_washing", new RecipeBuilder());
    public static RecipeMap CHEMICAL_REACTING = new RecipeMap<>("chemical_reacting", new RecipeBuilder());
    public static RecipeMap FLUID_CANNING = new RecipeMap<>("fluid_canning", new RecipeBuilder());
    public static RecipeMap DISASSEMBLING = new RecipeMap<>("disassembling", new RecipeBuilder());
    public static RecipeMap MASS_FABRICATING = new RecipeMap<>("mass_fabricating", new RecipeBuilder());
    public static RecipeMap FLUID_EXTRACTING = new RecipeMap<>("fluid_extracting", new RecipeBuilder());
    public static RecipeMap FLUID_SOLIDIFYING = new RecipeMap<>("fluid_solidifying", new RecipeBuilder());
    public static RecipeMap HAMMERING = new RecipeMap<>("hammering", new HammeringBuilder());
    public static RecipeMap SIFTING = new RecipeMap<>("sifting", new RecipeBuilder());


    public static RecipeMap STEAM_SMELTING = new RecipeMap<>("steam_smelting", new RecipeBuilder(), RecipeProxies.FURNACE_PROXY.apply(8, 180), InfoRenderers.STEAM_RENDERER);
    public static RecipeMap STEAM_MACERATING = new RecipeMap<>("steam_macerating", new RecipeBuilder(), InfoRenderers.STEAM_RENDERER);
    public static RecipeMap STEAM_EXTRACTING = new RecipeMap<>("steam_extracting", new RecipeBuilder(), InfoRenderers.STEAM_RENDERER);
    public static RecipeMap STEAM_HAMMERING = new RecipeMap<>("steam_hammering", new RecipeBuilder(), InfoRenderers.STEAM_RENDERER);
    public static RecipeMap STEAM_COMPRESSING = new RecipeMap<>("steam_compressing", new RecipeBuilder(), InfoRenderers.STEAM_RENDERER);
    public static RecipeMap STEAM_ALLOY_SMELTING = new RecipeMap<>("steam_alloy_smelting", new RecipeBuilder(), InfoRenderers.STEAM_RENDERER);

    public static RecipeMap COKING = new RecipeMap<>("coking", new RecipeBuilder(), MULTI_DISPLAY);
    public static RecipeMap DISTILLING = new RecipeMap<>("distilling", new RecipeBuilder(), MULTI_DISPLAY_MORE_FLUID);
    public static RecipeMap<BasicBlastingBuilder> BASIC_BLASTING = new RecipeMap<>("basic_blasting", new BasicBlastingBuilder(), MULTI_DISPLAY);
    public static RecipeMap BLASTING = new RecipeMap<>("industrial_blasting", new RecipeBuilder(), MULTI_DISPLAY, InfoRenderers.BLASTING_RENDERER);
    public static RecipeMap IMPLOSION_COMPRESSING = new RecipeMap<>("implosion_compressing", new RecipeBuilder(), MULTI_DISPLAY);
    public static RecipeMap INDUSTRIAL_GRINDING = new RecipeMap<>("industrial_grinding", new RecipeBuilder(), MULTI_DISPLAY);
    public static RecipeMap INDUSTRIAL_SAWMILLING = new RecipeMap<>("industrial_sawmilling", new RecipeBuilder(), MULTI_DISPLAY);
    public static RecipeMap VACUUM_FREEZING = new RecipeMap<>("vacuum_freezing", new RecipeBuilder(), MULTI_DISPLAY);
    public static RecipeMap FUSION = new RecipeMap<>("fusion", new RecipeBuilder(), MULTI_DISPLAY);
}
