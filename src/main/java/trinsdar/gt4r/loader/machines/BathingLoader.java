package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.RecipeMaps;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BATHING;

public class BathingLoader {
    public static void init(){
        ItemStack stoneDust = DUST.get(Stone, 1);
        addBathRecipe(Copper, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Copper, 1), DUST.get(Copper, 1), stoneDust);
        addBathRecipe(Gold, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Gold, 1), DUST.get(Copper, 1), stoneDust);
        addBathRecipe(Iron, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Iron, 1), DUST.get(Nickel, 1), stoneDust);
        addBathRecipe(Sphalerite, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Sphalerite, 1), DUST.get(Zinc, 1), stoneDust);
        addBathRecipe(Tetrahedrite, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Tetrahedrite, 1), DUST.get(Tetrahedrite, 1), stoneDust);
        addBathRecipe(Tin, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Tin, 1), DUST.get(Zinc, 1), stoneDust);
        addBathRecipe(Platinum, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Platinum, 1), DUST.get(Nickel, 1), stoneDust);
        addBathRecipe(Zinc, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Zinc, 1), DUST.get(Zinc, 1), stoneDust);
        addBathRecipe(Galena, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Galena, 1), DUST.get(Silver, 1), stoneDust);
        addBathRecipe(Tungstate, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Tungstate, 1), DUST.get(Silver, 1), stoneDust);
        addBathRecipe(Gold, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Gold, 1), DUST.get(Gold, 1), stoneDust);
        addBathRecipe(Iridium, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Iridium, 1), DUST.get(Platinum, 1), stoneDust);
        addBathRecipe(Copper, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Copper, 1), DUST.get(Gold, 1), stoneDust);
        addBathRecipe(Platinum, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(Platinum, 1), DUST.get(Platinum, 1), stoneDust);
        BATHING.RB().ii(AntimatterIngredient.of(DUST.getMaterialTag(Wood), 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(100).add(200);
        BATHING.RB().ii(AntimatterIngredient.of(Items.SUGAR_CANE, 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(100).add(100);
        BATHING.RB().ii(AntimatterIngredient.of(Items.BLACK_WOOL, 1)).fi(Chlorine.getLiquid(125)).io(new ItemStack(Items.WHITE_WOOL)).chances(100).add(12);
    }

    private static int[] i(int... ints){
        return ints;
    }

    private static void addBathRecipe(Material input, Material liquid, int[] chances, ItemStack... outputs){
        BATHING.RB().ii(AntimatterIngredient.of(CRUSHED.getMaterialTag(input), 1)).fi(liquid.getLiquid(1000)).io(outputs).chances(chances).add(800);
    }
}
