package trinsdar.gt4r.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.gt4r.Ref;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.Data.PLATE;
import static muramasa.antimatter.Data.WRENCH;
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

    }
}
