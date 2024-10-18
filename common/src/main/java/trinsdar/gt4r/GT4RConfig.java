package trinsdar.gt4r;

import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import muramasa.antimatter.util.AntimatterPlatformUtils;

public class GT4RConfig {
    static ConfigHandler CONFIG;
    public static ConfigEntry.BoolValue GT5_ELECTRIC_TOOLS;
    public static ConfigEntry.BoolValue NERF_VANILLA_COPPER_GEN;
    public static void createConfig(){
        Config config = new Config("gt4r");
        ConfigSection section = config.add("general");
        GT5_ELECTRIC_TOOLS = section.addBool("gt5_electric_tools", false, "Whether gt4r uses ic2 style electric tools or gt5 style electric tools - Default: false");
        NERF_VANILLA_COPPER_GEN = section.addBool("nerf_vanilla_copper_gen", true, "Nerfs the vanilla copper generation to only half the rate. Requires vanilla ore generation to be disabled in the antimatter config - Default: true");
        CONFIG = AntimatterPlatformUtils.INSTANCE.createConfig(GT4RRef.ID, config);
        CONFIG.register();
    }
}
