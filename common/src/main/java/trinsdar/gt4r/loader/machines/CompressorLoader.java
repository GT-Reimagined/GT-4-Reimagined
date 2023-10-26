package trinsdar.gt4r.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import trinsdar.gt4r.data.CustomTags;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static trinsdar.gt4r.data.CustomTags.CORALS;
import static trinsdar.gt4r.data.CustomTags.VINES;
import static trinsdar.gt4r.data.GT4RData.CompressedFireClay;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        INGOT.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(INGOT.getMaterialTag(ingot),9)).io(BLOCK.get().get(ingot).asStack(1)).add(ingot.getId() + "_block", Math.max(40,ingot.getMass()*2), 16);
            }
        });
        GEM.all().forEach(gem -> {
            if (gem.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(gem), 9)).io(BLOCK.get().get(gem).asStack(1)).add(gem.getId() + "_block", Math.max(40, gem.getMass() * 2), 16);
            }
            if (gem.has(PLATE)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(gem), 9)).io(PLATE.get(gem, 1)).add(gem.getId() + "_plate", Math.max(40, gem.getMass() * 2), 16);
            }
        });
        COMPRESSING.RB().ii(RecipeIngredient.of(GTCoreItems.CarbonMesh, 1)).io(PLATE.get(Carbon, 1)).add("carbon_plate",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(CustomTags.INGOTS_MIXED_METAL, 1).setIgnoreNbt()).io(new ItemStack(GT4RData.AdvancedAlloy)).add("advanced_alloy",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SNOWBALL, 1)).io(new ItemStack(Items.SNOW_BLOCK)).add("snow_block",400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Glowstone, 4)).io(new ItemStack(Items.GLOWSTONE)).add("glowstone",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SNOW_BLOCK, 1)).io(new ItemStack(Items.ICE)).add("ice",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(GTCoreItems.CoalBall, 1)).io(new ItemStack(GTCoreItems.CompressedCoalBall)).add("compressed_coal_ball",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SAND, 4)).io(new ItemStack(Items.SANDSTONE)).add("sandstone",400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.RED_SAND, 4)).io(new ItemStack(Items.RED_SANDSTONE)).add("red_sandstone",400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Energium, 9)).io(new ItemStack(GTCoreItems.EnergyCrystal)).add("energy_crystal",400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Wood, 1)).io(PLATE.get(Wood, 1)).add("wood_plate",400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Fireclay, 1)).io(new ItemStack(CompressedFireClay)).add("compressed_fireclay",200, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.SAPLINGS, 4)).io(new ItemStack(Plantball)).add("plantball",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.SMALL_FLOWERS, 8)).io(new ItemStack(Plantball)).add("plantball_1",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ForgeCTags.CROPS, 8)).io(new ItemStack(Plantball)).add("plantball_2",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ForgeCTags.SEEDS, 16)).io(new ItemStack(Plantball)).add("plantball_3",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.LEAVES, 8)).io(new ItemStack(Plantball)).add("plantball_4",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CACTUS, 8)).io(new ItemStack(Plantball)).add("plantball_5",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.DEAD_BUSH, 8)).io(new ItemStack(Plantball)).add("plantball_6",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(CORALS, 8)).io(new ItemStack(Plantball)).add("plantball_7",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ForgeCTags.MUSHROOMS, 8)).io(new ItemStack(Plantball)).add("plantball_8",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.NETHER_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add("plantball_9",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_FUNGUS, 4)).io(new ItemStack(Plantball)).add("plantball_10",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_ROOTS, 8)).io(new ItemStack(Plantball)).add("plantball_11",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CRIMSON_FUNGUS, 4)).io(new ItemStack(Plantball)).add("plantball_12",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CRIMSON_ROOTS, 8)).io(new ItemStack(Plantball)).add("plantball_13",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add("plantball_14",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(VINES, 8)).io(new ItemStack(Plantball)).add("plantball_15",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.KELP, 8)).io(new ItemStack(Plantball)).add("plantball_16",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.DRIED_KELP, 8)).io(new ItemStack(Plantball)).add("plantball_17",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.PUMPKIN, 4)).io(new ItemStack(Plantball)).add("plantball_18",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.MELON, 4)).io(new ItemStack(Plantball)).add("plantball_19",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.MELON_SLICE, 8)).io(new ItemStack(Plantball)).add("plantball_20",300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SUGAR_CANE, 8)).io(new ItemStack(Plantball)).add("plantball_21",300, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Quartz, 1)).io(new ItemStack(Items.QUARTZ)).add("quartz",200, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.BLAZE_POWDER, 5)).io(new ItemStack(Items.BLAZE_ROD)).add("blaze_rod",200, 2);
    }
}
