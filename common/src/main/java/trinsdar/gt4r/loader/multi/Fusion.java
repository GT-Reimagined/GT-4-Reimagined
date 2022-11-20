package trinsdar.gt4r.loader.multi;

import trinsdar.gt4r.data.RecipeMaps;

import static muramasa.antimatter.Data.Gold;
import static muramasa.antimatter.Data.Iron;
import static trinsdar.gt4r.data.Materials.*;

public class Fusion {
    public static void init(){
        RecipeMaps.FUSION.RB().fi(Helium3.getGas(125), Deuterium.getGas(125)).fo(Helium.getPlasma(125)).add(16, 2048, 60000000);
        RecipeMaps.FUSION.RB().fi(Tritium.getGas(125), Deuterium.getGas(125)).fo(Helium.getPlasma(125)).add(16, 4096, 40000000);
        RecipeMaps.FUSION.RB().fi(Helium3.getGas(125), Carbon.getLiquid(125)).fo(Oxygen.getPlasma(125)).add(32, 4096, 80000000);
        RecipeMaps.FUSION.RB().fi(Magnesium.getLiquid(16), Silicon.getLiquid(16)).fo(Iron.getPlasma(125)).add(32, 8192, 360000000);
        RecipeMaps.FUSION.RB().fi(Hydrogen.getGas(16), Manganese.getLiquid(16)).fo(Iron.getLiquid(16)).add(64, 8192, 120000000);
        RecipeMaps.FUSION.RB().fi(Lithium.getLiquid(16), Aluminium.getLiquid(16)).fo(Sulfur.getPlasma(125)).add(32, 10240, 240000000);
        RecipeMaps.FUSION.RB().fi(Deuterium.getGas(375), Beryllium.getLiquid(16)).fo(Nitrogen.getPlasma(175)).add(16, 16384, 180000000);
        RecipeMaps.FUSION.RB().fi(Helium.getGas(16), Tungsten.getLiquid(16)).fo(Osmium.getLiquid(16)).add(64, 24578, 150000000);
        RecipeMaps.FUSION.RB().fi(Fluorine.getGas(125), Potassium.getLiquid(16)).fo(Nickel.getPlasma(125)).add(16, 32768, 480000000);
        RecipeMaps.FUSION.RB().fi(Tungsten.getLiquid(16), Beryllium.getLiquid(16)).fo(Platinum.getLiquid(16)).add(32, 32768, 150000000);
        RecipeMaps.FUSION.RB().fi(Tungsten.getLiquid(16), Lithium.getLiquid(16)).fo(Iridium.getLiquid(16)).add(32, 32768, 300000000);
        RecipeMaps.FUSION.RB().fi(Magnesium.getLiquid(16), Mercury.getLiquid(16)).fo(Uranium238.getLiquid(16)).add(64, 49152, 240000000);
        RecipeMaps.FUSION.RB().fi(Aluminium.getLiquid(16), Gold.getLiquid(16)).fo(Uranium238.getLiquid(16)).add(64, 49152, 240000000);
        RecipeMaps.FUSION.RB().fi(Helium.getGas(16), Uranium238.getLiquid(16)).fo(Plutonium239.getLiquid(16)).add(128, 49152, 480000000);
    }
}
