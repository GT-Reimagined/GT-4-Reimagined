package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;
import trinsdar.gt4r.data.GT4RData;

import java.io.CharArrayReader;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.WIRE_FINE;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.WIRE_MILLING;

public class WiremillLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            RecipeIngredient ing = INGOT.getMaterialIngredient(t.getMaterial(),1);
            WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1)).io(stack).add(t.getMaterial().getMass()*2,24);
            /*if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILLING.RB().ii(AntimatterIngredient.of(wireItem,1)).io(WIRE_FINE.get(t.getMaterial(),4)).add((long)( t.getMaterial().getMass()*2.5),16);
            }*/
        });

        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Kanthal, 4)).io(new ItemStack(GT4RData.KanthalHeatingCoil)).add(450, 12);
        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Nichrome, 5)).io(new ItemStack(GT4RData.NichromeHeatingCoil)).add(600, 16);
        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Cupronickel, 3)).io(new ItemStack(GT4RData.CupronickelHeatingCoil)).add(300, 8);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Carbon, 8)).io(new ItemStack(GT4RData.CarbonFibre)).add(400, 2);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Charcoal, 8)).io(new ItemStack(GT4RData.CarbonFibre)).add(400, 2);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Coal, 4)).io(new ItemStack(GT4RData.CarbonFibre)).add(400, 2);

    }
}
