package trinsdar.gt4r.loader.multi;

import static muramasa.antimatter.Data.DUST;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.DISTILLING;

public class DistillationTower {
    public static void init(){
        DISTILLING.RB().fi(Oil.getLiquid(8000)).fo(Diesel.getLiquid(4000), Glyceryl.getLiquid(500), SulfuricAcid.getLiquid(4000), Naphtha.getLiquid(4000), Lubricant.getLiquid(2000)).add(4000, 64);
        DISTILLING.RB().fi(Naphtha.getLiquid(4000)).fo(Gasoline.getLiquid(4000), Propane.getGas(4000), Methane.getGas(3500)).io(DUST.get(Carbon, 1)).add(1000, 64);
        //DISTILLING.RB().fi(Water.getLiquid(500)).fo(DistilledWater.getLiquid(500)).add(16, 120);
    }
}
