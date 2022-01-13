package trinsdar.gt4r.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.config.OreConfigNode;

import static muramasa.antimatter.Data.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RConfiguredFeatures {

    public static final GT4ROreFeatureConfig COPPER_CONFIG = new GT4ROreFeatureConfig("copper", new OreConfigNode(true, 0, 100, 15, 10, "gold", 2), Copper, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD).setInvertBiomeFilter(true);
    public static final GT4ROreFeatureConfig TIN_CONFIG = new GT4ROreFeatureConfig("tin", new OreConfigNode(true, 0, 65, 25, 8, "iron", 1), Tin, World.OVERWORLD);
    public static final GT4ROreFeatureConfig URANITE_CONFIG = new GT4ROreFeatureConfig("uranite", new OreConfigNode(true, 0, 100, 8, 4, "null", 0), Uraninite, World.OVERWORLD).setInvalidBiomes(DEAD);
    public static final GT4ROreFeatureConfig URANITE_DEAD_CONFIG = new GT4ROreFeatureConfig("uranite_dead", new OreConfigNode(true, 0, 100, 20, 4, "null", 0), Uraninite, World.OVERWORLD).setValidBiomes(DEAD);
    public static final GT4ROreFeatureConfig CASSITERITE_CONFIG = new GT4ROreFeatureConfig("cassiterite", new OreConfigNode(true, 30, 80, 2, 32, "tin", 5), Cassiterite, World.OVERWORLD).setValidBiomes(MUSHROOM, MOUNTAIN, CONIFEROUS, COLD).setInvalidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig TETRAHEDRITE_CONFIG = new GT4ROreFeatureConfig("tetrahedrite", new OreConfigNode(true, 40, 90, 10, 12, "copper", 25), Tetrahedrite, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD);
    public static final GT4ROreFeatureConfig GALENA_CONFIG = new GT4ROreFeatureConfig("galena", new OreConfigNode(true, 0, 64, 12, 10, "null", 0), Galena, World.OVERWORLD);
    public static final GT4ROreFeatureConfig BAUXITE_CONFIG = new GT4ROreFeatureConfig("bauxite", new OreConfigNode(true, 50, 120, 6, 16, "null", 0), Bauxite, World.OVERWORLD).setValidBiomes(FOREST, PLAINS);
    public static final GT4ROreFeatureConfig RUBY_CONFIG = new GT4ROreFeatureConfig("ruby", new OreConfigNode(true, 0, 48, 3, 6, "null", 0), Ruby, World.OVERWORLD).setValidBiomes(HOT).setInvalidBiomes(JUNGLE, OCEAN);
    public static final GT4ROreFeatureConfig SAPPHIRE_CONFIG = new GT4ROreFeatureConfig("sapphire", new OreConfigNode(true, 0, 48, 3, 6, "null", 0), Sapphire, World.OVERWORLD).setValidBiomes(OCEAN, BEACH);
    public static final GT4ROreFeatureConfig PLATINUM_CONFIG = new GT4ROreFeatureConfig("platinum", new OreConfigNode(true, 10, 30, 3, 6, "sphalerite", 5), Platinum, World.OVERWORLD).setValidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig IRIDIUM_CONFIG = new GT4ROreFeatureConfig("iridium", new OreConfigNode(true, 0, 128, 1, 2, "null", 0), Iridium, World.OVERWORLD);
    public static final GT4ROreFeatureConfig EMERALD_CONFIG = new GT4ROreFeatureConfig("emerald", new OreConfigNode(true, 0, 32, 4, 6, "null", 0), Emerald, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig PYRITE_CONFIG = new GT4ROreFeatureConfig("pyrite", new OreConfigNode(true, 0, 64, 8, 16, "null", 0), Pyrite, World.NETHER);
    public static final GT4ROreFeatureConfig SPHALERITE_CONFIG = new GT4ROreFeatureConfig("sphalerite", new OreConfigNode(true, 32, 96, 8, 16, "null", 0), Sphalerite, World.NETHER);
    public static final GT4ROreFeatureConfig CINNABAR_CONFIG = new GT4ROreFeatureConfig("cinnabar",new OreConfigNode(true, 64, 128, 7, 16, "null", 0), Cinnabar, World.NETHER);
    public static final GT4ROreFeatureConfig TUNGSTATE_CONFIG = new GT4ROreFeatureConfig("tungstate", new OreConfigNode(true, 0, 80, 2, 16, "null", 0), Tungstate, World.END);
    public static final GT4ROreFeatureConfig PLATINUM_END_CONFIG = new GT4ROreFeatureConfig("platinum_end", new OreConfigNode(true, 0, 80, 2, 6, "null", 0), Platinum, World.END);
    public static final GT4ROreFeatureConfig OLIVINE_CONFIG = new GT4ROreFeatureConfig("olivine", new OreConfigNode(true, 0, 80, 5, 8, "null", 0), Olivine, World.END);
    public static final GT4ROreFeatureConfig SODALITE_CONFIG = new GT4ROreFeatureConfig("sodalite", new OreConfigNode(true, 0, 80, 6, 16, "lapis", 3), Sodalite, World.END);
    public static final GT4ROreFeatureConfig CHROMITE_CONFIG = new GT4ROreFeatureConfig("chromite", new OreConfigNode(true, 0, 80, 4, 5, "null", 0), Chromite, World.END);
    public static final GT4ROreFeatureConfig SALT_CONFIG = new GT4ROreFeatureConfig("salt", new OreConfigNode(true, 0, 62, 6, 64, "null", 0), Salt, World.OVERWORLD).setValidBiomes(OCEAN);
    public static final GT4ROreFeatureConfig ROCK_SALT_CONFIG = new GT4ROreFeatureConfig("rock_salt", new OreConfigNode(true, 0, 80, 4, 64, "null", 0), RockSalt, World.OVERWORLD).setValidBiomes(SANDY);

    //Vanilla
    public static final GT4ROreFeatureConfig IRON_CONFIG = new GT4ROreFeatureConfig("iron", new OreConfigNode(true, 0, 64, 20, 9, "tin", 2), Iron, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD_CONFIG = new GT4ROreFeatureConfig("gold", new OreConfigNode(true, 0, 100, 2, 9, "copper", 2), Gold, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD_MESA_CONFIG = new GT4ROreFeatureConfig("gold_mesa", new OreConfigNode(true, 32, 80, 20, 9, "copper", 2), Gold, World.OVERWORLD).setValidBiomes(MESA);
    public static final GT4ROreFeatureConfig EMERALD_VANILLA_CONFIG = new GT4ROreFeatureConfig("emerald_vanilla", new OreConfigNode(true, 0, 100, 15, 10, "null", 0), Emerald, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig DIAMOND_CONFIG = new GT4ROreFeatureConfig("diamond", new OreConfigNode(true, 0, 16, 1, 8, "null", 0), Diamond, World.OVERWORLD);
    public static final GT4ROreFeatureConfig COAL_CONFIG = new GT4ROreFeatureConfig("coal", new OreConfigNode(true, 0, 128, 20, 17, "null", 0), Coal, World.OVERWORLD);
    public static final GT4ROreFeatureConfig LAPIS_CONFIG = new GT4ROreFeatureConfig("lapis", new OreConfigNode(true, 0, 100, 1, 7, "null", 0), Lapis, World.OVERWORLD);
    public static final GT4ROreFeatureConfig REDSTONE_CONFIG = new GT4ROreFeatureConfig("redstone", new OreConfigNode(true, 0, 16, 8, 8, "cinnabar", 1), Redstone, World.OVERWORLD);

    public static final ConfiguredFeature<?, ?> COPPER = register("copper", GT4RFeatures.ORE.getConfiguration(COPPER_CONFIG));
    public static final ConfiguredFeature<?, ?> TIN = register("tim", GT4RFeatures.ORE.getConfiguration(TIN_CONFIG));
    public static final ConfiguredFeature<?, ?> URANITE = register("uranite", GT4RFeatures.ORE.getConfiguration(URANITE_CONFIG));
    public static final ConfiguredFeature<?, ?> URANITE_DEAD = register("uranite_dead", GT4RFeatures.ORE.getConfiguration(URANITE_DEAD_CONFIG));
    public static final ConfiguredFeature<?, ?> CASSITERITE = register("cassiterite", GT4RFeatures.ORE.getConfiguration(CASSITERITE_CONFIG));
    public static final ConfiguredFeature<?, ?> TETRAHEDRITE = register("tetrahedrite", GT4RFeatures.ORE.getConfiguration(TETRAHEDRITE_CONFIG));
    public static final ConfiguredFeature<?, ?> GALENA = register("galena", GT4RFeatures.ORE.getConfiguration(GALENA_CONFIG));
    public static final ConfiguredFeature<?, ?> BAUXITE = register("bauxite", GT4RFeatures.ORE.getConfiguration(BAUXITE_CONFIG));
    public static final ConfiguredFeature<?, ?> RUBY = register("ruby", GT4RFeatures.ORE.getConfiguration(RUBY_CONFIG));
    public static final ConfiguredFeature<?, ?> SAPPHIRE = register("sapphire", GT4RFeatures.ORE.getConfiguration(SAPPHIRE_CONFIG));
    public static final ConfiguredFeature<?, ?> PLATINUM = register("platinum", GT4RFeatures.ORE.getConfiguration(PLATINUM_CONFIG));
    public static final ConfiguredFeature<?, ?> IRIDIUM = register("iridium", GT4RFeatures.ORE.getConfiguration(IRIDIUM_CONFIG));
    public static final ConfiguredFeature<?, ?> EMERALD = register("emerald", GT4RFeatures.ORE.getConfiguration(EMERALD_CONFIG));
    public static final ConfiguredFeature<?, ?> PYRITE = register("pyrite", GT4RFeatures.ORE.getConfiguration(PYRITE_CONFIG));
    public static final ConfiguredFeature<?, ?> SPHALERITE = register("sphalerite", GT4RFeatures.ORE.getConfiguration(SPHALERITE_CONFIG));
    public static final ConfiguredFeature<?, ?> CINNABAR = register("cinnabar", GT4RFeatures.ORE.getConfiguration(CINNABAR_CONFIG));
    public static final ConfiguredFeature<?, ?> TUNGSTATE = register("tungstate", GT4RFeatures.ORE.getConfiguration(TUNGSTATE_CONFIG));
    public static final ConfiguredFeature<?, ?> PLATINUM_END = register("platinum_end", GT4RFeatures.ORE.getConfiguration(PLATINUM_END_CONFIG));
    public static final ConfiguredFeature<?, ?> OLIVINE = register("olivine", GT4RFeatures.ORE.getConfiguration(OLIVINE_CONFIG));
    public static final ConfiguredFeature<?, ?> SODALITE = register("sodalite", GT4RFeatures.ORE.getConfiguration(SODALITE_CONFIG));
    public static final ConfiguredFeature<?, ?> CHROMITE = register("chromite", GT4RFeatures.ORE.getConfiguration(CHROMITE_CONFIG));
    public static final ConfiguredFeature<?, ?> SALT = register("salt", GT4RFeatures.ORE.getConfiguration(SALT_CONFIG));
    public static final ConfiguredFeature<?, ?> ROCK_SALT = register("rock_salt", GT4RFeatures.ORE.getConfiguration(ROCK_SALT_CONFIG));

    public static final ConfiguredFeature<?, ?> IRON = register("iron", GT4RFeatures.ORE.getConfiguration(IRON_CONFIG));
    public static final ConfiguredFeature<?, ?> COAL = register("coal", GT4RFeatures.ORE.getConfiguration(COAL_CONFIG));
    public static final ConfiguredFeature<?, ?> GOLD = register("gold", GT4RFeatures.ORE.getConfiguration(GOLD_CONFIG));
    public static final ConfiguredFeature<?, ?> GOLD_MESA = register("gold_mesa", GT4RFeatures.ORE.getConfiguration(GOLD_MESA_CONFIG));
    public static final ConfiguredFeature<?, ?> REDSTONE = register("redstone", GT4RFeatures.ORE.getConfiguration(REDSTONE_CONFIG));
    public static final ConfiguredFeature<?, ?> DIAMOND = register("diamond", GT4RFeatures.ORE.getConfiguration(DIAMOND_CONFIG));
    public static final ConfiguredFeature<?, ?> EMERALD_VANILLA = register("emerald_vanilla", GT4RFeatures.ORE.getConfiguration(EMERALD_VANILLA_CONFIG));
    public static final ConfiguredFeature<?, ?> LAPIS = register("lapis", GT4RFeatures.ORE.configured(LAPIS_CONFIG).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared());

    public static ConfiguredFeature<?,?> register(String id, ConfiguredFeature<?,?> feature){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Ref.ID, id), feature);
    }

    public static void init(){
    }
}
