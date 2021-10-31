package trinsdar.gt4r.loader.machines;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.data.RecipeMaps.EXTRACTING;

public class ExtractorLoader {
    public static void init() {
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_LOG.asItem(),1)).io(DUST.get(Rubber, 2)).add(200,8);
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_LEAVES.asItem(),1)).io(DUST.get(Rubber, 1)).add(150,8);
        EXTRACTING.RB().ii(of(GT4RData.RUBBER_SAPLING.asItem(),1)).io(DUST.get(Rubber, 1)).add(150,8);
        EXTRACTING.RB().ii(of(GT4RData.StickyResin,1)).io(DUST.get(Rubber, 3)).add(150,8);
        EXTRACTING.RB().ii(of(Items.LILY_OF_THE_VALLEY, 1)).io(new ItemStack(Items.WHITE_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.ORANGE_TULIP, 1)).io(new ItemStack(Items.ORANGE_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.ALLIUM, 1)).io(new ItemStack(Items.MAGENTA_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.LILAC, 1)).io(new ItemStack(Items.MAGENTA_DYE, 6)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.BLUE_ORCHID, 1)).io(new ItemStack(Items.LIGHT_BLUE_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.DANDELION, 1)).io(new ItemStack(Items.YELLOW_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.SUNFLOWER, 1)).io(new ItemStack(Items.YELLOW_DYE, 6)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.PINK_TULIP, 1)).io(new ItemStack(Items.PINK_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.PEONY, 1)).io(new ItemStack(Items.PINK_DYE, 6)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.OXEYE_DAISY, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.AZURE_BLUET, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.WHITE_TULIP, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.CORNFLOWER, 1)).io(new ItemStack(Items.BLUE_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.RED_TULIP, 1)).io(new ItemStack(Items.RED_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.POPPY, 1)).io(new ItemStack(Items.RED_DYE, 3)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.ROSE_BUSH, 1)).io(new ItemStack(Items.RED_DYE, 6)).add(400, 2);
        EXTRACTING.RB().ii(of(Items.WITHER_ROSE, 1)).io(new ItemStack(Items.BLACK_DYE, 3)).add(400, 2);
    }
}
