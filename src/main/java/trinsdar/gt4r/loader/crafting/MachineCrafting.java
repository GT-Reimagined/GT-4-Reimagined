package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import trinsdar.gt4r.Ref;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.WRENCH;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static trinsdar.gt4r.data.GT4RData.CopperCoil;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class MachineCrafting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(output, Ref.ID, "electrolyzer", "machines", "has_extractor", criterion(EXTRACTOR.getItem(LV), provider),
                ELECTROLYZER.getItem(MV), of('C', getForgeItemTag("circuits/advanced"), 'P', getForgeItemTag("plates/steels"), 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil), "PEP", "CLC", "PEP");
    }
}
