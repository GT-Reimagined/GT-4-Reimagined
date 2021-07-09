package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

import java.util.Random;

import static trinsdar.gt4r.worldgen.GT4RFeatures.*;
import static trinsdar.gt4r.worldgen.GT4RFeatures.EMERALD_VANILLA;
import static trinsdar.gt4r.worldgen.GT4RFeatures.LAPIS;

public class RetrogenFeature extends AntimatterFeature<OreFeatureConfig> {

    public RetrogenFeature() {
        super(OreFeatureConfig.CODEC, OreFeatureConfig.class);
    }

    @Override
    public String getId() {
        return "retrogen_feature";
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public void init() {

    }

    @Override
    public void build(BiomeGenerationSettingsBuilder event) {
        /*if (AntimatterConfig.WORLD.ORE_VEINS){
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(SALT_OVERRIDE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(ROCK_SALT_OVERRIDE));
        }*/
    }

    public ConfiguredFeature<?, ?> getConfiguration(OreFeatureConfig config){
        return this.withConfiguration(config);
    }

    protected Heightmap.Type getHeightmapType() {
        //Use OCEAN_FLOOR instead of OCEAN_FLOOR_WG as the chunks are already generated
        return Heightmap.Type.OCEAN_FLOOR;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        if ((config == SALT_OVERRIDE && BiomeDictionary.hasType(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, reader.getBiome(pos).getRegistryName()), BiomeDictionary.Type.OCEAN)) || (config == ROCK_SALT_OVERRIDE && BiomeDictionary.hasType(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, reader.getBiome(pos).getRegistryName()), BiomeDictionary.Type.SANDY))){
            int i = 0;
            ChunkPos chunkPos = new ChunkPos(pos);
            for (int x = 0; x < 16; x++){
                for (int z = 0; z < 16; z++){
                    for (int y = 0; y < reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z) + 1; y++){
                        int x1 = chunkPos.getXStart() + x;
                        int z1 = chunkPos.getZStart() + z;
                        BlockPos newPos = new BlockPos(x1, y, z1);
                        BlockState existing = reader.getBlockState(newPos);
                        if (config.target.test(existing, rand)){
                            if (WorldGenHelper.setState(reader, newPos, config.state)) ++i;
                        }
                    }
                }
            }
            return i > 0;
        }
        return false;
    }
}
