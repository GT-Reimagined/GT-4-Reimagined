package trinsdar.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterDynamics;
import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import trinsdar.gt4r.data.*;
import trinsdar.gt4r.datagen.GT4RBlockLootProvider;
import trinsdar.gt4r.datagen.GT4RBlockTagProvider;
import trinsdar.gt4r.datagen.GT4RItemModelProvider;
import trinsdar.gt4r.datagen.GT4RItemTagProvider;
import trinsdar.gt4r.datagen.GT4RLocalizations;
import trinsdar.gt4r.datagen.GT4RRecipes;
import trinsdar.gt4r.datagen.ProgressionAdvancements;
import trinsdar.gt4r.loader.IntCircuitJeiLoader;
import trinsdar.gt4r.loader.WorldGenLoader;
import trinsdar.gt4r.loader.machines.*;
import trinsdar.gt4r.loader.machines.generator.CoalBoilerHandler;
import trinsdar.gt4r.loader.machines.generator.FuelBurnHandler;
import trinsdar.gt4r.loader.machines.generator.HeatExchangerLoader;
import trinsdar.gt4r.loader.multi.CokePyrolysisOven;
import trinsdar.gt4r.loader.multi.DistillationTower;
import trinsdar.gt4r.loader.multi.Blasting;
import trinsdar.gt4r.loader.multi.ImplosionCompressor;
import trinsdar.gt4r.loader.multi.IndustrialGrinder;
import trinsdar.gt4r.loader.multi.IndustrialSawmill;
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

import static muramasa.antimatter.Data.DRILL;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.Data.PICKAXE;
import static trinsdar.gt4r.data.Materials.Amethyst;


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
        MinecraftForge.EVENT_BUS.addListener(this::onRightlickBlock);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
        //GregTechAPI.addRegistrar(new ForestryRegistrar());
        //GregTechAPI.addRegistrar(new GalacticraftRegistrar());
        //if (ModList.get().isLoaded(Ref.MOD_UB)) GregTechAPI.addRegistrar(new UndergroundBiomesRegistrar());
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

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
        loader.add(HeatExchangerLoader::init);
        loader.add(FluidExtractorLoader::init);
        loader.add(FluidSolidifierLoader::init);
        loader.add(BathingLoader::init);
        loader.add(CoalBoilerHandler::init);
        loader.add(DistillingLoader::init);
        loader.add(FermentingLoader::init);
        loader.add(IndustrialSawmill::init);

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
        loader.add(CokePyrolysisOven::init);
        loader.add(FurnaceLoader::init);
        loader.add(DustbinLoader::init);
        loader.add(IntCircuitJeiLoader::init);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup(e);

    }

    @SubscribeEvent
    public void onRegister(final RegistryEvent.Register<?> e){
        if (e.getRegistry() == ForgeRegistries.ENTITIES){
            IForgeRegistry<EntityType<?>> reg = (IForgeRegistry<EntityType<?>>) e.getRegistry();
            reg.register(GT4RData.SPEAR_ENTITY_TYPE.setRegistryName(new ResourceLocation(Ref.ID, "spear")));
        }
    }

    public void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        PlayerEntity player = event.getPlayer();
        Hand hand = event.getHand();
        boolean server = !event.getWorld().isRemote;
        if (hand == Hand.OFF_HAND && server){
            if (player.getHeldItemMainhand().getItem() instanceof IAntimatterTool && (((IAntimatterTool)player.getHeldItemMainhand().getItem()).getAntimatterToolType() == PICKAXE || ((IAntimatterTool)player.getHeldItemMainhand().getItem()).getAntimatterToolType() == DRILL) && (player.getHeldItemOffhand().getItem() == Items.TORCH || player.getHeldItemOffhand().getItem() == Items.SOUL_TORCH)){
                player.sendMessage(new TranslationTextComponent("message.gt4r.pickaxe_torch_right_click"), player.getUniqueID());
            }
        }
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
                GT4RData.init(side);
                Machines.init();
                RecipeMaps.postInit();
                TileEntityTypes.init();
                Guis.init(side);
                Models.init();
                break;
            case DATA_READY:
                if (AntimatterAPI.isModLoaded(Ref.MOD_BLUEPOWER)){
                    GEM.forceOverride(Amethyst, ForgeRegistries.ITEMS.getValue(new ResourceLocation("bluepower", "amethyst_gem")));
                }
                GT4RData.buildTierMaps();
                Structures.init();
                GT4RFeatures.init();
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
