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
import static trinsdar.gt4r.data.Materials.Copper;
import static trinsdar.gt4r.data.Materials.Gold;
import static trinsdar.gt4r.data.Materials.Tin;
import static trinsdar.gt4r.data.Materials.Uraninite;

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
            new WorldGenVanillaTypeVeins("copper", 0, 100, 15, 10, Copper, Gold, 10, World.OVERWORLD);
            new WorldGenVanillaTypeVeins("tin", 0, 100, 20, 6, Tin, null, 0, World.OVERWORLD);
            List<Type> types = new ArrayList<>(Type.getAll());
            types.remove(Type.DEAD);
            new WorldGenVanillaTypeVeins("uranite", 0, 100, 6, 3, Uraninite, null, 0, World.OVERWORLD).setValidBiomes(types.toArray(new Type[0]));
            new WorldGenVanillaTypeVeins("uranite", 0, 100, 15, 3, Uraninite, null, 0, World.OVERWORLD).setValidBiomes(Type.DEAD);

        }

    }
}
