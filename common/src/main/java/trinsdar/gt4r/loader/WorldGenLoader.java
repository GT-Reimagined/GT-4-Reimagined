package trinsdar.gt4r.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.event.WorldGenEvent;
import muramasa.antimatter.worldgen.vanillaore.WorldGenVanillaOre;
import muramasa.antimatter.worldgen.vanillaore.WorldGenVanillaOreBuilder;
import muramasa.antimatter.worldgen.vein.WorldGenVein;
import muramasa.antimatter.worldgen.vein.WorldGenVeinBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterStoneTypes.ANDESITE;
import static muramasa.antimatter.data.AntimatterStoneTypes.DIORITE;
import static muramasa.antimatter.data.AntimatterStoneTypes.GRANITE;
import static muramasa.antimatter.data.AntimatterStoneTypes.TUFF;
import static net.minecraft.world.level.Level.OVERWORLD;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;

public class WorldGenLoader {


    public static void init(WorldGenEvent event) {
        if (GT4RConfig.WORLD.GENERATE_STONE_LAYERS){
            initStoneLayers(event);
        }
        initVanillaOres(event);
    }

    private static void initStoneLayers(WorldGenEvent event){
        WorldGenVein.setLayerChance(WorldGenVein.STONE_VEIN_LAYER, 0.25f);
        event.vein(new WorldGenVeinBuilder("vein_granite_black")
                .asMediumStoneVein(2, -64, 0, GRANITE_BLACK, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_granite_red")
                .asMediumStoneVein(2, -32, 32, GRANITE_RED, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_basalt")
                .asMediumStoneVein(5, -64, 0, AntimatterStoneTypes.BASALT, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_komatiite")
                .asMediumStoneVein(4, 0, 64, KOMATIITE, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_marble")
                .asLargeStoneVein(4, 32, 160, MARBLE, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_limestone")
                .asLargeStoneVein(4, 32, 160, LIMESTONE, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_green_schist")
                .asMediumStoneVein(2, 64, 320, GREEN_SCHIST, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_blue_schist")
                .asMediumStoneVein(2, 64, 320, BLUE_SCHIST, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_kimberlite")
                .asMediumStoneVein(3, 64, 320, KIMBERLITE, OVERWORLD)
                .buildVein());

        event.vein(new WorldGenVeinBuilder("vein_quartzite")
                .asMediumStoneVein(3, 64, 320, QUARTZITE, OVERWORLD)
                .buildVein());
        if (AntimatterConfig.WORLD.VANILLA_STONE_GEN){
            event.vein(new WorldGenVeinBuilder("vein_tuff")
                    .asSmallStoneVein(5, -64, 16, TUFF, OVERWORLD)
                    .buildVein());

            event.vein(new WorldGenVeinBuilder("vein_granite")
                    .asMediumStoneVein(5, 32, 128, GRANITE, OVERWORLD)
                    .buildVein());

            event.vein(new WorldGenVeinBuilder("vein_diorite")
                    .asMediumStoneVein(5, 32, 128, DIORITE, OVERWORLD)
                    .buildVein());

            event.vein(new WorldGenVeinBuilder("vein_andesite")
                    .asMediumStoneVein(5, 32, 128, ANDESITE, OVERWORLD)
                    .buildVein());
        }
    }

    private static void initVanillaOres(WorldGenEvent event){
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Tin).atHeight(-64, 0).withWeight(25).withSize(8).withSecondaryMaterial(Iron, 0.01f).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Uraninite).atHeight(-16, 100).withWeight(8).withSize(4).withBiomes(tagged("is_dead")).setBiomeBlacklist(true).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Uraninite).withCustomId("uranite_dead").atHeight(-16, 100).withWeight(20).withSize(4).withBiomes(tagged("is_dead")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Cassiterite).atHeight(-30, 30).withWeight(2).withSize(32).withSecondaryMaterial(Tin, 0.05f).withBiomes(tagged("has_cassiterite")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Tetrahedrite).atHeight(-20, 40).withWeight(10).withSize(12).withSecondaryMaterial(Copper, 0.225f).withBiomes(tagged("has_tetra")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Galena).atHeight(-32, 32).withWeight(12).withSize(10).withDiscardOnExposureChance(0.2f).setHasTriangleHeight(true).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Bauxite).atHeight(50, 120).withWeight(6).withSize(16).withBiomes(tagged("has_bauxite")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Ruby).atHeight(-16, 32).withWeight(3).withSize(6).withBiomes(tagged("has_ruby")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Sapphire).atHeight(-16, 32).withWeight(3).withSize(6).withBiomes(tagged("has_sapphire")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Platinum).atHeight(-40, 20).withWeight(3).withSize(6).withSecondaryMaterial(Sphalerite, 0.05f).withBiomes(tagged("has_platinum")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Iridium).atHeight(-64, 128).withWeight(1).withSize(4).setRare(true).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Salt).atHeight(0, 62).withWeight(6).withSize(64).setSpawnOnOceanFloor(true).withMaterialType(AntimatterMaterialTypes.ORE_STONE).withBiomes(tagged("has_salt")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(RockSalt).atHeight(0, 80).withWeight(2).withSize(64).withMaterialType(AntimatterMaterialTypes.ORE_STONE).withBiomes(tagged("has_rock_salt")).buildMaterial());
        // GT4R Nether Ores
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Pyrite).atHeight(0, 64).withWeight(8).withSize(16).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Sphalerite).atHeight(32, 96).withWeight(8).withSize(16).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Pyrite).atHeight(64, 128).withWeight(7).withSize(16).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
        // GT4R End Ores
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Tungstate).atHeight(0, 80).withWeight(2).withSize(16).withDimensions(new ResourceLocation("the_end")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Platinum).withCustomId("platinum_end").atHeight(0, 80).withWeight(2).withSize(6).withDimensions(new ResourceLocation("the_end")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Olivine).atHeight(0, 80).withWeight(5).withSize(8).withDimensions(new ResourceLocation("the_end")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Sodalite).atHeight(0, 80).withWeight(6).withSize(16).withDimensions(new ResourceLocation("the_end")).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Chromite).atHeight(0, 80).withWeight(4).withSize(5).withDimensions(new ResourceLocation("the_end")).buildMaterial());

        if (AntimatterConfig.WORLD.VANILLA_ORE_GEN){
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Coal).withCustomId("coal_upper").atHeight(136, 320).withWeight(30).withSize(17).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Coal).withCustomId("coal_lower").atHeight(0, 192).withWeight(20).withSize(17).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Iron).withCustomId("iron_upper").atHeight(80, 384).withWeight(90).withSize(9).withSecondaryMaterial(Tin, 0.02f).withDiscardOnExposureChance(0.5f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Iron).withCustomId("iron_middle").atHeight(-24, 56).withWeight(10).withSize(9).withSecondaryMaterial(Tin, 0.02f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Iron).withCustomId("iron_small").atHeight(-64, 72).withWeight(10).withSize(4).withSecondaryMaterial(Tin, 0.02f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Gold).withCustomId("gold_extra").atHeight(32, 256).withWeight(50).withSize(9).withSecondaryMaterial(Copper, 0.02f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Gold).atHeight(-64, 32).withWeight(4).withSize(9).withSecondaryMaterial(Copper, 0.02f).withDiscardOnExposureChance(0.5f).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Gold).withCustomId("gold_lower").atHeight(-64, -48).withWeight(2).withSize(9).withSecondaryMaterial(Copper, 0.02f).withDiscardOnExposureChance(0.5f).setRare(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Redstone).atHeight(-64, 15).withWeight(4).withSize(8).withSecondaryMaterial(Cinnabar, 0.01f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Redstone).withCustomId("redstone_lower").atHeight(-96, -32).withWeight(8).withSize(8).withSecondaryMaterial(Cinnabar, 0.01f).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Diamond).atHeight(-144, 16).withWeight(7).withSize(4).withDiscardOnExposureChance(0.5f).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Diamond).withCustomId("diamond_large").atHeight(-144, 16).withWeight(9).withSize(12).withDiscardOnExposureChance(0.7f).setHasTriangleHeight(true).setRare(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Diamond).withCustomId("diamond_buried").atHeight(-144, 16).withWeight(4).withSize(8).withDiscardOnExposureChance(1.0f).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(AntimatterMaterials.Lapis).atHeight(-32, 32).withWeight(2).withSize(7).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(AntimatterMaterials.Lapis).withCustomId("lapis_buried").atHeight(-64, 64).withWeight(4).withSize(7).withDiscardOnExposureChance(1.0f).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Emerald).atHeight(-16, 480).withWeight(100).withSize(3).setHasTriangleHeight(true).withBiomes("#minecraft:is_mountain").buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Copper).atHeight(-16, 112).withWeight(16).withSize(10).withSecondaryMaterial(Gold, 0.02f).setHasTriangleHeight(true).buildMaterial());
            event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Copper).withCustomId("copper_large").atHeight(-16, 112).withWeight(16).withSize(20).withSecondaryMaterial(Gold, 0.02f).setHasTriangleHeight(true).buildMaterial());
        }
    }

    private static String tagged(String id){
        return "#" + Ref.ID + ":" + id;
    }
}
