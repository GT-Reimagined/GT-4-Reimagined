package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import muramasa.antimatter.worldgen.object.WorldGenVeinLayer;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

import java.util.Random;

public class FeatureVanillaTypeOre extends AntimatterFeature<GT4ROreFeatureConfig> {
    public FeatureVanillaTypeOre() {
        super(GT4ROreFeatureConfig.CODEC, WorldGenVanillaTypeVeins.class);
    }

    @Override
    public String getId() {
        return "feature_vanilla_type_ore";
    }

    @Override
    public boolean enabled() {
        return AntimatterConfig.WORLD.ORE_VEINS && getRegistry().size() > 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void build(BiomeGenerationSettingsBuilder event) {
        event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.withConfiguration(new GT4ROreFeatureConfig("iron", 1, "iron", "nickel", 5)));
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;
        //Feature.ORE.generate()
        WorldGenVanillaTypeVeins.generate(world, chunkX, chunkZ);
        return true;
    }
}
