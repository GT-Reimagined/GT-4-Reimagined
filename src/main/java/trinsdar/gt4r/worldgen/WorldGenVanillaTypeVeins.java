package trinsdar.gt4r.worldgen;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import muramasa.antimatter.worldgen.object.WorldGenVeinLayer;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class WorldGenVanillaTypeVeins extends WorldGenBase<WorldGenVanillaTypeVeins> {
    private Material[] materials;
    private String primary, secondary;
    private int minY, maxY, weight, secondaryChance, size;

    public WorldGenVanillaTypeVeins(String id, int minY, int maxY, int weight, int size, Material primary, Material secondary, int secondaryChance, RegistryKey<World>... dimensions) {
        super(id, WorldGenVanillaTypeVeins.class, dimensions);
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.secondaryChance = secondaryChance;
        this.size = size;
        this.materials = new Material[] {primary, secondary};
        if (primary != null) {
            this.primary = primary.getId();
            this.secondary = secondary.getId();
        }
    }

    @Override
    public WorldGenVanillaTypeVeins build() {
        super.build();

        materials = new Material[] {Material.get(primary), Material.get(secondary)};
        if (materials[0] == null || !materials[0].has(Data.ORE)) throw new IllegalArgumentException("WorldGenOreVanillaTypeVeins - " + getId() + ": " + primary + " material either doesn't exist or doesn't have the ORE tag");
        if (materials[1] == null || !materials[1].has(Data.ORE)) throw new IllegalArgumentException("WorldGenOreVanillaTypeVeins - " + getId() + ": " + secondary + " material either doesn't exist or doesn't have the ORE tag");

        return this;
    }

    public static void generate(ISeedReader world, int chunkX, int chunkZ, int oreSeedX, int oreSeedZ){
        /*List<WorldGenVanillaTypeVeins> veins = AntimatterWorldGenerator.all(WorldGenVanillaTypeVeins.class, world.getWorld().getDimensionKey());
        if (veins == null || veins.size() == 0)
            return;
*/
        /*for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightdiff);
            int z = chunkZ * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }*/

    }
}
