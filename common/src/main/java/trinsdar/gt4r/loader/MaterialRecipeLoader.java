package trinsdar.gt4r.loader;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.RecipeBuilders.DUST_BUILDER;
import static trinsdar.gt4r.data.Materials.*;

//TODO EXCLUDED FROM COMPILE

public class MaterialRecipeLoader {

    //TODO register purified dust processing to centrifuged processing to regain lost benefits

    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    //TODO: When we do have MaterialInfo or a MaterialType 'amount' system, some of this would need to adapt to it!
    //TODO: Plasma Arc/Normal Arc smelting will be handled differently, when we have said amount system.

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        final CriterionTriggerInstance in = provider.hasSafeItem(WRENCH.getTag());
        int craftingMultiplier = GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 1 : 2;
        if (AntimatterAPI.isModLoaded(Ref.MOD_BLUEPOWER)){
            provider.shapeless(output, "amethyst_gem_convert", "gems", "has_gem", provider.hasSafeItem(GEM.getMaterialTag(Amethyst)), GEM.get(Amethyst, 1), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(Ref.MOD_BLUEPOWER, "amethyst_gem")));
        }
        GT4RMaterialTags.HULL.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_hull", "hulls", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), GT4RMaterialTags.HULL.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'W', WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        GT4RMaterialTags.TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_blade", "turbine_blades", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), GT4RMaterialTags.TURBINE_BLADE.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'H', HAMMER.getTag(), 'F', FILE.getTag()), " H ", "PPP", " F ");
        });
        GT4RMaterialTags.TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(GT4RMaterialTags.TURBINE_BLADE) && (m.has(BLOCK) || m == Carbon)){
                TagKey<Item> center = m == Carbon ? PLATE.getMaterialTag(Carbon) : BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_rotor", "turbine_rotors", "has_turbine_blade", provider.hasSafeItem(GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m)), GT4RMaterialTags.TURBINE_ROTOR.get(m), ImmutableMap.of('T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
            }

        });
        BLOCK.all().forEach(m -> {
            if (m.has(INGOT)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_block", "blocks", "has_ingot", provider.hasSafeItem(INGOT.getMaterialTag(m)), BLOCK.get().get(m).asStack(), ImmutableMap.of('I', INGOT.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"ingot_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(BLOCK.getMaterialTag(m)), INGOT.get(m, 9), BLOCK.getMaterialTag(m));
            } else if (m.has(GEM)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_block", "blocks", "has_gem", provider.hasSafeItem(GEM.getMaterialTag(m)), BLOCK.get().get(m).asStack(), ImmutableMap.of('I', GEM.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"gem_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(BLOCK.getMaterialTag(m)), GEM.get(m, 9), BLOCK.getMaterialTag(m));
            }
        });
        INGOT.all().forEach(m -> {
            if (m.has(NUGGET)){
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_ingot", "ingots", "has_nugget", provider.hasSafeItem(NUGGET.getMaterialTag(m)), INGOT.get(m), ImmutableMap.of('I', NUGGET.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"nugget_" + m.getId() + "_from_ingot", "ingots", "has_ingot", provider.hasSafeItem(INGOT.getMaterialTag(m)), NUGGET.get(m, 9), INGOT.getMaterialTag(m));
            }
        });
        /*DUST.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_dust", "dusts", "has_tiny_dust", provider.hasSafeItem(DUST_TINY.getMaterialTag(m)), DUST.get(m), ImmutableMap.of('I', DUST_TINY.getMaterialTag(m)), "III", "III", "III");
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_dust2", "dusts", "has_small_dust", provider.hasSafeItem(DUST_SMALL.getMaterialTag(m)), DUST.get(m), ImmutableMap.of('I', DUST_SMALL.getMaterialTag(m)), "II", "II");
            provider.addStackRecipe(output, Ref.ID,"tiny_dust_" + m.getId() + "_from_dust", "dusts", "has_dust", provider.hasSafeItem(DUST.getMaterialTag(m)), DUST_TINY.get(m, 9), ImmutableMap.of('I', DUST.getMaterialTag(m)), "I ");
            provider.addStackRecipe(output, Ref.ID,"small_dust_" + m.getId() + "_from_dust", "dusts", "has_dust", provider.hasSafeItem(DUST.getMaterialTag(m)), DUST_SMALL.get(m, 4), ImmutableMap.of('I', DUST.getMaterialTag(m)), " I");
        });*/
        ROD.all().forEach(m -> {
            if (m.has(INGOT) || m.has(GEM)){
                TagKey<?> input = m.has(GEM) ? GEM.getMaterialTag(m) : INGOT.getMaterialTag(m);
                provider.shapeless(output, m.getId() + "_rod", "rods", "has_file", provider.hasSafeItem(FILE.getTag()), ROD.get(m, craftingMultiplier), input, FILE.getTag());
            }
        });
        RING.all().forEach(m -> {
            if (m.has(ROD)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_ring", "rings", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), RING.get(m, craftingMultiplier),
                        ImmutableMap.of('H', HAMMER.getTag(), 'R', ROD.getMaterialTag(m)), "H ", " R");
            }
        });
        GEAR.all().forEach(m -> {
            if ((m.has(PLATE) || m.has(GEM)) && m.has(ROD)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_gear", "gears", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), GEAR.get(m), ImmutableMap.of('P', plate, 'R', ROD.getMaterialTag(m), 'W', WRENCH.getTag()), "RPR", "PWP", "RPR");
            }
        });
        BOLT.all().forEach(m -> {
            if (m.has(SCREW)){
                if (GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING){
                    provider.addItemRecipe(output, Ref.ID, m.getId() + "_screw", "screws", "has_file", provider.hasSafeItem(FILE.getTag()), SCREW.get(m), ImmutableMap.of('F', FILE.getTag(), 'B', BOLT.getMaterialTag(m)), "FB", "B ");
                } else {
                    provider.shapeless(output, m.getId() + "_screw", "screws", "has_file", provider.hasSafeItem(FILE.getTag()), SCREW.get(m, 1), FILE.getTag(), BOLT.getMaterialTag(m));
                }
            }
            provider.addStackRecipe(output, Ref.ID, m.getId() + "_bolt", "bolts", "has_saw", provider.hasSafeItem(SAW.getTag()), BOLT.get(m, craftingMultiplier * 2), ImmutableMap.of('S', SAW.getTag(), 'R', ROD.getMaterialTag(m)), "S ", " R");
        });
        DRILLBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_drillbit", "drillbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), DRILLBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
            }
        });
        CHAINSAWBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_chainsawbit", "chainsawbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), CHAINSAWBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel), 'R', RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
            }
        });
        WRENCHBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_wrenchbit", "wrenchbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), WRENCHBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', SCREW.getMaterialTag(Steel), 'R', RING.getMaterialTag(Steel), 's', SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
            }
        });
        BUZZSAW_BLADE.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_buzzsaw_blade", "buzzsaw_blades", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), BUZZSAW_BLADE.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'F', FILE.getTag(), 'W', WRENCH.getTag(), 'C', WIRE_CUTTER.getTag()), "WPH", "P P", "FPC");
            }
        });
        AntimatterAPI.all(StoneType.class).forEach(s -> {
            Material m = s.getMaterial();
            if (m.has(ROD)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_rod", "rods", "has_stone", provider.hasSafeItem(s.getState().getBlock()), ROD.get(m, 4), ImmutableMap.of('S', s.getState().getBlock()), "S", "S");
                if (s == STONE){
                    provider.addStackRecipe(output, Ref.ID, m.getId() + "_rod", "rods", "has_stone", provider.hasSafeItem(Items.COBBLESTONE), ROD.get(m, 4), ImmutableMap.of('S', Items.COBBLESTONE), "S", "S");
                }
                if (s instanceof CobbleStoneType){
                    provider.addStackRecipe(output, Ref.ID, m.getId() + "_rod", "rods", "has_stone", provider.hasSafeItem(((CobbleStoneType)s).getBlock("cobble")), ROD.get(m, 4), ImmutableMap.of('S', ((CobbleStoneType)s).getBlock("cobble")), "S", "S");
                }
            }
            if (s instanceof CobbleStoneType){
                CobbleStoneType c = (CobbleStoneType) s;
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(c.getBlock("cobble")), c.getBlock(""), 0.1F, 200).unlockedBy("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).save(output, m.getId() + "_stone");
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(c.getBlock("")), c.getBlock("smooth"), 0.1F, 200).unlockedBy("has_stone", provider.hasSafeItem(c.getBlock(""))).save(output, m.getId() + "_smooth");
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(c.getBlock("bricks")), c.getBlock("bricks_cracked"), 0.1F, 200).unlockedBy("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).save(output, m.getId() + "_bricks_cracked");
                String[] types = new String[]{"bricks_mossy", "cobble_mossy", "bricks", "cobble", "smooth", ""};
                for (String type : types){
                    String i = type.isEmpty() ? "" : "_";
                    provider.addStackRecipe(output, Ref.ID, "slab_" + m.getId() + i + type, "slabs", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "slab"), 6), of('S', c.getBlock(type)), "SSS");
                    provider.addStackRecipe(output, Ref.ID, "stairs_" + m.getId() + i + type, "stairs", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "stairs"), 4), of('S', c.getBlock(type)), "S  ", "SS ", "SSS");
                    provider.addStackRecipe(output, Ref.ID, "wall_" + m.getId() + i + type, "walls", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "wall"), 6), of('S', c.getBlock(type)), "SSS", "SSS");
                    String[] pattern = type.equals("bricks") ? new String[]{"SS"} : new String[]{"S", "S"};
                    provider.addStackRecipe(output, Ref.ID, m.getId() + i + type + "_from_slabs", "slabs", "has_stone", provider.hasSafeItem(c.getBlock(type + i + "slab")), new ItemStack(c.getBlock(type), 1), of('S', c.getBlock(type + i + "slab")), pattern);
                }
                provider.addStackRecipe(output, Ref.ID, "bricks_" + m.getId(), "bricks", "has_stone", provider.hasSafeItem(c.getBlock("")), new ItemStack(c.getBlock("bricks"), 4), of('S', c.getBlock("")), "SS", "SS");
                provider.addStackRecipe(output, Ref.ID, "bricks_chiseled_" + m.getId(), "bricks", "has_stone", provider.hasSafeItem(c.getBlock("bricks_slab")), new ItemStack(c.getBlock("bricks_chiseled"), 1), of('S', c.getBlock("bricks_slab")), "S", "S");
                provider.shapeless(output, "bricks_mossy_" + m.getId(), "bricks", "has_vines", provider.hasSafeItem(Items.VINE), new ItemStack(c.getBlock("bricks_mossy")), c.getBlock("bricks"), Items.VINE);
                provider.shapeless(output, "cobble_mossy_" + m.getId(), "bricks", "has_vines", provider.hasSafeItem(Items.VINE), new ItemStack(c.getBlock("cobble_mossy")), c.getBlock("cobble"), Items.VINE);
                types = new String[]{"stairs", "slab", "wall", "bricks_slab", "bricks_stairs", "bricks_chiseled", "bricks_wall", "bricks"};
                for (String type : types){
                    int amount = type.contains("slab") ? 2 : 1;
                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("")), c.getBlock(type), amount).unlockedBy("has_stone", provider.hasSafeItem(c.getBlock(""))).save(output, m.getId() + "_stone_" + type);
                }
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble")), c.getBlock("cobble_slab"), 2).unlockedBy("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).save(output, m.getId() + "_cobble_slab");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble")), c.getBlock("cobble_stairs")).unlockedBy("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).save(output, m.getId() + "_cobble_stairs");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble")), c.getBlock("cobble_wall")).unlockedBy("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).save(output, m.getId() + "_cobble_wall");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_slab"), 2).unlockedBy("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).save(output, m.getId() + "_cobble_mossy_slab");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_stairs")).unlockedBy("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).save(output, m.getId() + "_cobble_mossy_stairs");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_wall")).unlockedBy("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).save(output, m.getId() + "_cobble_mossy_wall");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks")), c.getBlock("bricks_slab"), 2).unlockedBy("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).save(output, m.getId() + "_bricks_slab2");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks")), c.getBlock("bricks_stairs")).unlockedBy("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).save(output, m.getId() + "_bricks_stairs2");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks")), c.getBlock("bricks_wall")).unlockedBy("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).save(output, m.getId() + "_bricks_wall2");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks")), c.getBlock("bricks_chiseled")).unlockedBy("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).save(output, m.getId() + "_bricks_chiseled2");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_slab"), 2).unlockedBy("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).save(output, m.getId() + "_bricks_mossy_slab");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_stairs")).unlockedBy("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).save(output, m.getId() + "_bricks_mossy_stairs");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_wall")).unlockedBy("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).save(output, m.getId() + "_bricks_mossy_wall");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("smooth")), c.getBlock("smooth_slab"), 2).unlockedBy("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).save(output, m.getId() + "_smooth_slab");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("smooth")), c.getBlock("smooth_stairs")).unlockedBy("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).save(output, m.getId() + "_smooth_stairs");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(c.getBlock("smooth")), c.getBlock("smooth_wall")).unlockedBy("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).save(output, m.getId() + "_smooth_wall");
            }
        });
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.PICKAXE_HEAD.getId()), output, Ref.ANTIMATTER, "pickaxe_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.PICKAXE_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(PICKAXE)).map(t -> GT4RMaterialTags.PICKAXE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.PICKAXE_HEAD).tool(PICKAXE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(GT4RMaterialTags.PICKAXE_HEAD).tool(PICKAXE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PII", "F H");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.AXE_HEAD.getId()), output, Ref.ANTIMATTER, "axe_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.AXE_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(AXE)).map(t -> GT4RMaterialTags.AXE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.AXE_HEAD).tool(AXE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(GT4RMaterialTags.AXE_HEAD).tool(AXE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PIH", "P  ", "F  ");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.SHOVEL_HEAD.getId()), output, Ref.ANTIMATTER, "shovel_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.SHOVEL_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(SHOVEL)).map(t -> GT4RMaterialTags.SHOVEL_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.SHOVEL_HEAD).tool(SHOVEL, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "FPH");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.SWORD_HEAD.getId()), output, Ref.ANTIMATTER, "sword_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.SWORD_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(SWORD)).map(t -> GT4RMaterialTags.SWORD_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.SWORD_HEAD).tool(SWORD, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), " P ", "FPH");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.HOE_HEAD.getId()), output, Ref.ANTIMATTER, "hoe_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.HOE_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(HOE)).map(t -> GT4RMaterialTags.HOE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.HOE_HEAD).tool(HOE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(GT4RMaterialTags.HOE_HEAD).tool(HOE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PIH", "F  ");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.HAMMER_HEAD.getId()), output, Ref.ANTIMATTER, "hammer_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.HAMMER_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(HAMMER)).map(t -> GT4RMaterialTags.HAMMER_HEAD.get(t,1)).collect(Collectors.toList()), of('I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(GT4RMaterialTags.HAMMER_HEAD).tool(HAMMER, true).build(), 'H', HAMMER.getTag()), "II ", "IIH", "II ");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.SAW_HEAD.getId()), output, Ref.ANTIMATTER, "saw_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.SAW_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(SAW)).map(t -> GT4RMaterialTags.SAW_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.SAW_HEAD).tool(SAW, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PP", "FH");
        provider.addToolRecipe(DUST_BUILDER.get(GT4RMaterialTags.FILE_HEAD.getId()), output, Ref.ANTIMATTER, "file_head", "antimatter_dusts",
                "has_wrench", in, GT4RMaterialTags.FILE_HEAD.all().stream().filter(t -> MaterialTags.TOOLS.getToolData(t).toolTypes().contains(FILE)).map(t -> GT4RMaterialTags.FILE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(GT4RMaterialTags.FILE_HEAD).tool(FILE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "FPH", " P ");
    }
}