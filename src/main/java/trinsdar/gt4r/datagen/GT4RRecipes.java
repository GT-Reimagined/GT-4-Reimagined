package trinsdar.gt4r.datagen;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.loader.MaterialRecipeLoader;
import trinsdar.gt4r.loader.crafting.BlockCrafting;
import trinsdar.gt4r.loader.crafting.MachineCrafting;
import trinsdar.gt4r.loader.crafting.Parts;
import trinsdar.gt4r.loader.crafting.ToolCraftingTableRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.loader.machines.FurnaceLoader;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.GRINDABLE;
import static muramasa.antimatter.material.MaterialTag.RUBBERTOOLS;
import static muramasa.antimatter.util.TagUtils.getForgeItemTag;
import static muramasa.antimatter.util.TagUtils.nc;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.GT4RData.SPEAR;
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
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        super.registerRecipes(consumer);
        addConditionalRecipe(consumer, getStackRecipe("", "has_sulfur_dust", criterion(getForgeItemTag("dusts/sulfur"), this),
                new ItemStack(Blocks.TORCH, 6), of('D', getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");

        addItemRecipe(consumer, Ref.ID, "hopper", "", "has_wrench", criterion(WRENCH.getTag(), this),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', getForgeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");


        addItemRecipe(consumer,  Ref.ID,"sticky_piston_from_resin", "", "has_piston", criterion(Blocks.PISTON, this), Blocks.STICKY_PISTON, of('S', GT4RData.StickyResin, 'P', Blocks.PISTON), "S", "P");
        shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), new ItemStack(Items.FLINT), MORTAR.getTag(), Items.GRAVEL);
    }

    @Override
    protected void registerMaterialRecipes(Consumer<IFinishedRecipe> consumer, String providerDomain) {
        AntimatterAPI.all(BlockOre.class, providerDomain, o -> {
            if (o.getOreType() != ORE) return;
            if (!o.getMaterial().has(INGOT)) return;
            Item ingot = INGOT.get(o.getMaterial());
            ITag.INamedTag<Item> oreTag = TagUtils.getForgeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()));
            ITag.INamedTag<Item> ingotTag = TagUtils.getForgeItemTag("ingots/".concat(o.getMaterial().getId()));
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(nc(oreTag)), ingot, 2.0F, 100)
                    .addCriterion("has_material_" + o.getMaterial().getId(), hasItem(ingotTag))
                    .build(consumer, fixLoc(providerDomain, o.getId().concat("_to_ingot")));
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(nc(oreTag)), ingot, 2.0F, 200)
                    .addCriterion("has_material_" + o.getMaterial().getId(), hasItem(ingotTag))
                    .build(consumer, fixLoc(providerDomain, o.getId().concat("_to_ingot")));
        });
        AntimatterAPI.all(Material.class, providerDomain).stream().filter(m -> m.has(DUST)).forEach(mat -> {
            Item dust = DUST.get(mat);
            if (mat.has(ROCK)) {
                ITag<Item> rockTag = nc(TagUtils.getForgeItemTag("rocks/".concat(mat.getId())));
                Item rock = ROCK.get(mat);
                Item smallDust = DUST_SMALL.get(mat);
                ShapelessRecipeBuilder.shapelessRecipe(dust)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(nc(MORTAR.getTag()))
                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(rockTag))
                        .setGroup("rocks_grind_to_dust").build(consumer, fixLoc(providerDomain, rock.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));

                ShapelessRecipeBuilder.shapelessRecipe(smallDust)
                        .addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(nc(MORTAR.getTag()))
                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(getForgeItemTag("rocks/".concat(mat.getId()))))
                        .setGroup("rocks_grind_to_small_dust").build(consumer, fixLoc(providerDomain, rock.getRegistryName().getPath() + "_grind_to_" + smallDust.getRegistryName().getPath()));
            }
            if (mat.has(INGOT, GRINDABLE)) {
                Item ingot = INGOT.get(mat);
                ITag<Item> ingotTag = nc(TagUtils.getForgeItemTag("ingots/".concat(mat.getId())));
                ShapelessRecipeBuilder.shapelessRecipe(dust).addIngredient(ingotTag).addIngredient(nc(MORTAR.getTag()))
                        .addCriterion("has_ingot_" + mat.getId(), this.hasItem(nc(TagUtils.getForgeItemTag("ingots/".concat(mat.getId())))))
                        .setGroup("ingots_grind_to_dust")
                        .build(consumer, fixLoc(providerDomain,ingot.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));
            }
        });
    }

    protected void registerToolRecipes(Consumer<IFinishedRecipe> consumer, String providerDomain) {
        super.registerToolRecipes(consumer, providerDomain);
        List<Material> mainMats = AntimatterAPI.all(Material.class, providerDomain).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.has(TOOLS))).collect(Collectors.toList());
        List<Material> handleMats = AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.isHandle())).collect(Collectors.toList());

        mainMats.forEach(main -> {
            if (!main.has(INGOT) && !main.has(GEM)) return; // TODO: For time being
            String ingotGem = main.has(INGOT) ? "ingots" : "gems";
            String plate = main.has(PLATE) ? "plates" : ingotGem;
            final ITag<Item> ingotTag = TagUtils.getForgeItemTag(ingotGem + "/" + main.getId()), plateTag = TagUtils.getForgeItemTag(plate + "/" + main.getId()), mainRodTag = TagUtils.getForgeItemTag("rods/" + main.getId());
            final Supplier<ICriterionInstance> ingotTrigger = this.hasSafeItem(ingotTag), plateTrigger = this.hasSafeItem(plateTag), rodTrigger = this.hasSafeItem(mainRodTag);

            for (Material handle : handleMats) {
                String handleId = handle.getId().equals("wood") ? "wooden" : handle.getId();
                final ITag<Item> rodTag = TagUtils.getForgeItemTag("rods/" + handleId);

                ImmutableMap<Character, Object> map1 = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? of('R', rodTag, 'P', plateTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()) : of('R', rodTag, 'P', plateTag);
                String[] strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{" FP", " RH", "R  "} : new String[]{"  P", " R ", "R  "};
                if (main.getToolTypes().contains(PICKAXE))
                    addStackRecipe(consumer, muramasa.antimatter.Ref.ID, SPEAR.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_spears",
                            "has_material_" + main.getId(), ingotTrigger, SPEAR.getToolStack(main, handle), map1, strings);
            }
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
