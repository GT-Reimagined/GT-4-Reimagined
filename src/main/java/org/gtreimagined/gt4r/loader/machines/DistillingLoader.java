package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_SMALL;
import static muramasa.antimatter.data.AntimatterMaterials.Sugar;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.DISTILLERY;

public class DistillingLoader {
    public static void init(){
        DISTILLERY.RB().fi(Water.getLiquid(5)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(5)).fo(DistilledWater.getLiquid(5)).add("water",16, 10);
        DISTILLERY.RB().fi(Biomass.getLiquid(40)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(0)).fo(Ethanol.getLiquid(12), DistilledWater.getLiquid(20)).add("biomass",24, 16);
        DISTILLERY.RB().fi(Biomass.getLiquid(40)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).fo(Glycerol.getLiquid(20), DistilledWater.getLiquid(20)).add("biomass_1",24, 16);
        DISTILLERY.RB().fi(Oil.getLiquid(80)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(0)).fo(Diesel.getLiquid(20), Lubricant.getLiquid(20)).add("oil",32, 16);
        DISTILLERY.RB().fi(Oil.getLiquid(80)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).fo(Naphtha.getLiquid(20), Lubricant.getLiquid(20)).add("oil_1",32, 16);
        DISTILLERY.RB().fi(Oil.getLiquid(80)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(2)).fo(SulfuricAcid.getLiquid(20), Lubricant.getLiquid(20)).add("oil_2",32, 16);
        DISTILLERY.RB().fi(Honey.getLiquid(100)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(0)).fo(DistilledWater.getLiquid(10)).io(DUST_SMALL.get(Sugar, 2)).add("honey",16, 16);
    }
}
