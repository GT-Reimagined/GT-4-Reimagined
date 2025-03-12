package org.gtreimagined.gt4r.reactor.components;

import org.gtreimagined.gt4r.reactor.Config;

public record FuelType(double energyMult, double heatMult, int pulsesPerTick, boolean isMox, double moxHeatCoefficient, double moxEUCoefficient) {

    public static final FuelType URANIUM = new FuelType(1.0, 4.0, 1, false);

    public FuelType(double energyMult, double heatMult, int pulsesPerTick, boolean isMox){
        this(energyMult, heatMult, pulsesPerTick, isMox, 2.0, Config.MOX_EU_COEFFICIENT);
    }
}
