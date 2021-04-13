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
        provider.addToolRecipe(POWERED_TOOL_BUILDER.apply(DRILL.getId() + "_lv"),output, Ref.ID, DRILL.getId() + "_lv_" + "recipe", "antimatter_lv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, StainlessSteel, 0, 100000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', PropertyIngredient.builder("battery").itemTags(CustomTags.BATTERIES_SMALL).build(), 'M', MotorLV), "sBS", "PMP", "PbP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_lv_" + "_cadmium_recipe", "antimatter_lv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, StainlessSteel, 0, 75000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_lv_" + "_sodium_recipe", "antimatter_lv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_lv, NULL, StainlessSteel, 0, 50000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_mv_" + "_lithium", "antimatter_mv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, Titanium, 0, 400000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_mv_" + "_cadmium", "antimatter_mv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, Titanium, 0, 300000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_mv_" + "_sodium", "antimatter_mv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_mv, NULL, Titanium, 0, 200000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_hv_" + "_lithium", "antimatter_hv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, TungstenSteel, 0, 1600000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_hv_" + "_cadmium", "antimatter_hv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, TungstenSteel, 0, 1200000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_hv_" + "_energy_crystal", "antimatter_hv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, TungstenSteel, 0, 1000000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorLV), "sBS", "PbP", "PMP");
        provider.addToolRecipe(TOOL_BUILDER.apply(DRILL.getId()),output, Ref.ID, DRILL.getId() + "_hv_" + "_sodium", "antimatter_hv_drills",
                "has_screwdriver", in, Collections.singletonList(resolveStack(drill_hv, NULL, TungstenSteel, 0, 800000)), of2('B', PropertyIngredient.of(DRILLBIT, "primary"), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorLV), "sBS", "PbP", "PMP");
        /*if (main.getToolTypes().contains(CHAINSAW)){
            IAntimatterTool chainsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_lv");
            IAntimatterTool chainsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_mv");
            IAntimatterTool chainsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "chainsaw_hv");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 100000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 75000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), resolveStack(chainsaw_lv, main, StainlessSteel, 0, 50000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 400000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 300000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_mv.resolveStack(main, Titanium, 0, 200000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, CHAINSAW.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_chainsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(CHAINSAWBIT.getMaterialTag(main)), chainsaw_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', CHAINSAWBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
        }
        if (main.getToolTypes().contains(ELECTRIC_WRENCH)){
            IAntimatterTool wrench_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_lv");
            IAntimatterTool wrench_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_mv");
            IAntimatterTool wrench_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_wrench_hv");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 100000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 75000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), resolveStack(wrench_lv, main, StainlessSteel, 0, 50000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 400000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 300000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_mv.resolveStack(main, Titanium, 0, 200000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_WRENCH.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_electric_wrenches",
                    "has_material_" + main.getId(), this.hasSafeItem(WRENCHBIT.getMaterialTag(main)), wrench_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', WRENCHBIT.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
        }
        if (main.getToolTypes().contains(BUZZSAW)){
            IAntimatterTool buzzsaw_lv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_lv");
            IAntimatterTool buzzsaw_mv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_mv");
            IAntimatterTool buzzsaw_hv = AntimatterAPI.get(IAntimatterTool.class, "buzzsaw_hv");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 100000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallLithium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 75000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallCadmium, 'M', MotorLV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), resolveStack(buzzsaw_lv, main, StainlessSteel, 0, 50000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(StainlessSteel), 's', SCREW.getMaterialTag(StainlessSteel), 'b', BatterySmallSodium, 'M', MotorLV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() + "_lithium", "antimatter_mv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 400000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumLithium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() +"_cadmium", "antimatter_mv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 300000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumCadmium, 'M', MotorMV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_mv_" + main.getId() + "_sodium", "antimatter_mv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_mv.resolveStack(main, Titanium, 0, 200000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(Titanium), 's', SCREW.getMaterialTag(Titanium), 'b', BatteryMediumSodium, 'M', MotorMV), "sBS", "PbP", "PMP");

            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_lithium", "antimatter_hv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1600000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeLithium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() +"_cadmium", "antimatter_hv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1200000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeCadmium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_sodium", "antimatter_hv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 800000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', BatteryLargeSodium, 'M', MotorHV), "sBS", "PbP", "PMP");
            addStackRecipe(consumer, Ref.ID, BUZZSAW.getId() + "_hv_" + main.getId() + "_energy_crystal", "antimatter_hv_buzzsaws",
                    "has_material_" + main.getId(), this.hasSafeItem(BUZZSAW_BLADE.getMaterialTag(main)), buzzsaw_hv.resolveStack(main, TungstenSteel, 0, 1000000), of2('B', BUZZSAW_BLADE.getMaterialTag(main), 'S', SCREWDRIVER.getTag(), 'P', PLATE.getMaterialTag(TungstenSteel), 's', SCREW.getMaterialTag(TungstenSteel), 'b', EnergyCrystal, 'M', MotorHV), "sBS", "PbP", "PMP");
        }
        if (main.getToolTypes().contains(ELECTRIC_SCREWDRIVER)){
            IAntimatterTool drill_lv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_lv");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_electric_screwdrivers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 100000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallLithium, 'M', MotorLV), "R  ", " RM", "  b");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_electric_screwdrivers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 75000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallCadmium, 'M', MotorLV), "R  ", " RM", "  b");
            addStackRecipe(consumer, Ref.ID, ELECTRIC_SCREWDRIVER.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_electric_screwdrivers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), drill_lv.resolveStack(main, NULL, 0, 50000), of('R', ROD.getMaterialTag(main), 'b', BatterySmallSodium, 'M', MotorLV), "R  ", " RM", "  b");
        }
        if (main.getToolTypes().contains(JACKHAMMER)){
            IAntimatterTool jackhammer_lv = AntimatterAPI.get(IAntimatterTool.class, "jackhammer_lv");
            addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() + "_lithium", "antimatter_lv_jackhammers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 100000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallLithium, 'M', MotorLV), "RbR", " M ", " P ");
            addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() +"_cadmium", "antimatter_lv_jackhammers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 75000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallCadmium, 'M', MotorLV), "RbR", " M ", " P ");
            addStackRecipe(consumer, Ref.ID, JACKHAMMER.getId() + "_lv_" + main.getId() + "_sodium", "antimatter_lv_jackhammers",
                    "has_material_" + main.getId(), this.hasSafeItem(ROD.getMaterialTag(main)), jackhammer_lv.resolveStack(main, NULL, 0, 50000), of('R', ROD.getMaterialTag(main), 'P', plateTag, 'b', BatterySmallSodium, 'M', MotorLV), "RbR", " M ", " P ");
        }*/
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
