package org.gtreimagined.gt4r;

import carbonconfiglib.CarbonConfig;
import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;

public class GT4RConfig {
    static ConfigHandler CONFIG;
    public static ConfigEntry.BoolValue GT5_ELECTRIC_TOOLS;
    public static ConfigEntry.BoolValue NERF_VANILLA_COPPER_GEN;
    public static ConfigEntry.BoolValue HARDER_ENERGY_CRYSTAL;
    public static ConfigEntry.BoolValue ADD_LOOT;
    public static void createConfig(){
        Config config = new Config("gt4r");
        ConfigSection section = config.add("general");
        GT5_ELECTRIC_TOOLS = section.addBool("gt5_electric_tools", false, "Whether gt4r uses ic2 style electric tools or gt5 style electric tools - Default: false");
        NERF_VANILLA_COPPER_GEN = section.addBool("nerf_vanilla_copper_gen", true, "Nerfs the vanilla copper generation to only half the rate. Requires vanilla ore generation to be disabled in the antimatter config - Default: true");
        HARDER_ENERGY_CRYSTAL = section.addBool("harder_energy_crystal", false, "Makes Energy Crystals harder to craft by requiring compression of energium dust");
        ADD_LOOT = section.addBool("add_loot", true, "Adds gt4r loot to chests");
        CONFIG = CarbonConfig.CONFIGS.createConfig(config);
        CONFIG.register();
    }
}
