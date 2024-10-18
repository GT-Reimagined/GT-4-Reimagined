package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.GT4RMaterialTags;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.Ref.U;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE_DENSE;
import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static muramasa.antimatter.material.MaterialTags.MOLTEN;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class SmelterLoader {
    public static void init() {
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RData.CupronickelHeatingCoil, 1)).add("cupronickel_heating_coil",0, 0, 250);
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RData.KanthalHeatingCoil, 1)).add("kanthal_heating_coil",0, 0, 500);
        SMELTER_COILS.RB().ii(RecipeIngredient.of(GT4RData.NichromeHeatingCoil, 1)).add("nichrome_heating_coil",0, 0, 750);
        MaterialTypeItem<?>[] items = new MaterialTypeItem<?>[]{INGOT, NUGGET, PLATE, PLATE_DENSE, ROD, ROD_LONG, RING, FOIL, BOLT, SCREW, GEAR, GEAR_SMALL, WIRE_FINE, ROTOR};
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
        addLava(Obsidian, ROD_LONG, ROD_LONG.getUnitValue());
        addLava(Obsidian, PLATE, PLATE.getUnitValue());
        addLava(Obsidian, DUST, DUST.getUnitValue());
    }

    private static void add(Material m, MaterialTypeItem<?> i, long materialAmount) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = //(long) (L * ratio);
                (L * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }

    private static void addLava(Material m, MaterialTypeItem<?> i, long materialAmount) {
        long flUnit = AntimatterPlatformUtils.INSTANCE.isFabric() ? L : 111;
        long amount = //(long) (L * ratio);
                (flUnit * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(Lava.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }
}
