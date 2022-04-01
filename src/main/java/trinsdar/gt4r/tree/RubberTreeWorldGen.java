package trinsdar.gt4r.tree;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import trinsdar.gt4r.data.GT4RData;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static muramasa.antimatter.Ref.RNG;

public class RubberTreeWorldGen  extends WorldGenBase<RubberTreeWorldGen> {
    public static Predicate<Biome.BiomeCategory> getValidBiomesStatic() {
        final Set<Biome.BiomeCategory> blacklist = new ObjectOpenHashSet<>();
        blacklist.add(Biome.BiomeCategory.DESERT);
        blacklist.add(Biome.BiomeCategory.TAIGA);
        blacklist.add(Biome.BiomeCategory.EXTREME_HILLS);
        blacklist.add(Biome.BiomeCategory.ICY);
        blacklist.add(Biome.BiomeCategory.THEEND);
        blacklist.add(Biome.BiomeCategory.OCEAN);
        blacklist.add(Biome.BiomeCategory.NETHER);
        blacklist.add(Biome.BiomeCategory.PLAINS);
        return b -> !blacklist.contains(b);
    }

    final static TreeConfiguration RUBBER_TREE_CONFIG_SWAMP =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new SimpleStateProvider(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new StraightTrunkPlacer(4, 3, 0), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().maxWaterDepth(1).decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new SimpleStateProvider(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new StraightTrunkPlacer(6, 3, 0), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_NORMAL =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new SimpleStateProvider(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new StraightTrunkPlacer(4, 3, 0), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();



    public RubberTreeWorldGen(){
        super("rubber_tree", RubberTreeWorldGen.class, Level.OVERWORLD);
    }


    public static void onEvent(BiomeLoadingEvent builder){
        Biome.BiomeCategory biomeCategory = builder.getCategory();
        if (!getValidBiomesStatic().test(biomeCategory)) return;
        float p = 0.15F;
        if (builder.getClimate().temperature > 0.8f) {
            p = 0.04F;
            if (builder.getClimate().precipitation == Biome.Precipitation.RAIN)
                p += 0.04F;
        }
        float finalp = p;
        builder.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RubberTree.TREE_FEATURE.configured(getTreeConfig(biomeCategory))
                .decorated(new RubberTreePlacement().configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, finalp, 1))));
        if (RNG.nextInt(4) == 0){
            builder.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RubberTree.TREE_FEATURE.configured(getTreeConfig(biomeCategory))
                    .decorated(new RubberTreePlacement().configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, finalp, 1))));
            if (RNG.nextInt(6) == 0){
                builder.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RubberTree.TREE_FEATURE.configured(getTreeConfig(biomeCategory))
                        .decorated(new RubberTreePlacement().configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, finalp, 1))));
            }
        }
    }

    static TreeConfiguration getTreeConfig(Biome.BiomeCategory biome){
        TreeConfiguration config = RUBBER_TREE_CONFIG_NORMAL;
        if (biome == Biome.BiomeCategory.SWAMP)
            config = RUBBER_TREE_CONFIG_SWAMP;
        else if (biome == Biome.BiomeCategory.JUNGLE)
            config = RUBBER_TREE_CONFIG_JUNGLE;
        return config;
    }
    public static class RubberTreePlacement extends FeatureDecorator<FrequencyWithExtraChanceDecoratorConfiguration> {
        public RubberTreePlacement() {
            super(FrequencyWithExtraChanceDecoratorConfiguration.CODEC);
        }
        @Override
        public Stream<BlockPos> getPositions(DecorationContext helper, Random random, FrequencyWithExtraChanceDecoratorConfiguration config, BlockPos pos) {
            int i = config.count;
            if (random.nextDouble() < config.extraChance) {
                i = random.nextInt(config.extraCount) + config.extraCount;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, helper.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }


    }
}
