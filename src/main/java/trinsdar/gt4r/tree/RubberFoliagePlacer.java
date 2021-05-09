package trinsdar.gt4r.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import javax.swing.tree.TreeNode;
import java.util.Random;
import java.util.Set;

public class RubberFoliagePlacer extends FoliagePlacer {
    public static final Codec<RubberFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242834_0_) -> {
        return func_242830_b(p_242834_0_).apply(p_242834_0_, RubberFoliagePlacer::new);
    });
    public static final FoliagePlacerType<RubberFoliagePlacer> RUBBER = new FoliagePlacerType<>(CODEC);
    public RubberFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> func_230371_a_() {
        return RUBBER;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader p_230372_1_, Random p_230372_2_, BaseTreeFeatureConfig p_230372_3_, int p_230372_4_, Foliage p_230372_5_, int p_230372_6_, int p_230372_7_, Set<BlockPos> p_230372_8_, int p_230372_9_, MutableBoundingBox p_230372_10_) {
        generate(p_230372_1_, p_230372_2_, p_230372_3_, p_230372_4_, p_230372_5_, p_230372_6_, p_230372_7_, p_230372_8_, p_230372_9_, p_230372_10_);
    }

    protected void generate(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig config, int trunkHeight, Foliage treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int offset, MutableBoundingBox box) {
        for(int i = offset; i >= offset - foliageHeight; --i) {
            //int j = Math.max(radius + treeNode.getFoliageRadius() - 1 - i / 2, 0);
            //this.generateSquare(world, random, config, treeNode.getCenter(), j, leaves, i, treeNode.isGiantTrunk(), box);
        }

    }

    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
