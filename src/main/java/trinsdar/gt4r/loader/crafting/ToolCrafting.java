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
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.CustomTags;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.ToolTypes;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.FLINT;
import static muramasa.antimatter.material.MaterialTag.HANDLE;
import static muramasa.antimatter.recipe.RecipeBuilders.TOOL_BUILDER;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.MotorLV;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.ToolTypes.ROCK_CUTTER;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class ToolCrafting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        loadPoweredRecipes(output, provider);
        loadOtherRecipes(output, provider);
    }

    private static void loadPoweredRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        ICriterionInstance in = provider.hasSafeItem(SCREWDRIVER.getTag());
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId()), output, Ref.ID, "rock_cutter_1", "rock_cutters", "has_screwdriver", in, ROCK_CUTTER.getToolStack(NULL, NULL), of('D', PropertyIngredient.builder("primary").types(DUST).tags(Materials.ROCK_CUTTER).build(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 'R', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(ROD).build(), 'C', CustomTags.CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build()), "DR ", "DP ", "DCB");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId()),output, Ref.ID, ROCK_CUTTER.getId() + "_power_unit_recipe", "rock_cutters",
                "has_screwdriver", in, ROCK_CUTTER.getToolStack(NULL, NULL), of('D', PropertyIngredient.builder("primary").types(DUST).tags(Materials.ROCK_CUTTER).build(), 'S', FILE.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_ROCK_CUTTER).build()), "DS", "DP", "D ");


        IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "drill_lv", Ref.ANTIMATTER);
        IAntimatterTool drill_mv = AntimatterAPI.get(IAntimatterTool.class, "drill_mv", Ref.ANTIMATTER);
        IAntimatterTool drill_hv = AntimatterAPI.get(IAntimatterTool.class, "drill_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(DRILL.getId() + "-lv"),output, Ref.ID, DRILL.getId() + "_lv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(DRILL.getId() + "-mv"),output, Ref.ID, DRILL.getId() + "_mv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(DRILL.getId() + "-hv"),output, Ref.ID, DRILL.getId() + "_hv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(DRILL.getId() + "-lv"),output, Ref.ID, DRILL.getId() + "_lv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(DRILL.getId() + "-mv"),output, Ref.ID, DRILL.getId() + "_mv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(DRILL.getId() + "-hv"),output, Ref.ID, DRILL.getId() + "_hv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv", Ref.ANTIMATTER);
        IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv", Ref.ANTIMATTER);
        IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-lv"),output, Ref.ID, CHAINSAW.getId() + "_lv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_lv, NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-mv"),output, Ref.ID, CHAINSAW.getId() + "_mv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_mv, NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-hv"),output, Ref.ID, CHAINSAW.getId() + "_hv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_hv, NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-lv"),output, Ref.ID, CHAINSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_lv, NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-mv"),output, Ref.ID, CHAINSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_mv, NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(CHAINSAW.getId() + "-hv"),output, Ref.ID, CHAINSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_hv, NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(CHAINSAWBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");

        IAntimatterTool electric_wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv", Ref.ANTIMATTER);
        IAntimatterTool electric_wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv", Ref.ANTIMATTER);
        IAntimatterTool electric_wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-lv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_lv, NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-mv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_mv, NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-hv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_hv, NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-lv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_lv, NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-mv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_mv, NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ELECTRIC_WRENCH.getId() + "-hv"),output, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_hv, NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(WRENCHBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv", Ref.ANTIMATTER);
        IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv", Ref.ANTIMATTER);
        IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-lv"),output, Ref.ID, BUZZSAW.getId() + "_lv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_lv, NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-mv"),output, Ref.ID, BUZZSAW.getId() + "_mv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_mv, NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-hv"),output, Ref.ID, BUZZSAW.getId() + "_hv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_hv, NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-lv"),output, Ref.ID, BUZZSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_lv, NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-mv"),output, Ref.ID, BUZZSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_mv, NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(BUZZSAW.getId() + "-hv"),output, Ref.ID, BUZZSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_hv, NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "PS", "B ");

        IAntimatterTool electric_screwdriver_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv", Ref.ANTIMATTER);

        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(ELECTRIC_SCREWDRIVER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV, 'S', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build()), "R  ", " RM", " bS");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_power_unit_lv", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(ELECTRIC_SCREWDRIVER, true).build(),'S', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "R  ", " R ", "  S");

        IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_lv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(JACKHAMMER.getId() + "-lv"), output, Ref.ID, JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                "has_battery_small", provider.hasSafeItem(BATTERIES_SMALL), jackhammer_lv.resolveStack(NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(PLATE, GEM).tool(JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV, 'r', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(ROD).build()), "RbR", "rMr", " P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(JACKHAMMER.getId() + "-lv"), output, Ref.ID, JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                "has_power_unit_small", provider.hasSafeItem(POWER_UNIT_SMALL), jackhammer_lv.resolveStack(NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(PLATE, GEM).tool(JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "RbR", " P ");
    }

    private static void loadOtherRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider){
        ICriterionInstance in = provider.hasSafeItem(FILE.getTag());

        provider.addToolRecipe(TOOL_BUILDER.get(ToolTypes.SPEAR.getId()), output, Ref.ID,  "spear_recipe", "antimatter_tools",
                "has_file", in, ToolTypes.SPEAR.getToolStack(NULL, NULL), of('I', PropertyIngredient.builder("primary").inverse().tags(FLINT).types(PLATE, GEM).tool(ToolTypes.SPEAR, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build(), 'F', FILE.getTag(), 'H', HAMMER.getTag()), " FI", " RH", "R  ");


        provider.addToolRecipe(TOOL_BUILDER.get(ToolTypes.SPEAR.getId()), output, Ref.ID, "flint_spear_recipe", "antimatter_tools",
                "has_flint", provider.hasSafeItem(GEM.getMaterialTag(Flint)), ToolTypes.SPEAR.getToolStack(Flint, NULL), of('I', PropertyIngredient.builder("primary").types(GEM).tags(FLINT).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "  I", " R ", "R  ");

        provider.addToolRecipe(TOOL_BUILDER.get(PICKAXE.getId()), output, Ref.ANTIMATTER, PICKAXE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, PICKAXE.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(PICKAXE_HEAD).tool(PICKAXE, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AXE.getId()), output, Ref.ANTIMATTER, AXE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AXE.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(AXE_HEAD).tool(AXE, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(SHOVEL.getId()), output, Ref.ANTIMATTER, SHOVEL.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, SHOVEL.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(SHOVEL_HEAD).tool(SHOVEL, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(SWORD.getId()), output, Ref.ANTIMATTER, SWORD.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, SWORD.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(SWORD_HEAD).tool(SWORD, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(HOE.getId()), output, Ref.ANTIMATTER, HOE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, HOE.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(HOE_HEAD).tool(HOE, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(HAMMER.getId()), output, Ref.ANTIMATTER, HAMMER.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, HAMMER.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(HAMMER_HEAD).tool(HAMMER, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(FILE.getId()), output, Ref.ANTIMATTER, FILE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, FILE.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(FILE_HEAD).tool(FILE, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(SAW.getId()), output, Ref.ANTIMATTER, SAW.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, SAW.getToolStack(NULL, NULL), of('P', PropertyIngredient.builder("primary").types(SAW_HEAD).tool(SAW, true).build(), 'R', PropertyIngredient.builder("secondary").types(ROD).tags(HANDLE).build()), "P", "R");
    }


    public static ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = new ItemStack(tool.getItem());
        tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
        Map<Enchantment, Integer> mainEnchants = primary.getToolEnchantments();
        if (!mainEnchants.isEmpty()) {
            mainEnchants.entrySet().stream().filter(e -> e.getKey().canApply(stack)).forEach(e -> stack.addEnchantment(e.getKey(), e.getValue()));
        }
        return stack;
    }
}
