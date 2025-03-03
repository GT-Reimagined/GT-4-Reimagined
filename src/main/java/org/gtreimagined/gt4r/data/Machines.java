package org.gtreimagined.gt4r.data;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.blockentity.single.BlockEntityDigitalTransformer;
import muramasa.antimatter.blockentity.single.BlockEntityTransformer;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.antimatter.machine.types.BasicMultiMachine;
import muramasa.antimatter.machine.types.GeneratorMachine;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.machine.types.MultiMachine;
import muramasa.antimatter.machine.types.TankMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.block.Block;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.machine.DrumMachine;
import org.gtreimagined.gtcore.machine.MultiblockTankMachine;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.block.BlockRedstoneMachine;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityCharcoalPit;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityCokeOven;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityFusionReactor;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityIndustrialBlastFurnace;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityLargeHeatExchanger;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityLargeTurbine;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityPrimitiveBlastFurnace;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityPyrolysisOven;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityThermalBoiler;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityBatteryBox;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityCoalBoiler;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityDigitalChest;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityDigitalTank;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityDustBin;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityForgeHammer;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityHeatExchanger;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityItemFilter;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityMacerator;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityPlayerDetector;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityPump;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityQuantumChest;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityQuantumTank;
import org.gtreimagined.gt4r.blockentity.single.BlockEntitySupercondensator;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityTeleporter;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityTranslocator;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityTypeFilter;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityWatermill;
import org.gtreimagined.gt4r.machine.NonSolidMachine;
import org.gtreimagined.gt4r.machine.SteamMachine;
import org.gtreimagined.gt4r.machine.StorageMachine;
import org.gtreimagined.gt4r.machine.UpgradeableBasicMultiMachine;
import org.gtreimagined.gt4r.machine.UpgradeableMachine;

import java.util.function.Supplier;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.cover.ICover.emptyFactory;
import static muramasa.antimatter.data.AntimatterMaterials.Netherite;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static org.gtreimagined.gt4r.data.GT4RCovers.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.*;

public class Machines {

