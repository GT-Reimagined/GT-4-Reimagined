package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.pipe.types.Wire;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.data.GT4RItems;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.DUST;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.INGOT;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Charcoal;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Coal;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.WIRE_MILL;

public class WiremillLoader {
    public static void init() {
        GTAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            if (t.getMaterial().has(INGOT)){
                RecipeIngredient ing = INGOT.getMaterialIngredient(t.getMaterial(),1);
                WIRE_MILL.RB().ii(ing).io(stack).add(t.getId() + "_wire_vtiny",t.getMaterial().getMass()*2,24);
            }

            /*if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILLING.RB().ii(RecipeIngredient.of(wireItem,1)).io(WIRE_FINE.get(t.getMaterial(),4)).add((long)( t.getMaterial().getMass()*2.5),16);
            }*/
        });

        WIRE_MILL.RB().ii(INGOT.getMaterialIngredient(Kanthal, 4)).io(new ItemStack(GT4RItems.KanthalHeatingCoil)).add("kanthal_heating_coil",450, 12);
        WIRE_MILL.RB().ii(INGOT.getMaterialIngredient(Nichrome, 5)).io(new ItemStack(GT4RItems.NichromeHeatingCoil)).add("nichroome_heating_coil",600, 16);
        WIRE_MILL.RB().ii(INGOT.getMaterialIngredient(Cupronickel, 3)).io(new ItemStack(GT4RItems.CupronickelHeatingCoil)).add("cupronickel_heating_coil",300, 8);
        WIRE_MILL.RB().ii(DUST.getMaterialIngredient(Carbon, 8)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre",400, 2);
        WIRE_MILL.RB().ii(DUST.getMaterialIngredient(Charcoal, 8)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre_1",400, 2);
        WIRE_MILL.RB().ii(DUST.getMaterialIngredient(Coal, 4)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre_2",400, 2);

    }
}
