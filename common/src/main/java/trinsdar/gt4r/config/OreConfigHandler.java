package trinsdar.gt4r.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OreConfigHandler {
    public static final OreConfigHandler ORE_CONFIG_HANDLER = new OreConfigHandler();
    private File file;
    private OreConfig config;

    private OreConfigHandler() {
    }

    private boolean prepareOreConfigFile() {
        if (file != null) {
            return !file.exists();
        }
        File configDirectory = new File(AntimatterPlatformUtils.INSTANCE.getConfigDir().toFile(), "gt4r");
        file = new File(configDirectory, "ores.json");
        if (file.exists()){
            return false;
        }
        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }
        return true;
    }

    public OreConfig getOreConfig() {
        if (config != null) {
            return config;
        }

        config = new OreConfig();
        load();

        return config;
    }

    private void load() {
        prepareOreConfigFile();

        try {
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new FileReader(file));

                config = gson.fromJson(br, OreConfig.class);
            }
        } catch (Exception e) {
            System.err.println("Couldn't load ore configuration file for  gt4r, reverting to defaults");
            e.printStackTrace();
        }
    }

    public void save() {
        if (!prepareOreConfigFile()) return;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(config);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.err.println("Couldn't save ore configuration file for gt4r");
            e.printStackTrace();
        }
    }
}
