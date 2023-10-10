package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import earth.terrarium.botarium.common.registry.fluid.FluidProperties;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.item.ItemMultiTextureBattery;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.cover.CoverConveyor;
import trinsdar.gt4r.cover.CoverCrafting;
import trinsdar.gt4r.cover.CoverDrain;
import trinsdar.gt4r.cover.CoverDynamoOld;
import trinsdar.gt4r.cover.CoverFusionInput;
import trinsdar.gt4r.cover.CoverFusionOutput;
import trinsdar.gt4r.cover.CoverPump;
import trinsdar.gt4r.cover.CoverSteamVent;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;
import trinsdar.gt4r.data.client.RecipeRenderer;
import trinsdar.gt4r.items.ItemCraftingModule;
import trinsdar.gt4r.items.ItemIntCircuit;
import trinsdar.gt4r.items.ItemMatch;
import trinsdar.gt4r.items.ItemMixedMetal;
import trinsdar.gt4r.items.ItemPowerUnit;
import trinsdar.gt4r.items.ItemStorageOrb;

import java.util.HashSet;
import java.util.Set;

import static muramasa.antimatter.fluid.AntimatterFluid.OVERLAY_TEXTURE;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RData {

    private static final String CAPE_PATH = "textures/capes/";
    public static final ResourceLocation[] CAPE_LOCATIONS = new ResourceLocation[] {new ResourceLocation(GT4RRef.ID,  CAPE_PATH + "braintech.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "silver.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "mrbrain.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "dev.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "gold.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "crazy.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "fake.png")};

    public static final Set<String> SupporterListSilver = new HashSet<>(), SupporterListGold = new HashSet<>();

    private static final boolean HC = AntimatterConfig.GAMEPLAY.HARDCORE_CABLES;

    static {
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            ImmutableMap.Builder<Integer, Item> builderItems = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                Item circuit = new ItemIntCircuit(GT4RRef.ID, "int_circuit_"+i, i).tip("ID: " + i);
                builder.put(i, RecipeIngredient.of(circuit, 1).setNoConsume());
                builderItems.put(i, circuit);
            }
            INT_CIRCUITS = builder.build();
            INT_CIRCUITS_ITEMS = builderItems.build();
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
            ImmutableMap.Builder<Tier, Wire<?>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WIRE_LEAD);
            builder.put(Tier.LV, WIRE_TIN);
            builder.put(Tier.MV, WIRE_COPPER);
            builder.put(Tier.HV, WIRE_GOLD);
            builder.put(Tier.EV, WIRE_ALUMINIUM);
            builder.put(Tier.IV, WIRE_TUNGSTEN);
            builder.put(Tier.LUV, WIRE_SUPERCONDUCTOR);
            TIER_WIRES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Cable<?>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, CABLE_LEAD);
            builder.put(Tier.LV, CABLE_TIN);
            builder.put(Tier.MV, CABLE_COPPER);
            builder.put(Tier.HV, CABLE_GOLD);
            builder.put(Tier.EV, CABLE_ALUMINIUM);
            builder.put(Tier.IV, CABLE_TUNGSTEN);
            builder.put(Tier.LUV, WIRE_SUPERCONDUCTOR);
            TIER_CABLES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, BatteryRE);
            builder.put(Tier.LV, BatterySmallLithium);
            builder.put(Tier.MV, BatteryMediumLithium);
            builder.put(Tier.HV, BatteryLargeLithium);
            builder.put(Tier.EV, LapotronCrystal);
            builder.put(Tier.IV, LapotronicEnergyOrb);
            TIER_BATTERIES = builder.build();
        }

    }

    public static void init(Side side) {
        if (side == Side.CLIENT){
            RecipeRenderer.clientMaps();
        }
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_TFC)){
            TFCData.init();
        }
    }

    public static final CoverFactory COVER_CONVEYOR = CoverFactory.builder(CoverConveyor::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "conveyor_module").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/conveyor_module")).build(GT4RRef.ID, "conveyor_module");
    public static final CoverFactory COVER_PUMP = CoverFactory.builder(CoverPump::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "pump_module").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/pump_module")).build(GT4RRef.ID, "pump_module");
    public static final CoverFactory COVER_FUSION_OUTPUT = CoverFactory.builder(CoverFusionOutput::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/fusion_output")).build(GT4RRef.ID, "fusion_output");
    public static final CoverFactory COVER_FUSION_INPUT = CoverFactory.builder(CoverFusionInput::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/fusion_input")).build(GT4RRef.ID, "fusion_input");
    public static final CoverFactory COVER_DYNAMO_OLD = CoverFactory.builder(CoverDynamoOld::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/dynamo")).build(GT4RRef.ID, "dynamo_old");
    public static final CoverFactory COVER_DRAIN = CoverFactory.builder(CoverDrain::new).item((a, b) ->
            new ItemCover(GT4RRef.ID, "drain").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/drain")).build(GT4RRef.ID, "drain");
    public static final CoverFactory COVER_STEAM_VENT = CoverFactory.builder(CoverSteamVent::new)
            .addTextures(new Texture(GT4RRef.ID, "block/cover/output")).build(GT4RRef.ID, "steam_vent");;
    public static final CoverFactory COVER_CRAFTING = CoverFactory.builder(CoverCrafting::new).gui().item((a, b) ->
            new ItemCraftingModule().tip("Can be placed on machines as a cover")).setMenuHandler(MenuHandlers.COVER_CRAFTING_HANDLER).addTextures(new Texture(GT4RRef.ID, "block/cover/crafting_module")).build(GT4RRef.ID, "crafting_module");
    public static final CoverFactory COVER_REDSTONE_MACHINE_CONTROLLER = CoverFactory.builder(CoverRedstoneMachineController::new).gui().item((a, b) ->
            new ItemCover(GT4RRef.ID, "redstone_machine_controller").tip("Can be placed on machines as a cover")).addTextures(new Texture(GT4RRef.ID, "block/cover/redstone_machine_controller")).build(GT4RRef.ID, "redstone_machine_controller");

    public static final AntimatterFluid PAHOEHOE_LAVA = AntimatterAPI.register(AntimatterFluid.class, new AntimatterFluid(GT4RRef.ID,"pahoehoe_lava", prepareAttributes(), prepareProperties()));

    private static FluidProperties.Builder prepareAttributes() {
        FluidProperties.Builder builder = FluidProperties.create();
        return builder.still(PAHOEHOE_STILL_TEXTURE).flowing(PAHOEHOE_STILL_TEXTURE).overlay(OVERLAY_TEXTURE)
                .viscosity(3000).density(6000).temperature(1200);
    }

    private static Block.Properties prepareProperties() {
        return Block.Properties.of(net.minecraft.world.level.material.Material.WATER).strength(100.0F).noDrops().lightLevel(s -> 9);
    }
    public static ItemBasic<?> Plantball = new ItemBasic<>(GT4RRef.ID, "plantball");
    public static ItemBasic<?> Biochaff = new ItemBasic<>(GT4RRef.ID, "biochaff");
    public static ItemBasic<?> CarbonFibre = new ItemBasic<>(GT4RRef.ID, "raw_carbon_fibre");
    public static ItemBasic<?> CarbonMesh = new ItemBasic<>(GT4RRef.ID, "carbon_mesh");
    public static ItemBasic<?> CoalBall = new ItemBasic<>(GT4RRef.ID, "coal_ball");
    public static ItemBasic<?> CompressedCoalBall = new ItemBasic<>(GT4RRef.ID, "compressed_coal_ball");
    public static ItemBasic<?> CoalChunk = new ItemBasic<>(GT4RRef.ID, "coal_chunk");

    public static ItemPowerUnit PowerUnitLV = new ItemPowerUnit(GT4RRef.ID, "power_unit_lv", Aluminium);
    public static ItemPowerUnit PowerUnitMV = new ItemPowerUnit(GT4RRef.ID, "power_unit_mv", StainlessSteel);
    public static ItemPowerUnit PowerUnitHV = new ItemPowerUnit(GT4RRef.ID, "power_unit_hv", Titanium);
    public static ItemPowerUnit SmallPowerUnit = new ItemPowerUnit(GT4RRef.ID, "small_power_unit", Aluminium);
    public static ItemPowerUnit RockCutterPowerUnit = new ItemPowerUnit(GT4RRef.ID, "rock_cutter_power_unit", Aluminium);

    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(GT4RRef.ID, "computer_monitor").tip("Can be placed on machines as a cover");
    public static ItemCover ConveyorModule = AntimatterAPI.get(ItemCover.class, COVER_CONVEYOR.getId(), GT4RRef.ID);
    public static ItemCover CraftingModule = AntimatterAPI.get(ItemCraftingModule.class, COVER_CRAFTING.getId(), GT4RRef.ID);
    public static ItemCover Drain = AntimatterAPI.get(ItemCover.class, COVER_DRAIN.getId(), GT4RRef.ID);
    public static ItemCover PumpModule = AntimatterAPI.get(ItemCover.class, COVER_PUMP.getId(), GT4RRef.ID);
    public static ItemCover RedstoneMachineController = AntimatterAPI.get(ItemCover.class, COVER_REDSTONE_MACHINE_CONTROLLER.getId(), GT4RRef.ID);
    public static ItemBasic<?> Shutter = new ItemBasic<>(GT4RRef.ID, "shutter").tip("Can be placed on machines as a cover");

    public static ItemBasic<?> OverclockerUpgrade = new ItemBasic<>(GT4RRef.ID, "overclocker_upgrade");
    public static ItemBasic<?> TransformerUpgrade = new ItemBasic<>(GT4RRef.ID, "transformer_upgrade");
    public static ItemBasic<?> SteamUpgrade = new ItemBasic<>(GT4RRef.ID, "steam_upgrade");
    public static ItemBasic<?> HVTransformerUpgrade = new ItemBasic<>(GT4RRef.ID, "hv_transformer_upgrade");
    public static ItemBasic<?> MufflerUpgrade = new ItemBasic<>(GT4RRef.ID, "muffler_upgrade");

    public static ItemFluidCell CellTin = new ItemFluidCell(GT4RRef.ID,Tin, 1000);

    public static ItemBasic<?> CopperCoil = new ItemBasic<>(GT4RRef.ID, "copper_coil");
    public static ItemBasic<?> CupronickelHeatingCoil = new ItemBasic<>(GT4RRef.ID, "cupronickel_heating_coil");
    public static ItemBasic<?> KanthalHeatingCoil = new ItemBasic<>(GT4RRef.ID, "kanthal_heating_coil");
    public static ItemBasic<?> NichromeHeatingCoil = new ItemBasic<>(GT4RRef.ID, "nichrome_heating_coil");
    public static ItemBasic<?> ItemFilter = new ItemBasic<>(GT4RRef.ID, "item_filter");
    public static ItemBasic<?> DiamondSawBlade = new ItemBasic<>(GT4RRef.ID, "diamond_saw_blade");
    public static ItemBasic<?> DiamondGrindHead = new ItemBasic<>(GT4RRef.ID, "diamond_grind_head");
    public static ItemBasic<?> TungstenGrindHead = new ItemBasic<>(GT4RRef.ID, "tungsten_grind_head");
    public static ItemBasic<?> IridiumAlloyIngot = new ItemBasic<>(GT4RRef.ID, "iridium_alloy_ingot").tip("Used to make Iridium Plates");
    public static ItemBasic<?> IridiumReinforcedPlate = new ItemBasic<>(GT4RRef.ID, "iridium_reinforced_plate").tip("GT2s Most Expensive Component");
    public static ItemBasic<?> IridiumNeutronReflector = new ItemBasic<>(GT4RRef.ID, "iridium_neutron_reflector").tip("Indestructible");
    public static ItemBasic<?> ThickNeutronReflector = new ItemBasic<>(GT4RRef.ID, "thick_neutron_reflector");
    public static ItemBasic<?> NeutronReflector = new ItemBasic<>(GT4RRef.ID, "neutron_reflector");
    public static ItemBasic<?> CompressedFireClay = new ItemBasic<>(GT4RRef.ID, "compressed_fire_clay").tip("Brick Shaped");
    public static ItemBasic<?> FireBrick = new ItemBasic<>(GT4RRef.ID, "fire_brick").tip("Heat Resistant");
    public static ItemBasic<?> ItemSuperconductor = new ItemBasic<>(GT4RRef.ID, "superconductor").tip("Conducts Energy Losslessly");
    public static ItemBasic<?> FrequencyTransmitter = new ItemBasic<>(GT4RRef.ID, "frequency_transmitter", new Item.Properties().tab(muramasa.antimatter.Ref.TAB_ITEMS).stacksTo(1));

    public static ItemBasic<?> LavaFilter = new ItemBasic<>(GT4RRef.ID, "lava_filter", new Item.Properties().tab(muramasa.antimatter.Ref.TAB_ITEMS).defaultDurability(100));
    public static ItemBasic<?> LighterEmpty = new ItemBasic<>(GT4RRef.ID, "lighter_empty");
    public static ItemBasic<ItemMatch> Match = new ItemMatch(GT4RRef.ID, "match", new Item.Properties().tab(muramasa.antimatter.Ref.TAB_ITEMS));
    public static ItemBasic<ItemMatch> MatchBook = new ItemMatch(GT4RRef.ID, "match_book", new Item.Properties().tab(muramasa.antimatter.Ref.TAB_ITEMS).defaultDurability(64));
    public static ItemBasic<ItemMatch> Lighter = new ItemMatch(GT4RRef.ID, "lighter", new Item.Properties().tab(muramasa.antimatter.Ref.TAB_ITEMS).defaultDurability(100));

    public static ItemBasic<?> MixedMetal = new ItemMixedMetal();
    public static ItemBasic<?> AdvancedAlloy = new ItemBasic<>(GT4RRef.ID,"advanced_alloy");
    public static ItemBasic<?> MachineParts = new ItemBasic<>(GT4RRef.ID, "machine_parts");
    public static ItemBasic<?> AdvCircuitParts = new ItemBasic<>(GT4RRef.ID, "advanced_circuit_parts").tip("Used for making Advanced Circuits");
    public static ItemBasic<?> CircuitBoardBasic = new ItemBasic<>(GT4RRef.ID, "basic_circuit_board").tip("A basic Board");
    public static ItemBasic<?> CircuitBoardAdv = new ItemBasic<>(GT4RRef.ID, "advanced_circuit_board").tip("An advanced Board");
    public static ItemBasic<?> CircuitBoardProcessor = new ItemBasic<>(GT4RRef.ID, "processor_circuit_board").tip("A Processor Board");
    public static ItemBasic<?> CircuitBasic = new ItemBasic<>(GT4RRef.ID, "basic_circuit").tip("A basic Circuit");
    public static ItemBasic<?> CircuitAdv = new ItemBasic<>(GT4RRef.ID, "advanced_circuit").tip("An advanced Circuit");
    public static ItemBasic<?> CircuitDataStorage = new ItemBasic<>(GT4RRef.ID, "data_storage_circuit").tip("A Data Storage Chip");
    public static ItemBasic<?> CircuitDataControl = new ItemBasic<>(GT4RRef.ID, "data_control_circuit").tip("A Data Control Processor");
    public static ItemBasic<?> CircuitEnergyFlow = new ItemBasic<>(GT4RRef.ID, "energy_flow_circuit").tip("A High Voltage Processor");
    public static ItemBasic<?> CircuitDataOrb = new ItemBasic<>(GT4RRef.ID, "data_orb").tip("A High Capacity Data Storage");
    public static ItemBasic<?> StorageDataOrb = new ItemStorageOrb(GT4RRef.ID, "storage_data_orb").tip("A High Capacity Data Storage");
    public static ItemBasic<?> MotorLV = new ItemBasic<>(GT4RRef.ID, "lv_motor");
    public static ItemBasic<?> MotorMV = new ItemBasic<>(GT4RRef.ID, "mv_motor");
    public static ItemBasic<?> MotorHV = new ItemBasic<>(GT4RRef.ID, "hv_motor");

    public static ItemBasic<?> BatteryHullSmall = new ItemBasic<>(GT4RRef.ID, "battery_hull_small").tip("An empty LV Battery Hull");
    public static ItemBasic<?> BatteryHullMedium = new ItemBasic<>(GT4RRef.ID, "battery_hull_medium").tip("An empty MV Battery Hull");
    public static ItemBasic<?> BatteryHullLarge = new ItemBasic<>(GT4RRef.ID, "battery_hull_large").tip("An empty HV Battery Hull");
    public static ItemBasic<?> BatterySmallAcid = new ItemMultiTextureBattery(GT4RRef.ID, "battery_small_acid", Tier.LV, 50000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallMercury = new ItemMultiTextureBattery(GT4RRef.ID, "battery_small_mercury", Tier.LV, 100000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallCadmium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_small_cadmium", Tier.LV,75000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallLithium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_small_lithium", Tier.LV, 100000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallSodium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_small_sodium", Tier.LV, 50000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumAcid = new ItemMultiTextureBattery(GT4RRef.ID, "battery_medium_acid", Tier.MV, 200000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumMercury = new ItemMultiTextureBattery(GT4RRef.ID, "battery_medium_mercury", Tier.MV, 400000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumCadmium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_medium_cadmium", Tier.MV, 300000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumLithium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_medium_lithium", Tier.MV, 400000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumSodium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_medium_sodium", Tier.MV,200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeAcid = new ItemMultiTextureBattery(GT4RRef.ID, "battery_large_acid", Tier.HV, 800000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeMercury = new ItemMultiTextureBattery(GT4RRef.ID, "battery_large_mercury", Tier.HV, 1600000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeCadmium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_large_cadmium", Tier.HV, 1200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeLithium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_large_lithium", Tier.HV, 1600000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeSodium = new ItemMultiTextureBattery(GT4RRef.ID, "battery_large_sodium", Tier.HV, 800000, true).tip("Reusable");
    public static ItemBasic<?> LapotronCrystal = new ItemMultiTextureBattery(GT4RRef.ID, "lapotron_crystal", Tier.EV, 10000000, true).tip("Reusable");
    public static ItemBasic<?> EnergyCrystal = new ItemMultiTextureBattery(GT4RRef.ID, "energy_crystal", Tier.HV, 1000000, true).tip("Reusable");
    public static ItemBasic<?> BatteryRE = new ItemMultiTextureBattery(GT4RRef.ID, "battery_re", Tier.LV, 10000, true).tip("Reusable");
    public static ItemBasic<?> LapotronicEnergyOrb = new ItemMultiTextureBattery(GT4RRef.ID, "lapotronic_energy_orb", Tier.IV,100000000, true);
    public static ItemBasic<?> ZPM = new ItemBattery(GT4RRef.ID, "zpm", Tier.ZPM, 100000000000L, false);
    //public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(Ref.ID, "battery_energy_orb_cluster");

    public static ItemBasic<?> EmptyShape = new ItemBasic<>(GT4RRef.ID, "empty_shape_plate").tip("Raw plate to make Molds and Extruder Shapes");
    public static ItemBasic<?> MoldPlate = new ItemBasic<>(GT4RRef.ID, "mold_plate").tip("Mold for making Plates");
    public static ItemBasic<?> MoldCasing = new ItemBasic<>(GT4RRef.ID, "mold_casing").tip("Mold for making Item Casings - only usable with ic2");
    public static ItemBasic<?> MoldGear = new ItemBasic<>(GT4RRef.ID, "mold_gear").tip("Mold for making Gears");
    public static ItemBasic<?> MoldCoinage = new ItemBasic<>(GT4RRef.ID, "mold_coinage").tip("Secure Mold for making Coins (Don't lose it!)");
    public static ItemBasic<?> MoldBottle = new ItemBasic<>(GT4RRef.ID, "mold_bottle").tip("Mold for making Bottles");
    public static ItemBasic<?> MoldIngot = new ItemBasic<>(GT4RRef.ID, "mold_ingot").tip("Mold for making Ingots");
    public static ItemBasic<?> MoldBlock = new ItemBasic<>(GT4RRef.ID, "mold_block").tip("Mold for making Blocks");
    public static ItemBasic<?> MoldNugget = new ItemBasic<>(GT4RRef.ID, "mold_nugget").tip("Mold for making Nuggets");
    public static ItemBasic<?> ShapePlate = new ItemBasic<>(GT4RRef.ID, "shape_plate").tip("Shape for making Plates");
    public static ItemBasic<?> ShapeRod = new ItemBasic<>(GT4RRef.ID, "shape_rod").tip("Shape for making Rods");
    public static ItemBasic<?> ShapeBolt = new ItemBasic<>(GT4RRef.ID, "shape_bolt").tip("Shape for making Boltss");
    public static ItemBasic<?> ShapeRing = new ItemBasic<>(GT4RRef.ID, "shape_ring").tip("Shape for making Rings");
    public static ItemBasic<?> ShapeCell = new ItemBasic<>(GT4RRef.ID, "shape_cell").tip("Shape for making Cells");
    public static ItemBasic<?> ShapeIngot = new ItemBasic<>(GT4RRef.ID, "shape_ingot").tip("Shape for making Ingots");
    public static ItemBasic<?> ShapeWire = new ItemBasic<>(GT4RRef.ID, "shape_wire").tip("Shape for making Wires");
    public static ItemBasic<?> ShapeCasing = new ItemBasic<>(GT4RRef.ID, "shape_casing").tip("Shape for making Item Casings - only usable with ic2");
    public static ItemBasic<?> ShapePipeTiny = new ItemBasic<>(GT4RRef.ID, "shape_pipe_tiny").tip("Shape for making Tiny Pipes");
    public static ItemBasic<?> ShapePipeSmall = new ItemBasic<>(GT4RRef.ID, "shape_pipe_small").tip("Shape for making Small Pipes");
    public static ItemBasic<?> ShapePipeNormal = new ItemBasic<>(GT4RRef.ID, "shape_pipe_normal").tip("Shape for making Normal Pipes");
    public static ItemBasic<?> ShapePipeLarge = new ItemBasic<>(GT4RRef.ID, "shape_pipe_large").tip("Shape for making Large Pipes");
    public static ItemBasic<?> ShapePipeHuge = new ItemBasic<>(GT4RRef.ID, "shape_pipe_huge").tip("Shape for making Huge Pipes");
    public static ItemBasic<?> ShapeBlock = new ItemBasic<>(GT4RRef.ID, "shape_block").tip("Shape for making Blocks");
    public static ItemBasic<?> ShapeHeadSword = new ItemBasic<>(GT4RRef.ID, "shape_head_sword").tip("Shape for making Sword Blades");
    public static ItemBasic<?> ShapeHeadPickaxe = new ItemBasic<>(GT4RRef.ID, "shape_head_pickaxe").tip("Shape for making Pickaxe Heads");
    public static ItemBasic<?> ShapeHeadShovel = new ItemBasic<>(GT4RRef.ID, "shape_head_shovel").tip("Shape for making Shovel Heads");
    public static ItemBasic<?> ShapeHeadAxe = new ItemBasic<>(GT4RRef.ID, "shape_head_axe").tip("Shape for making Axe Heads");
    public static ItemBasic<?> ShapeHeadHoe = new ItemBasic<>(GT4RRef.ID, "shape_head_hoe").tip("Shape for making Hoe Heads");
    public static ItemBasic<?> ShapeHeadHammer = new ItemBasic<>(GT4RRef.ID, "shape_head_hammer").tip("Shape for making Hammer Heads");
    public static ItemBasic<?> ShapeHeadFile = new ItemBasic<>(GT4RRef.ID, "shape_head_file").tip("Shape for making File Heads");
    public static ItemBasic<?> ShapeHeadSaw = new ItemBasic<>(GT4RRef.ID, "shape_head_saw").tip("Shape for making Saw Heads");
    public static ItemBasic<?> ShapeGear = new ItemBasic<>(GT4RRef.ID, "shape_gear").tip("Shape for making Gears");
    public static ItemBasic<?> ShapeBottle = new ItemBasic<>(GT4RRef.ID, "shape_bottle").tip("Shape for making Bottles"); //TODO needed?

    public static StoneType GRANITE_RED = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "red_granite", Materials.RedGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType GRANITE_BLACK = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "black_granite", Materials.BlackGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType MARBLE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "marble", Materials.Marble, "block/stone/", SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F));

    public static StoneType KOMATIITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "komatiite", Materials.Komatiite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType LIMESTONE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "limestone", Limestone, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType GREEN_SCHIST = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "green_schist", GreenSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType BLUE_SCHIST = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "blue_schist", BlueSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType KIMBERLITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "kimberlite", Kimberlite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType QUARTZITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GT4RRef.ID, "quartzite", Quartzite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);


    public static final BlockCasing STANDARD_MACHINE_CASING = new BlockCasing(GT4RRef.ID, "standard_machine_casing");
    public static final BlockCasing REINFORCED_MACHINE_CASING = new BlockCasing(GT4RRef.ID, "reinforced_machine_casing");
    public static final BlockCasing ADVANCED_MACHINE_CASING = new BlockCasing(GT4RRef.ID, "advanced_machine_casing");
    public static final BlockCasing TUNGSTENSTEEL_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "tungstensteel_reinforced_stone");
    public static final BlockCasing IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "iridium_tungstensteel_reinforced_stone");
    public static final BlockCasing HIGHLY_ADVANCED_MACHINE_BLOCK = new BlockCasing(GT4RRef.ID, "highly_advanced_machine_block");
    public static final BlockCasing ADVANCED_MACHINE_BLOCK = new BlockCasing(GT4RRef.ID, "advanced_machine_block");

    public static final BlockCasing FIRE_BRICKS = new BlockCasing(GT4RRef.ID, "fire_bricks");
    public static final BlockCasing REINFORCED_GLASS = new BlockCasing(GT4RRef.ID, "reinforced_glass", Block.Properties.of(net.minecraft.world.level.material.Material.GLASS).strength(15.0f, 150.0f).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().isValidSpawn(GT4RData::neverAllowSpawn).isRedstoneConductor(GT4RData::isntSolid).isSuffocating(GT4RData::isntSolid).isViewBlocking(GT4RData::isntSolid));
    public static final BlockCasing REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "reinforced_stone", Block.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(80.0f, 150.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final BlockCasing IRIDIUM_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "iridium_reinforced_stone", Block.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(80.0f, 150.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());

    public static final BlockCasing FUSION_COIL = new BlockCasing(GT4RRef.ID, "fusion_coil");

    public static final Cable<?> CABLE_LEAD = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Lead, 2, Tier.LV).amps(2));
    public static final Cable<?> CABLE_TIN = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tin, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, AntimatterMaterials.Copper, 2, Tier.MV).amps(1));
    public static final Cable<?> CABLE_NICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Nickel, 3, Tier.MV).amps(3));
    public static final Cable<?> CABLE_GOLD = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, AntimatterMaterials.Gold, 2, Tier.HV).amps(3));
    public static final Cable<?> CABLE_ELECTRUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Electrum, 2, Tier.HV).amps(2));
    public static final Cable<?> CABLE_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Steel, 2, Tier.EV).amps(2));
    public static final Cable<?> CABLE_ALUMINIUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Cable<?> CABLE_OSMIUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Osmium, 2, Tier.IV).amps(4));
    public static final Cable<?> CABLE_TUNGSTEN = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tungsten, 2, Tier.IV).amps(2));

    public static final Wire<?> WIRE_LEAD = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Lead, 4, Tier.LV).amps(2));
    public static final Wire<?> WIRE_TIN = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tin, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, AntimatterMaterials.Copper, 4, Tier.MV).amps(1));
    public static final Wire<?> WIRE_NICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Nickel, 6, Tier.MV).amps(3));
    public static final Wire<?> WIRE_GOLD = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, AntimatterMaterials.Gold, 6, Tier.HV).amps(3));
    public static final Wire<?> WIRE_ELECTRUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Electrum, 4, Tier.HV).amps(2));
    public static final Wire<?> WIRE_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Steel, 4, Tier.EV).amps(2));
    public static final Wire<?> WIRE_ALUMINIUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Wire<?> WIRE_OSMIUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Osmium, 4, Tier.IV).amps(4));
    public static final Wire<?> WIRE_TUNGSTEN = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tungsten, 4, Tier.IV).amps(2));
    public static final Wire<?> WIRE_SUPERCONDUCTOR = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Superconductor, 0, Tier.MAX).amps(4)); //MAX

    public static final FluidPipe<?> FLUID_PIPE_WOOD = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Wood, 350, false).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Plastic, 370, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Copper, 1696, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Bronze, 1696, true).caps(1).pressures(getPressures(450)));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Invar, 2395, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Steel, 2557, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, StainlessSteel, 2428, true).caps(1).pressures(getPressures(750)));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Netherite, 2807, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Tungsten, 4618, true).caps(1).pressures(getPressures(1050)));
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Titanium, 1668, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenSteel, 3587, true).caps(1).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenCarbide, 3837, true).caps(1).pressures(getPressures(1350)));
    public static final FluidPipe<?> FLUID_PIPE_HP = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000));

    public static final ItemPipe<?> ITEM_PIPE_BRASS = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Brass).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Cupronickel).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Electrum).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Magnalium).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Platinum).stepsize(8192).caps(0, 0, 0, 4, 8, 16));
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmium).stepsize(4096).caps(0, 0, 0, 8, 16, 32));
    public static final ItemPipe<?> ITEM_PIPE_HC = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, HighCapacity).stepsize(4096).caps(64));
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmiridium).stepsize(1024).caps(0, 0, 0, 32, 64, 128));

    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Integer, Item> INT_CIRCUITS_ITEMS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static ImmutableMap<Tier, Wire<?>> TIER_WIRES;
    public static ImmutableMap<Tier, Cable<?>> TIER_CABLES;

    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Item> TIER_BATTERIES;
    public static ImmutableMap<Tier, FluidPipe> TIER_PIPES;

    public static Boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    private static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    private static int[] getPressures(int basePressure){
        basePressure *= 20;
        return new int[]{basePressure / 6, basePressure / 6, basePressure / 3, basePressure, basePressure * 2, basePressure * 4};
    }

    public static class TFCData{
        public static final ItemPipe<?> ITEM_PIPE_STERLING_SILVER = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, SterlingSilver).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_ROSE_GOLD = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, RoseGold).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_BLACK_BRONZE = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, BlackBronze).caps(0, 0, 0, 2, 4, 8));
        public static void init(){
            //NOOP
        }
    }
}
