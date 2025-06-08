package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.material.MaterialTypeItem;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;

import static org.gtreimagined.gtlib.Ref.L;
import static org.gtreimagined.gtlib.Ref.U;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Lava;
import static org.gtreimagined.gtlib.material.MaterialTags.MOLTEN;
import static org.gtreimagined.gt4r.data.Materials.Obsidian;
import static org.gtreimagined.gt4r.data.RecipeMaps.SMELTER;
import static org.gtreimagined.gt4r.data.RecipeMaps.SMELTER_COILS;

public class SmelterLoader {
    public static void init() {
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RItems.CupronickelHeatingCoil, 1)).add("cupronickel_heating_coil",0, 0, 250);
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RItems.KanthalHeatingCoil, 1)).add("kanthal_heating_coil",0, 0, 500);
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RItems.NichromeHeatingCoil, 1)).add("nichrome_heating_coil",0, 0, 750);
        MaterialTypeItem<?>[] items = new MaterialTypeItem<?>[]{INGOT, NUGGET, PLATE, DENSE_PLATE, ROD, RING, FOIL, BOLT, SCREW, GEAR, SMALL_GEAR, WIRE_FINE, ROTOR};
        for (MaterialTypeItem<?> item : items) {
            item.all().forEach(m -> {
                add(m, item, item.getUnitValue());
            });
        }
        DUST.all().forEach(m -> {
            if (m.has(LIQUID) && m.has(MOLTEN) && !m.has(GT4RMaterialTags.NEEDS_BLAST_FURNACE)){
                add(m, DUST, DUST.getUnitValue());
            }
        });
        addLava(Obsidian, PLATE, PLATE.getUnitValue());
        addLava(Obsidian, DUST, DUST.getUnitValue());
    }

    private static void add(Material m, MaterialTypeItem<?> i, long materialAmount) {
        if (!m.has(GTMaterialTypes.LIQUID)) return;
        int amount = (int) ((L * materialAmount) / U);
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }

    private static void addLava(Material m, MaterialTypeItem<?> i, long materialAmount) {
        long flUnit = 111;
        int amount = (int) ((flUnit * materialAmount) / U);
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(Lava.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }
}
