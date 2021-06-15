package trinsdar.gt4r.loader.machines;

import net.minecraft.fluid.Fluids;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.Biomass;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Ethanol;
import static trinsdar.gt4r.data.Materials.Glyceryl;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_DISTILLING;

public class DistillingLoader {
    public static void init(){
        BASIC_DISTILLING.RB().fi(new FluidStack(Fluids.WATER, 5)).ii(INT_CIRCUITS.get(5)).fo(DistilledWater.getLiquid(5)).add(16, 10);
        BASIC_DISTILLING.RB().fi(Biomass.getLiquid(40)).ii(INT_CIRCUITS.get(0)).fo(Ethanol.getLiquid(12), DistilledWater.getLiquid(20)).add(24, 16);
        //BASIC_DISTILLING.RB().fi(Biomass.getLiquid(40)).ii(INT_CIRCUITS.get(1)).fo(Glyceryl.getLiquid(20), DistilledWater.getLiquid(20)).add(24, 16);
    }
}
