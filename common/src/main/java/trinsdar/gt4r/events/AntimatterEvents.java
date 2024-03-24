package trinsdar.gt4r.events;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.CraftingEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.event.WorldGenEvent;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import trinsdar.gt4r.GT4RRef;
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
import trinsdar.gt4r.loader.machines.MassFabLoader;
import trinsdar.gt4r.loader.machines.OreByproducts;
import trinsdar.gt4r.loader.machines.SiftingLoader;
import trinsdar.gt4r.loader.machines.ThermalCentrifuge;
import trinsdar.gt4r.loader.machines.WasherLoader;
import trinsdar.gt4r.loader.machines.WiremillLoader;
import trinsdar.gt4r.loader.machines.generator.SolidFuelBoilerHandler;
import trinsdar.gt4r.loader.machines.generator.FuelBurnHandler;
import trinsdar.gt4r.loader.machines.generator.HeatExchangerLoader;
import trinsdar.gt4r.loader.multi.Blasting;
import trinsdar.gt4r.loader.multi.CokePyrolysisOven;
import trinsdar.gt4r.loader.multi.DistillationTower;
import trinsdar.gt4r.loader.multi.Fusion;
import trinsdar.gt4r.loader.multi.ImplosionCompressor;
import trinsdar.gt4r.loader.multi.IndustrialGrinder;
import trinsdar.gt4r.loader.multi.IndustrialSawmill;
import trinsdar.gt4r.loader.multi.VacFreezer;

import java.util.function.BiConsumer;

public class AntimatterEvents {

    public static void registerWorldgen(WorldGenEvent event){
        WorldGenLoader.init(event);
    }

    public static void registerRecipeLoaders(IAntimatterRegistrar registrar, IRecipeRegistrate reg) {
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> reg.add(GT4RRef.ID, a, b);
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
        loader.accept("solid_fuel_boiler", SolidFuelBoilerHandler::init);
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
        loader.accept("mass_fab", MassFabLoader::init);
        loader.accept("fusion", Fusion::init);
    }

    public static void registerCraftingLoaders(CraftingEvent event){
        event.addLoader(Parts::loadRecipes);
        event.addLoader(ToolCraftingTableRecipes::loadRecipes);
        event.addLoader(MachineCrafting::loadRecipes);
        event.addLoader(MaterialRecipeLoader::loadRecipes);
        event.addLoader(FurnaceLoader::loadRecipes);
        event.addLoader(BlockCrafting::loadRecipes);
        event.addLoader(ToolCrafting::loadRecipes);
        event.addLoader(VanillaOverrides::loadRecipes);
        event.addLoader(WoodCrafting::loadRecipes);
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_IE)){
            event.addLoader(ModCompatRecipes::loadIE);
        }
    }

    public static void onProviders(ProvidersEvent event){
        //if (event.getSide() == Side.CLIENT) return;
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        event.addProvider(GT4RRef.ID, () -> {
            p[0] = new GT4RBlockTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Block Tags"), false);
            return p[0];
        });
        event.addProvider(GT4RRef.ID, () -> new GT4RItemTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Item Tags"), false, p[0]));
        event.addProvider(GT4RRef.ID, () -> new AntimatterFluidTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Fluid Tags"), false));

        event.addProvider(GT4RRef.ID, () -> new AntimatterAdvancementProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Advancements"), new ProgressionAdvancements()));

        event.addProvider(GT4RRef.ID, () -> new GT4RBlockLootProvider(GT4RRef.ID, GT4RRef.NAME.concat( " Loot generator")));
    }
}
