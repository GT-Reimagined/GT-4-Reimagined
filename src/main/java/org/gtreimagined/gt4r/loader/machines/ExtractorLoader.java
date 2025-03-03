package org.gtreimagined.gt4r.loader.machines;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt4r.data.Materials.Rubber;
import static org.gtreimagined.gt4r.data.RecipeMaps.EXTRACTOR;

public class ExtractorLoader {
    public static void init() {
        EXTRACTOR.RB().ii(of(GTCoreTags.RUBBER_LOGS,1)).io(DUST.get(Rubber, 2)).add("rubber_dust",200,8);
        EXTRACTOR.RB().ii(of(GTCoreBlocks.RUBBER_LEAVES.asItem(),1)).io(DUST.get(Rubber, 1)).add("rubber_dust_1", 150,8);
        EXTRACTOR.RB().ii(of(GTCoreBlocks.RUBBER_SAPLING.asItem(),1)).io(DUST.get(Rubber, 1)).add("rubber_dust_2", 150,8);
        EXTRACTOR.RB().ii(of(GTCoreItems.StickyResin,1)).io(DUST.get(Rubber, 3)).add("rubber_dust_3", 150,8);
        EXTRACTOR.RB().ii(of(Items.LILY_OF_THE_VALLEY, 1)).io(new ItemStack(Items.WHITE_DYE, 3)).add("white_dye", 400, 2);
        EXTRACTOR.RB().ii(of(Items.ORANGE_TULIP, 1)).io(new ItemStack(Items.ORANGE_DYE, 3)).add("orange_dye", 400, 2);
        EXTRACTOR.RB().ii(of(Items.ALLIUM, 1)).io(new ItemStack(Items.MAGENTA_DYE, 3)).add("magenta_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.LILAC, 1)).io(new ItemStack(Items.MAGENTA_DYE, 6)).add("magenta_dye_1",400, 2);
        EXTRACTOR.RB().ii(of(Items.BLUE_ORCHID, 1)).io(new ItemStack(Items.LIGHT_BLUE_DYE, 3)).add("light_blue_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.DANDELION, 1)).io(new ItemStack(Items.YELLOW_DYE, 3)).add("yellow_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.SUNFLOWER, 1)).io(new ItemStack(Items.YELLOW_DYE, 6)).add("yellow_dye_1",400, 2);
        EXTRACTOR.RB().ii(of(Items.PINK_TULIP, 1)).io(new ItemStack(Items.PINK_DYE, 3)).add("pink_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.PEONY, 1)).io(new ItemStack(Items.PINK_DYE, 6)).add("pink_dye_1",400, 2);
        EXTRACTOR.RB().ii(of(Items.OXEYE_DAISY, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add("light_gray_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.AZURE_BLUET, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add("light_gray_dye_1",400, 2);
        EXTRACTOR.RB().ii(of(Items.WHITE_TULIP, 1)).io(new ItemStack(Items.LIGHT_GRAY_DYE, 3)).add("light_gray_dye_2",400, 2);
        EXTRACTOR.RB().ii(of(Items.CORNFLOWER, 1)).io(new ItemStack(Items.BLUE_DYE, 3)).add("blue_dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.RED_TULIP, 1)).io(new ItemStack(Items.RED_DYE, 3)).add("red__dye",400, 2);
        EXTRACTOR.RB().ii(of(Items.POPPY, 1)).io(new ItemStack(Items.RED_DYE, 3)).add("red__dye_1",400, 2);
        EXTRACTOR.RB().ii(of(Items.ROSE_BUSH, 1)).io(new ItemStack(Items.RED_DYE, 6)).add("red__dye_2",400, 2);
        EXTRACTOR.RB().ii(of(Items.WITHER_ROSE, 1)).io(new ItemStack(Items.BLACK_DYE, 3)).add("black_dye",400, 2);
    }
}
