package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;


import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.ORE;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HAMMERING;
import static trinsdar.gt4r.data.RecipeMaps.UNIVERSAL_MACERATING;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;

public class MaceratorLoader {
    public static void init() {
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != Gold && m != Iron && m != Diamond && m != Emerald && m != Lapis && m != Redstone) return;
            int multiplier = 1;
            LazyValue<AntimatterIngredient> ore = AntimatterIngredient.of(ORE.getMaterialTag(m),1);
            LazyValue<AntimatterIngredient> crushed = CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            ItemStack stoneDust = DUST.get(Stone, 1);
            ItemStack dustStack = DUST.get(Stone, 1);

            //TODO better way to do this
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            Material aOreByProduct3 = m.getByProducts().size() >= 3 ? m.getByProducts().get(2) : aOreByProduct2;

            UNIVERSAL_MACERATING.RB().ii(ore).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack), DUST.get(aOreByProduct1, 1), stoneDust).chances(100, 10 * multiplier * m.getByProductMulti(), 50).add(400, 2);
            //MACERATING.RB().ii(ore).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack)).add(400, 2);
            UNIVERSAL_MACERATING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1), DUST.get(aOreByProduct1, 1)).chances(100, 10).add(400, 2);
            UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(CRUSHED_PURIFIED.get(m, 1))).io(DUST_PURE.get(m.getMacerateInto(), 1), DUST.get(aOreByProduct2, 1)).chances(100, 10).add(400, 2);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(CRUSHED_CENTRIFUGED.get(m,1))).io(DUST.get(m.getMacerateInto(), 1), DUST.get(aOreByProduct3, 1)).chances(100, 10).add(400, 2);
            }
        });
    }
}
