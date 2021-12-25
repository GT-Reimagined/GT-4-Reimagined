package trinsdar.gt4r.data;

import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.antimatter.machine.types.BasicMultiMachine;
import muramasa.antimatter.machine.types.GeneratorMachine;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.machine.types.MultiMachine;
import muramasa.antimatter.machine.types.TankMachine;
import muramasa.antimatter.tile.single.TileEntityBatteryBuffer;
import muramasa.antimatter.tile.single.TileEntityDigitalTransformer;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.machine.ChestMachine;
import trinsdar.gt4r.machine.MaterialMachine;
import trinsdar.gt4r.machine.NonSolidMachine;
import trinsdar.gt4r.machine.StorageMachine;
import trinsdar.gt4r.tile.multi.TileEntityCharcoalPit;
import trinsdar.gt4r.tile.multi.TileEntityCokeOven;
import trinsdar.gt4r.tile.multi.TileEntityDistillationTower;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;
import trinsdar.gt4r.tile.multi.TileEntityImplosionCompressor;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialGrinder;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialSawmill;
import trinsdar.gt4r.tile.multi.TileEntityLargeHeatExchanger;
import trinsdar.gt4r.tile.multi.TileEntityLargeTurbine;
import trinsdar.gt4r.tile.multi.TileEntityPrimitiveBlastFurnace;
import trinsdar.gt4r.tile.multi.TileEntityPyrolysisOven;
import trinsdar.gt4r.tile.multi.TileEntityThermalBoiler;
import trinsdar.gt4r.tile.multi.TileEntityVacuumFreezer;
import trinsdar.gt4r.tile.single.TileEntityCabinet;
import trinsdar.gt4r.tile.single.TileEntityChest;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;
import trinsdar.gt4r.tile.single.TileEntityCoveredGenerator;
import trinsdar.gt4r.tile.single.TileEntityDigitalChest;
import trinsdar.gt4r.tile.single.TileEntityDrum;
import trinsdar.gt4r.tile.single.TileEntityDustBin;
import trinsdar.gt4r.tile.single.TileEntityForgeHammer;
import trinsdar.gt4r.tile.single.TileEntityHeatExchanger;
import trinsdar.gt4r.tile.single.TileEntityInfiniteFluid;
import trinsdar.gt4r.tile.single.TileEntityInfiniteStorage;
import trinsdar.gt4r.tile.single.TileEntityItemFilter;
import trinsdar.gt4r.tile.single.TileEntityLocker;
import trinsdar.gt4r.tile.single.TileEntityPump;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;
import trinsdar.gt4r.tile.single.TileEntityQuantumTank;
import trinsdar.gt4r.tile.single.TileEntitySteamMachine;
import trinsdar.gt4r.tile.single.TileEntityTranslocator;
import trinsdar.gt4r.tile.single.TileEntityTypeFilter;
import trinsdar.gt4r.tile.single.TileEntityWorkbench;

