package trinsdar.gt4r.events;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.AntimatterCraftingEvent;
import muramasa.antimatter.event.AntimatterLoaderEvent;
import muramasa.antimatter.event.AntimatterProvidersEvent;
import muramasa.antimatter.event.AntimatterWorldGenEvent;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.datagen.GT4RBlockLootProvider;
import trinsdar.gt4r.datagen.GT4RBlockTagProvider;
import trinsdar.gt4r.datagen.GT4RItemTagProvider;
import trinsdar.gt4r.datagen.ProgressionAdvancements;
import trinsdar.gt4r.loader.IntCircuitJeiLoader;
import trinsdar.gt4r.loader.MaterialRecipeLoader;
import trinsdar.gt4r.loader.WorldGenLoader;
import trinsdar.gt4r.loader.crafting.BlockCrafting;
import trinsdar.gt4r.loader.crafting.MachineCrafting;
import trinsdar.gt4r.loader.crafting.ModCompatRecipes;
import trinsdar.gt4r.loader.crafting.Parts;
import trinsdar.gt4r.loader.crafting.ToolCrafting;
import trinsdar.gt4r.loader.crafting.ToolCraftingTableRecipes;
import trinsdar.gt4r.loader.crafting.VanillaOverrides;
import trinsdar.gt4r.loader.crafting.WoodCrafting;
import trinsdar.gt4r.loader.machines.AlloySmelterLoader;
import trinsdar.gt4r.loader.machines.AssemblyLoader;
import trinsdar.gt4r.loader.machines.BathingLoader;
import trinsdar.gt4r.loader.machines.BendingLoader;
import trinsdar.gt4r.loader.machines.CannerLoader;
import trinsdar.gt4r.loader.machines.CentrifugingLoader;
import trinsdar.gt4r.loader.machines.ChemicalReactorLoader;
import trinsdar.gt4r.loader.machines.CompressorLoader;
import trinsdar.gt4r.loader.machines.CutterLoader;
import trinsdar.gt4r.loader.machines.DistillingLoader;
import trinsdar.gt4r.loader.machines.DustbinLoader;
import trinsdar.gt4r.loader.machines.ElectrolyzerLoader;
import trinsdar.gt4r.loader.machines.ExtractorLoader;
import trinsdar.gt4r.loader.machines.ExtruderLoader;
import trinsdar.gt4r.loader.machines.FermentingLoader;
import trinsdar.gt4r.loader.machines.FluidCanningLoader;
import trinsdar.gt4r.loader.machines.FluidExtractorLoader;
import trinsdar.gt4r.loader.machines.FluidSolidifierLoader;
import trinsdar.gt4r.loader.machines.ForgeHammerLoader;
import trinsdar.gt4r.loader.machines.FurnaceLoader;
import trinsdar.gt4r.loader.machines.LatheLoader;
import trinsdar.gt4r.loader.machines.MaceratorLoader;
import trinsdar.gt4r.loader.machines.OreByproducts;
import trinsdar.gt4r.loader.machines.SiftingLoader;
import trinsdar.gt4r.loader.machines.ThermalCentrifuge;
import trinsdar.gt4r.loader.machines.WasherLoader;
import trinsdar.gt4r.loader.machines.WiremillLoader;
import trinsdar.gt4r.loader.machines.generator.CoalBoilerHandler;
import trinsdar.gt4r.loader.machines.generator.FuelBurnHandler;
import trinsdar.gt4r.loader.machines.generator.HeatExchangerLoader;
import trinsdar.gt4r.loader.multi.Blasting;
import trinsdar.gt4r.loader.multi.CokePyrolysisOven;
import trinsdar.gt4r.loader.multi.DistillationTower;
import trinsdar.gt4r.loader.multi.ImplosionCompressor;
import trinsdar.gt4r.loader.multi.IndustrialGrinder;
import trinsdar.gt4r.loader.multi.IndustrialSawmill;
import trinsdar.gt4r.loader.multi.VacFreezer;

import java.util.function.BiConsumer;

public class AntimatterEvents {

    public static void registerWorldgen(AntimatterWorldGenEvent event){
        WorldGenLoader.init(event);
    }

