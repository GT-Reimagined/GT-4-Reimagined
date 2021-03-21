package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.Energium;
import static trinsdar.gt4r.data.Materials.Glowstone;
import static trinsdar.gt4r.data.Materials.Wood;
import static trinsdar.gt4r.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        INGOT.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(AntimatterIngredient.of(INGOT.getMaterialTag(ingot),9)).io(BLOCK.get().get(ingot).asStack(1)).add(Math.max(40,ingot.getMass()*2), 16);
            }
        });
        GEM.all().forEach(gem -> {
            if (gem.has(BLOCK)) {
                COMPRESSING.RB().ii(AntimatterIngredient.of(GEM.getMaterialTag(gem),9)).io(BLOCK.get().get(gem).asStack(1)).add(Math.max(40,gem.getMass()*2), 16);
            }
            if (gem.has(PLATE)){
                COMPRESSING.RB().ii(AntimatterIngredient.of(DUST.getMaterialTag(gem),9)).io(PLATE.get(gem, 1)).add(Math.max(40,gem.getMass()*2), 16);
            }
        });
        COMPRESSING.RB().ii(AntimatterIngredient.of(GT4RData.CarbonMesh, 1)).io(new ItemStack(GT4RData.CarbonPlate)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(GT4RData.MixedMetal, 1)).io(new ItemStack(GT4RData.AdvancedAlloy)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(Items.SNOWBALL, 1)).io(new ItemStack(Items.SNOW_BLOCK)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Glowstone, 4)).io(new ItemStack(Items.GLOWSTONE)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(Items.SNOW_BLOCK, 1)).io(new ItemStack(Items.ICE)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(GT4RData.CoalBall, 1)).io(new ItemStack(GT4RData.CompressedCoalBall)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(Items.SAND, 4)).io(new ItemStack(Items.SANDSTONE)).add(400, 2);
        COMPRESSING.RB().ii(AntimatterIngredient.of(Items.RED_SAND, 4)).io(new ItemStack(Items.RED_SANDSTONE)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Energium, 9)).io(new ItemStack(GT4RData.EnergyCrystal)).add(400, 2);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Wood, 8)).io(PLATE.get(Wood, 1)).add(400, 2);
    }
}
