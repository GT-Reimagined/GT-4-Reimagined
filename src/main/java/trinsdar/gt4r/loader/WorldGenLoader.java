package trinsdar.gt4r.loader;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayer;
import muramasa.antimatter.worldgen.object.WorldGenVeinLayer;
import muramasa.antimatter.worldgen.old.WorldGenAsteroid;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tree.RubberTreeWorldGen;
import trinsdar.gt4r.worldgen.WorldGenVanillaTypeVeins;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Ref.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static trinsdar.gt4r.data.Materials.*;

public class WorldGenLoader {


    public static void init() {
        WorldGenStoneLayer.add(GT4RData.GRANITE_BLACK, 2, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.GRANITE_RED, 2, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.KOMATIITE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.BASALT, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.MARBLE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.LIMESTONE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.GREEN_SCHIST, 1, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.BLUE_SCHIST, 1, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.KIMBERLITE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GT4RData.QUARTZITE, 4, World.OVERWORLD);
        if (AntimatterConfig.WORLD.ORE_VEINS){
            new WorldGenVanillaTypeVeins("copper", 0, 100, 20, 10, Copper, Gold, 2, World.OVERWORLD);
            new WorldGenVanillaTypeVeins("tin", 0, 100, 25, 6, Tin, Iron, 3, World.OVERWORLD);
            new WorldGenVanillaTypeVeins("uranite", 0, 100, 8, 4, Uraninite, null, 0, World.OVERWORLD).setInvalidBiomes(DEAD);
            new WorldGenVanillaTypeVeins("uranite_dead", 0, 100, 20, 4, Uraninite, null, 0, World.OVERWORLD).setValidBiomes(DEAD);
            new WorldGenVanillaTypeVeins("cassiterite", 30, 80, 2, 32, Cassiterite, Tin, 5, World.OVERWORLD).setValidBiomes(MUSHROOM, MOUNTAIN, CONIFEROUS, COLD).setInvalidBiomes(JUNGLE);
            new WorldGenVanillaTypeVeins("tetrahedrite", 20, 70, 7, 6, Tetrahedrite, Copper, 1, World.OVERWORLD).setValidBiomes(JUNGLE, SWAMP, MOUNTAIN, MUSHROOM).setInvalidBiomes(COLD);
            new WorldGenVanillaTypeVeins("galena", 0, 64, 9, 10, Galena, null, 0, World.OVERWORLD);
            new WorldGenVanillaTypeVeins("bauxite", 50, 120, 6, 16, Bauxite, null, 0, World.OVERWORLD).setValidBiomes(FOREST, PLAINS);
            new WorldGenVanillaTypeVeins("ruby", 0, 48, 3, 6, Ruby, null, 0, World.OVERWORLD).setValidBiomes(HOT).setInvalidBiomes(JUNGLE, OCEAN);
            new WorldGenVanillaTypeVeins("sapphire", 0, 48, 3, 6, Sapphire, null, 0, World.OVERWORLD).setValidBiomes(OCEAN, BEACH);
            new WorldGenVanillaTypeVeins("platinum", 10, 30, 3, 8, Platinum, Sphalerite, 5, World.OVERWORLD).setValidBiomes(JUNGLE);
            new WorldGenVanillaTypeVeins("iridium", 0, 128, 1, 2, Iridium, null, 0, World.OVERWORLD);
            new WorldGenVanillaTypeVeins("emerald", 0, 32, 4, 6, Emerald, null, 0, World.OVERWORLD).setValidBiomes(MOUNTAIN);
            new WorldGenVanillaTypeVeins("pyrite", 0, 64, 8, 16, Pyrite, null, 0, World.THE_NETHER);
            new WorldGenVanillaTypeVeins("sphalerite", 32, 96, 8, 16, Sphalerite, null, 0, World.THE_NETHER);
            new WorldGenVanillaTypeVeins("cinnabar",64, 128, 7, 16, Cinnabar, null, 0, World.THE_NETHER);
            new WorldGenVanillaTypeVeins("tungstate", 0, 80, 2, 16, Tungstate, null, 0, World.THE_END);
            new WorldGenVanillaTypeVeins("platinum", 0, 80, 2, 5, Platinum, null, 0, World.THE_END);
            new WorldGenVanillaTypeVeins("olivine", 0, 80, 5, 8, Olivine, null, 0, World.THE_END);
            new WorldGenVanillaTypeVeins("sodalite", 0, 80, 6, 16, Sodalite, null, 0, World.THE_END);
            new WorldGenVanillaTypeVeins("chromite", 0, 80, 4, 5, Chromite, null, 0, World.THE_END);
        }

    }
}
