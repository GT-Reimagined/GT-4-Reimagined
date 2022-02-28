package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.data.Materials;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BATHING;

public class BathingLoader {
    public static void init(){
        ItemStack stoneDust = DUST.get(Stone, 1);
        BATHING_PERSULFATE.all().forEach(m -> {
            addBathRecipe(m, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(m, 1), DUST.get(BATHING_MAP_PERSULFATE.get(m), 1), stoneDust);
        });
        BATHING_MERCURY.all().forEach(m -> {
            addBathRecipe(m, Mercury, i(100, 70, 40), CRUSHED_PURIFIED.get(m, 1), DUST.get(BATHING_MAP_MERCURY.get(m), 1), stoneDust);
        });
        //addBathRecipe(Zinc, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Zinc, 1), DUST.get(Zinc, 1), stoneDust);
        BATHING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(Wood), 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(100).add(200);
        BATHING.RB().ii(RecipeIngredient.of(Items.SUGAR_CANE, 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(100).add(100);
        BATHING.RB().ii(RecipeIngredient.of(TagUtils.getItemTag(new ResourceLocation("minecraft", "wool")), 1)).fi(Chlorine.getGas(125)).io(new ItemStack(Items.WHITE_WOOL)).chances(100).add(12);
        BATHING.RB().ii(GEM.getMaterialIngredient(Diamond, 1)).fi(Netherite.getLiquid(36)).io(GEM.get(NetherizedDiamond, 1)).add(144);
    }

    private static int[] i(int... ints){
        return ints;
    }

    private static void addBathRecipe(Material input, Material liquid, int[] chances, ItemStack... outputs){
        BATHING.RB().ii(RecipeIngredient.of(CRUSHED.getMaterialTag(input), 1)).fi(liquid.getLiquid(1000)).io(outputs).chances(chances).add(800);
    }
}
