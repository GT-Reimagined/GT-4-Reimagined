package trinsdar.gt4r.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.data.AntimatterMaterials.Charcoal;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.WIRE_MILLING;

public class WiremillLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            if (t.getMaterial().has(INGOT)){
                RecipeIngredient ing = INGOT.getMaterialIngredient(t.getMaterial(),1);
                WIRE_MILLING.RB().ii(ing).io(stack).add(t.getId() + "_wire_vtiny",t.getMaterial().getMass()*2,24);
            }

            /*if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILLING.RB().ii(RecipeIngredient.of(wireItem,1)).io(WIRE_FINE.get(t.getMaterial(),4)).add((long)( t.getMaterial().getMass()*2.5),16);
            }*/
        });

        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Kanthal, 4)).io(new ItemStack(GT4RData.KanthalHeatingCoil)).add("kanthal_heating_coil",450, 12);
        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Nichrome, 5)).io(new ItemStack(GT4RData.NichromeHeatingCoil)).add("nichroome_heating_coil",600, 16);
        WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(Cupronickel, 3)).io(new ItemStack(GT4RData.CupronickelHeatingCoil)).add("cupronickel_heating_coil",300, 8);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Carbon, 8)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre",400, 2);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Charcoal, 8)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre_1",400, 2);
        WIRE_MILLING.RB().ii(DUST.getMaterialIngredient(Coal, 4)).io(new ItemStack(GTCoreItems.CarbonFibre)).add("carbon_fibre_2",400, 2);

    }
}
