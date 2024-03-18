package trinsdar.gt4r.data;

import earth.terrarium.botarium.common.registry.fluid.FluidProperties;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTools;
import io.github.gregtechintergalactical.gtcore.item.ItemPowerUnit;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.behaviour.BehaviourAOEBreak;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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
import trinsdar.gt4r.items.ItemElectricTool;
import trinsdar.gt4r.items.ItemMixedMetal;
import trinsdar.gt4r.items.ItemRockCutterUnit;
import trinsdar.gt4r.items.ItemStorageOrb;

import java.util.HashSet;
import java.util.Set;

import static muramasa.antimatter.fluid.AntimatterFluid.OVERLAY_TEXTURE;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RData {

    private static final String CAPE_PATH = "textures/capes/";
    public static final ResourceLocation[] CAPE_LOCATIONS = new ResourceLocation[] {new ResourceLocation(GT4RRef.ID,  CAPE_PATH + "braintech.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "silver.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "mrbrain.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "dev.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "gold.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "crazy.png"), new ResourceLocation(GT4RRef.ID, CAPE_PATH + "fake.png")};

    public static final Set<String> SupporterListSilver = new HashSet<>(), SupporterListGold = new HashSet<>();

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
                .viscosity(3000).density(6000).temperature(1200).sounds("bucket_fill", SoundEvents.BUCKET_FILL).sounds("bucket_empty", SoundEvents.BUCKET_EMPTY);
    }

    private static Block.Properties prepareProperties() {
        return Block.Properties.of(net.minecraft.world.level.material.Material.WATER).strength(100.0F).noDrops().lightLevel(s -> 9);
    }
    public static ItemPowerUnit RockCutterPowerUnit = new ItemRockCutterUnit(GT4RRef.ID, "rock_cutter_power_unit", Aluminium);

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
    public static ItemBasic<?> ThickNeutronReflector = new ItemBasic<>(GT4RRef.ID, "thick_neutron_reflector");
    public static ItemBasic<?> NeutronReflector = new ItemBasic<>(GT4RRef.ID, "neutron_reflector");
    public static ItemBasic<?> ItemSuperconductor = new ItemBasic<>(GT4RRef.ID, "superconductor").tip("Conducts Energy Losslessly");
    public static ItemBasic<?> FrequencyTransmitter = new ItemBasic<>(GT4RRef.ID, "frequency_transmitter", new Item.Properties().tab(Ref.TAB_ITEMS).stacksTo(1));

    public static ItemBasic<?> LavaFilter = new ItemBasic<>(GT4RRef.ID, "lava_filter", new Item.Properties().tab(Ref.TAB_ITEMS).defaultDurability(100));

    public static ItemBasic<?> MixedMetal = new ItemMixedMetal();
    public static ItemBasic<?> AdvancedAlloy = new ItemBasic<>(GT4RRef.ID,"advanced_alloy");
    public static ItemBasic<?> MachineParts = new ItemBasic<>(GT4RRef.ID, "machine_parts");
    public static ItemBasic<?> StorageDataOrb = new ItemStorageOrb(GT4RRef.ID, "storage_data_orb").tip("A High Capacity Data Storage");

    public static ItemElectricTool Drill = new ItemElectricTool("drill", GTCoreTools.DRILL, Steel, 6.0f, 5.5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool DiamondDrill = new ItemElectricTool("diamond_drill", GTCoreTools.DRILL, Steel, 8.0f, 6.0f, 3, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool AdvancedDrill = new ItemElectricTool("advanced_drill", GTCoreTools.DRILL, TungstenSteel, 10.0f, 9.0f, 4, 2, b -> true);
    public static ItemElectricTool Chainsaw = new ItemElectricTool("chainsaw", GTCoreTools.CHAINSAW, Steel, 6.0f, 8.5f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedChainsaw = new ItemElectricTool("advanced_chainsaw", GTCoreTools.CHAINSAW, TungstenSteel, 10.0f, 12f, 4, 2, b -> true);
    public static ItemElectricTool ElectricWrench = new ItemElectricTool("electric_wrench", GTCoreTools.ELECTRIC_WRENCH, Steel, 6.0f, 4f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedWrench = new ItemElectricTool("advanced_wrench", GTCoreTools.ELECTRIC_WRENCH, TungstenSteel, 10.0f, 7.5f, 4, 2, b -> true);
    public static ItemElectricTool ElectricWrenchAlt = new ItemElectricTool("electric_wrench_alt", GTCoreTools.ELECTRIC_WRENCH_ALT, Steel, 6.0f, 4f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedWrenchAlt = new ItemElectricTool("advanced_wrench_alt", GTCoreTools.ELECTRIC_WRENCH_ALT, TungstenSteel, 10.0f, 7.5f, 4, 2, b -> true);
    public static ItemElectricTool ElectricScrewdriver = new ItemElectricTool("electric_screwdriver", GTCoreTools.ELECTRIC_SCREWDRIVER, Steel, 6.0f, 2.5f, 2, 1, b -> true);
    public static ItemElectricTool RockCutter = new ItemElectricTool("rock_cutter", ToolTypes.ROCK_CUTTER, AntimatterMaterials.Diamond, 8.0f, 1.0f, 3, 1, b -> true);
    public static ItemElectricTool BronzeJackHammer = new ItemElectricTool("bronze_jackhammer", GTCoreTools.JACKHAMMER, Bronze, 6.0f, 5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool SteelJackHammer = new ItemElectricTool("steel_jackhammer", GTCoreTools.JACKHAMMER, Steel, 8.0f, 5.5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool DiamondJackHammer = new ItemElectricTool("diamond_jackhammer", GTCoreTools.JACKHAMMER, AntimatterMaterials.Diamond, 10.0f, 6.0f, 3, 2, b -> true);

    public static ItemBasic<?> ZPM = new ItemBattery(GT4RRef.ID, "zpm", Tier.ZPM, 100000000000L, false);
    //public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(Ref.ID, "battery_energy_orb_cluster");

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

    public static final Cable<?> CABLE_SOLDERING_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, SolderingAlloy, 0.02, Tier.ULV).amps(1));
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

    public static final Wire<?> WIRE_SOLDERING_ALLOY = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, SolderingAlloy, 0.04, Tier.ULV).amps(1));
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
