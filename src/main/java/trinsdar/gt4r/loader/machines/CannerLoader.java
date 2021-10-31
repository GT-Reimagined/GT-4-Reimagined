package trinsdar.gt4r.loader.machines;

import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.Redstone;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.Cadmium;
import static trinsdar.gt4r.data.Materials.Lithium;
import static trinsdar.gt4r.data.Materials.Sodium;
import static trinsdar.gt4r.data.RecipeMaps.CANNING;

public class CannerLoader {
    public static void init(){
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Cadmium, 2)).io(new ItemStack(BatterySmallCadmium)).add(100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Lithium, 2)).io(new ItemStack(BatterySmallLithium)).add(100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Sodium, 2)).io(new ItemStack(BatterySmallSodium)).add(100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Redstone, 2)).io(new ItemStack(BatteryRE)).add(100, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Cadmium, 8)).io(new ItemStack(BatteryMediumCadmium)).add(400, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Lithium, 8)).io(new ItemStack(BatteryMediumLithium)).add(400, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Sodium, 8)).io(new ItemStack(BatteryMediumSodium)).add(400, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Cadmium, 32)).io(new ItemStack(BatteryLargeCadmium)).add(1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Lithium, 32)).io(new ItemStack(BatteryLargeLithium)).add(1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Sodium, 32)).io(new ItemStack(BatteryLargeSodium)).add(1600, 2);
    }
}
