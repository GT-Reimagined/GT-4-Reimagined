package trinsdar.gt4r.worldgen;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.material.Material;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.config.OreConfigHandler;
import trinsdar.gt4r.config.OreConfigNode;
import trinsdar.gt4r.tree.RubberTreeWorldGen;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.TagUtils.getBiomeTag;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RPlacedFeatures {
    public static final Object2ObjectArrayMap<String, MapWrapper> FEATURE_MAP = new Object2ObjectArrayMap<>();

    public static ConfiguredFeature<?,?> register(String id, ConfiguredFeature<?,?> feature){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ref.ID, id), feature);
    }

    public static void init(){
        // GT4R Overworld Ores
        createOrePlacedFeature("tin", Tin, -64, 0, 25, 8, "iron", 0.01f, 0.0f, false, false, Level.OVERWORLD);
        createOrePlacedFeature("uranite", Uraninite, -16, 100, 8, 4, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), b -> !b.is(getBiomeTag(new ResourceLocation(Ref.ID, "is_dead"))), false, false);
        createOrePlacedFeature("uranite_dead", Uraninite, -16, 100, 20, 4, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "is_dead"))), false, false);
        createOrePlacedFeature("cassiterite", Cassiterite, -30, 30, 2, 32, "tin", 0.05f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_cassiterite"))), false, false);
        createOrePlacedFeature("tetrahedrite", Tetrahedrite, -20, 40, 10, 12, "copper", 0.225f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_tetra"))), false, false);
        createOrePlacedFeature("galena", Galena, -32, 32, 12, 10, null, 0.0f, 0.2f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("bauxite", Bauxite, 50, 120, 6, 16, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_bauxite"))), false, false);
        createOrePlacedFeature("ruby", Ruby, -16, 32, 3, 6, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_ruby"))), false, false);
        createOrePlacedFeature("sapphire", Sapphire, -16, 32, 3, 6,null, 0.0f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_sapphire"))), false, false);
        createOrePlacedFeature("platinum", Platinum, -40, 20, 3, 6, "sphalerite", 0.05f, 0.0f, List.of(Level.OVERWORLD), b -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_platinum"))), false, false);
        createOrePlacedFeature("iridium", Iridium, -64, 128, 1, 4, null, 0.0f, 0.0f, false, true, Level.OVERWORLD);
        //createOrePlacedFeature("emerald_extra", Emerald, -16, 480, 100, 3, null, 0.0f, 0.0f, true, false, Level.OVERWORLD);
        createOrePlacedFeature("salt", Salt, 0, 62, 6, 64, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), (b) -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_salt"))), false, false);
        createOrePlacedFeature("rock_salt", RockSalt, 0, 80, 4, 64, null, 0.0f, 0.0f, List.of(Level.OVERWORLD), (b) -> b.is(getBiomeTag(new ResourceLocation(Ref.ID, "has_rock_salt"))), false, false);
        // GT4R Nether Ores
        createOrePlacedFeature("pyrite", Pyrite, 0, 64, 8, 16, null, 0.0f, 0.0f, false, false, Level.NETHER);
        createOrePlacedFeature("sphalerite", Sphalerite, 32, 96, 8, 16, null, 0.0f, 0.0f, false, false, Level.NETHER);
        createOrePlacedFeature("cinnabar", Cinnabar, 64, 128, 7, 16, null, 0.0f, 0.0f, false, false, Level.NETHER);
        // GT4R End Ores
        createOrePlacedFeature("tungstate", Tungstate, 0, 80, 2, 16, null, 0.0f, 0.0f, false, false, Level.END);
        createOrePlacedFeature("platinum_end", Platinum, 0, 80, 2, 6, null, 0.0f, 0.0f, false, false, Level.END);
        createOrePlacedFeature("olivine", Olivine, 0, 80, 5, 8, null, 0.0f, 0.0f, false, false, Level.END);
        createOrePlacedFeature("sodalite", Sodalite, 0, 80, 6, 16, null, 0.0f, 0.0f, false, false, Level.END);
        createOrePlacedFeature("chromite", Chromite, 0, 80, 4, 5, null, 0.0f, 0.0f, false, false, Level.END);

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

    public static void createOrePlacedFeature(String id, Material material, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, float discardChance, List<ResourceKey<Level>> dimensions, GT4ROreFeatureConfig.FilterContext filter, boolean triangle, boolean rare){
        OreConfigNode node = OreConfigHandler.ORE_CONFIG_HANDLER.getOreConfig().ore(id, new OreConfigNode(true, minY, maxY, weight, size, secondary, secondaryChance, triangle));
        if (!node.isEnabled()) return;
        GT4ROreFeatureConfig config = new GT4ROreFeatureConfig(id, node.getSize(), discardChance, material.getId(), node.getSecondary(), node.getSecondaryChance());
        Holder<ConfiguredFeature<GT4ROreFeatureConfig, ?>> configuredFeature = RubberTreeWorldGen.register(id, GT4RFeatures.ORE, config);
        List<PlacementModifier> list = new ArrayList<>(List.of(BiomeFilter.biome(), InSquarePlacement.spread()));
        list.add(node.isTriangle() ? HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)) : HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
        list.add(rare ? RarityFilter.onAverageOnceEvery(weight) : CountPlacement.of(weight));
        Holder<PlacedFeature> placedFeature = RubberTreeWorldGen.createPlacedFeature(id, configuredFeature, list.toArray(new PlacementModifier[0]));
        FEATURE_MAP.put(id, new MapWrapper(placedFeature, dimensions, filter));
    }

    record MapWrapper(Holder<PlacedFeature> feature, List<ResourceKey<Level>> dimensions, GT4ROreFeatureConfig.FilterContext filterContext){}

    @SafeVarargs
    public static void createOrePlacedFeature(String id, Material material, int minY, int maxY, int weight, int size, String secondary, float secondaryChance, float discardChance, boolean triangle, boolean rare, ResourceKey<Level>... dimensions){
        createOrePlacedFeature(id, material, minY, maxY, weight, size, secondary, secondaryChance, discardChance, List.of(dimensions), (b) -> true, triangle, rare);
    }
}
