package trinsdar.gt4r.datagen;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.enchantment.Enchantment;
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
import trinsdar.gt4r.loader.crafting.ToolCrafting;
import trinsdar.gt4r.loader.crafting.ToolCraftingTableRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.loader.machines.FurnaceLoader;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.GRINDABLE;
import static muramasa.antimatter.util.TagUtils.getForgeItemTag;
import static muramasa.antimatter.util.TagUtils.nc;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.StainlessSteel;
import static trinsdar.gt4r.data.Materials.Titanium;
import static trinsdar.gt4r.data.Materials.TungstenSteel;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

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
            if (!o.getMaterial().getSmeltInto().has(INGOT)) return;
            Item ingot = INGOT.get(o.getMaterial().getSmeltInto());
            ITag.INamedTag<Item> oreTag = TagUtils.getForgeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()));
            ITag.INamedTag<Item> ingotTag = TagUtils.getForgeItemTag("ingots/".concat(o.getMaterial().getSmeltInto().getId()));
            GT4RCookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(nc(oreTag)), new ItemStack(ingot, o.getMaterial().getSmeltingMulti()), 2.0F, 100)
                    .addCriterion("has_material_" + o.getMaterial().getId(), hasItem(ingotTag))
                    .build(consumer, fixLoc(providerDomain, o.getId().concat("_to_ingot")));
            GT4RCookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(nc(oreTag)), new ItemStack(ingot, o.getMaterial().getSmeltingMulti()), 2.0F, 200)
                    .addCriterion("has_material_" + o.getMaterial().getId(), hasItem(ingotTag))
                    .build(consumer, fixLoc(providerDomain, o.getId().concat("_to_ingot_smelting")));
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
                        .addCriterion("has_rock_" + mat.getId(), this.hasSafeItem(rockTag))
                        .setGroup("rocks_grind_to_dust").build(consumer, fixLoc(providerDomain, rock.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));

                ShapelessRecipeBuilder.shapelessRecipe(smallDust)
                        .addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(nc(MORTAR.getTag()))
                        .addCriterion("has_rock_" + mat.getId(), this.hasSafeItem(getForgeItemTag("rocks/".concat(mat.getId()))))
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
            final ICriterionInstance ingotTrigger = this.hasSafeItem(ingotTag), plateTrigger = this.hasSafeItem(plateTag), rodTrigger = this.hasSafeItem(mainRodTag);

            /*if (main.getToolTypes().contains(DRILL)){
                IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "drill_lv");
                IAntimatterTool drill_mv = AntimatterAPI.get(IAntimatterTool.class, "drill_mv");
                IAntimatterTool drill_hv = AntimatterAPI.get(IAntimatterTool.class, "drill_hv");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), resolveStack(drill_lv, main, StainlessSteel, 0, 100000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), resolveStack(drill_lv, main, StainlessSteel, 0, 75000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), resolveStack(drill_lv, main, StainlessSteel, 0, 50000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_mv.resolveStack(main, Titanium, 0, 400000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_mv.resolveStack(main, Titanium, 0, 300000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_mv.resolveStack(main, Titanium, 0, 200000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, DRILL.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_drills",
                        "has_material_" + main.getId(), this.hasSafeItem(DRILLBIT.getMaterialTag(main)), drill_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', DRILLBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
            }*/
            if (main.getToolTypes().contains(CHAINSAW)){
                IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv");
                IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv");
                IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 100000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 75000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 50000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 400000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 300000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 200000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_chainsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
            }
            if (main.getToolTypes().contains(ELECTRIC_WRENCH)){
                IAntimatterTool wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv");
                IAntimatterTool wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv");
                IAntimatterTool wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 100000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 75000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 50000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 400000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 300000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 200000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_electric_wrenches",
                        "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
            }
            if (main.getToolTypes().contains(BUZZSAW)){
                IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv");
                IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv");
                IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 100000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 75000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 50000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 400000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 300000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 200000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
                addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_buzzsaws",
                        "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
            }
            if (main.getToolTypes().contains(ELECTRIC_SCREWDRIVER)){
                IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_electric_screwdrivers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 100000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallLithium, 'M', MotorLV), "R  ", " RM", "  b");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_electric_screwdrivers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 75000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallCadmium, 'M', MotorLV), "R  ", " RM", "  b");
                addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_electric_screwdrivers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 50000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallSodium, 'M', MotorLV), "R  ", " RM", "  b");
            }
            if (main.getToolTypes().contains(JACKHAMMER)){
                IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_lv");
                addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_jackhammers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 100000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallLithium, 'M', MotorLV), "RbR", " M ", " P ");
                addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_jackhammers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 75000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallCadmium, 'M', MotorLV), "RbR", " M ", " P ");
                addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_jackhammers",
                        "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 50000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallSodium, 'M', MotorLV), "RbR", " M ", " P ");
            }


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

    public static ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = new ItemStack(tool.getItem());
        tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
        Map<Enchantment, Integer> mainEnchants = primary.getEnchantments();
        if (!mainEnchants.isEmpty()) {
            mainEnchants.entrySet().stream().filter(e -> e.getKey().canApply(stack)).forEach(e -> stack.addEnchantment(e.getKey(), e.getValue()));
        }
        return stack;
    }

    @Override
    public String getName() {
        return "GT4R Crafting Recipes";
    }

    private String fixLoc(String attach) {
        return Ref.ID.concat(":").concat(attach);
    }

}
