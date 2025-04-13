package org.gtreimagined.gt4r.loader;

import net.minecraft.world.level.Level;
import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.GTLibConfig;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.event.GTWorldGenEvent;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gtlib.worldgen.smallore.SmallOreBuilder;
import org.gtreimagined.gtlib.worldgen.stonelayer.StoneLayerBuilder;
import org.gtreimagined.gtlib.worldgen.vanillaore.VanillaVeinBuilder;

import static org.gtreimagined.gtlib.data.GTLibMaterials.*;
import static org.gtreimagined.gtlib.data.VanillaStoneTypes.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreBlocks.*;

public class WorldGenLoader {


    public static void init(GTWorldGenEvent event) {
        if (GTLibConfig.STONE_LAYERS.get() && !GTAPI.isModLoaded("gt5r")){
            initStoneLayers(event);
        }
        initVanillaOres(event);
    }

    private static void initStoneLayers(GTWorldGenEvent ev){
        ev.stoneLayer(new StoneLayerBuilder(id("stone")).withStone(STONE).withWeight(6).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("black_granite")).withStone(GTCoreBlocks.BLACK_GRANITE).withWeight(2).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("red_granite")).withStone(GTCoreBlocks.RED_GRANITE).withWeight(2).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("komatiite")).withStone(KOMATIITE).withWeight(4).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("basalt")).withStone(BASALT).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("marble")).withStone(MARBLE).withWeight(4).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("limestone")).withStone(LIMESTONE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("green_schist")).withStone(GREEN_SCHIST).withWeight(1).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("blue_schist")).withStone(BLUE_SCHIST).withWeight(1).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("kimberlite")).withStone(KIMBERLITE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("quartzite")).withStone(QUARTZITE).withWeight(4).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("shale")).withStone(SHALE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("slate")).withStone(SLATE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("granite")).withStone(GRANITE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("diorite")).withStone(DIORITE).withWeight(3).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("andesite")).withStone(ANDESITE).withWeight(4).buildVein());
        ev.stoneLayer(new StoneLayerBuilder(id("tuff")).withStone(TUFF).withWeight(3).buildVein());
    }

    private static void initVanillaOres(GTWorldGenEvent event){
        event.smallOre(new SmallOreBuilder(id("ruby")).withMaterial(Ruby).withAmountPerChunk(2).withBiomes(tagged("has_ruby")).inDimension(Level.OVERWORLD).buildMaterial());
        event.smallOre(new SmallOreBuilder(id("sapphire")).withMaterial(Sapphire).withAmountPerChunk(2).withBiomes(tagged("has_sapphire")).inDimension(Level.OVERWORLD).buildMaterial());
        event.smallOre(new SmallOreBuilder(id("coal")).withMaterial(Coal).withAmountPerChunk(8).atHeight(16, 126).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("tin")).withMaterial(Tin).atHeight(-16, 48).withWeight(25).withSize(8).withSecondaryMaterial(Iron, 0.01f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("nickel")).withMaterial(Nickel).atHeight(-54, -32).withProbability(10).withSize(32).withBiomes(tagged("has_nickel")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("uranite")).withMaterial(Uraninite).atHeight(-16, 100).withWeight(8).withSize(4).withBiomes(tagged("is_dead")).setBiomeBlacklist(true).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("uranite_dead")).withMaterial(Uraninite).atHeight(-16, 100).withWeight(20).withSize(4).withBiomes(tagged("is_dead")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("cassiterite")).withMaterial(Cassiterite).atHeight(60, 120).withWeight(2).withSize(32).withSecondaryMaterial(Tin, 0.05f).withBiomes(tagged("has_cassiterite")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("tetrahedrite")).withMaterial(Tetrahedrite).atHeight(-20, 40).withWeight(10).withSize(12).withSecondaryMaterial(Copper, 0.225f).withBiomes(tagged("has_tetra")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("galena")).withMaterial(Galena).atHeight(-32, 32).withWeight(12).withSize(10).withDiscardOnExposureChance(0.2f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("bauxite")).withMaterial(Bauxite).atHeight(50, 120).withWeight(6).withSize(16).withBiomes(tagged("has_bauxite")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("ruby")).withMaterial(Ruby).atHeight(-16, 32).withWeight(3).withSize(6).withBiomes(tagged("has_ruby")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("sapphire")).withMaterial(Sapphire).atHeight(-16, 32).withWeight(3).withSize(6).withBiomes(tagged("has_sapphire")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("iridium")).withMaterial(Iridium).atHeight(-64, 128).withProbability(5).withSize(4).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("salt")).withMaterial(Salt).atHeight(0, 62).withWeight(6).withSize(64).setSpawnOnOceanFloor(true).withMaterialType(GTMaterialTypes.ORE_STONE).withBiomes(tagged("has_salt")).inDimension(Level.OVERWORLD).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("rock_salt")).withMaterial(RockSalt).atHeight(0, 80).withWeight(2).withSize(64).withMaterialType(GTMaterialTypes.ORE_STONE).withBiomes(tagged("has_rock_salt")).inDimension(Level.OVERWORLD).buildMaterial());
        // GT4R Nether Ores
        event.vanillaOre(new VanillaVeinBuilder(id("pyrite")).withMaterial(Pyrite).atHeight(0, 64).withWeight(8).withSize(16).inDimension(Level.NETHER).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("sphalerite")).withMaterial(Sphalerite).atHeight(32, 96).withWeight(8).withSize(16).inDimension(Level.NETHER).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("cinnabar")).withMaterial(Cinnabar).atHeight(64, 128).withWeight(7).withSize(16).inDimension(Level.NETHER).buildMaterial());
        // GT4R End Ores
        event.vanillaOre(new VanillaVeinBuilder(id("tungstate")).withMaterial(Tungstate).atHeight(0, 80).withWeight(2).withSize(16).inDimension(Level.END).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("platinum_end")).withMaterial(Platinum).atHeight(0, 80).withWeight(2).withSize(6).inDimension(Level.END).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("olivine")).withMaterial(Olivine).atHeight(0, 80).withWeight(5).withSize(8).inDimension(Level.END).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("sodalite")).withMaterial(Sodalite).atHeight(0, 80).withWeight(6).withSize(16).inDimension(Level.END).buildMaterial());
        event.vanillaOre(new VanillaVeinBuilder(id("chromite")).withMaterial(Chromite).atHeight(0, 80).withWeight(4).withSize(5).inDimension(Level.END).buildMaterial());

        if (GTLibConfig.VANILLA_ORE_GEN.get()){
            event.vanillaOre(new VanillaVeinBuilder(id("coal_upper")).withMaterial(Coal).atHeight(136, 320).withWeight(30).withSize(17).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("coal_lower")).withMaterial(Coal).atHeight(0, 192).withWeight(20).withSize(17).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("iron_upper")).withMaterial(Iron).atHeight(80, 384).withWeight(90).withSize(9).withSecondaryMaterial(Tin, 0.02f).withDiscardOnExposureChance(0.5f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("iron_middle")).withMaterial(Iron).atHeight(-24, 56).withWeight(10).withSize(9).withSecondaryMaterial(Tin, 0.02f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("iron_small")).withMaterial(Iron).atHeight(-64, 72).withWeight(10).withSize(4).withSecondaryMaterial(Tin, 0.02f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("gold_extra")).withMaterial(Gold).atHeight(32, 256).withWeight(50).withSize(9).withSecondaryMaterial(Copper, 0.02f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("gold")).withMaterial(Gold).atHeight(-64, 32).withWeight(4).withSize(9).withSecondaryMaterial(Copper, 0.02f).withDiscardOnExposureChance(0.5f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("gold_lower")).withMaterial(Gold).atHeight(-64, -48).withProbability(2).withSize(9).withSecondaryMaterial(Copper, 0.02f).withDiscardOnExposureChance(0.5f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("redstone")).withMaterial(Redstone).atHeight(-64, 15).withWeight(4).withSize(8).withSecondaryMaterial(Cinnabar, 0.01f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("redstone_lower")).withMaterial(Redstone).atHeight(-96, -32).withWeight(8).withSize(8).withSecondaryMaterial(Cinnabar, 0.01f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("diamond")).withMaterial(Diamond).atHeight(-144, 16).withWeight(7).withSize(4).withSecondaryMaterial(Diamond, 0.03f).withSecondaryType(GTMaterialTypes.ORE_SMALL).withDiscardOnExposureChance(0.5f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("diamond_large")).withMaterial(Diamond).atHeight(-144, 16).withProbability(9).withSecondaryMaterial(Diamond, 0.03f).withSecondaryType(GTMaterialTypes.ORE_SMALL).withSize(12).withDiscardOnExposureChance(0.7f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("diamond_buried")).withMaterial(Diamond).atHeight(-144, 16).withWeight(4).withSecondaryMaterial(Diamond, 0.03f).withSecondaryType(GTMaterialTypes.ORE_SMALL).withSize(8).withDiscardOnExposureChance(1.0f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("lapis")).withMaterial(GTLibMaterials.Lapis).atHeight(-32, 32).withWeight(2).withSize(7).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("lapis_buried")).withMaterial(GTLibMaterials.Lapis).atHeight(-64, 64).withWeight(4).withSize(7).withDiscardOnExposureChance(1.0f).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("emerald")).withMaterial(Emerald).atHeight(-16, 480).withWeight(100).withSize(3).setHasTriangleHeight(true).withBiomes("#minecraft:is_mountain").inDimension(Level.OVERWORLD).buildMaterial());
            int copperWeight = GT4RConfig.NERF_VANILLA_COPPER_GEN.get() ? 8 : 16;
            event.vanillaOre(new VanillaVeinBuilder(id("copper")).withMaterial(Copper).atHeight(-16, 112).withWeight(copperWeight).withSize(10).withSecondaryMaterial(Gold, 0.02f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
            event.vanillaOre(new VanillaVeinBuilder(id("copper_large")).withMaterial(Copper).atHeight(-16, 112).withWeight(copperWeight).withSize(20).withSecondaryMaterial(Gold, 0.02f).setHasTriangleHeight(true).inDimension(Level.OVERWORLD).buildMaterial());
        }
    }

    private static String tagged(String id){
        return "#" + GT4RRef.ID + ":" + id;
    }

    private static ResourceLocation id(String id){
        return new ResourceLocation(GT4RRef.ID, id);
    }
}
