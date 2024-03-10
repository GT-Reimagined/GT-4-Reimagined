package trinsdar.gt4r;

import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GT4RConfig {
    static ConfigHandler CONFIG;
    public static ConfigEntry.BoolValue GT5_ELECTRIC_TOOLS;
    public static void createConfig(){
        Config config = new Config("gt4r");
        ConfigSection section = config.add("general");
        GT5_ELECTRIC_TOOLS = section.addBool("gt5_electric_tools", false, "Whether gt4r uses ic2 style electric tools or gt5 style electric tools - Default: false");
        CONFIG = AntimatterPlatformUtils.createConfig(GT4RRef.ID, config);
        CONFIG.register();
    }
}
