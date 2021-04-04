package trinsdar.gt4r.worldgen;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import static muramasa.antimatter.Data.ORE;

public class WorldGenVanillaTypeVeins extends WorldGenBase<WorldGenVanillaTypeVeins> {
    private Material[] materials;
    private String primary, secondary;
    private final int minY;
    private final int maxY;
    private final int weight;
    private final int secondaryChance;
    private final int size;
    private BiomeDictionary.Type[] biomeTypes = new BiomeDictionary.Type[]{};
    private BiomeDictionary.Type[] invalidBiomeTypes = new BiomeDictionary.Type[]{};

    public WorldGenVanillaTypeVeins(String id, int minY, int maxY, int weight, int size, Material primary, Material secondary, int secondaryChance, RegistryKey<World>... dimensions) {
        super(id, WorldGenVanillaTypeVeins.class, dimensions);
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.secondaryChance = secondaryChance;
        this.size = size;
        if (secondary != null){
            materials = new Material[] {primary, secondary};
        } else {
            materials = new Material[] {primary};
        }
        if (primary != null) {
            this.primary = primary.getId();
        }
        if (secondary != null){
            this.secondary = secondary.getId();
        }
    }

    public WorldGenVanillaTypeVeins setValidBiomes(BiomeDictionary.Type... types){
        biomeTypes = types;
        return this;
    }

    public WorldGenVanillaTypeVeins setInvalidBiomes(BiomeDictionary.Type... types){
        invalidBiomeTypes = types;
        return this;
    }

    @Override
    public WorldGenVanillaTypeVeins build() {
        super.build();

        if (secondary != null){
            materials = new Material[] {Material.get(primary), Material.get(secondary)};
        } else {
            materials = new Material[] {Material.get(primary)};
        }

        if (materials[0] == null || !materials[0].has(ORE)) throw new IllegalArgumentException("WorldGenOreVanillaTypeVeins - " + getId() + ": " + primary + " material either doesn't exist or doesn't have the ORE tag");
        //if (materials[1] == null || !materials[1].has(ORE)) throw new IllegalArgumentException("WorldGenOreVanillaTypeVeins - " + getId() + ": " + secondary + " material either doesn't exist or doesn't have the ORE tag");

        return this;
    }

    public static void generate(ISeedReader world, int chunkX, int chunkZ){
        List<WorldGenVanillaTypeVeins> veins = AntimatterWorldGenerator.all(WorldGenVanillaTypeVeins.class, world.getWorld().getDimensionKey());
        if (veins == null || veins.size() == 0)
            return;

        veins.forEach(v -> {
            if (!v.getDims().contains(world.getWorld().getDimensionKey().getLocation())) return;

            //int baseCount = v.weight / 64;
            //int count = (int)Math.round(world.getRandom().nextGaussian() * Math.sqrt(baseCount) + baseCount);
            for (int i = 0; i < v.weight; i++){
                int heightdiff = v.maxY - v.minY + 1;
                Random rand = world.getRandom();
                int x = chunkX * 16 + world.getRandom().nextInt(16);
                int y = v.minY + world.getRandom().nextInt(heightdiff);
                int z = chunkZ * 16 + world.getRandom().nextInt(16);
                BlockPos position = new BlockPos(x, y, z);
                if (y > 80 && world.getWorld().getDimensionKey() == World.OVERWORLD){
                    --i;
                    continue;
                }
                List<BiomeDictionary.Type> types = Arrays.asList(v.biomeTypes);
                List<BiomeDictionary.Type> invalidTypes = Arrays.asList(v.invalidBiomeTypes);
                boolean hasType = types.isEmpty();
                for (BiomeDictionary.Type type : BiomeDictionary.getTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, world.getBiome(position).getRegistryName()))) {
                    if (types.contains(type)) {
                        hasType = true;
                    }
                    if (invalidTypes.contains(type)){
                        return;
                    }
                }
                if (!hasType){
                    return;
                }
                generate2(world, rand, position, v);
                /*float f = rand.nextFloat() * (float)Math.PI;
                double d0 = (float)(position.getX() + 8) + MathHelper.sin(f) * (float)v.size / 8.0F;
                double d1 = (float)(position.getX() + 8) - MathHelper.sin(f) * (float)v.size / 8.0F;
                double d2 = (float)(position.getZ() + 8) + MathHelper.cos(f) * (float)v.size / 8.0F;
                double d3 = (float)(position.getZ() + 8) - MathHelper.cos(f) * (float)v.size / 8.0F;
                double d4 = position.getY() + rand.nextInt(3) - 2;
                double d5 = position.getY() + rand.nextInt(3) - 2;
                for (int j = 0; j < v.size; j++){
                    float f1 = (float)i / (float)v.size;
                    double d6 = d0 + (d1 - d0) * (double)f1;
                    double d7 = d4 + (d5 - d4) * (double)f1;
                    double d8 = d2 + (d3 - d2) * (double)f1;
                    double d9 = rand.nextDouble() * (double)v.size / 16.0D;
                    double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
                    double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
                    int k = MathHelper.floor(d6 - d10 / 2.0D);
                    int l = MathHelper.floor(d7 - d11 / 2.0D);
                    int m = MathHelper.floor(d8 - d10 / 2.0D);
                    int i1 = MathHelper.floor(d6 + d10 / 2.0D);
                    int j1 = MathHelper.floor(d7 + d11 / 2.0D);
                    int k1 = MathHelper.floor(d8 + d10 / 2.0D);

                    for (int l1 = k; l1 <= i1; ++l1)
                    {
                        double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                        if (d12 * d12 < 1.0D)
                        {
                            for (int i2 = l; i2 <= j1; ++i2)
                            {
                                double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                                if (d12 * d12 + d13 * d13 < 1.0D)
                                {
                                    for (int j2 = m; j2 <= k1; ++j2)
                                    {
                                        double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                        if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                        {
                                            BlockPos set = new BlockPos(l1, i2, j2);
                                            Material mat = v.materials[0];
                                            if (v.secondary != null && v.secondaryChance > 0 && v.secondaryChance < 100){
                                                mat = rand.nextInt(100) < v.secondaryChance ? v.materials[1] : v.materials[0];
                                            }
                                            WorldGenHelper.setOre(world, set, world.getBlockState(set), mat, ORE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }*/
            }
        });

    }

    public static boolean generate2(ISeedReader reader, Random rand, BlockPos pos, WorldGenVanillaTypeVeins config) {
        float f = rand.nextFloat() * (float)Math.PI;
        float f1 = (float)config.size / 8.0F;
        int i = MathHelper.ceil(((float)config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
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

    protected static boolean generateVein(IWorld worldIn, Random random, WorldGenVanillaTypeVeins config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int j = config.size;
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
                                            Material mat = config.materials[0];
                                            if (config.secondary != null && config.secondaryChance > 0 && config.secondaryChance < 100){
                                                mat = random.nextInt(100) < config.secondaryChance ? config.materials[1] : config.materials[0];
                                            }
                                            if (WorldGenHelper.setOre(worldIn, blockpos$mutable, worldIn.getBlockState(blockpos$mutable), mat, ORE)) {
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
