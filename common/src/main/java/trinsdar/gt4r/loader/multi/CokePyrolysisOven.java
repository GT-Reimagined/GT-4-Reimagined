package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;

import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static trinsdar.gt4r.data.Materials.CoalCoke;
import static trinsdar.gt4r.data.Materials.Creosote;
import static trinsdar.gt4r.data.Materials.WoodGas;
import static trinsdar.gt4r.data.RecipeMaps.COKING;
import static trinsdar.gt4r.data.RecipeMaps.PYROLYSING;

public class CokePyrolysisOven {
    public static void init(){
        COKING.RB().ii(RecipeIngredient.of(Items.COAL, 1)).io(GEM.get(CoalCoke, 1)).fo(Creosote.getLiquid(500)).add(720);
        COKING.RB().ii(RecipeIngredient.of(Items.COAL_BLOCK, 1)).io(BLOCK.get().get(CoalCoke).asStack()).fo(Creosote.getLiquid(4500)).add(1620);
        COKING.RB().ii(RecipeIngredient.of(new ResourceLocation("minecraft", "logs"), 1)).io(new ItemStack(Items.CHARCOAL, 1)).fo(Creosote.getLiquid(250)).add(720);
        PYROLYSING.RB().ii(RecipeIngredient.of(new ResourceLocation("minecraft", "logs"), 16)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodGas.getGas(1500)).add(2400, 32);
    }
}
