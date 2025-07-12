package org.gtreimagined.gt4r.loader.crafting;

import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.util.RegistryUtils;
import org.gtreimagined.gtlib.util.TagUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.GT4RBlocks;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.*;
import static org.gtreimagined.gtlib.util.TagUtils.getForgelikeItemTag;
import static org.gtreimagined.gt4r.data.Materials.*;

public class VanillaOverrides {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, GTRecipeProvider provider){
        provider.addItemRecipe(consumer, GT4RRef.ID, "tiny_wooden_fluid_pipe", "pipes",
                GT4RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', GTTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', GTTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "small_wooden_fluid_pipe", "pipes",
                GT4RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', GTTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', GTTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "normal_wooden_fluid_pipe", "pipes",
                GT4RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', GTTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', GTTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, GT4RRef.ID, "large_wooden_fluid_pipe", "pipes",
                GT4RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', GTTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', GTTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, GT4RRef.ID, "huge_wooden_fluid_pipe", "pipes",
                GT4RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', GTTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', GTTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        provider.shapeless(consumer, "sodalite_to_blue_dye", "", new ItemStack(Items.BLUE_DYE), GTMaterialTypes.GEM.getMaterialTag(Sodalite));

        provider.addStackRecipe(consumer, GT4RRef.ID, "sulfur_torch", "torches", new ItemStack(Items.TORCH, 2), of('D', getForgelikeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_helmet", "chainmail_armor",
                Items.CHAINMAIL_HELMET, of('R', GTMaterialTypes.RING.getMaterialTag(Steel), 'H', GTTools.HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_chestplate", "chainmail_armor",
                Items.CHAINMAIL_CHESTPLATE, of('R', GTMaterialTypes.RING.getMaterialTag(Steel), 'H', GTTools.HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_leggings", "chainmail_armor",
                Items.CHAINMAIL_LEGGINGS, of('R', GTMaterialTypes.RING.getMaterialTag(Steel), 'H', GTTools.HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, GT4RRef.ID, "chainmail_boots", "chainmail_armor",
                Items.CHAINMAIL_BOOTS, of('R', GTMaterialTypes.RING.getMaterialTag(Steel), 'H', GTTools.HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, GT4RRef.ID, "saddle", "", Items.SADDLE,
                of('L', Items.LEATHER, 'R', GTMaterialTypes.RING.getMaterialTag(Steel), 'S', GTMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        /*provider.shapeless(consumer, "dust_brick", "mortar_recipes", GTMaterialTypes.DUST_SMALL.get(Brick, 1), GTTools.MORTAR.getTag(), Items.BRICK);
        provider.shapeless(consumer, "dust_clay", "mortar_recipes", GTMaterialTypes.DUST_SMALL.get(Clay, 2), GTTools.MORTAR.getTag(), Items.CLAY_BALL);*/
        provider.addStackRecipe(consumer, GT4RRef.ID, "torch_from_coal", "torches", new ItemStack(Items.TORCH, 4),
                of('C', Ingredient.of(RAW_ORE.get(Coal), DUST.get(Coal), IMPURE_DUST.get(Coal), PURE_DUST.get(Coal), CRUSHED_ORE.get(Coal), PURIFIED_ORE.get(Coal), REFINED_ORE.get(Coal)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GT4RRef.ID, "torch_from_creosote", "torches", new ItemStack(Items.TORCH, 6),
                of('W', ItemTags.WOOL, 'C', Creosote.getLiquid().getBucket(), 'S', Items.STICK), "C", "W", "S");
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            provider.addItemRecipe(consumer, GT4RRef.ID, "piston_" + m.getId(), "pistons",
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', Tags.Items.COBBLESTONE, 'R', DUST.getMaterialTag(GTLibMaterials.Redstone), 'I', GTMaterialTypes.INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        String[] colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (String color : colors){
            TagKey<Item> tag = TagUtils.getForgelikeItemTag("dyes/" + color);
            provider.shapeless(consumer, "concrete_" + color, "concretes", new ItemStack(RegistryUtils.getItemFromID(new ResourceLocation(color + "_concrete_powder")), 8), tag, Items.SAND, Items.SAND, Items.SAND, Items.SAND, DUST.get(Stone), DUST.get(Stone), DUST.get(Stone), DUST.get(Stone));
        }
    }
}
