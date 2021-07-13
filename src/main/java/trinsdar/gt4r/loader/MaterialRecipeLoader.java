package trinsdar.gt4r.loader;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.tags.ITag;
import net.minecraft.util.LazyValue;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

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

    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        final ICriterionInstance in = provider.hasSafeItem(WRENCH.getTag());
        int craftingMultiplier = GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 1 : 2;
        HULL.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_hull", "hulls", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), HULL.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'W', WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_blade", "turbine_blades", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), TURBINE_BLADE.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'H', HAMMER.getTag(), 'F', FILE.getTag()), " H ", "PPP", " F ");
        });
        TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(TURBINE_BLADE) && (m.has(BLOCK) || m == Carbon)){
                ITag.INamedTag<Item> center = m == Carbon ? PLATE.getMaterialTag(Carbon) : BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_rotor", "turbine_rotors", "has_turbine_blade", provider.hasSafeItem(TURBINE_BLADE.getMaterialTag(m)), TURBINE_ROTOR.get(m), ImmutableMap.of('T', TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
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
                ITag.INamedTag<?> input = m.has(GEM) ? GEM.getMaterialTag(m) : INGOT.getMaterialTag(m);
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
                ITag.INamedTag<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
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
                ITag.INamedTag<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_drillbit", "drillbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), DRILLBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
            }
        });
        CHAINSAWBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                ITag.INamedTag<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_chainsawbit", "chainsawbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), CHAINSAWBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel), 'R', RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
            }
        });
        WRENCHBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                ITag.INamedTag<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_wrenchbit", "wrenchbits", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), WRENCHBIT.get(m), ImmutableMap.of('H', HAMMER.getTag(), 'P', plate, 'S', SCREW.getMaterialTag(Steel), 'R', RING.getMaterialTag(Steel), 's', SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
            }
        });
        BUZZSAW_BLADE.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                ITag.INamedTag<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
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
                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(c.getBlock("cobble")), c.getBlock(""), 0.1F, 200).addCriterion("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).build(output, m.getId());
                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(c.getBlock("")), c.getBlock("smooth"), 0.1F, 200).addCriterion("has_stone", provider.hasSafeItem(c.getBlock(""))).build(output, m.getId() + "_smooth");
                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(c.getBlock("bricks")), c.getBlock("bricks_cracked"), 0.1F, 200).addCriterion("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).build(output, m.getId() + "_bricks_cracked");
                String[] types = new String[]{"bricks_mossy", "cobble_mossy", "bricks", "cobble", "smooth", ""};
                for (String type : types){
                    String i = type.isEmpty() ? "" : "_";
                    provider.addStackRecipe(output, Ref.ID, m.getId() + i + type + "_slab", "slabs", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "slab"), 6), of('S', c.getBlock(type)), "SSS");
                    provider.addStackRecipe(output, Ref.ID, m.getId() + i + type + "_stairs", "stairs", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "stairs"), 4), of('S', c.getBlock(type)), "S  ", "SS ", "SSS");
                    provider.addStackRecipe(output, Ref.ID, m.getId() + i + type + "_wall", "walls", "has_stone", provider.hasSafeItem(c.getBlock(type)), new ItemStack(c.getBlock(type + i + "wall"), 6), of('S', c.getBlock(type)), "SSS", "SSS");
                    String[] pattern = type.equals("bricks") ? new String[]{"SS"} : new String[]{"S", "S"};
                    provider.addStackRecipe(output, Ref.ID, m.getId() + i + type + "_from_slabs", "slabs", "has_stone", provider.hasSafeItem(c.getBlock(type + i + "slab")), new ItemStack(c.getBlock(type), 1), of('S', c.getBlock(type + i + "slab")), pattern);
                }
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_bricks", "bricks", "has_stone", provider.hasSafeItem(c.getBlock("")), new ItemStack(c.getBlock("bricks"), 4), of('S', c.getBlock("")), "SS", "SS");
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_bricks_chiseled", "bricks", "has_stone", provider.hasSafeItem(c.getBlock("bricks_slab")), new ItemStack(c.getBlock("bricks_chiseled"), 1), of('S', c.getBlock("bricks_slab")), "S", "S");
                provider.shapeless(output, m.getId() + "_bricks_mossy", "bricks", "has_vines", provider.hasSafeItem(Items.VINE), new ItemStack(c.getBlock("bricks_mossy")), c.getBlock("bricks"), Items.VINE);
                provider.shapeless(output, m.getId() + "_cobble_mossy", "bricks", "has_vines", provider.hasSafeItem(Items.VINE), new ItemStack(c.getBlock("cobble_mossy")), c.getBlock("cobble"), Items.VINE);
                types = new String[]{"stairs", "slab", "wall", "bricks_slab", "bricks_stairs", "bricks_chiseled", "bricks_wall", "bricks"};
                for (String type : types){
                    int amount = type.contains("slab") ? 2 : 1;
                    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("")), c.getBlock(type), amount).addCriterion("has_stone", provider.hasSafeItem(c.getBlock(""))).build(output, m.getId() + "_" + type);
                }
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble")), c.getBlock("cobble_slab"), 2).addCriterion("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).build(output, m.getId() + "_cobble_slab");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble")), c.getBlock("cobble_stairs")).addCriterion("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).build(output, m.getId() + "_cobble_stairs");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble")), c.getBlock("cobble_wall")).addCriterion("has_cobble", provider.hasSafeItem(c.getBlock("cobble"))).build(output, m.getId() + "_cobble_wall");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_slab"), 2).addCriterion("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).build(output, m.getId() + "_cobble_mossy_slab");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_stairs")).addCriterion("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).build(output, m.getId() + "_cobble_mossy_stairs");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("cobble_mossy")), c.getBlock("cobble_mossy_wall")).addCriterion("has_cobble_mossy", provider.hasSafeItem(c.getBlock("cobble_mossy"))).build(output, m.getId() + "_cobble_mossy_wall");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks")), c.getBlock("bricks_slab"), 2).addCriterion("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).build(output, m.getId() + "_bricks_slab2");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks")), c.getBlock("bricks_stairs")).addCriterion("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).build(output, m.getId() + "_bricks_stairs2");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks")), c.getBlock("bricks_wall")).addCriterion("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).build(output, m.getId() + "_bricks_wall2");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks")), c.getBlock("bricks_chiseled")).addCriterion("has_bricks", provider.hasSafeItem(c.getBlock("bricks"))).build(output, m.getId() + "_bricks_chiseled2");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_slab"), 2).addCriterion("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).build(output, m.getId() + "_bricks_mossy_slab");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_stairs")).addCriterion("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).build(output, m.getId() + "_bricks_mossy_stairs");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("bricks_mossy")), c.getBlock("bricks_mossy_wall")).addCriterion("has_bricks_mossy", provider.hasSafeItem(c.getBlock("bricks_mossy"))).build(output, m.getId() + "_bricks_mossy_wall");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("smooth")), c.getBlock("smooth_slab"), 2).addCriterion("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).build(output, m.getId() + "_smooth_slab");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("smooth")), c.getBlock("smooth_stairs")).addCriterion("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).build(output, m.getId() + "_smooth_stairs");
                SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(c.getBlock("smooth")), c.getBlock("smooth_wall")).addCriterion("has_smooth", provider.hasSafeItem(c.getBlock("smooth"))).build(output, m.getId() + "_smooth_wall");
            }
        });
        provider.addToolRecipe(DUST_BUILDER.get(PICKAXE_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "pickaxe_head", "antimatter_dusts",
                "has_wrench", in, PICKAXE_HEAD.all().stream().filter(t -> t.getToolTypes().contains(PICKAXE)).map(t -> PICKAXE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(PICKAXE_HEAD).tool(PICKAXE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(PICKAXE_HEAD).tool(PICKAXE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PII", "F H");
        provider.addToolRecipe(DUST_BUILDER.get(AXE_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "axe_head", "antimatter_dusts",
                "has_wrench", in, AXE_HEAD.all().stream().filter(t -> t.getToolTypes().contains(AXE)).map(t -> AXE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(AXE_HEAD).tool(AXE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(AXE_HEAD).tool(AXE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PIH", "P  ", "F  ");
        provider.addToolRecipe(DUST_BUILDER.get(SHOVEL_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "shovel_head", "antimatter_dusts",
                "has_wrench", in, SHOVEL_HEAD.all().stream().filter(t -> t.getToolTypes().contains(SHOVEL)).map(t -> SHOVEL_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(SHOVEL_HEAD).tool(SHOVEL, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "FPH");
        provider.addToolRecipe(DUST_BUILDER.get(SWORD_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "sword_head", "antimatter_dusts",
                "has_wrench", in, SWORD_HEAD.all().stream().filter(t -> t.getToolTypes().contains(SWORD)).map(t -> SWORD_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(SWORD_HEAD).tool(SWORD, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), " P ", "FPH");
        provider.addToolRecipe(DUST_BUILDER.get(HOE_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "hoe_head", "antimatter_dusts",
                "has_wrench", in, HOE_HEAD.all().stream().filter(t -> t.getToolTypes().contains(HOE)).map(t -> HOE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(HOE_HEAD).tool(HOE, true).build(), 'I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(HOE_HEAD).tool(HOE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PIH", "F  ");
        provider.addToolRecipe(DUST_BUILDER.get(HAMMER_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "hammer_head", "antimatter_dusts",
                "has_wrench", in, HAMMER_HEAD.all().stream().filter(t -> t.getToolTypes().contains(HAMMER)).map(t -> HAMMER_HEAD.get(t,1)).collect(Collectors.toList()), of('I', PropertyIngredient.builder("primary").types(INGOT, GEM).tags(HAMMER_HEAD).tool(HAMMER, true).build(), 'H', HAMMER.getTag()), "II ", "IIH", "II ");
        provider.addToolRecipe(DUST_BUILDER.get(SAW_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "saw_head", "antimatter_dusts",
                "has_wrench", in, SAW_HEAD.all().stream().filter(t -> t.getToolTypes().contains(SAW)).map(t -> SAW_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(SAW_HEAD).tool(SAW, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "PP", "FH");
        provider.addToolRecipe(DUST_BUILDER.get(FILE_HEAD.getId()), output, muramasa.antimatter.Ref.ID, "file_head", "antimatter_dusts",
                "has_wrench", in, FILE_HEAD.all().stream().filter(t -> t.getToolTypes().contains(FILE)).map(t -> FILE_HEAD.get(t,1)).collect(Collectors.toList()), of('P', PropertyIngredient.builder("primary").types(PLATE, GEM).tags(FILE_HEAD).tool(FILE, true).build(), 'H', HAMMER.getTag(), 'F', FILE.getTag()), "FPH", " P ");
    }
}