package trinsdar.gt4r.datagen;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.loader.MaterialRecipeLoader;
import trinsdar.gt4r.loader.crafting.BlockCrafting;
import trinsdar.gt4r.loader.crafting.MachineCrafting;
import trinsdar.gt4r.loader.crafting.ModCompatRecipes;
import trinsdar.gt4r.loader.crafting.Parts;
import trinsdar.gt4r.loader.crafting.ToolCrafting;
import trinsdar.gt4r.loader.crafting.ToolCraftingTableRecipes;
import trinsdar.gt4r.loader.machines.FurnaceLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.HANDLE;
import static muramasa.antimatter.material.MaterialTag.RUBBERTOOLS;
import static muramasa.antimatter.util.TagUtils.getForgeItemTag;
import static muramasa.antimatter.util.TagUtils.itemToBlockTag;
import static muramasa.antimatter.util.TagUtils.nc;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class GT4RRecipes extends AntimatterRecipeProvider {

    public GT4RRecipes(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
        registerCraftingLoaders();
        GT4RData.buildTierMaps();
    }

    protected void registerCraftingLoaders() {
        this.craftingLoaders.add(Parts::loadRecipes);
        this.craftingLoaders.add(ToolCraftingTableRecipes::loadRecipes);
        this.craftingLoaders.add(MachineCrafting::loadRecipes);
        this.craftingLoaders.add(MaterialRecipeLoader::loadRecipes);
        this.craftingLoaders.add(FurnaceLoader::loadRecipes);
        this.craftingLoaders.add(BlockCrafting::loadRecipes);
        this.craftingLoaders.add(ToolCrafting::loadRecipes);
        if (AntimatterAPI.isModLoaded(Ref.MOD_IE)){
            this.craftingLoaders.add(ModCompatRecipes::loadIE);
        }
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        super.registerRecipes(consumer);
        //registerMaterialRecipes(consumer, Ref.ID);
        addItemRecipe(consumer, Ref.ID, "tiny_wooden_fluid_pipe", "pipes", "has_saw", hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        addItemRecipe(consumer, Ref.ID, "small_wooden_fluid_pipe", "pipes", "has_saw", hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        addItemRecipe(consumer, Ref.ID, "normal_wooden_fluid_pipe", "pipes", "has_saw", hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        addItemRecipe(consumer, Ref.ID, "large_wooden_fluid_pipe", "pipes", "has_saw", hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        addItemRecipe(consumer, Ref.ID, "huge_wooden_fluid_pipe", "pipes", "has_saw", hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', SAW.getTag(), 's', ItemTags.LOGS, 'H', SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        shapeless(consumer, "sodalite_to_blue_dye", "", "has_sodalite", hasSafeItem(GEM.getMaterialTag(Sodalite)), new ItemStack(Items.BLUE_DYE), GEM.getMaterialTag(Sodalite));
        addConditionalRecipe(consumer, getStackRecipe("", "has_sulfur_dust", criterion(getForgeItemTag("dusts/sulfur"), this),
                new ItemStack(Blocks.TORCH, 6), of('D', getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");
        addItemRecipe(consumer, Ref.ID, "chainmail_helmet", "chainmail_armor", "has_hammer", hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_HELMET, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR");
        addItemRecipe(consumer, Ref.ID, "chainmail_chestplate", "chainmail_armor", "has_hammer", hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_CHESTPLATE, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RHR", "RRR", "RRR");
        addItemRecipe(consumer, Ref.ID, "chainmail_leggings", "chainmail_armor", "has_hammer", hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_LEGGINGS, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR", "R R");
        addItemRecipe(consumer, Ref.ID, "chainmail_boots", "chainmail_armor", "has_hammer", hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_BOOTS, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "R R", "RHR");
        addItemRecipe(consumer, Ref.ID, "saddle", "", "has_leather", hasSafeItem(Items.LEATHER), Items.SADDLE,
                of('L', Items.LEATHER, 'R', RING.getMaterialTag(Steel), 'S', SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        addItemRecipe(consumer,  Ref.ID,"sticky_piston_from_resin", "", "has_piston", criterion(Blocks.PISTON, this), Blocks.STICKY_PISTON, of('S', GT4RData.StickyResin, 'P', Blocks.PISTON), "S", "P");
        addStackRecipe(consumer, Ref.ID, "lead_from_resin", "", "has_resin", this.hasSafeItem(GT4RData.StickyResin), new ItemStack(Items.LEAD, 2), of('S', Items.STRING, 'R', GT4RData.StickyResin), "SS ", "SR ", "  S");
        shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), new ItemStack(Items.FLINT), MORTAR.getTag(), Items.GRAVEL);
        shapeless(consumer, "dust_brick", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), DUST_SMALL.get(Brick, 1), MORTAR.getTag(), Items.BRICK);
        shapeless(consumer, "dust_clay", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            addItemRecipe(consumer, Ref.ID, "piston_" + m.getId(), "pistons", "has_" + m.getId(), hasSafeItem(INGOT.getMaterialTag(m)),
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', Tags.Items.COBBLESTONE, 'R', DUST.getMaterialTag(Redstone), 'I', INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        String[] colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (String color : colors){
            ITag.INamedTag<Item> tag = TagUtils.getForgeItemTag("dyes/" + color);
            shapeless(consumer, "concrete_" + color, "concretes", "has_dye", hasSafeItem(tag), new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(color + "_concrete_powder")), 8), tag, Items.SAND, Items.SAND, Items.SAND, Items.SAND, DUST.get(Stone), DUST.get(Stone), DUST.get(Stone), DUST.get(Stone));
        }
        // todo: bucket, minecart, iron door
        if (GT4RConfig.GAMEPLAY.HARDER_WOOD){
            addStackRecipe(consumer, Ref.ID, "sticks_2", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 2), of('P', ItemTags.PLANKS), "P", "P");
            addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS, 'S', SAW.getTag()), "S", "P", "P");
        } else {
            addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS), "P", "P");
        }
        addWoodRecipe(consumer, ItemTags.OAK_LOGS, Items.OAK_PLANKS);
        addWoodRecipe(consumer, ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
        addWoodRecipe(consumer, ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
        addWoodRecipe(consumer, ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
        addWoodRecipe(consumer, ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
        addWoodRecipe(consumer, ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
        addWoodRecipe(consumer, ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
        addWoodRecipe(consumer, ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        if (AntimatterAPI.isModLoaded(Ref.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TERRESTRIA, woodType + "_logs")), ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MOD_TERRESTRIA, woodType + "_planks")));
            }

        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, TagUtils.getItemTag(new ResourceLocation(Ref.MOD_CINDERSCAPES, woodType + "_stems")), ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MOD_CINDERSCAPES, woodType + "_planks")));
            }

        }
        String[] stones = {"stone", "smooth_stone", "sandstone", "cut_sandstone", "cobblestone", "red_sandstone", "cut_red_sandstone", "prismarine", "dark_prismarine", "polished_granite", "smooth_red_sandstone", "polished_diorite", "mossy_cobblestone", "smooth_sandstone", "smooth_quartz", "granite", "andesite", "polished_andesite", "diorite", "blackstone", "polished_blackstone", "purpur", "quartz", "brick", "stone_brick", "nether_brick", "prismarine_brick", "mossy_stone_brick", "end_stone_brick", "red_nether_brick", "polished_blackstone_brick"};
        for (String stone : stones) {
            Item full = ForgeRegistries.ITEMS.getValue(new ResourceLocation(stone + (stone.equals("purpur") || stone.equals("quartz") ? "_block" : stone.contains("brick") ? "s" : "")));
            Item slab = ForgeRegistries.ITEMS.getValue(new ResourceLocation(stone + "_slab"));
            String[] pattern = stone.equals("purpur") || stone.equals("quartz") || stone.equals("sandstone") || stone.equals("red_sandstone") || stone.equals("stone_brick") || stone.equals("nether_brick") || stone.equals("polished_blackstone") ? new String[]{"SS"} : new String[]{"S", "S"};
            addItemRecipe(consumer, Ref.ID, stone + "_slab_to_" + stone, "slabs", "has_stone", this.hasSafeItem(slab), full, of('S', slab), pattern);
        }
        String[] wood = {"oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "crimson", "warped"};
        for (String s : wood) {
            ResourceLocation name = new ResourceLocation(s + "_planks");
            ResourceLocation slab = new ResourceLocation(s + "_slab");
            addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", this.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(Ref.MOD_TERRESTRIA,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(Ref.MOD_TERRESTRIA,woodType + "_slab");
                addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", this.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
            }

        }
        if (AntimatterAPI.isModLoaded(Ref.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(Ref.MOD_CINDERSCAPES,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(Ref.MOD_CINDERSCAPES,woodType + "_slab");
                addItemRecipe(consumer, Ref.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", "has_stone", this.hasSafeItem(ForgeRegistries.ITEMS.getValue(slab)), ForgeRegistries.ITEMS.getValue(name), of('S', ForgeRegistries.ITEMS.getValue(slab)), "S", "S");
            }

        }
    }

    public void addWoodRecipe(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> log, Item plank){
        if (GT4RConfig.GAMEPLAY.HARDER_WOOD){
            shapeless(consumer, plank.getRegistryName().getPath() + "_2", "planks", "has_" + log.getName().getPath(), this.hasSafeItem(log), new ItemStack(plank, 2), log);
            addStackRecipe(consumer, Ref.ID, plank.getRegistryName().getPath() + "_4", "planks", "has_" + log.getName().getPath(), this.hasSafeItem(log), new ItemStack(plank, 4), of('S', SAW.getTag(), 'P', log), "S", "P");
        } else {
            shapeless(consumer, plank.getRegistryName().getPath() + "_4", "planks", "has_" + log.getName().getPath(), this.hasSafeItem(log), new ItemStack(plank, 4), log);
        }
    }

    @Override
    protected void registerPipeRecipes(Consumer<IFinishedRecipe> consumer, String providerDomain) {
        super.registerPipeRecipes(consumer, providerDomain);
        final ICriterionInstance in = this.hasSafeItem(WRENCH.getTag());
        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            shapeless(consumer, "small_to_normal_pipe", "pipes", "has_wrench", in, new ItemStack(pipeNormal), pipeSmall, pipeSmall, pipeSmall);
        });
    }

    @Override
    public String getName() {
        return "GT4R Crafting Recipes";
    }

    private String fixLoc(String attach) {
        return Ref.ID.concat(":").concat(attach);
    }

}
