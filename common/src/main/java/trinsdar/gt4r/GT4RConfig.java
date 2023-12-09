package trinsdar.gt4r;

import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GT4RConfig {
    static ConfigHandler CONFIG;
    public static void createConfig(){
        /*Config config = new Config("gt4r");
        ConfigSection section = config.add("general");
        CONFIG = AntimatterPlatformUtils.createConfig(GT4RRef.ID, config);
        CONFIG.register();*/
    }
}
