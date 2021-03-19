package trinsdar.gt4r.loader.machines.generator;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.RecipeMaps;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static trinsdar.gt4r.data.Materials.Ash;
import static trinsdar.gt4r.data.Materials.CoalCoke;
import static trinsdar.gt4r.data.Materials.DarkAsh;
import static trinsdar.gt4r.data.Materials.Lava;
import static trinsdar.gt4r.data.RecipeMaps.COAL_BOILERS;
import static trinsdar.gt4r.data.RecipeMaps.LAVA_BOILERS;

public class BronzeSteelBoilerHandler {
    public static void init(){
        COAL_BOILERS.RB().ii(AntimatterIngredient.of(Items.COAL, 1)).io(DUST.get(DarkAsh, 1)).add(160);
        COAL_BOILERS.RB().ii(AntimatterIngredient.of(Items.CHARCOAL, 1)).io(DUST.get(Ash, 1)).add(160);
        COAL_BOILERS.RB().ii(AntimatterIngredient.of(GEM.get(CoalCoke), 1)).io(DUST.get(DarkAsh, 1)).add(320);
        LAVA_BOILERS.RB().fi(Lava.getLiquid(2)).add(8);
    }
}
