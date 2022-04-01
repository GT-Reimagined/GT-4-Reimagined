package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.material.Material;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.common.BiomeDictionary;
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
        }),OreConfigNode.ORE_CONFIG_NODE_CODEC.fieldOf("configNode").forGetter((config) -> {
            return config.configNode;
        }),Codec.STRING.fieldOf("primary").forGetter((config) -> {
            return config.primary;
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
    private final OreConfigNode configNode;
    private boolean invertBiomeFilter;
    private List<ResourceKey<Level>> dimensions;
    private Set<ResourceLocation> dimensionLocations;
    private List<BiomeDictionary.Type> biomeTypes = new ArrayList<>();
    private List<BiomeDictionary.Type> invalidBiomeTypes = new ArrayList<>();
    private List<String> biomeTypesID = new ArrayList<>();
    private List<String> invalidBiomeTypesID = new ArrayList<>();

    public GT4ROreFeatureConfig(String id, OreConfigNode configNode, String primary, ResourceKey<Level>... dimensions) {
        this.id = id;
        this.configNode = OreConfigHandler.ORE_CONFIG_HANDLER.getBiomeConfig().ore(id, configNode);
        this.invertBiomeFilter = false;
        if (primary == null || (!Material.get(primary).has(ORE) && Material.get(primary) != Salt && Material.get(primary) != RockSalt)) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + primary + " material either doesn't exist or doesn't have the ORE tag");
        //if (secondary != null && !Material.get(secondary).has(ORE) && Material.get(secondary) != NULL) throw new IllegalArgumentException("GT4ROreFeatureConfig - " + id + ": " + secondary + " material doesn't have the ORE tag");
        if (secondary != null){
            materials = new Material[] {Material.get(primary), Material.get(secondary)};
        } else {
            materials = new Material[] {Material.get(primary)};
        }
        this.primary = primary;
        /*if (secondary != null){
            this.secondary = secondary;
        }*/
        this.dimensions = Arrays.stream(dimensions).collect(Collectors.toList());
        this.dimensionLocations = this.dimensions.stream().map(ResourceKey::location).collect(Collectors.toCollection(ObjectOpenHashSet::new));
    }

    public GT4ROreFeatureConfig(String id, OreConfigNode configNode, Material primary, ResourceKey<Level>... dimensions) {
        this(id, configNode, primary.getId(), dimensions);
    }

    private GT4ROreFeatureConfig(String id, OreConfigNode configNode, String primary, List<ResourceKey<Level>> dimensions, List<String> biomeTypes, List<String> invalidBiomeTypes) {
        this(id, configNode, primary);
        this.dimensions = dimensions;
        this.dimensionLocations = this.dimensions.stream().map(ResourceKey::location).collect(Collectors.toCollection(ObjectOpenHashSet::new));
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

    public GT4ROreFeatureConfig setInvertBiomeFilter(boolean invert){
        this.invertBiomeFilter = invert;
        return this;
    }

    public OreConfigNode getConfigNode() {
        return configNode;
    }

    public String getId() {
        return id;
    }

    public int getMinY() {
        return configNode.getMinY();
    }

    public int getMaxY() {
        return configNode.getMaxY();
    }

    public int getWeight() {
        return configNode.getWeight();
    }

    public int getSize() {
        return configNode.getSize();
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return configNode.getSecondary();
    }

    public int getSecondaryChance() {
        return configNode.getSecondaryChance();
    }

    public boolean isInvertBiomeFilter() {
        return invertBiomeFilter;
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
