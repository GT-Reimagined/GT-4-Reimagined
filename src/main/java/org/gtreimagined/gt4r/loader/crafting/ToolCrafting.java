package org.gtreimagined.gt4r.loader.crafting;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.recipe.ingredient.PropertyIngredient;
import org.gtreimagined.gtlib.tool.IGTTool;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreTools;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;
import org.gtreimagined.gt4r.data.ToolTypes;

import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Diamond;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.MotorLV;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;
import static org.gtreimagined.gt4r.data.ToolTypes.ROCK_CUTTER;

public class ToolCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider){
        if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()) {
            loadBreakablePoweredRecipes(output, provider);
        } else {
            loadPoweredRecipes(output, provider);
        }
        loadOtherRecipes(output, provider);
    }

    private static void loadPoweredRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider){
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(GTCoreTools.DRILL.getId()),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_" + "recipe", "antimatter_drills",
                GT4RItems.Drill.getDefaultInstance(), of('S', PLATES_STEELS, 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), " S ", "SCS", "SBS");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("diamond_drill"),output, GT4RRef.ID,   "diamond_drill_" + "recipe", "antimatter_drills",
                GT4RItems.DiamondDrill.getDefaultInstance(), of('T', PLATE.getMaterialTag(Titanium), 'C', CIRCUITS_ADVANCED, 'D', GEM.getMaterialTag(Diamond), 'B', PropertyIngredient.builder("battery").itemTags(DRILL).build()), " D ", "DBD", "TCT");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("advanced_drill"),output, GT4RRef.ID,   "advanced_drill_" + "recipe", "antimatter_drills",
                GT4RItems.AdvancedDrill.getDefaultInstance(), of('T', PLATE.getMaterialTag(TungstenSteel), 'C', CIRCUITS_ELITE, 'D', GEM.getMaterialTag(Diamond), 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build()), "DDD", "TCT", "TBT");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(GTCoreTools.CHAINSAW.getId()),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_" + "recipe", "antimatter_drills",
                GT4RItems.Chainsaw.getDefaultInstance(), of('S', PLATES_STEELS, 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), " SS", "SCS", "BS ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("advanced_chainsaw"),output, GT4RRef.ID,   "advanced_chainsaw_" + "recipe", "antimatter_drills",
                GT4RItems.AdvancedChainsaw.getDefaultInstance(), of('T', PLATE.getMaterialTag(TungstenSteel), 'C', CIRCUITS_ELITE, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build()), " TT", "TCT", "BT ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(GTCoreTools.ELECTRIC_WRENCH.getId()),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_" + "recipe", "antimatter_drills",
                GT4RItems.ElectricWrench.getDefaultInstance(), of('S', PLATES_STEELS, 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "S S", "SCS", " B ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("advanced_wrench"),output, GT4RRef.ID,   "advanced_wrench_" + "recipe", "antimatter_drills",
                GT4RItems.AdvancedWrench.getDefaultInstance(), of('T', PLATE.getMaterialTag(TungstenSteel), 'C', CIRCUITS_ELITE, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build()), "T T", "TCT", " B ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(GTCoreTools.ELECTRIC_SCREWDRIVER.getId()),output, GT4RRef.ID, GTCoreTools.ELECTRIC_SCREWDRIVER.getId() + "_" + "recipe", "antimatter_drills",
                GT4RItems.ElectricScrewdriver.getDefaultInstance(), of('S', RODS_STEELS, 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "S  ", " SC", "  B");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(ROCK_CUTTER.getId()),output, GT4RRef.ID,  "rock_cutter_titanium", "antimatter_drills",
                GT4RItems.RockCutter.getDefaultInstance(), of('R', ROD.getMaterialTag(Titanium), 'P', PLATE.getMaterialTag(Titanium), 'C', CIRCUITS_BASIC, 'D', DUST.getMaterialTag(Diamond), 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "DR ", "DP ", "DCB");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get(ROCK_CUTTER.getId()),output, GT4RRef.ID,  "rock_cutter_tungstensteel", "antimatter_drills",
                GT4RItems.RockCutter.getDefaultInstance(), of('R', ROD.getMaterialTag(TungstenSteel), 'P', PLATE.getMaterialTag(TungstenSteel), 'C', CIRCUITS_BASIC, 'D', DUST.getMaterialTag(Diamond), 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "DR ", "DP ", "DCB");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("bronze_jackhammer"), output, GT4RRef.ID, "jackhammer_bronze", "drills",
                GT4RItems.BronzeJackHammer.getDefaultInstance(), of('R', ROD.getMaterialTag(Bronze), 'I', INGOT.getMaterialTag(Bronze), 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "RBR", " C ", " I ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("steel_jackhammer"), output, GT4RRef.ID, "jackhammer_steel", "drills",
                GT4RItems.SteelJackHammer.getDefaultInstance(), of('R', ROD.getMaterialTag(StainlessSteel), 'I', INGOT.getMaterialTag(StainlessSteel), 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "RBR", " C ", " I ");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER_BASIC.get("diamond_jackhammer"), output, GT4RRef.ID, "jackhammer_diamond", "drills",
                GT4RItems.DiamondJackHammer.getDefaultInstance(), of('R', ROD.getMaterialTag(TungstenSteel), 'I', GEM.getMaterialTag(Diamond), 'C', CIRCUITS_ADVANCED, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build()), "RBR", " C ", " I ");
    }

    private static void loadBreakablePoweredRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider){
        IGTTool rock_cutter_lv = GTAPI.get(IGTTool.class, "rock_cutter_lv", GT4RRef.ID);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId() + "-lv"), output, GT4RRef.ID, "rock_cutter_lv", "rock_cutters", ROCK_CUTTER.getToolStack(Material.NULL, Material.NULL), of('D', PropertyIngredient.builder("primary").types(GTMaterialTypes.DUST).tags(GT4RMaterialTags.ROCK_CUTTER).build(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 'R', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(ROD).build(), 'C', CIRCUITS_BASIC, 'B', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build()), "DR ", "DP ", "DCB");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(ROCK_CUTTER.getId() + "-lv"),output, GT4RRef.ID, ROCK_CUTTER.getId() + "_power_unit_recipe", "rock_cutters",
                resolveStack(rock_cutter_lv, Material.NULL, Aluminium, 0, 100000), of('D', PropertyIngredient.builder("primary").types(GTMaterialTypes.DUST).tags(GT4RMaterialTags.ROCK_CUTTER).build(), 'S', GTTools.FILE.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_ROCK_CUTTER).build()), "DS", "DP", "D ");


        IGTTool drill_lv = GTAPI.get(IGTTool.class, "drill_lv", GTCore.ID);
        IGTTool drill_mv = GTAPI.get(IGTTool.class, "drill_mv", GTCore.ID);
        IGTTool drill_hv = GTAPI.get(IGTTool.class, "drill_hv", GTCore.ID);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_lv_" + "recipe", "antimatter_drills",
                resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_mv_" + "recipe", "antimatter_drills",
                resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 200000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_hv_" + "recipe", "antimatter_drills",
                resolveStack(drill_hv, Material.NULL, Titanium, 0, 800000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_HV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_lv_power_unit_" + "recipe", "antimatter_drills",
                resolveStack(drill_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_mv_power_unit_" + "recipe", "antimatter_drills",
                resolveStack(drill_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.DRILL.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.DRILL.getId() + "_hv_power_unit_" + "recipe", "antimatter_drills",
                resolveStack(drill_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.DRILLBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IGTTool chainsaw_lv = GTAPI.get(IGTTool.class, "chainsaw_lv", GTCore.ID);
        IGTTool chainsaw_mv = GTAPI.get(IGTTool.class, "chainsaw_mv", GTCore.ID);
        IGTTool chainsaw_hv = GTAPI.get(IGTTool.class, "chainsaw_hv", GTCore.ID);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_lv_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_mv_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 200000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_hv_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 800000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_HV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.CHAINSAW.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.CHAINSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_chainsaws",
                resolveStack(chainsaw_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.CHAINSAWBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");

        IGTTool electric_wrench_lv = GTAPI.get(IGTTool.class, "electric_wrench_lv", GTCore.ID);
        IGTTool electric_wrench_mv = GTAPI.get(IGTTool.class, "electric_wrench_mv", GTCore.ID);
        IGTTool electric_wrench_hv = GTAPI.get(IGTTool.class, "electric_wrench_hv", GTCore.ID);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_lv_" + "recipe", "antimatter_electric_wrenches",
                resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_mv_" + "recipe", "antimatter_electric_wrenches",
                resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 200000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_hv_" + "recipe", "antimatter_electric_wrenches",
                resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 800000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_HV).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_lv_power_unit_" + "recipe", "electric_wrenches",
                resolveStack(electric_wrench_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_mv_power_unit_" + "recipe", "electric_wrenches",
                resolveStack(electric_wrench_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "BS", "P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_WRENCH.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.ELECTRIC_WRENCH.getId() + "_hv_power_unit_" + "recipe", "electric_wrenches",
                resolveStack(electric_wrench_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(GTMaterialTypes.WRENCHBIT, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "BS", "P ");


        IGTTool buzzsaw_lv = GTAPI.get(IGTTool.class, "buzzsaw_lv", GTCore.ID);
        IGTTool buzzsaw_mv = GTAPI.get(IGTTool.class, "buzzsaw_mv", GTCore.ID);
        IGTTool buzzsaw_hv = GTAPI.get(IGTTool.class, "buzzsaw_hv", GTCore.ID);
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_lv_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_mv_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 200000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_MV).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_hv_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 800000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").mats(Titanium, TungstenSteel).types(PLATE).build(), 's', SCREW.getMaterialTag(Steel), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_HV).build(), 'M', MotorLV), "PbM", "SBP", "sPP");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-lv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_lv_power_unit_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_lv, Material.NULL, Aluminium, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_LV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-mv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_mv_power_unit_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_mv, Material.NULL, StainlessSteel, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_MV).build()), "PS", "B ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.BUZZSAW.getId() + "-hv"),output, GT4RRef.ID, GTCoreTools.BUZZSAW.getId() + "_hv_power_unit_" + "recipe", "antimatter_buzzsaws",
                resolveStack(buzzsaw_hv, Material.NULL, Titanium, 0, 100000), of('B', PropertyIngredient.of(BUZZSAW_BLADE, "primary"), 'S', GTTools.SCREWDRIVER.getTag(), 'P', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_HV).build()), "PS", "B ");

        IGTTool electric_screwdriver_lv = GTAPI.get(IGTTool.class, "electric_screwdriver_lv", GTCore.ID);

        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, GT4RRef.ID, GTCoreTools.ELECTRIC_SCREWDRIVER.getId() + "_lv", "antimatter_electric_screwdrivers",
                electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(GTCoreTools.ELECTRIC_SCREWDRIVER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_LV).build(), 'M', MotorLV, 'S', PropertyIngredient.builder("secondary").mats(Aluminium).types(PLATE).build()), "R  ", " RM", " bS");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.ELECTRIC_SCREWDRIVER.getId() + "-lv"), output, GT4RRef.ID, GTCoreTools.ELECTRIC_SCREWDRIVER.getId() + "_power_unit_lv", "antimatter_electric_screwdrivers",
                electric_screwdriver_lv.resolveStack(Material.NULL, Aluminium, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(GTCoreTools.ELECTRIC_SCREWDRIVER, true).build(),'S', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "R  ", " R ", "  S");

        /*IGTTool jackhammer_lv = GTAPI.get(IGTTool.class, "jackhammer_hv");
        provider.addToolRecipe(ToolTypes.POWERED_TOOL_BUILDER.get(GTCoreTools.JACKHAMMER.getId() + "-hv"), output, GT4RRef.ID, GTCoreTools.JACKHAMMER.getId() + "_lv", "antimatter_jackhammers",
                jackhammer_lv.resolveStack(Material.NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(GTCoreTools.JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(PLATE, GTMaterialTypes.GEM).tool(GTCoreTools.JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("battery").itemTags(BATTERIES_SMALL).build(), 'M', MotorLV, 'r', PropertyIngredient.builder("secondary").mats(StainlessSteel).types(ROD).build()), "RbR", "rMr", " P ");
        provider.addToolRecipe(ToolTypes.UNIT_POWERED_TOOL_BUILDER.get(GTCoreTools.JACKHAMMER.getId() + "-hv"), output, GT4RRef.ID, GTCoreTools.JACKHAMMER.getId() + "_lv_from_pu", "antimatter_jackhammers",
                jackhammer_lv.resolveStack(Material.NULL, StainlessSteel, 0, 100000), of('R', PropertyIngredient.builder("primary").types(ROD).tool(GTCoreTools.JACKHAMMER, true).build(), 'P', PropertyIngredient.builder("primary").types(PLATE, GTMaterialTypes.GEM).tool(GTCoreTools.JACKHAMMER, true).build(), 'b', PropertyIngredient.builder("secondary").itemTags(POWER_UNIT_SMALL).build()), "RbR", " P ");*/
    }

    private static void loadOtherRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider){

    }


    public static ItemStack resolveStack(IGTTool tool, Material primary, Material secondary, long startingEnergy, long maxEnergy) {
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