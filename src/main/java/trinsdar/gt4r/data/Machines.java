package trinsdar.gt4r.data;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.tile.single.TileEntityBatteryBuffer;
import muramasa.antimatter.tile.single.TileEntityDigitalTransformer;
import muramasa.antimatter.tile.single.TileEntityInfiniteStorage;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import trinsdar.gt4r.machine.HatchMachineCustom;
import trinsdar.gt4r.machine.MaterialMachine;
import trinsdar.gt4r.machine.StorageMachine;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;
import trinsdar.gt4r.tile.single.TileEntityInfiniteFluid;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;
import trinsdar.gt4r.tile.single.TileEntityQuantumTank;
import trinsdar.gt4r.tile.single.TileEntitySteamMachine;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.tile.multi.*;


import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.MachineFlag.CELL;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.COVER_DYNAMO_OLD;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_INPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_OUTPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_STEAM_VENT;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new BasicMachine(Ref.ID, "alloy_smelter", ALLOY_SMELTING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine ASSEMBLER = new BasicMachine(Ref.ID, "assembler", ASSEMBLING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine BENDER = new BasicMachine(Ref.ID, "plate_bender", BENDING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine CANNER = new BasicMachine(Ref.ID, "canner", CANNING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine COMPRESSOR = new BasicMachine(Ref.ID, "compressor", COMPRESSING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine CUTTER = new BasicMachine(Ref.ID, "cutter", CUTTING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine FURNACE = new BasicMachine(Ref.ID, "furnace", SMELTING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine EXTRACTOR = new BasicMachine(Ref.ID, "extractor", EXTRACTING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder", EXTRUDING, GUI, ITEM, MV, Textures.TIER_HANDLER);
    public static BasicMachine LATHE = new BasicMachine(Ref.ID, "lathe", LATHING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine MACERATOR = new BasicMachine(Ref.ID, "macerator", MACERATING, GUI, ITEM, LV, MV, Textures.TIER_HANDLER);
    public static BasicMachine RECYCLER = new BasicMachine(Ref.ID, "recycler", RECYCLING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine SCANNER = new BasicMachine(Ref.ID, "scanner", SCANNING, GUI, ITEM, FLUID, HV, Textures.TIER_HANDLER);
    public static BasicMachine WIRE_MILL = new BasicMachine(Ref.ID, "wire_mill", WIRE_MILLING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine CENTRIFUGE = new BasicMachine(Ref.ID, "centrifuge", CENTRIFUGING, GUI, ITEM, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine ELECTROLYZER = new BasicMachine(Ref.ID, "electrolyzer", ELECTROLYZING, GUI, ITEM, FLUID, LV, MV, Textures.TIER_HANDLER);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(Ref.ID, "chemical_reactor", CHEMICAL_REACTING, GUI, ITEM, FLUID, MV, Textures.TIER_HANDLER);
    public static BasicMachine FLUID_CANNER = new BasicMachine(Ref.ID, "fluid_canner", FLUID_CANNING, GUI, ITEM, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine DISASSEMBLER = new BasicMachine(Ref.ID, "disassembler", DISASSEMBLING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(Ref.ID, "mass_fabricator", MASS_FABRICATING, GUI, ITEM, FLUID, EV, Textures.TIER_HANDLER);
    public static BasicMachine REPLICATOR = new BasicMachine(Ref.ID, "replicator", REPLICATING, GUI, ITEM, FLUID, EV, Textures.TIER_HANDLER);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(Ref.ID, "forge_hammer", HAMMERING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine ORE_WASHER = new BasicMachine(Ref.ID, "ore_washer", ORE_WASHING, GUI, ITEM, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(Ref.ID, "thermal_centrifuge", THERMAL_CENTRIFUGING, GUI, ITEM, FLUID, MV, Textures.TIER_HANDLER);
    //public static BasicMachine UNIVERSAL_MACERATOR = new BasicMachine(Ref.ID, "universal_macerator", UNIVERSAL_MACERATING, GUI, ITEM, MV, Textures.TIER_HANDLER);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(Ref.ID, "fluid_extractor", FLUID_EXTRACTING, GUI, ITEM, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(Ref.ID, "fluid_solidifier", FLUID_SOLIDIFYING, GUI, ITEM, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine PUMP = new BasicMachine(Ref.ID, "pump", GUI, FLUID, LV, Textures.TIER_HANDLER);
    public static BasicMachine SIFTER = new BasicMachine(Ref.ID, "sifter", SIFTING, GUI, ITEM, LV, Textures.TIER_HANDLER);
    public static BasicMachine BATH = new BasicMachine(Ref.ID, "bath", BATHING, GUI, ITEM, LV, Textures.TIER_HANDLER);

    public static BasicMachine COAL_BOILER = new BasicMachine(Ref.ID, "coal_boiler", COAL_BOILERS, BRONZE, STEEL, GUI, STEAM, RECIPE, ITEM, FLUID, CELL, Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntityCoalBoiler(m)).covers((ICover[]) null);
    public static BasicMachine STEAM_FURNACE = new BasicMachine(Ref.ID, "steam_furnace", BRONZE, STEEL, GUI, STEAM, ITEM, FLUID, STEAM_SMELTING, Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_MACERATOR = new BasicMachine(Ref.ID, "steam_macerator", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_MACERATING, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_EXTRACTOR = new BasicMachine(Ref.ID, "steam_extractor", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_EXTRACTING, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_FORGE_HAMMER = new BasicMachine(Ref.ID, "steam_forge_hammer", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_HAMMERING, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_COMPRESSOR = new BasicMachine(Ref.ID, "steam_compressor", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_COMPRESSING, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_ALLOY_SMELTER = new BasicMachine(Ref.ID, "steam_alloy_smelter",BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_ALLOY_SMELTING, Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);

    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(Ref.ID, "coke_oven", COKING, LV, GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityCokeOven(m));
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "primitive_blast_furnace", BASIC_BLASTING, BRONZE, GUI, ITEM).setTile(m -> () -> new TileEntityPrimitiveBlastFurnace(m));
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(Ref.ID, "charcoal_pit", LV).setTile(m -> () -> new TileEntityCharcoalPit(m));
    public static BasicMultiMachine<?> BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "industrial_blast_furnace", BLASTING, MV, GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityIndustrialBlastFurnace(m));
    public static BasicMultiMachine<?> IMPLOSION_COMPRESSOR = new BasicMultiMachine<>(Ref.ID, "implosion_compressor", IMPLOSION_COMPRESSING, LV, GUI, ITEM,ENERGY).setTile(m -> () -> new TileEntityImplosionCompressor(m));
    public static BasicMultiMachine<?> INDUSTRIAL_GRINDER = new BasicMultiMachine<>(Ref.ID, "industrial_grinder", INDUSTRIAL_GRINDING, MV, GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityIndustrialGrinder(m));
    public static BasicMultiMachine<?> INDUSTRIAL_SAWMILL = new BasicMultiMachine<>(Ref.ID, "industrial_sawmill", INDUSTRIAL_SAWMILLING, MV, GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityIndustrialSawmill(m));
    public static BasicMultiMachine<?> DISTILLATION_TOWER = new BasicMultiMachine<>(Ref.ID, "distillation_tower", DISTILLING, MV, GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityDistillationTower(m));
    public static BasicMultiMachine<?> VACUUM_FREEZER = new BasicMultiMachine<>(Ref.ID, "vacuum_freezer", VACUUM_FREEZING, LV, GUI, ITEM, FLUID,ENERGY).setTile(m -> () -> new TileEntityVacuumFreezer(m));
    public static MultiMachine THERMAL_BOILER = new MultiMachine(Ref.ID, "thermal_boiler", LV, GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityLargeBoiler(m));
    public static MultiMachine LARGE_STEAM_TURBINE = new MultiMachine(Ref.ID, "large_steam_turbine", EV, GUI, FLUID,ENERGY, STEAM_FUELS, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine LARGE_GAS_TURBINE = new MultiMachine(Ref.ID, "large_gas_turbine", IV, GUI, FLUID,ENERGY, GAS_FUELS, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine LARGE_HEAT_EXCHANGER = new MultiMachine(Ref.ID, "large_heat_exchanger", EV, GUI, FLUID,ENERGY).setTile(m -> () -> new TileEntityHeatExchanger(m));
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_control_computer", FUSION, IV,  GUI, FLUID,ENERGY).setTile(m -> () -> new TileEntityFusionReactor(m));

    public static HatchMachine HATCH_ITEM_I = new HatchMachineCustom(Ref.ID, "hatch_item_input", GUI, ITEM,COVERINPUT, LV, Textures.TIER_HANDLER).setTiers(LV);
    public static HatchMachine HATCH_ITEM_O = new HatchMachineCustom(Ref.ID, "hatch_item_output", GUI, ITEM, COVEROUTPUT, LV, Textures.TIER_HANDLER).setTiers(LV);
    public static HatchMachine HATCH_FLUID_I = new HatchMachineCustom(Ref.ID, "hatch_fluid_input", GUI, FLUID, COVERINPUT, CELL, LV, Textures.TIER_HANDLER).setTiers(LV);
    public static HatchMachine HATCH_FLUID_O = new HatchMachineCustom(Ref.ID, "hatch_fluid_output", GUI, FLUID, COVEROUTPUT, CELL, LV, Textures.TIER_HANDLER).setTiers(LV);
    public static HatchMachine HATCH_MUFFLER = new HatchMachineCustom(Ref.ID, "hatch_muffler", GUI, ITEM,COVERMUFFLER, LV, Textures.TIER_HANDLER).setTiers(LV);
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "hatch_dynamo", ENERGY,COVER_DYNAMO_OLD, EV, Textures.TIER_HANDLER).setTiers(EV, IV);
    public static HatchMachine FUSION_MATERIAL_INJECTOR = new HatchMachineCustom(Ref.ID, "fusion_material_injector", GUI, COVER_FUSION_INPUT, ITEM, FLUID, CELL, LV, Textures.FUSION_IN).setTiers(LV);
    public static HatchMachine FUSION_MATERIAL_EXTRACTOR = new HatchMachineCustom(Ref.ID, "fusion_material_extractor", GUI, COVER_FUSION_OUTPUT, ITEM, FLUID, CELL, LV, Textures.FUSION_OUT).setTiers(LV);
    public static HatchMachine FUSION_ENERGY_INJECTOR = new HatchMachine(Ref.ID, "fusion_energy_injector", ENERGY, IV, Textures.FUSION_IN).setTiers(IV).covers((ICover[]) null);
    public static HatchMachine FUSION_ENERGY_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_energy_extractor", ENERGY,COVER_DYNAMO_OLD, UV, Textures.FUSION_OUT).setTiers(UV);

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank", GUI, CELL, Textures.TIER_HANDLER, MAX).setTile(m -> () -> new TileEntityQuantumTank(m)).frontCovers();
    public static StorageMachine QUANTUM_CHEST = new StorageMachine(Ref.ID, "quantum_chest", GUI, ITEM, Textures.TIER_HANDLER, MAX).setTile(m -> () -> new TileEntityQuantumChest(m));

    public static MaterialMachine BRONZE_DRUM = new MaterialMachine(Ref.ID, "bronze_drum", Materials.Bronze, LV);
    public static MaterialMachine STEEL_DRUM = new MaterialMachine(Ref.ID, "steel_drum", Materials.Steel, LV);
    public static MaterialMachine STAINLESS_STEEL_DRUM = new MaterialMachine(Ref.ID, "stainless_steel_drum", Materials.StainlessSteel, LV);
    public static MaterialMachine INVAR_DRUM = new MaterialMachine(Ref.ID, "invar_drum", Materials.Invar, LV);
    public static MaterialMachine TUNGSTEN_DRUM = new MaterialMachine(Ref.ID, "tungsten_drum", Materials.Tungsten, LV);
    public static MaterialMachine TUNGSTENSTEEL_DRUM = new MaterialMachine(Ref.ID, "tungstensteel_drum", Materials.TungstenSteel, LV);
    public static MaterialMachine NETHERITE_DRUM = new MaterialMachine(Ref.ID, "netherite_drum", Materials.Netherite, LV);

    public static GeneratorMachine STEAM_TURBINE = new GeneratorMachine(Ref.ID, "steam_turbine", STEAM_FUELS, LV, GUI, ITEM, FLUID, GENERATOR, CELL, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);
    public static GeneratorMachine GAS_TURBINE = new GeneratorMachine(Ref.ID, "gas_turbine", GAS_FUELS, LV, GUI, FLUID, GENERATOR, CELL, ITEM, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);
    public static BasicMachine HEAT_EXCHANGER = new BasicMachine(Ref.ID, "heat_exchanger", LAVA_FUELS, LV, GUI, ITEM, FLUID, CELL, Textures.TIER_HANDLER).covers(COVEROUTPUT);
    public static GeneratorMachine DIESEL_GENERATOR = new GeneratorMachine(Ref.ID, "diesel_generator", DIESEL_FUELS, LV, GUI, FLUID, GENERATOR, CELL, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(Ref.ID, "semifluid_generator", SEMIFLUID_FUELS, LV, GUI, FLUID, GENERATOR, CELL, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);
    public static GeneratorMachine WINDMILL = new GeneratorMachine(Ref.ID, "windmill", ULV, GENERATOR, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);
    public static GeneratorMachine WATERMILL = new GeneratorMachine(Ref.ID, "watermill", ULV, GENERATOR, Textures.TIER_HANDLER).covers(COVERNONE,COVERNONE,COVERNONE,COVERNONE,COVERNONE, COVER_DYNAMO_OLD);

    public static BasicMachine INFINITE_STORAGE = new BasicMachine(Ref.ID, "infinite_storage", ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV, MAX, ENERGY, CONFIGURABLE, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityInfiniteStorage(m, 16))
            .covers((ICover[]) null);
    public static TankMachine INFINITE_STEAM = new TankMachine(Ref.ID, "infinite_steam", FLUID, CELL, CONFIGURABLE, GUI, LV, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityInfiniteFluid(m));
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(Ref.ID, "battery_buffer_one", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERONE, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(Ref.ID, "battery_buffer_four", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERFOUR, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine BATTERY_BUFFER_NINE = new BasicMachine(Ref.ID, "battery_buffer_nine", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERNINE, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine TRANSFORMER = new BasicMachine(Ref.ID, "transformer", ENERGY, CONFIGURABLE, LV, MV, HV, IV, EV, LUV, UV, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityTransformer(m, 1)).covers((ICover[]) null);
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(Ref.ID, "transformer_digital", EV, IV, GUI, ENERGY, CONFIGURABLE, Textures.TIER_HANDLER).setTile(m -> () -> new TileEntityDigitalTransformer(m)).covers((ICover[]) null);//.setTiers();

    public static void init() {
        STEAM_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        GAS_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        DIESEL_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        SEMIFLUID_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        WINDMILL.setOutputCover(COVER_DYNAMO_OLD);
        WATERMILL.setOutputCover(COVER_DYNAMO_OLD);
    }
}
