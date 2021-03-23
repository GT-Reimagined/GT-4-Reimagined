package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.data.Materials;

import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.Materials.Redstone;
import static trinsdar.gt4r.data.Materials.Stone;
import static trinsdar.gt4r.data.RecipeMaps.HAMMERING;
import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.RecipeMaps.UNIVERSAL_MACERATING;

public class ForgeHammerLoader {
    public static void init(){
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != Gold && m != Iron && m != Diamond && m != Emerald && m != Lapis && m != Redstone) return;
            int multiplier = 1;
            RecipeIngredient ore = AntimatterIngredient.of(ORE.getMaterialTag(m),1), crushed = CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = CRUSHED.get(m,1);

            HAMMERING.RB().ii(ore).io(Utils.ca(m.getOreMulti() * multiplier, crushedStack)).add(16, 10);
            HAMMERING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1)).add(16, 10);
            HAMMERING.RB().ii(AntimatterIngredient.of(CRUSHED_PURIFIED.get(m,1))).io(DUST_PURE.get(m.getMacerateInto(), 1)).add(16, 10);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                HAMMERING.RB().ii(AntimatterIngredient.of(CRUSHED_CENTRIFUGED.get(m,1))).io(DUST.get(m.getMacerateInto(), 1)).add(16, 10);
            }
        });
        PLATE.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(m, 3)).io(PLATE.get(m, 2)).add(m.getMass(), 16);
        });
        HAMMERING.RB().ii(AntimatterIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.GRAVEL)).add(10, 16);
        HAMMERING.RB().ii(AntimatterIngredient.of(Tags.Items.STONE, 1)).io(new ItemStack(Items.COBBLESTONE)).add(10, 16);
        HAMMERING.RB().ii(AntimatterIngredient.of(Tags.Items.GRAVEL, 1)).io(new ItemStack(Items.SAND)).add(10, 16);
    }
}
