package org.gtreimagined.gt4r.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class OreConfig {
    private Map<String, OreConfigNode> ores = new LinkedHashMap<>();

    public OreConfig() {
    }

    public OreConfigNode ore(String name, OreConfigNode defaultNode) {
        return this.ores.computeIfAbsent(name, name_ -> defaultNode);
    }
}
