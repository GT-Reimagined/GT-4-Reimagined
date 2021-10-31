package trinsdar.gt4r.data;

import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.machine.Tier.MV;
import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY;
import static trinsdar.gt4r.data.Guis.MULTI_DISPLAY_MORE_FLUID;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.RecipeBuilders.*;

public class RecipeMaps {

    public static RecipeMap<?> ORE_BYPRODUCTS = new RecipeMap<>(Ref.ID, "ore_byproducts", new RecipeBuilder());
    public static RecipeMap<?> INT_CIRCUITS = new RecipeMap<>(Ref.ID, "int_circuits", new RecipeBuilder());
    public static RecipeMap<?> STEAM_FUELS = new RecipeMap<>(Ref.ID, "steam_fuels", new RecipeBuilder());
    public static RecipeMap<?> GAS_FUELS = new RecipeMap<>(Ref.ID, "gas_fuels", new GasFuelBuilder());
    public static RecipeMap<?> LARGE_STEAM_FUELS = new RecipeMap<>(Ref.ID, "large_steam_fuels", new RecipeBuilder());
    public static RecipeMap<?> LARGE_GAS_FUELS = new RecipeMap<>(Ref.ID, "large_gas_fuels", new RecipeBuilder());
    public static RecipeMap<?> DIESEL_FUELS = new RecipeMap<>(Ref.ID, "diesel_fuels", new RecipeBuilder());
    public static RecipeMap<?> HOT_FUELS = new RecipeMap<>(Ref.ID, "hot_fuels", new RecipeBuilder());
    public static RecipeMap<?> SEMIFLUID_FUELS = new RecipeMap<>(Ref.ID, "semifluid_fuels", new RecipeBuilder());
    public static RecipeMap<?> THERMAL_BOILER_FUELS = new RecipeMap<>(Ref.ID, "thermal_boiler_fuels", new RecipeBuilder());

    public static RecipeMap<?> COAL_BOILERS = new RecipeMap<>(Ref.ID, "coal_boilers", new RecipeBuilder());

    public static RecipeMap<?> ALLOY_SMELTING = new RecipeMap<>(Ref.ID, "alloy_smelting", new AlloySmeltingBuilder());
    public static RecipeMap<?> ASSEMBLING = new RecipeMap<>(Ref.ID, "assembling", new RecipeBuilder());
    public static RecipeMap<?> AUTOCLAVING = new RecipeMap<>(Ref.ID, "autoclaving", new RecipeBuilder());
    public static RecipeMap<?> BATHING = new RecipeMap<>(Ref.ID, "bathing", new RecipeBuilder());
    public static RecipeMap<?> BENDING = new RecipeMap<>(Ref.ID, "plate_bending", new RecipeBuilder());
    public static RecipeMap<?> CANNING = new RecipeMap<>(Ref.ID, "canning", new RecipeBuilder());
    public static RecipeMap<?> CENTRIFUGING = new RecipeMap<>(Ref.ID, "centrifuging", new RecipeBuilder());
    public static RecipeMap<?> COMPRESSING = new RecipeMap<>(Ref.ID, "compressing", new CompressingBuilder());
    public static RecipeMap<?> CUTTING = new RecipeMap<>(Ref.ID, "plate_cutting", new RecipeBuilder());
    public static RecipeMap<?> SMELTING = new RecipeMap<>(Ref.ID, "smelting", new SmeltingBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(4, 60));
    public static RecipeMap<?> EXTRACTING = new RecipeMap<>(Ref.ID, "extracting", new ExtractingBuilder());
    public static RecipeMap<?> EXTRUDING = new RecipeMap<>(Ref.ID, "extruding", new RecipeBuilder());
    public static RecipeMap<?> LATHING = new RecipeMap<>(Ref.ID, "lathing", new RecipeBuilder());
    //public static RecipeMap UNIVERSAL_MACERATING = new RecipeMap<>("universal_macerating", new UniversalMaceratingBuilder(), Tier.MV);
    public static RecipeMap<MaceratingBuilder> MACERATING = new RecipeMap<>(Ref.ID, "macerating", new MaceratingBuilder()).setGuiTier(MV);
    public static RecipeMap<?> RECYCLING = new RecipeMap<>(Ref.ID, "recycling", new RecipeBuilder());
    public static RecipeMap<?> SCANNING = new RecipeMap<>(Ref.ID, "scanning", new RecipeBuilder());
    public static RecipeMap<?> WIRE_MILLING = new RecipeMap<>(Ref.ID, "wire_milling", new RecipeBuilder());
    public static RecipeMap<?> ELECTROLYZING = new RecipeMap<>(Ref.ID, "electrolyzing", new RecipeBuilder());
    public static RecipeMap<?> THERMAL_CENTRIFUGING = new RecipeMap<>(Ref.ID, "thermal_centrifuging", new RecipeBuilder());
    public static RecipeMap<?> ORE_WASHING = new RecipeMap<>(Ref.ID, "ore_washing", new RecipeBuilder());
    public static RecipeMap<?> CHEMICAL_REACTING = new RecipeMap<>(Ref.ID, "chemical_reacting", new RecipeBuilder());
    public static RecipeMap<?> FLUID_CANNING = new RecipeMap<>(Ref.ID, "fluid_canning", new RecipeBuilder());
    public static RecipeMap<?> DISASSEMBLING = new RecipeMap<>(Ref.ID, "disassembling", new RecipeBuilder());
    public static RecipeMap<?> MASS_FABRICATING = new RecipeMap<>(Ref.ID, "mass_fabricating", new RecipeBuilder());
    public static RecipeMap<?> REPLICATING = new RecipeMap<>(Ref.ID, "replicating", new RecipeBuilder());
    public static RecipeMap<?> FLUID_EXTRACTING = new RecipeMap<>(Ref.ID, "fluid_extracting", new RecipeBuilder());
    public static RecipeMap<?> FLUID_SOLIDIFYING = new RecipeMap<>(Ref.ID, "fluid_solidifying", new RecipeBuilder());
    public static RecipeMap<?> HAMMERING = new RecipeMap<>(Ref.ID, "hammering", new HammeringBuilder());
    public static RecipeMap<?> SIFTING = new RecipeMap<>(Ref.ID, "sifting", new RecipeBuilder());
    public static RecipeMap<?> BASIC_DISTILLING = new RecipeMap<>(Ref.ID, "basic_distilling", new RecipeBuilder());
    public static RecipeMap<?> DUSTBIN = new RecipeMap<>(Ref.ID, "dustbin", new RecipeBuilder());
    public static RecipeMap<?> FERMENTING = new RecipeMap<>(Ref.ID, "fermenting", new RecipeBuilder());
    public static RecipeMap<?> FLUID_EXTRACTOR_COILS = new RecipeMap<>(Ref.ID, "fluid_extractor_coils", new CoilBuilder());


