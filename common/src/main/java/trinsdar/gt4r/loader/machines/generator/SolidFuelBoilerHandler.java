package trinsdar.gt4r.loader.machines.generator;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import muramasa.antimatter.data.ForgeCTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.SOLID_FUEL_BOILERS;

public class SolidFuelBoilerHandler {
    public static void init(){
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(Coal), 1)).io(DUST.get(DarkAsh, 1)).add("coal",160);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(Charcoal), 1)).io(DUST.get(Ash, 1)).add("charcoal",160);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ForgeCTags.STORAGE_BLOCKS_COAL, 1)).io(DUST.get(DarkAsh, 9)).add("coal_block", 1600);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(Charcoal), 1)).io(DUST.get(Ash, 9)).add("charcoal_block",1600);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(CoalCoke), 1)).io(DUST.get(DarkAsh, 1)).add("coal_coke",320);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(CoalCoke), 1)).io(DUST.get(DarkAsh, 9)).add("coal_coke_block",3200);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.PLANKS, 1)).io(DUST.get(Ash, 1)).add("planks",30);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.LOGS_THAT_BURN, 1)).io(DUST.get(Ash, 1)).add("logs",30);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOOL, 1)).io(DUST.get(Ash, 1)).add("wool",10);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOODEN_SLABS, 1)).io(DUST.get(Ash, 1)).add("wooden_slabs",15);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ForgeCTags.BOOKSHELVES, 1)).io(DUST.get(Ash, 1)).add("bookshelves",30);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.WOODEN_STAIRS, 1)).io(DUST.get(Ash, 1)).add("wooden_stairs",30);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(Items.DRIED_KELP_BLOCK, 1)).add("dried_kelp_block",400);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ItemTags.SAPLINGS, 1)).add("saplings",10);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(Items.DEAD_BUSH, 1)).add("dead_bush",10);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(Items.BAMBOO, 1)).add("bamboo",5);
        SOLID_FUEL_BOILERS.RB().ii(RecipeIngredient.of(ForgeCTags.RODS_WOODEN, 1)).add("sticks",10);
    }
}
