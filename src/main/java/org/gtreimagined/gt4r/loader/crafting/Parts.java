package org.gtreimagined.gt4r.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
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
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.data.AntimatterMaterials.Redstone;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.GT4RItems.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;
import static org.gtreimagined.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_energy_flow", "parts",
                new ItemStack(CircuitEnergyFlow, 4), of('C', CIRCUITS_ADVANCED, 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_data_control", "parts",
                new ItemStack(CircuitDataControl, 4), of('C', CIRCUITS_ADVANCED, 'c', CIRCUITS_DATA, 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, GT4RRef.ID, "comp_monitor", "parts",
                new ItemStack(ComputerMonitor, 1), of2('A', AntimatterMaterialTypes.PLATE.getMaterialTag(Aluminium), 'G', Tags.Items.GLASS_PANES, 'g', Tags.Items.DYES_GREEN, 'R', Tags.Items.DYES_RED, 'B', Tags.Items.DYES_BLUE, 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, GT4RRef.ID, "conv_module", "parts",
                new ItemStack(ConveyorModule, 1), of('A', PLATES_IRON_ALUMINIUM, 'G', Tags.Items.GLASS, 'B', BATTERIES_LV, 'C', CIRCUITS_BASIC), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, GT4RRef.ID, "drain_expensive", "parts",
                new ItemStack(Drain, 1), of('A', PLATES_IRON_ALUMINIUM, 'B', Items.IRON_BARS), "ABA", "B B", "ABA");
        provider.addStackRecipe(output, GT4RRef.ID, "sawblade", "parts",
                new ItemStack(DiamondSawBlade, 4), of('A', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond)), "DAD", "A A", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "d_grindhead", "parts",
                new ItemStack(DiamondGrindHead, 4), of('A', PLATES_STEELS, 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond), 'G', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)), "DAD", "AGA", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "w_grindhead", "parts",
                new ItemStack(TungstenGrindHead, 4), of('S', PLATES_STEELS, 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'B', AntimatterMaterialTypes.BLOCK.getMaterialTag(Steel)), "TST", "SBS", "TST");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_h", "parts",
                new ItemStack(CircuitBasic, 1), of('C', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CCC", "RIR", "CCC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_v", "parts",
                new ItemStack(CircuitBasic, 1), of('C', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CRC", "CIC", "CRC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_h", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "RGR", "LCL", "RGR");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_v", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "RLR", "GCG", "RLR");
        provider.shapeless(output, "mesh_carbon", "parts", new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
        provider.addItemRecipe(output, GT4RRef.ID, "re_battery", "parts",
                REBattery, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone)), " C ", "TRT", "TRT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_lead_acid", "parts",
                new ItemStack(REBattery, 2), of('T', PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TLT", "TAT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_acid_lead", "parts",
                new ItemStack(REBattery, 2), of('T', PLATE.getMaterialTag(Tin), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TAT", "TLT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_alloy_lead_acid", "parts",
                new ItemStack(REBattery, 3), of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TLT", "TAT");
        provider.addStackRecipe(output, GT4RRef.ID, "re_battery_alloy_acid_lead", "parts",
                new ItemStack(REBattery, 3), of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'L', DUST.getMaterialTag(Lead), 'A', SulfuricAcid.getLiquid(1).getFluid().getBucket()), " C ", "TAT", "TLT");
        provider.addItemRecipe(output, GT4RRef.ID, "small_battery_hull", "parts",
                BatteryHull, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(BatteryAlloy), 'C', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY)), "C", "T", "T");
        provider.addItemRecipe(output, GT4RRef.ID, "shape_empty", "parts",
                EmptyShape, of('F', AntimatterDefaultTools.FILE.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'S', AntimatterMaterialTypes.PLATE.getMaterialTag(Steel)), "HF", "SS", "SS");
        provider.addItemRecipe(output, GT4RRef.ID, "plate_mold", "parts",
                MoldPlate, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "H", "P");
        provider.addItemRecipe(output, GT4RRef.ID, "casing_mold", "parts",
                MoldCasing, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), " H", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "gear_mold", "parts",
                MoldGear, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "PH");
        provider.addItemRecipe(output, GT4RRef.ID, "bottle_mold", "parts",
                MoldBottle, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "P ", " H");
        provider.addItemRecipe(output, GT4RRef.ID, "coinage_mold", "parts",
                MoldCoinage, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "H ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_mold", "parts",
                MoldIngot, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "P", "H");
        provider.addItemRecipe(output, GT4RRef.ID, "block_mold", "parts",
                MoldBlock, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), "HP");
        provider.addItemRecipe(output, GT4RRef.ID, "nugget_mold", "parts",
                MoldNugget, of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', EmptyShape), " P", "H ");
        provider.addItemRecipe(output, GT4RRef.ID, "plate_shape", "parts",
                ShapePlate, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "WP");
        provider.addItemRecipe(output, GT4RRef.ID, "rod_shape", "parts",
                ShapeRod, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", " W");
        provider.addItemRecipe(output, GT4RRef.ID, "bolt_shape", "parts",
                ShapeBolt, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "ring_shape", "parts",
                ShapeRing, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "PW");
        provider.addItemRecipe(output, GT4RRef.ID, "cell_shape", "parts",
                ShapeCell, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", "P");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_shape", "parts",
                ShapeIngot, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "wire_shape", "parts",
                ShapeWire, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", "W");
        provider.addItemRecipe(output, GT4RRef.ID, "casing_shape", "parts",
                ShapeCasing, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "W ");
        provider.addItemRecipe(output, GT4RRef.ID, "tiny_pipe_shape", "parts",
                ShapePipeTiny, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "  ", "P ");
        provider.addItemRecipe(output, GT4RRef.ID, "small_pipe_shape", "parts",
                ShapePipeSmall, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "  W");
        provider.addItemRecipe(output, GT4RRef.ID, "normal_pipe_shape", "parts",
                ShapePipeNormal, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", "  ", " W");
        provider.addItemRecipe(output, GT4RRef.ID, "large_pipe_shape", "parts",
                ShapePipeLarge, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "   ", "  W");
        provider.addItemRecipe(output, GT4RRef.ID, "huge_pipe_shape", "parts",
                ShapePipeHuge, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "   ", "P  ");
        provider.addItemRecipe(output, GT4RRef.ID, "block_shape", "parts",
                ShapeBlock, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P W");
        provider.addItemRecipe(output, GT4RRef.ID, "sword_head_shape", "parts",
                ShapeBladeSword, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", " ", "W");
        provider.addItemRecipe(output, GT4RRef.ID, "pickaxe_head_shape", "parts",
                ShapeHeadPickaxe, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "  ", "W ");
        provider.addItemRecipe(output, GT4RRef.ID, "shovel_head_shape", "parts",
                ShapeHeadShovel, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "   ", "W  ");
        provider.addItemRecipe(output, GT4RRef.ID, "axe_head_shape", "parts",
                ShapeHeadAxe, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "W  ");
        provider.addItemRecipe(output, GT4RRef.ID, "hoe_head_shape", "parts",
                ShapeHeadHoe, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W P");
        provider.addItemRecipe(output, GT4RRef.ID, "hammer_head_shape", "parts",
                ShapeHeadHammer, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "  P");
        provider.addItemRecipe(output, GT4RRef.ID, "file_head_shape", "parts",
                ShapeHeadFile, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "   ", "  P");
        provider.addItemRecipe(output, GT4RRef.ID, "saw_head_shape", "parts",
                ShapeBladeSaw, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", "  ", " P");
        provider.addItemRecipe(output, GT4RRef.ID, "gear_shape", "parts",
                ShapeGear, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", " ", "P");
        /*provider.addItemRecipe(output, GT4RRef.ID, "bottle_shape", "parts", "has_wire_cutter", provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                ShapeBottle, of('W', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "P  ");*/
        if (GT4RConfig.HARDER_ENERGY_CRYSTAL.get()){
            provider.addStackRecipe(output, GT4RRef.ID, "energium_dust", "parts",
                    AntimatterMaterialTypes.DUST.get(Energium, 9), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond)), "RDR", "DRD", "RDR");
            provider.addStackRecipe(output, GT4RRef.ID, "energium_dust2", "parts",
                    AntimatterMaterialTypes.DUST.get(Energium, 9), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(Ruby)), "RDR", "DRD", "RDR");
        } else {
            provider.addItemRecipe(output, "parts", EnergyCrystal,
                    of('R', DUST.getMaterialTag(Redstone), 'G', GEMS_DIAMOND_RUBY), "RRR", "RGR", "RRR");
        }


        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron2", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', AntimatterMaterialTypes.GEM.getMaterialTag(Sapphire)), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "orb_lapotron", "parts",
                LapotronicEnergyOrb, of('L', LapotronCrystal, 'I', IridiumReinforcedPlate), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "coil_copper", "parts",
                CopperCoil, of('L', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron)), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_iridium_alloy", "parts",
                IridiumAlloyIngot, of('I', AntimatterMaterialTypes.PLATE.getMaterialTag(Iridium), 'D', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'A', AdvancedAlloy), "IAI", "ADA", "IAI");
        provider.addItemRecipe(output, GT4RRef.ID, "freq_transmitter", "parts", FrequencyTransmitter, of('C', CIRCUITS_BASIC, 'c', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "Cc");
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_steel_ingot_2", "parts", AntimatterMaterialTypes.INGOT.get(SteelMagnetic), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(Steel)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_steel_rod", "parts", AntimatterMaterialTypes.ROD.get(SteelMagnetic, 1), AntimatterMaterialTypes.ROD.getMaterialTag(Steel), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone));
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_iron_ingot_2", "parts", AntimatterMaterialTypes.INGOT.get(IronMagnetic), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_iron_rod", "parts", AntimatterMaterialTypes.ROD.get(IronMagnetic, 1), AntimatterMaterialTypes.ROD.getMaterialTag(AntimatterMaterials.Iron), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone));
        provider.shapeless(output, "fire_clay_dust", "parts", AntimatterMaterialTypes.DUST.get(Fireclay, 2), AntimatterMaterialTypes.DUST.getMaterialTag(Brick), AntimatterMaterialTypes.DUST.getMaterialTag(Clay));
        provider.shapeless(output, "iron_ingot_from_wrought", "parts", new ItemStack(Items.IRON_INGOT), AntimatterMaterialTypes.DUST.getMaterialTag(Ash), AntimatterMaterialTypes.INGOT.getMaterialTag(WroughtIron));
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor_wire", "parts",
                new ItemStack(GT4RBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.NORMAL), 4), of('M', MACHINE_HULLS_ADVANCED, 'S', ItemSuperconductor, 'C', CIRCUITS_MASTER), "MCM", "SSS", "MCM");
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor", "parts",
                new ItemStack(ItemSuperconductor, 4), of('H', RecipeIngredient.of(Helium.getCellGas(1, CellTin)), 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'I', IridiumReinforcedPlate, 'C', CIRCUITS_MASTER), "HHH", "TIT", "CCC");
        provider.shapeless(output, GT4RRef.ID, "bronze_dust",
                AntimatterMaterialTypes.DUST.get(Bronze, 4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(Tin));
        provider.addItemRecipe(output, GT4RRef.ID, "selector_tag", "parts",
                SELECTOR_TAG_ITEMS.get(0), of('R', AntimatterMaterialTypes.ROD.getMaterialTag(AntimatterMaterials.Iron), 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, GT4RRef.ID, "selector_tag_1", "parts",
                SELECTOR_TAG_ITEMS.get(0), of('R', AntimatterMaterialTypes.ROD.getMaterialTag(WroughtIron), 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_lv", "parts",
                MotorLV, of('T', GT4RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(Steel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_mv", "parts",
                MotorMV, of('T', GT4RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.TINY), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(Aluminium), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_hv", "parts",
                MotorHV, of('T', GT4RBlocks.CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'C', GT4RBlocks.WIRE_COPPER.getBlockItem(PipeSize.SMALL), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(StainlessSteel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.shapeless(output, "match_r", "parts", new ItemStack(Match, 4), AntimatterMaterialTypes.DUST.getMaterialTag(Phosphor), getForgelikeItemTag("rods/wooden"));
        provider.addStackRecipe(output, GT4RRef.ID, "fluid_cell", "parts",
                new ItemStack(CellTin, 2), of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tin)), " T ", "T T", " T ");
        provider.shapeless(output, "resin_torch", "parts", new ItemStack(Items.TORCH, 4), StickyResin, getForgelikeItemTag("rods/wooden"));
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_iridium", "parts",
                IridiumNeutronReflector, of('N', ThickNeutronReflector, 'I', IridiumReinforcedPlate), "NNN", "NIN", "NNN");
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_thick", "parts",
                ThickNeutronReflector, of('N', NeutronReflector, 'I', AntimatterMaterialTypes.DUST.get(Beryllium)), " N ", "NIN", " N ");
        provider.addItemRecipe(output, GT4RRef.ID, "neutron_reflector_normal", "parts",
                NeutronReflector, of('C', AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal), 'T', AntimatterMaterialTypes.DUST.get(Tin), 'I', AntimatterMaterialTypes.PLATE.get(AntimatterMaterials.Copper)), "TCT", "CIC", "TCT");
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

    public static void loadMixedMetal(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
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

    public static void mixedMetalRecipe(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider, Material top, Material middle, Material bottom, int amount){
        provider.addStackRecipe(consumer, GT4RRef.ID, "mixed_metal_from_" + top.getId() + "_" + middle.getId() + "_" + bottom.getId(), "mixed_metal", Utils.ca(amount, GTCoreItems.MixedMetalIngot.getMixedMetalIngot(top, middle, bottom)),
                of('T', PLATE.getMaterialTag(top), 'M', PLATE.getMaterialTag(middle), 'B', PLATE.getMaterialTag(bottom)), "T", "M", "B");
    }
}
