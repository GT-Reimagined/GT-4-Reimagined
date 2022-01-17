package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.data.CustomTags;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
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
                COMPRESSING.RB().ii(RecipeIngredient.of(INGOT.getMaterialTag(ingot),9)).io(BLOCK.get().get(ingot).asStack(1)).add(Math.max(40,ingot.getMass()*2), 16);
            }
        });
        GEM.all().forEach(gem -> {
            if (gem.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(gem),9)).io(BLOCK.get().get(gem).asStack(1)).add(Math.max(40,gem.getMass()*2), 16);
            }
            if (gem.has(PLATE)){
                COMPRESSING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(gem),9)).io(PLATE.get(gem, 1)).add(Math.max(40,gem.getMass()*2), 16);
            }
        });
        COMPRESSING.RB().ii(RecipeIngredient.of(GT4RData.CarbonMesh, 1)).io(PLATE.get(Carbon, 1)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(CustomTags.INGOTS_MIXED_METAL, 1).setIgnoreNbt()).io(new ItemStack(GT4RData.AdvancedAlloy)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SNOWBALL, 1)).io(new ItemStack(Items.SNOW_BLOCK)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Glowstone, 4)).io(new ItemStack(Items.GLOWSTONE)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SNOW_BLOCK, 1)).io(new ItemStack(Items.ICE)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(GT4RData.CoalBall, 1)).io(new ItemStack(GT4RData.CompressedCoalBall)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.SAND, 4)).io(new ItemStack(Items.SANDSTONE)).add(400, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.RED_SAND, 4)).io(new ItemStack(Items.RED_SANDSTONE)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Energium, 9)).io(new ItemStack(GT4RData.EnergyCrystal)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Wood, 1)).io(PLATE.get(Wood, 1)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Fireclay, 1)).io(new ItemStack(CompressedFireClay)).add(200, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.SAPLINGS, 4)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.SMALL_FLOWERS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Tags.Items.CROPS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Tags.Items.SEEDS, 16)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(ItemTags.LEAVES, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CACTUS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.DEAD_BUSH, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(CORALS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Tags.Items.MUSHROOMS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.NETHER_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_FUNGUS, 4)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_ROOTS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CRIMSON_FUNGUS, 4)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.CRIMSON_ROOTS, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.WARPED_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(VINES, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.KELP, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.DRIED_KELP, 8)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.PUMPKIN, 4)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.MELON, 4)).io(new ItemStack(Plantball)).add(300, 2);
        COMPRESSING.RB().ii(RecipeIngredient.of(Items.MELON_SLICE, 8)).io(new ItemStack(Plantball)).add(300, 2);
    }
}
