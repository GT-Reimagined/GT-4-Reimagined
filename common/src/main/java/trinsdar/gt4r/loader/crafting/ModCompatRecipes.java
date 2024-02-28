package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.data.Materials;

import java.util.function.Consumer;

public class ModCompatRecipes {

    public static void loadIE(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addStackRecipe(output, GT4RRef.ID, "treated_wood", "mod_compat", new ItemStack(AntimatterPlatformUtils.getBlockFromId(new ResourceLocation(GT4RRef.MOD_IE, "treated_wood_horizontal")), 8), ImmutableMap.of('P', ItemTags.PLANKS, 'C', Materials.Creosote.getLiquid().getBucket()), "PPP", "PCP", "PPP");
    }

}
