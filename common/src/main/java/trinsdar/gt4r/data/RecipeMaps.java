package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.GT4RRef;

import static muramasa.antimatter.machine.Tier.MV;
import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY;
import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY_MORE_FLUID;
import static trinsdar.gt4r.data.Machines.*;

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
    public static RecipeMap<?> THERMAL_BOILER_FUELS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_boiler_fuels", new RecipeBuilder()));

    public static RecipeMap<?> STEAM_SMELTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_furnace", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 180)));
    public static RecipeMap<?> STEAM_MACERATING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_macerator", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_EXTRACTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_extractor", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_HAMMERING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_forge_hammer", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_COMPRESSING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_compressor", new RecipeBuilder()));
    public static RecipeMap<?> STEAM_ALLOY_SMELTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "steam_alloy_smelter", new RecipeBuilder()));

    public static RecipeMap<?> COAL_BOILERS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "coal_boilers", new RecipeBuilder()));

    public static RecipeMap<?> ALLOY_SMELTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "alloy_smelter", new io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder(STEAM_ALLOY_SMELTING)));
    public static RecipeMap<?> ASSEMBLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "assembler", new RecipeBuilder()));
    public static RecipeMap<?> AUTOCLAVING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "autoclave", new RecipeBuilder()));
    public static RecipeMap<?> BATHING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "bath", new RecipeBuilder()));
    public static RecipeMap<?> BENDING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_bender", new RecipeBuilder()));
    public static RecipeMap<?> CANNING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "canner", new RecipeBuilder()));
    public static RecipeMap<?> CENTRIFUGING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "centrifuge", new RecipeBuilder()));
    public static RecipeMap<?> COMPRESSING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "compressor", new io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder(STEAM_COMPRESSING)));
    public static RecipeMap<?> CUTTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "plate_cutter", new RecipeBuilder()));
    public static RecipeMap<?> SMELTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "furnace", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(4, 60)));
    public static RecipeMap<?> EXTRACTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extractor", new io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder(STEAM_EXTRACTING)));
    public static RecipeMap<?> EXTRUDING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "extruder", new RecipeBuilder()));
    public static RecipeMap<?> LATHING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "lathe", new RecipeBuilder()));
    //public static RecipeMap UNIVERSAL_MACERATING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>("universal_macerating", new UniversalMaceratingBuilder(), Tier.MV));
    public static RecipeMap<?> MACERATING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "macerator", new io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder(STEAM_MACERATING)).setGuiTier(MV));
    public static RecipeMap<?> RECYCLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "recycler", new RecipeBuilder()));
    public static RecipeMap<?> SCANNING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "scanner", new RecipeBuilder()));
    public static RecipeMap<?> WIRE_MILLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "wire_mill", new RecipeBuilder()));
    public static RecipeMap<?> ELECTROLYZING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "electrolyzer", new RecipeBuilder()));
    public static RecipeMap<?> THERMAL_CENTRIFUGING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "thermal_centrifuge", new RecipeBuilder()));
    public static RecipeMap<?> ORE_WASHING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "ore_washer", new RecipeBuilder()));
    public static RecipeMap<?> CHEMICAL_REACTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "chemical_reactor", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_CANNING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_canner", new RecipeBuilder()));
    public static RecipeMap<?> DISASSEMBLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "disassembler", new RecipeBuilder()));
    public static RecipeMap<?> MASS_FABRICATING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "mass_fabricator", new RecipeBuilder()));
    public static RecipeMap<?> REPLICATING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "replicator", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_EXTRACTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_extractor", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_SOLIDIFYING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_solidifier", new RecipeBuilder()));
    public static RecipeMap<?> HAMMERING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "forge_hammer", new io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder(STEAM_HAMMERING)));
    public static RecipeMap<?> SIFTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "sifter", new RecipeBuilder()));
    public static RecipeMap<?> BASIC_DISTILLING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "distillery", new RecipeBuilder()));
    public static RecipeMap<?> DUSTBIN = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "dustbin", new RecipeBuilder()));
    public static RecipeMap<?> FERMENTING = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fermenter", new RecipeBuilder()));
    public static RecipeMap<?> FLUID_EXTRACTOR_COILS = AntimatterAPI.register(RecipeMap.class, new RecipeMap<>(GT4RRef.ID, "fluid_extractor_coils", new RecipeBuilder())).setIcon(() -> GT4RData.CupronickelHeatingCoil);

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
        THERMAL_BOILER_FUELS.setGuiData(MULTI_DISPLAY, THERMAL_BOILER);
        COAL_BOILERS.setGuiData(MULTI_DISPLAY, COAL_BOILER);
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
        INT_CIRCUITS.setIcon(() -> TierMaps.INT_CIRCUITS_ITEMS.get(0));
    }
}
