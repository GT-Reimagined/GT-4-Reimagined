package org.gtreimagined.gt4r.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.COKING;
import static org.gtreimagined.gt4r.data.RecipeMaps.PYROLYSING;

public class CokePyrolysisOven {
    public static void init(){
        COKING.RB().ii(RecipeIngredient.of(Items.COAL, 1)).io(GEM.get(CoalCoke, 1)).fo(Creosote.getLiquid(500)).add("coal_coke",720);
        COKING.RB().ii(RecipeIngredient.of(Items.COAL_BLOCK, 1)).io(BLOCK.get().get(CoalCoke).asStack()).fo(Creosote.getLiquid(4500)).add("coal_coke_block",1620);
        COKING.RB().ii(RecipeIngredient.of(new ResourceLocation("minecraft", "logs"), 1)).io(new ItemStack(Items.CHARCOAL, 1)).fo(Creosote.getLiquid(250)).add("charcoal",720);
        PYROLYSING.RB().ii(RecipeIngredient.of(new ResourceLocation("minecraft", "logs"), 16)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodGas.getGas(1500)).add("charcoal",2400, 32);
    }
}
