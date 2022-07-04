package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.material.Material;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import org.lwjgl.system.macosx.LibSystem;
import trinsdar.gt4r.config.OreConfigHandler;
import trinsdar.gt4r.config.OreConfigNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.NULL;
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
        }), Codec.list(Level.RESOURCE_KEY_CODEC).fieldOf("dimension").forGetter((config) ->{
            return config.dimensions;
        }), Codec.list(Codec.STRING).fieldOf("biomeTypesID").forGetter((config) ->{
            return config.biomeTypesID;
        }), Codec.list(Codec.STRING).fieldOf("invalidBiomeTypesID").forGetter((config) ->{
            return config.invalidBiomeTypesID;
        })).apply(p_236568_0_, GT4ROreFeatureConfig::new);
    });
    private final String id;
    private Material[] materials;
    private String primary, secondary;
    final int size;
    final float secondaryChance, discardOnExposureChance;
    private List<ResourceKey<Level>> dimensions;
    private Set<ResourceLocation> dimensionLocations;

   private FilterContext filter;
    private List<String> biomeTypesID = new ArrayList<>();
    private List<String> invalidBiomeTypesID = new ArrayList<>();

    public GT4ROreFeatureConfig(String id, int size, float discardOnExposureChance, String primary, String secondary, float secondaryChance, List<ResourceKey<Level>> dimensions) {
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
        this.secondary = secondary;
        /*if (secondary != null){
            this.secondary = secondary;
        }*/
        this.dimensions = dimensions;
        this.dimensionLocations = this.dimensions.stream().map(ResourceKey::location).collect(Collectors.toCollection(ObjectOpenHashSet::new));
        this.filter = b -> true;
    }

    public GT4ROreFeatureConfig(String id, int size, float discardOnExposureChance, String primary, String secondary, float secondaryChance, List<ResourceKey<Level>> dimensions, FilterContext filter) {
        this(id, size, discardOnExposureChance, primary, secondary, secondaryChance, dimensions);
        this.filter = filter;
    }

    public GT4ROreFeatureConfig(String id, int size, float discardOnExposureChance, Material primary, Material secondary, float secondaryChance, List<ResourceKey<Level>> dimensions) {
        this(id, size, discardOnExposureChance, primary.getId(), secondary.getId(), secondaryChance, dimensions);
    }

    public GT4ROreFeatureConfig setValidBiomes(List<BiomeDictionary.Type> types){
        biomeTypes = types;
        biomeTypesID = types.stream().map(BiomeDictionary.Type::getName).collect(Collectors.toList());
        return this;
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

    public Set<ResourceLocation> getDimensionLocations() {
        return dimensionLocations;
    }

    public FilterContext getFilter() {
        return filter;
    }

    @FunctionalInterface
    public interface FilterContext{
        boolean test(Holder<Biome> biomeHolder);
    }
}
