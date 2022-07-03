package trinsdar.gt4r.loader.machines.generator;

import me.alphamode.forgetags.Tags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.COAL_BOILERS;

public class CoalBoilerHandler {
    public static void init(){
        COAL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(Coal), 1)).io(DUST.get(DarkAsh, 1)).add(160);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(Charcoal), 1)).io(DUST.get(Ash, 1)).add(160);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Tags.Items.STORAGE_BLOCKS_COAL, 1)).io(DUST.get(DarkAsh, 9)).add(1600);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(Charcoal), 1)).io(DUST.get(Ash, 9)).add(1600);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(CoalCoke), 1)).io(DUST.get(DarkAsh, 1)).add(320);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(CoalCoke), 1)).io(DUST.get(DarkAsh, 9)).add(3200);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.PLANKS, 1)).io(DUST.get(Ash, 1)).add(30);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.LOGS_THAT_BURN, 1)).io(DUST.get(Ash, 1)).add(30);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOOL, 1)).io(DUST.get(Ash, 1)).add(10);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOODEN_SLABS, 1)).io(DUST.get(Ash, 1)).add(15);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Tags.Items.BOOKSHELVES, 1)).io(DUST.get(Ash, 1)).add(30);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOODEN_STAIRS, 1)).io(DUST.get(Ash, 1)).add(30);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Items.DRIED_KELP_BLOCK, 1)).add(400);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.SAPLINGS, 1)).add(10);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Items.DEAD_BUSH, 1)).add(10);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Items.BAMBOO, 1)).add(5);
        COAL_BOILERS.RB().ii(RecipeIngredient.of(Tags.Items.RODS_WOODEN, 1)).add(10);
    }
}
