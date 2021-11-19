package trinsdar.gt4r.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;

import net.minecraft.world.gen.foliageplacer.FoliagePlacer.Foliage;

public class RubberFoliagePlacer extends FoliagePlacer {
    public static final Codec<RubberFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242834_0_) -> {
        return foliagePlacerParts(p_242834_0_).apply(p_242834_0_, RubberFoliagePlacer::new);
    });
    public static final FoliagePlacerType<RubberFoliagePlacer> RUBBER = new FoliagePlacerType<>(CODEC);
    protected RubberFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset);
    }

    public RubberFoliagePlacer() {
        super(FeatureSpread.fixed(2), FeatureSpread.fixed(-1));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return RUBBER;
    }

    @Override
    protected void createFoliage(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig config, int trunkHeight, Foliage treeNode, int foilageHeight, int radius, Set<BlockPos> leaves, int offset, MutableBoundingBox box) {
        generate(world, random, config, trunkHeight, treeNode, foilageHeight, radius, leaves, offset, box);
    }

    protected void generate(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig config, int trunkHeight, Foliage treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int offset, MutableBoundingBox box) {
        BlockPos center = treeNode.foliagePos();
        BlockPos.Mutable pos = center.mutable();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        double treeRadius = 2.5;
        for(int i = offset; i >= offset - foliageHeight; --i) {
            if (i == offset){
                this.placeLeavesRow(world, random, config, center, 1, leaves, i, treeNode.doubleTrunk(), box);
                continue;
            }
            pos.set(x, y + i, z);
            circle(pos.mutable(), treeRadius, position -> {
                if (TreeFeature.isAirOrLeaves(world, position)) {
                    world.setBlock(position, config.leavesProvider.getState(random, position), 19);
                    box.expand(new MutableBoundingBox(position, position));
                    leaves.add(position.immutable());
                }
            });
        }

        int spikeHeight = 2 + random.nextInt(3);
        for (int i = 0; i < spikeHeight; i++){
            BlockPos leaf = center.above(i);
            if (TreeFeature.isAirOrLeaves(world, leaf)) {
                world.setBlock(leaf, config.leavesProvider.getState(random, leaf), 19);
                box.expand(new MutableBoundingBox(leaf, leaf));
                leaves.add(leaf.immutable());
            }
        }

    }

    /**
     * Iterates over the positions contained with in a circle defined by origin and radius. The circle is two dimensional,
     * perpendicular to the Y axis.
     *
     * @param origin The center block of the circle; this function clobbers the variable, and it must be reset afterwards
     * @param radius The radius of the circle
     * @param consumer The target of the positions; it passes the same BlockPos.Mutable object each time
     */
    private static void circle(BlockPos.Mutable origin, double radius, Consumer<BlockPos.Mutable> consumer) {
        int x = origin.getX();
        int z = origin.getZ();

        double radiusSq = radius * radius;
        int radiusCeil = (int) Math.ceil(radius);

        for (int dz = -radiusCeil; dz <= radiusCeil; dz++) {
            int dzSq = dz * dz;

            for (int dx = -radiusCeil; dx <= radiusCeil; dx++) {
                int dxSq = dx * dx;

                if (dzSq + dxSq <= radiusSq) {
                    origin.set(x + dx, origin.getY(), z + dz);
                    consumer.accept(origin);
                }
            }
        }
    }

    @Override
    public int foliageHeight(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return Math.max(2, p_230374_2_ - (3 + p_230374_1_.nextInt(2)));
    }

    @Override
    protected boolean shouldSkipLocation(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
