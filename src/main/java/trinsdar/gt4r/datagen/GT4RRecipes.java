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
import net.minecraftforge.common.Tags;
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
import static trinsdar.gt4r.data.Materials.Steel;
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
