package trinsdar.gt4r.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.event.AntimatterWorldGenEvent;
import muramasa.antimatter.worldgen.vein.WorldGenVein;
import muramasa.antimatter.worldgen.vein.WorldGenVeinBuilder;
import trinsdar.gt4r.GT4RConfig;

import static muramasa.antimatter.Data.ANDESITE;
import static muramasa.antimatter.Data.DIORITE;
import static muramasa.antimatter.Data.GRANITE;
import static muramasa.antimatter.Data.TUFF;
import static net.minecraft.world.level.Level.OVERWORLD;
import static trinsdar.gt4r.data.GT4RData.*;

public class WorldGenLoader {


    public static void init(AntimatterWorldGenEvent event) {
        if (GT4RConfig.WORLD.GENERATE_STONE_LAYERS){
            WorldGenVein.setLayerChance(WorldGenVein.STONE_VEIN_LAYER, 0.25f);
            event.vein(new WorldGenVeinBuilder("vein_granite_black")
                    .asMediumStoneVein(2, -64, 0, GRANITE_BLACK, OVERWORLD)
                    .buildVein());

            event.vein(new WorldGenVeinBuilder("vein_granite_red")
                    .asMediumStoneVein(2, -32, 32, GRANITE_RED, OVERWORLD)
                    .buildVein());

            event.vein(new WorldGenVeinBuilder("vein_basalt")
                    .asMediumStoneVein(5, -64, 0, BASALT, OVERWORLD)
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
    }
}
