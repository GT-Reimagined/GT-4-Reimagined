package trinsdar.gt4r.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class OreConfigNode {
    public static final Codec<OreConfigNode> ORE_CONFIG_NODE_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
            Codec.BOOL.fieldOf("enable").forGetter((config) -> {
            return config.enable;
        }), Codec.INT.fieldOf("minY").forGetter((config) -> {
            return config.minY;
        }), Codec.INT.fieldOf("maxY").forGetter((config) -> {
            return config.maxY;
        }), Codec.INT.fieldOf("weight").forGetter((config) -> {
            return config.weight;
        }), Codec.INT.fieldOf("size").forGetter((config) -> {
            return config.size;
        }), Codec.STRING.fieldOf("secondary").forGetter((config) -> {
            return config.secondary;
        }), Codec.INT.fieldOf("secondaryChance").forGetter((config) -> {
            return config.secondaryChance;
        })).apply(instance, OreConfigNode::new);
    });
    private boolean enable;
    private int minY, maxY, weight, size;
    private String secondary;
    private int secondaryChance;

    public OreConfigNode(boolean enable, int minY, int maxY, int weight, int size, String secondary, int secondaryChance) {
        this.enable = enable;
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.size = size;
        this.secondary = secondary;
        this.secondaryChance = secondaryChance;
    }

    public boolean isEnabled() {
        return enable;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    public String getSecondary() {
        return secondary;
    }

    public int getSecondaryChance() {
        return secondaryChance;
    }

    public void setEnabled(boolean enable) {
        this.enable = enable;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public void setSecondaryChance(int secondaryChance) {
        this.secondaryChance = secondaryChance;
    }
}
