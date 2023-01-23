package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BATHING;

public class BathingLoader {
    public static void init(){
        ItemStack stoneDust = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);
        GT4RMaterialTags.BATHING_PERSULFATE.all().forEach(m -> {
            addBathRecipe(m, SodiumPersulfate, i(1.0, 0.7, 0.4), AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m, 1), AntimatterMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_PERSULFATE.getMapping(m), 1), stoneDust);
        });
        GT4RMaterialTags.BATHING_MERCURY.all().forEach(m -> {
            addBathRecipe(m, Mercury, i(1.0, 0.7, 0.4), AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m, 1), AntimatterMaterialTypes.DUST.get(GT4RMaterialTags.BATHING_MERCURY.getMapping(m), 1), stoneDust);
        });
        //addBathRecipe(Zinc, SodiumPersulfate, i(100, 70, 40), CRUSHED_PURIFIED.get(Zinc, 1), DUST.get(Zinc, 1), stoneDust);
        BATHING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(Wood), 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(1.0).add("paper",200);
        BATHING.RB().ii(RecipeIngredient.of(Items.SUGAR_CANE, 1)).fi(Water.getLiquid(100)).io(new ItemStack(Items.PAPER)).chances(1.0).add("paper_1",100);
        BATHING.RB().ii(RecipeIngredient.of(TagUtils.getItemTag(new ResourceLocation("minecraft", "wool")), 1)).fi(Chlorine.getGas(125)).io(new ItemStack(Items.WHITE_WOOL)).chances(1.0).add("white_wool",12);
        BATHING.RB().ii(GEM.getMaterialIngredient(Diamond, 1)).fi(Netherite.getLiquid(switchValues(36, 2250))).io(GEM.get(NetherizedDiamond, 1)).add("netherized_diamond",144);
    }

    private static long switchValues(long forge, long fabric){
        return AntimatterPlatformUtils.isForge() ? forge : fabric;
    }

    private static double[] i(double... doubles){
        return doubles;
    }

    private static void addBathRecipe(Material input, Material liquid, double[] chances, ItemStack... outputs){
        BATHING.RB().ii(RecipeIngredient.of(CRUSHED.getMaterialTag(input), 1)).fi(liquid.getLiquid(1000)).io(outputs).chances(chances).add(input.getId() + "_in_" + liquid.getId(),800);
    }
}
