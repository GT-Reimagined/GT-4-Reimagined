package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import speiger.src.collections.objects.lists.ObjectArrayList;

import java.util.List;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.RecipeMaps.ORE_BYPRODUCTS;

public class OreByproducts {
    public static void init() {
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE)) return;
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient();
            RecipeIngredient crushed = CRUSHED.getIngredient(m, 1);
            if (m.hasByProducts()) {
                List<Material> byProducts = m.getByProducts();
                int byProductsCount = byProducts.size();

                List<RecipeIngredient> ores = new ObjectArrayList<>();
                if (m.has(ORE)) ores.add(ore);
                if (m.has(CRUSHED)) ores.add(crushed);
                if (m.has(CRUSHED_PURIFIED)) ores.add(CRUSHED_PURIFIED.getIngredient(m, 1));
                if (m.has(CRUSHED_CENTRIFUGED)) ores.add(CRUSHED_CENTRIFUGED.getIngredient(m, 1));


                List<ItemStack> dusts = new ObjectArrayList<>(byProductsCount);
                byProducts.forEach(p -> dusts.add(DUST.get(p, 1)));
                ORE_BYPRODUCTS.RB().ii(ores).io(dusts.toArray(new ItemStack[byProductsCount])).add();
            }
        });
    }
}
