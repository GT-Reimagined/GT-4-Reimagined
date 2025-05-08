package org.gtreimagined.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.GT4RBlocks;
import org.gtreimagined.gt4r.data.Machines;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Iron;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Redstone;
import static org.gtreimagined.gtlib.machine.Tier.MV;
import static org.gtreimagined.gtlib.util.TagUtils.getForgelikeItemTag;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.GT4RItems.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;
import static org.gtreimagined.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_energy_flow", "parts",
                new ItemStack(CircuitEnergyFlow, 4), of('C', CIRCUITS_ADVANCED, 'T', GTMaterialTypes.PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_data_control", "parts",
                new ItemStack(CircuitDataControl, 4), of('C', CIRCUITS_ADVANCED, 'c', CIRCUITS_DATA, 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, GT4RRef.ID, "comp_monitor", "parts",
                new ItemStack(ComputerMonitor, 1), of2('A', GTMaterialTypes.PLATE.getMaterialTag(Aluminium), 'G', Tags.Items.GLASS_PANES, 'g', Tags.Items.DYES_GREEN, 'R', Tags.Items.DYES_RED, 'B', Tags.Items.DYES_BLUE, 'D', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, GT4RRef.ID, "conv_module", "parts",
                new ItemStack(ConveyorModule, 1), of('A', PLATES_IRON_ALUMINIUM, 'G', Tags.Items.GLASS, 'B', BATTERIES_LV, 'C', CIRCUITS_BASIC), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, GT4RRef.ID, "drain_expensive", "parts",
                new ItemStack(Drain, 1), of('A', PLATES_IRON_ALUMINIUM, 'B', Items.IRON_BARS), "ABA", "B B", "ABA");
        provider.addStackRecipe(output, GT4RRef.ID, "sawblade", "parts",
                new ItemStack(DiamondSawBlade, 4), of('A', GTMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'D', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Diamond)), "DAD", "A A", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "d_grindhead", "parts",
                new ItemStack(DiamondGrindHead, 4), of('A', PLATES_STEELS, 'D', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Diamond), 'G', GTMaterialTypes.GEM.getMaterialTag(GTLibMaterials.Diamond)), "DAD", "AGA", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "w_grindhead", "parts",
                new ItemStack(TungstenGrindHead, 4), of('S', PLATES_STEELS, 'T', GTMaterialTypes.PLATE.getMaterialTag(Tungsten), 'B', GTMaterialTypes.BLOCK.getMaterialTag(Steel)), "TST", "SBS", "TST");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_h", "parts",
                new ItemStack(CircuitBasic, 1), of('C', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', GTMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', GTMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CCC", "RIR", "CCC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_v", "parts",
                new ItemStack(CircuitBasic, 1), of('C', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', GTMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', GTMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CRC", "CIC", "CRC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_h", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', GTMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Glowstone)), "RGR", "LCL", "RGR");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_v", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', GTMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Glowstone)), "RLR", "GCG", "RLR");
        provider.shapeless(output, "mesh_carbon", "parts", new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
        provider.addItemRecipe(output, GT4RRef.ID, "re_battery", "parts",
                REBattery, of('T', GTMaterialTypes.PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'R', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone)), " C ", "TRT", "TRT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_lead_acid", "parts",
                new ItemStack(REBattery, 2), of('T', PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TLT", "TAT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_acid_lead", "parts",
                new ItemStack(REBattery, 2), of('T', PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TAT", "TLT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_alloy_lead_acid", "parts",
                new ItemStack(REBattery, 3), of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TLT", "TAT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_alloy_acid_lead", "parts",
                new ItemStack(REBattery, 3), of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TAT", "TLT");
        provider.addItemRecipe(output, GT4RRef.ID, "small_battery_hull", "parts",
                BatteryHull, of('T', GTMaterialTypes.PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY)), "C", "T", "T");
        provider.addItemRecipe(output, GT4RRef.ID, "shape_empty", "parts",
                EmptyShape, of('F', GTTools.FILE.getTag(), 'H', GTTools.HAMMER.getTag(), 'S', GTMaterialTypes.PLATE.getMaterialTag(Steel)), "HF", "SS", "SS");
        provider.addItemRecipe(output, GT4RRef.ID, "plate_mold", "parts",
                MoldPlate, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "H", "P");
        provider.addItemRecipe(output, GT4RRef.ID, "casing_mold", "parts",
                MoldCasing, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), " H", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "gear_mold", "parts",
                MoldGear, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "PH");
        provider.addItemRecipe(output, GT4RRef.ID, "bottle_mold", "parts",
                MoldBottle, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "P ", " H");
        provider.addItemRecipe(output, GT4RRef.ID, "coinage_mold", "parts",
                MoldCoinage, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "H ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_mold", "parts",
                MoldIngot, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "P", "H");
        provider.addItemRecipe(output, GT4RRef.ID, "block_mold", "parts",
                MoldBlock, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), "HP");
        provider.addItemRecipe(output, GT4RRef.ID, "nugget_mold", "parts",
                MoldNugget, of('H', GTTools.HAMMER.getTag(), 'P', EmptyShape), " P", "H ");
        provider.addItemRecipe(output, GT4RRef.ID, "plate_shape", "parts",
                ShapePlate, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "WP");
        provider.addItemRecipe(output, GT4RRef.ID, "rod_shape", "parts",
                ShapeRod, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", " W");
        provider.addItemRecipe(output, GT4RRef.ID, "bolt_shape", "parts",
                ShapeBolt, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "ring_shape", "parts",
                ShapeRing, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "PW");
        provider.addItemRecipe(output, GT4RRef.ID, "cell_shape", "parts",
                ShapeCell, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", "P");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_shape", "parts",
                ShapeIngot, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "wire_shape", "parts",
                ShapeWire, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", "W");
        provider.addItemRecipe(output, GT4RRef.ID, "casing_shape", "parts",
                ShapeCasing, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "W ");
        provider.addItemRecipe(output, GT4RRef.ID, "tiny_pipe_shape", "parts",
                ShapePipeTiny, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "  ", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "small_pipe_shape", "parts",
                ShapePipeSmall, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "  W");
        provider.addItemRecipe(output, GT4RRef.ID, "normal_pipe_shape", "parts",
                ShapePipeNormal, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", "  ", " W");
        provider.addItemRecipe(output, GT4RRef.ID, "large_pipe_shape", "parts",
                ShapePipeLarge, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "   ", "  W");
        provider.addItemRecipe(output, GT4RRef.ID, "huge_pipe_shape", "parts",
                ShapePipeHuge, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "   ", "P  ");
        provider.addItemRecipe(output, GT4RRef.ID, "block_shape", "parts",
                ShapeBlock, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P W");
        provider.addItemRecipe(output, GT4RRef.ID, "sword_head_shape", "parts",
                ShapeBladeSword, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", " ", "W");
        provider.addItemRecipe(output, GT4RRef.ID, "pickaxe_head_shape", "parts",
                ShapeHeadPickaxe, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "  ", "W ");
        provider.addItemRecipe(output, GT4RRef.ID, "shovel_head_shape", "parts",
                ShapeHeadShovel, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "   ", "W  ");
        provider.addItemRecipe(output, GT4RRef.ID, "axe_head_shape", "parts",
                ShapeHeadAxe, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "W  ");
        provider.addItemRecipe(output, GT4RRef.ID, "hoe_head_shape", "parts",
                ShapeHeadHoe, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W P");
        provider.addItemRecipe(output, GT4RRef.ID, "hammer_head_shape", "parts",
                ShapeHeadHammer, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "  P");
        provider.addItemRecipe(output, GT4RRef.ID, "file_head_shape", "parts",
                ShapeHeadFile, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "   ", "  P");
        provider.addItemRecipe(output, GT4RRef.ID, "saw_head_shape", "parts",
                ShapeBladeSaw, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", "  ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "gear_shape", "parts",
                ShapeGear, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", " ", "P");
        /*provider.addItemRecipe(output, GT4RRef.ID, "bottle_shape", "parts", "has_wire_cutter", provider.hasSafeItem(GTTools.WIRE_CUTTER.getTag()),
                ShapeBottle, of('W', GTTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "P  ");*/
        if (GT4RConfig.HARDER_ENERGY_CRYSTAL.get()){
            provider.addStackRecipe(output, GT4RRef.ID, "energium_dust", "parts",
                    GTMaterialTypes.DUST.get(Energium, 9), of('R', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), 'D', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Diamond)), "RDR", "DRD", "RDR");
            provider.addStackRecipe(output, GT4RRef.ID, "energium_dust2", "parts",
                    GTMaterialTypes.DUST.get(Energium, 9), of('R', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), 'D', GTMaterialTypes.DUST.getMaterialTag(Ruby)), "RDR", "DRD", "RDR");
        } else {
            provider.addItemRecipe(output, "parts", EnergyCrystal,
                    of('R', DUST.getMaterialTag(Redstone), 'G', GEMS_DIAMOND_RUBY), "RRR", "RGR", "RRR");
        }


        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron2", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', GTMaterialTypes.GEM.getMaterialTag(Sapphire)), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "orb_lapotron", "parts",
                LapotronicEnergyOrb, of('L', LapotronCrystal, 'I', IridiumReinforcedPlate), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "coil_copper", "parts",
                CopperCoil, of('L', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', GTMaterialTypes.INGOT.getMaterialTag(GTLibMaterials.Iron)), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_iridium_alloy", "parts",
                IridiumAlloyIngot, of('I', GTMaterialTypes.PLATE.getMaterialTag(Iridium), 'D', GTMaterialTypes.GEM.getMaterialTag(GTLibMaterials.Diamond), 'A', AdvancedAlloy), "IAI", "ADA", "IAI");
        provider.addItemRecipe(output, GT4RRef.ID, "freq_transmitter", "parts", FrequencyTransmitter, of('C', CIRCUITS_BASIC, 'c', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "Cc");
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_steel_ingot_2", "parts", GTMaterialTypes.INGOT.get(SteelMagnetic), of('R', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), 'I', GTMaterialTypes.INGOT.getMaterialTag(Steel)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_steel_rod", "parts", GTMaterialTypes.ROD.get(SteelMagnetic, 1), GTMaterialTypes.ROD.getMaterialTag(Steel), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone));
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_iron_ingot_2", "parts", GTMaterialTypes.INGOT.get(IronMagnetic), of('R', GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), 'I', GTMaterialTypes.INGOT.getMaterialTag(GTLibMaterials.Iron)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_iron_rod", "parts", GTMaterialTypes.ROD.get(IronMagnetic, 1), GTMaterialTypes.ROD.getMaterialTag(GTLibMaterials.Iron), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone), GTMaterialTypes.DUST.getMaterialTag(GTLibMaterials.Redstone));
        provider.shapeless(output, "fire_clay_dust", "parts", GTMaterialTypes.DUST.get(Fireclay, 2), GTMaterialTypes.DUST.getMaterialTag(Brick), GTMaterialTypes.DUST.getMaterialTag(Clay));
        provider.shapeless(output, "iron_ingot_from_wrought", "parts", new ItemStack(Items.IRON_INGOT), GTMaterialTypes.DUST.getMaterialTag(Ash), GTMaterialTypes.INGOT.getMaterialTag(WroughtIron));
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor_wire", "parts",
                new ItemStack(GT4RBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.NORMAL), 4), of('M', MACHINE_HULLS_ADVANCED, 'S', ItemSuperconductor, 'C', CIRCUITS_MASTER), "MCM", "SSS", "MCM");
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor", "parts",
                new ItemStack(ItemSuperconductor, 4), of('H', RecipeIngredient.of(Helium.getCellGas(1, CellTin)), 'T', GTMaterialTypes.PLATE.getMaterialTag(Tungsten), 'I', IridiumReinforcedPlate, 'C', CIRCUITS_MASTER), "HHH", "TIT", "CCC");
        provider.shapeless(output, GT4RRef.ID, "bronze_dust",
                GTMaterialTypes.DUST.get(Bronze, 4), GTMaterialTypes.DUST.get(GTLibMaterials.Copper), GTMaterialTypes.DUST.get(GTLibMaterials.Copper), GTMaterialTypes.DUST.get(GTLibMaterials.Copper), GTMaterialTypes.DUST.get(Tin));
        provider.addItemRecipe(output, GT4RRef.ID, "selector_tag", "parts",
                SELECTOR_TAG_ITEMS.get(0), of('R', GTMaterialTypes.ROD.getMaterialTag(GTLibMaterials.Iron), 'P', GTMaterialTypes.PLATE.getMaterialTag(GTLibMaterials.Iron), 'H', GTTools.HAMMER.getTag(), 'W', GTTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, GT4RRef.ID, "selector_tag_1", "parts",
                SELECTOR_TAG_ITEMS.get(0), of('R', GTMaterialTypes.ROD.getMaterialTag(WroughtIron), 'P', GTMaterialTypes.PLATE.getMaterialTag(WroughtIron), 'H', GTTools.HAMMER.getTag(), 'W', GTTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()){
            provider.addItemRecipe(output, GT4RRef.ID, "motor_lv", "parts",
                    MotorLV, of('T', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', GTMaterialTypes.ROD.getMaterialTag(Steel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
            provider.addItemRecipe(output, GT4RRef.ID, "motor_mv", "parts",
                    MotorMV, of('T', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.TINY), 'I', GTMaterialTypes.ROD.getMaterialTag(Aluminium), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
            provider.addItemRecipe(output, GT4RRef.ID, "motor_hv", "parts",
                    MotorHV, of('T', GT4RBlocks.CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.SMALL), 'I', GTMaterialTypes.ROD.getMaterialTag(StainlessSteel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        } else {
            ImmutableMap<Character, Object> map = of('C', CopperCoil, 'T', PLATE.getMaterialTag(Tin), 'I', INGOT.getMaterialTag(Iron));
            provider.addItemRecipe(output, GT4RRef.ID, "motor", "parts", MotorLV, map, " T ", "CIC", " T ");
            provider.addItemRecipe(output, GT4RRef.ID, "motor_alt", "parts", MotorLV, map, " C ", "TIT", " C ");
        }
        provider.shapeless(output, "match_r", "parts", new ItemStack(Match, 4), GTMaterialTypes.DUST.getMaterialTag(Phosphor), getForgelikeItemTag("rods/wooden"));
        provider.addStackRecipe(output, GT4RRef.ID, "fluid_cell", "parts",
                new ItemStack(CellTin, 2), of('T', GTMaterialTypes.PLATE.getMaterialTag(Tin)), " T ", "T T", " T ");
        provider.shapeless(output, "resin_torch", "parts", new ItemStack(Items.TORCH, 4), StickyResin, getForgelikeItemTag("rods/wooden"));
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_iridium", "parts",
                IridiumNeutronReflector, of('N', ThickNeutronReflector, 'I', IridiumReinforcedPlate), "NNN", "NIN", "NNN");
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_thick", "parts",
                ThickNeutronReflector, of('N', NeutronReflector, 'I', GTMaterialTypes.DUST.get(Beryllium)), " N ", "NIN", " N ");
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_normal", "parts",
                NeutronReflector, of('C', GTMaterialTypes.DUST.get(GTLibMaterials.Coal), 'T', GTMaterialTypes.DUST.get(Tin), 'I', GTMaterialTypes.PLATE.get(GTLibMaterials.Copper)), "TCT", "CIC", "TCT");
        provider.addItemRecipe(output, "upgrades", TransformerUpgrade,
                of('G', Tags.Items.GLASS, 'C', GT4RBlocks.CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'T', Machines.TRANSFORMER.getItem(MV), 'c', CIRCUITS_BASIC), "GGG", "CTC", "GcG");
        provider.addItemRecipe(output, "upgrades", SteelUpgrade, of('S', PLATE.getMaterialTag(Steel), 'B', PLATE.getMaterialTag(Bronze)), "SSS", "SBS");
        provider.shapeless(output, GT4RRef.ID, "tape_from_empty", "tapes", new ItemStack(Tape), TapeEmpty, TapeEmpty, TapeEmpty, TapeEmpty);
        provider.shapeless(output, GT4RRef.ID, "duct_tape_from_empty", "tapes", new ItemStack(DuctTape), DuctTapeEmpty, DuctTapeEmpty, DuctTapeEmpty, DuctTapeEmpty);
        provider.shapeless(output, GT4RRef.ID, "fal_duct_tape_from_empty", "tapes", new ItemStack(FALDuctTape), FALDuctTapeEmpty, FALDuctTapeEmpty, FALDuctTapeEmpty, FALDuctTapeEmpty);
        provider.addItemRecipe(output, GT4RRef.ID, "", "tapes", Tape, of('P', Items.PAPER, 'G', StickyResin), "PPP", " G ");
        provider.addItemRecipe(output, GT4RRef.ID, "", "tapes", DuctTape, of('P', FOIL.getMaterialTag(Plastic), 'G', StickyResin), "PPP", " G ");
        provider.addItemRecipe(output, GT4RRef.ID, "", "tapes", FALDuctTape, of('P', FOIL.getMaterialTag(Tungsten), 'G', StickyResin), "PPP", " G ");
        provider.addItemRecipe(output, "hazmat", UniversalHazardSuitMask, of('L', PLATE.getMaterialTag(Lead), 'A', PLATE.getMaterialTag(Aluminium), 'C', Items.CHAINMAIL_HELMET, 'G', Items.GLASS_PANE), "ALA", "LCL", "AGA");
        provider.addItemRecipe(output, "hazmat", UniversalHazardSuitShirt, of('L', PLATE.getMaterialTag(Lead), 'A', PLATE.getMaterialTag(Aluminium), 'C', Items.CHAINMAIL_CHESTPLATE), "ALA", "LCL", "ALA");
        provider.addItemRecipe(output, "hazmat", UniversalHazardSuitPants, of('L', PLATE.getMaterialTag(Lead), 'A', PLATE.getMaterialTag(Aluminium), 'C', Items.CHAINMAIL_LEGGINGS), "ALA", "LCL", "ALA");
        provider.addItemRecipe(output, "hazmat", UniversalHazardSuitBoots, of('L', PLATE.getMaterialTag(Lead), 'A', PLATE.getMaterialTag(Aluminium), 'C', Items.CHAINMAIL_BOOTS), "ALA", "LCL", "ALA");
        loadMixedMetal(output, provider);
    }

    public static void loadMixedMetal(Consumer<FinishedRecipe> consumer, GTRecipeProvider provider){
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Tin, 1);
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Tin, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Tin, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Tin, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Tin, 2);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Tin, 2);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Tin, 2);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Tin, 2);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Tin, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Zinc, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Aluminium, 6);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Tin, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Zinc, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Aluminium, 6);
    }

    public static void mixedMetalRecipe(Consumer<FinishedRecipe> consumer, GTRecipeProvider provider, Material top, Material middle, Material bottom, int amount){
        provider.addStackRecipe(consumer, GT4RRef.ID, "mixed_metal_from_" + top.getId() + "_" + middle.getId() + "_" + bottom.getId(), "mixed_metal", Utils.ca(amount, GTCoreItems.MixedMetalIngot.getMixedMetalIngot(top, middle, bottom)),
                of('T', PLATE.getMaterialTag(top), 'M', PLATE.getMaterialTag(middle), 'B', PLATE.getMaterialTag(bottom)), "T", "M", "B");
    }
}
