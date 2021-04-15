package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.CustomTags;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.GT4RData.BatterySmallSodium;
import static trinsdar.gt4r.data.GT4RData.MotorLV;
import static trinsdar.gt4r.data.Materials.StainlessSteel;
import static trinsdar.gt4r.data.Materials.Titanium;
import static trinsdar.gt4r.data.Materials.TungstenSteel;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class ToolCrafting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        final ICriterionInstance in = provider.hasSafeItem(SCREWDRIVER.getTag());

        IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "drill_lv");
        IAntimatterTool drill_mv = AntimatterAPI.get(IAntimatterTool.class, "drill_mv");
        IAntimatterTool drill_hv = AntimatterAPI.get(IAntimatterTool.class, "drill_hv");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(DRILL.getId() + "_lv"),output, Ref.ID, DRILL.getId() + "_lv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, StainlessSteel, 0, 100000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(DRILL.getId() + "_mv"),output, Ref.ID, DRILL.getId() + "_mv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, Titanium, 0, 200000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(DRILL.getId() + "_hv"),output, Ref.ID, DRILL.getId() + "_hv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, TungstenSteel, 0, 800000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");

        IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv");
        IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv");
        IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(CHAINSAW.getId() + "_lv"),output, Ref.ID, CHAINSAW.getId() + "_lv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_lv, NULL, StainlessSteel, 0, 100000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(CHAINSAW.getId() + "_mv"),output, Ref.ID, CHAINSAW.getId() + "_mv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_mv, NULL, Titanium, 0, 200000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(CHAINSAW.getId() + "_hv"),output, Ref.ID, CHAINSAW.getId() + "_hv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_hv, NULL, TungstenSteel, 0, 800000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");

        IAntimatterTool electric_wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv");
        IAntimatterTool electric_wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv");
        IAntimatterTool electric_wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(ELECTRIC_WRENCH.getId() + "_lv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_lv, NULL, StainlessSteel, 0, 100000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(ELECTRIC_WRENCH.getId() + "_mv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_mv, NULL, Titanium, 0, 200000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(ELECTRIC_WRENCH.getId() + "_hv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_hv, NULL, TungstenSteel, 0, 800000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");

        IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv");
        IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv");
        IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(BUZZSAW.getId() + "_lv"),output, Ref.ID, BUZZSAW.getId() + "_lv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_lv, NULL, StainlessSteel, 0, 100000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(BUZZSAW.getId() + "_mv"),output, Ref.ID, BUZZSAW.getId() + "_mv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_mv, NULL, Titanium, 0, 200000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(BUZZSAW.getId() + "_hv"),output, Ref.ID, BUZZSAW.getId() + "_hv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_hv, NULL, TungstenSteel, 0, 800000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "PbM", "SBP", "sPP");


        IAntimatterTool electric_screwdriver_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv");

        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(ELECTRIC_SCREWDRIVER.getId() + "_lv"), output, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv", "antimatter_electric_screwdrivers",
                "has_material_" + StainlessSteel.getId(), provider.hasSafeItem(PLATE.getMaterialTag(StainlessSteel)), electric_screwdriver_lv.resolveStack(NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(ELECTRIC_SCREWDRIVER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV, 'S', PLATE.getMaterialTag(StainlessSteel)), "R  ", " RM", " bS");

        IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_lv");
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(JACKHAMMER.getId() + "_lv"), output, Ref.ID, JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                "has_battery_small", provider.hasSafeItem(CustomTags.BATTERIES_SMALL), jackhammer_lv.resolveStack(NULL, NULL, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(PLATE).tool(JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "RbR", " M ", " P ");

        /*final ICriterionInstance in = provider.hasSafeItem(FILE.getTag());

        provider.addStackRecipe("spear", output, Ref.ID, HAMMER.getId() + "_" +"recipe", "antimatter_tools",
                "has_file", in, SPEAR.getToolStack(NULL, NULL), of('I', MaterialIngredient.of(INGOT), 'R', MaterialIngredient.of(ROD)), "II ", "IIR", "II ");*/
    }


    public static ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = new ItemStack(tool.getItem());
        tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
        Map<Enchantment, Integer> mainEnchants = primary.getEnchantments();
        if (!mainEnchants.isEmpty()) {
            mainEnchants.entrySet().stream().filter(e -> e.getKey().canApply(stack)).forEach(e -> stack.addEnchantment(e.getKey(), e.getValue()));
        }
        return stack;
    }
}
