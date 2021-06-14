package trinsdar.gt4r.loader.machines;

import net.minecraft.fluid.Fluids;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_DISTILLING;

public class DistillingLoader {
    public static void init(){
        BASIC_DISTILLING.RB().fi(new FluidStack(Fluids.WATER, 5)).ii(INT_CIRCUITS.get(5)).fo(DistilledWater.getLiquid(5)).add(16, 10);
    }
}
