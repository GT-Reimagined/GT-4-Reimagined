package org.gtreimagined.gt4r.loader;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.util.RegistryUtils;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;

import java.util.function.Consumer;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gt4r.data.Materials.*;

public class MaterialRecipeLoader {

    public static void loadRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        final CriterionTriggerInstance in = provider.hasSafeItem(GTTools.WRENCH.getTag());
        if (GTAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)){
            provider.shapeless(output, "amethyst_gem_convert", "gems", GEM.get(Amethyst, 1), RegistryUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_BLUEPOWER, "amethyst_gem")));
        }
        GT4RMaterialTags.HULL.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_hull", "hulls", GT4RMaterialTags.HULL.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'W', GTTools.WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        GT4RMaterialTags.TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_blade", "turbine_blades", GT4RMaterialTags.TURBINE_BLADE.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'H', GTTools.HAMMER.getTag(), 'F', GTTools.FILE.getTag()), " H ", "PPP", " F ");
        });
        GT4RMaterialTags.TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(GT4RMaterialTags.TURBINE_BLADE) && (m.has(BLOCK) || m == Carbon)){
                TagKey<Item> center = m == Carbon ? PLATE.getMaterialTag(Carbon) : BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_rotor", "turbine_rotors", GT4RMaterialTags.TURBINE_ROTOR.get(m), ImmutableMap.of('T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
            }

        });

        if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()) {
            GTMaterialTypes.DRILLBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_drillbit", "drillbits", GTMaterialTypes.DRILLBIT.get(m), ImmutableMap.of('H', GTTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
                }
            });
            GTMaterialTypes.CHAINSAWBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_chainsawbit", "chainsawbits", GTMaterialTypes.CHAINSAWBIT.get(m), ImmutableMap.of('H', GTTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel), 'R', GTMaterialTypes.RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
                }
            });
            GTMaterialTypes.WRENCHBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_wrenchbit", "wrenchbits", GTMaterialTypes.WRENCHBIT.get(m), ImmutableMap.of('H', GTTools.HAMMER.getTag(), 'P', plate, 'S', GTMaterialTypes.SCREW.getMaterialTag(Steel), 'R', GTMaterialTypes.RING.getMaterialTag(Steel), 's', GTTools.SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
                }
            });
        }
        GTMaterialTypes.BUZZSAW_BLADE.all().forEach(m -> {
            if (m != Steel && !GT4RConfig.GT5_ELECTRIC_TOOLS.get()) return;
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_buzzsaw_blade", "buzzsaw_blades", GTMaterialTypes.BUZZSAW_BLADE.get(m), ImmutableMap.of('H', GTTools.HAMMER.getTag(), 'P', plate, 'F', GTTools.FILE.getTag(), 'W', GTTools.WRENCH.getTag(), 'C', GTTools.WIRE_CUTTER.getTag()), "WPH", "P P", "FPC");
            }
        });

    }
}