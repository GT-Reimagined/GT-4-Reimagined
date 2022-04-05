package trinsdar.gt4r.worldgen;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.core.Registry;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static muramasa.antimatter.Data.Coal;
import static muramasa.antimatter.Data.ORE_STONE;
import static muramasa.antimatter.worldgen.WorldGenHelper.ORE_PREDICATE;
import static trinsdar.gt4r.worldgen.GT4RPlacedFeatures.*;

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
    public void build(BiomeLoadingEvent event) {
        if (AntimatterConfig.WORLD.ORE_VEINS){
            BiomeGenerationSettingsBuilder builder = event.getGeneration();
            FEATURE_MAP.forEach((k, v) -> {
                if (k.contains("emerald") || k.contains("coal") || k.contains("iron") || k.contains("copper") || k.contains("gold") || k.contains("diamond") || k.contains("lapis") || k.contains("redstone")){
                    if (AntimatterConfig.WORLD.VANILLA_ORE_GEN && event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND){
                        if (k.contains("emerald")){
                            if (event.getCategory() == Biome.BiomeCategory.EXTREME_HILLS){
                                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, v.feature());
                            }
                            return;
                        }
                        if (k.equals("gold_extra")){
                            if (event.getCategory() == Biome.BiomeCategory.MESA){
                                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, v.feature());
                            }
                            return;
                        }
                        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, v.feature());
                    }
                    return;
                }
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, v.feature());
            });
        }
    }

    public boolean place(WorldGenLevel worldgenlevel, Random random, BlockPos blockpos, GT4ROreFeatureConfig config) {

        float f = random.nextFloat() * (float)Math.PI;
        float f1 = (float)config.size / 8.0F;
        int i = Mth.ceil(((float)config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double minX = (double)blockpos.getX() + Math.sin(f) * (double)f1;
        double maxX = (double)blockpos.getX() - Math.sin(f) * (double)f1;
        double minZ = (double)blockpos.getZ() + Math.cos(f) * (double)f1;
        double maxZ = (double)blockpos.getZ() - Math.cos(f) * (double)f1;
        double minY = blockpos.getY() + random.nextInt(3) - 2;
        double maxY = blockpos.getY() + random.nextInt(3) - 2;
        int x = blockpos.getX() - Mth.ceil(f1) - i;
        int y = blockpos.getY() - 2 - i;
        int z = blockpos.getZ() - Mth.ceil(f1) - i;
        int width = 2 * (Mth.ceil(f1) + i);
        int height = 2 * (2 + i);

        for(int ix = x; ix <= x + width; ++ix) {
            for(int iz = z; iz <= z + width; ++iz) {
                if (config.getId().equals("salt")){
                    int y2 = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, ix, iz);
                    double minY0 = y + random.nextInt(3) - 2;
                    double maxY0 = y + random.nextInt(3) - 2;
                    return this.doPlace(worldgenlevel, random, config, minX, maxX, minZ, maxZ, minY0, maxY0, x, y2, z, width, height);
                }
                if (y <= worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, ix, iz)) {
                    return this.doPlace(worldgenlevel, random, config, minX, maxX, minZ, maxZ, minY, maxY, x, y, z, width, height);
                }
            }
        }

        return false;
    }

    public boolean place(FeaturePlaceContext<GT4ROreFeatureConfig> pContext) {
        Random random = pContext.random();
        BlockPos blockpos = pContext.origin();
        WorldGenLevel worldgenlevel = pContext.level();
        GT4ROreFeatureConfig config = pContext.config();
        if (!config.getDimensionLocations().contains(worldgenlevel.getLevel().dimension().location())) return false;
        List<BiomeDictionary.Type> types = config.getBiomeTypes();
        List<BiomeDictionary.Type> invalidTypes = config.getInvalidBiomeTypes();
        boolean hasType = types.isEmpty();
        for (BiomeDictionary.Type type : BiomeDictionary.getTypes(worldgenlevel.getBiome(blockpos).unwrapKey().get())) {
            if (types.contains(type)) {
                hasType = true;
            }
            if (invalidTypes.contains(type)){
                hasType = false;
                break;
            }
        }
        if (!hasType){
            return false;
        }
        return place(worldgenlevel, random, blockpos, config);
    }

    protected boolean doPlace(WorldGenLevel pLevel, Random pRandom, GT4ROreFeatureConfig config, double pMinX, double pMaxX, double pMinZ, double pMaxZ, double pMinY, double pMaxY, int pX, int pY, int pZ, int pWidth, int pHeight) {
        int i = 0;
        BitSet bitset = new BitSet(pWidth * pHeight * pWidth);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = config.size;
        double[] adouble = new double[j * 4];

        for(int k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            double d0 = Mth.lerp(f, pMinX, pMaxX);
            double d1 = Mth.lerp(f, pMinY, pMaxY);
            double d2 = Mth.lerp(f, pMinZ, pMaxZ);
            double d3 = pRandom.nextDouble() * (double)j / 16.0D;
            double d4 = ((double)(Mth.sin((float)Math.PI * f) + 1.0F) * d3 + 1.0D) / 2.0D;
            adouble[k * 4 + 0] = d0;
            adouble[k * 4 + 1] = d1;
            adouble[k * 4 + 2] = d2;
            adouble[k * 4 + 3] = d4;
        }

        for(int l3 = 0; l3 < j - 1; ++l3) {
            if (!(adouble[l3 * 4 + 3] <= 0.0D)) {
                for(int i4 = l3 + 1; i4 < j; ++i4) {
                    if (!(adouble[i4 * 4 + 3] <= 0.0D)) {
                        double d8 = adouble[l3 * 4 + 0] - adouble[i4 * 4 + 0];
                        double d10 = adouble[l3 * 4 + 1] - adouble[i4 * 4 + 1];
                        double d12 = adouble[l3 * 4 + 2] - adouble[i4 * 4 + 2];
                        double d14 = adouble[l3 * 4 + 3] - adouble[i4 * 4 + 3];
                        if (d14 * d14 > d8 * d8 + d10 * d10 + d12 * d12) {
                            if (d14 > 0.0D) {
                                adouble[i4 * 4 + 3] = -1.0D;
                            } else {
                                adouble[l3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        BulkSectionAccess bulksectionaccess = new BulkSectionAccess(pLevel);

        try {
            for(int j4 = 0; j4 < j; ++j4) {
                double d9 = adouble[j4 * 4 + 3];
                if (!(d9 < 0.0D)) {
                    double d11 = adouble[j4 * 4 + 0];
                    double d13 = adouble[j4 * 4 + 1];
                    double d15 = adouble[j4 * 4 + 2];
                    int k4 = Math.max(Mth.floor(d11 - d9), pX);
                    int l = Math.max(Mth.floor(d13 - d9), pY);
                    int i1 = Math.max(Mth.floor(d15 - d9), pZ);
                    int j1 = Math.max(Mth.floor(d11 + d9), k4);
                    int k1 = Math.max(Mth.floor(d13 + d9), l);
                    int l1 = Math.max(Mth.floor(d15 + d9), i1);

                    for(int i2 = k4; i2 <= j1; ++i2) {
                        double d5 = ((double)i2 + 0.5D - d11) / d9;
                        if (d5 * d5 < 1.0D) {
                            for(int j2 = l; j2 <= k1; ++j2) {
                                double d6 = ((double)j2 + 0.5D - d13) / d9;
                                if (d5 * d5 + d6 * d6 < 1.0D) {
                                    for(int k2 = i1; k2 <= l1; ++k2) {
                                        double d7 = ((double)k2 + 0.5D - d15) / d9;
                                        if (d5 * d5 + d6 * d6 + d7 * d7 < 1.0D && !pLevel.isOutsideBuildHeight(j2)) {
                                            int l2 = i2 - pX + (j2 - pY) * pWidth + (k2 - pZ) * pWidth * pHeight;
                                            if (!bitset.get(l2)) {
                                                bitset.set(l2);
                                                blockpos$mutableblockpos.set(i2, j2, k2);
                                                if (pLevel.ensureCanWrite(blockpos$mutableblockpos)) {
                                                    LevelChunkSection levelchunksection = bulksectionaccess.getSection(blockpos$mutableblockpos);
                                                    if (levelchunksection != null) {
                                                        int i3 = SectionPos.sectionRelative(i2);
                                                        int j3 = SectionPos.sectionRelative(j2);
                                                        int k3 = SectionPos.sectionRelative(k2);
                                                        BlockState blockstate = levelchunksection.getBlockState(i3, j3, k3);

                                                        Material mat = Material.get(config.getPrimary());
                                                        if (mat.has(ORE_STONE) && mat != Coal){
                                                            StoneType stone = WorldGenHelper.STONE_MAP.get(blockstate);
                                                            if (stone == null) continue;
                                                            if (WorldGenHelper.setState(pLevel, new BlockPos(i3, j3, k3), ORE_STONE.get().get(mat).asState())) {
                                                                ++i;
                                                                continue;
                                                            }
                                                        }
                                                        if (config.getSecondary() != null && !config.getSecondary().equals("null") && config.getSecondaryChance() > 0 && config.getSecondaryChance() < 1.0F){
                                                            mat = pRandom.nextFloat() < config.getSecondaryChance() ? Material.get(config.getSecondary()) : Material.get(config.getPrimary());
                                                        }
                                                        if (canPlaceOre(blockstate, bulksectionaccess::getBlockState, pRandom, config, mat, Data.ORE, blockpos$mutableblockpos) && WorldGenHelper.setOre(pLevel, new BlockPos(i3, j3, k3), blockstate, mat, Data.ORE)) {
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
                }
            }
        } catch (Throwable throwable1) {
            try {
                bulksectionaccess.close();
            } catch (Throwable throwable) {
                throwable1.addSuppressed(throwable);
            }

            throw throwable1;
        }

        bulksectionaccess.close();
        return i > 0;
    }

    public static BlockState getOre(BlockState existing, Material material,
                                 MaterialType<?> type) {

        StoneType stone = WorldGenHelper.STONE_MAP.get(existing);
        if (stone == null)
            return null;
        BlockState oreState = type == Data.ORE ? Data.ORE.get().get(material, stone).asState()
                : Data.ORE_SMALL.get().get(material, stone).asState();
        if (!ORE_PREDICATE.test(existing))
            return null;
        return oreState;
    }

    public static boolean canPlaceOre(BlockState pState, Function<BlockPos, BlockState> pAdjacentStateAccessor, Random pRandom, GT4ROreFeatureConfig pConfig, Material material, MaterialType<?> type, BlockPos.MutableBlockPos pMatablePos) {
        if (getOre(pState, material, type) == null) {
            return false;
        } else if (shouldSkipAirCheck(pRandom, pConfig.getDiscardOnExposureChance())) {
            return true;
        } else {
            return !isAdjacentToAir(pAdjacentStateAccessor, pMatablePos);
        }
    }

    protected static boolean shouldSkipAirCheck(Random pRandom, float pChance) {
        if (pChance <= 0.0F) {
            return true;
        } else if (pChance >= 1.0F) {
            return false;
        } else {
            return pRandom.nextFloat() >= pChance;
        }
    }
}
