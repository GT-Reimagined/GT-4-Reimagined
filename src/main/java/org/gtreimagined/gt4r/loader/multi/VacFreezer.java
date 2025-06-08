package org.gtreimagined.gt4r.loader.multi;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.INGOT;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.HOT_INGOT;
import static org.gtreimagined.gt4r.data.RecipeMaps.VACUUM_FREEZING;

public class VacFreezer {
    public static void init() {
        HOT_INGOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            VACUUM_FREEZING.RB().ii(HOT_INGOT.getMaterialIngredient(hi, 1))
                    .io(new ItemStack(ingot,1)).add(hi.getId() + "_hot_ingot", hi.getMass(), 120);
        });
    }
}
