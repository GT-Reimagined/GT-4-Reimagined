package trinsdar.gt4r.datagen;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.PipeType;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.enchantment.Enchantment;
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
import static muramasa.antimatter.material.MaterialTag.FLUIDPIPE;
import static muramasa.antimatter.material.MaterialTag.GRINDABLE;
import static muramasa.antimatter.material.MaterialTag.RUBBERTOOLS;
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
        registerMaterialRecipes(consumer, this.providerDomain);
        addConditionalRecipe(consumer, getStackRecipe("", "has_sulfur_dust", criterion(getForgeItemTag("dusts/sulfur"), this),
                new ItemStack(Blocks.TORCH, 6), of('D', getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");




        addItemRecipe(consumer,  Ref.ID,"sticky_piston_from_resin", "", "has_piston", criterion(Blocks.PISTON, this), Blocks.STICKY_PISTON, of('S', GT4RData.StickyResin, 'P', Blocks.PISTON), "S", "P");
        shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", hasSafeItem(MORTAR.getTag()), new ItemStack(Items.FLINT), MORTAR.getTag(), Items.GRAVEL);
    }

    @Override
    protected void registerPipeRecipes(Consumer<IFinishedRecipe> consumer, String providerDomain) {
        if (providerDomain.equals(muramasa.antimatter.Ref.ID)) {
            final ICriterionInstance in = this.hasSafeItem(WRENCH.getTag());
            final Map<Class, MaterialTag> tags = ImmutableMap.of(ItemPipe.class, MaterialTag.ITEMPIPE, FluidPipe.class, FLUIDPIPE);
            for (Map.Entry<Class<? extends PipeType>, String> c : ImmutableMap.of(ItemPipe.class, "item", FluidPipe.class, "fluid").entrySet()) {
                List<ItemStack> stacks = AntimatterAPI.all(c.getKey()).stream().filter(t -> t.getSizes().contains(PipeSize.TINY)).filter(t -> t.getMaterial().has(PLATE)).map(t -> new ItemStack(t.getBlock(PipeSize.TINY), 6)).collect(Collectors.toList());
                if (stacks.size() > 0) addToolRecipe(GT4RData.PIPE_BUILDER.apply(c.getValue(), PipeSize.TINY, c.getKey()),  consumer, muramasa.antimatter.Ref.ID, "pipe_"+c.getValue()+ "_" + PipeSize.TINY.getId(), "antimatter_pipes",
                        "has_wrench", in, stacks, of('H', HAMMER.getTag(), 'W', WRENCH.getTag(), 'P', PropertyIngredient.builder("primary").types(PLATE).tags(tags.get(c.getKey())).build()), "PPP", "H W", "PPP");
                stacks = AntimatterAPI.all(c.getKey()).stream().filter(t -> t.getSizes().contains(PipeSize.SMALL)).filter(t -> t.getMaterial().has(PLATE)).map(t -> new ItemStack(t.getBlock(PipeSize.SMALL), 6)).collect(Collectors.toList());
                if (stacks.size() > 0) addToolRecipe(GT4RData.PIPE_BUILDER.apply(c.getValue(), PipeSize.SMALL, c.getKey()),  consumer, muramasa.antimatter.Ref.ID, "pipe_"+c.getValue()+ "_" + PipeSize.SMALL.getId(), "antimatter_pipes",
                        "has_wrench", in, stacks, of('H', HAMMER.getTag(), 'W', WRENCH.getTag(), 'P', PropertyIngredient.builder("primary").types(PLATE).tags(tags.get(c.getKey())).build()), "PWP", "P P", "PHP");
                stacks = AntimatterAPI.all(c.getKey()).stream().filter(t -> t.getSizes().contains(PipeSize.NORMAL)).filter(t -> t.getMaterial().has(PLATE)).map(t -> new ItemStack(t.getBlock(PipeSize.NORMAL), 2)).collect(Collectors.toList());
                if (stacks.size() > 0) addToolRecipe(GT4RData.PIPE_BUILDER.apply(c.getValue(), PipeSize.NORMAL, c.getKey()),  consumer, muramasa.antimatter.Ref.ID, "pipe_"+c.getValue()+ "_" + PipeSize.NORMAL.getId(), "antimatter_pipes",
                        "has_wrench", in, stacks, of('H', HAMMER.getTag(), 'W', WRENCH.getTag(), 'P', PropertyIngredient.builder("primary").types(PLATE).tags(tags.get(c.getKey())).build()), "PPP", "W H", "PPP");
                stacks = AntimatterAPI.all(c.getKey()).stream().filter(t -> t.getSizes().contains(PipeSize.LARGE)).filter(t -> t.getMaterial().has(PLATE)).map(t -> new ItemStack(t.getBlock(PipeSize.LARGE), 6)).collect(Collectors.toList());
                if (stacks.size() > 0) addToolRecipe(GT4RData.PIPE_BUILDER.apply(c.getValue(), PipeSize.LARGE, c.getKey()),  consumer, muramasa.antimatter.Ref.ID, "pipe_"+c.getValue()+ "_" + PipeSize.LARGE.getId(), "antimatter_pipes",
                        "has_wrench", in, stacks, of('H', HAMMER.getTag(), 'W', WRENCH.getTag(), 'P', PropertyIngredient.builder("primary").types(PLATE).tags(tags.get(c.getKey())).build()), "PHP", "P P", "PWP");
            }
        }
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
        //Temp override till bug in new recipe system gets fixed
        List<Material> mainMats = AntimatterAPI.all(Material.class, providerDomain).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.has(TOOLS))).collect(Collectors.toList());
        List<Material> armorMats = AntimatterAPI.all(Material.class, providerDomain).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.has(ARMOR))).collect(Collectors.toList());
        List<Material> handleMats = AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.isHandle())).collect(Collectors.toList());

        handleMats.forEach(handle -> AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.has(RUBBERTOOLS))).forEach(rubber -> {
            ITag<Item> ingotTag = TagUtils.getForgeItemTag("ingots/" + rubber.getId()), rodTag = TagUtils.getForgeItemTag("rods/" + handle.getId());
            addStackRecipe(consumer, Ref.ID, PLUNGER.getId() + "_" + handle.getId() + "_" + rubber.getId(), "antimatter_plungers",
                    "has_material_" + rubber.getId(), hasSafeItem(ingotTag), PLUNGER.getToolStack(handle, rubber),
                    of('W', WIRE_CUTTER.getTag(), 'I', ingotTag, 'S', Tags.Items.SLIMEBALLS, 'R', rodTag, 'F', FILE.getTag()), "WIS", " RI", "R F");
        }));

        armorMats.forEach(armor -> {
            if (!armor.has(INGOT) && !armor.has(GEM)) return; // TODO: For time being
            final ITag<Item> inputTag;
            if (armor.has(INGOT)){
                inputTag = INGOT.getMaterialTag(armor);
            } else {
                inputTag = GEM.getMaterialTag(armor);
            }
            final ICriterionInstance inputTrigger = this.hasSafeItem(inputTag);
            addStackRecipe(consumer, Ref.ID, HELMET.getId() + "_" + armor.getId(), "antimatter_helmets",
                    "has_material_" + armor.getId(), inputTrigger, HELMET.getToolStack(armor), of('I', inputTag, 'H', HAMMER.getTag()), "III", "IHI");
            addStackRecipe(consumer, Ref.ID, CHESTPLATE.getId() + "_" + armor.getId(), "antimatter_chestplates",
                    "has_material_" + armor.getId(), inputTrigger, CHESTPLATE.getToolStack(armor), of('I', inputTag, 'H', HAMMER.getTag()), "IHI", "III", "III");
            addStackRecipe(consumer, Ref.ID, LEGGINGS.getId() + "_" + armor.getId(), "antimatter_leggings",
                    "has_material_" + armor.getId(), inputTrigger, LEGGINGS.getToolStack(armor), of('I', inputTag, 'H', HAMMER.getTag()), "III", "IHI", "I I");
            addStackRecipe(consumer, Ref.ID, BOOTS.getId() + "_" + armor.getId(), "antimatter_boots",
                    "has_material_" + armor.getId(), inputTrigger, BOOTS.getToolStack(armor), of('I', inputTag, 'H', HAMMER.getTag()), "I I", "IHI");
        });

        mainMats.forEach(main -> {
            if (!main.has(INGOT) && !main.has(GEM)) return; // TODO: For time being
            String ingotGem = main.has(INGOT) ? "ingots" : "gems";
            String plate = main.has(PLATE) ? "plates" : ingotGem;
            final ITag<Item> ingotTag = TagUtils.getForgeItemTag(ingotGem + "/" + main.getId()), plateTag = TagUtils.getForgeItemTag(plate + "/" + main.getId()), mainRodTag = TagUtils.getForgeItemTag("rods/" + main.getId());
            final ICriterionInstance ingotTrigger = this.hasSafeItem(ingotTag), plateTrigger = this.hasSafeItem(plateTag), rodTrigger = this.hasSafeItem(mainRodTag);

            if (main.getToolTypes().contains(WRENCH))
                addStackRecipe(consumer, Ref.ID, WRENCH.getId() + "_" + main.getId(), "antimatter_wrenches",
                        "has_material_" + main.getId(), ingotTrigger, WRENCH.getToolStack(main, NULL), of('I', ingotTag, 'H', HAMMER.getTag()), "IHI", "III", " I ");

            if (main.getToolTypes().contains(MORTAR))
                addStackRecipe(consumer, Ref.ID, MORTAR.getId() + "_" + main.getId(), "antimatter_mortars",
                        "has_material_" + main.getId(), ingotTrigger, MORTAR.getToolStack(main, NULL), of('I', ingotTag, 'S', Tags.Items.STONE), " I ", "SIS", "SSS");

            if (main.getToolTypes().contains(CROWBAR)) {
                for (DyeColor colour : DyeColor.values()) {
                    int colourValue = colour.getMapColor().colorValue;
                    ItemStack crowbarStack = CROWBAR.getToolStack(main, NULL);
                    crowbarStack.getChildTag(muramasa.antimatter.Ref.TAG_TOOL_DATA).putInt(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_COLOUR, colourValue);
                    addStackRecipe(consumer, Ref.ID, CROWBAR.getId() + "_" + main.getId() + "_" + colour.toString(), "antimatter_crowbars",
                            "has_material_" + main.getId(), rodTrigger, crowbarStack, of('H', HAMMER.getTag(), 'C', colour.getTag(), 'R', mainRodTag, 'F', FILE.getTag()), "HCR", "CRC", "RCF");
                }
            }

            for (Material handle : handleMats) {
                String handleId = handle.getId().equals("wood") ? "wooden" : handle.getId();
                final ITag<Item> rodTag = TagUtils.getForgeItemTag("rods/" + handleId);

                ImmutableMap<Character, Object> map1 = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? of('I', ingotTag, 'R', rodTag, 'P', plateTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()) : of('I', ingotTag, 'R', rodTag, 'P', plateTag);
                ImmutableMap<Character, Object> map2 = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? of('R', rodTag, 'P', plateTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()) : of('R', rodTag, 'P', plateTag);
                String[] strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{"PII", "FRH", " R "} : new String[]{"PII", " R ", " R "};
                if (main.getToolTypes().contains(PICKAXE))
                    addStackRecipe(consumer, Ref.ID, PICKAXE.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_pickaxes",
                            "has_material_" + main.getId(), ingotTrigger, PICKAXE.getToolStack(main, handle), map1, strings);
                strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{" FP", " RH", "R  "} : new String[]{"  P", " R ", "R  "};
                if (main.getToolTypes().contains(PICKAXE))
                    addStackRecipe(consumer, muramasa.antimatter.Ref.ID, SPEAR.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_spears",
                        "has_material_" + main.getId(), ingotTrigger, SPEAR.getToolStack(main, handle), map2, strings);
                strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{"IIH", "PR ", "FR "} : new String[]{"II", "PR", " R"};
                if (main.getToolTypes().contains(AXE))
                    addStackRecipe(consumer, Ref.ID, AXE.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_axes",
                            "has_material_" + main.getId(), ingotTrigger, AXE.getToolStack(main, handle), map1, strings);
                strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{"PIH", "FR ", " R "} : new String[]{"PI", " R", " R"};
                if (main.getToolTypes().contains(HOE))
                    addStackRecipe(consumer, Ref.ID, HOE.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_hoes",
                            "has_material_" + main.getId(), ingotTrigger, HOE.getToolStack(main, handle), map1, strings);
                strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{"FPH", " R ", " R "} : new String[]{"P", "R", "R"};
                if (main.getToolTypes().contains(SHOVEL))
                    addStackRecipe(consumer, Ref.ID, SHOVEL.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_shovels",
                            "has_material_" + main.getId(), ingotTrigger, SHOVEL.getToolStack(main, handle), map2, strings);
                strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{" P ", "FPH", " R "} : new String[]{"P", "P", "R"};
                if (main.getToolTypes().contains(SWORD))
                    addStackRecipe(consumer, Ref.ID, SWORD.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_swords",
                            "has_material_" + main.getId(), ingotTrigger, SWORD.getToolStack(main, handle), map2, strings);

                if (main.getToolTypes().contains(HAMMER))
                    addStackRecipe(consumer, Ref.ID, HAMMER.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_hammers",
                            "has_material_" + main.getId(), ingotTrigger, HAMMER.getToolStack(main, handle), of('I', ingotTag, 'R', rodTag), "II ", "IIR", "II ");

                if (main.getToolTypes().contains(SAW))
                    addStackRecipe(consumer, Ref.ID, SAW.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_saws",
                            "has_material_" + main.getId(), plateTrigger, SAW.getToolStack(main, handle), of('P', plateTag, 'R', rodTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()), "PPR", "FH ");

                if (main.getToolTypes().contains(FILE))
                    addStackRecipe(consumer, Ref.ID, FILE.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_files",
                            "has_material_" + main.getId(), plateTrigger, FILE.getToolStack(main, handle), of('P', plateTag, 'R', rodTag), "P", "P", "R");

                if (main.getToolTypes().contains(KNIFE))
                    addStackRecipe(consumer, Ref.ID, KNIFE.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_knives",
                            "has_material_" + main.getId(), plateTrigger, KNIFE.getToolStack(main, handle), of('P', plateTag, 'R', rodTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()), "FPH", " R ");

                if (main.getToolTypes().contains(SCREWDRIVER))
                    addStackRecipe(consumer, Ref.ID, SCREWDRIVER.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_screwdrivers",
                            "has_material_" + main.getId(), rodTrigger, SCREWDRIVER.getToolStack(main, handle),
                            of('M', mainRodTag, 'R', rodTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()), " FM", " MH", "R  ");

                if (main.getToolTypes().contains(WIRE_CUTTER)) {
                    if (main.has(SCREW)){
                        addStackRecipe(consumer, Ref.ID, WIRE_CUTTER.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_wire_cutters",
                                "has_material_" + main.getId(), plateTrigger, WIRE_CUTTER.getToolStack(main, handle),
                                b -> b.put('P', plateTag).put('R', rodTag).put('F', FILE.getTag()).put('H', HAMMER.getTag())
                                        .put('S', SCREWDRIVER.getTag()).put('W', getForgeItemTag("screws/" + main.getId())), "PFP", "HPS", "RWR");
                    } else {
                        addStackRecipe(consumer, Ref.ID, WIRE_CUTTER.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_wire_cutters",
                                "has_material_" + main.getId(), plateTrigger, WIRE_CUTTER.getToolStack(main, handle),
                                b -> b.put('P', plateTag).put('R', rodTag).put('F', FILE.getTag()).put('H', HAMMER.getTag())
                                        .put('S', SCREWDRIVER.getTag()), "PFP", "HPS", "R R");
                    }
                }
            }
        });

        /*super.registerToolRecipes(consumer, providerDomain);
        List<Material> mainMats = AntimatterAPI.all(Material.class, providerDomain).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.has(TOOLS))).collect(Collectors.toList());
        List<Material> handleMats = AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(providerDomain) && m.isHandle())).collect(Collectors.toList());

        mainMats.forEach(main -> {
            if (!main.has(INGOT) && !main.has(GEM)) return; // TODO: For time being
            String ingotGem = main.has(INGOT) ? "ingots" : "gems";
            String plate = main.has(PLATE) ? "plates" : ingotGem;
            final ITag<Item> ingotTag = TagUtils.getForgeItemTag(ingotGem + "/" + main.getId()), plateTag = TagUtils.getForgeItemTag(plate + "/" + main.getId()), mainRodTag = TagUtils.getForgeItemTag("rods/" + main.getId());
            final ICriterionInstance ingotTrigger = this.hasSafeItem(ingotTag), plateTrigger = this.hasSafeItem(plateTag), rodTrigger = this.hasSafeItem(mainRodTag);

            for (Material handle : handleMats) {
                String handleId = handle.getId().equals("wood") ? "wooden" : handle.getId();
                final ITag<Item> rodTag = TagUtils.getForgeItemTag("rods/" + handleId);

                ImmutableMap<Character, Object> map1 = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? of('R', rodTag, 'P', plateTag, 'F', FILE.getTag(), 'H', HAMMER.getTag()) : of('R', rodTag, 'P', plateTag);
                String[] strings = main.getToolTypes().contains(HAMMER) && main.getToolTypes().contains(FILE) ? new String[]{" FP", " RH", "R  "} : new String[]{"  P", " R ", "R  "};
                if (main.getToolTypes().contains(PICKAXE)){}
                    addStackRecipe(consumer, muramasa.antimatter.Ref.ID, SPEAR.getId() + "_" + main.getId() + "_" + handle.getId(), "antimatter_spears",
                            "has_material_" + main.getId(), ingotTrigger, SPEAR.getToolStack(main, handle), map1, strings);
            }
        });*/
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
