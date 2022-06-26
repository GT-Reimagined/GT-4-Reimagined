package trinsdar.gt4r.tree;

import net.minecraft.core.Holder;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.server.level.ServerLevel;
import trinsdar.gt4r.data.GT4RData;

import java.util.Random;

public class RubberTree extends AbstractTreeGrower {
    public static final RubberTreeFeature TREE_FEATURE = new RubberTreeFeature();
    public static final WeightedStateProvider TRUNK_BLOCKS = new WeightedStateProvider(list());

    public RubberTree() {
    }

    public static SimpleWeightedRandomList<BlockState> list(){
        SimpleWeightedRandomList.Builder<BlockState> builder = new SimpleWeightedRandomList.Builder<>();
        BlockStateProperties.HORIZONTAL_FACING.getPossibleValues().forEach(d -> {
            builder.add(GT4RData.RUBBER_LOG.defaultBlockState()
                    .setValue(BlockRubberLog.RESIN_STATE, ResinState.FILLED)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
            builder.add(GT4RData.RUBBER_LOG.defaultBlockState()
                    .setValue(BlockRubberLog.RESIN_STATE, ResinState.EMPTY)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
        });
        builder.add(GT4RData.RUBBER_LOG.defaultBlockState()
                .setValue(BlockRubberLog.RESIN_STATE, ResinState.NONE), 20);
        return builder.build();
    }

    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(Random rand, boolean flowers) {
        return RubberTreeWorldGen.RUBBER_TREE_CONFIGURED_NORMAL;
    }

    @Override
    public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        ConfiguredFeature<TreeConfiguration, ?> configuredFeature = RubberTreeWorldGen.getTreeConfigured(Biome.getBiomeCategory(world.getBiome(pos))).value();
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        //configuredFeature.config();
        if (!configuredFeature.place(world, chunkGenerator, random, pos)) {
            world.setBlock(pos, state, 4);
            return false;
        } else
            return true;
    }
}
