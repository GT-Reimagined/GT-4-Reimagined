package trinsdar.gt4r.loader.crafting;

import io.github.gregtechintergalactical.gtcore.GTCoreConfig;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.GT4RRef;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.SAW;

public class WoodCrafting {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, provider, GT4RRef.MOD_TERRESTRIA, TagUtils.getItemTag(new ResourceLocation(GT4RRef.MOD_TERRESTRIA, woodType + "_logs")), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_TERRESTRIA, woodType + "_planks")));
            }

        }
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                addWoodRecipe(consumer, provider, GT4RRef.MOD_CINDERSCAPES, TagUtils.getItemTag(new ResourceLocation(GT4RRef.MOD_CINDERSCAPES, woodType + "_stems")), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_CINDERSCAPES, woodType + "_planks")));
            }

        }
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_TERRESTRIA)){
            String[] woodTypes = {"cypress", "hemlock", "japanese_maple", "rainbow_eucalyptus", "redwood", "rubber", "sakura", "willow", "yucca_palm"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(GT4RRef.MOD_TERRESTRIA,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(GT4RRef.MOD_TERRESTRIA,woodType + "_slab");
                provider.addItemRecipe(consumer, GT4RRef.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", AntimatterPlatformUtils.getItemFromID(name), of('S', AntimatterPlatformUtils.getItemFromID(slab)), "S", "S");
            }

        }
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_CINDERSCAPES)){
            String[] woodTypes = {"scorched", "umbral"};
            for (String woodType : woodTypes) {
                ResourceLocation name = new ResourceLocation(GT4RRef.MOD_CINDERSCAPES,woodType + "_planks");
                ResourceLocation slab = new ResourceLocation(GT4RRef.MOD_CINDERSCAPES,woodType + "_slab");
                provider.addItemRecipe(consumer, GT4RRef.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", AntimatterPlatformUtils.getItemFromID(name), of('S', AntimatterPlatformUtils.getItemFromID(slab)), "S", "S");
            }
        }
    }

    public static void addWoodRecipe(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider, String domain, TagKey<Item> log, Item plank){
        if (GTCoreConfig.HARDER_WOOD.get()){
            provider.shapeless(consumer, domain, "", "planks", new ItemStack(plank, 2), log);
            provider.addStackRecipe(consumer, domain, plank.getRegistryName().getPath() + "_4", "planks", new ItemStack(plank, 4), of('S', SAW.getTag(), 'P', log), "S", "P");
        }
    }
}
