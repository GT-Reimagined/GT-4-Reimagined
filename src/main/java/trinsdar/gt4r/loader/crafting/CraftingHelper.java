package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

// This class is just so I can do recipes in both 1.15 and 1.16 without changing anything when they get merged into 1.16
public class CraftingHelper {
    public static ICriterionInstance criterion(IItemProvider item, AntimatterRecipeProvider provider){
        return provider.hasItem(item);
    }
}
