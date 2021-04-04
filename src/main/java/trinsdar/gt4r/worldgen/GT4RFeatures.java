package trinsdar.gt4r.worldgen;

import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class GT4RFeatures {
    public static final AntimatterFeature<?> ORE = new FeatureVanillaTypeOre();

    //public static final GT4ROreFeatureConfig IRON = new GT4ROreFeatureConfig("iron", 1, "iron", "nickel", 5);
    public static final GT4ROreFeatureConfig COPPER = new GT4ROreFeatureConfig("copper", 10, "coppper", "gold", 3);

    //public static final ConfiguredFeature<?, ?> ORE_IRON = register("ore_iron_gt", ORE.withConfiguration(new GT4ROreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Features.States.IRON_ORE, 9)).range(64).square().func_242731_b(20));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static void init(){

    }
}