    public static RecipeMap<?> STEAM_SMELTING = new RecipeMap<>(Ref.ID, "steam_smelting", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 180));
    public static RecipeMap<?> STEAM_MACERATING = new RecipeMap<>(Ref.ID, "steam_macerating", new RecipeBuilder());
    public static RecipeMap<?> STEAM_EXTRACTING = new RecipeMap<>(Ref.ID, "steam_extracting", new RecipeBuilder());
    public static RecipeMap<?> STEAM_HAMMERING = new RecipeMap<>(Ref.ID, "steam_hammering", new RecipeBuilder());
    public static RecipeMap<?> STEAM_COMPRESSING = new RecipeMap<>(Ref.ID, "steam_compressing", new RecipeBuilder());
    public static RecipeMap<?> STEAM_ALLOY_SMELTING = new RecipeMap<>(Ref.ID, "steam_alloy_smelting", new RecipeBuilder());

    public static RecipeMap<?> COKING = new RecipeMap<>(Ref.ID, "coking", new RecipeBuilder());
    public static RecipeMap<?> PYROLYSING = new RecipeMap<>(Ref.ID, "pyrolysing", new RecipeBuilder());
    public static RecipeMap<?> DISTILLING = new RecipeMap<>(Ref.ID, "distilling", new RecipeBuilder());
    public static RecipeMap<BasicBlastingBuilder> BASIC_BLASTING = new RecipeMap<>(Ref.ID, "basic_blasting", new BasicBlastingBuilder());
    public static RecipeMap<?> BLASTING = new RecipeMap<>(Ref.ID, "industrial_blasting", new RecipeBuilder());
    public static RecipeMap<?> IMPLOSION_COMPRESSING = new RecipeMap<>(Ref.ID, "implosion_compressing", new RecipeBuilder());
    public static RecipeMap<?> INDUSTRIAL_GRINDING = new RecipeMap<>(Ref.ID, "industrial_grinding", new RecipeBuilder());
    public static RecipeMap<?> INDUSTRIAL_SAWMILLING = new RecipeMap<>(Ref.ID, "industrial_sawmilling", new RecipeBuilder());
    public static RecipeMap<?> VACUUM_FREEZING = new RecipeMap<>(Ref.ID, "vacuum_freezing", new RecipeBuilder());
    public static RecipeMap<?> FUSION = new RecipeMap<>(Ref.ID, "fusion", new RecipeBuilder());


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
    }
}
