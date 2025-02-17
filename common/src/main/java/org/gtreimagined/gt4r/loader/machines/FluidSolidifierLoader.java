package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static org.gtreimagined.gt4r.data.Materials.Obsidian;
import static org.gtreimagined.gt4r.data.RecipeMaps.FLUID_SOLIDIFIER;

public class FluidSolidifierLoader {

    public static void init() {
        NUGGET.all().forEach(r -> {
            add(r, NUGGET.get(r, 1), "nugget", GTCoreItems.MoldNugget, L / 9, 4);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r, 1), "plate", GTCoreItems.MoldPlate, 1.0f, 4);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r, 1), "ingot", GTCoreItems.MoldIngot, 1.0f, 4);
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r, 1), "gear", GTCoreItems.MoldGear, 4.0f, 8);
        });
        BLOCK.all().forEach(r -> {
            add(r, BLOCK.get().get(r).asStack(), "block", GTCoreItems.MoldBlock, 9.0f, 16);
        });
        FLUID_SOLIDIFIER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).fi(Lava.getLiquid(111)).io(PLATE.get(Obsidian)).add("obsidian_plate", 16, 8);
    }

    private static void add(Material m, ItemStack output, String suffix, Item mold, float ratio, int power) {
        long amount = amount(ratio);
        add(m, output, suffix, mold, amount, power);
    }

    private static void add(Material m, ItemStack output, String suffix, Item mold, long amount, int power) {
        if (!m.has(LIQUID)) return;
        FLUID_SOLIDIFIER.RB()
                .ii(RecipeIngredient.of(mold,1))
                .fi(m.getLiquid(amount))
                .io(output)
                .add(m.getId() + "_" + suffix, (long)(16 * ((float)amount / L)), power);
    }

    private static long amount(float ingots){
        return (long) (L * ingots);
    }

}
