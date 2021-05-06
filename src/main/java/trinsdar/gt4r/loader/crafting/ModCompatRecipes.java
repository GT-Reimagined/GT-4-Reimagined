package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.Materials;

import java.util.function.Consumer;

public class ModCompatRecipes {

    public static void loadIE(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addStackRecipe(output, Ref.ID, "treated_wood", "mod_compat", "has_wood", provider.hasSafeItem(ItemTags.PLANKS), new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.IE, "treated_wood_horizontal")), 8), ImmutableMap.of('P', ItemTags.PLANKS, 'C', Materials.Creosote.getLiquid().getFilledBucket()), "PPP", "PCP", "PPP");
    }

}
