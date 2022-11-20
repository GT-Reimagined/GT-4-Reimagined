package trinsdar.gt4r.loader.machines;

import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.RecipeMaps;

public class MassFabLoader {
    public static void init(){
        RecipeMaps.MASS_FABRICATING.RB().fi(Materials.UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter_1",803, 256);
        RecipeMaps.MASS_FABRICATING.RB().ii(GT4RData.INT_CIRCUITS.get(1)).fo(Materials.UUMatter.getLiquid(1)).add("uumatter",3125, 256);
    }
}
