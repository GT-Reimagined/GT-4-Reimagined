package trinsdar.gt4r.worldgen;

import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.BiomeDictionary;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.NULL;
import static muramasa.antimatter.Data.ORE_STONE;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RFeatures {
    public static final AntimatterFeature<?> ORE = new FeatureVanillaTypeOre();
    public static final AntimatterFeature<?> RETROGEN_ORE_STONE = new RetrogenFeature();

    //public static final GT4ROreFeatureConfig IRON = new GT4ROreFeatureConfig("iron", 1, "iron", "nickel", 5);
    public static final GT4ROreFeatureConfig COPPER = new GT4ROreFeatureConfig("copper", 0, 100, 15, 10, Copper, Gold, 2, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD).setInvertBiomeFilter(true);
    public static final GT4ROreFeatureConfig TIN = new GT4ROreFeatureConfig("tin", 0, 65, 25, 8, Tin, Iron, 1, World.OVERWORLD);
    public static final GT4ROreFeatureConfig URANITE = new GT4ROreFeatureConfig("uranite", 0, 100, 8, 4, Uraninite, NULL, 0, World.OVERWORLD).setInvalidBiomes(DEAD);
    public static final GT4ROreFeatureConfig URANITE_DEAD = new GT4ROreFeatureConfig("uranite_dead", 0, 100, 20, 4, Uraninite, NULL, 0, World.OVERWORLD).setValidBiomes(DEAD);
    public static final GT4ROreFeatureConfig CASSITERITE = new GT4ROreFeatureConfig("cassiterite", 30, 80, 2, 32, Cassiterite, Tin, 5, World.OVERWORLD).setValidBiomes(MUSHROOM, MOUNTAIN, CONIFEROUS, COLD).setInvalidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig TETRAHEDRITE = new GT4ROreFeatureConfig("tetrahedrite", 40, 90, 10, 12, Tetrahedrite, Copper, 25, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD);
    public static final GT4ROreFeatureConfig GALENA = new GT4ROreFeatureConfig("galena", 0, 64, 12, 10, Galena, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig BAUXITE = new GT4ROreFeatureConfig("bauxite", 50, 120, 6, 16, Bauxite, NULL, 0, World.OVERWORLD).setValidBiomes(FOREST, PLAINS);
    public static final GT4ROreFeatureConfig RUBY = new GT4ROreFeatureConfig("ruby", 0, 48, 3, 6, Ruby, NULL, 0, World.OVERWORLD).setValidBiomes(HOT).setInvalidBiomes(JUNGLE, OCEAN);
    public static final GT4ROreFeatureConfig SAPPHIRE = new GT4ROreFeatureConfig("sapphire", 0, 48, 3, 6, Sapphire, NULL, 0, World.OVERWORLD).setValidBiomes(OCEAN, BEACH);
    public static final GT4ROreFeatureConfig PLATINUM = new GT4ROreFeatureConfig("platinum", 10, 30, 3, 6, Platinum, Sphalerite, 5, World.OVERWORLD).setValidBiomes(JUNGLE);
    public static final GT4ROreFeatureConfig IRIDIUM = new GT4ROreFeatureConfig("iridium", 0, 128, 1, 2, Iridium, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig EMERALD = new GT4ROreFeatureConfig("emerald", 0, 32, 4, 6, Emerald, NULL, 0, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig PYRITE = new GT4ROreFeatureConfig("pyrite", 0, 64, 8, 16, Pyrite, NULL, 0, World.THE_NETHER);
    public static final GT4ROreFeatureConfig SPHALERITE = new GT4ROreFeatureConfig("sphalerite", 32, 96, 8, 16, Sphalerite, NULL, 0, World.THE_NETHER);
    public static final GT4ROreFeatureConfig CINNABAR = new GT4ROreFeatureConfig("cinnabar",64, 128, 7, 16, Cinnabar, NULL, 0, World.THE_NETHER);
    public static final GT4ROreFeatureConfig TUNGSTATE = new GT4ROreFeatureConfig("tungstate", 0, 80, 2, 16, Tungstate, NULL, 0, World.THE_END);
    public static final GT4ROreFeatureConfig PLATINUM_END = new GT4ROreFeatureConfig("platinum_end", 0, 80, 2, 6, Platinum, NULL, 0, World.THE_END);
    public static final GT4ROreFeatureConfig OLIVINE = new GT4ROreFeatureConfig("olivine", 0, 80, 5, 8, Olivine, NULL, 0, World.THE_END);
    public static final GT4ROreFeatureConfig SODALITE = new GT4ROreFeatureConfig("sodalite", 0, 80, 6, 16, Sodalite, Lapis, 3, World.THE_END);
    public static final GT4ROreFeatureConfig CHROMITE = new GT4ROreFeatureConfig("chromite", 0, 80, 4, 5, Chromite, NULL, 0, World.THE_END);
    public static final GT4ROreFeatureConfig SALT = new GT4ROreFeatureConfig("salt", 0, 62, 6, 64, Salt, NULL, 0, World.OVERWORLD).setValidBiomes(OCEAN);
    public static final GT4ROreFeatureConfig ROCK_SALT = new GT4ROreFeatureConfig("rock_salt", 0, 80, 4, 64, RockSalt, NULL, 0, World.OVERWORLD).setValidBiomes(SANDY);


    //Vanilla
    public static final GT4ROreFeatureConfig IRON = new GT4ROreFeatureConfig("iron", 0, 64, 20, 9, Iron, Tin, 2, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD = new GT4ROreFeatureConfig("gold", 0, 100, 2, 9, Gold, Copper, 2, World.OVERWORLD);
    public static final GT4ROreFeatureConfig GOLD_MESA = new GT4ROreFeatureConfig("gold_mesa", 32, 80, 20, 9, Gold, Copper, 2, World.OVERWORLD).setValidBiomes(MESA);
    public static final GT4ROreFeatureConfig EMERALD_VANILLA = new GT4ROreFeatureConfig("emerald", 0, 100, 15, 10, Emerald, NULL, 0, World.OVERWORLD).setValidBiomes(MOUNTAIN);
    public static final GT4ROreFeatureConfig DIAMOND = new GT4ROreFeatureConfig("diamond", 0, 16, 1, 8, Diamond, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig COAL = new GT4ROreFeatureConfig("coal", 0, 128, 20, 17, Coal, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig LAPIS = new GT4ROreFeatureConfig("lapis", 0, 100, 1, 7, Lapis, NULL, 0, World.OVERWORLD);
    public static final GT4ROreFeatureConfig REDSTONE = new GT4ROreFeatureConfig("redstone", 0, 16, 8, 8, Redstone, Cinnabar, 1, World.OVERWORLD);

    public static OreFeatureConfig SALT_OVERRIDE = new OreFeatureConfig(new BlockMatchRuleTest(GT4RData.SALT.getState().getBlock()), ORE_STONE.get().get(Salt).asState(), 1);
    public static final OreFeatureConfig ROCK_SALT_OVERRIDE = new OreFeatureConfig(new BlockMatchRuleTest(GT4RData.ROCK_SALT.getState().getBlock()), ORE_STONE.get().get(RockSalt).asState(), 1);

    public static void init(){


    }
}
