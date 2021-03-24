package trinsdar.gt4r.loader.machines;

import static muramasa.antimatter.Data.CRUSHED;
import static muramasa.antimatter.Data.CRUSHED_PURIFIED;
import static muramasa.antimatter.Data.NUGGET;
import static trinsdar.gt4r.data.RecipeMaps.STEAM_SMELTING;

public class ElectricFurnaceLoader {
    public static void init(){
        CRUSHED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            STEAM_SMELTING.RB().ii(CRUSHED.getMaterialIngredient(m, 1)).io(NUGGET.get(m.getDirectSmeltInto(), 10)).add(1200, 4);
        });
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (m.needsBlastFurnace()) return;
            if (!m.getDirectSmeltInto().has(NUGGET)) return;
            STEAM_SMELTING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(m, 1)).io(NUGGET.get(m.getDirectSmeltInto(), 10)).add(1200, 4);
        });
    }
}
