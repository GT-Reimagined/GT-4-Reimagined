package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.Materials;

import java.util.function.Consumer;

public class ModCompatRecipes {

    public static void loadIE(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addStackRecipe(output, Ref.ID, "treated_wood", "mod_compat", "has_wood", provider.hasSafeItem(ItemTags.PLANKS), new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_IE, "treated_wood_horizontal")), 8), ImmutableMap.of('P', ItemTags.PLANKS, 'C', Materials.Creosote.getLiquid().getBucket()), "PPP", "PCP", "PPP");
    }

}
