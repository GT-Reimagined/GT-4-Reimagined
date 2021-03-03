package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.tags.Tag;
import trinsdar.gt4r.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.DUST_IMPURE;
import static muramasa.antimatter.material.MaterialTag.CALCITE2X;
import static muramasa.antimatter.material.MaterialTag.CALCITE3X;
import static trinsdar.gt4r.data.GregTechData.INT_CIRCUITS;
import static trinsdar.gt4r.data.GregTechData.STONE;
import static trinsdar.gt4r.data.GregTechData.TungstenGrindHead;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BLASTING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class ElectricBlasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        //Tag<Item> rockTag = getForgeItemTag()
        Material m = Aluminium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Silicon;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Steel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(Carbon, 1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(CoalCoke, 1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Titanium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        m = TungstenSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Tungsten,1)), of(INGOT.get(Steel,1))).io(INGOT_HOT.get(m, 2), DUST.get(DarkAsh, 4)).add(1700, 120, m.getBlastTemp());
        m = Tungsten;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Iridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Galena;
        BLASTING.RB().ii(of(DUST.get(m,2))).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1)).add(600, 120, 1500);
        m = Osmium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Chrome;
        BLASTING.RB().ii(of(DUST.get(m,1)), INT_CIRCUITS.get(1)).io(INGOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        m = Osmiridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Osmium,1)), of(INGOT.get(Iridium, 1))).io(INGOT_HOT.get(m, 2)).add(10000, 120, m.getBlastTemp());
        m = StainlessSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(10200, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron,6)), of(INGOT.get(Nickel, 1)), of(INGOT.get(Chrome, 1)), of(INGOT.get(Manganese, 1))).io(INGOT.get(m, 9)).add(10200, 120, m.getBlastTemp());
        m = Kanthal;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(5100, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron,1)), of(INGOT.get(Aluminium, 1)), of(INGOT.get(Chrome, 1))).io(INGOT_HOT.get(m, 3)).add(5100, 120, m.getBlastTemp());
        m = Nichrome;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10200, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Nickel,4)), of(INGOT.get(Chrome, 1)), INT_CIRCUITS.get(2)).io(INGOT_HOT.get(m, 5)).add(10200, 120, m.getBlastTemp());
    }
}