    public static UpgradeableMachine ALLOY_SMELTER = new UpgradeableMachine(GT4RRef.ID, "alloy_smelter").setMap(RecipeMaps.ALLOY_SMELTER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine ASSEMBLER = new UpgradeableMachine(GT4RRef.ID, "assembler").setMap(RecipeMaps.ASSEMBLER).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static UpgradeableMachine BENDER = new UpgradeableMachine(GT4RRef.ID, "plate_bender").setMap(PLATE_BENDER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine CANNER = new UpgradeableMachine(GT4RRef.ID, "canner").setMap(RecipeMaps.CANNER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine COMPRESSOR = new UpgradeableMachine(GT4RRef.ID, "compressor").setMap(RecipeMaps.COMPRESSOR).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine CUTTER = new UpgradeableMachine(GT4RRef.ID, "cutter").setMap(PLATE_CUTTER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine FURNACE = new UpgradeableMachine(GT4RRef.ID, "furnace").setMap(RecipeMaps.FURNACE).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine EXTRACTOR = new UpgradeableMachine(GT4RRef.ID, "extractor").setMap(RecipeMaps.EXTRACTOR).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine EXTRUDER = new UpgradeableMachine(GT4RRef.ID, "extruder").setTiers(MV).setMap(RecipeMaps.EXTRUDER).addFlags(GUI, ITEM).custom();
    public static UpgradeableMachine LATHE = new UpgradeableMachine(GT4RRef.ID, "lathe").setMap(RecipeMaps.LATHE).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine MACERATOR = new UpgradeableMachine(GT4RRef.ID, "macerator").setTiers(LV, MV).setMap(UNIVERSAL_MACERATOR).addFlags(GUI, ITEM).setTile(BlockEntityMacerator::new).custom().overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTierSpecificLang();
    public static UpgradeableMachine RECYCLER = new UpgradeableMachine(GT4RRef.ID, "recycler").setMap(RecipeMaps.RECYCLER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine SCANNER = new UpgradeableMachine(GT4RRef.ID, "scanner").setTiers(HV).setMap(RecipeMaps.SCANNER).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine WIRE_MILL = new UpgradeableMachine(GT4RRef.ID, "wire_mill").setMap(RecipeMaps.WIRE_MILL).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static UpgradeableMachine CENTRIFUGE = new UpgradeableMachine(GT4RRef.ID, "centrifuge").setMap(RecipeMaps.CENTRIFUGE).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine ELECTROLYZER = new UpgradeableMachine(GT4RRef.ID, "electrolyzer").setMap(RecipeMaps.ELECTROLYZER).addFlags(GUI, ITEM, FLUID).setTiers(LV, MV).setTierSpecificLang();
    public static UpgradeableMachine CHEMICAL_REACTOR = new UpgradeableMachine(GT4RRef.ID, "chemical_reactor").setTiers(MV).setMap(RecipeMaps.CHEMICAL_REACTOR).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine FLUID_CANNER = new UpgradeableMachine(GT4RRef.ID, "fluid_canner").setMap(RecipeMaps.FLUID_CANNER).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine DISASSEMBLER = new UpgradeableMachine(GT4RRef.ID, "disassembler").setMap(RecipeMaps.DISASSEMBLER).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static UpgradeableMachine MASS_FABRICATOR = new UpgradeableMachine(GT4RRef.ID, "mass_fabricator").setTiers(EV).setMap(RecipeMaps.MASS_FABRICATOR).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine REPLICATOR = new UpgradeableMachine(GT4RRef.ID, "replicator").setTiers(EV).setMap(RecipeMaps.REPLICATOR).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine FORGE_HAMMER = new UpgradeableMachine(GT4RRef.ID, "forge_hammer").setMap(RecipeMaps.FORGE_HAMMER).setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityForgeHammer::new);
    public static UpgradeableMachine ORE_WASHER = new UpgradeableMachine(GT4RRef.ID, "ore_washer").setMap(RecipeMaps.ORE_WASHER).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine THERMAL_CENTRIFUGE = new UpgradeableMachine(GT4RRef.ID, "thermal_centrifuge").setTiers(MV).setMap(RecipeMaps.THERMAL_CENTRIFUGE).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine FLUID_PRESS = new UpgradeableMachine(GT4RRef.ID, "fluid_press").setMap(RecipeMaps.FLUID_PRESS).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine FLUID_SOLIDIFIER = new UpgradeableMachine(GT4RRef.ID, "fluid_solidifier").setMap(RecipeMaps.FLUID_SOLIDIFIER).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PUMP = new BasicMachine(GT4RRef.ID, "pump").addFlags(GUI, ITEM, FLUID).setTiers(LV).setTile(BlockEntityPump::new).baseTexture(Textures.BASE_HANDLER);
    public static UpgradeableMachine SIFTER = new UpgradeableMachine(GT4RRef.ID, "sifter").setMap(RecipeMaps.SIFTER).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine SMELTER = new UpgradeableMachine(GT4RRef.ID, "smelter").setTiers(MV).setMap(RecipeMaps.SMELTER).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine BATH = new UpgradeableMachine(GT4RRef.ID, "bath").setMap(RecipeMaps.BATH).setTiers(LV).addFlags(GUI, ITEM);
    public static UpgradeableMachine DISTILLERY = new UpgradeableMachine(GT4RRef.ID, "distillery").setMap(RecipeMaps.DISTILLERY).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static UpgradeableMachine FERMENTER = new UpgradeableMachine(GT4RRef.ID, "fermenter").setMap(RecipeMaps.FERMENTER).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static NonSolidMachine DUSTBIN = new NonSolidMachine(GT4RRef.ID, "dustbin").setMap(RecipeMaps.DUSTBIN).addFlags(GUI, ITEM).setTiers(LV).custom().baseTexture(Textures.DUSTBIN_HANDLER).covers(emptyFactory).frontCovers().setTile(BlockEntityDustBin::new);

    public static SteamMachine SOLID_FUEL_BOILER = new SteamMachine(GT4RRef.ID, "solid_fuel_boiler").setMap(SOLID_FUEL_BOILERS).setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(BlockEntityCoalBoiler::new).noCovers();
    public static SteamMachine STEAM_FURNACE = new SteamMachine(GT4RRef.ID, "steam_furnace").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_FURNACE).baseTexture(Textures.BRICKED_HANDLER).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_MACERATOR = new SteamMachine(GT4RRef.ID, "steam_macerator").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_MACERATOR).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(GT4RRef.ID, "steam_extractor").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_EXTRACTOR).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(GT4RRef.ID, "steam_forge_hammer").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_FORGE_HAMMER).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(GT4RRef.ID, "steam_compressor").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_COMPRESSOR).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(GT4RRef.ID, "steam_alloy_smelter").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_ALLOY_SMELTER).baseTexture(Textures.BRICKED_HANDLER).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_CUTTER = new SteamMachine(GT4RRef.ID, "steam_cutter").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_CUTTER).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_SIFTER = new SteamMachine(GT4RRef.ID, "steam_sifter").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(RecipeMaps.STEAM_SIFTER).covers(COVER_STEAM_VENT);

    public static BasicMachine TELEPORTER = new BasicMachine(GT4RRef.ID, "teleporter").baseTexture(Textures.BASE_HANDLER).setTiers(HV, LUV).setTile(BlockEntityTeleporter::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER);
    public static BasicMachine COMPUTER_CUBE = new BasicMachine(GT4RRef.ID, "computer_cube").baseTexture(Textures.BASE_HANDLER).setTiers(LV).overlayTexture(Textures.SIMPLE_SIDED).noCovers();
    public static BasicMachine ELECTRIC_ITEM_FILTER = new BasicMachine(GT4RRef.ID, "electric_item_filter").baseTexture(Textures.BASE_HANDLER).setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityItemFilter::new).noCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_TYPE_FILTER = new BasicMachine(GT4RRef.ID, "electric_type_filter").baseTexture(Textures.BASE_HANDLER).setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityTypeFilter::new).noCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_ITEM_TRANSLOCATOR = new BasicMachine(GT4RRef.ID, "electric_item_translocator").baseTexture(Textures.BASE_HANDLER).setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityTranslocator.BlockEntityItemTranslocator::new).noCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_FLUID_TRANSLOCATOR = new BasicMachine(GT4RRef.ID, "electric_fluid_translocator").baseTexture(Textures.BASE_HANDLER).setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityTranslocator.BlockEntityFluidTranslocator::new).noCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);

    public static BasicMachine PLAYER_DETECTOR = new BasicMachine(GT4RRef.ID, "player_detector").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setTile(BlockEntityPlayerDetector::new).setBlock(BlockRedstoneMachine::new).setItemBlockClass(() -> BlockRedstoneMachine.class).frontCovers().noCovers().allowFrontIO().overlayTexture(Textures.SIMPLE_ACTIVE_SIDED);

    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(GT4RRef.ID, "coke_oven").setMap(COKING).setTiers(NONE).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityCokeOven::new);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(GT4RRef.ID, "primitive_blast_furnace").setMap(BASIC_BLASTING).setTiers(NONE).addFlags(GUI, ITEM).setTile(BlockEntityPrimitiveBlastFurnace::new);
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(GT4RRef.ID, "charcoal_pit").setTiers(LV).setTile(BlockEntityCharcoalPit::new);
    public static BasicMultiMachine<?> BLAST_FURNACE = new UpgradeableBasicMultiMachine(GT4RRef.ID, "industrial_blast_furnace").setMap(BLASTING).setTiers(MV).addFlags(GUI, ITEM, EU).setTile(BlockEntityIndustrialBlastFurnace::new);
    public static BasicMultiMachine<?> IMPLOSION_COMPRESSOR = new UpgradeableBasicMultiMachine(GT4RRef.ID, "implosion_compressor").setMap(IMPLOSION_COMPRESSING).setTiers(LV).addFlags(GUI, ITEM, EU);
    public static BasicMultiMachine<?> INDUSTRIAL_GRINDER = new UpgradeableBasicMultiMachine(GT4RRef.ID, "industrial_grinder").setMap(INDUSTRIAL_GRINDING).setTiers(MV).addFlags(GUI, ITEM, EU, FLUID);
    public static BasicMultiMachine<?> INDUSTRIAL_SAWMILL = new UpgradeableBasicMultiMachine(GT4RRef.ID, "industrial_sawmill").setMap(INDUSTRIAL_SAWMILLING).setTiers(MV).addFlags(GUI, ITEM, EU, FLUID);
    public static BasicMultiMachine<?> DISTILLATION_TOWER = new UpgradeableBasicMultiMachine(GT4RRef.ID, "distillation_tower").setMap(DISTILLING).setTiers(MV).addFlags(GUI, ITEM, EU, FLUID);
    public static BasicMultiMachine<?> VACUUM_FREEZER = new UpgradeableBasicMultiMachine(GT4RRef.ID, "vacuum_freezer").setMap(VACUUM_FREEZING).setTiers(LV).addFlags(GUI, ITEM, FLUID, EU);
    public static BasicMultiMachine<?> PYROLYSIS_OVEN = new UpgradeableBasicMultiMachine(GT4RRef.ID, "pyrolysis_oven").setTiers(LV).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityPyrolysisOven::new);
    public static MultiMachine THERMAL_BOILER = new MultiMachine(GT4RRef.ID, "thermal_boiler").setMap(THERMAL_BOILER_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityThermalBoiler::new);
    public static MultiMachine LARGE_STEAM_TURBINE = new MultiMachine(GT4RRef.ID, "large_steam_turbine").setMap(LARGE_STEAM_FUELS).setTiers(EV).addFlags(GUI, FLUID, ITEM, EU, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE);
    public static MultiMachine LARGE_GAS_TURBINE = new MultiMachine(GT4RRef.ID, "large_gas_turbine").setMap(LARGE_GAS_FUELS).setTiers(IV).addFlags(GUI, FLUID, ITEM, EU, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE);
    public static MultiMachine LARGE_HEAT_EXCHANGER = new MultiMachine(GT4RRef.ID, "large_heat_exchanger").setTiers(EV).addFlags(GUI, FLUID, EU).setTile(BlockEntityLargeHeatExchanger::new);
    public static MultiMachine FUSION_REACTOR = new MultiMachine(GT4RRef.ID, "fusion_control_computer").setMap(FUSION).setTiers(IV).addFlags(GUI, FLUID, EU).setTile(BlockEntityFusionReactor::new);

    public static HatchMachine HATCH_ITEM_I = new HatchMachine(GT4RRef.ID, "item_input_hatch", COVERINPUT, "item_input").baseTexture(Textures.BASE_HANDLER).addFlags(GUI, ITEM).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(GT4RRef.ID, "item_output_hatch", COVEROUTPUT, "item_output").baseTexture(Textures.BASE_HANDLER).addFlags(GUI, ITEM).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(GT4RRef.ID, "fluid_input_hatch", COVERINPUT, "fluid_input").baseTexture(Textures.BASE_HANDLER).addFlags(GUI, FLUID, CELL).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(GT4RRef.ID, "fluid_output_hatch", COVEROUTPUT, "fluid_output").baseTexture(Textures.BASE_HANDLER).addFlags(GUI, FLUID, CELL).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(GT4RRef.ID, "muffler_hatch", COVERMUFFLER, "muffler").baseTexture(Textures.BASE_HANDLER).addFlags(GUI, ITEM).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(GT4RRef.ID, "dynamo_hatch", COVER_DYNAMO_OLD, "dynamo").baseTexture(Textures.BASE_HANDLER).addFlags(EU).setTiers(EV, IV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_INJECTOR = new HatchMachine(GT4RRef.ID, "fusion_item_injector", COVER_FUSION_INPUT, "item_input").addFlags(GUI, ITEM).baseTexture(Textures.FUSION_IN).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_EXTRACTOR = new HatchMachine(GT4RRef.ID, "fusion_item_extractor", COVER_FUSION_OUTPUT, "item_output").addFlags(GUI, ITEM).baseTexture(Textures.FUSION_OUT).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_INJECTOR = new HatchMachine(GT4RRef.ID, "fusion_fluid_injector", COVER_FUSION_INPUT, "fluid_input").addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_IN).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_EXTRACTOR = new HatchMachine(GT4RRef.ID, "fusion_fluid_extractor", COVER_FUSION_OUTPUT, "fluid_output").addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_OUT).setTiers(LV).setVerticalFacingAllowed(false).allowFrontIO();
    public static HatchMachine FUSION_ENERGY_INJECTOR = new HatchMachine(GT4RRef.ID, "fusion_energy_injector", emptyFactory, "energy").addFlags(EU).baseTexture(Textures.FUSION_IN).setTiers(IV).setVerticalFacingAllowed(false).allowFrontIO();
    //public static HatchMachine FUSION_ENERGY_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_energy_extractor", COVER_DYNAMO_OLD).addFlags(ENERGY).baseTexture(Textures.FUSION_OUT).setTiers(UV).setAllowVerticalFacing(false).allowFrontIO();

    public static TankMachine QUANTUM_TANK = new TankMachine(GT4RRef.ID, "quantum_tank").addFlags(GUI, CELL).setTiers(MAX).setTile(BlockEntityQuantumTank::new).frontCovers().allowFrontIO();
    public static TankMachine DIGITAL_TANK = new TankMachine(GT4RRef.ID, "digital_tank").addFlags(GUI, CELL, ITEM).setTiers(LV).setTile(BlockEntityDigitalTank::new).frontCovers().allowFrontIO();
    public static StorageMachine QUANTUM_CHEST = new StorageMachine(GT4RRef.ID, "quantum_chest").addFlags(GUI, ITEM).setTiers(MAX).setTile(BlockEntityQuantumChest::new);
    public static StorageMachine DIGITAL_CHEST = new StorageMachine(GT4RRef.ID, "digital_chest").addFlags(GUI, ITEM).setTiers(LV).setTile(BlockEntityDigitalChest::new);

    public static DrumMachine BRONZE_DRUM = GTCoreBlocks.createDrum(Materials.Bronze, 16000);
    public static DrumMachine INVAR_DRUM = GTCoreBlocks.createDrum(Materials.Invar, 32000);
    public static DrumMachine STEEL_DRUM = GTCoreBlocks.createDrum(Materials.Steel, 48000);
    public static DrumMachine STAINLESS_STEEL_DRUM = GTCoreBlocks.createDrum(Materials.StainlessSteel, 64000).acidProof();
    public static DrumMachine TUNGSTEN_DRUM = GTCoreBlocks.createDrum(Materials.Tungsten, 256000);
    public static DrumMachine TUNGSTENSTEEL_DRUM = GTCoreBlocks.createDrum(Materials.TungstenSteel, 256000);
    public static DrumMachine NETHERITE_DRUM = GTCoreBlocks.createDrum(AntimatterMaterials.Netherite, 128000).acidProof();

    public static MultiblockTankMachine WOOD_TANK;
    public static MultiblockTankMachine[] STEEL_TANKS;
    public static MultiblockTankMachine[] INVAR_TANKS;
    public static MultiblockTankMachine[] STAINLESS_STEEL_TANKS;
    public static MultiblockTankMachine[] TITANIUM_TANKS;
    public static MultiblockTankMachine[] NETHERITE_TANKS;
    public static MultiblockTankMachine[] TUNGSTENSTEEL_TANKS;
    public static MultiblockTankMachine[] TUNGSTEN_TANKS;

    public static void initTanks() {
        WOOD_TANK = new MultiblockTankMachine(GT4RRef.ID, Wood, true, 432000, () -> GT4RBlocks.WOOD_WALL).maxHeat(350);
        STEEL_TANKS = createTankMachine(Materials.Steel, 3);
        INVAR_TANKS = createTankMachine(Materials.Invar, 2);
        STAINLESS_STEEL_TANKS = createTankMachine(Materials.StainlessSteel, 4);
        TITANIUM_TANKS = createTankMachine(Materials.Titanium, 8);
        NETHERITE_TANKS = createTankMachine(Netherite, 8);
        TUNGSTENSTEEL_TANKS = createTankMachine(Materials.TungstenSteel, 16);
        TUNGSTEN_TANKS = createTankMachine(Materials.Tungsten, 16);
    }

    public static GeneratorMachine STEAM_TURBINE = new GeneratorMachine(GT4RRef.ID, "steam_turbine").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setMap(STEAM_FUELS).addFlags(GUI, FLUID, CELL).efficiency(t -> 100);
    public static GeneratorMachine GAS_TURBINE = new GeneratorMachine(GT4RRef.ID, "gas_turbine").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setMap(GAS_FUELS).addFlags(GUI, FLUID, CELL).efficiency(t -> 100);
    public static BasicMachine HEAT_EXCHANGER = new BasicMachine(GT4RRef.ID, "heat_exchanger").baseTexture(Textures.BASE_HANDLER).setMap(HOT_FUELS).setTiers(LV).addFlags(GUI, FLUID).overlayTexture(Textures.LEFT_RIGHT_HANDLER).covers(emptyFactory).setTile(BlockEntityHeatExchanger::new);
    public static GeneratorMachine DIESEL_GENERATOR = new GeneratorMachine(GT4RRef.ID, "diesel_generator").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setMap(DIESEL_FUELS).addFlags(GUI, FLUID, CELL).efficiency(t -> 100);
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(GT4RRef.ID, "semifluid_generator").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setMap(SEMIFLUID_FUELS).addFlags(GUI, FLUID, CELL).efficiency(t -> 100);
    public static GeneratorMachine THERMAL_GENERATOR = new GeneratorMachine(GT4RRef.ID, "thermal_generator").baseTexture(Textures.BASE_HANDLER).setTiers(LV).setMap(THERMAL_FUELS).addFlags(GUI, FLUID, CELL).efficiency(t -> 80);
    public static GeneratorMachine WINDMILL = new GeneratorMachine(GT4RRef.ID, "windmill").baseTexture(Textures.BASE_HANDLER).setTiers(ULV);
    public static GeneratorMachine WATERMILL = new GeneratorMachine(GT4RRef.ID, "watermill").baseTexture(Textures.BASE_HANDLER).setTiers(ULV).setTile(BlockEntityWatermill::new).custom();

    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(GT4RRef.ID, "4x_battery_buffer").setTiers(Tier.getStandardWithIV()).noCovers().addFlags(GUI, EU, ITEM).setTile(BlockEntityBatteryBox::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).baseTexture(Textures.BATBOX_HANDLER).allowFrontIO().setVerticalFacingAllowed(true);
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(GT4RRef.ID, "8x_battery_buffer").setTiers(Tier.getStandardWithIV()).noCovers().addFlags(GUI, EU, ITEM).setTile(BlockEntityBatteryBox::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).baseTexture(Textures.BATBOX_HANDLER).allowFrontIO().setVerticalFacingAllowed(true);
    public static BasicMachine TRANSFORMER = new BasicMachine(GT4RRef.ID, "transformer").setTiers(Tier.getStandardWithIV()).addFlags(EU).baseTexture(Textures.BATBOX_HANDLER).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((m, p, s) -> new BlockEntityTransformer<>(m, p, s, 1)).noCovers().setVerticalFacingAllowed(true).allowFrontIO().addTooltipInfo((machine, stack, world, tooltip, flag) -> {
        tooltip.remove(tooltip.size() - 1);
        tooltip.remove(tooltip.size() - 1);
        Tier upper = Tier.getTier(machine.getTier().getVoltage() * 4);
        tooltip.add(Utils.translatable("machine.transformer.voltage_info", Utils.literal(upper.getId().toUpperCase()), Utils.literal(machine.getTier().getId().toUpperCase())));
        tooltip.add(Utils.translatable("machine.voltage.in").append(": ").append(Utils.literal(upper.getVoltage() + " (" + upper.getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("machine.voltage.out").append(": ").append(Utils.literal(machine.getTier().getVoltage() + " (" + machine.getTier().getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("generic.amp").append(": ").append(Utils.literal(String.valueOf(4)).withStyle(ChatFormatting.YELLOW)));
        tooltip.add(Utils.translatable("machine.power.capacity").append(": ").append(Utils.literal(String.valueOf(512L + machine.getTier().getVoltage() * 8L))).withStyle(ChatFormatting.BLUE));
    });
    public static BasicMachine SUPERCONDENSATOR = new BasicMachine(GT4RRef.ID, "supercondensator").addFlags(EU).setTile((m, p, s) -> new BlockEntitySupercondensator(m, p, s, 1)).setTiers(LUV).noCovers().setVerticalFacingAllowed(true).allowFrontIO();
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(GT4RRef.ID, "transformer_digital").addFlags(GUI, EU).setTiers(EV, IV).setTile(BlockEntityDigitalTransformer::new).noCovers().allowFrontIO();

    public static void init() {
        HEAT_EXCHANGER.removeFlags(EU);
        DUSTBIN.removeFlags(EU);
    }

    private static MultiblockTankMachine[] createTankMachine(Material material, int multiplier){
        Supplier<Block> casing = () -> AntimatterAPI.get(BlockColoredWall.class, material.getId() + "_wall", GT4RRef.ID);
        MultiblockTankMachine[] multiblockTankMachines = {
                (MultiblockTankMachine) new MultiblockTankMachine(GT4RRef.ID, material, true, 432 * multiplier * 1000, casing).gasProof().baseTexture(new Texture(GT4RRef.ID, "block/casing/wall/metal")),
                (MultiblockTankMachine) new MultiblockTankMachine(GT4RRef.ID, material, false, 2000 * multiplier * 1000, casing).gasProof().baseTexture(new Texture(GT4RRef.ID, "block/casing/wall/metal"))
        };
        if (material == Materials.StainlessSteel || material == Netherite){
            multiblockTankMachines[0].acidProof();
            multiblockTankMachines[1].acidProof();
        }
        return multiblockTankMachines;
    }
}
