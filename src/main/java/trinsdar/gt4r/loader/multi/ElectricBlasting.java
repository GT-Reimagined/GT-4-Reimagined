package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.Utils;
import trinsdar.gt4r.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.DUST_IMPURE;
import static muramasa.antimatter.material.MaterialTag.CALCITE2X;
import static muramasa.antimatter.material.MaterialTag.CALCITE3X;
import static trinsdar.gt4r.data.GregTechData.INT_CIRCUITS;
import static trinsdar.gt4r.data.GregTechData.STONE;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BLASTING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class ElectricBlasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        CRUSHED.all().forEach(m -> {
            boolean needsBF = m.needsBlastFurnace() || m.getDirectSmeltInto().needsBlastFurnace();
            if (!m.has(ORE) || !m.has(INGOT)) return;
            Item crushed = CRUSHED.get(m);
            Item dust = DUST.get(m);
            AntimatterIngredient ore = ORE.get().get(m, STONE).asIngredient();
            ItemStack ingot = m.hasDirectSmeltInto() ? INGOT.get(m.getDirectSmeltInto(), 1) : INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == m.getSmeltInto() ? ingot : INGOT.get(m.getSmeltInto(), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 4, 1) * m.getBlastTemp();

                ItemStack blastOut = m.getBlastTemp() > 1750 && m.getSmeltInto().has(INGOT_HOT) ? INGOT_HOT.get(m.getSmeltInto(), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration/4, 120, m.getBlastTemp());
                BLASTING.RB().ii(CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(CRUSHED_CENTRIFUGED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
                BLASTING.RB().ii(DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add(aBlastDuration, 120, m.getBlastTemp());
            }

            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3 * m.getSmeltingMulti(), ingot);
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * mixedOreYield * m.getSmeltingMulti(), ingot), darkAsh).add(ingot.getCount() * 500L, 120, 1500);
            }
        });
    }
}
