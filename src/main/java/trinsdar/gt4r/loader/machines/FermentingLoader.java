package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.Biomass;
import static trinsdar.gt4r.data.Materials.Honey;
import static trinsdar.gt4r.data.Materials.Methane;
import static trinsdar.gt4r.data.RecipeMaps.FERMENTING;

public class FermentingLoader {
    public static void init(){
        FERMENTING.RB().fi(new FluidStack(Fluids.WATER, 1000)).ii(RecipeIngredient.of(GT4RData.Biochaff, 1)).fo(Biomass.getLiquid(1000)).add(128, 16);
        FERMENTING.RB().fi(Honey.getLiquid(1000)).ii(RecipeIngredient.of(GT4RData.Biochaff, 1)).fo(Biomass.getLiquid(1500)).add(128, 16);
        FERMENTING.RB().fi(Biomass.getLiquid(6)).ii(INT_CIRCUITS.get(0)).fo(Methane.getGas(4)).add(20, 16);
    }
}
