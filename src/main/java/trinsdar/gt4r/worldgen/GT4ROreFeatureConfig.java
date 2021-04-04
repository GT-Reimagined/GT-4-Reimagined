package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.NULL;
import static muramasa.antimatter.Data.ORE;

public class GT4ROreFeatureConfig implements IFeatureConfig {
    public static final Codec<GT4ROreFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(Codec.STRING.fieldOf("id").forGetter((config) -> {
            return config.id;
        }), Codec.intRange(0, 256).fieldOf("minY").forGetter((config) -> {
            return config.minY;
        }), Codec.intRange(0, 256).fieldOf("maxY").forGetter((config) -> {
            return config.maxY;
        }), Codec.intRange(0, 32).fieldOf("weight").forGetter((config) -> {
            return config.weight;
        }), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> {
            return config.size;
        }),Codec.STRING.fieldOf("primary").forGetter((config) -> {
            return config.primary;
        }), Codec.STRING.fieldOf("secondary").forGetter((config) -> {
            return config.secondary;
        }), Codec.INT.fieldOf("secondaryChance").forGetter((config) -> {
            return config.secondaryChance;
        }), Codec.list(World.CODEC).fieldOf("dimension").forGetter((config) ->{
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
    private final int minY;
    private final int maxY;
    private final int weight;
    private final int secondaryChance;
    private final int size;
    private List<RegistryKey<World>> dimensions;
    private Set<ResourceLocation> dimensionLocations;
    private List<BiomeDictionary.Type> biomeTypes = new ArrayList<>();
    private List<BiomeDictionary.Type> invalidBiomeTypes = new ArrayList<>();
    private List<String> biomeTypesID = new ArrayList<>();
    private List<String> invalidBiomeTypesID = new ArrayList<>();

    public GT4ROreFeatureConfig(String id , int minY, int maxY, int weight, int size, String primary, String secondary, int secondaryChance, RegistryKey<World>... dimensions) {
        this.id = id;
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.secondaryChance = secondaryChance;
        this.size = size;
        if (primary == null || !Material.get(primary).has(ORE)) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + primary + " material either doesn't exist or doesn't have the ORE tag");
        if (secondary != null && !Material.get(secondary).has(ORE) && Material.get(secondary) != NULL) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + secondary + " material doesn't have the ORE tag");
        if (secondary != null){
            materials = new Material[] {Material.get(primary), Material.get(secondary)};
        } else {
            materials = new Material[] {Material.get(primary)};
        }
        this.primary = primary;
        if (secondary != null){
            this.secondary = secondary;
        }
        this.dimensions = Arrays.stream(dimensions).collect(Collectors.toList());
        this.dimensionLocations = this.dimensions.stream().map(RegistryKey::getLocation).collect(Collectors.toCollection(ObjectOpenHashSet::new));
    }

    public GT4ROreFeatureConfig(String id, int minY, int maxY, int weight, int size, Material primary, Material secondary, int secondaryChance, RegistryKey<World>... dimensions) {
        this(id, minY, maxY, weight, size, primary.getId(), secondary.getId(), secondaryChance, dimensions);
    }

    private GT4ROreFeatureConfig(String id, int minY, int maxY, int weight, int size, String primary, String secondary, int secondaryChance, List<RegistryKey<World>> dimensions, List<String> biomeTypes, List<String> invalidBiomeTypes) {
        this(id, minY, maxY, weight, size, primary, secondary, secondaryChance);
        this.dimensions = dimensions;
        this.dimensionLocations = this.dimensions.stream().map(RegistryKey::getLocation).collect(Collectors.toCollection(ObjectOpenHashSet::new));
        this.biomeTypes = biomeTypes.stream().map(BiomeDictionary.Type::getType).collect(Collectors.toList());
        this.invalidBiomeTypes = invalidBiomeTypes.stream().map(BiomeDictionary.Type::getType).collect(Collectors.toList());
        this.biomeTypesID = biomeTypes;
        this.invalidBiomeTypesID = invalidBiomeTypes;
    }

    public GT4ROreFeatureConfig setValidBiomes(BiomeDictionary.Type... types){
        biomeTypes = Arrays.stream(types).collect(Collectors.toList());
        biomeTypesID = Arrays.stream(types).map(BiomeDictionary.Type::getName).collect(Collectors.toList());
        return this;
    }

    public GT4ROreFeatureConfig setInvalidBiomes(BiomeDictionary.Type... types){
        invalidBiomeTypes = Arrays.stream(types).collect(Collectors.toList());
        invalidBiomeTypesID = Arrays.stream(types).map(BiomeDictionary.Type::getName).collect(Collectors.toList());
        return this;
    }

    public String getId() {
        return id;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getWeight() {
        return weight;
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

    public int getSecondaryChance() {
        return secondaryChance;
    }

    public Set<ResourceLocation> getDimensionLocations() {
        return dimensionLocations;
    }

    public List<BiomeDictionary.Type> getBiomeTypes() {
        return biomeTypes;
    }

    public List<BiomeDictionary.Type> getInvalidBiomeTypes() {
        return invalidBiomeTypes;
    }
}
