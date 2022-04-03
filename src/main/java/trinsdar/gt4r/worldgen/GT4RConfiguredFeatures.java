package trinsdar.gt4r.worldgen;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.material.Material;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.DepthAverageConfigation;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.common.BiomeDictionary;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.config.OreConfigHandler;
import trinsdar.gt4r.config.OreConfigNode;
import trinsdar.gt4r.tree.RubberTreeWorldGen;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Data.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RConfiguredFeatures {
    public static final Object2ObjectArrayMap<String, Holder<PlacedFeature>> FEATURE_MAP = new Object2ObjectArrayMap<>();
    public static final GT4ROreFeatureConfig GALENA_CONFIG = new GT4ROreFeatureConfig("galena", new OreConfigNode(true, 0, 64, 12, 10, "null", 0), Galena, Level.OVERWORLD);
    public static final GT4ROreFeatureConfig BAUXITE_CONFIG = new GT4ROreFeatureConfig("bauxite", new OreConfigNode(true, 50, 120, 6, 16, "null", 0), Bauxite, Level.OVERWORLD).setValidBiomes(FOREST, PLAINS);
    public static final GT4ROreFeatureConfig RUBY_CONFIG = new GT4ROreFeatureConfig("ruby", new OreConfigNode(true, 0, 48, 3, 6, "null", 0), Ruby, Level.OVERWORLD).setValidBiomes(HOT).setInvalidBiomes(JUNGLE, OCEAN);
    public static final GT4ROreFeatureConfig SAPPHIRE_CONFIG = new GT4ROreFeatureConfig("sapphire", new OreConfigNode(true, 0, 48, 3, 6, "null", 0), Sapphire, Level.OVERWORLD).setValidBiomes(OCEAN, BEACH);
    public static final GT4ROreFeatureConfig PLATINUM_CONFIG = new GT4ROreFeatureConfig("platinum", new OreConfigNode(true, 10, 30, 3, 6, "sphalerite", 5), Platinum, Level.OVERWORLD).setValidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig IRIDIUM_CONFIG = new GT4ROreFeatureConfig("iridium", new OreConfigNode(true, 0, 128, 1, 2, "null", 0), Iridium, Level.OVERWORLD);
    public static final GT4ROreFeatureConfig EMERALD_CONFIG = new GT4ROreFeatureConfig("emerald", new OreConfigNode(true, 0, 32, 4, 6, "null", 0), Emerald, Level.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig PYRITE_CONFIG = new GT4ROreFeatureConfig("pyrite", new OreConfigNode(true, 0, 64, 8, 16, "null", 0), Pyrite, Level.NETHER);
    public static final GT4ROreFeatureConfig SPHALERITE_CONFIG = new GT4ROreFeatureConfig("sphalerite", new OreConfigNode(true, 32, 96, 8, 16, "null", 0), Sphalerite, Level.NETHER);
    public static final GT4ROreFeatureConfig CINNABAR_CONFIG = new GT4ROreFeatureConfig("cinnabar",new OreConfigNode(true, 64, 128, 7, 16, "null", 0), Cinnabar, Level.NETHER);
    public static final GT4ROreFeatureConfig TUNGSTATE_CONFIG = new GT4ROreFeatureConfig("tungstate", new OreConfigNode(true, 0, 80, 2, 16, "null", 0), Tungstate, Level.END);
    public static final GT4ROreFeatureConfig PLATINUM_END_CONFIG = new GT4ROreFeatureConfig("platinum_end", new OreConfigNode(true, 0, 80, 2, 6, "null", 0), Platinum, Level.END);
    public static final GT4ROreFeatureConfig OLIVINE_CONFIG = new GT4ROreFeatureConfig("olivine", new OreConfigNode(true, 0, 80, 5, 8, "null", 0), Olivine, Level.END);
    public static final GT4ROreFeatureConfig SODALITE_CONFIG = new GT4ROreFeatureConfig("sodalite", new OreConfigNode(true, 0, 80, 6, 16, "lapis", 3), Sodalite, Level.END);
    public static final GT4ROreFeatureConfig CHROMITE_CONFIG = new GT4ROreFeatureConfig("chromite", new OreConfigNode(true, 0, 80, 4, 5, "null", 0), Chromite, Level.END);
    public static final GT4ROreFeatureConfig SALT_CONFIG = new GT4ROreFeatureConfig("salt", new OreConfigNode(true, 0, 62, 6, 64, "null", 0), Salt, Level.OVERWORLD).setValidBiomes(OCEAN);
    public static final GT4ROreFeatureConfig ROCK_SALT_CONFIG = new GT4ROreFeatureConfig("rock_salt", new OreConfigNode(true, 0, 80, 4, 64, "null", 0), RockSalt, Level.OVERWORLD).setValidBiomes(SANDY);



    public static ConfiguredFeature<?,?> register(String id, ConfiguredFeature<?,?> feature){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ref.ID, id), feature);
    }

    public static void init(){
        createOrePlacedFeature("tin", Tin, -64, 0, 25, 8, "iron", 0.01f, 0.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("uranite", Uraninite, -16, 100, 8, 4, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), List.of(), List.of(DEAD), false, false, false);
        createOrePlacedFeature("uranite_dead", Uraninite, -16, 100, 20, 4, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), List.of(DEAD), List.of(), false, false, false);
        createOrePlacedFeature("cassiterite", Cassiterite, -30, 30, 2, 32, "tin", 0.05f, 0.0f, List.of(Level.OVERWORLD), List.of(MUSHROOM, MOUNTAIN, CONIFEROUS, COLD), List.of(JUNGLE), false, false, false);
        createOrePlacedFeature("tetrahedrite", Tetrahedrite, -20, 40, 10, 12, "copper", 0.225f, 0.0f, List.of(Level.OVERWORLD), List.of(MUSHROOM, MOUNTAIN, JUNGLE, SWAMP), List.of(COLD), false, false, false);

        createOrePlacedFeature("coal_upper", Coal, 136, 320, 30, 17, null, 0.0f, 0.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("coal_lower", Coal, 0, 192, 20, 17, null, 0.0f, 0.5f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("iron_upper", Iron, 80, 384, 90, 9, "tin", 0.02f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("iron_middle", Iron, -24, 56, 10, 9, "tin", 0.02f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("iron_small", Iron, -64, 72, 10, 4, "tin", 0.02f, 0.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("gold_extra", Gold, 32, 256, 50, 9, "copper", 0.02f, 0.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("gold", Gold, -64, 32, 4, 9, "copper", 0.02f, 0.5f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("redstone", Redstone, -64, 15, 4, 8, "cinnabar", 0.01f, 0, false, false, Level.OVERWORLD);
        createOrePlacedFeature("redstone_lower", Redstone, -96, -32, 8, 8, "cinnabar", 0.01f, 0, true, false, Level.OVERWORLD);
        createOrePlacedFeature("diamond", Diamond, -144, 16, 7, 4, null, 0.0f, 0.5f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("diamond_large", Diamond, -144, 16, 9, 12, null, 0.0f, 0.7f, true, true, Level.OVERWORLD);
        createOrePlacedFeature("diamond_buried", Diamond, -144, 16, 4, 8, null, 0.0f, 1.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("lapis", Lapis, -32, 32, 2, 7, null, 0.0f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("lapis_buried", Lapis, -64, 64, 4, 7, null, 0.0f, 1.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("emerald", Emerald, -16, 480, 100, 3, null, 0.0f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("copper", Copper, -16, 112, 16, 10, "gold", 0.02f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("copper_buried", Copper, -16, 112, 16, 20, "gold", 0.02f, 0.0f, true, false, Level.OVERWORLD);
    }

    public static void createOrePlacedFeature(String id, Material material, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, float discardChance, List<ResourceKey<Level>> dimensions, List<BiomeDictionary.Type> validBiomes, List<BiomeDictionary.Type> invalidBiomes, boolean invertBiomeFilter, boolean triangle, boolean rare){
        OreConfigNode node = OreConfigHandler.ORE_CONFIG_HANDLER.getBiomeConfig().ore(id, new OreConfigNode(true, minY, maxY, weight, size, secondary, secondaryChance));
        if (!node.isEnabled()) return;
        GT4ROreFeatureConfig config = new GT4ROreFeatureConfig(id, node.getSize(), discardChance, material.getId(), node.getSecondary(), node.getSecondaryChance(), dimensions);
        if (!validBiomes.isEmpty()){
            config.setValidBiomes(validBiomes);
        }
        if (!invalidBiomes.isEmpty()){
            config.setValidBiomes(invalidBiomes);
        }
        config.setInvertBiomeFilter(invertBiomeFilter);
        Holder<ConfiguredFeature<GT4ROreFeatureConfig, ?>> configuredFeature = RubberTreeWorldGen.register(id, new ConfiguredFeature<>(FeatureVanillaTypeOre.ORE, config));
        List<PlacementModifier> list = new ArrayList<>();
        list.addAll(List.of(BiomeFilter.biome(), InSquarePlacement.spread()));
        list.add(triangle ? HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)) : HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
        list.add(rare ? RarityFilter.onAverageOnceEvery(weight) : CountPlacement.of(weight));
        Holder<PlacedFeature> placedFeature = RubberTreeWorldGen.createPlacedFeature(id, configuredFeature, list.toArray(new PlacementModifier[0]));
        FEATURE_MAP.put(id, placedFeature);
    }

    @SafeVarargs
    public static void createOrePlacedFeature(String id, Material material, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, float discardChance, boolean triangle, boolean rare, ResourceKey<Level>... dimensions){
        createOrePlacedFeature(id, material, minY, maxY, weight, size, secondary, secondaryChance, discardChance, List.of(dimensions), List.of(), List.of(), false, triangle, rare);
    }
}
