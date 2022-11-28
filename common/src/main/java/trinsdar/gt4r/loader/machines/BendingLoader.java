package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.Materials.Tin;
import static trinsdar.gt4r.data.RecipeMaps.BENDING;

public class BendingLoader {
    public static void init() {
        AntimatterMaterialTypes.PLATE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(AntimatterMaterialTypes.INGOT)) return;
            BENDING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(t,1), GT4RData.INT_CIRCUITS.get(1)).io(AntimatterMaterialTypes.PLATE.get(t,1)).add(duration, 24);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(t -> {
            long duration = Math.max(t.getMass(), 1);
            if (!t.has(AntimatterMaterialTypes.INGOT)) return;
            BENDING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(t,9), GT4RData.INT_CIRCUITS.get(9)).io(AntimatterMaterialTypes.PLATE_DENSE.get(t,1)).add(duration, 24);
        });
        AntimatterMaterialTypes.RING.all().forEach(m -> {
            long duration = Math.max(m.getMass(), 1);
            if (!m.has(AntimatterMaterialTypes.ROD)) return;
            BENDING.RB().ii(AntimatterMaterialTypes.ROD.getMaterialIngredient(m, 1), GT4RData.INT_CIRCUITS.get(1)).io(AntimatterMaterialTypes.RING.get(m, 2)).add(duration, 24);
        });
        BENDING.RB().ii(RecipeIngredient.of(GT4RData.MixedMetal, 1)).io(new ItemStack(GT4RData.AdvancedAlloy)).add(100, 8);
        BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(Tin, 2)).io(new ItemStack(GT4RData.CellTin)).add(200, 8);
        BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(AntimatterMaterials.Iron, 3)).io(new ItemStack(Items.BUCKET)).add(200, 4);
    }
}