import java.util.Arrays;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.cover.ICover.emptyFactory;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.COVER_DYNAMO_OLD;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_INPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_OUTPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_STEAM_VENT;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new BasicMachine(Ref.ID, "alloy_smelter").setMap(ALLOY_SMELTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine ASSEMBLER = new BasicMachine(Ref.ID, "assembler").setMap(ASSEMBLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine BENDER = new BasicMachine(Ref.ID, "plate_bender").setMap(BENDING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new BasicMachine(Ref.ID, "canner").setMap(CANNING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine COMPRESSOR = new BasicMachine(Ref.ID, "compressor").setMap(COMPRESSING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(Ref.ID, "cutter").setMap(CUTTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine FURNACE = new BasicMachine(Ref.ID, "furnace").setMap(SMELTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(Ref.ID, "extractor").setMap(EXTRACTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder").setMap(EXTRUDING).setTiers(MV).addFlags(GUI, ITEM).custom();
    public static BasicMachine LATHE = new BasicMachine(Ref.ID, "lathe").setMap(LATHING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine MACERATOR = new BasicMachine(Ref.ID, "macerator").setMap(MACERATING).addFlags(GUI, ITEM).setTiers(LV, MV).custom().overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER);
    public static BasicMachine RECYCLER = new BasicMachine(Ref.ID, "recycler").setMap(RECYCLING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine SCANNER = new BasicMachine(Ref.ID, "scanner").setMap(SCANNING).addFlags(GUI, ITEM, FLUID).setTiers(HV);
    public static BasicMachine WIRE_MILL = new BasicMachine(Ref.ID, "wire_mill").setMap(WIRE_MILLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine CENTRIFUGE = new BasicMachine(Ref.ID, "centrifuge").setMap(CENTRIFUGING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ELECTROLYZER = new BasicMachine(Ref.ID, "electrolyzer").setMap(ELECTROLYZING).addFlags(GUI, ITEM, FLUID).setTiers(LV, MV);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(Ref.ID, "chemical_reactor").setMap(CHEMICAL_REACTING).setTiers(MV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_CANNER = new BasicMachine(Ref.ID, "fluid_canner").setMap(FLUID_CANNING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(Ref.ID, "disassembler").setMap(DISASSEMBLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(Ref.ID, "mass_fabricator").setMap(MASS_FABRICATING).setTiers(EV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine REPLICATOR = new BasicMachine(Ref.ID, "replicator").setMap(REPLICATING).setTiers(EV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(Ref.ID, "forge_hammer").setMap(HAMMERING).setTiers(LV).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityForgeHammer(m));
    public static BasicMachine ORE_WASHER = new BasicMachine(Ref.ID, "ore_washer").setMap(ORE_WASHING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(Ref.ID, "thermal_centrifuge").setMap(THERMAL_CENTRIFUGING).setTiers(MV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(Ref.ID, "fluid_extractor").setMap(FLUID_EXTRACTING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(Ref.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PUMP = new BasicMachine(Ref.ID, "pump").addFlags(GUI, ITEM, FLUID).setTiers(LV).setTile(m -> () -> new TileEntityPump(m));
    public static BasicMachine SIFTER = new BasicMachine(Ref.ID, "sifter").setMap(SIFTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine BATH = new BasicMachine(Ref.ID, "bath").setMap(BATHING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine DISTILLERY = new BasicMachine(Ref.ID, "distillery").setMap(BASIC_DISTILLING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FERMENTER = new BasicMachine(Ref.ID, "fermenter").setMap(FERMENTING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static NonSolidMachine DUSTBIN = new NonSolidMachine(Ref.ID, "dustbin").setMap(RecipeMaps.DUSTBIN).addFlags(GUI, ITEM).setTiers(LV).custom().baseTexture(Textures.DUSTBIN_HANDLER).covers(emptyFactory).frontCovers().setTile(m -> () -> new TileEntityDustBin(m));

    public static BasicMachine COAL_BOILER = new BasicMachine(Ref.ID, "coal_boiler").setMap(COAL_BOILERS).setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntityCoalBoiler(m)).noCovers();
    public static BasicMachine STEAM_FURNACE = new BasicMachine(Ref.ID, "steam_furnace").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_SMELTING).baseTexture(Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_MACERATOR = new BasicMachine(Ref.ID, "steam_macerator").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_MACERATING).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_EXTRACTOR = new BasicMachine(Ref.ID, "steam_extractor").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_EXTRACTING).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_FORGE_HAMMER = new BasicMachine(Ref.ID, "steam_forge_hammer").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_HAMMERING).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_COMPRESSOR = new BasicMachine(Ref.ID, "steam_compressor").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_COMPRESSING).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);
    public static BasicMachine STEAM_ALLOY_SMELTER = new BasicMachine(Ref.ID, "steam_alloy_smelter").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_ALLOY_SMELTING).baseTexture(Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntitySteamMachine(m)).covers(COVER_STEAM_VENT);

    public static BasicMachine ELECTRIC_ITEM_FILTER = new BasicMachine(Ref.ID, "electric_item_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityItemFilter(m)).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_TYPE_FILTER = new BasicMachine(Ref.ID, "electric_type_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityTypeFilter(m)).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_ITEM_TRANSLOCATOR = new BasicMachine(Ref.ID, "electric_item_translocator").setTiers(LV).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityTranslocator.TileEntityItemTranslocator(m)).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_FLUID_TRANSLOCATOR = new BasicMachine(Ref.ID, "electric_fluid_translocator").setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityTranslocator.TileEntityFluidTranslocator(m)).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);

    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(Ref.ID, "coke_oven").setMap(COKING).setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityCokeOven(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "primitive_blast_furnace").setMap(BASIC_BLASTING).setTiers(BRONZE).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityPrimitiveBlastFurnace(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(Ref.ID, "charcoal_pit").setTiers(LV).setTile(m -> () -> new TileEntityCharcoalPit(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "industrial_blast_furnace").setMap(BLASTING).setTiers(MV).addFlags(GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityIndustrialBlastFurnace(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> IMPLOSION_COMPRESSOR = new BasicMultiMachine<>(Ref.ID, "implosion_compressor").setMap(IMPLOSION_COMPRESSING).setTiers(LV).addFlags(GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityImplosionCompressor(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> INDUSTRIAL_GRINDER = new BasicMultiMachine<>(Ref.ID, "industrial_grinder").setMap(INDUSTRIAL_GRINDING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityIndustrialGrinder(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> INDUSTRIAL_SAWMILL = new BasicMultiMachine<>(Ref.ID, "industrial_sawmill").setMap(INDUSTRIAL_SAWMILLING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityIndustrialSawmill(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> DISTILLATION_TOWER = new BasicMultiMachine<>(Ref.ID, "distillation_tower").setMap(DISTILLING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(m -> () -> new TileEntityDistillationTower(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> VACUUM_FREEZER = new BasicMultiMachine<>(Ref.ID, "vacuum_freezer").setMap(VACUUM_FREEZING).setTiers(LV).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(m -> () -> new TileEntityVacuumFreezer(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMultiMachine<?> PYROLYSIS_OVEN = new BasicMultiMachine<>(Ref.ID, "pyrolysis_oven").setTiers(LV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(m -> () -> new TileEntityPyrolysisOven(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine THERMAL_BOILER = new MultiMachine(Ref.ID, "thermal_boiler").setMap(THERMAL_BOILER_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityThermalBoiler(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine LARGE_STEAM_TURBINE = new MultiMachine(Ref.ID, "large_steam_turbine").setMap(LARGE_STEAM_FUELS).setTiers(EV).addFlags(GUI, FLUID, ITEM, ENERGY, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine LARGE_GAS_TURBINE = new MultiMachine(Ref.ID, "large_gas_turbine").setMap(LARGE_GAS_FUELS).setTiers(IV).addFlags(GUI, FLUID, ITEM, ENERGY, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine LARGE_HEAT_EXCHANGER = new MultiMachine(Ref.ID, "large_heat_exchanger").setTiers(EV).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityLargeHeatExchanger(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_control_computer").setMap(FUSION).setTiers(IV).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityFusionReactor(m)).custom(Textures.STANDARD_MACHINE_MODELS);

    public static HatchMachine HATCH_ITEM_I = new HatchMachine(Ref.ID, "item_input_hatch", COVERINPUT).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(Ref.ID, "item_output_hatch", COVEROUTPUT).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(Ref.ID, "fluid_input_hatch", COVERINPUT).addFlags(GUI, FLUID, CELL).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(Ref.ID, "fluid_output_hatch", COVEROUTPUT).addFlags(GUI, FLUID, CELL).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(Ref.ID, "muffler_hatch", COVERMUFFLER).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "dynamo_hatch", COVER_DYNAMO_OLD).addFlags(ENERGY).setTiers(EV, IV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_INJECTOR = new HatchMachine(Ref.ID, "fusion_item_injector", COVER_FUSION_INPUT).addFlags(GUI, ITEM).baseTexture(Textures.FUSION_IN).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_item_extractor", COVER_FUSION_OUTPUT).addFlags(GUI, ITEM).baseTexture(Textures.FUSION_OUT).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_INJECTOR = new HatchMachine(Ref.ID, "fusion_fluid_injector", COVER_FUSION_INPUT).addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_IN).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_fluid_extractor", COVER_FUSION_OUTPUT).addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_OUT).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ENERGY_INJECTOR = new HatchMachine(Ref.ID, "fusion_energy_injector", emptyFactory).addFlags(ENERGY).baseTexture(Textures.FUSION_IN).setTiers(IV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ENERGY_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_energy_extractor", COVER_DYNAMO_OLD).addFlags(ENERGY).baseTexture(Textures.FUSION_OUT).setTiers(UV).setAllowVerticalFacing(false).allowFrontIO();

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank").addFlags(GUI, CELL).setTiers(MAX).setTile(m -> () -> new TileEntityQuantumTank(m)).frontCovers().allowFrontIO();
    public static StorageMachine QUANTUM_CHEST = new StorageMachine(Ref.ID, "quantum_chest").addFlags(GUI, ITEM).setTiers(MAX).setTile(m -> () -> new TileEntityQuantumChest(m));
    public static StorageMachine DIGITAL_CHEST = new StorageMachine(Ref.ID, "digital_chest").addFlags(GUI, ITEM).setTiers(LV).setTile(m -> () -> new TileEntityDigitalChest(m));

    public static MaterialMachine BRONZE_DRUM = new MaterialMachine(Ref.ID, "bronze_drum", Materials.Bronze).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine STEEL_DRUM = new MaterialMachine(Ref.ID, "steel_drum", Materials.Steel).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine STAINLESS_STEEL_DRUM = new MaterialMachine(Ref.ID, "stainless_steel_drum", Materials.StainlessSteel).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine INVAR_DRUM = new MaterialMachine(Ref.ID, "invar_drum", Materials.Invar).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine TUNGSTEN_DRUM = new MaterialMachine(Ref.ID, "tungsten_drum", Materials.Tungsten).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine TUNGSTENSTEEL_DRUM = new MaterialMachine(Ref.ID, "tungstensteel_drum", Materials.TungstenSteel).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();
    public static MaterialMachine NETHERITE_DRUM = new MaterialMachine(Ref.ID, "netherite_drum", Netherite).setTiers(LV).baseTexture(Textures.DRUM_HANDLER).overlayTexture(Textures.DRUM_OVERLAY_HANDLER).addFlags(COVERABLE, FLUID).setTile(m -> () -> new TileEntityDrum(m)).allowFrontIO();

    public static MaterialMachine IRON_CABINET = new MaterialMachine(Ref.ID, "iron_cabinet", Iron).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 222));
    public static MaterialMachine ALUMINIUM_CABINET = new MaterialMachine(Ref.ID, "aluminium_cabinet", Materials.Aluminium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 222));
    public static MaterialMachine WROUGHT_IRON_CABINET = new MaterialMachine(Ref.ID, "wrought_iron_cabinet", Materials.WroughtIron).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 222));
    public static MaterialMachine BRASS_CABINET = new MaterialMachine(Ref.ID, "brass_cabinet", Materials.Brass).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 222));
    public static MaterialMachine CUPRONICKEL_CABINET = new MaterialMachine(Ref.ID, "cupronickel_cabinet", Materials.Cupronickel).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 222));
    public static MaterialMachine ELECTRUM_CABINET = new MaterialMachine(Ref.ID, "electrum_cabinet", Materials.Electrum).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 240));
    public static MaterialMachine GOLD_CABINET = new MaterialMachine(Ref.ID, "gold_cabinet", Gold).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 240));
    public static MaterialMachine SILVER_CABINET = new MaterialMachine(Ref.ID, "silver_cabinet", Materials.Silver).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 240));
    public static MaterialMachine MAGNALIUM_CABINET = new MaterialMachine(Ref.ID, "magnalium_cabinet", Materials.Magnalium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 240));
    public static MaterialMachine PLATINUM_CABINET = new MaterialMachine(Ref.ID, "platinum_cabinet", Materials.Platinum).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 258));
    public static MaterialMachine OSMIUM_CABINET = new MaterialMachine(Ref.ID, "osmium_cabinet", Materials.Osmium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityCabinet(m, 276));

    public static MaterialMachine IRON_CHEST = new ChestMachine(Ref.ID, "iron_chest",Iron).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 222)).tesr();
    public static MaterialMachine ALUMINIUM_CHEST = new ChestMachine(Ref.ID, "aluminium_chest", Materials.Aluminium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 222)).tesr();
    public static MaterialMachine WROUGHT_IRON_CHEST = new ChestMachine(Ref.ID, "wrought_iron_chest", Materials.WroughtIron).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 222)).tesr();
    public static MaterialMachine BRASS_CHEST = new ChestMachine(Ref.ID, "brass_chest", Materials.Brass).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 222)).tesr();
    public static MaterialMachine CUPRONICKEL_CHEST = new ChestMachine(Ref.ID, "cupronickel_chest", Materials.Cupronickel).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 222)).tesr();
    public static MaterialMachine ELECTRUM_CHEST = new ChestMachine(Ref.ID, "electrum_chest", Materials.Electrum).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 240)).tesr();
    public static MaterialMachine GOLD_CHEST = new ChestMachine(Ref.ID, "gold_chest", Gold).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 240)).tesr();
    public static MaterialMachine SILVER_CHEST = new ChestMachine(Ref.ID, "silver_chest", Materials.Silver).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 240)).tesr();
    public static MaterialMachine MAGNALIUM_CHEST = new ChestMachine(Ref.ID, "magnalium_chest", Materials.Magnalium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 240)).tesr();
    public static MaterialMachine PLATINUM_CHEST = new ChestMachine(Ref.ID, "platinum_chest", Materials.Platinum).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 258)).tesr();
    public static MaterialMachine OSMIUM_CHEST = new ChestMachine(Ref.ID, "osmium_chest", Materials.Osmium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile(m -> () -> new TileEntityChest(m, 276)).tesr();

    public static MaterialMachine BRONZE_WORKBENCH = new MaterialMachine(Ref.ID, "bronze_workbench", Materials.Bronze).setTiers(LV).baseTexture(Textures.WORKBENCH_HANDLER).overlayTexture(Textures.WORKBENCH_OVERLAY_HANDLER).addFlags(ITEM, GUI).setTile(m -> () -> new TileEntityWorkbench(m));
    public static MaterialMachine IRON_WORKBENCH = new MaterialMachine(Ref.ID, "iron_workbench", Iron).setTiers(LV).baseTexture(Textures.WORKBENCH_HANDLER).overlayTexture(Textures.WORKBENCH_OVERLAY_HANDLER).addFlags(ITEM, GUI).setTile(m -> () -> new TileEntityWorkbench(m));
    public static MaterialMachine ALUMINIUM_WORKBENCH = new MaterialMachine(Ref.ID, "aluminium_workbench", Materials.Aluminium).setTiers(LV).baseTexture(Textures.WORKBENCH_HANDLER).overlayTexture(Textures.WORKBENCH_OVERLAY_HANDLER).addFlags(ITEM, GUI).setTile(m -> () -> new TileEntityWorkbench(m));
    public static MaterialMachine IRON_CHARGING_WORKBENCH = new MaterialMachine(Ref.ID, "iron_charging_workbench", Iron).setTiers(HV).baseTexture(Textures.WORKBENCH_HANDLER).overlayTexture(Textures.CHARGING_WORKBENCH_OVERLAY_HANDLER).addFlags(ITEM, GUI, ENERGY).setTile(m -> () -> new TileEntityWorkbench(m));
    public static MaterialMachine ALUMINIUM_CHARGING_WORKBENCH = new MaterialMachine(Ref.ID, "aluminium_charging_workbench", Materials.Aluminium).setTiers(HV).baseTexture(Textures.WORKBENCH_HANDLER).overlayTexture(Textures.CHARGING_WORKBENCH_OVERLAY_HANDLER).addFlags(ITEM, GUI, ENERGY).setTile(m -> () -> new TileEntityWorkbench(m));
    public static MaterialMachine IRON_LOCKER = new MaterialMachine(Ref.ID, "iron_locker", Iron).setTiers(LV).baseTexture(Textures.LOCKER_HANDLER).overlayTexture(Textures.LOCKER_OVERLAY_HANDLER).addFlags(ITEM, GUI).setTile(m -> () -> new TileEntityLocker(m));
    public static MaterialMachine ALUMINIUM_LOCKER = new MaterialMachine(Ref.ID, "aluminium_locker", Materials.Aluminium).setTiers(LV).baseTexture(Textures.LOCKER_HANDLER).overlayTexture(Textures.LOCKER_OVERLAY_HANDLER).addFlags(ITEM, GUI).setTile(m -> () -> new TileEntityLocker(m));
    public static MaterialMachine IRON_CHARGING_LOCKER = new MaterialMachine(Ref.ID, "iron_charging_locker", Iron).setTiers(HV).baseTexture(Textures.LOCKER_HANDLER).overlayTexture(Textures.CHARGING_LOCKER_OVERLAY_HANDLER).addFlags(ITEM, GUI, ENERGY).setTile(m -> () -> new TileEntityLocker(m));
    public static MaterialMachine ALUMINIUM_CHARGING_LOCKER = new MaterialMachine(Ref.ID, "aluminium_charging_locker", Materials.Aluminium).setTiers(HV).baseTexture(Textures.LOCKER_HANDLER).overlayTexture(Textures.CHARGING_LOCKER_OVERLAY_HANDLER).addFlags(ITEM, GUI, ENERGY).setTile(m -> () -> new TileEntityLocker(m));

    public static GeneratorMachine STEAM_TURBINE = new GeneratorMachine(Ref.ID, "steam_turbine").frontCovers().allowFrontIO().setMap(STEAM_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static GeneratorMachine GAS_TURBINE = new GeneratorMachine(Ref.ID, "gas_turbine").frontCovers().allowFrontIO().setMap(GAS_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static BasicMachine HEAT_EXCHANGER = new BasicMachine(Ref.ID, "heat_exchanger").setMap(HOT_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, CELL).overlayTexture(Textures.LEFT_RIGHT_HANDLER).covers(emptyFactory).setTile(m -> () -> new TileEntityHeatExchanger(m));
    public static GeneratorMachine DIESEL_GENERATOR = new GeneratorMachine(Ref.ID, "diesel_generator").frontCovers().allowFrontIO().setMap(DIESEL_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(Ref.ID, "semifluid_generator").frontCovers().allowFrontIO().setMap(SEMIFLUID_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static GeneratorMachine WINDMILL = new GeneratorMachine(Ref.ID, "windmill").setTiers(ULV).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).allowFrontIO().setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m)).custom(Textures.STANDARD_MACHINE_MODELS);
    public static GeneratorMachine WATERMILL = new GeneratorMachine(Ref.ID, "watermill").setTiers(ULV).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).allowFrontIO().setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(m -> () -> new TileEntityCoveredGenerator(m));

    public static BasicMachine INFINITE_STORAGE = new BasicMachine(Ref.ID, "infinite_storage").addFlags(ENERGY, GUI).setTiers(MAX).allowFrontIO().setTile(m -> () -> new TileEntityInfiniteStorage<>(m))
            .noCovers();
    public static TankMachine INFINITE_STEAM = new TankMachine(Ref.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTiers(LV).setTile(m -> () -> new TileEntityInfiniteFluid(m));
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(Ref.ID, "battery_buffer_one").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(Ref.ID, "battery_buffer_four").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(Ref.ID, "battery_buffer_eight").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine TRANSFORMER = new BasicMachine(Ref.ID, "transformer").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile(m -> () -> new TileEntityTransformer<>(m, 1)).setTiers(Arrays.stream(getAllElectric()).filter(t -> t != ULV && t != MAX).toArray(Tier[]::new)).noCovers().setAllowVerticalFacing(true);
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(Ref.ID, "transformer_digital").addFlags(GUI, ENERGY).setTiers(EV, IV).setTile(m -> () -> new TileEntityDigitalTransformer<>(m)).noCovers();//.setTiers();

    public static void init() {
        STEAM_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        GAS_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        DIESEL_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        SEMIFLUID_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        WINDMILL.setOutputCover(COVER_DYNAMO_OLD);
        WATERMILL.setOutputCover(COVER_DYNAMO_OLD);
        ENERGY.remove(HEAT_EXCHANGER);
        ENERGY.remove(DUSTBIN);
    }
}
