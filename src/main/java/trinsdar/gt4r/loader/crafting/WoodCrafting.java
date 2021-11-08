package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.SAW;
import static muramasa.antimatter.Data.Stone;
import static muramasa.antimatter.util.TagUtils.getForgeItemTag;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class WoodCrafting {

    public static void loadRecipes(Consumer<IFinishedRecipe> consumer, AntimatterRecipeProvider provider) {

        if (GT4RConfig.GAMEPLAY.HARDER_WOOD){
            provider.addStackRecipe(consumer, Ref.ID, "sticks_2", "wood_stuff", "has_planks", provider.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 2), of('P', ItemTags.PLANKS), "P", "P");
            provider.addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", provider.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS, 'S', SAW.getTag()), "S", "P", "P");
        } else {
            provider.addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", provider.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS), "P", "P");
        }
        addWoodRecipe(consumer, provider, ItemTags.OAK_LOGS, Items.OAK_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
        addWoodRecipe(consumer, provider, ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        if (AntimatterAPI.isModLoaded(Ref.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, provider, TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TERRESTRIA, woodType + "_logs")), ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MOD_TERRESTRIA, woodType + "_planks")));
            }

        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, provider, TagUtils.getItemTag(new ResourceLocation(Ref.MOD_CINDERSCAPES, woodType + "_stems")), ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MOD_CINDERSCAPES, woodType + "_planks")));
            }

        }
        String[] stones = {"stone", "smooth_stone", "sandstone", "cut_sandstone", "cobblestone", "red_sandstone", "cut_red_sandstone", "prismarine", "dark_prismarine", "polished_granite", "smooth_red_sandstone", "polished_diorite", "mossy_cobblestone", "smooth_sandstone", "smooth_quartz", "granite", "andesite", "polished_andesite", "diorite", "blackstone", "polished_blackstone", "purpur", "quartz", "brick", "stone_brick", "nether_brick", "prismarine_brick", "mossy_stone_brick", "end_stone_brick", "red_nether_brick", "polished_blackstone_brick"};
        for (String stone : stones) {
            Item full = ForgeRegistries.ITEMS.getValue(new ResourceLocation(stone + (stone.equals("purpur") || stone.equals("quartz") ? "_block" : stone.contains("brick") ? "s" : "")));
            Item slab = ForgeRegistries.ITEMS.getValue(new ResourceLocation(stone + "_slab"));
            String[] pattern = stone.equals("purpur") || stone.equals("quartz") || stone.equals("sandstone") || stone.equals("red_sandstone") || stone.equals("stone_brick") || stone.equals("nether_brick") || stone.equals("polished_blackstone") ? new String[]{"SS"} : new String[]{"S", "S"};
            provider.addItemRecipe(consumer, Ref.ID, stone + "_slab_to_" + stone, "slabs", "has_stone", provider.hasSafeItem(slab), full, of('S', slab), pattern);
        }
        String[] wood = {"oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "crimson", "warped"};
        for (String s : wood) {
            ResourceLocation name = new ResourceLocation(s + "_planks");
            ResourceLocation slab = new ResourceLocation(s + "_slab");
            provider.addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", provider.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(Ref.MOD_TERRESTRIA,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(Ref.MOD_TERRESTRIA,woodType + "_slab");
                provider.addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", provider.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
            }

        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(Ref.MOD_CINDERSCAPES,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(Ref.MOD_CINDERSCAPES,woodType + "_slab");
                provider.addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", provider.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
            }
        }
    }

    public static void addWoodRecipe(Consumer<IFinishedRecipe> consumer, AntimatterRecipeProvider provider, ITag.INamedTag<Item> log, Item plank){
        if (GT4RConfig.GAMEPLAY.HARDER_WOOD){
            provider.shapeless(consumer, plank.getRegistryName().getPath() + "_2", "planks", "has_" + log.getName().getPath(), provider.hasSafeItem(log), new ItemStack(plank, 2), log);
            provider.addStackRecipe(consumer, Ref.ID, plank.getRegistryName().getPath() + "_4", "planks", "has_" + log.getName().getPath(), provider.hasSafeItem(log), new ItemStack(plank, 4), of('S', SAW.getTag(), 'P', log), "S", "P");
        } else {
            provider.shapeless(consumer, plank.getRegistryName().getPath() + "_4", "planks", "has_" + log.getName().getPath(), provider.hasSafeItem(log), new ItemStack(plank, 4), log);
        }
    }
}
