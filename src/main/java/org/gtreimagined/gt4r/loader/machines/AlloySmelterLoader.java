package org.gtreimagined.gt4r.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.gtreimagined.gtcore.GTCoreConfig;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreMaterials;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.ALLOY_SMELTER;

public class AlloySmelterLoader {
    public static void init(){
        addAlloyRecipes(ImmutableMap.of(Tetrahedrite, 3, Tin, 1), Bronze, 3, "bronze_ingot_from_tetrahedrite");
        addAlloyRecipes(ImmutableMap.of(Tetrahedrite, 3, Zinc, 1), Brass, 3, "brass_ingot_from_tetrahedrite");
        addAlloyRecipes(ImmutableMap.of(Copper, 3, Tin, 1), Bronze);
        addAlloyRecipes(ImmutableMap.of(Copper, 3, Zinc, 1), Brass);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Nickel, 1), Cupronickel);
        addAlloyRecipes(ImmutableMap.of(Iron, 2, Nickel, 1), Invar);
        addAlloyRecipes(ImmutableMap.of(Tin, 9, Antimony, 1), SolderingAlloy);
        addAlloyRecipes(ImmutableMap.of(Lead, 4, Antimony, 1), BatteryAlloy);
        addAlloyRecipes(ImmutableMap.of(Gold, 1, Silver, 1), Electrum);
        addAlloyRecipes(ImmutableMap.of(Magnesium, 1, Aluminium, 2), Magnalium);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Redstone, 4), RedAlloy, 1);
        addAlloyRecipes(ImmutableMap.of(Lead, 1, Redstone, 4), GTCoreMaterials.LeadedRedstone, 1);
        //TODO compat for bluepower
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), DUST.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);
        //ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Nikolite, 4), INGOT.getMaterialIngredient(Copper)).io(INGOT.get(BlueAlloy, 1)).add(50, 16);
        int ingotCount = GTCoreConfig.LOSSY_PART_CRAFTING.get() ? 2 : 1;
        AntimatterMaterialTypes.PLATE.all().forEach(m -> {
            if (!m.has(GT4RMaterialTags.NEEDS_BLAST_FURNACE) && m.has(AntimatterMaterialTypes.INGOT)){
                int euTick = m.has(RUBBERTOOLS) ? 16 : 32;
                ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(m, ingotCount), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate", m.getMass() * ingotCount, euTick);
                if (m.has(RUBBERTOOLS) && m.has(DUST)){
                    ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(m, ingotCount), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate_1", m.getMass() * ingotCount, euTick);
                }
            }
        });

    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output){
        addAlloyRecipes(inputs, output, inputs.values().stream().mapToInt(i -> i).sum(), output.getId() + "_ingot");
    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output, int amount){
        addAlloyRecipes(inputs, output, amount, output.getId() + "_ingot");
    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output, int amount, String id){
        if (inputs.size() > 1){
            List<Ingredient> ingredients = new ArrayList<>();
            inputs.forEach((m, i) -> {
                List<TagKey<Item>> tags = new ArrayList<>();
                if (m.has(DUST)){
                    tags.add(DUST.getMaterialTag(m));
                }
                if (m.has(INGOT)) {
                    tags.add(INGOT.getMaterialTag(m));
                }
                ingredients.add(RecipeIngredient.of(i, tags.toArray(TagKey[]::new)));
            });
            ALLOY_SMELTER.RB().ii(ingredients).io(INGOT.get(output, amount)).add(id, 50L * amount, 16);
        }
    }
}
