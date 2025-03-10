package org.gtreimagined.gt4r.reactor.fluids;


import org.gtreimagined.gt4r.data.Materials;
import org.gtreimagined.gt4r.reactor.Config;

public class FluidList {

    public static void registerCoolants() {
        CoolantRegistry.registerCoolant(Materials.ColdCoolant.getLiquid(), Materials.HotCoolant.getLiquid(), Config.COOLANT_SPECIFIC_HEAT);

        CoolantRegistry.registerCoolant(Materials.DistilledWater.getLiquid(), Materials.Steam.getGas(), Config.COOLANT_SPECIFIC_HEAT);

    }
}
