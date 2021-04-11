package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import trinsdar.gt4r.Ref;

import java.io.File;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.GT4RData.SPEAR;

public class ToolCrafting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        /*final ICriterionInstance in = provider.hasSafeItem(FILE.getTag());

        provider.addStackRecipe("spear", output, Ref.ID, HAMMER.getId() + "_" +"recipe", "antimatter_tools",
                "has_file", in, SPEAR.getToolStack(NULL, NULL), of('I', MaterialIngredient.of(INGOT), 'R', MaterialIngredient.of(ROD)), "II ", "IIR", "II ");*/
    }
}
