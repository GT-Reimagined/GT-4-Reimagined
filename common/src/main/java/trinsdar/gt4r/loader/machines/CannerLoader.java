package trinsdar.gt4r.loader.machines;

import net.minecraft.world.item.ItemStack;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Redstone;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.Cadmium;
import static trinsdar.gt4r.data.Materials.Lithium;
import static trinsdar.gt4r.data.Materials.Sodium;
import static trinsdar.gt4r.data.RecipeMaps.CANNING;

public class CannerLoader {
    public static void init(){
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Cadmium, 2)).io(new ItemStack(BatterySmallCadmium)).add("battery_small_cadmium",100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Lithium, 2)).io(new ItemStack(BatterySmallLithium)).add("battery_small_lithium",100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Sodium, 2)).io(new ItemStack(BatterySmallSodium)).add("battery_small_sodium",100, 2);
        CANNING.RB().ii(of(BatteryHullSmall, 1), DUST.getMaterialIngredient(Redstone, 2)).io(new ItemStack(BatteryRE)).add("battery_re",100, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Cadmium, 8)).io(new ItemStack(BatteryMediumCadmium)).add("battery_medium_cadmium",400, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Lithium, 8)).io(new ItemStack(BatteryMediumLithium)).add("battery_medium_lithium",400, 2);
        CANNING.RB().ii(of(BatteryHullMedium, 1), DUST.getMaterialIngredient(Sodium, 8)).io(new ItemStack(BatteryMediumSodium)).add("battery_medium_sodium",400, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Cadmium, 32)).io(new ItemStack(BatteryLargeCadmium)).add("battery_large_cadmium",1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Lithium, 32)).io(new ItemStack(BatteryLargeLithium)).add("battery_large_lithium",1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge, 1), DUST.getMaterialIngredient(Sodium, 32)).io(new ItemStack(BatteryLargeSodium)).add("battery_large_sodium",1600, 2);
    }
}
