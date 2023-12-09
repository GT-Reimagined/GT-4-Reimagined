package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RRef;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.StickyResin;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static muramasa.antimatter.data.AntimatterMaterials.Stone;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.Materials.*;

public class VanillaOverrides {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        provider.addItemRecipe(consumer, GT4RRef.ID, "tiny_wooden_fluid_pipe", "pipes",
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "small_wooden_fluid_pipe", "pipes",
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "normal_wooden_fluid_pipe", "pipes",
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "large_wooden_fluid_pipe", "pipes",
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, GT4RRef.ID, "huge_wooden_fluid_pipe", "pipes",
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        provider.shapeless(consumer, "sodalite_to_blue_dye", "", new ItemStack(Items.BLUE_DYE), AntimatterMaterialTypes.GEM.getMaterialTag(Sodalite));

        provider.addStackRecipe(consumer, GT4RRef.ID, "sulfur_torch", "torches", new ItemStack(Items.TORCH, 2), of('D', getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_helmet", "chainmail_armor",
                Items.CHAINMAIL_HELMET, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_chestplate", "chainmail_armor",
                Items.CHAINMAIL_CHESTPLATE, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_leggings", "chainmail_armor",
                Items.CHAINMAIL_LEGGINGS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_boots", "chainmail_armor",
                Items.CHAINMAIL_BOOTS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "saddle", "", Items.SADDLE,
                of('L', Items.LEATHER, 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        /*provider.shapeless(consumer, "dust_brick", "mortar_recipes", AntimatterMaterialTypes.DUST_SMALL.get(Brick, 1), AntimatterDefaultTools.MORTAR.getTag(), Items.BRICK);
        provider.shapeless(consumer, "dust_clay", "mortar_recipes", AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), AntimatterDefaultTools.MORTAR.getTag(), Items.CLAY_BALL);*/
        provider.addStackRecipe(consumer, GT4RRef.ID, "torch_from_coal", "torches", new ItemStack(Items.TORCH, 4),
                of('C', Ingredient.of(RAW_ORE.get(Coal), DUST.get(Coal), DUST_IMPURE.get(Coal), DUST_PURE.get(Coal), CRUSHED.get(Coal),CRUSHED_PURIFIED.get(Coal), CRUSHED_REFINED.get(Coal)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GT4RRef.ID, "torch_from_creosote", "torches", new ItemStack(Items.TORCH, 6),
                of('W', ItemTags.WOOL, 'C', Creosote.getLiquid().getBucket(), 'S', Items.STICK), "C", "W", "S");
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            provider.addItemRecipe(consumer, GT4RRef.ID, "piston_" + m.getId(), "pistons",
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', ForgeCTags.COBBLESTONE, 'R', DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        String[] colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (String color : colors){
            TagKey<Item> tag = TagUtils.getForgelikeItemTag("dyes/" + color);
            provider.shapeless(consumer, "concrete_" + color, "concretes", new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation(color + "_concrete_powder")), 8), tag, Items.SAND, Items.SAND, Items.SAND, Items.SAND, DUST.get(Stone), DUST.get(Stone), DUST.get(Stone), DUST.get(Stone));
        }
    }
}
