package trinsdar.gt4r.tree;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.mixin.BiomeAccessor;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;
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
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(4, 3, 0), SimpleStateProvider.simple(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(6, 3, 0), SimpleStateProvider.simple(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_NORMAL =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(4, 3, 0), SimpleStateProvider.simple(GT4RData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RUBBER_TREE_CONFIGURED_NORMAL = register("rubber_tree_normal", new ConfiguredFeature<>(RubberTree.TREE_FEATURE, RUBBER_TREE_CONFIG_NORMAL));
    static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RUBBER_TREE_CONFIGURED_JUNGLE = register("rubber_tree_jungle", new ConfiguredFeature<>(RubberTree.TREE_FEATURE, RUBBER_TREE_CONFIG_JUNGLE));
    static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RUBBER_TREE_CONFIGURED_SWAMP = register("rubber_tree_swamp", new ConfiguredFeature<>(RubberTree.TREE_FEATURE, RUBBER_TREE_CONFIG_SWAMP));

    static final Holder<PlacedFeature> RUBBER_TREE_NORMAL = createPlacedFeature("rubber_tree_normal", RUBBER_TREE_CONFIGURED_NORMAL, new RubberTreePlacementModifier(), BiomeFilter.biome());
    static final Holder<PlacedFeature> RUBBER_TREE_JUNGLE = createPlacedFeature("rubber_tree_jungle", RUBBER_TREE_CONFIGURED_JUNGLE, new RubberTreePlacementModifier(), BiomeFilter.biome());
    static final Holder<PlacedFeature> RUBBER_TREE_SWAMP = createPlacedFeature("rubber_tree_swamp", RUBBER_TREE_CONFIGURED_SWAMP, SurfaceWaterDepthFilter.forMaxDepth(1), new RubberTreePlacementModifier(), BiomeFilter.biome());

    public static final PlacementModifierType<RubberTreePlacementModifier> RUBBER_TREE_PLACEMENT_MODIFIER  = () -> RubberTreePlacementModifier.CODEC;

    public static void init(){
        Registry.register(Registry.PLACEMENT_MODIFIERS, new ResourceLocation(Ref.ID, "rubber_tree_placement_modifier"), RUBBER_TREE_PLACEMENT_MODIFIER);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, ConfiguredFeature<FC, F> cf) {
        ResourceLocation realId = new ResourceLocation(Ref.ID, id);
        Preconditions.checkState(!BuiltinRegistries.CONFIGURED_FEATURE.keySet().contains(realId), "Duplicate ID: %s", id);
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, realId.toString(), cf);
    }

    public static <FC extends FeatureConfiguration> Holder<PlacedFeature> createPlacedFeature(String id, Holder<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
        ResourceLocation realID = new ResourceLocation(Ref.ID, id);
        if (BuiltinRegistries.PLACED_FEATURE.keySet().contains(realID))
            throw new IllegalStateException("Placed Feature ID: \"" + realID.toString() + "\" already exists in the Placed Features registry!");

        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, realID, new PlacedFeature(Holder.hackyErase(feature), List.of(placementModifiers)));
    }

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

        builder.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  getTreeConfig(biomeCategory));
        if (RNG.nextInt(4) == 0){
            builder.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  getTreeConfig(biomeCategory));
            if (RNG.nextInt(6) == 0){
                builder.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  getTreeConfig(biomeCategory));
            }
        }
    }

    static Holder<PlacedFeature> getTreeConfig(Biome.BiomeCategory biome){
        Holder<PlacedFeature> config = RUBBER_TREE_NORMAL;
        if (biome == Biome.BiomeCategory.SWAMP)
            config = RUBBER_TREE_SWAMP;
        else if (biome == Biome.BiomeCategory.JUNGLE)
            config = RUBBER_TREE_JUNGLE;
        return config;
    }

    static Holder<ConfiguredFeature<TreeConfiguration, ?>> getTreeConfigured(Biome.BiomeCategory biome){
        Holder<ConfiguredFeature<TreeConfiguration, ?>> config = RUBBER_TREE_CONFIGURED_NORMAL;
        if (biome == Biome.BiomeCategory.SWAMP)
            config = RUBBER_TREE_CONFIGURED_SWAMP;
        else if (biome == Biome.BiomeCategory.JUNGLE)
            config = RUBBER_TREE_CONFIGURED_JUNGLE;
        return config;
    }

    public static class RubberTreePlacementModifier extends PlacementModifier{
        public static final Codec<RubberTreePlacementModifier> CODEC = Codec.unit(RubberTreePlacementModifier::new);


        @Override
        public Stream<BlockPos> getPositions(PlacementContext context, Random random, BlockPos pos) {
            BiomeAccessor biome = ((BiomeAccessor)(Object)context.getLevel().getBiome(pos).value());
            Biome.BiomeCategory biomeCategory = Biome.getBiomeCategory(context.getLevel().getBiome(pos));
            if (!getValidBiomesStatic().test(biomeCategory)) return Stream.empty();
            float p = 0.15F;
            if (biome.getClimateSettings().temperature > 0.8f) {
                p = 0.04F;
                if (biome.getClimateSettings().precipitation == Biome.Precipitation.RAIN)
                    p += 0.04F;
            }
            float finalp = p;
            int i = 0;
            if (random.nextDouble() < finalp) {
                i = random.nextInt(1) + 1;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, context.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }

        @Override
        public PlacementModifierType<?> type() {
            return RUBBER_TREE_PLACEMENT_MODIFIER;
        }
    }
}
