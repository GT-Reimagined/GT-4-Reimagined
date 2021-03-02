package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.client.AntimatterTextureStitcher;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockCoil;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.texture.Texture;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockFusionCasing;
import trinsdar.gt4r.block.BlockTurbineCasing;
import trinsdar.gt4r.cover.CoverConveyor;
import trinsdar.gt4r.cover.CoverPlate;
import trinsdar.gt4r.cover.CoverPump;
import trinsdar.gt4r.items.ItemIntCircuit;
import trinsdar.gt4r.tree.BlockRubberLeaves;
import trinsdar.gt4r.tree.BlockRubberLog;
import trinsdar.gt4r.tree.BlockRubberSapling;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static muramasa.antimatter.Data.ROTOR;
import static trinsdar.gt4r.data.Materials.*;

public class GregTechData {

    private static final boolean HC = AntimatterConfig.GAMEPLAY.HARDCORE_CABLES;

    static {
        {
            ImmutableMap.Builder<Integer, AntimatterIngredient> builder = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                builder.put(i, AntimatterIngredient.of(new ItemIntCircuit(Ref.ID, "int_circuit_"+i,i).tip("ID: " + i),1).setNonConsume());
            }
            INT_CIRCUITS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WroughtIron);
            builder.put(Tier.LV, Steel);
            builder.put(Tier.MV, Aluminium);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            TIER_MATERIALS = builder.build();
        }
    }

    public static void buildTierMaps() {
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WIRE_LEAD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, WIRE_GOLD.getBlockItem(PipeSize.TINY));
            builder.put(Tier.IV, WIRE_PLATINUM.getBlockItem(PipeSize.TINY));
            TIER_WIRES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, CABLE_LEAD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, CABLE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, CABLE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, CABLE_SILVER.getBlockItem(PipeSize.TINY));
            builder.put(Tier.EV, CABLE_ALUMINIUM.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.IV, CABLE_PLATINUM.getBlockItem(PipeSize.TINY));
            TIER_CABLES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, ROTOR.get(Bronze));
            builder.put(Tier.LV, ROTOR.get(Bronze));
            builder.put(Tier.MV, ROTOR.get(Steel));
            builder.put(Tier.HV, ROTOR.get(StainlessSteel));
            builder.put(Tier.EV, ROTOR.get(Titanium));
            builder.put(Tier.IV, ROTOR.get(TungstenSteel));
            TIER_ROTORS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.LV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.MV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.HV, AntimatterAPI.get(FluidPipe.class, "fluid_"+StainlessSteel.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.EV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Titanium.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.IV, AntimatterAPI.get(FluidPipe.class, "fluid_"+TungstenSteel.getId()).getBlockItem(PipeSize.NORMAL));
            TIER_PIPES = builder.build();
        }
    }

    public static void init() {

    }

    public static final Cover COVER_PLATE = new CoverPlate();
    public static final Cover COVER_CONVEYOR = new CoverConveyor();
    public static final Cover COVER_PUMP = new CoverPump();

    public static ItemBasic<?> StickyResin = new ItemBasic<>(Ref.ID, "sticky_resin");
    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(Ref.ID, "computer_monitor").tip("Can be placed on machines as a cover");

    public static ItemFluidCell CellTin = new ItemFluidCell(Ref.ID,Tin, 1000);

    public static ItemBasic<?> ItemFilter = new ItemBasic<>(Ref.ID, "item_filter");
    public static ItemBasic<?> DiamondSawBlade = new ItemBasic<>(Ref.ID, "diamond_saw_blade");
    public static ItemBasic<?> DiamondGrindHead = new ItemBasic<>(Ref.ID, "diamond_grind_head");
    public static ItemBasic<?> TungstenGrindHead = new ItemBasic<>(Ref.ID, "tungsten_grind_head");
    public static ItemBasic<?> IridiumAlloyIngot = new ItemBasic<>(Ref.ID, "iridium_alloy_ingot").tip("Used to make Iridium Plates");
    public static ItemBasic<?> IridiumReinforcedPlate = new ItemBasic<>(Ref.ID, "iridium_reinforced_plate").tip("GT2s Most Expensive Component");
    public static ItemBasic<?> IridiumNeutronReflector = new ItemBasic<>(Ref.ID, "iridium_neutron_reflector").tip("Indestructible");

    public static ItemBasic<?> NandChip = new ItemBasic<>(Ref.ID, "nand_chip").tip("A very simple circuit");
    public static ItemBasic<?> AdvCircuitParts = new ItemBasic<>(Ref.ID, "adv_circuit_parts").tip("Used for making Advanced Circuits");
    public static ItemBasic<?> EngravedCrystalChip = new ItemBasic<>(Ref.ID, "engraved_crystal_chip").tip("Needed for Circuits");
    public static ItemBasic<?> EngravedLapotronChip = new ItemBasic<>(Ref.ID, "engraved_lapotron_chip").tip("Needed for Circuits");
    public static ItemBasic<?> CircuitBoardEmpty = new ItemBasic<>(Ref.ID, "circuit_board_empty").tip("A board Part");
    public static ItemBasic<?> CircuitBoardBasic = new ItemBasic<>(Ref.ID, "circuit_board_basic").tip("A basic Board");
    public static ItemBasic<?> CircuitBoardAdv = new ItemBasic<>(Ref.ID, "circuit_board_adv").tip("An advanced Board");
    public static ItemBasic<?> CircuitBoardProcessorEmpty = new ItemBasic<>(Ref.ID, "circuit_board_processor_empty").tip("A Processor Board Part");
    public static ItemBasic<?> CircuitBoardProcessor = new ItemBasic<>(Ref.ID, "circuit_board_processor").tip("A Processor Board");
    public static ItemBasic<?> CircuitBasic = new ItemBasic<>(Ref.ID, "circuit_basic").tip("A basic Circuit");
    public static ItemBasic<?> CircuitGood = new ItemBasic<>(Ref.ID, "circuit_good").tip("A good Circuit");
    public static ItemBasic<?> CircuitAdv = new ItemBasic<>(Ref.ID, "circuit_adv").tip("An advanced Circuit");
    public static ItemBasic<?> CircuitDataStorage = new ItemBasic<>(Ref.ID, "circuit_data_storage").tip("A Data Storage Chip");
    public static ItemBasic<?> CircuitDataControl = new ItemBasic<>(Ref.ID, "circuit_data_control").tip("A Data Control Processor");
    public static ItemBasic<?> CircuitEnergyFlow = new ItemBasic<>(Ref.ID, "circuit_energy_flow").tip("A High Voltage Processor");
    public static ItemBasic<?> CircuitDataOrb = new ItemBasic<>(Ref.ID, "circuit_data_orb").tip("A High Capacity Data Storage");
    public static ItemBasic<?> DataStick = new ItemBasic<>(Ref.ID, "data_stick").tip("A Low Capacity Data Storage");

    public static ItemBasic<?> BatteryHullSmall = new ItemBasic<>(Ref.ID, "battery_hull_small").tip("An empty LV Battery Hull");
    public static ItemBasic<?> BatteryHullMedium = new ItemBasic<>(Ref.ID, "battery_hull_medium").tip("An empty MV Battery Hull");
    public static ItemBasic<?> BatteryHullLarge = new ItemBasic<>(Ref.ID, "battery_hull_large").tip("An empty HV Battery Hull");
    public static ItemBasic<?> BatterySmallAcid = new ItemBattery(Ref.ID, "battery_small_acid", Tier.LV, 50000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallMercury = new ItemBattery(Ref.ID, "battery_small_mercury", Tier.LV, 100000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallCadmium = new ItemBattery(Ref.ID, "battery_small_cadmium", Tier.LV,75000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallLithium = new ItemBattery(Ref.ID, "battery_small_lithium", Tier.LV, 100000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallSodium = new ItemBattery(Ref.ID, "battery_small_sodium", Tier.LV, 50000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumAcid = new ItemBattery(Ref.ID, "battery_medium_acid", Tier.MV, 200000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumMercury = new ItemBattery(Ref.ID, "battery_medium_mercury", Tier.MV, 400000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumCadmium = new ItemBattery(Ref.ID, "battery_medium_cadmium", Tier.MV, 300000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumLithium = new ItemBattery(Ref.ID, "battery_medium_lithium", Tier.MV, 400000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumSodium = new ItemBattery(Ref.ID, "battery_medium_sodium", Tier.MV,200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeAcid = new ItemBattery(Ref.ID, "battery_large_acid", Tier.HV, 800000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeMercury = new ItemBattery(Ref.ID, "battery_large_mercury", Tier.HV, 1600000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeCadmium = new ItemBattery(Ref.ID, "battery_large_cadmium", Tier.HV, 1200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeLithium = new ItemBattery(Ref.ID, "battery_large_lithium", Tier.HV, 1600000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeSodium = new ItemBattery(Ref.ID, "battery_large_sodium", Tier.HV, 800000, true).tip("Reusable");
    public static ItemBasic<?> BatteryEnergyOrb = new ItemBasic<>(Ref.ID, "battery_energy_orb");
    public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(Ref.ID, "battery_energy_orb_cluster");

    public static ItemBasic<?> EmptyShape = new ItemBasic<>(Ref.ID, "empty_shape_plate").tip("Raw plate to make Molds and Extruder Shapes");
    public static ItemBasic<?> MoldPlate = new ItemBasic<>(Ref.ID, "mold_plate").tip("Mold for making Plates");
    public static ItemBasic<?> MoldGear = new ItemBasic<>(Ref.ID, "mold_gear").tip("Mold for making Gears");
    public static ItemBasic<?> MoldCoinage = new ItemBasic<>(Ref.ID, "mold_coinage").tip("Secure Mold for making Coins (Don't lose it!)");
    public static ItemBasic<?> MoldBottle = new ItemBasic<>(Ref.ID, "mold_bottle").tip("Mold for making Bottles");
    public static ItemBasic<?> MoldIngot = new ItemBasic<>(Ref.ID, "mold_ingot").tip("Mold for making Ingots");
    public static ItemBasic<?> MoldBlock = new ItemBasic<>(Ref.ID, "mold_block").tip("Mold for making Blocks");
    public static ItemBasic<?> MoldNugget = new ItemBasic<>(Ref.ID, "mold_nugget").tip("Mold for making Nuggets");
    public static ItemBasic<?> ShapePlate = new ItemBasic<>(Ref.ID, "shape_plate").tip("Shape for making Plates");
    public static ItemBasic<?> ShapeRod = new ItemBasic<>(Ref.ID, "shape_rod").tip("Shape for making Rods");
    public static ItemBasic<?> ShapeCell = new ItemBasic<>(Ref.ID, "shape_cell").tip("Shape for making Cells");
    public static ItemBasic<?> ShapeIngot = new ItemBasic<>(Ref.ID, "shape_ingot").tip("Shape for making Ingots");
    public static ItemBasic<?> ShapePipeTiny = new ItemBasic<>(Ref.ID, "shape_pipe_tiny").tip("Shape for making Tiny Pipes");
    public static ItemBasic<?> ShapePipeSmall = new ItemBasic<>(Ref.ID, "shape_pipe_small").tip("Shape for making Small Pipes");
    public static ItemBasic<?> ShapePipeNormal = new ItemBasic<>(Ref.ID, "shape_pipe_normal").tip("Shape for making Normal Pipes");
    public static ItemBasic<?> ShapePipeLarge = new ItemBasic<>(Ref.ID, "shape_pipe_large").tip("Shape for making Large Pipes");
    public static ItemBasic<?> ShapePipeHuge = new ItemBasic<>(Ref.ID, "shape_pipe_huge").tip("Shape for making Huge Pipes");
    public static ItemBasic<?> ShapeBlock = new ItemBasic<>(Ref.ID, "shape_block").tip("Shape for making Blocks");
    public static ItemBasic<?> ShapeHeadSword = new ItemBasic<>(Ref.ID, "shape_head_sword").tip("Shape for making Sword Blades");
    public static ItemBasic<?> ShapeHeadPickaxe = new ItemBasic<>(Ref.ID, "shape_head_pickaxe").tip("Shape for making Pickaxe Heads");
    public static ItemBasic<?> ShapeHeadShovel = new ItemBasic<>(Ref.ID, "shape_head_shovel").tip("Shape for making Shovel Heads");
    public static ItemBasic<?> ShapeHeadAxe = new ItemBasic<>(Ref.ID, "shape_head_axe").tip("Shape for making Axe Heads");
    public static ItemBasic<?> ShapeHeadHoe = new ItemBasic<>(Ref.ID, "shape_head_hoe").tip("Shape for making Hoe Heads");
    public static ItemBasic<?> ShapeHeadHammer = new ItemBasic<>(Ref.ID, "shape_head_hammer").tip("Shape for making Hammer Heads");
    public static ItemBasic<?> ShapeHeadFile = new ItemBasic<>(Ref.ID, "shape_head_file").tip("Shape for making File Heads");
    public static ItemBasic<?> ShapeHeadSaw = new ItemBasic<>(Ref.ID, "shape_head_saw").tip("Shape for making Saw Heads");
    public static ItemBasic<?> ShapeGear = new ItemBasic<>(Ref.ID, "shape_gear").tip("Shape for making Gears");
    public static ItemBasic<?> ShapeBottle = new ItemBasic<>(Ref.ID, "shape_bottle").tip("Shape for making Bottles"); //TODO needed?

    //TODO
    //public static BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling();
    //public static BlockRubberLog RUBBER_LOG = new BlockRubberLog();
    //public static BlockLeavesBase RUBBER_LEAVES = new BlockLeavesBase("rubber_leaves", RUBBER_SAPLING);

    //STONE should be the only non-removable StoneType. It serves as the foundation. It is also used natively by BlockRock
    //TODO move vanilla stone types (and (vanilla) materials) to Antimatter
    public static StoneType STONE = new StoneType(Ref.ID, "stone", Materials.Stone, new Texture("minecraft", "block/stone"), SoundType.STONE, false).setState(Blocks.STONE);

    public static StoneType GRANITE = new StoneType(Ref.ID, "granite", Granite, new Texture("minecraft", "block/granite"), SoundType.STONE, AntimatterConfig.WORLD.VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.GRANITE);
    public static StoneType DIORITE = new StoneType(Ref.ID, "diorite", Diorite, new Texture("minecraft", "block/diorite"), SoundType.STONE, AntimatterConfig.WORLD.VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.DIORITE);
    public static StoneType ANDESITE = new StoneType(Ref.ID, "andesite", Andesite, new Texture("minecraft", "block/andesite"), SoundType.STONE, AntimatterConfig.WORLD.VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.ANDESITE);

    public static StoneType GRAVEL = new StoneType(Ref.ID, "gravel", Gravel, new Texture("minecraft", "block/gravel"), SoundType.GROUND, false).setState(Blocks.GRAVEL);
    public static StoneType SAND = new StoneType(Ref.ID, "sand", Sand, new Texture("minecraft", "block/sand"), SoundType.SAND, false).setState(Blocks.SAND);
    public static StoneType SAND_RED = new StoneType(Ref.ID, "sand_red", RedSand, new Texture("minecraft", "block/red_sand"), SoundType.SAND, false).setState(Blocks.RED_SAND);
    public static StoneType SANDSTONE = new StoneType(Ref.ID, "sandstone", Sandstone, new Texture("minecraft", "block/sandstone"), SoundType.STONE, false).setState(Blocks.SANDSTONE);

    public static StoneType NETHERRACK = new StoneType(Ref.ID, "netherrack", Materials.Netherrack, new Texture("minecraft", "block/netherrack"), SoundType.STONE, false).setState(Blocks.NETHERRACK);
    public static StoneType ENDSTONE = new StoneType(Ref.ID, "endstone", Materials.Endstone, new Texture("minecraft", "block/end_stone"), SoundType.STONE, false).setState(Blocks.END_STONE);

    public static StoneType GRANITE_RED = new StoneType(Ref.ID, "granite_red", Materials.RedGranite, new Texture(Ref.ID, "block/stone/granite_red"), SoundType.STONE, true);
    public static StoneType GRANITE_BLACK = new StoneType(Ref.ID, "granite_black", Materials.BlackGranite, new Texture(Ref.ID, "block/stone/granite_black"), SoundType.STONE, true);
    public static StoneType MARBLE = new StoneType(Ref.ID, "marble", Materials.Marble, new Texture(Ref.ID, "block/stone/marble"), SoundType.STONE, true);
    public static StoneType BASALT = new StoneType(Ref.ID, "basalt", Materials.Basalt, new Texture(Ref.ID, "block/stone/basalt"), SoundType.STONE, true);

    public static StoneType KOMATIITE = new StoneType(Ref.ID, "komatiite", Materials.Komatiite, new Texture(Ref.ID, "block/stone/komatiite"), SoundType.STONE, true);
    public static StoneType LIMESTONE = new StoneType(Ref.ID, "limestone", Limestone, new Texture(Ref.ID, "block/stone/limestone"), SoundType.STONE, true);
    public static StoneType GREEN_SCHIST = new StoneType(Ref.ID, "green_schist", GreenSchist, new Texture(Ref.ID, "block/stone/green_schist"), SoundType.STONE, true);
    public static StoneType BLUE_SCHIST = new StoneType(Ref.ID, "blue_schist", BlueSchist, new Texture(Ref.ID, "block/stone/blue_schist"), SoundType.STONE, true);
    public static StoneType KIMBERLITE = new StoneType(Ref.ID, "kimberlite", Kimberlite, new Texture(Ref.ID, "block/stone/kimberlite"), SoundType.STONE, true);
    public static StoneType QUARTZITE = new StoneType(Ref.ID, "quartzite", Quartzite, new Texture(Ref.ID, "block/stone/quartzite"), SoundType.STONE, true);


    public static final BlockCasing CASING_ULV = new BlockCasing(Ref.ID, "casing_ulv");
    public static final BlockCasing CASING_LV = new BlockCasing(Ref.ID, "casing_lv");
    public static final BlockCasing CASING_MV = new BlockCasing(Ref.ID, "casing_mv");
    public static final BlockCasing CASING_HV = new BlockCasing(Ref.ID, "casing_hv");
    public static final BlockCasing CASING_EV = new BlockCasing(Ref.ID, "casing_ev");
    public static final BlockCasing CASING_IV = new BlockCasing(Ref.ID, "casing_iv");
    public static final BlockCasing CASING_LUV = new BlockCasing(Ref.ID, "casing_luv");
    public static final BlockCasing CASING_ZPM = new BlockCasing(Ref.ID, "casing_zpm");
    public static final BlockCasing CASING_UV = new BlockCasing(Ref.ID, "casing_uv");
    public static final BlockCasing CASING_MAX = new BlockCasing(Ref.ID, "casing_max");

    public static final BlockCasing CASING_FIRE_BRICK = new BlockCasing(Ref.ID, "casing_fire_brick");
    public static final BlockCasing CASING_BRONZE = new BlockCasing(Ref.ID, "casing_bronze");
    public static final BlockCasing CASING_BRICKED_BRONZE = new BlockCasing(Ref.ID, "casing_bricked_bronze");
    public static final BlockCasing CASING_BRONZE_PLATED_BRICK = new BlockCasing(Ref.ID, "casing_bronze_plated_brick");
    public static final BlockCasing CASING_STEEL = new BlockCasing(Ref.ID, "casing_steel");
    public static final BlockCasing CASING_BRICKED_STEEL = new BlockCasing(Ref.ID, "casing_bricked_steel");
    public static final BlockCasing CASING_SOLID_STEEL = new BlockCasing(Ref.ID, "casing_solid_steel");
    public static final BlockCasing CASING_STAINLESS_STEEL = new BlockCasing(Ref.ID, "casing_stainless_steel");
    public static final BlockCasing CASING_TITANIUM = new BlockCasing(Ref.ID, "casing_titanium");
    public static final BlockCasing CASING_TUNGSTENSTEEL = new BlockCasing(Ref.ID, "casing_tungstensteel");
    public static final BlockCasing CASING_HEAT_PROOF = new BlockCasing(Ref.ID, "casing_heat_proof");
    public static final BlockCasing CASING_FROST_PROOF = new BlockCasing(Ref.ID, "casing_frost_proof");
    public static final BlockCasing CASING_RADIATION_PROOF = new BlockCasing(Ref.ID, "casing_radiation_proof");
    public static final BlockCasing CASING_FIREBOX_BRONZE = new BlockCasing(Ref.ID, "casing_firebox_bronze");
    public static final BlockCasing CASING_FIREBOX_STEEL = new BlockCasing(Ref.ID, "casing_firebox_steel");
    public static final BlockCasing CASING_FIREBOX_TITANIUM = new BlockCasing(Ref.ID, "casing_firebox_titanium");
    public static final BlockCasing CASING_FIREBOX_TUNGSTENSTEEL = new BlockCasing(Ref.ID, "casing_firebox_tungstensteel");
    public static final BlockCasing CASING_GEARBOX_BRONZE = new BlockCasing(Ref.ID, "casing_gearbox_bronze");
    public static final BlockCasing CASING_GEARBOX_STEEL = new BlockCasing(Ref.ID, "casing_gearbox_steel");
    public static final BlockCasing CASING_GEARBOX_TITANIUM = new BlockCasing(Ref.ID, "casing_gearbox_titanium");
    public static final BlockCasing CASING_GEARBOX_TUNGSTENSTEEL = new BlockCasing(Ref.ID, "casing_gearbox_tungstensteel");
    public static final BlockCasing CASING_PIPE_BRONZE = new BlockCasing(Ref.ID, "casing_pipe_bronze");
    public static final BlockCasing CASING_PIPE_STEEL = new BlockCasing(Ref.ID, "casing_pipe_steel");
    public static final BlockCasing CASING_PIPE_TITANIUM = new BlockCasing(Ref.ID, "casing_pipe_titanium");
    public static final BlockCasing CASING_PIPE_TUNGSTENSTEEL = new BlockCasing(Ref.ID, "casing_pipe_tungstensteel");
    public static final BlockCasing CASING_ENGINE_INTAKE = new BlockCasing(Ref.ID, "casing_engine_intake");

    public static final BlockFusionCasing CASING_FUSION_1 = new BlockFusionCasing(Ref.ID, "fusion_1");
    public static final BlockFusionCasing CASING_FUSION_2 = new BlockFusionCasing(Ref.ID, "fusion_2");
    public static final BlockFusionCasing CASING_FUSION_3 = new BlockFusionCasing(Ref.ID, "fusion_3");

    public static final BlockTurbineCasing CASING_TURBINE_1 = new BlockTurbineCasing(Ref.ID, "casing_turbine_1");
    public static final BlockTurbineCasing CASING_TURBINE_2 = new BlockTurbineCasing(Ref.ID, "casing_turbine_2");
    public static final BlockTurbineCasing CASING_TURBINE_3 = new BlockTurbineCasing(Ref.ID, "casing_turbine_3");
    public static final BlockTurbineCasing CASING_TURBINE_4 = new BlockTurbineCasing(Ref.ID, "casing_turbine_4");

    public static final BlockCoil COIL_CUPRONICKEL = new BlockCoil(Ref.ID, "coil_cupronickel", 113); //1808
    public static final BlockCoil COIL_KANTHAL = new BlockCoil(Ref.ID, "coil_kanthal", 169); //2704
    public static final BlockCoil COIL_NICHROME = new BlockCoil(Ref.ID, "coil_nichrome", 225); //3600
    public static final BlockCoil COIL_TUNGSTENSTEEL = new BlockCoil(Ref.ID, "coil_tungstensteel", 282); //4512
    public static final BlockCoil COIL_HSSG = new BlockCoil(Ref.ID, "coil_hssg", 338); //5408
    public static final BlockCoil COIL_NAQUADAH = new BlockCoil(Ref.ID, "coil_naquadah", 450); //7200
    public static final BlockCoil COIL_NAQUADAH_ALLOY = new BlockCoil(Ref.ID, "coil_naquadah_alloy", 563); //9008
    public static final BlockCoil COIL_FUSION = new BlockCoil(Ref.ID, "coil_fusion", 563); //9008
    public static final BlockCoil COIL_SUPERCONDUCTOR = new BlockCoil(Ref.ID, "coil_superconductor", 563); //9008

    public static final Cable<?> CABLE_RED_ALLOY = new Cable<>(Ref.ID, RedAlloy, 0, Tier.ULV).amps(1);
    public static final Cable<?> CABLE_LEAD = new Cable<>(Ref.ID, Lead, 2, Tier.LV).amps(2);
    public static final Cable<?> CABLE_TIN = new Cable<>(Ref.ID, Tin, 1, Tier.LV).amps(1);
    public static final Cable<?> CABLE_ZINC = new Cable<>(Ref.ID, Zinc, 1, Tier.LV).amps(1);
    public static final Cable<?> CABLE_SOLDERING_ALLOY = new Cable<>(Ref.ID, SolderingAlloy, 1, Tier.LV).amps(1);
    public static final Cable<?> CABLE_IRON = new Cable<>(Ref.ID, Iron, HC ? 3 : 4, Tier.MV).amps(2); //MV
    public static final Cable<?> CABLE_NICKEL = new Cable<>(Ref.ID, Nickel, HC ? 3 : 5, Tier.MV).amps(3);
    public static final Cable<?> CABLE_CUPRONICKEL = new Cable<>(Ref.ID, Cupronickel, HC ? 3 : 4, Tier.MV).amps(2);
    public static final Cable<?> CABLE_COPPER = new Cable<>(Ref.ID, Copper, HC ? 2 : 3, Tier.MV).amps(1);
    public static final Cable<?> CABLE_KANTHAL = new Cable<>(Ref.ID, Kanthal, HC ? 3 : 8, Tier.HV).amps(4); //HV
    public static final Cable<?> CABLE_GOLD = new Cable<>(Ref.ID, Gold, HC ? 2 : 6, Tier.HV).amps(3);
    public static final Cable<?> CABLE_ELECTRUM = new Cable<>(Ref.ID, Electrum, HC ? 2 : 5, Tier.HV).amps(2);
    public static final Cable<?> CABLE_SILVER = new Cable<>(Ref.ID, Silver, HC ? 1 : 4, Tier.HV).amps(1);
    public static final Cable<?> CABLE_NICHROME = new Cable<>(Ref.ID, Nichrome, HC ? 4 : 32, Tier.EV).amps(3); //EV
    public static final Cable<?> CABLE_STEEL = new Cable<>(Ref.ID, Steel, HC ? 2 : 16, Tier.EV).amps(2);
    public static final Cable<?> CABLE_TITANIUM = new Cable<>(Ref.ID, Titanium, HC ? 2 : 12, Tier.EV).amps(4);
    public static final Cable<?> CABLE_ALUMINIUM = new Cable<>(Ref.ID, Aluminium, HC ? 1 : 8, Tier.EV).amps(1);
    public static final Cable<?> CABLE_OSMIUM = new Cable<>(Ref.ID, Osmium, HC ? 2 : 32, Tier.IV).amps(4);
    public static final Cable<?> CABLE_PLATINUM = new Cable<>(Ref.ID, Platinum, HC ? 1 : 16, Tier.IV).amps(2);
    public static final Cable<?> CABLE_TUNGSTEN_STEEL = new Cable<>(Ref.ID, TungstenSteel, HC ? 1 : 14, Tier.IV).amps(3);
    public static final Cable<?> CABLE_TUNGSTEN = new Cable<>(Ref.ID, Tungsten, HC ? 2 : 12, Tier.IV).amps(1);
    public static final Cable<?> CABLE_SUPERCONDUCTOR = new Cable<>(Ref.ID, Superconductor, 0, Tier.MAX).amps(4); //MAX

    public static final Wire<?> WIRE_RED_ALLOY = new Wire<>(Ref.ID, RedAlloy, 1, Tier.ULV).amps(1);
    public static final Wire<?> WIRE_LEAD = new Wire<>(Ref.ID, Lead, 4, Tier.LV).amps(2);
    public static final Wire<?> WIRE_TIN = new Wire<>(Ref.ID, Tin, 2, Tier.LV).amps(1);
    public static final Wire<?> WIRE_ZINC = new Wire<>(Ref.ID, Zinc, 2, Tier.LV).amps(1);
    public static final Wire<?> WIRE_SOLDERING_ALLOY = new Wire<>(Ref.ID, SolderingAlloy, 2, Tier.LV).amps(1);
    public static final Wire<?> WIRE_IRON = new Wire<>(Ref.ID, Iron, HC ? 6 : 8, Tier.MV).amps(2); //MV
    public static final Wire<?> WIRE_NICKEL = new Wire<>(Ref.ID, Nickel, HC ? 6 : 10, Tier.MV).amps(3);
    public static final Wire<?> WIRE_CUPRONICKEL = new Wire<>(Ref.ID, Cupronickel, HC ? 6 : 8, Tier.MV).amps(2);
    public static final Wire<?> WIRE_COPPER = new Wire<>(Ref.ID, Copper, HC ? 4 : 6, Tier.MV).amps(1);
    public static final Wire<?> WIRE_KANTHAL = new Wire<>(Ref.ID, Kanthal, HC ? 6 : 16, Tier.HV).amps(4); //HV
    public static final Wire<?> WIRE_GOLD = new Wire<>(Ref.ID, Gold, HC ? 4 : 12, Tier.HV).amps(3);
    public static final Wire<?> WIRE_ELECTRUM = new Wire<>(Ref.ID, Electrum, HC ? 4 : 10, Tier.HV).amps(2);
    public static final Wire<?> WIRE_SILVER = new Wire<>(Ref.ID, Silver, HC ? 2 : 8, Tier.HV).amps(1);
    public static final Wire<?> WIRE_NICHROME = new Wire<>(Ref.ID, Nichrome, HC ? 8 : 64, Tier.EV).amps(3); //EV
    public static final Wire<?> WIRE_STEEL = new Wire<>(Ref.ID, Steel, HC ? 4 : 32, Tier.EV).amps(2);
    public static final Wire<?> WIRE_TITANIUM = new Wire<>(Ref.ID, Titanium, HC ? 4 : 24, Tier.EV).amps(4);
    public static final Wire<?> WIRE_ALUMINIUM = new Wire<>(Ref.ID, Aluminium, HC ? 2 : 16, Tier.EV).amps(1);
    public static final Wire<?> WIRE_OSMIUM = new Wire<>(Ref.ID, Osmium, HC ? 4 : 64, Tier.IV).amps(4);
    public static final Wire<?> WIRE_PLATINUM = new Wire<>(Ref.ID, Platinum, HC ? 2 : 32, Tier.IV).amps(2);
    public static final Wire<?> WIRE_TUNGSTEN_STEEL = new Wire<>(Ref.ID, TungstenSteel, HC ? 2 : 28, Tier.IV).amps(3);
    public static final Wire<?> WIRE_TUNGSTEN = new Wire<>(Ref.ID, Tungsten, HC ? 2 : 12, Tier.IV).amps(1);
    public static final Wire<?> WIRE_SUPERCONDUCTOR = new Wire<>(Ref.ID, Superconductor, 1, Tier.MAX).amps(4); //MAX

    public static final FluidPipe<?> FLUID_PIPE_BRICK = new FluidPipe<>(Ref.ID, Brick, 1000, false).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(10, 10, 30, 60, 60, 60).pressures(400, 400, 400, 400, 400, 400);
    public static final FluidPipe<?> FLUID_PIPE_COPPER = new FluidPipe<>(Ref.ID, Copper, 700, true).caps(10).pressures(600);
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = new FluidPipe<>(Ref.ID, StainlessSteel, 1300, true).caps(60).pressures(1000);
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = new FluidPipe<>(Ref.ID, Titanium, 1668, true).caps(80).pressures(2500);
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = new FluidPipe<>(Ref.ID, TungstenSteel, 3422, true).caps(100).pressures(5000);
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = new FluidPipe<>(Ref.ID, Plastic, 160, true).caps(60).pressures(2000);
    public static final FluidPipe<?> FLUID_PIPE_HP = new FluidPipe<>(Ref.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(4800, 4800, 4800, 7200, 9600, 9600).pressures(10000);
    public static final FluidPipe<?> FLUID_PIPE_PLASMA = new FluidPipe<>(Ref.ID, PlasmaContainment, 100000, true).sizes(PipeSize.NORMAL).caps(240, 240, 240, 240, 240, 240).pressures(100000);

    public static final ItemPipe<?> ITEM_PIPE_WOOD = new ItemPipe<>(Ref.ID, Wood).sizes(PipeSize.SMALL).caps(0, 0, 1, 0, 0, 0);
    public static final ItemPipe<?> ITEM_PIPE_WROUGHT_IRON = new ItemPipe<>(Ref.ID, WroughtIron).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(0, 0, 2, 3, 4, 0);
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = new ItemPipe<>(Ref.ID, Platinum).caps(5).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE);
    public static final ItemPipe<?> ITEM_PIPE_HC = new ItemPipe<>(Ref.ID, HighCapacity).caps(10);
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = new ItemPipe<>(Ref.ID, Osmiridium).caps(20);

    // Rubber Tree
    public static final BlockRubberLeaves RUBBER_LEAVES = new BlockRubberLeaves(Ref.ID, "rubber_leaves");
    public static final BlockRubberLog RUBBER_LOG = new BlockRubberLog(Ref.ID, "rubber_log");
    public static final BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling(Ref.ID, "rubber_sapling");

    public static final ImmutableMap<Integer, AntimatterIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static ImmutableMap<Tier, Item> TIER_WIRES;
    public static ImmutableMap<Tier, Item> TIER_CABLES;
    public static ImmutableMap<Tier, ItemBasic<?>> TIER_CIRCUITS;

    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Item> TIER_PIPES;
}
