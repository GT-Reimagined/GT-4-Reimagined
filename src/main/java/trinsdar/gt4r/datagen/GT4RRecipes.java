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
import net.minecraftforge.common.Tags;
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
        shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), new ItemStack(Items.FLINT), MORTAR.getTag(), Items.GRAVEL);
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            addItemRecipe(consumer, Ref.ID, "piston_" + m.getId(), "pistons", "has_" + m.getId(), hasSafeItem(INGOT.getMaterialTag(m)),
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', Tags.Items.COBBLESTONE, 'R', DUST.getMaterialTag(Redstone), 'I', INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        // todo: bucket, minecart, iron door
        if (GT4RConfig.GAMEPLAY.HARDER_WOOD){
            addStackRecipe(consumer, Ref.ID, "sticks_2", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 2), of('P', ItemTags.PLANKS), "P", "P");
            addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS, 'S', SAW.getTag()), "S", "P", "P");
            shapeless(consumer, "oak_planks_2", "planks", "has_oak_wood", this.hasSafeItem(ItemTags.OAK_LOGS), new ItemStack(Items.OAK_PLANKS, 2), ItemTags.OAK_LOGS);
            shapeless(consumer, "birch_planks_2", "planks", "has_birch_wood", this.hasSafeItem(ItemTags.BIRCH_LOGS), new ItemStack(Items.BIRCH_PLANKS, 2), ItemTags.BIRCH_LOGS);
            shapeless(consumer, "spruce_planks_2", "planks", "has_spruce_wood", this.hasSafeItem(ItemTags.SPRUCE_LOGS), new ItemStack(Items.SPRUCE_PLANKS, 2), ItemTags.SPRUCE_LOGS);
            shapeless(consumer, "jungle_planks_2", "planks", "has_jungle_wood", this.hasSafeItem(ItemTags.JUNGLE_LOGS), new ItemStack(Items.JUNGLE_PLANKS, 2), ItemTags.JUNGLE_LOGS);
            shapeless(consumer, "acacia_planks_2", "planks", "has_acacia_wood", this.hasSafeItem(ItemTags.ACACIA_LOGS), new ItemStack(Items.ACACIA_PLANKS, 2), ItemTags.ACACIA_LOGS);
            shapeless(consumer, "dark_oak_planks_2", "planks", "has_dark_oak_wood", this.hasSafeItem(ItemTags.DARK_OAK_LOGS), new ItemStack(Items.DARK_OAK_PLANKS, 2), ItemTags.DARK_OAK_LOGS);
            shapeless(consumer, "crimson_planks_2", "planks", "has_crimson_wood", this.hasSafeItem(ItemTags.CRIMSON_STEMS), new ItemStack(Items.CRIMSON_PLANKS, 2), ItemTags.CRIMSON_STEMS);
            shapeless(consumer, "warped_planks_2", "planks", "has_warped_wood", this.hasSafeItem(ItemTags.WARPED_STEMS), new ItemStack(Items.WARPED_PLANKS, 2), ItemTags.WARPED_STEMS);

            addStackRecipe(consumer, Ref.ID, "oak_planks_4", "planks", "has_oak_wood", this.hasSafeItem(ItemTags.OAK_LOGS), new ItemStack(Items.OAK_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.OAK_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "birch_planks_4", "planks", "has_birch_wood", this.hasSafeItem(ItemTags.BIRCH_LOGS), new ItemStack(Items.BIRCH_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.BIRCH_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "spruce_planks_4", "planks", "has_spruce_wood", this.hasSafeItem(ItemTags.SPRUCE_LOGS), new ItemStack(Items.SPRUCE_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.SPRUCE_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "jungle_planks_4", "planks", "has_jungle_wood", this.hasSafeItem(ItemTags.JUNGLE_LOGS), new ItemStack(Items.JUNGLE_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.JUNGLE_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "acacia_planks_4", "planks", "has_acacia_wood", this.hasSafeItem(ItemTags.ACACIA_LOGS), new ItemStack(Items.ACACIA_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.ACACIA_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "dark_oak_planks_4", "planks", "has_dark_oak_wood", this.hasSafeItem(ItemTags.DARK_OAK_LOGS), new ItemStack(Items.DARK_OAK_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.DARK_OAK_LOGS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "crimson_planks_4", "planks", "has_crimson_wood", this.hasSafeItem(ItemTags.CRIMSON_STEMS), new ItemStack(Items.CRIMSON_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.CRIMSON_STEMS), "S", "P");
            addStackRecipe(consumer, Ref.ID, "warped_planks_4", "planks", "has_warped_wood", this.hasSafeItem(ItemTags.WARPED_STEMS), new ItemStack(Items.WARPED_PLANKS, 4), of('S', SAW.getTag(), 'P', ItemTags.WARPED_STEMS), "S", "P");
        } else {
            addStackRecipe(consumer, Ref.ID, "sticks_4", "wood_stuff", "has_planks", this.hasSafeItem(ItemTags.PLANKS), new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS), "P", "P");
            shapeless(consumer, "oak_planks_4", "planks", "has_oak_wood", this.hasSafeItem(ItemTags.OAK_LOGS), new ItemStack(Items.OAK_PLANKS, 4), ItemTags.OAK_LOGS);
            shapeless(consumer, "birch_planks_4", "planks", "has_birch_wood", this.hasSafeItem(ItemTags.BIRCH_LOGS), new ItemStack(Items.BIRCH_PLANKS, 4), ItemTags.BIRCH_LOGS);
            shapeless(consumer, "spruce_planks_4", "planks", "has_spruce_wood", this.hasSafeItem(ItemTags.SPRUCE_LOGS), new ItemStack(Items.SPRUCE_PLANKS, 4), ItemTags.SPRUCE_LOGS);
            shapeless(consumer, "jungle_planks_4", "planks", "has_jungle_wood", this.hasSafeItem(ItemTags.JUNGLE_LOGS), new ItemStack(Items.JUNGLE_PLANKS, 4), ItemTags.JUNGLE_LOGS);
            shapeless(consumer, "acacia_planks_4", "planks", "has_acacia_wood", this.hasSafeItem(ItemTags.ACACIA_LOGS), new ItemStack(Items.ACACIA_PLANKS, 4), ItemTags.ACACIA_LOGS);
            shapeless(consumer, "dark_oak_planks_4", "planks", "has_dark_oak_wood", this.hasSafeItem(ItemTags.DARK_OAK_LOGS), new ItemStack(Items.DARK_OAK_PLANKS, 4), ItemTags.DARK_OAK_LOGS);
            shapeless(consumer, "crimson_planks_4", "planks", "has_crimson_wood", this.hasSafeItem(ItemTags.CRIMSON_STEMS), new ItemStack(Items.CRIMSON_PLANKS, 4), ItemTags.CRIMSON_STEMS);
            shapeless(consumer, "warped_planks_4", "planks", "has_warped_wood", this.hasSafeItem(ItemTags.WARPED_STEMS), new ItemStack(Items.WARPED_PLANKS, 4), ItemTags.WARPED_STEMS);
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
