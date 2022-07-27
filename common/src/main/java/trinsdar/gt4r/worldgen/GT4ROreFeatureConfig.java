package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import muramasa.antimatter.material.Material;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import static muramasa.antimatter.Data.ORE;
import static trinsdar.gt4r.data.Materials.RockSalt;
import static trinsdar.gt4r.data.Materials.Salt;

public class GT4ROreFeatureConfig implements FeatureConfiguration {

    public static final Codec<GT4ROreFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(Codec.STRING.fieldOf("id").forGetter((config) -> {
            return config.id;
        }),Codec.INT.fieldOf("size").forGetter(config -> {
            return config.size;
        }), Codec.FLOAT.fieldOf("discardOnExposureChance").forGetter(config -> {
            return config.discardOnExposureChance;
        }), Codec.STRING.fieldOf("primary").forGetter((config) -> {
            return config.primary;
        }), Codec.STRING.fieldOf("secondary").forGetter((config) -> {
            return config.secondary;
        }), Codec.FLOAT.fieldOf("secondaryChance").forGetter(config -> {
            return config.secondaryChance;
        })).apply(p_236568_0_, GT4ROreFeatureConfig::new);
    });
    private final String id;
    private Material[] materials;
    private String primary, secondary;
    final int size;
    final float secondaryChance, discardOnExposureChance;

    public GT4ROreFeatureConfig(String id, int size, float discardOnExposureChance, String primary, String secondary, float secondaryChance) {
        this.id = id;
        this.size = size;
        this.secondaryChance = secondaryChance;
        this.discardOnExposureChance = discardOnExposureChance;
        if (primary == null || (!Material.get(primary).has(ORE) && Material.get(primary) != Salt && Material.get(primary) != RockSalt)) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + primary + " material either doesn't exist or doesn't have the ORE tag");
        //if (secondary != null && !Material.get(secondary).has(ORE) && Material.get(secondary) != NULL) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + secondary + " material doesn't have the ORE tag");
        if (secondary != null){
            materials = new Material[] {Material.get(primary), Material.get(secondary)};
        } else {
            materials = new Material[] {Material.get(primary)};
        }
        this.primary = primary;
        if (secondary == null){
            secondary = "";
        }
        this.secondary = secondary;
        /*if (secondary != null){
            this.secondary = secondary;
        }*/
    }

    public GT4ROreFeatureConfig(String id, int size, float discardOnExposureChance, Material primary, Material secondary, float secondaryChance) {
        this(id, size, discardOnExposureChance, primary.getId(), secondary.getId(), secondaryChance);
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public float getSecondaryChance() {
        return secondaryChance;
    }

    public float getDiscardOnExposureChance() {
        return discardOnExposureChance;
    }

    @FunctionalInterface
    public interface FilterContext{
        boolean test(Holder<Biome> biomeHolder);
    }
}
