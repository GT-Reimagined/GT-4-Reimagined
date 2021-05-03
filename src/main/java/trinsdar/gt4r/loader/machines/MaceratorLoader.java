package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.data.Materials;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;

public class MaceratorLoader {
    public static void init() {
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != NetheriteScrap) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(ORE.getMaterialTag(m),1);
            RecipeIngredient crushed = RecipeIngredient.of(CRUSHED.getMaterialTag(m), 1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            ItemStack stoneDust = DUST.get(Stone, 1);

            //TODO better way to do this
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;
            Material oreByProduct3 = m.getByProducts().size() >= 3 ? m.getByProducts().get(2) : oreByProduct2;

            MACERATING.RB().ii(ore).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1), stoneDust).chances(100, 10 * multiplier * m.getByProductMulti(), 50).add(400, 2);
            MACERATING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct1, 1)).chances(100, 10).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.getMaterialTag(m), 1)).io(DUST_PURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct2, 1)).chances(100, 10).add(400, 2);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_CENTRIFUGED.getMaterialTag(m), 1)).io(DUST.get(m.getMacerateInto(), 1), DUST.get(oreByProduct3, 1)).chances(100, 10).add(400, 2);
            }
        });
        DUST.all().forEach(m -> {
            if (m.has(PLATE) && m != Wood){
                long duration = m.getMass();
                MACERATING.RB().ii(PLATE.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(INGOT)){
                long duration = m.getMass();
                MACERATING.RB().ii(INGOT.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(GEM)){
                long duration = m.getMass();
                MACERATING.RB().ii(GEM.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
        });
        MACERATING.RB().ii(RecipeIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.SAND)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Tags.Items.STONE, 1)).io(new ItemStack(Items.GRAVEL)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY_BALL, 1)).io(DUST_SMALL.get(Clay, 2)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY, 1)).io(DUST.get(Clay, 2)).add(30, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(DUST.get(Wood, 6)).add(400, 2);
    }
}
