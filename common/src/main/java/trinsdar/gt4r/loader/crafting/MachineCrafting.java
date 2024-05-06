package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.GTCore;
import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreMaterials;
import io.github.gregtechintergalactical.gtcore.machine.WorkbenchMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.pipe.PipeSize;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.GT4RRef;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.CustomTags;
import trinsdar.gt4r.data.GT4RMaterialTags;
import trinsdar.gt4r.data.TierMaps;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.of2;

public class MachineCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        loadSteamMachineRecipes(output, provider);
        loadSimpleMachineRecipes(output, provider);
        loadGeneratorRecipes(output, provider);
        loadMultiblockRecipes(output, provider);
        loadHatchRecipes(output, provider);
        loadUtilityBlockRecipes(output, provider);
    }

    private static void loadMultiblockRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addItemRecipe(output, GT4RRef.ID, "", "machines",
                PRIMITIVE_BLAST_FURNACE.getItem(NONE), of('B', FireBrick, 'I', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron)), "BBB", "BIB", "BBB");
        provider.addItemRecipe(output, GT4RRef.ID, "", "machines",
                COKE_OVEN.getItem(NONE), of('B', FireBrick), "BBB", "B B", "BBB");
        provider.addItemRecipe(output, GT4RRef.ID,"large_gas_turbine","machines",
                LARGE_GAS_TURBINE.getItem(IV), of('G', GAS_TURBINE.getItem(LV), 'T', GEARS_TITAN_TUNGSTEEL, 'H', MACHINE_HULLS_VERY_ADVANCED, 'C', CIRCUITS_MASTER), "GGG", "THT", "GCG");
        provider.addItemRecipe(output, GT4RRef.ID,"large_steam_turbine","machines",
                LARGE_STEAM_TURBINE.getItem(EV), of('G', STEAM_TURBINE.getItem(LV), 'T', GEARS_STEELS, 'H', MACHINE_HULLS_ADVANCED, 'C', CIRCUITS_ADVANCED), "GGG", "THT", "GCG");
        provider.addItemRecipe(output, GT4RRef.ID,"thermal_boiler","machines",
                THERMAL_BOILER.getItem(LV), of('G', HEAT_EXCHANGER.getItem(LV), 'T', GEARS_TITAN_TUNGSTEEL, 'M', CENTRIFUGE.getItem(LV), 'C', CIRCUITS_ELITE), "GMG", "TCT", "GMG");
        provider.addItemRecipe(output, GT4RRef.ID, "industrial_grinder", "machines",
                INDUSTRIAL_GRINDER.getItem(MV), of('E', ELECTROLYZER.getItem(MV),'C', CIRCUITS_ADVANCED, 'M', MACHINE_HULLS_ADVANCED, 'P', PUMP.getItem(LV), 'G', GRINDING_HEAD), "ECP", "GGG", "CMC");
        provider.addItemRecipe(output, GT4RRef.ID, "industrial_blast_furnace", "machines",
                BLAST_FURNACE.getItem(MV), of('C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'H', CupronickelHeatingCoil, 'F', FURNACE.getItem(LV)), "CHC", "HMH", "FHF");
        provider.addItemRecipe(output, GT4RRef.ID, "pyrolysis_oven", "machines",
                PYROLYSIS_OVEN.getItem(LV), of('C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'p', PumpModule, 'P', PISTONS, 'F', FURNACE.getItem(LV)), "PCP", "CMC", "FpF");
        provider.addItemRecipe(output, GT4RRef.ID, "implosion_compressor", "machines",
                IMPLOSION_COMPRESSOR.getItem(LV), of('C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'c', COMPRESSOR.getItem(LV), 'A', AdvancedAlloy), "AMA", "CcC", "AMA");
        provider.addItemRecipe(output, GT4RRef.ID, "industrial_sawmill", "machines",
                INDUSTRIAL_SAWMILL.getItem(MV), of('C', CIRCUITS_ADVANCED, 'M', MACHINE_HULLS_ADVANCED, 'P', PUMP.getItem(LV), 'S', DiamondSawBlade), "PCP", "SSS", "CMC");
        provider.addItemRecipe(output, GT4RRef.ID, "vacuum_freezer", "machines",
                VACUUM_FREEZER.getItem(LV), of('C', CIRCUITS_BASIC, 'G', REINFORCED_GLASS, 'P', PUMP.getItem(LV), 'A', AntimatterMaterialTypes.PLATE.getMaterialTag(Aluminium)), "APA", "CGC", "APA");
        provider.addItemRecipe(output, GT4RRef.ID, "chemical_reactor", "machines",
                CHEMICAL_REACTOR.getItem(MV), of('C', CIRCUITS_ADVANCED, 'P', PLATES_INVAR_ALUMINIUM, 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil, 'c', COMPRESSOR.getItem(LV)), "PLP", "CcC", "PEP");
        provider.addItemRecipe(output, GT4RRef.ID, "distillation_tower", "machines",
                DISTILLATION_TOWER.getItem(MV), of('C', CIRCUITS_MASTER, 'P', PUMP.getItem(LV), 'E', ELECTROLYZER.getItem(MV), 'c', CENTRIFUGE.getItem(LV), 'A', HIGHLY_ADVANCED_MACHINE_BLOCK), "cCc", "PAP", "ECE");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_control_computer", "machines",
                FUSION_REACTOR.getItem(IV), of('C', CIRCUITS_MASTER, 'c', COMPUTER_CUBE.getItem(LV), 'F', FUSION_COIL), "CCC", "cFc", "CCC");
    }

    private static void loadGeneratorRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addItemRecipe(output, GT4RRef.ID, "diesel_generator", "machines",
                DIESEL_GENERATOR.getItem(LV), of('P', PLATES_WROUGHT_ALUMINIUM, 'B', BatteryRE, 'C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED), "PBP", "P P", "CMC");
        provider.addItemRecipe(output, GT4RRef.ID, "semifluid_generator", "machines",
                SEMIFLUID_GENERATOR.getItem(LV), of('P', PLATES_WROUGHT_ALUMINIUM, 'B', BatteryRE, 'C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'R', REINFORCED_GLASS), "PBP", "PRP", "CMC");
        provider.addItemRecipe(output, GT4RRef.ID, "gas_turbine", "machines",
                GAS_TURBINE.getItem(LV), of('P', PLATES_INVAR_ALUMINIUM, 'W', WINDMILL.getItem(ULV), 'C', CIRCUITS_ADVANCED, 'G', REINFORCED_GLASS), "PCP", "WGW", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "steam_turbine", "machines",
                STEAM_TURBINE.getItem(LV), of('P', PLATES_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', CIRCUITS_BASIC, 'B', GT4RMaterialTags.TURBINE_ROTOR.getMaterialTag(Bronze)), "PCP", "BMB", "PCP");
    }

    private static void loadSteamMachineRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        for (Tier tier : getSteam()) {
            TagKey<Item> plate = tier == BRONZE ? PLATE.getMaterialTag(Bronze) : PLATES_STEELS;
            TagKey<Item> gear = tier == BRONZE ? GEAR.getMaterialTag(Bronze) : GEARS_STEELS;
            TagKey<Item> hull = tier == BRONZE ? MACHINE_HULLS_CHEAP : CustomTags.MACHINE_HULLS_BASIC;
            String prefix = tier == BRONZE ? "high_pressure_" : "";
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "solid_fuel_boiler","machines",
                    SOLID_FUEL_BOILER.getItem(tier), of( 'P',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE), "PPP", "PWP", "BFB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_macerator","machines",
                    STEAM_MACERATOR.getItem(tier), of2( 'P',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'D', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'M', hull, 'p', PISTONS, 'G', gear), "WDH", "GMG", "PpP");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_furnace","machines",
                    STEAM_FURNACE.getItem(tier), of( 'P',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE, 'M', hull), "PWP", "PFP", "BMB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_alloy_smelter","machines",
                    STEAM_ALLOY_SMELTER.getItem(tier), of( 'P',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(), 'B', Items.BRICKS, 'F', STEAM_FURNACE.getItem(tier)), "PPP", "FWF", "BBB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_forge_hammer","machines",
                    STEAM_FORGE_HAMMER.getItem(tier), of( 'B', plate, 'P', PISTONS, 'M', hull, 'A', Items.ANVIL), "BPB", "BMB", "BAB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_compressor","machines",
                    STEAM_COMPRESSOR.getItem(tier), of( 'B',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(), 'G', gear, 'P', PISTONS, 'M', hull), "BGB", "PWP", "BMB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_extractor","machines",
                    STEAM_EXTRACTOR.getItem(tier), of( 'B',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(),'P', PISTONS, 'M', hull), "BBB", "PWP", "BMB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_cutter","machines",
                    STEAM_CUTTER.getItem(tier), of( 'B',  plate, 'W', AntimatterDefaultTools.WRENCH.getTag(),'P', PISTONS, 'M', hull, 'C', BUZZSAW_BLADE.getMaterialTag(Steel)), "BCB", "PWP", "BMB");
            provider.addItemRecipe(output, GT4RRef.ID,prefix + "steam_sifter","machines",
                    STEAM_SIFTER.getItem(tier), of( 'B',  plate, 'C', WIRE_STEEL.getBlock(PipeSize.VTINY), 'W', AntimatterDefaultTools.WRENCH.getTag(),'P', PISTONS, 'M', hull), "CCC", "PWP", "BMB");

        }
        provider.addItemRecipe(output, GT4RRef.ID,"high_pressure_solid_fuel_boiler_upgrade","machines",
                SOLID_FUEL_BOILER.getItem(STEEL), of( 'P',  PLATES_STEELS, 'C', SOLID_FUEL_BOILER.getItem(BRONZE)), "PPP", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID,"high_pressure_furnace_upgrade","machines",
                STEAM_FURNACE.getItem(STEEL), of( 'P',  PLATES_STEELS, 'C', STEAM_FURNACE.getItem(BRONZE)), "PPP", "PCP");
    }

    private static void loadSimpleMachineRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        BATTERY_BUFFER_FOUR.getTiers().forEach(t -> {
            provider.addItemRecipe(output, GT4RRef.ID, t.getId() + "_batter_buffer_four", "machines",
                    BATTERY_BUFFER_FOUR.getItem(t), of('H', GT4RMaterialTags.HULL.get(TierMaps.TIER_MATERIALS.get(t)), 'C', ForgeCTags.CHESTS, 'W', TierMaps.TIER_WIRES.get(t).getBlockItem(PipeSize.SMALL)), "WCW", "WHW");
            provider.addItemRecipe(output, GT4RRef.ID, t.getId() + "_batter_buffer_eight", "machines",
                    BATTERY_BUFFER_EIGHT.getItem(t), of('H', GT4RMaterialTags.HULL.get(TierMaps.TIER_MATERIALS.get(t)), 'C', ForgeCTags.CHESTS, 'W', TierMaps.TIER_WIRES.get(t).getBlockItem(PipeSize.NORMAL)), "WCW", "WHW");
        });
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(LV),
                of('H', MACHINE_HULLS_ADVANCED, 'C', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'c', CopperCoil), " C ", "cHc", " C ");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(MV),
                of('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY), 'H', MACHINE_HULLS_ADVANCED), "C", "H", "C");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(HV),
                of('C', CIRCUITS_BASIC, 'c', CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'T', TRANSFORMER.getItem(MV), 'B', BatteryMediumLithium), " c ", "CTB", " c ");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(EV),
                of('C', CIRCUITS_ADVANCED, 'T', TRANSFORMER.getItem(HV), 'c', CABLE_STEEL.getBlockItem(PipeSize.VTINY), 'B', LapotronCrystal), " c ", "CTB", " c ");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(IV),
                of('I', IRIDIUM_REINFORCED_STONE, 'C', CIRCUITS_ADVANCED, 'T', TRANSFORMER.getItem(EV), 'c', CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY), 'B', BatteryEnergyOrb), "IcI", "CTB", "IcI");
        /*TRANSFORMER.getTiers().forEach(t -> {
            provider.addItemRecipe(output, GT4RRef.ID, t.getId() + "_transformer", "machines",
                    TRANSFORMER.getItem(t), of( 'C', TierMaps.TIER_CABLES.get(t).getBlockItem(PipeSize.VTINY), 'M', GT4RMaterialTags.HULL.getMaterialTag(TierMaps.TIER_MATERIALS.get(t)), 'c', TierMaps.TIER_CABLES.get(Tier.getTier(t.getVoltage() * 4)).getBlockItem(PipeSize.VTINY))," CC", "cM ", " CC");
        });*/
        provider.addItemRecipe(output, GT4RRef.ID, "mv_electrolyzer", "machines",
                ELECTROLYZER.getItem(MV), of('C', CIRCUITS_ADVANCED, 'P', PLATES_STEELS, 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil), "PEP", "CLC", "PEP");
        provider.addItemRecipe(output, GT4RRef.ID, "mv_electrolyzer_upgrade", "machines",
                ELECTROLYZER.getItem(MV), of('C', CIRCUITS_ADVANCED, 'P', PLATES_STEELS, 'E', ELECTROLYZER.getItem(LV)), " P ", "CEC", " P ");
        provider.addItemRecipe(output, GT4RRef.ID, "lv_electrolyzer", "machines",
                ELECTROLYZER.getItem(LV), of('C', CIRCUITS_BASIC, 'P', PLATES_WROUGHT_ALUMINIUM, 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil), "PEP", "CLC", "PEP");
        provider.addItemRecipe(output, GT4RRef.ID, "macerator_1", "machines",
                MACERATOR.getItem(LV), of('C', CIRCUITS_ADVANCED, 'P', PLATES_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'D', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Diamond)), "PDP", "DMD", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "macerator_2", "machines",
                MACERATOR.getItem(LV), of('C', CIRCUITS_BASIC, 'P', PLATES_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'D', GRINDING_HEAD), "PDP", "CMC", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID,"extractor","machines",
                EXTRACTOR.getItem(LV), of('C', CIRCUITS_BASIC, 'P', PISTONS, 'M', MACHINE_HULLS_ADVANCED, 'G', getForgelikeItemTag("glass")), "GMG", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID,"compressor","machines",
                COMPRESSOR.getItem(LV), of('C', CIRCUITS_BASIC, 'P', PISTONS, 'M', MACHINE_HULLS_ADVANCED, 'S', getForgelikeItemTag("stone")), "S S", "PMP", "SCS");
        provider.addItemRecipe(output, GT4RRef.ID,"forge_hammer","machines",
                FORGE_HAMMER.getItem(LV), of('C', CIRCUITS_BASIC, 'P', PISTONS, 'M', MACHINE_HULLS_ADVANCED, 'A', Items.ANVIL), " P ", "CMC", " A ");
        provider.addItemRecipe(output, GT4RRef.ID,"e_furnace","machines",
                FURNACE.getItem(LV), of('C', CIRCUITS_BASIC, 'S', STEAM_FURNACE.getItem(STEEL), 'R', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Redstone)), " C ", "RSR");
        provider.addItemRecipe(output, GT4RRef.ID,"wiremill","machines",
                WIRE_MILL.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', MACHINE_HULLS_ADVANCED, 'D', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(Brass)), "BDB", "CMC", "BcB");
        provider.addItemRecipe(output, GT4RRef.ID,"alloy_smelter","machines",
                ALLOY_SMELTER.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', FURNACE.getItem(LV), 'D', CupronickelHeatingCoil, 'B', AntimatterMaterialTypes.PLATE.getMaterialTag(Invar)), "BDB", "CMC", "BcB");
        provider.addItemRecipe(output, GT4RRef.ID,"canner","machines",
                CANNER.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', MACHINE_HULLS_ADVANCED, 'T', AntimatterMaterialTypes.PLATE.getMaterialTag(Tin)), "TCT", "TMT", "TcT");
        provider.addItemRecipe(output, GT4RRef.ID,"plate_bender","machines",
                BENDER.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', COMPRESSOR.getItem(LV), 'P', PISTONS), "PCP", "McM", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID,"assembler","machines",
                ASSEMBLER.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'S', PLATES_STEELS, 'P', PISTONS), "CPC", "ScS", "CSC");
        //provider.addItemRecipe(output, Ref.ID,"printing_factory","machines",
        //        PRINTING_FACTORY.getItem(LV), of('C', CIRCUITS_BASIC, 'c', ConveyorModule, 'S', PLATES_STEELS, 'P', PISTONS), "SPS", "CcC", "SSS");
        provider.addItemRecipe(output, GT4RRef.ID,"centrifuge","machines",
                CENTRIFUGE.getItem(LV), of('C', CIRCUITS_ADVANCED, 'E', EXTRACTOR.getItem(LV), 'S', PLATES_STEELS, 'M', MACHINE_HULLS_ADVANCED), "SCS", "MEM", "SCS");
        provider.addItemRecipe(output, GT4RRef.ID,"universal_macerator","machines",
                MACERATOR.getItem(MV), of('D', GRINDING_HEAD, 'M', MACERATOR.getItem(LV), 'S', AntimatterMaterialTypes.PLATE.getMaterialTag(Titanium), 'H', MACHINE_HULLS_ADVANCED), "SDS", "SMS", "SHS");
        provider.addItemRecipe(output, GT4RRef.ID,"fluid_canner","machines",
                FLUID_CANNER.getItem(LV), of('C', CIRCUITS_BASIC, 'c', CANNER.getItem(LV), 'T', getForgelikeItemTag("plates/tin"), 'P', FLUID_PIPE_BRONZE.getBlockItem(PipeSize.SMALL), 'E', CellTin), " C ", "EcE", "TPT");
        provider.addItemRecipe(output, GT4RRef.ID,"lathe","machines",
                LATHE.getItem(LV), of('c', ConveyorModule, 'G', GEARS_STEELS, 'H', MACHINE_HULLS_ADVANCED, 'C', CIRCUITS_ADVANCED, 'P',  PLATES_STEELS), "PCP", "GcG", "PHP");
        provider.addItemRecipe(output, GT4RRef.ID,"cutter","machines",
                CUTTER.getItem(LV), of('D', DiamondSawBlade, 'G', GEARS_STEELS, 'H', MACHINE_HULLS_ADVANCED, 'C', CIRCUITS_ADVANCED, 'P',  PLATES_STEELS), "PCP", "GDG", "PHP");
        provider.addItemRecipe(output, GT4RRef.ID, "extruder", "machines",
                EXTRUDER.getItem(MV), of2('P', PLATES_TITAN_TUNGSTEEL, 'G', GEARS_TITAN_TUNGSTEEL, 'H', NichromeHeatingCoil, 'M', MACHINE_HULLS_ADVANCED, 'D', DiamondSawBlade, 'C', CIRCUITS_ELITE), "PGP", "HMD", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "disassembler", "machines",
                DISASSEMBLER.getItem(LV), of('R', PLATES_STEELS, 'A', EXTRACTOR.getItem(LV), 'W', ASSEMBLER.getItem(LV), 'E', CABLE_TIN.getBlockItem(PipeSize.VTINY), 'C', CIRCUITS_ADVANCED), "RAR", "ECE", "RWR");
        provider.addItemRecipe(output, GT4RRef.ID, "pump", "machines",
                PUMP.getItem(LV), of('c', CellTin, 'C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'P', FLUID_PIPE_BRONZE.getBlockItem(PipeSize.SMALL)),"cCc", "cMc", "PPP");
        provider.addItemRecipe(output, GT4RRef.ID, "ore_washer", "machines",
                ORE_WASHER.getItem(LV), of('m', MotorLV, 'C', CIRCUITS_BASIC, 'M', MACHINE_HULLS_ADVANCED, 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(AntimatterMaterials.Iron), 'B', Items.BUCKET),"PPP", "BMB", "mCm");
        provider.addItemRecipe(output, GT4RRef.ID, "thermal_centrifuge", "machines",
                THERMAL_CENTRIFUGE.getItem(MV), of('m', MotorMV, 'C', CopperCoil, 'M', MACHINE_HULLS_ADVANCED, 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(WroughtIron)),"CmC", "PMP", "PmP");
        provider.addItemRecipe(output, GT4RRef.ID, "watermill", "machines",
                WATERMILL.getItem(ULV), of('M', MACHINE_HULLS_ADVANCED, 'P', AntimatterMaterialTypes.PLATE.getMaterialTag(Steel), 'T', GT4RMaterialTags.TURBINE_BLADE.getMaterialTag(Bronze), 'C', CopperCoil),"PTP", "CMC", "PTP");
        provider.addItemRecipe(output, GT4RRef.ID, "bath", "machines",
                BATH.getItem(LV), of('S', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'M', MACHINE_HULLS_ADVANCED), "SSS", "S S", "SMS");
        provider.addItemRecipe(output, GT4RRef.ID, "sifter", "machines",
                SIFTER.getItem(LV), of('I', ItemFilter, 'M', MACHINE_HULLS_ADVANCED, 'P', PISTONS, 'C', CIRCUITS_BASIC), " I ", "PMP", "CIC");
        provider.addItemRecipe(output, GT4RRef.ID, "recycler", "machines",
                RECYCLER.getItem(LV), of('G', AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Glowstone), 'C', COMPRESSOR.getItem(LV), 'D', Items.DIRT, 'S', PLATES_STEELS), " G ", "DCD", "SDS");
        provider.addItemRecipe(output, GT4RRef.ID, "heat_exchanger", "machines",
                HEAT_EXCHANGER.getItem(LV), of('I', AntimatterMaterialTypes.PLATE.getMaterialTag(Invar), 'C', CopperCoil, 'P', FLUID_PIPE_INVAR.getBlock(PipeSize.SMALL), 'M', MACHINE_HULLS_ADVANCED), "ICI", "PMP", "ICI");
        provider.addItemRecipe(output, GT4RRef.ID, "fermenter", "machines", FERMENTER.getItem(LV), of('C', CIRCUITS_BASIC, 'G', ForgeCTags.GLASS, 'M', MACHINE_HULLS_ADVANCED, 'P', PumpModule), " P ", "GMG", " C ");
        provider.addItemRecipe(output, GT4RRef.ID, "distillery", "machines", DISTILLERY.getItem(LV), of('C', CIRCUITS_BASIC, 'G', ForgeCTags.GLASS, 'M', MACHINE_HULLS_ADVANCED, 'P', PumpModule, 'B', ROD.getMaterialTag(AntimatterMaterials.Blaze)), " B ", "CMC", "GPG");
        provider.addItemRecipe(output, GT4RRef.ID, "mass_fabricator", "machines",
                MASS_FABRICATOR.getItem(EV), of('C', CIRCUITS_MASTER, 'F', FUSION_COIL, 'L', BatteryEnergyOrb, 'T', TELEPORTER.getItem(HV)), "CTC", "FLF", "CTC");
        provider.addItemRecipe(output, GT4RRef.ID, "replicator", "machines",
                REPLICATOR.getItem(EV), of('R', REINFORCED_STONE, 'G', REINFORCED_GLASS, 'T',TELEPORTER.getItem(HV), 't', TRANSFORMER.getItem(HV), 'M', CABLE_ALUMINIUM.getBlock(PipeSize.SMALL)), "RGR", "TTT", "tMt");
        provider.addItemRecipe(output, GT4RRef.ID, "scanner", "machines",
                SCANNER.getItem(HV), of('P', PLATES_STEELS, 'G', REINFORCED_GLASS, 'M', MotorHV, 'C', ComputerMonitor, 'c', CIRCUITS_ADVANCED, 'm', MACHINE_HULLS_ADVANCED), "PGP", "MCM", "cmc");
        provider.addItemRecipe(output, GT4RRef.ID, "fluid_press", "machines", FLUID_PRESS.getItem(LV), of('C', CIRCUITS_BASIC, 'G', ForgeCTags.GLASS, 'M', MACHINE_HULLS_ADVANCED, 'P', PISTONS, 'p', PumpModule), " C ", "pMP", "GCG");
        provider.addItemRecipe(output, GT4RRef.ID, "fluid_solidifier", "machines", FLUID_SOLIDIFIER.getItem(LV), of('C', CIRCUITS_BASIC, 'G', ForgeCTags.GLASS, 'M', MACHINE_HULLS_ADVANCED, 'P', PumpModule, 'c', ForgeCTags.CHESTS_WOODEN), " G ", "PMP", "CcC");
        provider.addItemRecipe(output, GT4RRef.ID, "smelter", "machines", SMELTER.getItem(MV), of(
                'P', PLATE.getMaterialTag(Titanium),
                'S', FLUID_PIPE_STAINLESS_STEEL.getBlock(PipeSize.NORMAL),
                'H', MACHINE_HULLS_VERY_ADVANCED,
                'B', Items.BRICKS,
                'C', CIRCUITS_ELITE), "P P", "SHS", "BCB");
    }
    private static void loadUtilityBlockRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        AntimatterAPI.all(WorkbenchMachine.class).forEach(m -> {
            if (!m.getId().contains("charging")) {
                provider.addItemRecipe(output, GT4RRef.ID, "", "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(m.getMaterial()), 'C', ForgeCTags.CHESTS_WOODEN, 'c', Items.CRAFTING_TABLE, 'S', SCREWDRIVER.getTag()), "PSP", "PcP", "PCP");
            } else {
                provider.addItemRecipe(output, GT4RRef.ID, "", "machines", m.getItem(HV),
                        of('S', SCREWDRIVER.getTag(), 'w', WIRE_CUTTER.getTag(), 'W', Machine.get(m.getId().replace("charging_", ""), GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), 'c', CABLE_GOLD.getBlock(PipeSize.SMALL), 'C', CIRCUITS_ADVANCED, 'R', ROD.getMaterialTag(m.getMaterial())), "RCR", "SWw", "ccc");
            }
        });
        provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.WOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Bronze), 'W', ItemTags.PLANKS, 's', SAW.getTag()), "SCs", "WRW", "WRW");
        if (GTCoreBlocks.IRONWOOD_ITEM_BARREL != null) {
            provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.IRONWOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Iron), 'W', PLATE.getMaterialTag(GTCoreMaterials.Ironwood), 's', SAW.getTag()), "SCs", "WRW", "WRW");
        }
        provider.addItemRecipe(output, GT4RRef.ID, "dustbin", "machines",
                DUSTBIN.getItem(LV), of2('H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag(), 'S', AntimatterDefaultTools.SAW.getTag(), 'P', PLATES_STEELS, 'h', Items.HOPPER, 'R', RODS_STEELS), "HWS", "PhP", "RPR");
        provider.addItemRecipe(output, GT4RRef.ID, "electric_item_translocator", "machines",
                ELECTRIC_ITEM_TRANSLOCATOR.getItem(LV), of('B', BatteryRE, 'E', AntimatterMaterialTypes.PLATE.getMaterialTag(Electrum), 'C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', MACHINE_HULLS_BASIC), "EBE", "CcC", "EME");
        provider.addItemRecipe(output, GT4RRef.ID, "electric_fluid_translocator", "machines",
                ELECTRIC_FLUID_TRANSLOCATOR.getItem(LV), of('B', BatteryRE, 'E', AntimatterMaterialTypes.PLATE.getMaterialTag(Electrum), 'C', CIRCUITS_BASIC, 'c', Drain, 'M', MACHINE_HULLS_BASIC), "EBE", "CcC", "EME");
        provider.addItemRecipe(output, GT4RRef.ID, "electric_item_filter", "machines",
                ELECTRIC_ITEM_FILTER.getItem(LV), of2('B', ItemFilter, 'E', PLATES_IRON_ALUMINIUM, 'C', CIRCUITS_BASIC, 'c', ConveyorModule, 'M', MACHINE_HULLS_BASIC, 'h', ForgeCTags.CHESTS_WOODEN), "EBE", "hMc", "ECE");
        provider.addItemRecipe(output, GT4RRef.ID, "electric_type_filter", "machines",
                ELECTRIC_TYPE_FILTER.getItem(LV), of2('B', ItemFilter, 'E', PLATES_IRON_ALUMINIUM, 'C', CIRCUITS_ADVANCED, 'c', ConveyorModule, 'M', MACHINE_HULLS_BASIC, 'h', ForgeCTags.CHESTS_WOODEN), "EBE", "hMc", "ECE");
        provider.addItemRecipe(output, GT4RRef.ID, "digital_tank", "machines",
                DIGITAL_TANK.getItem(LV), of('S', PLATES_STEELS, 'D', DataOrb, 'C', ComputerMonitor), "SSS", "SDS", "SCS");
        provider.addItemRecipe(output, GT4RRef.ID, "digital_chest", "machines",
                DIGITAL_CHEST.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(Platinum), 'D', DataOrb, 'C', ComputerMonitor, 'S', CIRCUITS_ELITE), "PPP", "SDS", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "quantum_tank", "machines",
                QUANTUM_TANK.getItem(MAX), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(Platinum), 'Q', QUANTUM_CHEST.getItem(MAX), 'C', CIRCUITS_MASTER), "CPC", "PQP", "CPC");
        provider.addItemRecipe(output, GT4RRef.ID, "quantum_chest", "machines",
                QUANTUM_CHEST.getItem(MAX), ImmutableMap.<Character, Object>builder().put('D', DataOrb).put('C', ComputerMonitor).put('H', HIGHLY_ADVANCED_MACHINE_BLOCK).put('T', TELEPORTER.getItem(HV)).put('d', DIGITAL_CHEST.getItem(LV)).build(), "DCD", "HTH", "DdD");
        provider.addItemRecipe(output, GT4RRef.ID, "computer_cube", "machines",
                COMPUTER_CUBE.getItem(LV), of('C', CIRCUITS_MASTER, 'c', ComputerMonitor, 'B', MACHINE_HULLS_ADVANCED, 'D', CIRCUITS_ULTIMATE), "DcC", "cBc", "CcD");
        GT4RMaterialTags.CABINET.all().forEach(m -> {
            provider.addItemRecipe(output, GT4RRef.ID, "barrel_" + m.getId(), "machines", Machine.get(m.getId() + "_barrel", GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m), 'C', Items.BARREL, 'R', ROD.getMaterialTag(m), 'S', AntimatterDefaultTools.SAW.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "PRP", "SCW", "PRP");
            provider.addItemRecipe(output, GT4RRef.ID, "chest_" + m.getId(), "machines", Machine.get(m.getId() + "_chest", GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m), 'C', ForgeCTags.CHESTS_WOODEN, 'R', ROD.getMaterialTag(m), 'S', AntimatterDefaultTools.SAW.getTag(), 'W', AntimatterDefaultTools.WRENCH.getTag()), "SPW", "RCR", "PPP");
        });

        /*GT4RMaterialTags.LOCKER.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, "locker_" + m.getId(), "machines", "has_chest", provider.hasSafeItem(Machine.get(m.getId() + "_cabinet", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR)), Machine.get(m.getId() + "_locker", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m), 'R', AntimatterMaterialTypes.ROD.getMaterialTag(m), 'L', Items.LEATHER, 'C', Machine.get(m.getId() + "_cabinet", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR), 'M', GT4RMaterialTags.HULL.getMaterialTag(m)), "RLR", "LCL", "PMP");
        });
        GT4RMaterialTags.CHARGING_LOCKER.all().forEach(m -> {
            provider.addItemRecipe(output, Ref.ID, "charging_locker_" + m.getId(), "machines", "has_chest", provider.hasSafeItem(Machine.get(m.getId() + "_locker", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR)), Machine.get(m.getId() + "_charging_locker", Ref.ID).map(mch -> mch.getItem(HV)).orElse(Items.AIR), of('L', Machine.get(m.getId() + "_locker", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR), 'c', CABLE_GOLD.getBlockItem(PipeSize.VTINY), 'C', CIRCUITS_ADVANCED), "cCc", "cLc", "cCc");
        });*/
        provider.addItemRecipe(output, GT4RRef.ID, "hv_teleporter", "machines", TELEPORTER.getItem(HV), of('C', CIRCUITS_ADVANCED, 'S', MACHINE_HULLS_STABILIZED, 'D', AntimatterMaterialTypes.GEM.getMaterialTag(AntimatterMaterials.Diamond), 'c', CABLE_ELECTRUM.getBlock(PipeSize.TINY), 'f', FrequencyTransmitter), "CfC", "cSc", "CDC");
        provider.addItemRecipe(output, GT4RRef.ID, "luv_teleporter", "machines", TELEPORTER.getItem(LUV), of('T', TELEPORTER.getItem(HV), 'M', HIGHLY_ADVANCED_MACHINE_BLOCK, 'L', BatteryEnergyOrb, 'C', CIRCUITS_MASTER), "CTC", "TMT", "CLC");
        provider.addItemRecipe(output, "trash_bin", GTCoreBlocks.ENDER_GARBAGE_BIN.getItem(NONE),
                of('O', PLATE.getMaterialTag(Obsidian), 'I', PLATE.getMaterialTag(Iron), 'E', Items.ENDER_EYE), "OOO", "OEO", "III");
    }

    private static void loadHatchRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addItemRecipe(output, GT4RRef.ID, "item_input_hatch", "hatches", HATCH_ITEM_I.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', ForgeCTags.CHESTS_WOODEN), "PCP", "GMG", "PPP");
        provider.addItemRecipe(output, GT4RRef.ID, "item_output_hatch", "hatches", HATCH_ITEM_O.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', ForgeCTags.CHESTS_WOODEN), "PPP", "GMG", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "fluid_input_hatch", "hatches", HATCH_FLUID_I.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', ForgeCTags.GLASS), "PCP", "GMG", "PPP");
        provider.addItemRecipe(output, GT4RRef.ID, "fluid_output_hatch", "hatches", HATCH_FLUID_O.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', ForgeCTags.GLASS), "PPP", "GMG", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "ev_dynamo_hatch", "hatches", HATCH_DYNAMO.getItem(EV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', TierMaps.TIER_CABLES.get(EV).getBlock(PipeSize.VTINY)), "PPP", "GMG", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "iv_dynamo_hatch", "hatches", HATCH_DYNAMO.getItem(IV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', TierMaps.TIER_CABLES.get(IV).getBlock(PipeSize.VTINY)), "PPP", "GMG", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_item_injector", "hatches", FUSION_ITEM_INJECTOR.getItem(LV), of('P', ConveyorModule, 'c', CIRCUITS_MASTER, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK, 'C', ForgeCTags.CHESTS_WOODEN), "PCP", "cMc", "PcP");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_item_extractor", "hatches", FUSION_ITEM_EXTRACTOR.getItem(LV), of('P', ConveyorModule, 'c', CIRCUITS_MASTER, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK, 'C', ForgeCTags.CHESTS_WOODEN), "PcP", "cMc", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_fluid_injector", "hatches", FUSION_FLUID_INJECTOR.getItem(LV), of('P', PumpModule, 'c', CIRCUITS_MASTER, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK, 'C', ForgeCTags.GLASS), "PCP", "cMc", "PcP");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_fluid_extractor", "hatches", FUSION_FLUID_EXTRACTOR.getItem(LV), of('P', PumpModule, 'c', CIRCUITS_MASTER, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK, 'C', ForgeCTags.GLASS), "PcP", "cMc", "PCP");
        provider.addItemRecipe(output, GT4RRef.ID, "fusion_energy_injector", "hatches", FUSION_ENERGY_INJECTOR.getItem(IV), of('S', ItemSuperconductor, 'C', CIRCUITS_MASTER, 'T', SUPERCONDENSATOR.getItem(LUV)), "SCS", "CTC", "SCS");
        provider.addItemRecipe(output, GT4RRef.ID, "supercondensator", "machines", SUPERCONDENSATOR.getItem(LUV), of('C', CIRCUITS_MASTER, 'L', BatteryEnergyOrb, 'S', ItemSuperconductor, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK), "CLC", "SMS", "CLC");
        provider.addItemRecipe(output, GT4RRef.ID, "muffler_hatch", "hatches", HATCH_MUFFLER.getItem(LV), of('P', AntimatterMaterialTypes.PLATE.getMaterialTag(StainlessSteel), 'G', GEARS_STEELS, 'M', MACHINE_HULLS_ADVANCED, 'C', REINFORCED_GLASS), "PMP", "GCG", "PCP");
    }
}
