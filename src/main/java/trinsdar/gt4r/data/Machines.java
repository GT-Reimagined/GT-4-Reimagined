package trinsdar.gt4r.data;

import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.tile.single.TileEntityBatteryBuffer;
import muramasa.antimatter.tile.single.TileEntityDigitalTransformer;
import muramasa.antimatter.tile.single.TileEntityInfiniteStorage;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import trinsdar.gt4r.machine.HatchMachineCustom;
import trinsdar.gt4r.tile.single.TileEntityInfiniteFluid;
import trinsdar.gt4r.tile.single.TileEntitySteamMachine;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.tile.multi.*;


import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.MachineFlag.CELL;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new BasicMachine(Ref.ID, "alloy_smelter", ALLOY_SMELTING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine ASSEMBLER = new BasicMachine(Ref.ID, "assembler", ASSEMBLING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine BENDER = new BasicMachine(Ref.ID, "plate_bender", BENDING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine CANNER = new BasicMachine(Ref.ID, "canner", CANNING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine COMPRESSOR = new BasicMachine(Ref.ID, "compressor", COMPRESSING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine CUTTER = new BasicMachine(Ref.ID, "cutter", CUTTING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine FURNACE = new BasicMachine(Ref.ID, "furnace", SMELTING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine EXTRACTOR = new BasicMachine(Ref.ID, "extractor", EXTRACTING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder", EXTRUDING, GUI, ITEM, MV, Textures.MACHINE_BASE);
    public static BasicMachine LATHE = new BasicMachine(Ref.ID, "lathe", LATHING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine MACERATOR = new BasicMachine(Ref.ID, "macerator", MACERATING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine RECYCLER = new BasicMachine(Ref.ID, "recycler", RECYCLING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine WIRE_MILL = new BasicMachine(Ref.ID, "wire_mill", WIRE_MILLING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine CENTRIFUGE = new BasicMachine(Ref.ID, "centrifuge", CENTRIFUGING, GUI, ITEM, FLUID, LV, Textures.MACHINE_BASE);
    public static BasicMachine ELECTROLYZER = new BasicMachine(Ref.ID, "electrolyzer", ELECTROLYZING, GUI, ITEM, FLUID, MV, Textures.MACHINE_BASE);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(Ref.ID, "chemical_reactor", CHEMICAL_REACTING, GUI, ITEM, FLUID, MV, Textures.MACHINE_BASE);
    public static BasicMachine FLUID_CANNER = new BasicMachine(Ref.ID, "fluid_canner", FLUID_CANNING, GUI, ITEM, FLUID, LV, Textures.MACHINE_BASE);
    public static BasicMachine DISASSEMBLER = new BasicMachine(Ref.ID, "disassembler", DISASSEMBLING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(Ref.ID, "mass_fabricator", MASS_FABRICATING, GUI, ITEM, FLUID, EV, Textures.MACHINE_BASE);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(Ref.ID, "forge_hammer", HAMMERING, GUI, ITEM, LV, Textures.MACHINE_BASE);
    public static BasicMachine ORE_WASHER = new BasicMachine(Ref.ID, "ore_washer", ORE_WASHING, GUI, ITEM, FLUID, LV, Textures.MACHINE_BASE);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(Ref.ID, "thermal_centrifuge", THERMAL_CENTRIFUGING, GUI, ITEM, FLUID, MV, Textures.MACHINE_BASE);


    public static BasicMachine COAL_BOILER = new BasicMachine(Ref.ID, "coal_boiler", SMALL_BOILERS, BRONZE, GUI, STEAM, ITEM, FLUID, Textures.BOILER_HANDLER);
    public static BasicMachine LAVA_BOILER = new BasicMachine(Ref.ID, "lava_boiler", SMALL_BOILERS, GUI, STEAM, ITEM, FLUID); //TODO
    public static BasicMachine SOLAR_BOILER = new BasicMachine(Ref.ID, "solar_boiler", SMALL_BOILERS, BRONZE, GUI, STEAM, ITEM, FLUID); //TODO
    public static BasicMachine STEAM_FURNACE = new BasicMachine(Ref.ID, "steam_furnace", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_SMELTING).setTile(m -> () -> new TileEntitySteamMachine(m));
    public static BasicMachine STEAM_MACERATOR = new BasicMachine(Ref.ID, "steam_macerator", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_MACERATING).setTile(m -> () -> new TileEntitySteamMachine(m));
    public static BasicMachine STEAM_EXTRACTOR = new BasicMachine(Ref.ID, "steam_extractor", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_EXTRACTING).setTile(m -> () -> new TileEntitySteamMachine(m));
    public static BasicMachine STEAM_FORGE_HAMMER = new BasicMachine(Ref.ID, "steam_forge_hammer", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_HAMMERING).setTile(m -> () -> new TileEntitySteamMachine(m));
    public static BasicMachine STEAM_COMPRESSOR = new BasicMachine(Ref.ID, "steam_compressor", BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_COMPRESSING).setTile(m -> () -> new TileEntitySteamMachine(m));
    public static BasicMachine STEAM_ALLOY_SMELTER = new BasicMachine(Ref.ID, "steam_alloy_smelter",BRONZE, GUI, STEAM, ITEM, FLUID, STEAM_ALLOY_SMELTING).setTile(m -> () -> new TileEntitySteamMachine(m));

    public static MultiMachine COKE_OVEN = new MultiMachine(Ref.ID, "coke_oven", COKING, LV, GUI, ITEM).setTile(m -> () -> new TileEntityCokeOven(m));
    public static MultiMachine PRIMITIVE_BLAST_FURNACE = new MultiMachine(Ref.ID, "primitive_blast_furnace", BASIC_BLASTING, BRONZE, GUI, ITEM).setTile(m -> () -> new TileEntityPrimitiveBlastFurnace(m));
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(Ref.ID, "charcoal_pit", LV).setTile(m -> () -> new TileEntityCharcoalPit(m));
    public static MultiMachine BLAST_FURNACE = new MultiMachine(Ref.ID, "industrial_blast_furnace", BLASTING, MV, GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityElectricBlastFurnace(m));
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(Ref.ID, "implosion_compressor", IMPLOSION_COMPRESSING, MV, GUI, ITEM,ENERGY).setTile(m -> () -> new TileEntityImplosionCompressor(m));
    public static MultiMachine INDUSTRIAL_GRINDER = new MultiMachine(Ref.ID, "industrial_grinder", INDUSTRIAL_GRINDING, MV, GUI, ITEM, ENERGY, FLUID_INPUT).setTile(m -> () -> new TileEntityIndustrialGrinder(m));
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(Ref.ID, "vacuum_freezer", VACUUM_FREEZING, LV, GUI, ITEM, FLUID,ENERGY).setTile(m -> () -> new TileEntityVacuumFreezer(m));
    public static MultiMachine THERMAL_BOILER = new MultiMachine(Ref.ID, "thermal_boiler", LV, GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityLargeBoiler(m));
    public static MultiMachine LARGE_STEAM_TURBINE = new MultiMachine(Ref.ID, "large_steam_turbine", EV, GUI, FLUID,ENERGY, STEAM_FUELS, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine LARGE_GAS_TURBINE = new MultiMachine(Ref.ID, "large_gas_turbine", IV, GUI, FLUID,ENERGY, GAS_FUELS, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(Ref.ID, "heat_exchanger", EV, GUI, FLUID,ENERGY).setTile(m -> () -> new TileEntityHeatExchanger(m));
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_control_computer", FUSION, IV,  GUI, FLUID,ENERGY).setTile(m -> () -> new TileEntityFusionReactor(m));

    public static HatchMachine HATCH_ITEM_I = new HatchMachineCustom(Ref.ID, "hatch_item_input", GUI, ITEM,COVERINPUT, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine HATCH_ITEM_O = new HatchMachineCustom(Ref.ID, "hatch_item_output", GUI, ITEM, COVEROUTPUT, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine HATCH_FLUID_I = new HatchMachineCustom(Ref.ID, "hatch_fluid_input", GUI, FLUID, COVERINPUT, CELL, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine HATCH_FLUID_O = new HatchMachineCustom(Ref.ID, "hatch_fluid_output", GUI, FLUID, COVEROUTPUT, CELL, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine HATCH_MUFFLER = new HatchMachineCustom(Ref.ID, "hatch_muffler", GUI, ITEM,COVERMUFFLER, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "hatch_dynamo", ENERGY,COVERDYNAMO, EV, Textures.MACHINE_BASE).setTiers(EV, IV);
    public static HatchMachine FUSION_MATERIAL_INJECTOR = new HatchMachineCustom(Ref.ID, "fusion_material_injector", GUI, ITEM, FLUID, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine FUSION_MATERIAL_EXTRACTOR = new HatchMachineCustom(Ref.ID, "fusion_material_extractor", GUI, ITEM, FLUID, LV, Textures.MACHINE_BASE).setTiers(LV);
    public static HatchMachine FUSION_ENERGY_INJECTOR = new HatchMachine(Ref.ID, "fusion_energy_injector", ENERGY, IV, Textures.MACHINE_BASE).setTiers(IV);
    public static HatchMachine FUSION_ENERGY_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_energy_extractor", ENERGY, UV, Textures.MACHINE_BASE).setTiers(UV);

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank", GUI, CELL);

    public static BasicMachine STEAM_GENERATOR = new BasicMachine(Ref.ID, "steam_generator", STEAM_FUELS, LV, MV, HV, GUI, ITEM, FLUID, GENERATOR, CELL);
    public static BasicMachine GAS_GENERATOR = new BasicMachine(Ref.ID, "gas_generator", GAS_FUELS, LV, MV, HV, GUI, ITEM, FLUID, GENERATOR, CELL);
    public static BasicMachine COMBUSTION_GENERATOR = new BasicMachine(Ref.ID, "combustion_generator", COMBUSTION_FUELS, LV, MV, HV, GUI, ITEM, FLUID, GENERATOR, CELL);

    public static BasicMachine INFINITE_STORAGE = new BasicMachine(Ref.ID, "infinite_storage", ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV, MAX, ENERGY, CONFIGURABLE).setTile(m -> () -> new TileEntityInfiniteStorage(m, 16))
            .covers(COVERNONE);
    public static BasicMachine INFINITE_STEAM = new BasicMachine(Ref.ID, "infinite_steam", FLUID, CELL, CONFIGURABLE, GUI).setTile(m -> () -> new TileEntityInfiniteFluid(m));
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(Ref.ID, "battery_buffer_four", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERFOUR).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(Ref.ID, "battery_buffer_one", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERONE).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine BATTERY_BUFFER_NINE = new BasicMachine(Ref.ID, "battery_buffer_nine", GUI, ENERGY, CONFIGURABLE, ITEM,COVERBUFFERNINE).setTile(m -> () -> new TileEntityBatteryBuffer(m)).frontCovers();
    public static BasicMachine TRANSFORMER = new BasicMachine(Ref.ID, "transformer", ENERGY, CONFIGURABLE, LV, MV, HV, IV, EV, LUV, UV).setTile(m -> () -> new TileEntityTransformer(m, 1));
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(Ref.ID, "transformer_digital", EV, IV, GUI, ENERGY, CONFIGURABLE).setTile(m -> () -> new TileEntityDigitalTransformer(m));//.setTiers();

    public static void init() {

    }
}
