package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;
import trinsdar.gt4r.Ref;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class MachineCrafting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(output, Ref.ID, "electrolyzer", "machines", "has_extractor", provider.hasSafeItem(EXTRACTOR.getItem(LV)),
                ELECTROLYZER.getItem(MV), of('C', getForgeItemTag("circuits/advanced"), 'P', getForgeItemTag("plates/steels"), 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil), "PEP", "CLC", "PEP");
        provider.addItemRecipe(output, Ref.ID, "industrial_grinder", "machines", "has_electrolyzer", provider.hasSafeItem(ELECTROLYZER.getItem(MV)),
                INDUSTRIAL_GRINDER.getItem(MV), of('E', ELECTROLYZER.getItem(MV),'C', getForgeItemTag("circuits/advanced"), 'M', getForgeItemTag("machine_hull/basic"), 'P', PUMP.getItem(LV), 'G', getForgeItemTag("grinding_head")), "ECP", "GGG", "CMC");
        provider.addItemRecipe(output, Ref.ID, "industrial_blast_furnace", "machines", "has_cupronickel_coils", provider.hasSafeItem(CupronickelHeatingCoil),
                BLAST_FURNACE.getItem(MV), of('C', getForgeItemTag("circuits/basic"), 'M', getForgeItemTag("machine_hull/basic"), 'H', CupronickelHeatingCoil, 'F', FURNACE.getItem(LV)), "CHC", "HMH", "FHF");
        provider.addItemRecipe(output, Ref.ID, "implosion_compressor", "machines", "has_compressor", provider.hasSafeItem(COMPRESSOR.getItem(LV)),
                IMPLOSION_COMPRESSOR.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'M', getForgeItemTag("machine_hull/basic"), 'c', COMPRESSOR.getItem(LV), 'A', AdvancedAlloy), "AMA", "CcC", "AMA");
        provider.addItemRecipe(output, Ref.ID, "industrial_sawmill", "machines", "has_pump", provider.hasSafeItem(PUMP.getItem(LV)),
                INDUSTRIAL_SAWMILL.getItem(MV), of('C', getForgeItemTag("circuits/advanced"), 'M', getForgeItemTag("machine_hull/basic"), 'P', PUMP.getItem(LV), 'S', DiamondSawBlade), "PCP", "SSS", "CMC");
        //TODO: add reinforced glass
        provider.addItemRecipe(output, Ref.ID, "vacuum_freezer", "machines", "has_pump", provider.hasSafeItem(PUMP.getItem(LV)),
                VACUUM_FREEZER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'G', Items.GLASS, 'P', PUMP.getItem(LV), 'A', PLATE.getMaterialTag(Aluminium)), "APA", "CGC", "APA");
        provider.addItemRecipe(output, Ref.ID, "chemical_reactor", "machines", "has_extractor", provider.hasSafeItem(EXTRACTOR.getItem(LV)),
                CHEMICAL_REACTOR.getItem(MV), of('C', getForgeItemTag("circuits/advanced"), 'P', getForgeItemTag("plates/invaraluminium"), 'E', EXTRACTOR.getItem(LV), 'L', CopperCoil, 'c', COMPRESSOR.getItem(LV)), "PLP", "CcC", "PEP");
        provider.addItemRecipe(output, Ref.ID, "distillation_tower", "machines", "has_centrifuge", provider.hasSafeItem(CENTRIFUGE.getItem(LV)),
                DISTILLATION_TOWER.getItem(MV), of('C', getForgeItemTag("circuits/master"), 'P', PUMP.getItem(LV), 'E', ELECTROLYZER.getItem(MV), 'c', CENTRIFUGE.getItem(LV), 'A', HIGHLY_ADVANCED_MACHINE_BLOCK), "cCc", "PAP", "ECE");
        provider.addItemRecipe(output, Ref.ID, "macerator_1", "machines", "has_hull", provider.hasSafeItem(getForgeItemTag("machine_hull/basic")),
                MACERATOR.getItem(LV), of('C', getForgeItemTag("circuits/advanced"), 'P', getForgeItemTag("plates/steels"), 'M', getForgeItemTag("machine_hull/basic"), 'D', DUST.getMaterialTag(Diamond)), "PDP", "DMD", "PCP");
        provider.addItemRecipe(output, Ref.ID, "macerator_2", "machines", "has_hull", provider.hasSafeItem(getForgeItemTag("machine_hull/basic")),
                MACERATOR.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'P', getForgeItemTag("plates/steels"), 'M', getForgeItemTag("machine_hull/basic"), 'D', getForgeItemTag("grinding_head")), "PDP", "CMC", "PCP");
        provider.addItemRecipe(output, Ref.ID,"extractor","machines", "has_piston", provider.hasSafeItem(getForgeItemTag("pistons")),
                EXTRACTOR.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/basic"), 'G', getForgeItemTag("glass")), "GMG", "PCP");
        provider.addItemRecipe(output, Ref.ID,"compressor","machines", "has_piston", provider.hasSafeItem(getForgeItemTag("pistons")),
                COMPRESSOR.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/basic"), 'S', getForgeItemTag("stone")), "S S", "PMP", "SCS");
        provider.addItemRecipe(output, Ref.ID,"forge_hammer","machines", "has_piston", provider.hasSafeItem(getForgeItemTag("pistons")),
                FORGE_HAMMER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/basic"), 'A', Items.ANVIL), " P ", "CMC", " A ");
        provider.addItemRecipe(output, Ref.ID,"e_furnace","machines", "has_steam_furnace", provider.hasSafeItem(STEAM_FURNACE.getItem(STEEL)),
                FURNACE.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'S', STEAM_FURNACE.getItem(STEEL), 'R', DUST.getMaterialTag(Redstone)), " C ", "RSR");
        provider.addItemRecipe(output, Ref.ID,"wiremill","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                WIRE_MILL.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'M', getForgeItemTag("machine_hull/basic"), 'D', GEM.getMaterialTag(Diamond), 'B', PLATE.getMaterialTag(Brass)), "BDB", "CMC", "BcB");
        provider.addItemRecipe(output, Ref.ID,"alloy_smelter","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                ALLOY_SMELTER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'M', FURNACE.getItem(LV), 'D', CupronickelHeatingCoil, 'B', PLATE.getMaterialTag(Invar)), "BDB", "CMC", "BcB");
        provider.addItemRecipe(output, Ref.ID,"canner","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                CANNER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'M', getForgeItemTag("machine_hull/basic"), 'T', PLATE.getMaterialTag(Tin)), "TCT", "TMT", "TcT");
        provider.addItemRecipe(output, Ref.ID,"plate_bender","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                BENDER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'M', COMPRESSOR.getItem(LV), 'P', getForgeItemTag("pistons")), "PCP", "McM", "PCP");
        provider.addItemRecipe(output, Ref.ID,"assembler","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                ASSEMBLER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'S', getForgeItemTag("plates/steels"), 'P', getForgeItemTag("pistons")), "CPC", "ScS", "CSC");
        //provider.addItemRecipe(output, Ref.ID,"printing_factory","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
        //        PRINTING_FACTORY.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', ConveyorModule, 'S', getForgeItemTag("plates/steels"), 'P', getForgeItemTag("pistons")), "SPS", "CcC", "SSS");
        provider.addItemRecipe(output, Ref.ID,"centrifuge","machines", "has_extractor", provider.hasSafeItem(EXTRACTOR.getItem(LV)),
                CENTRIFUGE.getItem(LV), of('C', getForgeItemTag("circuits/advanced"), 'E', EXTRACTOR.getItem(LV), 'S', getForgeItemTag("plates/steels"), 'M', getForgeItemTag("machine_hull/basic")), "SCS", "MEM", "SCS");
        provider.addItemRecipe(output, Ref.ID,"universal_macerator","machines", "has_macerator", provider.hasSafeItem(MACERATOR.getItem(LV)),
                UNIVERSAL_MACERATOR.getItem(MV), of('D', getForgeItemTag("grinding_head"), 'M', MACERATOR.getItem(LV), 'S', PLATE.getMaterialTag(Titanium), 'H', getForgeItemTag("machine_hull/very_advanced")), "SDS", "SMS", "SHS");
        provider.addItemRecipe(output, Ref.ID,"fluid_canner","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                FLUID_CANNER.getItem(LV), of('C', getForgeItemTag("circuits/basic"), 'c', CANNER.getItem(LV), 'T', getForgeItemTag("plates/tin"), 'P', FLUID_PIPE_BRONZE.getBlockItem(PipeSize.SMALL), 'E', CellTin), " C ", "EcE", "TPT");
        provider.addItemRecipe(output, Ref.ID,"large_gas_turbine","machines", "has_gas_turbine", provider.hasSafeItem(GAS_TURBINE.getItem(LV)),
                LARGE_GAS_TURBINE.getItem(IV), of('G', GAS_TURBINE.getItem(LV), 'T', getForgeItemTag("gears/titantungsteel"), 'H', getForgeItemTag("machine_hull/very_advanced"), 'C', getForgeItemTag("circuits/master")), "GGG", "THT", "GCG");
        provider.addItemRecipe(output, Ref.ID,"large_steam_turbine","machines", "has_steam_turbine", provider.hasSafeItem(STEAM_TURBINE.getItem(LV)),
                LARGE_STEAM_TURBINE.getItem(EV), of('G', STEAM_TURBINE.getItem(LV), 'T', getForgeItemTag("gears/steels"), 'H', getForgeItemTag("machine_hull/basic"), 'C', getForgeItemTag("circuits/advanced")), "GGG", "THT", "GCG");
        provider.addItemRecipe(output, Ref.ID,"thermal_boiler","machines", "has_thermal_generator", provider.hasSafeItem(THERMAL_GENERATOR.getItem(LV)),
                THERMAL_BOILER.getItem(LV), of('G', THERMAL_GENERATOR.getItem(LV), 'T', getForgeItemTag("gears/titantungsteel"), 'M', CENTRIFUGE.getItem(LV), 'C', getForgeItemTag("circuits/elite")), "GMG", "TCT", "GMG");
        provider.addItemRecipe(output, Ref.ID,"lathe","machines", "has_conveyor_module", provider.hasSafeItem(ConveyorModule),
                LATHE.getItem(LV), of('c', ConveyorModule, 'G', getForgeItemTag("gears/steels"), 'H', getForgeItemTag("machine_hull/basic"), 'C', getForgeItemTag("circuits/advanced"), 'P',  getForgeItemTag("plates/steels")), "PCP", "GcG", "PHP");
        provider.addItemRecipe(output, Ref.ID,"cutter","machines", "has_sawblade", provider.hasSafeItem(DiamondSawBlade),
                CUTTER.getItem(LV), of('D', DiamondSawBlade, 'G', getForgeItemTag("gears/steels"), 'H', getForgeItemTag("machine_hull/basic"), 'C', getForgeItemTag("circuits/advanced"), 'P',  getForgeItemTag("plates/steels")), "PCP", "GDG", "PHP");
        provider.addItemRecipe(output, Ref.ID,"small_coil_boiler","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                COAL_BOILER.getItem(BRONZE), of( 'P',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, Ref.ID,"steam_macerator","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_MACERATOR.getItem(BRONZE), CraftingHelper.of2( 'P',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'H', HAMMER.getTag(), 'D', GEM.getMaterialTag(Diamond), 'M', getForgeItemTag("machine_hull/cheap"), 'p', getForgeItemTag("pistons"), 'G', GEAR.getMaterialTag(Bronze)), "WDH", "GMG", "PpP");
        provider.addItemRecipe(output, Ref.ID,"steam_furnace","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_FURNACE.getItem(BRONZE), of( 'P',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE, 'M', getForgeItemTag("machine_hull/cheap")), "PWP", "PFP", "BMB");
        provider.addItemRecipe(output, Ref.ID,"steam_alloy_smelter","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_ALLOY_SMELTER.getItem(BRONZE), of( 'P',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'B', Items.BRICKS, 'F', STEAM_FURNACE.getItem(BRONZE)), "PPP", "FWF", "BBB");
        provider.addItemRecipe(output, Ref.ID,"steam_forge_hammer","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_FORGE_HAMMER.getItem(BRONZE), of( 'B',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'G', GEAR.getMaterialTag(Bronze), 'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/cheap")), "GPG", "BWB", "BMB");
        provider.addItemRecipe(output, Ref.ID,"steam_compressor","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_COMPRESSOR.getItem(BRONZE), of( 'B',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(), 'G', GEAR.getMaterialTag(Bronze), 'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/cheap")), "BGB", "PWP", "BMB");
        provider.addItemRecipe(output, Ref.ID,"steam_extractor","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_EXTRACTOR.getItem(BRONZE), of( 'B',  getForgeItemTag("plates/bronze"), 'W', WRENCH.getTag(),'P', getForgeItemTag("pistons"), 'M', getForgeItemTag("machine_hull/cheap")), "BBB", "PWP", "BMB");
        provider.addItemRecipe(output, Ref.ID,"high_pressure_coil_boiler","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                COAL_BOILER.getItem(STEEL), of( 'P',  getForgeItemTag("plates/steels"), 'W', WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, Ref.ID,"high_pressure_steam_furnace","machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                STEAM_FURNACE.getItem(STEEL), of( 'P',  getForgeItemTag("plates/steels"), 'W', WRENCH.getTag(), 'B', Items.BRICKS, 'F', Items.FURNACE, 'M', getForgeItemTag("machine_hull/semi_cheap")), "PWP", "PFP", "BMB");


    }

}
