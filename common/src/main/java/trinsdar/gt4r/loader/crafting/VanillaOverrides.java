package trinsdar.gt4r.loader.crafting;

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
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.Stone;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.Materials.*;

public class VanillaOverrides {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        provider.addItemRecipe(consumer, Ref.ID, "tiny_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "small_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "normal_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, Ref.ID, "large_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', SAW.getTag(), 's', ItemTags.PLANKS, 'H', SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, Ref.ID, "huge_wooden_fluid_pipe", "pipes", "has_saw", provider.hasSafeItem(SAW.getTag()),
                GT4RData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', SAW.getTag(), 's', ItemTags.LOGS, 'H', SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        provider.shapeless(consumer, "sodalite_to_blue_dye", "", "has_sodalite", provider.hasSafeItem(GEM.getMaterialTag(Sodalite)), new ItemStack(Items.BLUE_DYE), GEM.getMaterialTag(Sodalite));
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", "has_sulfur_dust", provider.hasSafeItem(getForgelikeItemTag("dusts/sulfur")),
                new ItemStack(Items.TORCH, 6), of('D', getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), "gt4r-common", "Gameplay.SULFUR_TORCH", Ref.ID, "sulfur_torch");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_helmet", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_HELMET, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_chestplate", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_CHESTPLATE, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_leggings", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_LEGGINGS, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, Ref.ID, "chainmail_boots", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_BOOTS, of('R', RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, Ref.ID, "saddle", "", "has_leather", provider.hasSafeItem(Items.LEATHER), Items.SADDLE,
                of('L', Items.LEATHER, 'R', RING.getMaterialTag(Steel), 'S', SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        provider.addItemRecipe(consumer,  Ref.ID,"sticky_piston_from_resin", "", "has_piston", provider.hasSafeItem(Blocks.PISTON), Blocks.STICKY_PISTON, of('S', GTRubberData.StickyResin, 'P', Blocks.PISTON), "S", "P");
        provider.addStackRecipe(consumer, Ref.ID, "lead_from_resin", "", "has_resin", provider.hasSafeItem(GTRubberData.StickyResin), new ItemStack(Items.LEAD, 2), of('S', Items.STRING, 'R', GTRubberData.StickyResin), "SS ", "SR ", "  S");
        provider.shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", provider.hasSafeItem(MORTAR.getTag()), new ItemStack(Items.FLINT), MORTAR.getTag(), Items.GRAVEL);
        provider.shapeless(consumer, "dust_brick", "mortar_recipes", "has_mortar", provider.hasSafeItem(MORTAR.getTag()), DUST_SMALL.get(Brick, 1), MORTAR.getTag(), Items.BRICK);
        provider.shapeless(consumer, "dust_clay", "mortar_recipes", "has_mortar", provider.hasSafeItem(MORTAR.getTag()), DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        Material[] mats = new Material[]{Bronze, WroughtIron, Aluminium, Steel, Titanium};
        for (Material m : mats){
            provider.addItemRecipe(consumer, Ref.ID, "piston_" + m.getId(), "pistons", "has_" + m.getId(), provider.hasSafeItem(INGOT.getMaterialTag(m)),
                    Items.PISTON, of('W', ItemTags.PLANKS, 'C', ForgeCTags.COBBLESTONE, 'R', DUST.getMaterialTag(Redstone), 'I', INGOT.getMaterialTag(m)), "WWW", "CIC", "CRC");
        }
        String[] colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (String color : colors){
            TagKey<Item> tag = TagUtils.getForgelikeItemTag("dyes/" + color);
            provider.shapeless(consumer, "concrete_" + color, "concretes", "has_dye", provider.hasSafeItem(tag), new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation(color + "_concrete_powder")), 8), tag, Items.SAND, Items.SAND, Items.SAND, Items.SAND, DUST.get(Stone), DUST.get(Stone), DUST.get(Stone), DUST.get(Stone));
        }
        // todo: bucket, minecart, iron door
    }
}
