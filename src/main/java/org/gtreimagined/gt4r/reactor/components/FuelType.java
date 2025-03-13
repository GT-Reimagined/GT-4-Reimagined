package org.gtreimagined.gt4r.reactor.components;

import org.gtreimagined.gt4r.reactor.Config;

public record FuelType(double energyMult, double heatMult, int pulsesPerTick, boolean isMox, int durability, double moxHeatCoefficient, double moxEUCoefficient) {

    public static final FuelType URANIUM = new FuelType(1.0, 4.0, 1, false, 10000);

    public FuelType(double energyMult, double heatMult, int pulsesPerTick, boolean isMox, int durability){
        this(energyMult, heatMult, pulsesPerTick, isMox, durability, 2.0, Config.MOX_EU_COEFFICIENT);
    }
}
