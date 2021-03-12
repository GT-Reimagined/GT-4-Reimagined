package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

// This class is just so I can do recipes in both 1.15 and 1.16 without changing anything when they get merged into 1.16
public class CraftingHelper {
    public static Supplier<ICriterionInstance> criterion(IItemProvider item, AntimatterRecipeProvider provider){
        return provider.hasSafeItem(item);
    }

    public static Supplier<ICriterionInstance> criterion(ITag.INamedTag<Item> item, AntimatterRecipeProvider provider){
        return provider.hasSafeItem(item);
    }
}
