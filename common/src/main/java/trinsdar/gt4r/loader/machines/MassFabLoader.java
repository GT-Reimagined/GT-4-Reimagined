package trinsdar.gt4r.loader.machines;

import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.data.TierMaps;

public class MassFabLoader {
    public static void init(){
        RecipeMaps.MASS_FABRICATOR.RB().fi(Materials.UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter_1",803, 256);
        RecipeMaps.MASS_FABRICATOR.RB().ii(TierMaps.INT_CIRCUITS.get(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter",3125, 256);
    }
}
