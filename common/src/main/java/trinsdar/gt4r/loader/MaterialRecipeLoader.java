package trinsdar.gt4r.loader;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static trinsdar.gt4r.data.Materials.*;

public class MaterialRecipeLoader {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        final CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag());
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)){
            provider.shapeless(output, "amethyst_gem_convert", "gems", GEM.get(Amethyst, 1), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_BLUEPOWER, "amethyst_gem")));
        }
        GT4RMaterialTags.HULL.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_hull", "hulls", GT4RMaterialTags.HULL.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        GT4RMaterialTags.TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_blade", "turbine_blades", GT4RMaterialTags.TURBINE_BLADE.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), " H ", "PPP", " F ");
        });
        GT4RMaterialTags.TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(GT4RMaterialTags.TURBINE_BLADE) && (m.has(BLOCK) || m == Carbon)){
                TagKey<Item> center = m == Carbon ? PLATE.getMaterialTag(Carbon) : BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_rotor", "turbine_rotors", GT4RMaterialTags.TURBINE_ROTOR.get(m), ImmutableMap.of('T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
            }

        });
        BLOCK.all().forEach(m -> {
            if (m.has(INGOT)){
                provider.addStackRecipe(output, GT4RRef.ID, m.getId() + "_block", "blocks", BLOCK.get().get(m).asStack(), ImmutableMap.of('I', INGOT.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"ingot_" + m.getId() + "_from_block", "blocks", INGOT.get(m, 9), BLOCK.getMaterialTag(m));
            } else if (m.has(GEM)){
                provider.addStackRecipe(output, GT4RRef.ID, m.getId() + "_block", "blocks", BLOCK.get().get(m).asStack(), ImmutableMap.of('I', GEM.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"gem_" + m.getId() + "_from_block", "blocks", GEM.get(m, 9), BLOCK.getMaterialTag(m));
            }
        });
        if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()) {
            AntimatterMaterialTypes.DRILLBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_drillbit", "drillbits", AntimatterMaterialTypes.DRILLBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
                }
            });
            AntimatterMaterialTypes.CHAINSAWBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_chainsawbit", "chainsawbits", AntimatterMaterialTypes.CHAINSAWBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
                }
            });
            AntimatterMaterialTypes.WRENCHBIT.all().forEach(m -> {
                if (m.has(PLATE) || m.has(GEM)){
                    TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                    provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_wrenchbit", "wrenchbits", AntimatterMaterialTypes.WRENCHBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 's', AntimatterDefaultTools.SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
                }
            });
        }
        AntimatterMaterialTypes.BUZZSAW_BLADE.all().forEach(m -> {
            if (m != Steel && !GT4RConfig.GT5_ELECTRIC_TOOLS.get()) return;
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_buzzsaw_blade", "buzzsaw_blades", AntimatterMaterialTypes.BUZZSAW_BLADE.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'F', AntimatterDefaultTools.FILE.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag(), 'C', AntimatterDefaultTools.WIRE_CUTTER.getTag()), "WPH", "P P", "FPC");
            }
        });

    }
}