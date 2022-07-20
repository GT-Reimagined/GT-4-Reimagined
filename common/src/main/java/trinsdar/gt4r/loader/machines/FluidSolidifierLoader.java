package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_SOLIDIFYING;

public class FluidSolidifierLoader {

    public static void init() {
        NUGGET.all().forEach(r -> {
            add(r, NUGGET.get(r, 1), GT4RData.MoldNugget, ratio() / 9, 4);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r, 1), GT4RData.MoldPlate, 1.0f, 4);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r, 1), GT4RData.MoldIngot, 1.0f, 4);
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r, 1), GT4RData.MoldGear, 4.0f, 8);
        });
        BLOCK.all().forEach(r -> {
            add(r, BLOCK.get().get(r).asStack(), GT4RData.MoldBlock, 9.0f, 16);
        });
    }

    private static void add(Material m, ItemStack output, Item mold, float ratio, int power) {
        if (!m.has(LIQUID)) return;
        long amount = amount(ratio);
        FLUID_SOLIDIFYING.RB()
                .ii(RecipeIngredient.of(mold,1))
                .fi(m.getLiquid(amount))
                .io(output)
                .add((long)(16 * ((float)amount / ratio())), power);
    }

    private static void add(Material m, ItemStack output, Item mold, long amount, int power) {
        if (!m.has(LIQUID)) return;
        FLUID_SOLIDIFYING.RB()
                .ii(RecipeIngredient.of(mold,1))
                .fi(m.getLiquid(amount))
                .io(output)
                .add((long)(16 * ((float)amount / ratio())), power);
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }

}
