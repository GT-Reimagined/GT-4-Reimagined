package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.CustomTags;
import trinsdar.gt4r.data.GT4RMaterialTags;
import trinsdar.gt4r.data.ToolTypes;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.material.MaterialTags.HANDLE;
import static muramasa.antimatter.recipe.RecipeBuilders.TOOL_BUILDER;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.MotorLV;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.ToolTypes.ROCK_CUTTER;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class ToolCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        loadPoweredRecipes(output, provider);
        loadOtherRecipes(output, provider);
    }

    private static void loadPoweredRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.SCREWDRIVER.getTag());
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId()), output, Ref.ID, "rock_cutter_1", "rock_cutters", "has_screwdriver", in, ROCK_CUTTER.getToolStack(Material.NULL, Material.NULL), of('D', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.DUST).tags(GT4RMaterialTags.ROCK_CUTTER).build(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.PLATE).build(), 'R', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.ROD).build(), 'C', CustomTags.CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build()), "DR ", "DP ", "DCB");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId()),output, Ref.ID, ROCK_CUTTER.getId() + "_power_unit_recipe", "rock_cutters",
                "has_screwdriver", in, ROCK_CUTTER.getToolStack(Material.NULL, Material.NULL), of('D', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.DUST).tags(GT4RMaterialTags.ROCK_CUTTER).build(), 'S', AntimatterDefaultTools.FILE.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_ROCK_CUTTER).build()), "DS", "DP", "D ");


        IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "drill_lv", Ref.ANTIMATTER);
        IAntimatterTool drill_mv = AntimatterAPI.get(IAntimatterTool.class, "drill_mv", Ref.ANTIMATTER);
        IAntimatterTool drill_hv = AntimatterAPI.get(IAntimatterTool.class, "drill_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_lv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_mv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_hv_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, Material.NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_lv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_mv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.DRILL.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.DRILL.getId() + "_hv_power_unit_" + "recipe", "antimatter_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, Material.NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.DRILLBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv", Ref.ANTIMATTER);
        IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv", Ref.ANTIMATTER);
        IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_lv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_mv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_hv_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.CHAINSAW.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.CHAINSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_chainsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.CHAINSAWBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");

        IAntimatterTool electric_wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv", Ref.ANTIMATTER);
        IAntimatterTool electric_wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv", Ref.ANTIMATTER);
        IAntimatterTool electric_wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_lv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_mv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_hv_" + "recipe", "antimatter_electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_lv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_mv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.ELECTRIC_WRENCH.getId() + "_hv_power_unit_" + "recipe", "electric_wrenches",
                "has_screwdriver", in, Collections.singletonList(resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.WRENCHBIT, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv", Ref.ANTIMATTER);
        IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv", Ref.ANTIMATTER);
        IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_lv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_mv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 200000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_MEDIUM).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_hv_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 800000)), of2('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.PLATE).build(), 's', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(AntimatterMaterialTypes.SCREW).build(), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_LARGE).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-lv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-mv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.BUZZSAW.getId() + "-hv"),output, Ref.ID, AntimatterDefaultTools.BUZZSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_buzzsaws",
                "has_screwdriver", in, Collections.singletonList(resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 100000)), of('B', PropertyIngredient.of(AntimatterMaterialTypes.BUZZSAW_BLADE, "primary"), 'S', AntimatterDefaultTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "PS", "B ");

        IAntimatterTool electric_screwdriver_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv", Ref.ANTIMATTER);

        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, Ref.ID, AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "_lv", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.ROD).tool(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV, 'S', PropertyIngredient.builder("secondary").mats(Aluminium).types(AntimatterMaterialTypes.PLATE).build()), "R  ", " RM", " bS");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, Ref.ID, AntimatterDefaultTools.ELECTRIC_SCREWDRIVER.getId() + "_power_unit_lv", "antimatter_electric_screwdrivers",
                "has_material_" + Aluminium.getId(), provider.hasSafeItem(AntimatterMaterialTypes.PLATE.getMaterialTag(Aluminium)), electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.ROD).tool(AntimatterDefaultTools.ELECTRIC_SCREWDRIVER, true).build(),'S', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "R  ", " R ", "  S");

        IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_lv", Ref.ANTIMATTER);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.JACKHAMMER.getId() + "-lv"), output, Ref.ID, AntimatterDefaultTools.JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                "has_battery_small", provider.hasSafeItem(BATTERIES_SMALL), jackhammer_lv.resolveStack(Material.NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.ROD).tool(AntimatterDefaultTools.JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.GEM).tool(AntimatterDefaultTools.JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV, 'r', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(AntimatterMaterialTypes.ROD).build()), "RbR", "rMr", " P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(AntimatterDefaultTools.JACKHAMMER.getId() + "-lv"), output, Ref.ID, AntimatterDefaultTools.JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                "has_power_unit_small", provider.hasSafeItem(POWER_UNIT_SMALL), jackhammer_lv.resolveStack(Material.NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.ROD).tool(AntimatterDefaultTools.JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.GEM).tool(AntimatterDefaultTools.JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "RbR", " P ");
    }

    private static void loadOtherRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.FILE.getTag());

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.PICKAXE.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.PICKAXE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.PICKAXE.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.PICKAXE_HEAD).tool(AntimatterDefaultTools.PICKAXE, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.AXE.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.AXE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.AXE.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.AXE_HEAD).tool(AntimatterDefaultTools.AXE, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.SHOVEL.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.SHOVEL.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.SHOVEL.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.SHOVEL_HEAD).tool(AntimatterDefaultTools.SHOVEL, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.SWORD.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.SWORD.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.SWORD.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.SWORD_HEAD).tool(AntimatterDefaultTools.SWORD, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.HOE.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.HOE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.HOE.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.HOE_HEAD).tool(AntimatterDefaultTools.HOE, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.HAMMER.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.HAMMER.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.HAMMER.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.HAMMER_HEAD).tool(AntimatterDefaultTools.HAMMER, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.FILE.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.FILE.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.FILE.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.FILE_HEAD).tool(AntimatterDefaultTools.FILE, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");

        provider.addToolRecipe(TOOL_BUILDER.get(AntimatterDefaultTools.SAW.getId()), output, Ref.ANTIMATTER, AntimatterDefaultTools.SAW.getId() + "__recipe_with_head", "antimatter_files",
                "has_wrench", in, AntimatterDefaultTools.SAW.getToolStack(Material.NULL, Material.NULL), of('P', PropertyIngredient.builder("primary").types(GT4RMaterialTags.SAW_HEAD).tool(AntimatterDefaultTools.SAW, true).build(), 'R', PropertyIngredient.builder("secondary").types(AntimatterMaterialTypes.ROD).tags(HANDLE).build()), "P", "R");
    }


    public static ItemStack resolveStack(IAntimatterTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = new ItemStack(tool.getItem());
        tool.validateTag(stack, primary, secondary, startingEnergy, maxEnergy);
        if (!primary.has(MaterialTags.TOOLS)) return stack;
        Map<Enchantment, Integer> mainEnchants = MaterialTags.TOOLS.get(primary).toolEnchantment();
        if (!mainEnchants.isEmpty()) {
            mainEnchants.entrySet().stream().filter(e -> e.getKey().canEnchant(stack)).forEach(e -> stack.enchant(e.getKey(), e.getValue()));
        }
        return stack;
    }
}