package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import net.minecraft.world.item.ItemStack;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Water;
import static org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt4r.data.RecipeMaps.ORE_WASHER;

public class WasherLoader {
    public static void init() {
        GTMaterialTypes.CRUSHED_ORE.all().forEach(m -> {
            if (!m.has(GTMaterialTypes.PURIFIED_ORE)) return;
            ItemStack stoneDust = GTMaterialTypes.DUST.get(GTLibMaterials.Stone, 1);

            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            //Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ORE_WASHER.RB().fi(Water.getLiquid(1000)).ii(of(CRUSHED_ORE.get(m,1))).io(PURIFIED_ORE.get(m,1), TINY_DUST.get(aOreByProduct1,1), stoneDust).add(m.getId() + "_crushed_ore",200, 24);
        });
    }
}
