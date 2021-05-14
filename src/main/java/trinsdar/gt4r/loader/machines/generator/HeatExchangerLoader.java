package trinsdar.gt4r.loader.machines.generator;

import static trinsdar.gt4r.data.Materials.ColdCoolant;
import static trinsdar.gt4r.data.Materials.HotCoolant;
import static trinsdar.gt4r.data.Materials.Lava;
import static trinsdar.gt4r.data.Materials.PahoehoeLava;
import static trinsdar.gt4r.data.RecipeMaps.HOT_FUELS;

public class HeatExchangerLoader {
    public static void init(){
        HOT_FUELS.RB().fi(Lava.getLiquid(1)).fo(PahoehoeLava.getLiquid(1)).add(1, 0, 80);
        HOT_FUELS.RB().fi(HotCoolant.getLiquid(1)).fo(ColdCoolant.getLiquid(1)).add(1, 0, 20);
    }
}
