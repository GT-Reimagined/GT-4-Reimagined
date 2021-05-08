package trinsdar.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.AntimatterDynamics;
import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.datagen.resources.DynamicDataPackFinder;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import trinsdar.gt4r.data.*;
import trinsdar.gt4r.datagen.GT4RBlockLootProvider;
import trinsdar.gt4r.datagen.GT4RBlockTagProvider;
import trinsdar.gt4r.datagen.GT4RDataPackFinder;
import trinsdar.gt4r.datagen.GT4RItemTagProvider;
import trinsdar.gt4r.datagen.GT4RLocalizations;
import trinsdar.gt4r.datagen.GT4RRecipes;
import trinsdar.gt4r.datagen.ProgressionAdvancements;
import trinsdar.gt4r.loader.WorldGenLoader;
import trinsdar.gt4r.loader.machines.*;
import trinsdar.gt4r.loader.machines.generator.CoalBoilerHandler;
import trinsdar.gt4r.loader.machines.generator.FuelBurnHandler;
import trinsdar.gt4r.loader.multi.CokeOven;
import trinsdar.gt4r.loader.multi.DistillationTower;
import trinsdar.gt4r.loader.multi.Blasting;
import trinsdar.gt4r.loader.multi.ImplosionCompressor;
import trinsdar.gt4r.loader.multi.IndustrialGrinder;
import trinsdar.gt4r.loader.multi.VacFreezer;
import trinsdar.gt4r.proxy.ClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.gt4r.proxy.CommonHandler;
import trinsdar.gt4r.proxy.ServerHandler;
import trinsdar.gt4r.tile.TileEntityTypes;
import trinsdar.gt4r.worldgen.GT4RFeatures;


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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
        //GregTechAPI.addRegistrar(new ForestryRegistrar());
        //GregTechAPI.addRegistrar(new GalacticraftRegistrar());
        //if (ModList.get().isLoaded(Ref.MOD_UB)) GregTechAPI.addRegistrar(new UndergroundBiomesRegistrar());
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];

        AntimatterDynamics.addProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterDynamics.addProvider(Ref.ID, g -> new AntimatterItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
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

        GT4RFeatures.init();
        Attributes.init();
        registerRecipeLoaders();
    }

    private void registerRecipeLoaders() {
        IRecipeRegistrate loader = AntimatterAPI.getRecipeRegistrate();
        loader.add(WiremillLoader::init);
        loader.add(WasherLoader::init);
        loader.add(Blasting::init);
        loader.add(IndustrialGrinder::init);
        loader.add(BendingLoader::init);
        loader.add(ForgeHammerLoader::init);
        loader.add(AssemblyLoader::init);
        loader.add(ChemicalReactorLoader::init);
        loader.add(FuelBurnHandler::init);
        loader.add(FluidExtractorLoader::init);
        loader.add(BathingLoader::init);
        loader.add(CoalBoilerHandler::init);

        loader.add(ElectrolyzerLoader::init);
        loader.add(FluidCanningLoader::init);
        loader.add(CannerLoader::init);
        loader.add(CentrifugingLoader::init);
        loader.add(ExtractorLoader::init);
        loader.add(CompressorLoader::init);
        loader.add(VacFreezer::init);
        loader.add(OreByproducts::init);
        loader.add(MaceratorLoader::init);
        loader.add(SiftingLoader::init);
        loader.add(ThermalCentrifuge::init);
        loader.add(AlloySmelterLoader::init);
        loader.add(CutterLoader::init);
        loader.add(LatheLoader::init);
        loader.add(ImplosionCompressor::init);
        loader.add(DistillationTower::init);
        loader.add(ExtruderLoader::init);
        loader.add(CokeOven::init);
        loader.add(FurnaceLoader::init);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup(e);

    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event){
        MinecraftForge.EVENT_BUS.register(GT4RDataPackFinder.class);
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Dist side) {
        switch (event) {
            case DATA_INIT:
                Materials.init();
                GT4RData.init(side);
                Machines.init();
                TileEntityTypes.init();
                Guis.init(side);
                Models.init();
                break;
            case DATA_READY:
                GT4RData.buildTierMaps();
                Structures.init();
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
    public String getId() {
        return Ref.ID;
    }
}
