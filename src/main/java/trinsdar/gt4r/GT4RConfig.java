package trinsdar.gt4r;

import muramasa.antimatter.Ref;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GT4RConfig {

    public static final Data DATA = new Data();
    public static final Gameplay GAMEPLAY = new Gameplay();
    public static final World WORLD = new World();
    //public static final ModCompatibility MOD_COMPAT = new ModCompatibility();

    public static final CommonConfig COMMON_CONFIG;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {

        final Pair<CommonConfig, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_CONFIG = COMMON_PAIR.getLeft();
        COMMON_SPEC = COMMON_PAIR.getRight();

    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent e) {
        if (e.getConfig().getSpec() == COMMON_SPEC) bakeCommonConfig();

    }

    public static class Data {

        /** @see CommonConfig **/

        public boolean ALL_MATERIAL_ITEMS, ITEM_REPLACEMENTS;

    }

    public static class Gameplay {

        /** @see CommonConfig **/

        public boolean LOSSY_PART_CRAFTING, REMOVE_VANILLA_CHARCOAL_RECIPE, HARDER_WOOD;

    }

    public static class World {

        /** @see CommonConfig **/

        public boolean COPPER_ORE_GEN, TIN_ORE_GEN, URANITE_ORE_GEN, PYRITE_ORE_GEN, CINNABAR_ORE_GEN, SPHALERITE_ORE_GEN, GALENA_ORE_GEN, CASSITERITE_ORE_GEN, TETRAHEDRITE_ORE_GEN, BAUXITE_ORE_GEN, RUBY_ORE_GEN, SAPPHIRE_ORE_GEN, IRIDIUM_ORE_GEN, TUNGSTATE_ORE_GEN, SHELDONITE_ORE_GEN, OLIVINE_ORE_GEN, SODALITE_ORE_GEN, CHROMITE_ORE_GEN, EXTRA_EMERALD_ORE_GEN, REPLACEMENT_VANILLA_ORE_GEN;

        public int COPPER_ORE_WEIGHT, TIN_ORE_WEIGHT, URANITE_ORE_WEIGHT, URANITE_DEAD_ORE_WEIGHT,  PYRITE_ORE_WEIGHT, CINNABAR_ORE_WEIGHT, SPHALERITE_ORE_WEIGHT, GALENA_ORE_WEIGHT, CASSITERITE_ORE_WEIGHT, TETRAHEDRITE_ORE_WEIGHT, BAUXITE_ORE_WEIGHT, RUBY_ORE_WEIGHT, SAPPHIRE_ORE_WEIGHT, IRIDIUM_ORE_WEIGHT, SHELDONITE_ORE_WEIGHT, TUNGSTATE_ORE_WEIGHT, SHELDONITE_END_ORE_WEIGHT, OLIVINE_ORE_WEIGHT, SODALITE_ORE_WEIGHT, CHROMITE_ORE_WEIGHT, EXTRA_EMERALD_ORE_WEIGHT;

        public int COPPER_ORE_SIZE, TIN_ORE_SIZE, URANITE_ORE_SIZE,  PYRITE_ORE_SIZE, CINNABAR_ORE_SIZE, SPHALERITE_ORE_SIZE, GALENA_ORE_SIZE, CASSITERITE_ORE_SIZE, TETRAHEDRITE_ORE_SIZE, BAUXITE_ORE_SIZE, RUBY_ORE_SIZE, SAPPHIRE_ORE_SIZE, IRIDIUM_ORE_SIZE, TUNGSTATE_ORE_SIZE, SHELDONITE_ORE_SIZE, OLIVINE_ORE_SIZE, SODALITE_ORE_SIZE, CHROMITE_ORE_SIZE, EXTRA_EMERALD_ORE_SIZE;

    }

    public static class CommonConfig {

        public ForgeConfigSpec.BooleanValue LOSSY_PART_CRAFTING, REMOVE_VANILLA_CHARCOAL_RECIPE, HARDER_WOOD;

        public ForgeConfigSpec.IntValue COPPER_ORE_WEIGHT, TIN_ORE_WEIGHT, URANITE_ORE_WEIGHT, URANITE_DEAD_ORE_WEIGHT,  PYRITE_ORE_WEIGHT, CINNABAR_ORE_WEIGHT, SPHALERITE_ORE_WEIGHT, GALENA_ORE_WEIGHT, CASSITERITE_ORE_WEIGHT, TETRAHEDRITE_ORE_WEIGHT, BAUXITE_ORE_WEIGHT, RUBY_ORE_WEIGHT, SAPPHIRE_ORE_WEIGHT, IRIDIUM_ORE_WEIGHT, SHELDONITE_ORE_WEIGHT, TUNGSTATE_ORE_WEIGHT, SHELDONITE_END_ORE_WEIGHT, OLIVINE_ORE_WEIGHT, SODALITE_ORE_WEIGHT, CHROMITE_ORE_WEIGHT, EXTRA_EMERALD_ORE_WEIGHT;

        public ForgeConfigSpec.IntValue COPPER_ORE_SIZE, TIN_ORE_SIZE, URANITE_ORE_SIZE,  PYRITE_ORE_SIZE, CINNABAR_ORE_SIZE, SPHALERITE_ORE_SIZE, GALENA_ORE_SIZE, CASSITERITE_ORE_SIZE, TETRAHEDRITE_ORE_SIZE, BAUXITE_ORE_SIZE, RUBY_ORE_SIZE, SAPPHIRE_ORE_SIZE, IRIDIUM_ORE_SIZE, TUNGSTATE_ORE_SIZE, SHELDONITE_ORE_SIZE, OLIVINE_ORE_SIZE, SODALITE_ORE_SIZE, CHROMITE_ORE_SIZE, EXTRA_EMERALD_ORE_SIZE;

        public ForgeConfigSpec.BooleanValue COPPER_ORE_GEN, TIN_ORE_GEN, URANITE_ORE_GEN, PYRITE_ORE_GEN, CINNABAR_ORE_GEN, SPHALERITE_ORE_GEN, GALENA_ORE_GEN, CASSITERITE_ORE_GEN, TETRAHEDRITE_ORE_GEN, BAUXITE_ORE_GEN, RUBY_ORE_GEN, SAPPHIRE_ORE_GEN, IRIDIUM_ORE_GEN, TUNGSTATE_ORE_GEN, SHELDONITE_ORE_GEN, OLIVINE_ORE_GEN, SODALITE_ORE_GEN, CHROMITE_ORE_GEN, EXTRA_EMERALD_ORE_GEN, REPLACEMENT_VANILLA_ORE_GEN;

        public CommonConfig(ForgeConfigSpec.Builder builder) {

            builder.push("World");

            REPLACEMENT_VANILLA_ORE_GEN = builder.comment("Enables my own version of vanilla ore gen (Iron, Coal, Diamond, ect.) if Antimatter.World.DISABLE_ORE_GEN is true - Default: true")
                    .translation(Ref.ID + ".config.replacement_vanilla_ore_gen")
                    .worldRestart()
                    .define("ENABLE_REPLACEMENT_VANILLA_ORE_GEN", true);

            builder.push("Ore Vein Flags");

            COPPER_ORE_GEN = builder.comment("Generate Copper ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.copper_ore_gen")
                    .worldRestart()
                    .define("ENABLE_COPPER_ORE_GEN", true);

            TIN_ORE_GEN = builder.comment("Generate Tin ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.tin_ore_gen")
                    .worldRestart()
                    .define("ENABLE_TIN_ORE_GEN", true);

            URANITE_ORE_GEN = builder.comment("Generate Uranite ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.uranite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_URANITE_ORE_GEN", true);

            GALENA_ORE_GEN = builder.comment("Generate Galena ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.galena_ore_gen")
                    .worldRestart()
                    .define("ENABLE_GALENA_ORE_GEN", true);

            CASSITERITE_ORE_GEN = builder.comment("Generate Cassiterite ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.cassiterite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_CASSITERITE_ORE_GEN", true);

            TETRAHEDRITE_ORE_GEN = builder.comment("Generate Tetrahedrite ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.tetrahedrite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_TETRAHEDRITE_ORE_GEN", true);

            BAUXITE_ORE_GEN = builder.comment("Generate Bauxite ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.bauxite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_BAUXITE_ORE_GEN", true);

            RUBY_ORE_GEN = builder.comment("Generate Ruby ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.ruby_ore_gen")
                    .worldRestart()
                    .define("ENABLE_RUBY_ORE_GEN", true);

            SAPPHIRE_ORE_GEN = builder.comment("Generate Sapphire ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.sapphire_ore_gen")
                    .worldRestart()
                    .define("ENABLE_SAPPHIRE_ORE_GEN", true);

            IRIDIUM_ORE_GEN = builder.comment("Generate Iridium ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.iridium_ore_gen")
                    .worldRestart()
                    .define("ENABLE_IRIDIUM_ORE_GEN", true);

            EXTRA_EMERALD_ORE_GEN = builder.comment("Generate Extra Emerald ore in the Overworld - Default: true")
                    .translation(Ref.ID + ".config.extra_emerald_ore_gen")
                    .worldRestart()
                    .define("ENABLE_EXTRA_EMERALD_ORE_GEN", true);

            PYRITE_ORE_GEN = builder.comment("Generate Pyrite ore in the Nether - Default: true")
                    .translation(Ref.ID + ".config.pyrite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_PYRITE_ORE_GEN", true);

            CINNABAR_ORE_GEN = builder.comment("Generate Cinnabar ore in the Nether - Default: true")
                    .translation(Ref.ID + ".config.cinnabar_ore_gen")
                    .worldRestart()
                    .define("ENABLE_CINNABAR_ORE_GEN", true);

            SPHALERITE_ORE_GEN = builder.comment("Generate Sphalerite ore in the Nether - Default: true")
                    .translation(Ref.ID + ".config.sphalerite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_SPHALERITE_ORE_GEN", true);

            TUNGSTATE_ORE_GEN = builder.comment("Generate Tungstate ore in the End - Default: true")
                    .translation(Ref.ID + ".config.tungstate_ore_gen")
                    .worldRestart()
                    .define("ENABLE_TUNGSTATE_ORE_GEN", true);

            SODALITE_ORE_GEN = builder.comment("Generate Sodalite ore in the End - Default: true")
                    .translation(Ref.ID + ".config.sodalite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_SODALITE_ORE_GEN", true);

            SHELDONITE_ORE_GEN = builder.comment("Generate Sheldonite ore in the End & Overworld - Default: true")
                    .translation(Ref.ID + ".config.sheldonite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_SHELDONITE_ORE_GEN", true);

            OLIVINE_ORE_GEN = builder.comment("Generate Olivine ore in the End - Default: true")
                    .translation(Ref.ID + ".config.olivine_ore_gen")
                    .worldRestart()
                    .define("ENABLE_OLIVINE_ORE_GEN", true);

            CHROMITE_ORE_GEN = builder.comment("Generate Chromite ore in the End - Default: true")
                    .translation(Ref.ID + ".config.chromite_ore_gen")
                    .worldRestart()
                    .define("ENABLE_CHROMITE_ORE_GEN", true);

            builder.pop();

            builder.push("Ore Vein Size");

            COPPER_ORE_SIZE = builder.comment("Max size of Copper veins - Default: 10")
                    .translation(Ref.ID + ".config.copper_ore_size")
                    .worldRestart()
                    .defineInRange("COPPER_ORE_SIZE", 10, 1, 32);

            TIN_ORE_SIZE = builder.comment("Max size of Tin veins - Default: 8")
                    .translation(Ref.ID + ".config.tin_ore_size")
                    .worldRestart()
                    .defineInRange("TIN_ORE_SIZE", 8, 1, 32);

            URANITE_ORE_SIZE = builder.comment("Max size of Uranite veins - Default: 4")
                    .translation(Ref.ID + ".config.uranite_ore_size")
                    .worldRestart()
                    .defineInRange("URANITE_ORE_SIZE", 4, 1, 32);

            GALENA_ORE_SIZE = builder.comment("Max size of Galena veins - Default: 10")
                    .translation(Ref.ID + ".config.galena_ore_size")
                    .worldRestart()
                    .defineInRange("GALENA_ORE_SIZE", 10, 1, 32);

            CASSITERITE_ORE_SIZE = builder.comment("Max size of Cassiterite veins - Default: 32")
                    .translation(Ref.ID + ".config.cassiterite_ore_size")
                    .worldRestart()
                    .defineInRange("CASSITERITE_ORE_SIZE", 32, 1, 64);

            TETRAHEDRITE_ORE_SIZE = builder.comment("Max size of Tetrahedrite veins - Default: 6")
                    .translation(Ref.ID + ".config.tetrahedrite_ore_size")
                    .worldRestart()
                    .defineInRange("TETRAHEDRITE_ORE_SIZE", 6, 1, 32);

            BAUXITE_ORE_SIZE = builder.comment("Max size of Bauxite veins - Default: 16")
                    .translation(Ref.ID + ".config.bauxite_ore_size")
                    .worldRestart()
                    .defineInRange("BAUXITE_ORE_SIZE", 16, 1, 32);

            RUBY_ORE_SIZE = builder.comment("Max size of Ruby veins - Default: 6")
                    .translation(Ref.ID + ".config.ruby_ore_size")
                    .worldRestart()
                    .defineInRange("RUBY_ORE_SIZE", 6, 1, 16);

            SAPPHIRE_ORE_SIZE = builder.comment("Max size of Sapphire veins - Default: 6")
                    .translation(Ref.ID + ".config.sapphire_ore_size")
                    .worldRestart()
                    .defineInRange("SAPPHIRE_ORE_SIZE", 6, 1, 16);

            IRIDIUM_ORE_SIZE = builder.comment("Max size of Iridium veins - Default: 2")
                    .translation(Ref.ID + ".config.iridium_ore_size")
                    .worldRestart()
                    .defineInRange("IRIDIUM_ORE_SIZE", 2, 1, 16);

            EXTRA_EMERALD_ORE_SIZE = builder.comment("Max size of Extra Emerald veins - Default: 6")
                    .translation(Ref.ID + ".config.extra_emerald_ore_size")
                    .worldRestart()
                    .defineInRange("EXTRA_EMERALD_ORE_SIZE", 6, 1, 16);

            PYRITE_ORE_SIZE = builder.comment("Max size of Pyrite veins - Default: 16")
                    .translation(Ref.ID + ".config.pyrite_ore_size")
                    .worldRestart()
                    .defineInRange("PYRITE_ORE_SIZE", 16, 1, 32);

            CINNABAR_ORE_SIZE = builder.comment("Max size of Cinnabar veins - Default: 16")
                    .translation(Ref.ID + ".config.cinnabar_ore_size")
                    .worldRestart()
                    .defineInRange("CINNABAR_ORE_SIZE", 16, 1, 32);

            SPHALERITE_ORE_SIZE = builder.comment("Max size of Sphalerite veins - Default: 16")
                    .translation(Ref.ID + ".config.sphalerite_ore_size")
                    .worldRestart()
                    .defineInRange("SPHALERITE_ORE_SIZE", 16, 1, 32);

            TUNGSTATE_ORE_SIZE = builder.comment("Max size of Tungstate veins - Default: 16")
                    .translation(Ref.ID + ".config.tungstate_ore_size")
                    .worldRestart()
                    .defineInRange("TUNGSTATE_ORE_SIZE", 16, 1, 32);

            SODALITE_ORE_SIZE = builder.comment("Max size of Sodalite veins - Default: 16")
                    .translation(Ref.ID + ".config.sodalite_ore_size")
                    .worldRestart()
                    .defineInRange("SODALITE_ORE_SIZE", 16,1,32);

            SHELDONITE_ORE_SIZE = builder.comment("Max size of Sheldonite veins - Default: 6")
                    .translation(Ref.ID + ".config.sheldonite_ore_size")
                    .worldRestart()
                    .defineInRange("SHELDONITE_ORE_SIZE", 6, 1, 16);

            OLIVINE_ORE_SIZE = builder.comment("Max size of Olivine veins - Default: 8")
                    .translation(Ref.ID + ".config.olivine_ore_size")
                    .worldRestart()
                    .defineInRange("OLIVINE_ORE_SIZE", 8, 1, 32);

            CHROMITE_ORE_SIZE = builder.comment("Max size of Chromite veins - Default: 6")
                    .translation(Ref.ID + ".config.chromite_ore_size")
                    .worldRestart()
                    .defineInRange("CHROMITE_ORE_SIZE", 6, 1, 16);

            builder.pop();

            builder.push("Ore Vein Weight");

            COPPER_ORE_WEIGHT = builder.comment("Total attempts to spawn Copper veins in a chunk - Default: 15")
                    .translation(Ref.ID + ".config.copper_ore_weight")
                    .worldRestart()
                    .defineInRange("COPPER_ORE_WEIGHT", 15, 1, 32);

            TIN_ORE_WEIGHT = builder.comment("Total attempts to spawn Tin veins in a chunk - Default: 25")
                    .translation(Ref.ID + ".config.tin_ore_weight")
                    .worldRestart()
                    .defineInRange("TIN_ORE_WEIGHT", 25, 1, 32);

            URANITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Uranite veins in a chunk - Default: 8")
                    .translation(Ref.ID + ".config.uranite_ore_weight")
                    .worldRestart()
                    .defineInRange("URANITE_ORE_WEIGHT", 8, 1, 32);

            URANITE_DEAD_ORE_WEIGHT = builder.comment("Total attempts to spawn Dead biome Uranite veins in a chunk - Default: 20")
                    .translation(Ref.ID + ".config.uranite_ore_weight")
                    .worldRestart()
                    .defineInRange("URANITE_DEAD_ORE_WEIGHT", 20, 1, 32);

            GALENA_ORE_WEIGHT = builder.comment("Total attempts to spawn Galena veins in a chunk - Default: 12")
                    .translation(Ref.ID + ".config.galena_ore_weight")
                    .worldRestart()
                    .defineInRange("GALENA_ORE_WEIGHT", 12, 1, 32);

            CASSITERITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Cassiterite veins in a chunk - Default: 2")
                    .translation(Ref.ID + ".config.cassiterite_ore_weight")
                    .worldRestart()
                    .defineInRange("CASSITERITE_ORE_WEIGHT", 2, 1, 32);

            TETRAHEDRITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Tetrahedrite veins in a chunk - Default: 7")
                    .translation(Ref.ID + ".config.tetrahedrite_ore_weight")
                    .worldRestart()
                    .defineInRange("TETRAHEDRITE_ORE_WEIGHT", 7, 1, 32);

            BAUXITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Bauxite veins in a chunk - Default: 8")
                    .translation(Ref.ID + ".config.bauxite_ore_weight")
                    .worldRestart()
                    .defineInRange("BAUXITE_ORE_WEIGHT", 8, 1, 32);

            RUBY_ORE_WEIGHT = builder.comment("Total attempts to spawn Ruby veins in a chunk - Default: 3")
                    .translation(Ref.ID + ".config.ruby_ore_weight")
                    .worldRestart()
                    .defineInRange("RUBY_ORE_WEIGHT", 3, 1, 32);

            SAPPHIRE_ORE_WEIGHT = builder.comment("Total attempts to spawn Sapphire veins in a chunk - Default: 3")
                    .translation(Ref.ID + ".config.sapphire_ore_weight")
                    .worldRestart()
                    .defineInRange("SAPPHIRE_ORE_WEIGHT", 3, 1, 32);

            IRIDIUM_ORE_WEIGHT = builder.comment("Total attempts to spawn Iridium veins in a chunk - Default: 1")
                    .translation(Ref.ID + ".config.iridium_ore_weight")
                    .worldRestart()
                    .defineInRange("IRIDIUM_ORE_WEIGHT", 1, 1, 32);

            EXTRA_EMERALD_ORE_WEIGHT = builder.comment("Total attempts to spawn Extra Emerald veins in a chunk - Default: 4")
                    .translation(Ref.ID + ".config.extra_emerald_ore_weight")
                    .worldRestart()
                    .defineInRange("EXTRA_EMERALD_ORE_WEIGHT", 4, 1, 32);

            SHELDONITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Sheldonite veins in a chunk in the Overworld - Default: 3")
                    .translation(Ref.ID + ".config.sheldonite_ore_weight")
                    .worldRestart()
                    .defineInRange("SHELDONITE_ORE_WEIGHT", 3, 1, 32);

            PYRITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Pyrite veins in a chunk - Default: 8")
                    .translation(Ref.ID + ".config.pyrite_ore_weight")
                    .worldRestart()
                    .defineInRange("PYRITE_ORE_WEIGHT", 8, 1, 32);

            CINNABAR_ORE_WEIGHT = builder.comment("Total attempts to spawn Cinnabar veins in a chunk - Default: 7")
                    .translation(Ref.ID + ".config.cinnabar_ore_weight")
                    .worldRestart()
                    .defineInRange("CINNABAR_ORE_WEIGHT", 7, 1, 32);

            SPHALERITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Sphalerite veins in a chunk - Default: 8")
                    .translation(Ref.ID + ".config.sphalerite_ore_weight")
                    .worldRestart()
                    .defineInRange("SPHALERITE_ORE_WEIGHT", 8, 1, 32);

            TUNGSTATE_ORE_WEIGHT = builder.comment("Total attempts to spawn Tungstate veins in a chunk - Default: 2")
                    .translation(Ref.ID + ".config.tungstate_ore_weight")
                    .worldRestart()
                    .defineInRange("TUNGSTATE_ORE_WEIGHT", 2, 1, 32);

            SODALITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Sodalite veins in a chunk - Default: 6")
                    .translation(Ref.ID + ".config.sodalite_ore_weight")
                    .worldRestart()
                    .defineInRange("SODALITE_ORE_WEIGHT", 6,1,32);

            SHELDONITE_END_ORE_WEIGHT = builder.comment("Total attempts to spawn Sheldonite veins in a chunk in the End - Default: 2")
                    .translation(Ref.ID + ".config.sheldonite_end_ore_weight")
                    .worldRestart()
                    .defineInRange("SHELDONITE_END_ORE_WEIGHT", 2, 1, 32);

            OLIVINE_ORE_WEIGHT = builder.comment("Total attempts to spawn Olivine veins in a chunk - Default: 5")
                    .translation(Ref.ID + ".config.olivine_ore_weight")
                    .worldRestart()
                    .defineInRange("OLIVINE_ORE_WEIGHT", 5, 1, 32);

            CHROMITE_ORE_WEIGHT = builder.comment("Total attempts to spawn Chromite veins in a chunk - Default: 4")
                    .translation(Ref.ID + ".config.chromite_ore_weight")
                    .worldRestart()
                    .defineInRange("CHROMITE_ORE_WEIGHT", 4, 1, 32);

            builder.pop(2);

            builder.push("Gameplay");

            LOSSY_PART_CRAFTING = builder.comment("Whether or not plates cost more in the forge hammer and in the crafting table and other parts like rods give less in the crafting table - Default : false")
                    .translation(Ref.ID + ".config.lossy_part_crafting")
                    .define("LOSSY_PART_CRAFTING", false);

            builder.pop();

        }

    }

    private static void bakeCommonConfig() {
        WORLD.REPLACEMENT_VANILLA_ORE_GEN = COMMON_CONFIG.REPLACEMENT_VANILLA_ORE_GEN.get();

        WORLD.COPPER_ORE_GEN = COMMON_CONFIG.COPPER_ORE_GEN.get();
        WORLD.TIN_ORE_GEN= COMMON_CONFIG.TIN_ORE_GEN.get();
        WORLD.URANITE_ORE_GEN = COMMON_CONFIG.URANITE_ORE_GEN.get();
        WORLD.PYRITE_ORE_GEN = COMMON_CONFIG.PYRITE_ORE_GEN.get();
        WORLD.SPHALERITE_ORE_GEN = COMMON_CONFIG.SPHALERITE_ORE_GEN.get();
        WORLD.CINNABAR_ORE_GEN = COMMON_CONFIG.CINNABAR_ORE_GEN.get();
        WORLD.GALENA_ORE_GEN = COMMON_CONFIG.GALENA_ORE_GEN.get();
        WORLD.CASSITERITE_ORE_GEN = COMMON_CONFIG.CASSITERITE_ORE_GEN.get();
        WORLD.TETRAHEDRITE_ORE_GEN = COMMON_CONFIG.TETRAHEDRITE_ORE_GEN.get();
        WORLD.BAUXITE_ORE_GEN = COMMON_CONFIG.BAUXITE_ORE_GEN.get();
        WORLD.RUBY_ORE_GEN = COMMON_CONFIG.RUBY_ORE_GEN.get();
        WORLD.SAPPHIRE_ORE_GEN = COMMON_CONFIG.SAPPHIRE_ORE_GEN.get();
        WORLD.IRIDIUM_ORE_GEN = COMMON_CONFIG.IRIDIUM_ORE_GEN.get();
        WORLD.EXTRA_EMERALD_ORE_GEN = COMMON_CONFIG.EXTRA_EMERALD_ORE_GEN.get();
        WORLD.TUNGSTATE_ORE_GEN = COMMON_CONFIG.TUNGSTATE_ORE_GEN.get();
        WORLD.SODALITE_ORE_GEN = COMMON_CONFIG.SODALITE_ORE_GEN.get();
        WORLD.SHELDONITE_ORE_GEN = COMMON_CONFIG.SHELDONITE_ORE_GEN.get();
        WORLD.OLIVINE_ORE_GEN = COMMON_CONFIG.OLIVINE_ORE_GEN.get();
        WORLD.CHROMITE_ORE_GEN = COMMON_CONFIG.CHROMITE_ORE_GEN.get();

        WORLD.COPPER_ORE_SIZE = COMMON_CONFIG.COPPER_ORE_SIZE.get();
        WORLD.TIN_ORE_SIZE= COMMON_CONFIG.TIN_ORE_SIZE.get();
        WORLD.URANITE_ORE_SIZE = COMMON_CONFIG.URANITE_ORE_SIZE.get();
        WORLD.PYRITE_ORE_SIZE = COMMON_CONFIG.PYRITE_ORE_SIZE.get();
        WORLD.SPHALERITE_ORE_SIZE = COMMON_CONFIG.SPHALERITE_ORE_SIZE.get();
        WORLD.CINNABAR_ORE_SIZE = COMMON_CONFIG.CINNABAR_ORE_SIZE.get();
        WORLD.GALENA_ORE_SIZE = COMMON_CONFIG.GALENA_ORE_SIZE.get();
        WORLD.CASSITERITE_ORE_SIZE = COMMON_CONFIG.CASSITERITE_ORE_SIZE.get();
        WORLD.TETRAHEDRITE_ORE_SIZE = COMMON_CONFIG.TETRAHEDRITE_ORE_SIZE.get();
        WORLD.BAUXITE_ORE_SIZE = COMMON_CONFIG.BAUXITE_ORE_SIZE.get();
        WORLD.RUBY_ORE_SIZE = COMMON_CONFIG.RUBY_ORE_SIZE.get();
        WORLD.SAPPHIRE_ORE_SIZE = COMMON_CONFIG.SAPPHIRE_ORE_SIZE.get();
        WORLD.IRIDIUM_ORE_SIZE = COMMON_CONFIG.IRIDIUM_ORE_SIZE.get();
        WORLD.EXTRA_EMERALD_ORE_SIZE = COMMON_CONFIG.EXTRA_EMERALD_ORE_SIZE.get();
        WORLD.TUNGSTATE_ORE_SIZE = COMMON_CONFIG.TUNGSTATE_ORE_SIZE.get();
        WORLD.SODALITE_ORE_SIZE = COMMON_CONFIG.SODALITE_ORE_SIZE.get();
        WORLD.SHELDONITE_ORE_SIZE = COMMON_CONFIG.SHELDONITE_ORE_SIZE.get();
        WORLD.OLIVINE_ORE_SIZE = COMMON_CONFIG.OLIVINE_ORE_SIZE.get();
        WORLD.CHROMITE_ORE_SIZE = COMMON_CONFIG.CHROMITE_ORE_SIZE.get();

        WORLD.COPPER_ORE_WEIGHT = COMMON_CONFIG.COPPER_ORE_WEIGHT.get();
        WORLD.TIN_ORE_WEIGHT= COMMON_CONFIG.TIN_ORE_WEIGHT.get();
        WORLD.URANITE_ORE_WEIGHT = COMMON_CONFIG.URANITE_ORE_WEIGHT.get();
        WORLD.URANITE_DEAD_ORE_WEIGHT = COMMON_CONFIG.URANITE_DEAD_ORE_WEIGHT.get();
        WORLD.PYRITE_ORE_WEIGHT = COMMON_CONFIG.PYRITE_ORE_WEIGHT.get();
        WORLD.SPHALERITE_ORE_WEIGHT = COMMON_CONFIG.SPHALERITE_ORE_WEIGHT.get();
        WORLD.CINNABAR_ORE_WEIGHT = COMMON_CONFIG.CINNABAR_ORE_WEIGHT.get();
        WORLD.GALENA_ORE_WEIGHT = COMMON_CONFIG.GALENA_ORE_WEIGHT.get();
        WORLD.CASSITERITE_ORE_WEIGHT = COMMON_CONFIG.CASSITERITE_ORE_WEIGHT.get();
        WORLD.TETRAHEDRITE_ORE_WEIGHT = COMMON_CONFIG.TETRAHEDRITE_ORE_WEIGHT.get();
        WORLD.BAUXITE_ORE_WEIGHT = COMMON_CONFIG.BAUXITE_ORE_WEIGHT.get();
        WORLD.RUBY_ORE_WEIGHT = COMMON_CONFIG.RUBY_ORE_WEIGHT.get();
        WORLD.SAPPHIRE_ORE_WEIGHT = COMMON_CONFIG.SAPPHIRE_ORE_WEIGHT.get();
        WORLD.IRIDIUM_ORE_WEIGHT = COMMON_CONFIG.IRIDIUM_ORE_WEIGHT.get();
        WORLD.EXTRA_EMERALD_ORE_WEIGHT = COMMON_CONFIG.EXTRA_EMERALD_ORE_WEIGHT.get();
        WORLD.TUNGSTATE_ORE_WEIGHT = COMMON_CONFIG.TUNGSTATE_ORE_WEIGHT.get();
        WORLD.SODALITE_ORE_WEIGHT = COMMON_CONFIG.SODALITE_ORE_WEIGHT.get();
        WORLD.SHELDONITE_ORE_WEIGHT = COMMON_CONFIG.SHELDONITE_ORE_WEIGHT.get();
        WORLD.SHELDONITE_END_ORE_WEIGHT = COMMON_CONFIG.SHELDONITE_END_ORE_WEIGHT.get();
        WORLD.OLIVINE_ORE_WEIGHT = COMMON_CONFIG.OLIVINE_ORE_WEIGHT.get();
        WORLD.CHROMITE_ORE_WEIGHT = COMMON_CONFIG.CHROMITE_ORE_WEIGHT.get();

        GAMEPLAY.LOSSY_PART_CRAFTING = COMMON_CONFIG.LOSSY_PART_CRAFTING.get();

    }
}
