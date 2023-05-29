package trinsdar.gt4r.loader;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static trinsdar.gt4r.data.GT4RMaterialTags.*;
import static trinsdar.gt4r.data.Materials.*;

public class MaterialRecipeLoader {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        final CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag());
        if (AntimatterAPI.isModLoaded(Ref.MOD_BLUEPOWER)){
            provider.shapeless(output, "amethyst_gem_convert", "gems", "has_gem", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(Amethyst)), AntimatterMaterialTypes.GEM.get(Amethyst, 1), AntimatterPlatformUtils.getItemFromID(new ResourceLocation(Ref.MOD_BLUEPOWER, "amethyst_gem")));
        }
        GT4RMaterialTags.HULL.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_hull", "hulls", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), GT4RMaterialTags.HULL.get(m), ImmutableMap.of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PPP", "PWP", "PPP");
        });
        GT4RMaterialTags.TURBINE_BLADE.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_blade", "turbine_blades", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), GT4RMaterialTags.TURBINE_BLADE.get(m), ImmutableMap.of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), " H ", "PPP", " F ");
        });
        GT4RMaterialTags.TURBINE_ROTOR.all().forEach(m -> {
            if (m.has(GT4RMaterialTags.TURBINE_BLADE) && (m.has(AntimatterMaterialTypes.BLOCK) || m == Carbon)){
                TagKey<Item> center = m == Carbon ? AntimatterMaterialTypes.PLATE.getMaterialTag(Carbon) : AntimatterMaterialTypes.BLOCK.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_turbine_rotor", "turbine_rotors", "has_turbine_blade", provider.hasSafeItem(GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m)), GT4RMaterialTags.TURBINE_ROTOR.get(m), ImmutableMap.of('T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(m), 'C', center), "TTT", "TCT", "TTT");
            }

        });
        AntimatterMaterialTypes.BLOCK.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.INGOT)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_block", "blocks", "has_ingot", provider.hasSafeItem(AntimatterMaterialTypes.INGOT.getMaterialTag(m)), AntimatterMaterialTypes.BLOCK.get().get(m).asStack(), ImmutableMap.of('I', AntimatterMaterialTypes.INGOT.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"ingot_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(AntimatterMaterialTypes.BLOCK.getMaterialTag(m)), AntimatterMaterialTypes.INGOT.get(m, 9), AntimatterMaterialTypes.BLOCK.getMaterialTag(m));
            } else if (m.has(AntimatterMaterialTypes.GEM)){
                provider.addStackRecipe(output, Ref.ID, m.getId() + "_block", "blocks", "has_gem", provider.hasSafeItem(AntimatterMaterialTypes.GEM.getMaterialTag(m)), AntimatterMaterialTypes.BLOCK.get().get(m).asStack(), ImmutableMap.of('I', AntimatterMaterialTypes.GEM.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(output,"gem_" + m.getId() + "_from_block", "blocks", "has_block", provider.hasSafeItem(AntimatterMaterialTypes.BLOCK.getMaterialTag(m)), AntimatterMaterialTypes.GEM.get(m, 9), AntimatterMaterialTypes.BLOCK.getMaterialTag(m));
            }
        });
        AntimatterMaterialTypes.DRILLBIT.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.PLATE) || m.has(AntimatterMaterialTypes.GEM)){
                TagKey<?> plate = m.has(AntimatterMaterialTypes.PLATE) ? AntimatterMaterialTypes.PLATE.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_drillbit", "drillbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.DRILLBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', AntimatterMaterialTypes.PLATE.getMaterialTag(Steel)), "PSP", "PSP", "SHS");
            }
        });
        AntimatterMaterialTypes.CHAINSAWBIT.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.PLATE) || m.has(AntimatterMaterialTypes.GEM)){
                TagKey<?> plate = m.has(AntimatterMaterialTypes.PLATE) ? AntimatterMaterialTypes.PLATE.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_chainsawbit", "chainsawbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.CHAINSAWBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', AntimatterMaterialTypes.PLATE.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel)), "SRS", "PHP", "SRS");
            }
        });
        AntimatterMaterialTypes.WRENCHBIT.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.PLATE) || m.has(AntimatterMaterialTypes.GEM)){
                TagKey<?> plate = m.has(AntimatterMaterialTypes.PLATE) ? AntimatterMaterialTypes.PLATE.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_wrenchbit", "wrenchbits", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.WRENCHBIT.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel), 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 's', AntimatterDefaultTools.SCREWDRIVER.getTag()), "HPS", "PRP", "SPs");
            }
        });
        AntimatterMaterialTypes.BUZZSAW_BLADE.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.PLATE) || m.has(AntimatterMaterialTypes.GEM)){
                TagKey<?> plate = m.has(AntimatterMaterialTypes.PLATE) ? AntimatterMaterialTypes.PLATE.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
                provider.addItemRecipe(output, Ref.ID, m.getId() + "_buzzsaw_blade", "buzzsaw_blades", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.BUZZSAW_BLADE.get(m), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'P', plate, 'F', AntimatterDefaultTools.FILE.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag(), 'C', AntimatterDefaultTools.WIRE_CUTTER.getTag()), "WPH", "P P", "FPC");
            }
        });
        MaterialTags.TOOLS.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.INGOT) && !m.has(AntimatterMaterialTypes.GEM)) return;
            TagKey<Item> plate = m.has(AntimatterMaterialTypes.PLATE) ? AntimatterMaterialTypes.PLATE.getMaterialTag(m) : m.has(AntimatterMaterialTypes.INGOT) ? AntimatterMaterialTypes.INGOT.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
            TagKey<Item> ingotGem = m.has(AntimatterMaterialTypes.INGOT) ? AntimatterMaterialTypes.INGOT.getMaterialTag(m) : AntimatterMaterialTypes.GEM.getMaterialTag(m);
            if (m.has(PICKAXE_HEAD)) {
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_pickaxe_head", "antimatter_dusts",
                        "has_wrench", in, PICKAXE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PII", "F H");
            }
            if (m.has(AXE_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_axe_head", "antimatter_dusts",
                        "has_wrench", in, AXE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PIH", "P  ", "F  ");
            }
            if (m.has(SHOVEL_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_shovel_head", "antimatter_dusts",
                        "has_wrench", in, SHOVEL_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "FPH");
            }
            if (m.has(SWORD_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_sword_head", "antimatter_dusts",
                        "has_wrench", in, SWORD_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), " P ", "FPH");
            }
            if (m.has(HOE_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_hoe_head", "antimatter_dusts",
                        "has_wrench", in, HOE_HEAD.get(m,1), of('P', plate, 'I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PIH", "F  ");
            }
            if (m.has(HAMMER_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_hammer_head", "antimatter_dusts",
                        "has_wrench", in, HAMMER_HEAD.get(m,1), of('I', ingotGem, 'H', AntimatterDefaultTools.HAMMER.getTag()), "II ", "IIH", "II ");
            }
            if (m.has(SAW_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_saw_head", "antimatter_dusts",
                        "has_wrench", in, SAW_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "PP", "FH");
            }
            if (m.has(FILE_HEAD)){
                provider.addStackRecipe(output, Ref.ANTIMATTER, m.getId() + "_file_head", "antimatter_dusts",
                        "has_wrench", in, FILE_HEAD.get(m,1), of('P', plate, 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterDefaultTools.FILE.getTag()), "FPH", " P ");
            }
        });

    }
}