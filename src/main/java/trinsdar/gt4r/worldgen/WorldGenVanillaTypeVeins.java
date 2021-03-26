package trinsdar.gt4r.worldgen;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.Arrays;
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

                /*if (v.biomeTypes.length != 0) {
                    List<BiomeDictionary.Type> types = Arrays.asList(v.biomeTypes);
                    boolean hasType = false;
                    for (BiomeDictionary.Type type : BiomeDictionary.getTypes(world.getBiome(position))) {
                        if (types.contains(type)) {
                            hasType = true;
                            break;
                        }
                    }
                    if (!hasType){
                        return;
                    }
                }*/
                float f = rand.nextFloat() * (float)Math.PI;
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
                                            WorldGenHelper.setOre(world, set, world.getBlockState(set), v.materials[0], ORE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

    }
}
