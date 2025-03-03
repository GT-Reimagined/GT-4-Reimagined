package org.gtreimagined.gt4r.loader;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.data.RecipeMaps;

public class IntCircuitJeiLoader {
    public static void init(){
        Ingredient all = Ingredient.of(GTCoreItems.SELECTOR_TAG_ITEMS.values().toArray(new Item[0]));
        for (int i = 0; i < 25; i++){
            RecipeMaps.INT_CIRCUITS.RB().ii(all).io(new ItemStack(GTCoreItems.SELECTOR_TAG_ITEMS.get(i))).add("int_circuit_" + i,1);
        }
    }
}
