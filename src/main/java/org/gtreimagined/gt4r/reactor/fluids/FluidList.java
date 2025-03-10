package org.gtreimagined.gt4r.reactor.fluids;


import org.gtreimagined.gt4r.data.Materials;

public class FluidList {

    public static void registerCoolants() {
        CoolantRegistry.registerCoolant(Materials.ColdCoolant.getLiquid(), Materials.HotCoolant.getLiquid(), 1);

        CoolantRegistry.registerCoolant(Materials.DistilledWater.getLiquid(), Materials.Steam.getGas(), 1);

    }
}
