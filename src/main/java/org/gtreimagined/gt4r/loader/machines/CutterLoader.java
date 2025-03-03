package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.PLATE_CUTTER;
import static org.gtreimagined.gt4r.data.RecipeMaps.STEAM_CUTTER;

public class CutterLoader {
    public static void init(){
        PLATE.all().forEach(t -> {
            if (!t.has(BLOCK)) return;
            long duration = Math.max(t.getMass(), 1);
            int multiplier = 1;//mat.has(AntimatterMaterialTypes.GEM) ? 8 : 3;
            if (t == AntimatterMaterials.Diamond || t == AntimatterMaterials.NetherizedDiamond)
                multiplier = 5;
            int count = t.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
            PLATE_CUTTER.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(t), 1)).fi(AntimatterMaterials.Water.getLiquid(3)).io(PLATE.get(t,count)).add(t.getId() + "_plate",duration * 8 * multiplier, 30);
            STEAM_CUTTER.RB().ii(BLOCK.getMaterialIngredient(t, 1)).fi(AntimatterMaterials.Water.getLiquid(3)).io(PLATE.get(t, count)).add(t.getId() + "_plate", duration * 8 * multiplier * 4, 30);
        });
        BOLT.all().forEach(t -> {
            if (!t.has(ROD)) return;
            long duration = Math.max(t.getMass(), 1) * 4;
            PLATE_CUTTER.RB().ii(RecipeIngredient.of(ROD.getMaterialTag(t), 1)).fi(AntimatterMaterials.Water.getLiquid(3)).io(BOLT.get(t,4)).add(t.getId() + "_bolt",duration, 30);
        });
        PLATE_CUTTER.RB().ii(RecipeIngredient.of(Items.GLASS, 3)).fi(AntimatterMaterials.Water.getLiquid(3)).io(new ItemStack(Items.GLASS_PANE)).add("glass_pane",50, 8);

    }
}
