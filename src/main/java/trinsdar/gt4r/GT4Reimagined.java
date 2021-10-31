package trinsdar.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterDynamics;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.AntimatterLoaderEvent;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.RegistrationEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.gt4r.client.BakedModels;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Guis;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.MenuHandlers;
import trinsdar.gt4r.data.Models;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.data.Structures;
import trinsdar.gt4r.data.ToolTypes;
import trinsdar.gt4r.datagen.GT4RBlockLootProvider;
import trinsdar.gt4r.datagen.GT4RBlockTagProvider;
import trinsdar.gt4r.datagen.GT4RItemModelProvider;
import trinsdar.gt4r.datagen.GT4RItemTagProvider;
import trinsdar.gt4r.datagen.GT4RLocalizations;
import trinsdar.gt4r.datagen.GT4RRecipes;
import trinsdar.gt4r.datagen.ProgressionAdvancements;
import trinsdar.gt4r.events.ForgeEventBusEvents;
import trinsdar.gt4r.events.RegistrationEvents;
import trinsdar.gt4r.events.RemappingEvents;
import trinsdar.gt4r.loader.IntCircuitJeiLoader;
import trinsdar.gt4r.loader.WorldGenLoader;
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
import trinsdar.gt4r.network.GT4RNetwork;
import trinsdar.gt4r.proxy.ClientHandler;
import trinsdar.gt4r.proxy.CommonHandler;
import trinsdar.gt4r.proxy.ServerHandler;
import trinsdar.gt4r.tile.TileEntityTypes;
import trinsdar.gt4r.worldgen.GT4RConfiguredFeatures;
import trinsdar.gt4r.worldgen.GT4RFeatures;

import java.util.function.BiConsumer;


@Mod(Ref.ID)
public class GT4Reimagined extends AntimatterMod {

    public static GT4Reimagined INSTANCE;
    public static IProxyHandler PROXY;
    public static Logger LOGGER = LogManager.getLogger(Ref.ID);

    public GT4Reimagined() {
        super();
        INSTANCE = this;
        PROXY = DistExecutor.runForDist(() -> ClientHandler::new, () -> ServerHandler::new); // todo: scheduled to change in new Forge
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        new ForgeEventBusEvents();
        MinecraftForge.EVENT_BUS.register(RemappingEvents.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        FMLJavaModLoadingContext.get().getModEventBus().register(RegistrationEvents.class);
        AntimatterDynamics.addProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterDynamics.addProvider(Ref.ID, g -> new GT4RItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
        AntimatterDynamics.addProvider(Ref.ID, g -> {
            p[0] = new GT4RBlockTagProvider(Ref.ID, Ref.NAME.concat(" Block Tags"), false, g, new ExistingFileHelperOverride());
            return p[0];
        });
        AntimatterDynamics.addProvider(Ref.ID, g -> new GT4RItemTagProvider(Ref.ID, Ref.NAME.concat(" Item Tags"), false, g, p[0], new ExistingFileHelperOverride()));
        AntimatterDynamics.addProvider(Ref.ID, g -> new AntimatterFluidTagProvider(Ref.ID, Ref.NAME.concat(" Fluid Tags"), false, g, new ExistingFileHelperOverride()));
        AntimatterDynamics.addProvider(Ref.ID, g -> new GT4RRecipes(Ref.ID, Ref.NAME.concat(" Recipes"), g));
        AntimatterDynamics.addProvider(Ref.ID, g -> new AntimatterAdvancementProvider(Ref.ID, Ref.NAME.concat(" Advancements"), g, new ProgressionAdvancements()));
        AntimatterDynamics.addProvider(Ref.ID, GT4RLocalizations.en_US::new);
        AntimatterDynamics.addProvider(Ref.ID, g -> new GT4RBlockLootProvider(Ref.ID, Ref.NAME.concat( " Loot generator"),g));

        Attributes.init();
        MinecraftForge.EVENT_BUS.addListener(GT4Reimagined::registerRecipeLoaders);
    }

    private static void registerRecipeLoaders(AntimatterLoaderEvent event) {
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
        loader.accept("macerating_automatic", MaceratorLoader::init);
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

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup(e);
        DeferredWorkQueue.runLater(GT4RNetwork::init);

    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event){
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Dist side) {
        switch (event) {
            case DATA_INIT:
                ToolTypes.init();
                Materials.init();
                RecipeMaps.init();
                MenuHandlers.init();
                GT4RData.init(side);
                Machines.init();
                GT4RFeatures.init();
                RecipeMaps.postInit();
                TileEntityTypes.init();
                Guis.init(side);
                Models.init();
                if (side == Dist.CLIENT){
                    BakedModels.init();
                }
                break;
            case DATA_READY:
                if (AntimatterAPI.isModLoaded(Ref.MOD_BLUEPOWER)){
                    //GEM.forceOverride(Amethyst, ForgeRegistries.ITEMS.getValue(new ResourceLocation("bluepower", "amethyst_gem")));
                }
                GT4RData.buildTierMaps();
                Structures.init();
                GT4RConfiguredFeatures.init();
                //GregTechAPI.registerFluidCell(Data.CellTin.get(1));
                //GregTechAPI.registerFluidCell(Data.CellSteel.get(1));
                //GregTechAPI.registerFluidCell(Data.CellTungstensteel.get(1));

//                AntimatterAPI.registerCover(Data.COVER_PLATE);
//                AntimatterAPI.registerCover(Data.COVER_CONVEYOR);
//                AntimatterAPI.registerCover(Data.COVER_PUMP);

//                AntimatterAPI.registerCoverStack(Data.ConveyorLV.get(1), new CoverConveyor(Tier.LV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorMV.get(1), new CoverConveyor(Tier.MV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorHV.get(1), new CoverConveyor(Tier.HV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorEV.get(1), new CoverConveyor(Tier.EV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorIV.get(1), new CoverConveyor(Tier.IV));
//                AntimatterAPI.registerCoverStack(Data.PumpLV.get(1), new CoverPump(Tier.LV));
//                AntimatterAPI.registerCoverStack(Data.PumpMV.get(1), new CoverPump(Tier.MV));
//                AntimatterAPI.registerCoverStack(Data.PumpHV.get(1), new CoverPump(Tier.HV));
//                AntimatterAPI.registerCoverStack(Data.PumpEV.get(1), new CoverPump(Tier.EV));
//                AntimatterAPI.registerCoverStack(Data.PumpIV.get(1), new CoverPump(Tier.IV));
//                MaterialType.PLATE.all().forEach(m -> AntimatterAPI.registerCoverStack(MaterialType.PLATE.get(m, 1), Data.COVER_PLATE));
                break;
            case WORLDGEN_INIT:
                WorldGenLoader.init();
                break;
        }
    }

    @Override
    public int getPriority() {
        return 800;
    }

    @Override
    public String getId() {
        return Ref.ID;
    }
}
