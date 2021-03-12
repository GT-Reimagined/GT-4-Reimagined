package trinsdar.gt4r.loader.machines;

import trinsdar.gt4r.data.GT4RData;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.DUST;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.data.RecipeMaps.EXTRACTING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class ExtractorLoader {
    public static void init() {
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_LOG.asItem(),1)).io(DUST.get(Rubber, 2)).add(200,8);
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_LEAVES.asItem(),1)).io(DUST.get(Rubber, 1)).add(150,8);
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_SAPLING.asItem(),1)).io(DUST.get(Rubber, 1)).add(150,8);
        EXTRACTING.RB().ii(of(GT4RData.StickyResin,1)).io(DUST.get(Rubber, 3)).add(150,8);
    }
}
