package org.gtreimagined.gt4r.loader.multi;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT_HOT;
import static org.gtreimagined.gt4r.data.RecipeMaps.VACUUM_FREEZING;

public class VacFreezer {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            VACUUM_FREEZING.RB().ii(INGOT_HOT.getMaterialIngredient(hi, 1))
                    .io(new ItemStack(ingot,1)).add(hi.getId() + "_hot_ingot", hi.getMass(), 120);
        });
    }
}
