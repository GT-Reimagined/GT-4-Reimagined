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
            provider.shapeless(output, "amethyst_gem_convert", "gems", "has_gem", provider.hasSafeItem(GEM.getMaterialTag(Amethyst)), GEM.get(Amethyst, 1), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(GT4RRef.MOD_BLUEPOWER, "amethyst_gem")));
        }
        GT4RMaterialTags.HULL.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_hull", "hulls", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), GT4RMaterialTags.HULL.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        GT4RMaterialTags.TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_blade", "turbine_blades", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), GT4RMaterialTags.TURBINE_BLADE.get(m), ImmutableMap.of('P', PLATE.getMaterialTag(m), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), " H ", "PPP", " F ");
        });
        GT4RMaterialTags.TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(GT4RMaterialTags.TURBINE_BLADE) && (m.has(BLOCK) || m == Carbon)){
                TagKey<Item> center = m == Carbon ? PLATE.getMaterialTag(Carbon) : BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_turbine_rotor", "turbine_rotors", "has_turbine_blade", provider.hasSafeItem(GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m)), GT4RMaterialTags.TURBINE_ROTOR.get(m), ImmutableMap.of('T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
            }

        });
        BLOCK.all().forEach(m -> {
            if (m.has(INGOT)){
                provider.addStackRecipe(output, GT4RRef.ID, m.getId() + "_block", "blocks", "has_ingot", provider.hasSafeItem(INGOT.getMaterialTag(m)), BLOCK.get().get(m).asStack(), ImmutableMap.of('I', INGOT.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"ingot_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(BLOCK.getMaterialTag(m)), INGOT.get(m, 9), BLOCK.getMaterialTag(m));
            } else if (m.has(GEM)){
                provider.addStackRecipe(output, GT4RRef.ID, m.getId() + "_block", "blocks", "has_gem", provider.hasSafeItem(GEM.getMaterialTag(m)), BLOCK.get().get(m).asStack(), ImmutableMap.of('I', GEM.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"gem_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(BLOCK.getMaterialTag(m)), GEM.get(m, 9), BLOCK.getMaterialTag(m));
            }
        });
        AntimatterMaterialTypes.DRILLBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_drillbit", "drillbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.DRILLBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
            }
        });
        AntimatterMaterialTypes.CHAINSAWBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_chainsawbit", "chainsawbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.CHAINSAWBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', PLATE.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
            }
        });
        AntimatterMaterialTypes.WRENCHBIT.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_wrenchbit", "wrenchbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.WRENCHBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 's', AntimatterDefaultTools.SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
            }
        });
        AntimatterMaterialTypes.BUZZSAW_BLADE.all().forEach(m -> {
            if (m.has(PLATE) || m.has(GEM)){
                TagKey<?> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : GEM.getMaterialTag(m);
                provider.addItemRecipe(output, GT4RRef.ID, m.getId() + "_buzzsaw_blade", "buzzsaw_blades", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.BUZZSAW_BLADE.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'F', AntimatterDefaultTools.FILE.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag(), 'C', AntimatterDefaultTools.WIRE_CUTTER.getTag()), "WPH", "P P", "FPC");
            }
        });
        MaterialTags.TOOLS.all().forEach(m -> {
            if (!m.has(INGOT) && !m.has(GEM)) return;
            TagKey<Item> plate = m.has(PLATE) ? PLATE.getMaterialTag(m) : m.has(INGOT) ? INGOT.getMaterialTag(m) : GEM.getMaterialTag(m);
            TagKey<Item> ingotGem = m.has(INGOT) ? INGOT.getMaterialTag(m) : GEM.getMaterialTag(m);
            if (m.has(PICKAXE_HEAD)) {
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_pickaxe_head", "antimatter_dusts",
                        "has_wrench", in, PICKAXE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PII", "F H");
            }
            if (m.has(AXE_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_axe_head", "antimatter_dusts",
                        "has_wrench", in, AXE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PIH", "P  ", "F  ");
            }
            if (m.has(SHOVEL_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_shovel_head", "antimatter_dusts",
                        "has_wrench", in, SHOVEL_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "FPH");
            }
            if (m.has(SWORD_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_sword_head", "antimatter_dusts",
                        "has_wrench", in, SWORD_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), " P ", "FPH");
            }
            if (m.has(HOE_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_hoe_head", "antimatter_dusts",
                        "has_wrench", in, HOE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PIH", "F  ");
            }
            if (m.has(HAMMER_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_hammer_head", "antimatter_dusts",
                        "has_wrench", in, HAMMER_HEAD.get(m,1), of('I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag()), "II ", "IIH", "II ");
            }
            if (m.has(SAW_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_saw_head", "antimatter_dusts",
                        "has_wrench", in, SAW_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PP", "FH");
            }
            if (m.has(FILE_HEAD)){
                provider.addStackRecipe(output, GT4RRef.ANTIMATTER, m.getId() + "_file_head", "antimatter_dusts",
                        "has_wrench", in, FILE_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "FPH", " P ");
            }
        });

    }
}