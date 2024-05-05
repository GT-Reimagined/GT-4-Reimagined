package trinsdar.gt4r.loader.crafting;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import trinsdar.gt4r.GT4RRef;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.TierMaps;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.FOIL;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class Parts {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_energy_flow", "parts",
                new ItemStack(CircuitEnergyFlow, 4), of('C', CIRCUITS_ADVANCED, 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'L', LapotronCrystal, 'P', IridiumReinforcedPlate), "CTC", "LPL", "CTC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_data_control", "parts",
                new ItemStack(CircuitDataControl, 4), of('C', CIRCUITS_ADVANCED, 'c', CIRCUITS_DATA, 'P', IridiumReinforcedPlate), "CcC", "cPc", "CcC");
        provider.addStackRecipe(output, GT4RRef.ID, "comp_monitor", "parts",
                new ItemStack(ComputerMonitor, 1), of2('A', AntimatterMaterialTypes.PLATE.getMaterialTag(Aluminium), 'G', ForgeCTags.GLASS_PANES, 'g', ForgeCTags.DYES_GREEN, 'R', ForgeCTags.DYES_RED, 'B', ForgeCTags.DYES_BLUE, 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "AgA", "RGB", "ADA");
        provider.addStackRecipe(output, GT4RRef.ID, "conv_module", "parts",
                new ItemStack(ConveyorModule, 1), of('A', PLATES_IRON_ALUMINIUM, 'G', ForgeCTags.GLASS, 'B', BatteryRE, 'C', CIRCUITS_BASIC), "GGG", "AAA", "CBC");
        provider.addStackRecipe(output, GT4RRef.ID, "drain_expensive", "parts",
                new ItemStack(Drain, 1), of('A', PLATES_IRON_ALUMINIUM, 'B', Items.IRON_BARS), "ABA", "B B", "ABA");
        provider.addStackRecipe(output, GT4RRef.ID, "sawblade", "parts",
                new ItemStack(DiamondSawBlade, 4), of('A', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond)), "DAD", "A A", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "d_grindhead", "parts",
                new ItemStack(DiamondGrindHead, 4), of('A', PLATES_STEELS, 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond), 'G', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)), "DAD", "AGA", "DAD");
        provider.addStackRecipe(output, GT4RRef.ID, "w_grindhead", "parts",
                new ItemStack(TungstenGrindHead, 4), of('S', PLATES_STEELS, 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'B', AntimatterMaterialTypes.BLOCK.getMaterialTag(Steel)), "TST", "SBS", "TST");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_h", "parts",
                new ItemStack(CircuitBasic, 1), of('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CCC", "RIR", "CCC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_basic_v", "parts",
                new ItemStack(CircuitBasic, 1), of('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'I', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron)), "CRC", "CIC", "CRC");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_h", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "RGR", "LCL", "RGR");
        provider.addStackRecipe(output, GT4RRef.ID, "circuit_advanced_v", "parts",
                new ItemStack(CircuitAdv, 1), of('C', CIRCUITS_BASIC, 'R', AntimatterMaterialTypes.PLATE.getMaterialTag(RedAlloy), 'L', getForgelikeItemTag("dusts/lapislaz"), 'G', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone)), "RLR", "GCG", "RLR");
        provider.shapeless(output, "mesh_carbon", "parts", new ItemStack(CarbonMesh), CarbonFibre, CarbonFibre);
        provider.addItemRecipe(output, GT4RRef.ID, "re_battery", "parts",
                BatteryRE, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tin), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone)), " C ", "TRT", "TRT");
        provider.addItemRecipe(output, GT4RRef.ID, "small_battery_hull", "parts",
                BatteryHullSmall, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY)), "C", "T", "T");
        provider.addItemRecipe(output, GT4RRef.ID, "medium_battery_hull", "parts",
                BatteryHullMedium, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(BatteryAlloy), 'C', CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "C C", "TTT", "TTT");
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
        provider.addStackRecipe(output, GT4RRef.ID, "energium_dust", "parts",
                AntimatterMaterialTypes.DUST.get(Energium, 9), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond)), "RDR", "DRD", "RDR");
        provider.addStackRecipe(output, GT4RRef.ID, "energium_dust2", "parts",
                AntimatterMaterialTypes.DUST.get(Energium, 9), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'D', AntimatterMaterialTypes.DUST.getMaterialTag(Ruby)), "RDR", "DRD", "RDR");
        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "crystal_lapotron2", "parts",
                LapotronCrystal, of('L', DUSTS_LAPIS_LAZ, 'C', CIRCUITS_ADVANCED, 'E', AntimatterMaterialTypes.GEM.getMaterialTag(Sapphire)), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GT4RRef.ID, "orb_lapotron", "parts",
                BatteryEnergyOrb, of('L', LapotronCrystal, 'I', IridiumReinforcedPlate), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "coil_copper", "parts",
                CopperCoil, of('L', WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron)), "LLL", "LIL", "LLL");
        provider.addItemRecipe(output, GT4RRef.ID, "ingot_iridium_alloy", "parts",
                IridiumAlloyIngot, of('I', AntimatterMaterialTypes.PLATE.getMaterialTag(Iridium), 'D', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'A', AdvancedAlloy), "IAI", "ADA", "IAI");
        provider.addItemRecipe(output, GT4RRef.ID, "freq_transmitter", "parts", FrequencyTransmitter, of('C', CIRCUITS_BASIC, 'c', CABLE_COPPER.getBlockItem(PipeSize.VTINY)), "Cc");
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_steel_ingot_2", "parts", AntimatterMaterialTypes.INGOT.get(SteelMagnetic), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(Steel)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_steel_rod", "parts", AntimatterMaterialTypes.ROD.get(SteelMagnetic, 1), AntimatterMaterialTypes.ROD.getMaterialTag(Steel), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone));
        provider.addItemRecipe(output, GT4RRef.ID, "magnetic_iron_ingot_2", "parts", AntimatterMaterialTypes.INGOT.get(IronMagnetic), of('R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(AntimatterMaterials.Iron)), "RRR", "RIR", "RRR");
        provider.shapeless(output, GT4RRef.ID,"magnetic_iron_rod", "parts", AntimatterMaterialTypes.ROD.get(IronMagnetic, 1), AntimatterMaterialTypes.ROD.getMaterialTag(AntimatterMaterials.Iron), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone), AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone));
        Material[] in = new Material[]{AntimatterMaterials.Iron, WroughtIron, Nickel};
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
                    provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts",
                            stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(it), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                    provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts",
                            stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(it), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                    provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts",
                            stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(it), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                    provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts",
                            stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(it), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                    provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_" + it.getId() + "_" + im.getId() + "_" + ib.getId(), "parts",
                            stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(it), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_tungstensteel_" + im.getId() + "_" + ib.getId(), "parts",
                        stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(TungstenSteel), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
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
                provider.addStackRecipe(output, GT4RRef.ID, "mixed_metal_tungstensteel_" + im.getId() + "_" + ib.getId(), "parts",
                        stack, of('T', AntimatterMaterialTypes.PLATE.getMaterialTag(TungstenSteel), 'M', AntimatterMaterialTypes.PLATE.getMaterialTag(im), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(ib)), "T", "M", "B");
            }
        }
        provider.shapeless(output, "fire_clay_dust", "parts", AntimatterMaterialTypes.DUST.get(Fireclay, 2), AntimatterMaterialTypes.DUST.getMaterialTag(Brick), AntimatterMaterialTypes.DUST.getMaterialTag(Clay));
        provider.shapeless(output, "iron_ingot_from_wrought", "parts", new ItemStack(Items.IRON_INGOT), AntimatterMaterialTypes.DUST.getMaterialTag(Ash), AntimatterMaterialTypes.INGOT.getMaterialTag(WroughtIron));
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor_wire", "parts",
                new ItemStack(WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.NORMAL), 4), of('M', MACHINE_HULLS_BASIC, 'S', ItemSuperconductor, 'C', CIRCUITS_MASTER), "MCM", "SSS", "MCM");
        provider.addStackRecipe(output, GT4RRef.ID, "super_conductor", "parts",
                new ItemStack(ItemSuperconductor, 4), of('H', RecipeIngredient.of(Helium.getCellGas(1, CellTin)), 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tungsten), 'I', IridiumReinforcedPlate, 'C', CIRCUITS_MASTER), "HHH", "TIT", "CCC");
        provider.shapeless(output, GT4RRef.ID, "bronze_dust",
                AntimatterMaterialTypes.DUST.get(Bronze, 4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(Tin));
        provider.addItemRecipe(output, GT4RRef.ID, "int_circuit", "parts",
                TierMaps.INT_CIRCUITS_ITEMS.get(0), of('R', AntimatterMaterialTypes.ROD.getMaterialTag(AntimatterMaterials.Iron), 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, GT4RRef.ID, "int_circuit1", "parts",
                TierMaps.INT_CIRCUITS_ITEMS.get(0), of('R', AntimatterMaterialTypes.ROD.getMaterialTag(WroughtIron), 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PHP", "RRR", "PWP");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_lv", "parts",
                MotorLV, of('T', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.VTINY), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(Steel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_mv", "parts",
                MotorMV, of('T', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.TINY), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(Aluminium), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
        provider.addItemRecipe(output, GT4RRef.ID, "motor_hv", "parts",
                MotorHV, of('T', CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'C', WIRE_COPPER.getBlockItem(PipeSize.SMALL), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(StainlessSteel), 'M', RODS_MAGNETIC), "TCI", "CMC", "ICT");
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
                of('G', ForgeCTags.GLASS, 'C', CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'T', Machines.TRANSFORMER.getItem(MV), 'c', CIRCUITS_BASIC), "GGG", "CTC", "GcG");
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
    }
}
