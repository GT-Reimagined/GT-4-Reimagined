package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.data.Materials;
import org.gtreimagined.gt4r.data.RecipeMaps;

public class MassFabLoader {
    public static void init(){
        RecipeMaps.MASS_FABRICATOR.RB().fi(Materials.UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter_1",803, 256);
        RecipeMaps.MASS_FABRICATOR.RB().ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter",3125, 256);
    }
}
