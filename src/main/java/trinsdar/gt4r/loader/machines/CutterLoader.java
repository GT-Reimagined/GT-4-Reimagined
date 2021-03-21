package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.PLATE;
import static trinsdar.gt4r.data.RecipeMaps.BENDING;
import static trinsdar.gt4r.data.RecipeMaps.CUTTING;

public class CutterLoader {
    public static void init(){
        PLATE.all().forEach(t -> {
            if (!t.has(BLOCK)) return;
            long duration = Math.max(t.getMass(), 1) * 300;
            CUTTING.RB().ii(AntimatterIngredient.of(BLOCK.getMaterialTag(t), 1)).io(PLATE.get(t,9)).add(duration, 30);
        });
        CUTTING.RB().ii(AntimatterIngredient.of(Items.GLASS, 3)).io(new ItemStack(Items.GLASS_PANE)).add(50, 8);

    }
}
