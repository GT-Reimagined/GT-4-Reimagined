package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.GT4RData.Biochaff;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.Materials.Brick;
import static trinsdar.gt4r.data.Materials.Clay;
import static trinsdar.gt4r.data.Materials.Limestone;
import static trinsdar.gt4r.data.Materials.Scoria;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;

public class MaceratorManualLoader {
    public static void init(){
        MACERATING.RB().ii(RecipeIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.SAND)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.GRAVEL)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(25, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY_BALL, 1)).io(DUST_SMALL.get(Clay, 2)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Plantball, 1)).io(new ItemStack(Biochaff, 1)).add(300, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Biochaff, 1)).io(new ItemStack(Items.DIRT, 1)).add(300, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY, 1)).io(DUST.get(Clay, 2)).add(30, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.TERRACOTTA, 1)).io(DUST.get(Clay, 1)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add(100, 2);
        MACERATING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(DUST.get(Wood, 6)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.PRISMARINE, 1)).io(DUST.get(Prismarine, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.DARK_PRISMARINE, 1)).io(DUST.get(DarkPrismarine, 1)).add(400, 2);
        if (AntimatterAPI.isModLoaded(Ref.MOD_CREATE)){
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "weathered_limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "dark_scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
        }
    }
}
