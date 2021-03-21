package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.Tags;


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
            LazyValue<AntimatterIngredient> crushed = AntimatterIngredient.of(CRUSHED.getMaterialTag(m), 1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            ItemStack stoneDust = DUST.get(Stone, 1);

            //TODO better way to do this
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;
            Material oreByProduct3 = m.getByProducts().size() >= 3 ? m.getByProducts().get(2) : oreByProduct2;

            UNIVERSAL_MACERATING.RB().ii(ore).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1), stoneDust).chances(100, 10 * multiplier * m.getByProductMulti(), 50).add(400, 2);
            UNIVERSAL_MACERATING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct1, 1)).chances(100, 10).add(400, 2);
            UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(CRUSHED_PURIFIED.getMaterialTag(m), 1)).io(DUST_PURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct2, 1)).chances(100, 10).add(400, 2);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(CRUSHED_CENTRIFUGED.getMaterialTag(m), 1)).io(DUST.get(m.getMacerateInto(), 1), DUST.get(oreByProduct3, 1)).chances(100, 10).add(400, 2);
            }
        });
        DUST.all().forEach(m -> {
            if (m.has(PLATE) && m != Wood){
                long duration = m.getMass();
                UNIVERSAL_MACERATING.RB().ii(PLATE.getMaterialIngredient(m)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(INGOT)){
                long duration = m.getMass();
                UNIVERSAL_MACERATING.RB().ii(INGOT.getMaterialIngredient(m)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(GEM)){
                long duration = m.getMass();
                UNIVERSAL_MACERATING.RB().ii(GEM.getMaterialIngredient(m)).io(DUST.get(m, 1)).add(duration, 4);
            }
        });
        UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.SAND)).add(400, 2);
        UNIVERSAL_MACERATING.RB().ii(AntimatterIngredient.of(Tags.Items.STONE, 1)).io(new ItemStack(Items.GRAVEL)).add(400, 2);
    }
}
