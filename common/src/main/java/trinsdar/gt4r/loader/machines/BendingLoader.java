package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.TierMaps;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static trinsdar.gt4r.data.Materials.Tin;
import static trinsdar.gt4r.data.RecipeMaps.PLATE_BENDER;

public class BendingLoader {
    public static void init() {
        AntimatterMaterialTypes.PLATE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(INGOT)) return;
            PLATE_BENDER.RB().ii(INGOT.getMaterialIngredient(t,1), TierMaps.INT_CIRCUITS.get(1)).io(PLATE.get(t,1)).add(t.getId() + "_plate", duration, 24);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(INGOT)) return;
            PLATE_BENDER.RB().ii(INGOT.getMaterialIngredient(t,9), TierMaps.INT_CIRCUITS.get(9)).io(PLATE_DENSE.get(t,1)).add(t.getId() + "_dense_plate", duration, 24);
        });
        AntimatterMaterialTypes.RING.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            if (!m.has(ROD)) return;
            PLATE_BENDER.RB().ii(ROD.getMaterialIngredient(m, 1), TierMaps.INT_CIRCUITS.get(1)).io(RING.get(m, 2)).add(m.getId() + "_ring", duration, 24);
        });
        PLATE_BENDER.RB().ii(RecipeIngredient.of(GT4RData.MixedMetal, 1)).io(new ItemStack(GT4RData.AdvancedAlloy)).add("advanced_alloy",100, 8);
        PLATE_BENDER.RB().ii(PLATE.getMaterialIngredient(Tin, 2)).io(new ItemStack(GT4RData.CellTin)).add("tin_cell",200, 8);
        PLATE_BENDER.RB().ii(PLATE.getMaterialIngredient(Iron, 3)).io(new ItemStack(Items.BUCKET)).add("bucket",200, 4);
    }
}
