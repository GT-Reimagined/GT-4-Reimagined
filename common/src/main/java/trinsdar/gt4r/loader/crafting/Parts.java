package trinsdar.gt4r.loader.crafting;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import trinsdar.gt4r.Ref;
import muramasa.antimatter.data.ForgeCTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, Ref.ID, "circuit_energy_flow", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitEnergyFlow, 4), of('C', CIRCUITS_ADVANCED, 'T', PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, Ref.ID, "circuit_data_control", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(CircuitDataControl, 4), of('C', CIRCUITS_ADVANCED, 'c', CIRCUITS_DATA, 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, Ref.ID, "comp_monitor", "parts", "has_aluminium_plate", provider.hasSafeItem(PLATE.getMaterialTag(Aluminium)),
                new ItemStack(ComputerMonitor, 1), of2('A', PLATE.getMaterialTag(Aluminium), 'G', ForgeCTags.GLASS_PANES, 'g', ForgeCTags.DYES_GREEN, 'R', ForgeCTags.DYES_RED, 'B', ForgeCTags.DYES_BLUE, 'D', DUST.getMaterialTag(Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, Ref.ID, "conv_module", "parts", "has_battery", provider.hasSafeItem(BatteryRE),
                new ItemStack(ConveyorModule, 1), of('A', PLATES_IRON_ALUMINIUM, 'G', ForgeCTags.GLASS, 'B', BatteryRE, 'C', CIRCUITS_BASIC), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, Ref.ID, "drain_expensive", "parts", "has_battery", provider.hasSafeItem(BatteryRE),
                new ItemStack(Drain, 1), of('A', PLATES_IRON_ALUMINIUM, 'B', Items.IRON_BARS), "ABA", "B B", "ABA");
        provider.addStackRecipe(output, Ref.ID, "sawblade", "parts", "has_stainless_steel_plate", provider.hasSafeItem(PLATE.getMaterialTag(StainlessSteel)),
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
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', DUST.getMaterialTag(Glowstone)), "RGR", "LCL", "RGR");
        provider.addStackRecipe(output, Ref.ID, "circuit_advanced_v", "parts", "has_basic_circuit", provider.hasSafeItem(CIRCUITS_BASIC),
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', DUST.getMaterialTag(Glowstone)), "RLR", "GCG", "RLR");
        provider.shapeless(output, "mesh_carbon", "parts", "has_carbon_fibre", provider.hasSafeItem(CarbonFibre), new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
        provider.addItemRecipe(output, Ref.ID, "re_battery", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_TIN.getBlockItem(PipeSize.VTINY)),
                BatteryRE, of('T', PLATE.getMaterialTag(Tin), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'R', DUST.getMaterialTag(Redstone)), " C ", "TRT", "TRT");
        provider.addItemRecipe(output, Ref.ID, "small_battery_hull", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_TIN.getBlockItem(PipeSize.VTINY)),
                BatteryHullSmall, of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY)), "C", "T", "T");
        provider.addItemRecipe(output, Ref.ID, "medium_battery_hull", "parts", "has_copper_cable", provider.hasSafeItem(CABLE_COPPER.getBlockItem(PipeSize.VTINY)),
                BatteryHullMedium, of('T', PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "C C", "TTT", "TTT");
        provider.addItemRecipe(output, Ref.ID, "shape_empty", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                EmptyShape, of('F', FILE.getTag(), 'H', HAMMER.getTag(), 'S', PLATE.getMaterialTag(Steel)), "HF", "SS", "SS");
        provider.addItemRecipe(output, Ref.ID, "plate_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldPlate, of('H', HAMMER.getTag(), 'P', EmptyShape), "H", "P");
        provider.addItemRecipe(output, Ref.ID, "casing_mold", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                MoldCasing, of('H', HAMMER.getTag(), 'P', EmptyShape), " H", "P ");
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
        provider.addItemRecipe(output, Ref.ID, "bolt_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeBolt, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "W ", " P");
        provider.addItemRecipe(output, Ref.ID, "ring_shape", "parts", "has_wire_cutter", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                ShapeRing, of('W', WIRE_CUTTER.getTag(), 'P', EmptyShape), "PW");
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
        provider.addStackRecipe(output, Ref.ID, "energium_dust", "parts", "has_diamond_dust", provider.hasSafeItem(DUST.getMaterialTag(Diamond)),
                DUST.get(Energium, 9), of('R', DUST.getMaterialTag(Redstone), 'D', DUST.getMaterialTag(Diamond)), "RDR", "DRD", "RDR");
        provider.addStackRecipe(output, Ref.ID, "energium_dust2", "parts", "has_ruby_dust", provider.hasSafeItem(DUST.getMaterialTag(Ruby)),
                DUST.get(Energium, 9), of('R', DUST.getMaterialTag(Redstone), 'D', DUST.getMaterialTag(Ruby)), "RDR", "DRD", "RDR");
        provider.addItemRecipe(output, Ref.ID, "crystal_lapotron", "parts", "has_energy_crystal", provider.hasSafeItem(EnergyCrystal),
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, Ref.ID, "crystal_lapotron2", "parts", "has_sapphire", provider.hasSafeItem(GEM.getMaterialTag(Sapphire)),
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', GEM.getMaterialTag(Sapphire)), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, Ref.ID, "orb_lapotron", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                LapotronicEnergyOrb, of('L', LapotronCrystal, 'I', IridiumReinforcedPlate), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, Ref.ID, "coil_copper", "parts", "has_iron_ingot", provider.hasSafeItem(INGOT.getMaterialTag(Iron)),
                CopperCoil, of('L', WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', INGOT.getMaterialTag(Iron)), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, Ref.ID, "ingot_iridium_alloy", "parts", "has_diamond", provider.hasSafeItem(GEM.getMaterialTag(Diamond)),
                IridiumAlloyIngot, of('I', PLATE.getMaterialTag(Iridium), 'D', GEM.getMaterialTag(Diamond), 'A', AdvancedAlloy), "IAI", "ADA", "IAI");
        provider.addItemRecipe(output, Ref.ID, "freq_transmitter", "parts", "has_circuit", provider.hasSafeItem(CIRCUITS_BASIC), FrequencyTransmitter, of('C', CIRCUITS_BASIC, 'c', CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "Cc");
        provider.addItemRecipe(output, Ref.ID, "magnetic_steel_ingot", "parts", "has_redstone", provider.hasSafeItem(DUST.getMaterialTag(Redstone)), INGOT.get(SteelMagnetic), of('R', DUST.getMaterialTag(Redstone), 'I', INGOT.getMaterialTag(Steel)), "RRR", "RIR", "RRR");
        provider.shapeless(output, "magnetic_steel_rod", "parts", "has_redstone", provider.hasSafeItem(DUST.getMaterialTag(Redstone)), ROD.get(SteelMagnetic, 1), ROD.getMaterialTag(Steel), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone));
        provider.addItemRecipe(output, Ref.ID, "magnetic_iron_ingot", "parts", "has_redstone", provider.hasSafeItem(DUST.getMaterialTag(Redstone)), INGOT.get(IronMagnetic), of('R', DUST.getMaterialTag(Redstone), 'I', INGOT.getMaterialTag(Iron)), "RRR", "RIR", "RRR");
        provider.shapeless(output, "magnetic_iron_rod", "parts", "has_redstone", provider.hasSafeItem(DUST.getMaterialTag(Redstone)), ROD.get(IronMagnetic, 1), ROD.getMaterialTag(Iron), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone), DUST.getMaterialTag(Redstone));
        Material[] in = new Material[]{Iron, WroughtIron, Nickel};
        Material[] b = new Material[]{Bronze, Brass};
        Material[] tz = new Material[]{Tin, Zinc};
        for (Material it : in){
            for (Material im : b){
                for (Material ib : tz){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("tm", it.getId());
                    nbt.putString("mm", im.getId());
                    nbt.putString("bm", ib.getId());
                    ItemStack stack = new ItemStack(MixedMetal, 1);
                    stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                    provider.addStackRecipe(output, Ref.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(it)),
                            stack, of('T', PLATE.getMaterialTag(it), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
                }
            }
        }
        Material[] is = new Material[]{Invar, Steel};
        Material[] ase = new Material[]{Aluminium, Silver, Electrum};
        for (Material it : is){
            for (Material im : b){
                for (Material ib : tz){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("tm", it.getId());
                    nbt.putString("mm", im.getId());
                    nbt.putString("bm", ib.getId());
                    ItemStack stack = new ItemStack(MixedMetal, 2);
                    stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                    provider.addStackRecipe(output, Ref.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(it)),
                            stack, of('T', PLATE.getMaterialTag(it), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
                }
            }
        }
        for (Material it : is){
            for (Material im : b){
                for (Material ib : ase){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("tm", it.getId());
                    nbt.putString("mm", im.getId());
                    nbt.putString("bm", ib.getId());
                    ItemStack stack = new ItemStack(MixedMetal, 3);
                    stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                    provider.addStackRecipe(output, Ref.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(it)),
                            stack, of('T', PLATE.getMaterialTag(it), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
                }
            }
        }
        Material[] st = new Material[]{StainlessSteel, Titanium, Tungsten};
        for (Material it : st){
            for (Material im : b){
                for (Material ib : tz){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("tm", it.getId());
                    nbt.putString("mm", im.getId());
                    nbt.putString("bm", ib.getId());
                    ItemStack stack = new ItemStack(MixedMetal, 3);
                    stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                    provider.addStackRecipe(output, Ref.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(it)),
                            stack, of('T', PLATE.getMaterialTag(it), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
                }
            }
        }
        for (Material it : st){
            for (Material im : b){
                for (Material ib : ase){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("tm", it.getId());
                    nbt.putString("mm", im.getId());
                    nbt.putString("bm", ib.getId());
                    ItemStack stack = new ItemStack(MixedMetal, 4);
                    stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                    provider.addStackRecipe(output, Ref.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(it)),
                            stack, of('T', PLATE.getMaterialTag(it), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
                }
            }
        }
        for (Material im : b){
            for (Material ib : tz){
                CompoundTag nbt = new CompoundTag();
                nbt.putString("tm", TungstenSteel.getId());
                nbt.putString("mm", im.getId());
                nbt.putString("bm", ib.getId());
                ItemStack stack = new ItemStack(MixedMetal, 5);
                stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                provider.addStackRecipe(output, Ref.ID, "mixed_metal_tungstensteel_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(TungstenSteel)),
                        stack, of('T', PLATE.getMaterialTag(TungstenSteel), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
            }
        }
        for (Material im : b){
            for (Material ib : ase){
                CompoundTag nbt = new CompoundTag();
                nbt.putString("tm", TungstenSteel.getId());
                nbt.putString("mm", im.getId());
                nbt.putString("bm", ib.getId());
                ItemStack stack = new ItemStack(MixedMetal, 6);
                stack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
                provider.addStackRecipe(output, Ref.ID, "mixed_metal_tungstensteel_" + im.getId() + "_" + ib.getId(), "parts", "has_first_plate", provider.hasSafeItem(PLATE.getMaterialTag(TungstenSteel)),
                        stack, of('T', PLATE.getMaterialTag(TungstenSteel), 'M', PLATE.getMaterialTag(im), 'B', PLATE.getMaterialTag(ib)), "T", "M", "B");
            }
        }
        provider.shapeless(output, "fire_clay_dust", "parts", "has_clay_dust", provider.hasSafeItem(DUST.getMaterialTag(Clay)), DUST.get(Fireclay, 2), DUST.getMaterialTag(Brick), DUST.getMaterialTag(Clay));
        provider.shapeless(output, "iron_ingot_from_wrought", "parts", "has_ash", provider.hasSafeItem(DUST.getMaterialTag(Ash)), new ItemStack(Items.IRON_INGOT), DUST.getMaterialTag(Ash), INGOT.getMaterialTag(WroughtIron));
        provider.addStackRecipe(output, Ref.ID, "super_conductor_wire", "parts", "has_superconductor", provider.hasSafeItem(ItemSuperconductor),
                new ItemStack(WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.NORMAL), 4), of('M', MACHINE_HULLS_BASIC, 'S', ItemSuperconductor, 'C', CIRCUITS_MASTER), "MCM", "SSS", "MCM");
        provider.addStackRecipe(output, Ref.ID, "super_conductor", "parts", "has_iridium_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                new ItemStack(ItemSuperconductor, 4), of('H', RecipeIngredient.of(Helium.getCellGas(1, CellTin)), 'T', PLATE.getMaterialTag(Tungsten), 'I', IridiumReinforcedPlate, 'C', CIRCUITS_MASTER), "HHH", "TIT", "CCC");
        provider.shapeless(output, Ref.ID, "bronze_dust", "had_copper", provider.hasSafeItem(DUST.getMaterialTag(Bronze)),
                DUST.get(Bronze, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Tin));
        provider.addItemRecipe(output, Ref.ID, "int_circuit", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                INT_CIRCUITS_ITEMS.get(0), of('R', ROD.getMaterialTag(Iron), 'P', PLATE.getMaterialTag(Iron), 'H', HAMMER.getTag(), 'W', WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, Ref.ID, "int_circuit1", "parts", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                INT_CIRCUITS_ITEMS.get(0), of('R', ROD.getMaterialTag(WroughtIron), 'P', PLATE.getMaterialTag(WroughtIron), 'H', HAMMER.getTag(), 'W', WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, Ref.ID, "motor_lv", "parts", "has_tin_cable", provider.hasSafeItem(CABLE_TIN.getBlockItem(PipeSize.VTINY)),
                MotorLV, of('T', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', ROD.getMaterialTag(Steel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, Ref.ID, "motor_mv", "parts", "has_copper_cable", provider.hasSafeItem(CABLE_COPPER.getBlockItem(PipeSize.VTINY)),
                MotorMV, of('T', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.TINY), 'I', ROD.getMaterialTag(Aluminium), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, Ref.ID, "motor_hv", "parts", "has_gold_cable", provider.hasSafeItem(CABLE_GOLD.getBlockItem(PipeSize.VTINY)),
                MotorHV, of('T', CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.SMALL), 'I', ROD.getMaterialTag(StainlessSteel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.shapeless(output, "match_r", "parts", "has_stick", provider.hasSafeItem(getForgelikeItemTag("rods/wooden")), new ItemStack(Match, 4), DUST.getMaterialTag(Phosphor), getForgelikeItemTag("rods/wooden"));
        provider.addStackRecipe(output, Ref.ID, "fluid_cell", "parts", "has_tin", provider.hasSafeItem(PLATE.getMaterialTag(Tin)),
                new ItemStack(CellTin, 2), of('T', PLATE.getMaterialTag(Tin)), " T ", "T T", " T ");
        provider.shapeless(output, "resin_torch", "parts", "has_sticky_resin", provider.hasSafeItem(GTRubberData.StickyResin), new ItemStack(Items.TORCH, 4), GTRubberData.StickyResin, getForgelikeItemTag("rods/wooden"));
        provider.addItemRecipe(output, Ref.ID, "neutron_reflector_iridium", "parts", "has_iridium_reinforced_plate", provider.hasSafeItem(IridiumReinforcedPlate),
                IridiumNeutronReflector, of('N', ThickNeutronReflector, 'I', IridiumReinforcedPlate), "NNN", "NIN", "NNN");
        provider.addItemRecipe(output, Ref.ID, "neutron_reflector_thick", "parts", "has_beryllium_dust", provider.hasSafeItem(DUST.get(Beryllium)),
                ThickNeutronReflector, of('N', NeutronReflector, 'I', DUST.get(Beryllium)), " N ", "NIN", " N ");
        provider.addItemRecipe(output, Ref.ID, "neutron_reflector_normal", "parts", "has_copper_plate", provider.hasSafeItem(PLATE.get(Copper)),
                NeutronReflector, of('C', DUST.get(Coal), 'T', DUST.get(Tin), 'I', PLATE.get(Copper)), "TCT", "CIC", "TCT");
    }
}
