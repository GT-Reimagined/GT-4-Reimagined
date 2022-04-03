package trinsdar.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterDynamics;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.proxy.IProxyHandler;
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
import trinsdar.gt4r.config.OreConfigHandler;
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
import trinsdar.gt4r.datagen.GT4RItemModelProvider;
import trinsdar.gt4r.datagen.GT4RLocalizations;
import trinsdar.gt4r.events.AntimatterEvents;
import trinsdar.gt4r.events.ForgeEventBusEvents;
import trinsdar.gt4r.events.RegistrationEvents;
import trinsdar.gt4r.events.RemappingEvents;
import trinsdar.gt4r.loader.WorldGenLoader;
import trinsdar.gt4r.network.GT4RNetwork;
import trinsdar.gt4r.proxy.ClientHandler;
import trinsdar.gt4r.proxy.CommonHandler;
import trinsdar.gt4r.proxy.ServerHandler;
import trinsdar.gt4r.tile.TileEntityTypes;
import trinsdar.gt4r.tree.RubberTreeWorldGen;
import trinsdar.gt4r.worldgen.GT4RConfiguredFeatures;
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
        new ForgeEventBusEvents();
        MinecraftForge.EVENT_BUS.register(RemappingEvents.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().register(RegistrationEvents.class);
        AntimatterDynamics.clientProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterDynamics.clientProvider(Ref.ID, g -> new GT4RItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
        AntimatterDynamics.clientProvider(Ref.ID, GT4RLocalizations.en_US::new);

        Attributes.init();
        MinecraftForge.EVENT_BUS.register(AntimatterEvents.class);
    }



    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup(e);
        e.enqueueWork(GT4RNetwork::init);
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
                Structures.initPatterns();
                RubberTreeWorldGen.init();
                GT4RConfiguredFeatures.init();
                OreConfigHandler.ORE_CONFIG_HANDLER.save();
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
                //WorldGenLoader.init();
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
