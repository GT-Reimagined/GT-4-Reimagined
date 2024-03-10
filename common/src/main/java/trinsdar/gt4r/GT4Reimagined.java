package trinsdar.gt4r;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import io.github.gregtechintergalactical.gtcore.item.ItemPowerUnit;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.tool.IBasicAntimatterTool;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.gt4r.config.OreConfigHandler;
import trinsdar.gt4r.data.*;
import trinsdar.gt4r.data.client.ScreenFactories;
import trinsdar.gt4r.datagen.GT4RItemModelProvider;
import trinsdar.gt4r.datagen.GT4RLocalizations;
import trinsdar.gt4r.datagen.GT4RRandomDropBonus;
import trinsdar.gt4r.material.GT4RMaterialEvent;

import java.util.Arrays;
import java.util.stream.Collectors;


public class GT4Reimagined extends AntimatterMod {

    public static GT4Reimagined INSTANCE;
    public static IProxyHandler PROXY;
    public static Logger LOGGER = LogManager.getLogger(GT4RRef.ID);

    public GT4Reimagined() {
        super();
        INSTANCE = this;
    }

    @Override
    public void onRegistrarInit() {
        super.onRegistrarInit();
        AntimatterDynamics.clientProvider(GT4RRef.ID, () -> new AntimatterBlockStateProvider(GT4RRef.ID, GT4RRef.NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(GT4RRef.ID, () -> new GT4RItemModelProvider(GT4RRef.ID, GT4RRef.NAME + " Item Models"));
        AntimatterDynamics.clientProvider(GT4RRef.ID, GT4RLocalizations.en_US::new);
        GT4RConfig.createConfig();
        new GT4RLateRegistrar();
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        switch (event) {
            case DATA_INIT -> {
                ToolTypes.init();
                GT4RMaterialTags.init();
                Materials.init();
                Attributes.init();
                RecipeMaps.init();
                MenuHandlers.init();
                GT4RData.init(side);
                Machines.init();
                RecipeMaps.postInit();
                Guis.init(side);
                Models.init();
                Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(GT4RRef.ID, "random_drop_bonus"), GT4RRandomDropBonus.RANDOM_DROP_BONUS);
                TierMaps.buildTierMaps();
                AntimatterJEIREIPlugin.addItemsToHide(l -> {
                    l.addAll(Arrays.asList(GTCoreItems.GlassTube, GTCoreItems.VacuumTube, GTCoreItems.NandChip, GTCoreItems.EtchedWiringMV, GTCoreItems.EtchedWiringHV,
                            GTCoreItems.EtchedWiringEV, GTCoreItems.EngravedCrystalChip, GTCoreItems.EngravedLapotronChip, GTCoreItems.Transistor, GTCoreItems.SMDTransistor,
                            GTCoreItems.Resistor, GTCoreItems.SMDResistor, GTCoreItems.Diode, GTCoreItems.SMDDiode, GTCoreItems.Capacitor, GTCoreItems.SMDCapacitor,
                            GTCoreItems.CircuitBoardEmpty, GTCoreItems.CircuitBoardProcessorEmpty, GTCoreItems.CircuitBoardCoated, GTCoreItems.CircuitBoardPhenolic,
                            GTCoreItems.CircuitBoardPlastic, GTCoreItems.CircuitBoardEpoxy, GTCoreItems.CircuitBoardFiber, GTCoreItems.CircuitBoardMultiFiber,
                            GTCoreItems.CircuitBoardWetware, GTCoreItems.CircuitGood, GTCoreItems.CircuitComplex, GTCoreItems.CircuitFuturistic,
                            GTCoreItems.Circuit3D, GTCoreItems.CircuitInfinite));
                    l.add(GT4RData.AdvancedWrenchAlt);
                    l.add(GT4RData.ElectricWrenchAlt);
                    if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()){
                        l.addAll(Arrays.asList(GT4RData.Drill, GT4RData.DiamondDrill, GT4RData.AdvancedDrill, GT4RData.Chainsaw, GT4RData.AdvancedChainsaw,
                                GT4RData.ElectricWrench, GT4RData.AdvancedWrench,GT4RData.ElectricScrewdriver, GT4RData.RockCutter));
                    } else {
                        if (!AntimatterAPI.isModLoaded("gti")){
                            l.addAll(AntimatterAPI.all(IAntimatterTool.class).stream().filter(i -> i.getAntimatterToolType().isPowered()).map(IBasicAntimatterTool::getItem).toList());
                            l.addAll(AntimatterAPI.all(ItemPowerUnit.class));
                        } else {
                            l.add(GT4RData.RockCutterPowerUnit);
                            l.add(AntimatterAPI.get(IAntimatterTool.class, "rock_cutter").getItem());
                        }
                    }
                });
            }
            case DATA_READY -> {
                if (AntimatterAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)) {
                    //GEM.forceOverride(Amethyst, ForgeRegistries.ITEMS.getValue(new ResourceLocation("bluepower", "amethyst_gem")));
                }
                Structures.init();
                Structures.initPatterns();
                OreConfigHandler.ORE_CONFIG_HANDLER.save();
            }
            case CLIENT_DATA_INIT -> ScreenFactories.init();
        }
    }

    @Override
    public int getPriority() {
        return 800;
    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        event = new GT4RMaterialEvent();
        GT4RMaterialEvents.onMaterialEvent((GT4RMaterialEvent) event);
    }

    @Override
    public String getId() {
        return GT4RRef.ID;
    }
}
