package trinsdar.gt4r.loader.multi;

import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.RecipeMaps;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.Iron;
import static trinsdar.gt4r.data.Materials.*;

public class Fusion {
    public static void init(){
        RecipeMaps.FUSION.RB().fi(Helium3.getGas(125), Deuterium.getGas(125)).fo(Helium.getPlasma(125)).add(16, 2048, 60000000);
        RecipeMaps.FUSION.RB().fi(Tritium.getGas(125), Deuterium.getGas(125)).fo(Helium.getPlasma(125)).add(16, 4096, 40000000);
        RecipeMaps.FUSION.RB().fi(Helium3.getGas(125), Carbon.getLiquid(125)).fo(Oxygen.getPlasma(125)).add(32, 4096, 80000000);
        RecipeMaps.FUSION.RB().fi(Magnesium.getLiquid(16), Silicon.getLiquid(16)).fo(Iron.getPlasma(125)).add(32, 8192, 360000000);
    }
}
