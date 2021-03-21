package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ALLOY_SMELTING;

public class AlloySmelterLoader {
    public static void init(){
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), INGOT.getMaterialIngredient(Tin)).io(INGOT.get(Bronze, 3)).add(150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), INGOT.getMaterialIngredient(Zinc)).io(INGOT.get(Brass, 3)).add(150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), DUST.getMaterialIngredient(Tin)).io(INGOT.get(Bronze, 3)).add(150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), DUST.getMaterialIngredient(Zinc)).io(INGOT.get(Brass, 3)).add(150, 16);
        addAlloyRecipes(Copper, 3, Tin, 1, Bronze, 4, 200);
        addAlloyRecipes(Copper, 3, Zinc, 1, Brass, 4, 200);
        addAlloyRecipes(Copper, 1, Nickel, 1, Cupronickel, 2, 100);
        addAlloyRecipes(Iron, 2, Nickel, 1, Invar, 3, 150);
        addAlloyRecipes(Tin, 9, Antimony, 1, SolderingAlloy, 10, 500);
        addAlloyRecipes(Lead, 4, Antimony, 1, BatteryAlloy, 5, 250);
        addAlloyRecipes(Gold, 1, Silver, 1, Electrum, 2, 100);
        addAlloyRecipes(Magnesium, 1, Aluminium, 2, Magnalium, 3, 150);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Redstone, 4), DUST.getMaterialIngredient(Copper)).io(INGOT.get(RedAlloy, 1)).add(50, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Redstone, 4), INGOT.getMaterialIngredient(Copper)).io(INGOT.get(RedAlloy, 1)).add(50, 16);
        //TODO compat for bluepower
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), DUST.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), INGOT.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);


    }

    private static void addAlloyRecipes(Material input1, int count1, Material input2, int count2, Material output, int countO, int duration){
        ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(duration, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(duration, 16);
        ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(duration, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(duration, 16);
    }
}
