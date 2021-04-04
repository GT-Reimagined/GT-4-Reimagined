package trinsdar.gt4r.worldgen;

import com.mojang.serialization.Codec;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import muramasa.antimatter.worldgen.object.WorldGenVeinLayer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

import static muramasa.antimatter.Data.ORE;
import static trinsdar.gt4r.worldgen.GT4RFeatures.*;

public class FeatureVanillaTypeOre extends AntimatterFeature<GT4ROreFeatureConfig> {
    public FeatureVanillaTypeOre() {
        super(GT4ROreFeatureConfig.CODEC, GT4ROreFeatureConfig.class);
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
        if (AntimatterConfig.WORLD.ORE_VEINS){
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(COPPER));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(TIN));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(URANITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(URANITE_DEAD));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(CASSITERITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(TETRAHEDRITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(GALENA));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(BAUXITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(RUBY));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(SAPPHIRE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(PLATINUM));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(IRIDIUM));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(EMERALD));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(PYRITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(SPHALERITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(CINNABAR));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(TUNGSTATE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(PLATINUM_END));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(OLIVINE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(SODALITE));
            event.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, this.getConfiguration(CHROMITE));
        }
    }

    public ConfiguredFeature<?, ?> getConfiguration(GT4ROreFeatureConfig config){
        return this.withConfiguration(config).withPlacement(Placement.RANGE.configure(getRange(config.getMinY(), config.getMaxY()))).square().func_242731_b(config.getWeight());
    }

    public TopSolidRangeConfig getRange(int min, int max){
        return new TopSolidRangeConfig(min, min, max);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;
        //Feature.ORE.generate()
        if (!config.getDimensionLocations().contains(world.getWorld().getDimensionKey().getLocation())) return false;
        List<BiomeDictionary.Type> types = config.getBiomeTypes();
        List<BiomeDictionary.Type> invalidTypes = config.getInvalidBiomeTypes();
        boolean hasType = types.isEmpty();
        for (BiomeDictionary.Type type : BiomeDictionary.getTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, world.getBiome(pos).getRegistryName()))) {
            if (types.contains(type)) {
                hasType = true;
            }
            if (invalidTypes.contains(type)){
                return false;
            }
        }
        if (!hasType){
            return false;
        }

        return generate2(world, rand, pos, config);
    }

    public boolean generate2(ISeedReader reader, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {
        float f = rand.nextFloat() * (float)Math.PI;
        float f1 = (float)config.getSize() / 8.0F;
        int i = MathHelper.ceil(((float)config.getSize() / 16.0F * 2.0F + 1.0F) / 2.0F);
        double x0 = (double)pos.getX() + Math.sin(f) * (double)f1;
        double x1 = (double)pos.getX() - Math.sin(f) * (double)f1;
        double z0 = (double)pos.getZ() + Math.cos(f) * (double)f1;
        double z1 = (double)pos.getZ() - Math.cos(f) * (double)f1;
        double y0 = pos.getY() + rand.nextInt(3) - 2;
        double y1 = pos.getY() + rand.nextInt(3) - 2;
        int x = pos.getX() - MathHelper.ceil(f1) - i;
        int y = pos.getY() - 2 - i;
        int z = pos.getZ() - MathHelper.ceil(f1) - i;
        int j1 = 2 * (MathHelper.ceil(f1) + i);
        int k1 = 2 * (2 + i);

        for(int l1 = x; l1 <= x + j1; ++l1) {
            for(int i2 = z; i2 <= z + j1; ++i2) {
                if (y <= reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, l1, i2)) {
                    return generateVein(reader, rand, config, x0, x1, z0, z1, y0, y1, x, y, z, j1, k1);
                }
            }
        }

        return false;
    }

    protected boolean generateVein(IWorld worldIn, Random random, GT4ROreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int j = config.getSize();
        double[] adouble = new double[j * 4];

        for(int k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            double d0 = MathHelper.lerp(f, p_207803_4_, p_207803_6_);
            double d2 = MathHelper.lerp(f, p_207803_12_, p_207803_14_);
            double d4 = MathHelper.lerp(f, p_207803_8_, p_207803_10_);
            double d6 = random.nextDouble() * (double)j / 16.0D;
            double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            adouble[k * 4] = d0;
            adouble[k * 4 + 1] = d2;
            adouble[k * 4 + 2] = d4;
            adouble[k * 4 + 3] = d7;
        }

        for(int i3 = 0; i3 < j - 1; ++i3) {
            if (!(adouble[i3 * 4 + 3] <= 0.0D)) {
                for(int k3 = i3 + 1; k3 < j; ++k3) {
                    if (!(adouble[k3 * 4 + 3] <= 0.0D)) {
                        double d12 = adouble[i3 * 4] - adouble[k3 * 4];
                        double d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1];
                        double d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2];
                        double d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                adouble[k3 * 4 + 3] = -1.0D;
                            } else {
                                adouble[i3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(int j3 = 0; j3 < j; ++j3) {
            double d11 = adouble[j3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double d1 = adouble[j3 * 4];
                double d3 = adouble[j3 * 4 + 1];
                double d5 = adouble[j3 * 4 + 2];
                int l = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
                int l3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
                int i1 = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
                int j1 = Math.max(MathHelper.floor(d1 + d11), l);
                int k1 = Math.max(MathHelper.floor(d3 + d11), l3);
                int l1 = Math.max(MathHelper.floor(d5 + d11), i1);

                for(int i2 = l; i2 <= j1; ++i2) {
                    double d8 = ((double)i2 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D) {
                        for(int j2 = l3; j2 <= k1; ++j2) {
                            double d9 = ((double)j2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for(int k2 = i1; k2 <= l1; ++k2) {
                                    double d10 = ((double)k2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int l2 = i2 - p_207803_16_ + (j2 - p_207803_17_) * p_207803_19_ + (k2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(l2)) {
                                            bitset.set(l2);
                                            blockpos$mutable.setPos(i2, j2, k2);
                                            Material mat = Material.get(config.getPrimary());
                                            if (config.getSecondary() != null && !config.getSecondary().equals("null") && config.getSecondaryChance()> 0 && config.getSecondaryChance() < 100){
                                                mat = random.nextInt(100) < config.getSecondaryChance() ? Material.get(config.getSecondary()) : Material.get(config.getPrimary());
                                            }
                                            if (WorldGenHelper.setOre(worldIn, blockpos$mutable, worldIn.getBlockState(blockpos$mutable), mat, Data.ORE)) {
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
