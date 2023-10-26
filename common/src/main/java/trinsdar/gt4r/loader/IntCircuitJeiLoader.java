package trinsdar.gt4r.loader;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.data.TierMaps;

public class IntCircuitJeiLoader {
    public static void init(){
        Ingredient all = Ingredient.of(TierMaps.INT_CIRCUITS_ITEMS.values().toArray(new Item[0]));
        for (int i = 0; i < 25; i++){
            RecipeMaps.INT_CIRCUITS.RB().ii(all).io(new ItemStack(TierMaps.INT_CIRCUITS_ITEMS.get(i))).add("int_circuit_" + i,1);
        }
    }
}
