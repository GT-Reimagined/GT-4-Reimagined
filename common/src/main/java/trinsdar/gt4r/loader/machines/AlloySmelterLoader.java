package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ALLOY_SMELTING;

public class AlloySmelterLoader {
    public static void init(){
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), INGOT.getMaterialIngredient(Tin, 1)).io(INGOT.get(Bronze, 3)).add("bronze_ingot_from_tetra", 150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), INGOT.getMaterialIngredient(Zinc, 1)).io(INGOT.get(Brass, 3)).add("brass_ingot_from_tetra",150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), DUST.getMaterialIngredient(Tin, 1)).io(INGOT.get(Bronze, 3)).add("bronze_ingot_from_tetra_1",150, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Tetrahedrite, 3), DUST.getMaterialIngredient(Zinc, 1)).io(INGOT.get(Brass, 3)).add("brass_ingot_from_tetra_1",150, 16);
        addAlloyRecipes(Copper, 3, Tin, 1, Bronze, 4, 200);
        addAlloyRecipes(Copper, 3, Zinc, 1, Brass, 4, 200);
        addAlloyRecipes(Copper, 1, Nickel, 1, Cupronickel, 2, 100);
        addAlloyRecipes(Iron, 2, Nickel, 1, Invar, 3, 150);
        addAlloyRecipes(Tin, 9, Antimony, 1, SolderingAlloy, 10, 500);
        addAlloyRecipes(Lead, 4, Antimony, 1, BatteryAlloy, 5, 250);
        addAlloyRecipes(AntimatterMaterials.Gold, 1, Silver, 1, Electrum, 2, 100);
        addAlloyRecipes(Magnesium, 1, Aluminium, 2, Magnalium, 3, 150);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Redstone, 4), DUST.getMaterialIngredient(Copper, 1)).io(INGOT.get(RedAlloy, 1)).add("red_alloy_ingot", 50, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Redstone, 4), INGOT.getMaterialIngredient(Copper, 1)).io(INGOT.get(RedAlloy, 1)).add("red_alloy_ingot_1", 50, 16);
        //TODO compat for bluepower
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), DUST.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), INGOT.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);
        int ingotCount = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 2 : 1;
        AntimatterMaterialTypes.PLATE.all().forEach(m -> {
            if (!m.has(GT4RMaterialTags.NEEDS_BLAST_FURNACE) && m.has(AntimatterMaterialTypes.INGOT)){
                int euTick = m.has(RUBBERTOOLS) ? 16 : 32;
                ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(m, ingotCount), RecipeIngredient.of(GT4RData.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate", m.getMass() * ingotCount, euTick);
                if (m.has(RUBBERTOOLS) && m.has(DUST)){
                    ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(m, ingotCount), RecipeIngredient.of(GT4RData.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate_1", m.getMass() * ingotCount, euTick);
                }
            }
        });

    }

    private static void addAlloyRecipes(Material input1, int count1, Material input2, int count2, Material output, int countO, int duration){
        ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot", duration, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_1", duration, 16);
        ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_2", duration, 16);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_3", duration, 16);
    }
}
