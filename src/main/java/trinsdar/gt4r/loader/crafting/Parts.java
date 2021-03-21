package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.PUMP;
import static trinsdar.gt4r.data.Machines.VACUUM_FREEZER;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, Ref.ID, "circuit_energy_flow", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitEnergyFlow, 4), of('C', CIRCUITS_ADVANCED, 'T', PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, Ref.ID, "circuit_data_control", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitDataControl, 4), of('C', CIRCUITS_ADVANCED, 'c', CIRCUITS_DATA, 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, Ref.ID, "comp_monitor", "parts", "has_glass_pane", provider.hasSafeItem(Tags.Items.GLASS_PANES),
                new ItemStack(ComputerMonitor, 1), of2('A', PLATE.getMaterialTag(Aluminium), 'G', Tags.Items.GLASS_PANES, 'g', Tags.Items.DYES_GREEN, 'R', Tags.Items.DYES_RED, 'B', Tags.Items.DYES_BLUE, 'D', DUST.getMaterialTag(Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, Ref.ID, "conv_module", "parts", "has_battery", provider.hasSafeItem(BatteryRE),
                new ItemStack(ConveyorModule, 1), of('A', PLATES_IRON_ALUMINIUM, 'G', Tags.Items.GLASS, 'B', BatteryRE, 'C', CIRCUITS_BASIC), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, Ref.ID, "sawblade", "parts", "has_diamond_dust", provider.hasSafeItem(DUST.getMaterialTag(Diamond)),
                new ItemStack(DiamondSawBlade, 4), of('A', PLATE.getMaterialTag(StainlessSteel), 'D', DUST.getMaterialTag(Diamond)), "DAD", "A A", "DAD");
        provider.addStackRecipe(output, Ref.ID, "d_grindhead", "parts", "has_diamond", provider.hasSafeItem(GEM.getMaterialTag(Diamond)),
                new ItemStack(DiamondGrindHead, 4), of('A', PLATES_STEELS, 'D', DUST.getMaterialTag(Diamond), 'G', GEM.getMaterialTag(Diamond)), "DAD", "AGA", "DAD");
        provider.addStackRecipe(output, Ref.ID, "w_grindhead", "parts", "has_steel_block", provider.hasSafeItem(BLOCK.getMaterialTag(Steel)),
                new ItemStack(TungstenGrindHead, 4), of('S', PLATES_STEELS, 'T', PLATE.getMaterialTag(Tungsten), 'B', BLOCK.getMaterialTag(Steel)), "TST", "SBS", "TST");
        provider.addStackRecipe(output, Ref.ID, "circuit_basic_h", "parts", "has_copper_cable", provider.hasSafeItem(CABLE_COPPER.getBlockItem(PipeSize.VTINY)),
                new ItemStack(CircuitBasic, 1), of('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', PLATE.getMaterialTag(RedAlloy), 'I', PLATE.getMaterialTag(WroughtIron)), "CCC", "RIR", "CCC");
        provider.addStackRecipe(output, Ref.ID, "circuit_basic_v", "parts", "has_copper_cable", provider.hasSafeItem(CABLE_COPPER.getBlockItem(PipeSize.VTINY)),
                new ItemStack(CircuitBasic, 1), of('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', PLATE.getMaterialTag(RedAlloy), 'I', PLATE.getMaterialTag(WroughtIron)), "CRC", "CIC", "CRC");
        provider.addStackRecipe(output, Ref.ID, "circuit_advanced_h", "parts", "has_basic_circuit", provider.hasSafeItem(CIRCUITS_BASIC),
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', PLATE.getMaterialTag(RedAlloy), 'L', getForgeItemTag("dusts/lapislaz"), 'G', DUST.getMaterialTag(Glowstone)), "RGR", "LCL", "RGR");
        provider.addStackRecipe(output, Ref.ID, "circuit_advanced_v", "parts", "has_basic_circuit", provider.hasSafeItem(CIRCUITS_BASIC),
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', PLATE.getMaterialTag(RedAlloy), 'L', getForgeItemTag("dusts/lapislaz"), 'G', DUST.getMaterialTag(Glowstone)), "RLR", "GCG", "RLR");
        provider.shapeless(output, "mesh_carbon", "parts", "has_carbon_fibre", provider.hasSafeItem(CarbonFibre), new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
        provider.addItemRecipe(output, Ref.ID, "re_battery", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_TIN.getBlockItem(PipeSize.VTINY)),
                BatteryRE, of('T', PLATE.getMaterialTag(Tin), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'R', DUST.getMaterialTag(Redstone)), " C ", "TRT", "TRT");
        provider.addItemRecipe(output, Ref.ID, "small_battery_hull", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_TIN.getBlockItem(PipeSize.VTINY)),
                BatteryHullSmall, of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY)), "C", "T", "T");
        provider.addItemRecipe(output, Ref.ID, "medium_battery_hull", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_COPPER.getBlockItem(PipeSize.VTINY)),
                BatteryHullMedium, of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "C C", "TTT", "TTT");
        provider.addItemRecipe(output, Ref.ID, "shape_empty", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                EmptyShape, of('F', FILE.getTag(), 'H', HAMMER.getTag(), 'S', PLATE.getMaterialTag(Steel)), "HF", "SS", "SS");
        provider.addItemRecipe(output, Ref.ID, "plate_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldPlate, of('H', HAMMER.getTag(), 'P', EmptyShape), "H", "P");
        provider.addItemRecipe(output, Ref.ID, "gear_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldGear, of('H', HAMMER.getTag(), 'P', EmptyShape), "PH");
        provider.addItemRecipe(output, Ref.ID, "bottle_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldBottle, of('H', HAMMER.getTag(), 'P', EmptyShape), "P ", " H");
        provider.addItemRecipe(output, Ref.ID, "coinage_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldCoinage, of('H', HAMMER.getTag(), 'P', EmptyShape), "H ", " P");
        provider.addItemRecipe(output, Ref.ID, "ingot_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldIngot, of('H', HAMMER.getTag(), 'P', EmptyShape), "P", "H");
        provider.addItemRecipe(output, Ref.ID, "block_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldBlock, of('H', HAMMER.getTag(), 'P', EmptyShape), "HP");
        provider.addItemRecipe(output, Ref.ID, "nugget_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldNugget, of('H', HAMMER.getTag(), 'P', EmptyShape), " P", "H ");
        provider.addItemRecipe(output, Ref.ID, "plate_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePlate, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "WP");
        provider.addItemRecipe(output, Ref.ID, "rod_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeRod, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", " W");
        provider.addItemRecipe(output, Ref.ID, "cell_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeCell, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", "P");
        provider.addItemRecipe(output, Ref.ID, "ingot_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeIngot, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "P ");
        provider.addItemRecipe(output, Ref.ID, "wire_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeWire, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", "W");
        provider.addItemRecipe(output, Ref.ID, "casing_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeCasing, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "W ");
        provider.addItemRecipe(output, Ref.ID, "tiny_pipe_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePipeTiny, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), " W", "  ", "P ");
        provider.addItemRecipe(output, Ref.ID, "small_pipe_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePipeSmall, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "  W");
        provider.addItemRecipe(output, Ref.ID, "normal_pipe_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePipeNormal, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P ", "  ", " W");
        provider.addItemRecipe(output, Ref.ID, "large_pipe_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePipeLarge, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P  ", "   ", "  W");
        provider.addItemRecipe(output, Ref.ID, "huge_pipe_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapePipeHuge, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "   ", "P  ");
        provider.addItemRecipe(output, Ref.ID, "block_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeBlock, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P W");
        provider.addItemRecipe(output, Ref.ID, "sword_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadSword, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "P", " ", "W");
        provider.addItemRecipe(output, Ref.ID, "pickaxe_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadPickaxe, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), " P", "  ", "W ");
        provider.addItemRecipe(output, Ref.ID, "shovel_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadShovel, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "   ", "W  ");
        provider.addItemRecipe(output, Ref.ID, "axe_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadAxe, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "  P", "W  ");
        provider.addItemRecipe(output, Ref.ID, "hoe_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadHoe, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W P");
        provider.addItemRecipe(output, Ref.ID, "hammer_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadHammer, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "  P");
        provider.addItemRecipe(output, Ref.ID, "file_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadFile, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W  ", "   ", "  P");
        provider.addItemRecipe(output, Ref.ID, "saw_head_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeHeadSaw, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", "  ", " P");
        provider.addItemRecipe(output, Ref.ID, "gear_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeGear, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W", " ", "P");
        provider.addItemRecipe(output, Ref.ID, "bottle_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeBottle, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "  W", "P  ");
    }
}
