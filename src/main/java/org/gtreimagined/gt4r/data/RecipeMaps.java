package org.gtreimagined.gt4r.data;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.recipe.IRecipe;
import org.gtreimagined.gtlib.recipe.RecipeProxies;
import org.gtreimagined.gtlib.recipe.map.RecipeBuilder;
import org.gtreimagined.gtlib.recipe.map.RecipeMap;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;

import static org.gtreimagined.gtlib.machine.Tier.MV;
import static org.gtreimagined.gt4r.data.Guis.MULTI_DISPLAY;
import static org.gtreimagined.gt4r.data.Guis.MULTI_DISPLAY_MORE_FLUID;
import static org.gtreimagined.gt4r.data.Machines.*;

public class RecipeMaps {

    public static RecipeMap<?> ORE_BYPRODUCTS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "ore_byproducts", new RecipeBuilder()));
    public static RecipeMap<?> INT_CIRCUITS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "int_circuits", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_fuels", new RecipeBuilder()));
    public static RecipeMap<?> GAS_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "gas_fuels", new RecipeBuilders.GasFuelBuilder()));
    public static RecipeMap<?> LARGE_STEAM_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "large_steam_fuels", new RecipeBuilder()));
    public static RecipeMap<?> LARGE_GAS_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "large_gas_fuels", new RecipeBuilder()));
    public static RecipeMap<?> DIESEL_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "diesel_fuels", new RecipeBuilder()));
    public static RecipeMap<?> HOT_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "hot_fuels", new RecipeBuilder()));
    public static RecipeMap<?> SEMIFLUID_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "semifluid_fuels", new RecipeBuilder()));
    public static RecipeMap<?> THERMAL_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_fuels", new RecipeBuilder()));
    public static RecipeMap<?> THERMAL_BOILER_FUELS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_boiler_fuels", new RecipeBuilder()));

    public static RecipeMap<?> SOLID_FUEL_BOILERS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "solid_fuel_boilers", new RecipeBuilder()));

    public static RecipeMap<?> ALLOY_SMELTER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "alloy_smelter", new RecipeBuilder()));
    public static RecipeMap<?> ASSEMBLER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "assembler", new RecipeBuilder()));
    public static RecipeMap<?> AUTOCLAVE = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "autoclave", new RecipeBuilder()));
    public static RecipeMap<?> BATH = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "bath", new RecipeBuilder()));
    public static RecipeMap<?> CANNER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "canner", new RecipeBuilder()));
    public static RecipeMap<?> CENTRIFUGE = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "centrifuge", new RecipeBuilder()));
    public static RecipeMap<?> CHEMICAL_REACTOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "chemical_reactor", new RecipeBuilder()));
    public static RecipeMap<?> COMPRESSOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "compressor", new RecipeBuilder()));
    public static RecipeMap<?> DISASSEMBLER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "disassembler", new RecipeBuilder()));
    public static RecipeMap<?> DISTILLERY = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "distillery", new RecipeBuilder()));
    public static RecipeMap<?> DUSTBIN = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "dustbin", new RecipeBuilder()));
    public static RecipeMap<?> ELECTROLYZER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "electrolyzer", new RecipeBuilder()));
    public static RecipeMap<?> EXTRACTOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extractor", new RecipeBuilder()));
    public static RecipeMap<?> EXTRUDER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extruder", new RecipeBuilder()));
    public static RecipeMap<?> FERMENTER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fermenter", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_CANNER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_canner", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_PRESS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_press", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_SOLIDIFIER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_solidifier", new RecipeBuilder()));
    public static RecipeMap<?> FORGE_HAMMER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "forge_hammer", new RecipeBuilder()));
    public static RecipeMap<?> FURNACE = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "furnace", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(4, 80)));
    public static RecipeMap<?> LATHE = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "lathe", new RecipeBuilder()));
    public static RecipeMap<?> MACERATOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "macerator", new RecipeBuilder()));
    public static RecipeMap<?> MASS_FABRICATOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "mass_fabricator", new RecipeBuilder()));
    public static RecipeMap<?> ORE_WASHER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "ore_washer", new RecipeBuilder()));
    public static RecipeMap<?> PLATE_BENDER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_bender", new RecipeBuilder()));
    public static RecipeMap<?> PLATE_CUTTER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_cutter", new RecipeBuilder()));
    public static RecipeMap<?> RECYCLER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "recycler", new RecipeBuilder()));
    public static RecipeMap<?> REPLICATOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "replicator", new RecipeBuilder()));
    public static RecipeMap<?> SCANNER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "scanner", new RecipeBuilder()));
    public static RecipeMap<?> SIFTER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "sifter", new RecipeBuilder()));
    public static RecipeMap<?> SMELTER = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "smelter", new RecipeBuilder()));
    public static RecipeMap<?> SMELTER_COILS = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "smelter_coils", new RecipeBuilder())).setIcon(() -> GT4RItems.CupronickelHeatingCoil);
    public static RecipeMap<?> UNIVERSAL_MACERATOR = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "universal_macerator", new UniversalMaceratorBuilder()).setGuiTier(MV));
    public static RecipeMap<?> WIRE_MILL = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "wire_mill", new RecipeBuilder()));

    public static RecipeMap<?> COKING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "coke_oven", new RecipeBuilder()));
    public static RecipeMap<?> PYROLYSING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "pyrolysis_oven", new RecipeBuilder()));
    public static RecipeMap<?> DISTILLING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "distillation_tower", new RecipeBuilder()));
    public static RecipeMap<?> BASIC_BLASTING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "primitive_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<?> BLASTING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<?> IMPLOSION_COMPRESSING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "implosion_compressor", new RecipeBuilder()));
    public static RecipeMap<?> INDUSTRIAL_GRINDING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_grinder", new RecipeBuilder()));
    public static RecipeMap<?> INDUSTRIAL_SAWMILLING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_sawmill", new RecipeBuilder()));
    public static RecipeMap<?> VACUUM_FREEZING = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "vacuum_freezer", new RecipeBuilder()));
    public static RecipeMap<?> FUSION = GTAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fusion", new RecipeBuilder()));


    public static void init(){

    }

    public static void postInit(){
        STEAM_FUELS.setGuiData(MULTI_DISPLAY, STEAM_TURBINE);
        GAS_FUELS.setGuiData(MULTI_DISPLAY, GAS_TURBINE);
        LARGE_STEAM_FUELS.setGuiData(MULTI_DISPLAY, LARGE_STEAM_TURBINE);
        LARGE_GAS_FUELS.setGuiData(MULTI_DISPLAY, LARGE_GAS_TURBINE);
        DIESEL_FUELS.setGuiData(MULTI_DISPLAY, DIESEL_GENERATOR);
        HOT_FUELS.setGuiData(MULTI_DISPLAY, HEAT_EXCHANGER);
        SEMIFLUID_FUELS.setGuiData(MULTI_DISPLAY, SEMIFLUID_GENERATOR);
        THERMAL_FUELS.setGuiData(MULTI_DISPLAY, THERMAL_GENERATOR);
        THERMAL_BOILER_FUELS.setGuiData(MULTI_DISPLAY, THERMAL_BOILER);
        SOLID_FUEL_BOILERS.setGuiData(MULTI_DISPLAY, SOLID_FUEL_BOILER);
        FUSION.setGuiData(MULTI_DISPLAY, FUSION_REACTOR);
        ORE_BYPRODUCTS.setIcon(() -> Items.IRON_ORE);
        INT_CIRCUITS.setIcon(() -> GTCoreItems.SELECTOR_TAG_ITEMS.get(0));
    }

    public static class UniversalMaceratorBuilder extends RecipeBuilder{
        @Override
        public IRecipe add(String domain, String id) {
            IRecipe recipe = super.add(domain, id);
            var  recipeBuilder = MACERATOR.RB().hide().ii(recipe.getInputItems());
            if (recipe.hasOutputItems() && recipe.getOutputItems().length > 0) {
                recipeBuilder.io(recipe.getOutputItems(false)[0]);
            }
            if (recipe.hasOutputChances() && recipe.getOutputChances().length > 0) {
                recipeBuilder.outputChances(recipe.getOutputChances()[0]);
            }
            recipeBuilder.inputChances(recipe.getInputChances()).add(domain, id, recipe.getDuration(), recipe.getPower(), recipe.getSpecialValue(), recipe.getAmps());
            return recipe;
        }
    }
}
