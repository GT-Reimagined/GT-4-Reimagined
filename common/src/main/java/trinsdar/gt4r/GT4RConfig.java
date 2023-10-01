package trinsdar.gt4r;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GT4RConfig {
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

    public static void onModConfigEvent(final ModConfig config) {
        if (config.getModId().equals(GT4RRef.ID)){
            if (config.getSpec() == COMMON_SPEC) bakeCommonConfig();
        }
    }

    public static class Gameplay {

        /** @see CommonConfig **/

        public boolean REMOVE_VANILLA_CHARCOAL_RECIPE, HARDER_WOOD, HARDER_VANILLA_RECIPES, SULFUR_TORCH;

    }

    public static class World {

        /** @see CommonConfig **/

        public boolean REPLACEMENT_VANILLA_ORE_GEN;

        public boolean GENERATE_STONE_LAYERS;
    }

    public static class CommonConfig {

        public ForgeConfigSpec.BooleanValue LOSSY_PART_CRAFTING, REMOVE_VANILLA_CHARCOAL_RECIPE, HARDER_WOOD, HARDER_VANILLA_RECIPES, GENERATE_STONE_LAYERS, SULFUR_TORCH;

        public ForgeConfigSpec.BooleanValue REPLACEMENT_VANILLA_ORE_GEN;

        public CommonConfig(ForgeConfigSpec.Builder builder) {

            builder.push("World");

            REPLACEMENT_VANILLA_ORE_GEN = builder.comment("Enables my own version of vanilla ore gen (Iron, Coal, Diamond, ect.) if Antimatter.World.DISABLE_ORE_GEN is true - Default: true")
                    .translation(GT4RRef.ID + ".config.replacement_vanilla_ore_gen")
                    .worldRestart()
                    .define("ENABLE_REPLACEMENT_VANILLA_ORE_GEN", true);

            GENERATE_STONE_LAYERS = builder.comment("Enables Generations of my stnes as stone layers in the world - Default: false")
                    .translation(GT4RRef.ID + ".config.generate_stone_layers")
                    .worldRestart()
                    .define("GENERATE_STONE_LAYERS", false);

            builder.pop();

            builder.push("Gameplay");

            HARDER_VANILLA_RECIPES = builder.comment("Enables Harder vanilla crafting recipes for things like hoppers and armor - Default: true")
                    .translation(GT4RRef.ID + ".config.harder_vanilla_recipes")
                    .define("HARDER_VANILLA_RECIPES", true);

            HARDER_WOOD = builder.comment("If true logs to planks and planks to sticks give half of vannila amounts - Default: false")
                    .translation(GT4RRef.ID + ".config.harder_wood")
                    .define("HARDER_WOOD", false);

            REMOVE_VANILLA_CHARCOAL_RECIPE = builder.comment("Disables vanilla charcoal recipe if true. - Default: false")
                    .translation(GT4RRef.ID + ".config.remove_vanilla_charcoal_recipe")
                    .define("REMOVE_VANILLA_CHARCOAL_RECIPE", false);

            SULFUR_TORCH = builder.comment("Enables sulfur torch recipe. - Default: true")
                    .translation(GT4RRef.ID + ".config.sulfur_torch")
                    .define("SULFUR_TORCH", true);
            builder.pop();

        }

    }

    private static void bakeCommonConfig() {
        WORLD.REPLACEMENT_VANILLA_ORE_GEN = COMMON_CONFIG.REPLACEMENT_VANILLA_ORE_GEN.get();
        WORLD.GENERATE_STONE_LAYERS = COMMON_CONFIG.GENERATE_STONE_LAYERS.get();

        GAMEPLAY.HARDER_VANILLA_RECIPES = COMMON_CONFIG.HARDER_VANILLA_RECIPES.get();
        GAMEPLAY.HARDER_WOOD = COMMON_CONFIG.HARDER_WOOD.get();
        GAMEPLAY.REMOVE_VANILLA_CHARCOAL_RECIPE = COMMON_CONFIG.REMOVE_VANILLA_CHARCOAL_RECIPE.get();
        GAMEPLAY.SULFUR_TORCH = COMMON_CONFIG.SULFUR_TORCH.get();

    }
}
