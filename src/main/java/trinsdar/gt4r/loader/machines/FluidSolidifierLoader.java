package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.GEAR;
import static muramasa.antimatter.Data.LIQUID;
import static muramasa.antimatter.Data.PLATE_DENSE;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTING;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_SOLIDIFYING;

public class FluidSolidifierLoader {

    public static void init() {
        NUGGET.all().forEach(r -> {
            add(r, NUGGET.get(r, 1), GT4RData.MoldNugget, 16);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r, 1), GT4RData.MoldPlate, 144);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r, 1), GT4RData.MoldIngot, 144);
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r, 1), GT4RData.MoldGear, 144*4);
        });
        BLOCK.all().forEach(r -> {
            add(r, BLOCK.get().get(r).asStack(), GT4RData.MoldBlock, 144*9);
        });
    }

    private static void add(Material m, ItemStack output, Item mold, int amount) {
        if (!m.has(LIQUID)) return;
        FLUID_SOLIDIFYING.RB()
                .ii(RecipeIngredient.of(mold,1))
                .fi(m.getLiquid(amount))
                .io(output)
                .add((long)(16 * ((float)amount / 144)), 16);
    }

}
