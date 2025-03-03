package org.gtreimagined.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.RecipeBuilders.SteamBuilder;
import org.gtreimagined.gt4r.GT4RRef;

import static muramasa.antimatter.machine.Tier.MV;
import static org.gtreimagined.gt4r.data.Guis.MULTI_DISPLAY;
import static org.gtreimagined.gt4r.data.Guis.MULTI_DISPLAY_MORE_FLUID;
import static org.gtreimagined.gt4r.data.Machines.*;

public class RecipeMaps {

    public static RecipeMap<?> ORE_BYPRODUCTS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "ore_byproducts", new RecipeBuilder()));
    public static RecipeMap<?> INT_CIRCUITS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "int_circuits", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_fuels", new RecipeBuilder()));
    public static RecipeMap<?> GAS_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "gas_fuels", new RecipeBuilders.GasFuelBuilder()));
    public static RecipeMap<?> LARGE_STEAM_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "large_steam_fuels", new RecipeBuilder()));
    public static RecipeMap<?> LARGE_GAS_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "large_gas_fuels", new RecipeBuilder()));
    public static RecipeMap<?> DIESEL_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "diesel_fuels", new RecipeBuilder()));
    public static RecipeMap<?> HOT_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "hot_fuels", new RecipeBuilder()));
    public static RecipeMap<?> SEMIFLUID_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "semifluid_fuels", new RecipeBuilder()));
    public static RecipeMap<?> THERMAL_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_fuels", new RecipeBuilder()));
    public static RecipeMap<?> THERMAL_BOILER_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_boiler_fuels", new RecipeBuilder()));

    public static RecipeMap<?> STEAM_FURNACE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_furnace", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 180)));
    public static RecipeMap<?> STEAM_MACERATOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_macerator", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_EXTRACTOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_extractor", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_forge_hammer", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_COMPRESSOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_compressor", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_alloy_smelter", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_CUTTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_cutter", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_SIFTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_sifter", new RecipeBuilder()));

    public static RecipeMap<?> SOLID_FUEL_BOILERS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "solid_fuel_boilers", new RecipeBuilder()));

    public static RecipeMap<?> ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "alloy_smelter", new SteamBuilder(STEAM_ALLOY_SMELTER)));
    public static RecipeMap<?> ASSEMBLER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "assembler", new RecipeBuilder()));
    public static RecipeMap<?> AUTOCLAVE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "autoclave", new RecipeBuilder()));
    public static RecipeMap<?> BATH = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "bath", new RecipeBuilder()));
    public static RecipeMap<?> CANNER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "canner", new RecipeBuilder()));
    public static RecipeMap<?> CENTRIFUGE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "centrifuge", new RecipeBuilder()));
    public static RecipeMap<?> CHEMICAL_REACTOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "chemical_reactor", new RecipeBuilder()));
    public static RecipeMap<?> COMPRESSOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "compressor", new SteamBuilder(STEAM_COMPRESSOR)));
    public static RecipeMap<?> DISASSEMBLER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "disassembler", new RecipeBuilder()));
    public static RecipeMap<?> DISTILLERY = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "distillery", new RecipeBuilder()));
    public static RecipeMap<?> DUSTBIN = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "dustbin", new RecipeBuilder()));
    public static RecipeMap<?> ELECTROLYZER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "electrolyzer", new RecipeBuilder()));
    public static RecipeMap<?> EXTRACTOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extractor", new SteamBuilder(STEAM_EXTRACTOR)));
    public static RecipeMap<?> EXTRUDER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extruder", new RecipeBuilder()));
    public static RecipeMap<?> FERMENTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fermenter", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_CANNER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_canner", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_PRESS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_press", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_SOLIDIFIER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_solidifier", new RecipeBuilder()));
    public static RecipeMap<?> FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "forge_hammer", new SteamBuilder(STEAM_FORGE_HAMMER)));
    public static RecipeMap<?> FURNACE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "furnace", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(4, 60)));
    public static RecipeMap<?> LATHE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "lathe", new RecipeBuilder()));
    public static RecipeMap<?> MACERATOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "macerator", new SteamBuilder(STEAM_MACERATOR)));
    public static RecipeMap<?> MASS_FABRICATOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "mass_fabricator", new RecipeBuilder()));
    public static RecipeMap<?> ORE_WASHER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "ore_washer", new RecipeBuilder()));
    public static RecipeMap<?> PLATE_BENDER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_bender", new RecipeBuilder()));
    public static RecipeMap<?> PLATE_CUTTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_cutter", new RecipeBuilder()));
    public static RecipeMap<?> RECYCLER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "recycler", new RecipeBuilder()));
    public static RecipeMap<?> REPLICATOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "replicator", new RecipeBuilder()));
    public static RecipeMap<?> SCANNER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "scanner", new RecipeBuilder()));
    public static RecipeMap<?> SIFTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "sifter", new SteamBuilder(STEAM_SIFTER)));
    public static RecipeMap<?> SMELTER = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "smelter", new RecipeBuilder()));
    public static RecipeMap<?> SMELTER_COILS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "smelter_coils", new RecipeBuilder())).setIcon(() -> GT4RItems.CupronickelHeatingCoil);
    public static RecipeMap<?> THERMAL_CENTRIFUGE = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_centrifuge", new RecipeBuilder()));
    public static RecipeMap<?> UNIVERSAL_MACERATOR = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "universal_macerator", new UniversalMaceratorBuilder()).setGuiTier(MV));
    public static RecipeMap<?> WIRE_MILL = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "wire_mill", new RecipeBuilder()));

    public static RecipeMap<?> COKING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "coke_oven", new RecipeBuilder()));
    public static RecipeMap<?> PYROLYSING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "pyrolysis_oven", new RecipeBuilder()));
    public static RecipeMap<?> DISTILLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "distillation_tower", new RecipeBuilder()));
    public static RecipeMap<?> BASIC_BLASTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "primitive_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<?> BLASTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<?> IMPLOSION_COMPRESSING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "implosion_compressor", new RecipeBuilder()));
    public static RecipeMap<?> INDUSTRIAL_GRINDING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_grinder", new RecipeBuilder()));
    public static RecipeMap<?> INDUSTRIAL_SAWMILLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "industrial_sawmill", new RecipeBuilder()));
    public static RecipeMap<?> VACUUM_FREEZING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "vacuum_freezer", new RecipeBuilder()));
    public static RecipeMap<?> FUSION = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fusion", new RecipeBuilder()));


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
        COKING.setGuiData(MULTI_DISPLAY, COKE_OVEN);
        PYROLYSING.setGuiData(MULTI_DISPLAY, PYROLYSIS_OVEN);
        DISTILLING.setGuiData(MULTI_DISPLAY_MORE_FLUID, DISTILLATION_TOWER);
        BASIC_BLASTING.setGuiData(MULTI_DISPLAY, PRIMITIVE_BLAST_FURNACE);
        BLASTING.setGuiData(MULTI_DISPLAY, BLAST_FURNACE);
        IMPLOSION_COMPRESSING.setGuiData(MULTI_DISPLAY, IMPLOSION_COMPRESSOR);
        INDUSTRIAL_GRINDING.setGuiData(MULTI_DISPLAY, INDUSTRIAL_GRINDER);
        INDUSTRIAL_SAWMILLING.setGuiData(MULTI_DISPLAY, INDUSTRIAL_SAWMILL);
        VACUUM_FREEZING.setGuiData(MULTI_DISPLAY, VACUUM_FREEZER);
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
