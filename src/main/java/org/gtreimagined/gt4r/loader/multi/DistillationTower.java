package org.gtreimagined.gt4r.loader.multi;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.DISTILLING;

public class DistillationTower {
    public static void init(){
        DISTILLING.RB().fi(Oil.getLiquid(8000)).fo(Diesel.getLiquid(4000), Glyceryl.getLiquid(500), SulfuricAcid.getLiquid(4000), Naphtha.getLiquid(4000), Lubricant.getLiquid(2000)).add("oil",4000, 64);
        DISTILLING.RB().fi(Naphtha.getLiquid(4000)).fo(Gasoline.getLiquid(4000), Propane.getGas(4000), Methane.getGas(3500)).io(DUST.get(Carbon, 1)).add("naphtha",1000, 64);
        DISTILLING.RB().fi(Biomass.getLiquid(4000)).fo(Ethanol.getLiquid(1000), Glycerol.getLiquid(1000), DistilledWater.getLiquid(2500), Methane.getGas(200)).io(DUST.get(Wood, 1)).add("biomass",500, 64);
    }
}
