package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.GEM;
import static trinsdar.gt4r.data.Materials.CoalCoke;
import static trinsdar.gt4r.data.Materials.Creosote;
import static trinsdar.gt4r.data.RecipeMaps.COKING;

public class CokeOven {
    public static void init(){
        COKING.RB().ii(AntimatterIngredient.of(Items.COAL)).io(GEM.get(CoalCoke, 1)).fo(Creosote.getLiquid(500)).add(720);
        COKING.RB().ii(AntimatterIngredient.of(Items.COAL_BLOCK)).io(BLOCK.get().get(CoalCoke).asStack()).fo(Creosote.getLiquid(4500)).add(1620);
        COKING.RB().ii(AntimatterIngredient.of(new ResourceLocation("minecraft", "logs"), 1)).io(new ItemStack(Items.CHARCOAL, 1)).fo(Creosote.getLiquid(250)).add(720);
    }
}
