package org.gtreimagined.gt4r.reactor;

public class Config {
    public static double ROD_EU_MULTIPLIER = 1;
    public static double ROD_HU_MULTIPLIER = 1;
    public static double MOX_EU_COEFFICIENT = 4;
    public static int REACTOR_EU_MULTIPLIER = 100;
    public static int FLUID_NUKE_HU_MULTIPLIER = 2;
    // 1 HU converts this/160 mb of distilled water to this mb of steam
    // ex: 600 HU/s converts 1200mb/s of distilled water to 192000mb/s of steam
    // BWRs are limited by the size of the reactor output buffer and this value
    // which currently puts maximum power at just over 600 HU/s
    public static int BWR_STEAM_PER_HU_MULTIPLIER = 320;
    public static int COOLANT_SPECIFIC_HEAT = 1;
}