    @SubscribeEvent
    public static void registerRecipeLoaders(AntimatterLoaderEvent event) {
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> event.registrat.add(Ref.ID, a, b);
        loader.accept("wiremill", WiremillLoader::init);
        loader.accept("washer", WasherLoader::init);
        loader.accept("blasting", Blasting::init);
        loader.accept("industrial_grinding", IndustrialGrinder::init);
        loader.accept("bending", BendingLoader::init);
        loader.accept("forge_hammer", ForgeHammerLoader::init);
        loader.accept("assembling", AssemblyLoader::init);
        loader.accept("chemical_reactor", ChemicalReactorLoader::init);
        loader.accept("fuels", FuelBurnHandler::init);
        loader.accept("heat_exchanger", HeatExchangerLoader::init);
        loader.accept("fluid_extractor", FluidExtractorLoader::init);
        loader.accept("fluid_solidifier", FluidSolidifierLoader::init);
        loader.accept("bathing", BathingLoader::init);
        loader.accept("coal_boiler", CoalBoilerHandler::init);
        loader.accept("distilling", DistillingLoader::init);
        loader.accept("fermenting", FermentingLoader::init);
        loader.accept("industrial_sawmilling", IndustrialSawmill::init);

        loader.accept("electrolyer", ElectrolyzerLoader::init);
        loader.accept("fluid_canning", FluidCanningLoader::init);
        loader.accept("canner", CannerLoader::init);
        loader.accept("centrifuging", CentrifugingLoader::init);
        loader.accept("extractor", ExtractorLoader::init);
        loader.accept("compressor", CompressorLoader::init);
        loader.accept("vac_freezer", VacFreezer::init);
        loader.accept("ore_byproducts", OreByproducts::init);
        loader.accept("macerating_automatic", MaceratorLoader::initAuto);
        loader.accept("macerating_manual", MaceratorLoader::initManual);
        loader.accept("sifting", SiftingLoader::init);
        loader.accept("thermal_centrifuge", ThermalCentrifuge::init);
        loader.accept("alloy_smelter", AlloySmelterLoader::init);
        loader.accept("cutter", CutterLoader::init);
        loader.accept("lathe", LatheLoader::init);
        loader.accept("implosion_compressor", ImplosionCompressor::init);
        loader.accept("distillation_tower", DistillationTower::init);
        loader.accept("extruder", ExtruderLoader::init);
        loader.accept("coke_and_pyrolysis", CokePyrolysisOven::init);
        loader.accept("furnace", FurnaceLoader::init);
        loader.accept("dustbin", DustbinLoader::init);
        loader.accept("int_circuit_jei", IntCircuitJeiLoader::init);
    }

    @SubscribeEvent
    public static void registerCraftingLoaders(AntimatterCraftingEvent event){
        event.addLoader(Parts::loadRecipes);
        event.addLoader(ToolCraftingTableRecipes::loadRecipes);
        event.addLoader(MachineCrafting::loadRecipes);
        event.addLoader(MaterialRecipeLoader::loadRecipes);
        event.addLoader(FurnaceLoader::loadRecipes);
        event.addLoader(BlockCrafting::loadRecipes);
        event.addLoader(ToolCrafting::loadRecipes);
        event.addLoader(VanillaOverrides::loadRecipes);
        event.addLoader(WoodCrafting::loadRecipes);
        if (AntimatterAPI.isModLoaded(Ref.MOD_IE)){
            event.addLoader(ModCompatRecipes::loadIE);
        }
    }

    @SubscribeEvent
    public static void onProviders(AntimatterProvidersEvent event){
        if (event.getSide() == Dist.CLIENT) return;
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        event.addProvider(Ref.ID, g -> {
            p[0] = new GT4RBlockTagProvider(Ref.ID, Ref.NAME.concat(" Block Tags"), false, g, new ExistingFileHelperOverride());
            return p[0];
        });
        event.addProvider(Ref.ID, g -> new GT4RItemTagProvider(Ref.ID, Ref.NAME.concat(" Item Tags"), false, g, p[0], new ExistingFileHelperOverride()));
        event.addProvider(Ref.ID, g -> new AntimatterFluidTagProvider(Ref.ID, Ref.NAME.concat(" Fluid Tags"), false, g));

        event.addProvider(Ref.ID, g -> new AntimatterAdvancementProvider(Ref.ID, Ref.NAME.concat(" Advancements"), g, new ProgressionAdvancements()));

        event.addProvider(Ref.ID, g -> new GT4RBlockLootProvider(Ref.ID, Ref.NAME.concat( " Loot generator"),g));
    }
}
