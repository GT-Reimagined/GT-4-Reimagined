package trinsdar.gt4r.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.Data.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RConfiguredFeatures {

    //public static final GT4ROreFeatureConfig IRON = new GT4ROreFeatureConfig("iron", 1, "iron", "nickel", 5);
    public static final GT4ROreFeatureConfig COPPER_CONFIG = new GT4ROreFeatureConfig("copper", 0, 100, 15, 10, Copper, Gold, 2, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD).setInvertBiomeFilter(true);
    public static final GT4ROreFeatureConfig TIN_CONFIG = new GT4ROreFeatureConfig("tin", 0, 65, 25, 8, Tin, Iron, 1, World.OVERWORLD);
    public static final GT4ROreFeatureConfig URANITE_CONFIG = new GT4ROreFeatureConfig("uranite", 0, 100, 8, 4, Uraninite, NULL, 0, World.OVERWORLD).setInvalidBiomes(DEAD);
    public static final GT4ROreFeatureConfig URANITE_DEAD_CONFIG = new GT4ROreFeatureConfig("uranite_dead", 0, 100, 20, 4, Uraninite, NULL, 0, World.OVERWORLD).setValidBiomes(DEAD);
    public static final GT4ROreFeatureConfig CASSITERITE_CONFIG = new GT4ROreFeatureConfig("cassiterite", 30, 80, 2, 32, Cassiterite, Tin, 5, World.OVERWORLD).setValidBiomes(MUSHROOM, MOUNTAIN, CONIFEROUS, COLD).setInvalidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig TETRAHEDRITE_CONFIG = new GT4ROreFeatureConfig("tetrahedrite", 40, 90, 10, 12, Tetrahedrite, Copper, 25, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD);
    public static final GT4ROreFeatureConfig GALENA_CONFIG = new GT4ROreFeatureConfig("galena", 0, 64, 12, 10, Galena, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig BAUXITE_CONFIG = new GT4ROreFeatureConfig("bauxite", 50, 120, 6, 16, Bauxite, NULL, 0, World.OVERWORLD).setValidBiomes(FOREST, PLAINS);
    public static final GT4ROreFeatureConfig RUBY_CONFIG = new GT4ROreFeatureConfig("ruby", 0, 48, 3, 6, Ruby, NULL, 0, World.OVERWORLD).setValidBiomes(HOT).setInvalidBiomes(JUNGLE, OCEAN);
    public static final GT4ROreFeatureConfig SAPPHIRE_CONFIG = new GT4ROreFeatureConfig("sapphire", 0, 48, 3, 6, Sapphire, NULL, 0, World.OVERWORLD).setValidBiomes(OCEAN, BEACH);
    public static final GT4ROreFeatureConfig PLATINUM_CONFIG = new GT4ROreFeatureConfig("platinum", 10, 30, 3, 6, Platinum, Sphalerite, 5, World.OVERWORLD).setValidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig IRIDIUM_CONFIG = new GT4ROreFeatureConfig("iridium", 0, 128, 1, 2, Iridium, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig EMERALD_CONFIG = new GT4ROreFeatureConfig("emerald", 0, 32, 4, 6, Emerald, NULL, 0, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig PYRITE_CONFIG = new GT4ROreFeatureConfig("pyrite", 0, 64, 8, 16, Pyrite, NULL, 0, World.NETHER);
    public static final GT4ROreFeatureConfig SPHALERITE_CONFIG = new GT4ROreFeatureConfig("sphalerite", 32, 96, 8, 16, Sphalerite, NULL, 0, World.NETHER);
    public static final GT4ROreFeatureConfig CINNABAR_CONFIG = new GT4ROreFeatureConfig("cinnabar",64, 128, 7, 16, Cinnabar, NULL, 0, World.NETHER);
    public static final GT4ROreFeatureConfig TUNGSTATE_CONFIG = new GT4ROreFeatureConfig("tungstate", 0, 80, 2, 16, Tungstate, NULL, 0, World.END);
    public static final GT4ROreFeatureConfig PLATINUM_END_CONFIG = new GT4ROreFeatureConfig("platinum_end", 0, 80, 2, 6, Platinum, NULL, 0, World.END);
    public static final GT4ROreFeatureConfig OLIVINE_CONFIG = new GT4ROreFeatureConfig("olivine", 0, 80, 5, 8, Olivine, NULL, 0, World.END);
    public static final GT4ROreFeatureConfig SODALITE_CONFIG = new GT4ROreFeatureConfig("sodalite", 0, 80, 6, 16, Sodalite, Lapis, 3, World.END);
    public static final GT4ROreFeatureConfig CHROMITE_CONFIG = new GT4ROreFeatureConfig("chromite", 0, 80, 4, 5, Chromite, NULL, 0, World.END);
    public static final GT4ROreFeatureConfig SALT_CONFIG = new GT4ROreFeatureConfig("salt", 0, 62, 6, 64, Salt, NULL, 0, World.OVERWORLD).setValidBiomes(OCEAN);
    public static final GT4ROreFeatureConfig ROCK_SALT_CONFIG = new GT4ROreFeatureConfig("rock_salt", 0, 80, 4, 64, RockSalt, NULL, 0, World.OVERWORLD).setValidBiomes(SANDY);

    //Vanilla
    public static final GT4ROreFeatureConfig IRON_CONFIG = new GT4ROreFeatureConfig("iron", 0, 64, 20, 9, Iron, Tin, 2, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD_CONFIG = new GT4ROreFeatureConfig("gold", 0, 100, 2, 9, Gold, Copper, 2, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD_MESA_CONFIG = new GT4ROreFeatureConfig("gold_mesa", 32, 80, 20, 9, Gold, Copper, 2, World.OVERWORLD).setValidBiomes(MESA);
    public static final GT4ROreFeatureConfig EMERALD_VANILLA_CONFIG = new GT4ROreFeatureConfig("emerald", 0, 100, 15, 10, Emerald, NULL, 0, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig DIAMOND_CONFIG = new GT4ROreFeatureConfig("diamond", 0, 16, 1, 8, Diamond, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig COAL_CONFIG = new GT4ROreFeatureConfig("coal", 0, 128, 20, 17, Coal, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig LAPIS_CONFIG = new GT4ROreFeatureConfig("lapis", 0, 100, 1, 7, Lapis, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig REDSTONE_CONFIG = new GT4ROreFeatureConfig("redstone", 0, 16, 8, 8, Redstone, Cinnabar, 1, World.OVERWORLD);

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
