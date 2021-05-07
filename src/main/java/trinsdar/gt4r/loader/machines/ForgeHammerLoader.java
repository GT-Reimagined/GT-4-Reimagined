package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.GT4RConfig;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HAMMERING;

public class ForgeHammerLoader {
    public static void init(){
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != Gold && m != Iron && m != Diamond && m != Emerald && m != Lapis && m != Redstone) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(ORE.getMaterialTag(m),1), crushed = CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = CRUSHED.get(m,1);

            HAMMERING.RB().ii(ore).io(Utils.ca(m.getOreMulti() * multiplier, crushedStack)).add(16, 10);
            HAMMERING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1)).add(16, 10);
            HAMMERING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m,1))).io(DUST_PURE.get(m.getMacerateInto(), 1)).add(16, 10);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                HAMMERING.RB().ii(RecipeIngredient.of(CRUSHED_CENTRIFUGED.get(m,1))).io(DUST.get(m.getMacerateInto(), 1)).add(16, 10);
            }
        });
        PLATE.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int in = GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 3 : 1;
            int out = GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 2 : 1;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(m, in)).io(PLATE.get(m, out)).add(m.getMass(), 16);
        });
        HAMMERING.RB().ii(RecipeIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.GRAVEL)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.COBBLESTONE)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Tags.Items.GRAVEL, 1)).io(new ItemStack(Items.SAND)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(10, 16);
    }
}
