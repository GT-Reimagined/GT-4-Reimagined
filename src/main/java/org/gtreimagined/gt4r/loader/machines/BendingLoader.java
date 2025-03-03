package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreTags;
import org.gtreimagined.gt4r.data.GT4RItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static org.gtreimagined.gt4r.data.Materials.Tin;
import static org.gtreimagined.gt4r.data.RecipeMaps.PLATE_BENDER;

public class BendingLoader {
    public static void init() {
        AntimatterMaterialTypes.PLATE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(INGOT)) return;
            PLATE_BENDER.RB().ii(INGOT.getMaterialIngredient(t,1), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).io(PLATE.get(t,1)).add(t.getId() + "_plate", duration, 24);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(INGOT)) return;
            PLATE_BENDER.RB().ii(INGOT.getMaterialIngredient(t,9), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(9)).io(PLATE_DENSE.get(t,1)).add(t.getId() + "_dense_plate", duration, 24);
        });
        AntimatterMaterialTypes.RING.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            if (!m.has(ROD)) return;
            PLATE_BENDER.RB().ii(ROD.getMaterialIngredient(m, 1), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).io(RING.get(m, 2)).add(m.getId() + "_ring", duration, 24);
        });
        PLATE_BENDER.RB().ii(RecipeIngredient.of(GTCoreTags.INGOTS_MIXED_METAL, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.AdvancedAlloy)).add("advanced_alloy",100, 8);
        PLATE_BENDER.RB().ii(PLATE.getMaterialIngredient(Tin, 2)).io(new ItemStack(GT4RItems.CellTin)).add("tin_cell",200, 8);
        PLATE_BENDER.RB().ii(PLATE.getMaterialIngredient(Iron, 3)).io(new ItemStack(Items.BUCKET)).add("bucket",200, 4);
    }
}
