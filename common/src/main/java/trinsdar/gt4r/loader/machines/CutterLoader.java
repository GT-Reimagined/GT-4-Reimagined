package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.BOLT;
import static muramasa.antimatter.Data.PLATE;
import static muramasa.antimatter.Data.ROD;
import static trinsdar.gt4r.data.RecipeMaps.CUTTING;

public class CutterLoader {
    public static void init(){
        PLATE.all().forEach(t -> {
            if (!t.has(BLOCK)) return;
            long duration = Math.max(t.getMass(), 1) * 300;
            CUTTING.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(t), 1)).io(PLATE.get(t,9)).add(duration, 30);
        });
        BOLT.all().forEach(t -> {
            if (!t.has(ROD)) return;
            long duration = Math.max(t.getMass(), 1) * 4;
            CUTTING.RB().ii(RecipeIngredient.of(ROD.getMaterialTag(t), 1)).io(BOLT.get(t,4)).add(duration, 30);
        });
        CUTTING.RB().ii(RecipeIngredient.of(Items.GLASS, 3)).io(new ItemStack(Items.GLASS_PANE)).add(50, 8);

    }
}
