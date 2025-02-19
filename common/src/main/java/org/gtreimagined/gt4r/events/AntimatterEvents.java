package org.gtreimagined.gt4r.events;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.event.forge.AntimatterWorldGenEvent;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.datagen.GT4RBlockLootProvider;
import org.gtreimagined.gt4r.datagen.GT4RBlockTagProvider;
import org.gtreimagined.gt4r.datagen.GT4RItemTagProvider;
import org.gtreimagined.gt4r.datagen.ProgressionAdvancements;
import org.gtreimagined.gt4r.loader.IntCircuitJeiLoader;
import org.gtreimagined.gt4r.loader.MaterialRecipeLoader;
import org.gtreimagined.gt4r.loader.WorldGenLoader;
import org.gtreimagined.gt4r.loader.crafting.BlockCrafting;
import org.gtreimagined.gt4r.loader.crafting.MachineCrafting;
import org.gtreimagined.gt4r.loader.crafting.ModCompatRecipes;
import org.gtreimagined.gt4r.loader.crafting.Parts;
import org.gtreimagined.gt4r.loader.crafting.ToolCrafting;
import org.gtreimagined.gt4r.loader.crafting.ToolCraftingTableRecipes;
import org.gtreimagined.gt4r.loader.crafting.VanillaOverrides;
import org.gtreimagined.gt4r.loader.crafting.WoodCrafting;
import org.gtreimagined.gt4r.loader.machines.AlloySmelterLoader;
import org.gtreimagined.gt4r.loader.machines.AssemblyLoader;
import org.gtreimagined.gt4r.loader.machines.BathingLoader;
import org.gtreimagined.gt4r.loader.machines.BendingLoader;
import org.gtreimagined.gt4r.loader.machines.CannerLoader;
import org.gtreimagined.gt4r.loader.machines.CentrifugingLoader;
import org.gtreimagined.gt4r.loader.machines.ChemicalReactorLoader;
import org.gtreimagined.gt4r.loader.machines.CompressorLoader;
import org.gtreimagined.gt4r.loader.machines.CutterLoader;
import org.gtreimagined.gt4r.loader.machines.DistillingLoader;
import org.gtreimagined.gt4r.loader.machines.DustbinLoader;
import org.gtreimagined.gt4r.loader.machines.ElectrolyzerLoader;
import org.gtreimagined.gt4r.loader.machines.ExtractorLoader;
import org.gtreimagined.gt4r.loader.machines.ExtruderLoader;
import org.gtreimagined.gt4r.loader.machines.FermentingLoader;
import org.gtreimagined.gt4r.loader.machines.FluidCanningLoader;
import org.gtreimagined.gt4r.loader.machines.FluidPressLoader;
import org.gtreimagined.gt4r.loader.machines.FluidSolidifierLoader;
import org.gtreimagined.gt4r.loader.machines.ForgeHammerLoader;
import org.gtreimagined.gt4r.loader.machines.FurnaceLoader;
import org.gtreimagined.gt4r.loader.machines.LatheLoader;
import org.gtreimagined.gt4r.loader.machines.MaceratorLoader;
import org.gtreimagined.gt4r.loader.machines.MassFabLoader;
import org.gtreimagined.gt4r.loader.machines.OreByproducts;
import org.gtreimagined.gt4r.loader.machines.SiftingLoader;
import org.gtreimagined.gt4r.loader.machines.SmelterLoader;
import org.gtreimagined.gt4r.loader.machines.ThermalCentrifuge;
import org.gtreimagined.gt4r.loader.machines.WasherLoader;
import org.gtreimagined.gt4r.loader.machines.WiremillLoader;
import org.gtreimagined.gt4r.loader.machines.generator.FuelBurnHandler;
import org.gtreimagined.gt4r.loader.machines.generator.HeatExchangerLoader;
import org.gtreimagined.gt4r.loader.machines.generator.SolidFuelBoilerHandler;
import org.gtreimagined.gt4r.loader.multi.Blasting;
import org.gtreimagined.gt4r.loader.multi.CokePyrolysisOven;
import org.gtreimagined.gt4r.loader.multi.DistillationTower;
import org.gtreimagined.gt4r.loader.multi.Fusion;
import org.gtreimagined.gt4r.loader.multi.ImplosionCompressor;
import org.gtreimagined.gt4r.loader.multi.IndustrialGrinder;
import org.gtreimagined.gt4r.loader.multi.IndustrialSawmill;
import org.gtreimagined.gt4r.loader.multi.VacFreezer;

import java.util.function.BiConsumer;

@Mod.EventBusSubscriber(modid = GT4RRef.ID)
public class AntimatterEvents {

    @SubscribeEvent
    public static void registerWorldgen(AntimatterWorldGenEvent event){
        WorldGenLoader.init(event);
    }

    @SubscribeEvent
    public static void registerRecipeLoaders(AntimatterLoaderEvent event) {
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> event.registrat.add(GT4RRef.ID, a, b);
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
        loader.accept("fluid_press", FluidPressLoader::init);
        loader.accept("fluid_solidifier", FluidSolidifierLoader::init);
        loader.accept("bathing", BathingLoader::init);
        loader.accept("solid_fuel_boiler", SolidFuelBoilerHandler::init);
        loader.accept("smelter", SmelterLoader::init);
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
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_IE)){
            event.addLoader(ModCompatRecipes::loadIE);
        }
    }

    public static void onProviders(AntimatterProvidersEvent event){
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        event.addProvider(() -> {
            p[0] = new GT4RBlockTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Block Tags"), false);
            return p[0];
        });
        event.addProvider(() -> new GT4RItemTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Item Tags"), false, p[0]));
        event.addProvider(() -> new AntimatterFluidTagProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Fluid Tags"), false));

        event.addProvider(() -> new AntimatterAdvancementProvider(GT4RRef.ID, GT4RRef.NAME.concat(" Advancements"), new ProgressionAdvancements()));

        event.addProvider(() -> new GT4RBlockLootProvider(GT4RRef.ID, GT4RRef.NAME.concat( " Loot generator")));
    }
}
