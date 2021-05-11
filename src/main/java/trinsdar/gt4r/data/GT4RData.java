package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.PipeType;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.material.MaterialRecipe;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import tesseract.api.capability.TesseractGTCapability;
import trinsdar.gt4r.block.BlockCasing;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.texture.Texture;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockConnectedCasing;
import trinsdar.gt4r.block.BlockSapBag;
import trinsdar.gt4r.cover.CoverConveyor;
import trinsdar.gt4r.cover.CoverDrain;
import trinsdar.gt4r.cover.CoverDynamoOld;
import trinsdar.gt4r.cover.CoverFusionInput;
import trinsdar.gt4r.cover.CoverFusionOutput;
import trinsdar.gt4r.cover.CoverPump;
import trinsdar.gt4r.cover.CoverSteamVent;
import trinsdar.gt4r.items.ItemIntCircuit;
import trinsdar.gt4r.items.ItemMatch;
import trinsdar.gt4r.items.ItemMixedMetal;
import trinsdar.gt4r.items.MaterialSpear;
import trinsdar.gt4r.tree.BlockRubberLeaves;
import trinsdar.gt4r.tree.BlockRubberLog;
import trinsdar.gt4r.tree.BlockRubberSapling;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static muramasa.antimatter.Data.NULL;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RData {

    private static final boolean HC = AntimatterConfig.GAMEPLAY.HARDCORE_CABLES;

    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingInventory inv, MaterialRecipe.Result mats) {
            Material m = id.contains("jackhammer") ? NULL : id.contains("lv") ? StainlessSteel : id.contains("mv") ? Titanium : id.contains("hv") ? TungstenSteel : NULL;
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'));
            return resolveStack(type, (Material) mats.mats.get("primary"), m, battery.getA(), battery.getB());
        }

        public ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
            if (tool == null){
                return ItemStack.EMPTY;
            }
            ItemStack stack = new ItemStack(tool.getItem());
            tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
            Map<Enchantment, Integer> mainEnchants = primary.getEnchantments();
            if (!mainEnchants.isEmpty()) {
                mainEnchants.entrySet().stream().filter(e -> e.getKey().canApply(stack)).forEach(e -> stack.addEnchantment(e.getKey(), e.getValue()));
            }
            return stack;
        }

        @Override
        public Map<String, Object> getFromResult(@Nonnull ItemStack stack) {
            CompoundNBT nbt = stack.getTag().getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
            Material primary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    static {
        PropertyIngredient.addGetter(CustomTags.BATTERIES_SMALL.getName(), GT4RData::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_MEDIUM.getName(), GT4RData::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_LARGE.getName(), GT4RData::getEnergy);
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            ImmutableMap.Builder<Integer, Item> builderItems = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                Item circuit = new ItemIntCircuit(Ref.ID, "int_circuit_"+i, i).tip("ID: " + i);
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

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery){
            long energy = stack.getTag() != null ? stack.getTag().getLong(muramasa.antimatter.Ref.KEY_ITEM_ENERGY) : 0;
            return new Tuple<>(energy, ((ItemBattery)stack.getItem()).getCapacity());
        }
        if (stack.getItem() instanceof IAntimatterTool){
            IAntimatterTool tool = (IAntimatterTool) stack.getItem();
            if (tool.getType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        return new Tuple<>((long)0, (long)100000);
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
        //temp method till this behavior has a hotkey
        Data.DRILL.removeBehaviour("aoe_break");
    }

    public static void init(Dist side) {
        if (side.isClient()){
            RecipeRenderer.clientMaps();
        }
    }

    public static final BaseCover COVER_CONVEYOR = new CoverConveyor();
    public static final BaseCover COVER_PUMP = new CoverPump();
    public static final BaseCover COVER_FUSION_OUTPUT = new CoverFusionOutput();
    public static final BaseCover COVER_FUSION_INPUT = new CoverFusionInput();
    public static final BaseCover COVER_DYNAMO_OLD = new CoverDynamoOld("dynamo_old");
    public static final BaseCover COVER_DRAIN = new CoverDrain();
    public static final BaseCover COVER_STEAM_VENT = new CoverSteamVent();

    public static ItemBasic<?> StickyResin = new ItemBasic<>(Ref.ID, "sticky_resin");
    public static ItemBasic<?> Plantball = new ItemBasic<>(Ref.ID, "plantball");
    public static ItemBasic<?> Biochaff = new ItemBasic<>(Ref.ID, "biochaff");
    public static ItemBasic<?> CarbonFibre = new ItemBasic<>(Ref.ID, "raw_carbon_fibre");
    public static ItemBasic<?> CarbonMesh = new ItemBasic<>(Ref.ID, "carbon_mesh");
    public static ItemBasic<?> CoalBall = new ItemBasic<>(Ref.ID, "coal_ball");
    public static ItemBasic<?> CompressedCoalBall = new ItemBasic<>(Ref.ID, "compressed_coal_ball");
    public static ItemBasic<?> CoalChunk = new ItemBasic<>(Ref.ID, "coal_chunk");

    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(Ref.ID, "computer_monitor").tip("Can be placed on machines as a cover");
    public static ItemCover ConveyorModule = new ItemCover(Ref.ID, COVER_CONVEYOR.getId()).tip("Can be placed on machines as a cover");
    public static ItemBasic<?> CraftingModule = new ItemBasic<>(Ref.ID, "crafting_module").tip("Can be placed on machines as a cover");
    public static ItemCover Drain = new ItemCover(Ref.ID, "drain").tip("Can be placed on machines as a cover");
    public static ItemBasic<?> ItemTransportValve = new ItemBasic<>(Ref.ID, "item_transport_valve").tip("Can be placed on machines as a cover");
    public static ItemCover PumpModule = new ItemCover(Ref.ID, COVER_PUMP.getId()).tip("Can be placed on machines as a cover");
    public static ItemBasic<?> RedstoneMachineController = new ItemBasic<>(Ref.ID, "redstone_machine_controller").tip("Can be placed on machines as a cover");
    public static ItemBasic<?> Shutter = new ItemBasic<>(Ref.ID, "shutter").tip("Can be placed on machines as a cover");

    public static ItemFluidCell CellTin = new ItemFluidCell(Ref.ID,Tin, 1000);

    public static ItemBasic<?> CopperCoil = new ItemBasic<>(Ref.ID, "copper_coil");
    public static ItemBasic<?> CupronickelHeatingCoil = new ItemBasic<>(Ref.ID, "cupronickel_heating_coil");
    public static ItemBasic<?> KanthalHeatingCoil = new ItemBasic<>(Ref.ID, "kanthal_heating_coil");
    public static ItemBasic<?> NichromeHeatingCoil = new ItemBasic<>(Ref.ID, "nichrome_heating_coil");
    public static ItemBasic<?> ItemFilter = new ItemBasic<>(Ref.ID, "item_filter");
    public static ItemBasic<?> DiamondSawBlade = new ItemBasic<>(Ref.ID, "diamond_saw_blade");
    public static ItemBasic<?> DiamondGrindHead = new ItemBasic<>(Ref.ID, "diamond_grind_head");
    public static ItemBasic<?> TungstenGrindHead = new ItemBasic<>(Ref.ID, "tungsten_grind_head");
    public static ItemBasic<?> IridiumAlloyIngot = new ItemBasic<>(Ref.ID, "iridium_alloy_ingot").tip("Used to make Iridium Plates");
    public static ItemBasic<?> IridiumReinforcedPlate = new ItemBasic<>(Ref.ID, "iridium_reinforced_plate").tip("GT2s Most Expensive Component");
    public static ItemBasic<?> IridiumNeutronReflector = new ItemBasic<>(Ref.ID, "iridium_neutron_reflector").tip("Indestructible");
    public static ItemBasic<?> CompressedFireClay = new ItemBasic<>(Ref.ID, "compressed_fire_clay").tip("Brick Shaped");
    public static ItemBasic<?> FireBrick = new ItemBasic<>(Ref.ID, "fire_brick").tip("Heat Resistant");
    public static ItemBasic<?> ItemSuperconductor = new ItemBasic<>(Ref.ID, "superconductor").tip("Conducts Energy Losslessly");

    public static ItemBasic<?> LavaFilter = new ItemBasic<>(Ref.ID, "lava_filter");
    public static ItemBasic<ItemMatch> Match = new ItemMatch(Ref.ID, "match", new Item.Properties().group(muramasa.antimatter.Ref.TAB_ITEMS));
    public static ItemBasic<ItemMatch> MatchBook = new ItemMatch(Ref.ID, "match_book", new Item.Properties().group(muramasa.antimatter.Ref.TAB_ITEMS).defaultMaxDamage(64));
    public static ItemBasic<?> Treetap = new ItemBasic<>(Ref.ID, "treetap", new Item.Properties().defaultMaxDamage(16).group(muramasa.antimatter.Ref.TAB_ITEMS));

    public static ItemBasic<?> MixedMetal = new ItemMixedMetal();
    public static ItemBasic<?> AdvancedAlloy = new ItemBasic<>(Ref.ID,"advanced_alloy");
    public static ItemBasic<?> MachineParts = new ItemBasic<>(Ref.ID, "machine_parts");
    public static ItemBasic<?> AdvCircuitParts = new ItemBasic<>(Ref.ID, "advanced_circuit_parts").tip("Used for making Advanced Circuits");
    public static ItemBasic<?> CircuitBoardBasic = new ItemBasic<>(Ref.ID, "basic_circuit_board").tip("A basic Board");
    public static ItemBasic<?> CircuitBoardAdv = new ItemBasic<>(Ref.ID, "advanced_circuit_board").tip("An advanced Board");
    public static ItemBasic<?> CircuitBoardProcessor = new ItemBasic<>(Ref.ID, "processor_circuit_board").tip("A Processor Board");
    public static ItemBasic<?> CircuitBasic = new ItemBasic<>(Ref.ID, "basic_circuit").tip("A basic Circuit");
    public static ItemBasic<?> CircuitAdv = new ItemBasic<>(Ref.ID, "advanced_circuit").tip("An advanced Circuit");
    public static ItemBasic<?> CircuitDataStorage = new ItemBasic<>(Ref.ID, "data_storage_circuit").tip("A Data Storage Chip");
    public static ItemBasic<?> CircuitDataControl = new ItemBasic<>(Ref.ID, "data_control_circuit").tip("A Data Control Processor");
    public static ItemBasic<?> CircuitEnergyFlow = new ItemBasic<>(Ref.ID, "energy_flow_circuit").tip("A High Voltage Processor");
    public static ItemBasic<?> CircuitDataOrb = new ItemBasic<>(Ref.ID, "data_orb").tip("A High Capacity Data Storage");
    public static ItemBasic<?> MotorLV = new ItemBasic<>(Ref.ID, "lv_motor");
    public static ItemBasic<?> MotorMV = new ItemBasic<>(Ref.ID, "mv_motor");
    public static ItemBasic<?> MotorHV = new ItemBasic<>(Ref.ID, "hv_motor");

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
    public static ItemBasic<?> LapotronCrystal = new ItemBattery(Ref.ID, "lapotron_crystal", Tier.EV, 10000000, true).tip("Reusable");
    public static ItemBasic<?> EnergyCrystal = new ItemBattery(Ref.ID, "energy_crystal", Tier.HV, 1000000, true).tip("Reusable");
    public static ItemBasic<?> BatteryRE = new ItemBattery(Ref.ID, "battery_re", Tier.LV, 10000, true).tip("Reusable");
    public static ItemBasic<?> LapotronicEnergyOrb = new ItemBattery(Ref.ID, "lapotronic_energy_orb", Tier.IV,100000000, true);
    public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(Ref.ID, "battery_energy_orb_cluster");

    public static ItemBasic<?> EmptyShape = new ItemBasic<>(Ref.ID, "empty_shape_plate").tip("Raw plate to make Molds and Extruder Shapes");
    public static ItemBasic<?> MoldPlate = new ItemBasic<>(Ref.ID, "mold_plate").tip("Mold for making Plates");
    public static ItemBasic<?> MoldCasing = new ItemBasic<>(Ref.ID, "mold_casing").tip("Mold for making Item Casings - only usable with ic2");
    public static ItemBasic<?> MoldGear = new ItemBasic<>(Ref.ID, "mold_gear").tip("Mold for making Gears");
    public static ItemBasic<?> MoldCoinage = new ItemBasic<>(Ref.ID, "mold_coinage").tip("Secure Mold for making Coins (Don't lose it!)");
    public static ItemBasic<?> MoldBottle = new ItemBasic<>(Ref.ID, "mold_bottle").tip("Mold for making Bottles");
    public static ItemBasic<?> MoldIngot = new ItemBasic<>(Ref.ID, "mold_ingot").tip("Mold for making Ingots");
    public static ItemBasic<?> MoldBlock = new ItemBasic<>(Ref.ID, "mold_block").tip("Mold for making Blocks");
    public static ItemBasic<?> MoldNugget = new ItemBasic<>(Ref.ID, "mold_nugget").tip("Mold for making Nuggets");
    public static ItemBasic<?> ShapePlate = new ItemBasic<>(Ref.ID, "shape_plate").tip("Shape for making Plates");
    public static ItemBasic<?> ShapeRod = new ItemBasic<>(Ref.ID, "shape_rod").tip("Shape for making Rods");
    public static ItemBasic<?> ShapeBolt = new ItemBasic<>(Ref.ID, "shape_bolt").tip("Shape for making Boltss");
    public static ItemBasic<?> ShapeRing = new ItemBasic<>(Ref.ID, "shape_ring").tip("Shape for making Rings");
    public static ItemBasic<?> ShapeCell = new ItemBasic<>(Ref.ID, "shape_cell").tip("Shape for making Cells");
    public static ItemBasic<?> ShapeIngot = new ItemBasic<>(Ref.ID, "shape_ingot").tip("Shape for making Ingots");
    public static ItemBasic<?> ShapeWire = new ItemBasic<>(Ref.ID, "shape_wire").tip("Shape for making Wires");
    public static ItemBasic<?> ShapeCasing = new ItemBasic<>(Ref.ID, "shape_casing").tip("Shape for making Item Casings - only usable with ic2");
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

    public static StoneType GRANITE_RED = new StoneType(Ref.ID, "red_granite", Materials.RedGranite, new Texture(Ref.ID, "block/stone/granite_red"), SoundType.STONE, true).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType GRANITE_BLACK = new StoneType(Ref.ID, "black_granite", Materials.BlackGranite, new Texture(Ref.ID, "block/stone/granite_black"), SoundType.STONE, true).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType MARBLE = new StoneType(Ref.ID, "marble", Materials.Marble, new Texture(Ref.ID, "block/stone/marble"), SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType BASALT = new StoneType(Ref.ID, "basalt", Materials.Basalt, new Texture(Ref.ID, "block/stone/basalt"), SoundType.STONE, true).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);

    public static StoneType KOMATIITE = new StoneType(Ref.ID, "komatiite", Materials.Komatiite, new Texture(Ref.ID, "block/stone/komatiite"), SoundType.STONE, true).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType LIMESTONE = new StoneType(Ref.ID, "limestone", Limestone, new Texture(Ref.ID, "block/stone/limestone"), SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType GREEN_SCHIST = new StoneType(Ref.ID, "green_schist", GreenSchist, new Texture(Ref.ID, "block/stone/green_schist"), SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType BLUE_SCHIST = new StoneType(Ref.ID, "blue_schist", BlueSchist, new Texture(Ref.ID, "block/stone/blue_schist"), SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType KIMBERLITE = new StoneType(Ref.ID, "kimberlite", Kimberlite, new Texture(Ref.ID, "block/stone/kimberlite"), SoundType.STONE, true).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType QUARTZITE = new StoneType(Ref.ID, "quartzite", Quartzite, new Texture(Ref.ID, "block/stone/quartzite"), SoundType.STONE, true).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType SALT = new StoneType(Ref.ID, "salt", Salt, new Texture(Ref.ID, "block/stone/salt"), SoundType.STONE, true).setHardnessAndResistance(1.0F,7.5F);
    public static StoneType ROCK_SALT = new StoneType(Ref.ID, "rock_salt", RockSalt, new Texture(Ref.ID, "block/stone/rock_salt"), SoundType.STONE, true).setHardnessAndResistance(1.0F,7.5F);


    public static final BlockConnectedCasing STANDARD_MACHINE_CASING = new BlockConnectedCasing(Ref.ID, "standard_machine_casing");
    public static final BlockConnectedCasing REINFORCED_MACHINE_CASING = new BlockConnectedCasing(Ref.ID, "reinforced_machine_casing");
    public static final BlockConnectedCasing ADVANCED_MACHINE_CASING = new BlockConnectedCasing(Ref.ID, "advanced_machine_casing");
    public static final BlockConnectedCasing TUNGSTENSTEEL_REINFORCED_STONE = new BlockConnectedCasing(Ref.ID, "tungstensteel_reinforced_stone");
    public static final BlockConnectedCasing IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE = new BlockConnectedCasing(Ref.ID, "iridium_tungstensteel_reinforced_stone");
    public static final BlockCasing HIGHLY_ADVANCED_MACHINE_BLOCK = new BlockCasing(Ref.ID, "highly_advanced_machine_block");
    public static final BlockCasing ADVANCED_MACHINE_BLOCK = new BlockCasing(Ref.ID, "advanced_machine_block");

    public static final BlockCasing FIRE_BRICKS = new BlockCasing(Ref.ID, "fire_bricks");
    public static final BlockCasing REINFORCED_GLASS = new BlockCasing(Ref.ID, "reinforced_glass", Block.Properties.create(net.minecraft.block.material.Material.GLASS).hardnessAndResistance(15.0f, 150.0f).sound(SoundType.GLASS).setRequiresTool().harvestLevel(2).notSolid().setAllowsSpawn(GT4RData::neverAllowSpawn).setOpaque(GT4RData::isntSolid).setSuffocates(GT4RData::isntSolid).setBlocksVision(GT4RData::isntSolid));
    public static final BlockCasing REINFORCED_STONE = new BlockCasing(Ref.ID, "reinforced_stone", Block.Properties.create(net.minecraft.block.material.Material.ROCK).hardnessAndResistance(80.0f, 150.0f).sound(SoundType.STONE).setRequiresTool().harvestLevel(2));
    public static final BlockCasing IRIDIUM_REINFORCED_STONE = new BlockCasing(Ref.ID, "iridium_reinforced_stone", Block.Properties.create(net.minecraft.block.material.Material.ROCK).hardnessAndResistance(80.0f, 150.0f).sound(SoundType.STONE).setRequiresTool().harvestLevel(2));

    public static final BlockCasing FUSION_COIL = new BlockCasing(Ref.ID, "fusion_coil");

    public static final BlockSapBag SAP_BAG = new BlockSapBag();

    public static final Cable<?> CABLE_LEAD = new Cable<>(Ref.ID, Lead, 2, Tier.LV).amps(2);
    public static final Cable<?> CABLE_TIN = new Cable<>(Ref.ID, Tin, 1, Tier.LV).amps(1);
    public static final Cable<?> CABLE_COPPER = new Cable<>(Ref.ID, Copper, 2, Tier.MV).amps(1);
    public static final Cable<?> CABLE_NICKEL = new Cable<>(Ref.ID, Nickel, 3, Tier.MV).amps(3);
    public static final Cable<?> CABLE_GOLD = new Cable<>(Ref.ID, Gold, 2, Tier.HV).amps(3);
    public static final Cable<?> CABLE_ELECTRUM = new Cable<>(Ref.ID, Electrum, 2, Tier.HV).amps(2);
    public static final Cable<?> CABLE_STEEL = new Cable<>(Ref.ID, Steel, 2, Tier.EV).amps(2);
    public static final Cable<?> CABLE_ALUMINIUM = new Cable<>(Ref.ID, Aluminium, 1, Tier.EV).amps(1);
    public static final Cable<?> CABLE_OSMIUM = new Cable<>(Ref.ID, Osmium, 2, Tier.IV).amps(4);
    public static final Cable<?> CABLE_TUNGSTEN = new Cable<>(Ref.ID, Tungsten, 2, Tier.IV).amps(2);

    public static final Wire<?> WIRE_LEAD = new Wire<>(Ref.ID, Lead, 4, Tier.LV).amps(2);
    public static final Wire<?> WIRE_TIN = new Wire<>(Ref.ID, Tin, 2, Tier.LV).amps(1);
    public static final Wire<?> WIRE_COPPER = new Wire<>(Ref.ID, Copper, 4, Tier.MV).amps(1);
    public static final Wire<?> WIRE_NICKEL = new Wire<>(Ref.ID, Nickel, 6, Tier.MV).amps(3);
    public static final Wire<?> WIRE_GOLD = new Wire<>(Ref.ID, Gold, 6, Tier.HV).amps(3);
    public static final Wire<?> WIRE_ELECTRUM = new Wire<>(Ref.ID, Electrum, 4, Tier.HV).amps(2);
    public static final Wire<?> WIRE_STEEL = new Wire<>(Ref.ID, Steel, 4, Tier.EV).amps(2);
    public static final Wire<?> WIRE_ALUMINIUM = new Wire<>(Ref.ID, Aluminium, 1, Tier.EV).amps(1);
    public static final Wire<?> WIRE_OSMIUM = new Wire<>(Ref.ID, Osmium, 4, Tier.IV).amps(4);
    public static final Wire<?> WIRE_TUNGSTEN = new Wire<>(Ref.ID, Tungsten, 4, Tier.IV).amps(2);
    public static final Wire<?> WIRE_SUPERCONDUCTOR = new Wire<>(Ref.ID, Superconductor, 0, Tier.MAX).amps(4); //MAX

    public static final FluidPipe<?> FLUID_PIPE_WOOD = new FluidPipe<>(Ref.ID, Wood, 350, false).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(150));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = new FluidPipe<>(Ref.ID, Copper, 1696, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(300));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = new FluidPipe<>(Ref.ID, Bronze, 1696, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(450));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = new FluidPipe<>(Ref.ID, Invar, 2395, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(600));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = new FluidPipe<>(Ref.ID, Steel, 2557, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(600));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = new FluidPipe<>(Ref.ID, StainlessSteel, 2428, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(750));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = new FluidPipe<>(Ref.ID, Netherite, 2807, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(900));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = new FluidPipe<>(Ref.ID, Tungsten, 4618, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(1050));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = new FluidPipe<>(Ref.ID, TungstenSteel, 3587, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(1200));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = new FluidPipe<>(Ref.ID, TungstenCarbide, 3837, true).sizes(PipeSize.TINY, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(1).pressures(getPressures(1350));
    public static final FluidPipe<?> FLUID_PIPE_HP = new FluidPipe<>(Ref.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000);

    public static final ItemPipe<?> ITEM_PIPE_BRASS = new ItemPipe<>(Ref.ID, Brass).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 1, 2, 4);
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = new ItemPipe<>(Ref.ID, Cupronickel).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 1, 2, 4);
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = new ItemPipe<>(Ref.ID, Electrum).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 2, 4, 8);
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = new ItemPipe<>(Ref.ID, Magnalium).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 2, 4, 8);
    public static final ItemPipe<?> ITEM_PIPE_WROUGHT_IRON = new ItemPipe<>(Ref.ID, WroughtIron).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 2, 3, 4);
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = new ItemPipe<>(Ref.ID, Platinum).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 4, 8, 16);
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = new ItemPipe<>(Ref.ID, Osmium).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 8, 16, 32);
    public static final ItemPipe<?> ITEM_PIPE_HC = new ItemPipe<>(Ref.ID, HighCapacity).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(64);
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = new ItemPipe<>(Ref.ID, Osmiridium).sizes(PipeSize.NORMAL, PipeSize.LARGE, PipeSize.HUGE).caps(0, 0, 0, 32, 64, 128);

    // Rubber Tree
    public static final BlockRubberLeaves RUBBER_LEAVES = new BlockRubberLeaves(Ref.ID, "rubber_leaves");
    public static final BlockRubberLog RUBBER_LOG = new BlockRubberLog(Ref.ID, "rubber_log");
    public static final BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling(Ref.ID, "rubber_sapling");

    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Integer, Item> INT_CIRCUITS_ITEMS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static ImmutableMap<Tier, Wire<?>> TIER_WIRES;
    public static ImmutableMap<Tier, Cable<?>> TIER_CABLES;

    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Item> TIER_BATTERIES;
    public static ImmutableMap<Tier, FluidPipe> TIER_PIPES;

    public static Boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    private static int[] getPressures(int basePressure){
        basePressure *= 20;
        return new int[]{basePressure / 6, basePressure / 6, basePressure / 3, basePressure, basePressure * 2, basePressure * 4};
    }
}
