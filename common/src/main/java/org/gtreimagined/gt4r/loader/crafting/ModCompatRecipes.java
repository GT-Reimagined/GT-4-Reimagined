package org.gtreimagined.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.RegistryUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.Materials;

import java.util.function.Consumer;

public class ModCompatRecipes {

    public static void loadIE(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addStackRecipe(output, GT4RRef.ID, "treated_wood", "mod_compat", new ItemStack(RegistryUtils.getBlockFromId(new ResourceLocation(GT4RRef.MOD_IE, "treated_wood_horizontal")), 8), ImmutableMap.of('P', ItemTags.PLANKS, 'C', Materials.Creosote.getLiquid().getBucket()), "PPP", "PCP", "PPP");
    }

}
