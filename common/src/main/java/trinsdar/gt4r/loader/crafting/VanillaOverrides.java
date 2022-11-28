package trinsdar.gt4r.loader.crafting;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RData;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Stone;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.Materials.*;

public class VanillaOverrides {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        provider.addItemRecipe(consumer, Ref.ID, "tiny_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(AntimatterDefaultTools.SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "small_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(AntimatterDefaultTools.SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "normal_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(AntimatterDefaultTools.SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "large_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(AntimatterDefaultTools.SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, Ref.ID, "huge_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(AntimatterDefaultTools.SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        provider.shapeless(consumer, "sodalite_to_blue_dye", "", "has_sodalite", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(Sodalite)), new ItemStack(Items.BLUE_DYE), AntimatterMaterialTypes.GEM.getMaterialTag(Sodalite));
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", "has_sulfur_dust", provider.hasSafeItem(getForgelikeItemTag("dusts/sulfur")),
                new ItemStack(Items.TORCH, 6), of('D', getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), "gt4r-common", "Gameplay.SULFUR_TORCH", Ref.ID, "sulfur_torch");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_helmet", "chainmail_armor", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                Items.CHAINMAIL_HELMET, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_chestplate", "chainmail_armor", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                Items.CHAINMAIL_CHESTPLATE, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_leggings", "chainmail_armor", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                Items.CHAINMAIL_LEGGINGS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_boots", "chainmail_armor", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                Items.CHAINMAIL_BOOTS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', AntimatterDefaultTools.HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, Ref.ID, "saddle", "", "has_leather", provider.hasSafeItem(Items.LEATHER), Items.SADDLE,
                of('L', Items.LEATHER, 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        provider.addItemRecipe(consumer,  Ref.ID,"sticky_piston_from_resin", "", "has_piston", provider.hasSafeItem(Blocks.PISTON), Blocks.STICKY_PISTON, of('S', GTRubberData.StickyResin, 'P', Blocks.PISTON), "S", "P");
        provider.addStackRecipe(consumer, Ref.ID, "lead_from_resin", "", "has_resin", provider.hasSafeItem(GTRubberData.StickyResin), new ItemStack(Items.LEAD, 2), of('S', Items.STRING, 'R', GTRubberData.StickyResin), "SS ", "SR ", "  S");
        provider.shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", provider.hasSafeItem(AntimatterDefaultTools.MORTAR.getTag()), new ItemStack(Items.FLINT), AntimatterDefaultTools.MORTAR.getTag(), Items.GRAVEL);
        provider.shapeless(consumer, "dust_brick", "mortar_recipes", "has_mortar", provider.hasSafeItem(AntimatterDefaultTools.MORTAR.getTag()), AntimatterMaterialTypes.DUST_SMALL.get(Brick, 1), AntimatterDefaultTools.MORTAR.getTag(), Items.BRICK);
        provider.shapeless(consumer, "dust_clay", "mortar_recipes", "has_mortar", provider.hasSafeItem(AntimatterDefaultTools.MORTAR.getTag()), AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), AntimatterDefaultTools.MORTAR.getTag(), Items.CLAY_BALL);
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            provider.addItemRecipe(consumer, Ref.ID, "piston_" + m.getId(), "pistons", "has_" + m.getId(), provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(m)),
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', ForgeCTags.COBBLESTONE, 'R', DUST.getMaterialTag(AntimatterMaterials.Redstone), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        String[] colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (String color : colors){
            TagKey<Item> tag = TagUtils.getForgelikeItemTag("dyes/" + color);
            provider.shapeless(consumer, "concrete_" + color, "concretes", "has_dye", provider.hasSafeItem(tag), new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation(color + "_concrete_powder")), 8), tag, Items.SAND, Items.SAND, Items.SAND, Items.SAND, DUST.get(Stone), DUST.get(Stone), DUST.get(Stone), DUST.get(Stone));
        }
        // todo: bucket, minecart, iron door

        provider.addItemRecipe(consumer, "minecraft", "", "misc", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.BUCKET, of('I', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag()), "IHI", " I ");
        provider.addItemRecipe(consumer, "minecraft", "", "misc", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.HOPPER, of('I', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'W', AntimatterDefaultTools.WRENCH.getTag(), 'C', ForgeCTags.CHESTS), "IWI", "ICI", " I ");


        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_diamond", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)),
                Items.DIAMOND_BOOTS, of('X', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'H', AntimatterDefaultTools.HAMMER.getTag()), "X X", "XHX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_diamond", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)),
                Items.DIAMOND_LEGGINGS, of('X', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX", "X X");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_diamond", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)),
                Items.DIAMOND_CHESTPLATE, of('X', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XHX", "XXX", "XXX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_diamond", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond)),
                Items.DIAMOND_HELMET, of('X', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX");

        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_gold", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold)),
                Items.GOLDEN_BOOTS, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold), 'H', AntimatterDefaultTools.HAMMER.getTag()), "X X", "XHX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_gold", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold)),
                Items.GOLDEN_LEGGINGS, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX", "X X");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_gold", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold)),
                Items.GOLDEN_CHESTPLATE, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XHX", "XXX", "XXX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_gold", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold)),
                Items.GOLDEN_HELMET, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Gold), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX");

        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.IRON_BOOTS, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag()), "X X", "XHX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.IRON_LEGGINGS, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX", "X X");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.IRON_CHESTPLATE, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XHX", "XXX", "XXX");
        provider.addItemRecipe(consumer, "minecraft", "", "material_armor", "has_iron", provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)),
                Items.IRON_HELMET, of('X', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'H', AntimatterDefaultTools.HAMMER.getTag()), "XXX", "XHX");
    }
}
