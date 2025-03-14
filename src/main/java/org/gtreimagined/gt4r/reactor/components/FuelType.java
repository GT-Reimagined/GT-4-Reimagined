package org.gtreimagined.gt4r.reactor.components;

import com.google.common.collect.ImmutableList;
import muramasa.antimatter.util.int2;
import org.gtreimagined.gt4r.reactor.Config;

import java.util.List;

public record FuelType(int durability, double energyMult, double heatMult, int pulsesPerTick,  int connectivityPulses, boolean isMox, double explosionMult, double moxHeatCoefficient, double moxEUCoefficient, int breedingHeat, List<int2> pulseArea, List<int2> heatPulseArea) {
    public static final List<int2> DEFAULT_AREA = ImmutableList.of(new int2(-1, 0), new int2(1, 0), new int2(0, -1), new int2(0, 1));
    public static final List<int2> LARGE_PULSES_AREA = ImmutableList.of(new int2(-1, 0), new int2(1, 0), new int2(0, -1), new int2(0, 1), new int2(-1, -1), new int2(1, -1), new int2(-1, 1), new int2(1, 1));

    public static final FuelType URANIUM = new FuelType(10000, 1.0, 4.0, 1, 1, false, 1.0);

    public FuelType(int durability, double energyMult, double heatMult, int pulsesPerTick, int connectivityPulses, boolean isMox, double explosionMult){
        this(durability, energyMult, heatMult, pulsesPerTick, connectivityPulses, isMox, explosionMult, 2.0, Config.MOX_EU_COEFFICIENT, 3000, DEFAULT_AREA, DEFAULT_AREA);
    }
}
