package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.FishOil;
import static trinsdar.gt4r.data.Materials.Honey;
import static trinsdar.gt4r.data.Materials.SeedOil;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTING;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTOR_COILS;

public class FluidExtractorLoader {
    public static void init() {
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.CupronickelHeatingCoil, 1)).add(0, 0, 250);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.KanthalHeatingCoil, 1)).add(0, 0, 500);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.NichromeHeatingCoil, 1)).add(0, 0, 750);
        ROD.all().forEach(r -> {
            add(r, ROD.get(r), 72);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r), 144);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r), 144);
            if (r.has(DUST)){
                add(r, DUST.get(r), 144);
            }
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r), 144*4);
        });
        PLATE_DENSE.all().forEach(r -> {
            add(r, PLATE_DENSE.get(r), 144*9);
        });

        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(Tags.Items.SEEDS,1))
                .fo(SeedOil.getLiquid(10))
                .add(32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(ItemTags.FISHES,1))
                .fo(FishOil.getLiquid(10))
                .add(32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(Items.HONEY_BOTTLE,1))
                .fo(Honey.getLiquid(250))
                .io(new ItemStack(Items.GLASS_BOTTLE))
                .add(32, 2);
    }

    private static void add(Material m, Item i, int amount) {
        if (!m.has(LIQUID)) return;
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(i,1))
                .fo(m.getLiquid(amount))
                .add((long)(m.getMass()*((float)amount/144F)), 64, m.getMeltingPoint());
    }
}
