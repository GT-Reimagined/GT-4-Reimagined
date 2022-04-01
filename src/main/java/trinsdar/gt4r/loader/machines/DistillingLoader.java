package trinsdar.gt4r.loader.machines;

import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.Data.DUST_SMALL;
import static muramasa.antimatter.Data.Sugar;
import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_DISTILLING;

public class DistillingLoader {
    public static void init(){
        BASIC_DISTILLING.RB().fi(new FluidStack(Fluids.WATER, 5)).ii(INT_CIRCUITS.get(5)).fo(DistilledWater.getLiquid(5)).add(16, 10);
        BASIC_DISTILLING.RB().fi(Biomass.getLiquid(40)).ii(INT_CIRCUITS.get(0)).fo(Ethanol.getLiquid(12), DistilledWater.getLiquid(20)).add(24, 16);
        BASIC_DISTILLING.RB().fi(Biomass.getLiquid(40)).ii(INT_CIRCUITS.get(1)).fo(Glycerol.getLiquid(20), DistilledWater.getLiquid(20)).add(24, 16);
        BASIC_DISTILLING.RB().fi(Oil.getLiquid(80)).ii(INT_CIRCUITS.get(0)).fo(Diesel.getLiquid(20), Lubricant.getLiquid(20)).add(32, 16);
        BASIC_DISTILLING.RB().fi(Oil.getLiquid(80)).ii(INT_CIRCUITS.get(1)).fo(Naphtha.getLiquid(20), Lubricant.getLiquid(20)).add(32, 16);
        BASIC_DISTILLING.RB().fi(Oil.getLiquid(80)).ii(INT_CIRCUITS.get(2)).fo(SulfuricAcid.getLiquid(20), Lubricant.getLiquid(20)).add(32, 16);
        BASIC_DISTILLING.RB().fi(Honey.getLiquid(100)).ii(INT_CIRCUITS.get(0)).fo(DistilledWater.getLiquid(10)).io(DUST_SMALL.get(Sugar, 2)).add(16, 16);
    }
}
