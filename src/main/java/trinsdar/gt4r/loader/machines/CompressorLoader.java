package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;
import org.lwjgl.system.CallbackI;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.INGOT;
import static trinsdar.gt4r.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        INGOT.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(AntimatterIngredient.of(INGOT.get(ingot),9)).io(BLOCK.get().get(ingot).asStack(1)).add(Math.max(40,ingot.getMass()*2), 16);
            }
        });
        COMPRESSING.RB().ii(AntimatterIngredient.of(GT4RData.CarbonMesh, 1)).io(new ItemStack(GT4RData.CarbonPlate)).add(400, 2);
    }
}
