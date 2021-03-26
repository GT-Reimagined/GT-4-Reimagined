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
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

import java.util.Random;

public class FeatureVanillaTypeOre extends AntimatterFeature<NoFeatureConfig> {
    public FeatureVanillaTypeOre() {
        super(NoFeatureConfig.field_236558_a_, WorldGenVanillaTypeVeins.class);
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
        event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG));
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;
        WorldGenVanillaTypeVeins.generate(world, chunkX, chunkZ);
        return true;
    }
}
