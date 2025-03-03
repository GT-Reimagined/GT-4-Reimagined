package org.gtreimagined.gt4r.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        }), Codec.FLOAT.fieldOf("secondaryChance").forGetter((config) -> {
            return config.secondaryChance;
        }), Codec.BOOL.fieldOf("triangle").forGetter((config) -> {
            return config.triangle;
        }), Codec.BOOL.fieldOf("biomeBlacklist").forGetter((config) -> {
            return config.biomeBlacklist;
        }), Codec.list(Codec.STRING).fieldOf("dimensions").forGetter((config) -> {
            return config.dimensions;
        }), Codec.list(Codec.STRING).fieldOf("biomeFilters").forGetter((config) -> {
            return config.biomeFilters;
        })).apply(instance, OreConfigNode::new);
    });
    private boolean enable, triangle;
    private int minY, maxY, weight, size;
    private String secondary;
    private float secondaryChance;

    private List<String> biomeFilters;
    private List<String> dimensions;
    private boolean biomeBlacklist = false;

    public OreConfigNode(boolean enable, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, boolean triangle, boolean biomeBlacklist, List<String> dimensions, String... biomeFilters) {
        this.enable = enable;
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.size = size;
        this.secondary = secondary;
        this.secondaryChance = secondaryChance;
        this.triangle = triangle;
        this.biomeFilters = biomeFilters.length > 0 ? new ArrayList<>(Arrays.asList(biomeFilters)) : new ArrayList<>();
        this.biomeBlacklist = biomeBlacklist;
        this.dimensions = dimensions;
    }

    private OreConfigNode(boolean enable, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, boolean triangle, boolean biomeBlacklist, List<String> dimensions, List<String> biomeFilters) {
        this.enable = enable;
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.size = size;
        this.secondary = secondary;
        this.secondaryChance = secondaryChance;
        this.triangle = triangle;
        this.biomeFilters = biomeFilters;
        this.biomeBlacklist = biomeBlacklist;
        this.dimensions = dimensions;
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

    public float getSecondaryChance() {
        return secondaryChance;
    }

    public boolean isTriangle() {
        return triangle;
    }

    public boolean isBiomeBlackListed() {
        return biomeBlacklist;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public List<String> getBiomeFilters() {
        return biomeFilters;
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

    public void setTriangle(boolean triangle) {
        this.triangle = triangle;
    }

    public enum OreConfigDistributionShape{
        UNIFORM,
        TRAPEZOID
    }
}
