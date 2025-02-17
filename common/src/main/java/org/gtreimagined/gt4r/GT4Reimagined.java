package org.gtreimagined.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.tool.IBasicAntimatterTool;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.item.ItemPowerUnit;
import org.gtreimagined.gt4r.config.OreConfigHandler;
import org.gtreimagined.gt4r.data.Attributes;
import org.gtreimagined.gt4r.data.GT4RBlocks;
import org.gtreimagined.gt4r.data.GT4RCovers;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.GT4RMaterialEvents;
import org.gtreimagined.gt4r.data.GT4RMaterialTags;
import org.gtreimagined.gt4r.data.Guis;
import org.gtreimagined.gt4r.data.Machines;
import org.gtreimagined.gt4r.data.Materials;
import org.gtreimagined.gt4r.data.MenuHandlers;
import org.gtreimagined.gt4r.data.Models;
import org.gtreimagined.gt4r.data.RecipeMaps;
import org.gtreimagined.gt4r.data.Structures;
import org.gtreimagined.gt4r.data.TierMaps;
import org.gtreimagined.gt4r.data.ToolTypes;
import org.gtreimagined.gt4r.data.client.ScreenFactories;
import org.gtreimagined.gt4r.datagen.GT4RItemModelProvider;
import org.gtreimagined.gt4r.datagen.GT4RLocalizations;
import org.gtreimagined.gt4r.datagen.GT4RRandomDropBonus;
import org.gtreimagined.gt4r.material.GT4RMaterialEvent;

import java.util.Arrays;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt4r.data.Materials.Steel;


public class GT4Reimagined extends AntimatterMod {

    public static GT4Reimagined INSTANCE;
    public static IProxyHandler PROXY;
    public static Logger LOGGER = LogManager.getLogger(GT4RRef.ID);

    public GT4Reimagined() {
        super();
        INSTANCE = this;
        AntimatterDynamics.clientProvider(GT4RRef.ID, () -> new AntimatterBlockStateProvider(GT4RRef.ID, GT4RRef.NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(GT4RRef.ID, () -> new GT4RItemModelProvider(GT4RRef.ID, GT4RRef.NAME + " Item Models"));
        AntimatterDynamics.clientProvider(GT4RRef.ID, GT4RLocalizations.en_US::new);
        GT4RConfig.createConfig();
        new GT4RLateRegistrar();
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Dist side) {
        switch (event) {
            case DATA_INIT -> {
                ToolTypes.init();
                GT4RMaterialTags.init();
                Materials.init();
                Attributes.init();
                RecipeMaps.init();
                MenuHandlers.init();
                GT4RCovers.init();
                GT4RBlocks.init();
                GT4RItems.init(side);
                Machines.init();
                RecipeMaps.postInit();
                Guis.init(side);
                Models.init();
                Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(GT4RRef.ID, "random_drop_bonus"), GT4RRandomDropBonus.RANDOM_DROP_BONUS);
                TierMaps.buildTierMaps();
                AntimatterJEIREIPlugin.addItemsToHide(l -> {
                    l.addAll(Arrays.asList(GTCoreItems.NandChip, GTCoreItems.EtchedWiringMV, GTCoreItems.EtchedWiringHV,
                            GTCoreItems.EtchedWiringEV, GTCoreItems.EngravedCrystalChip, GTCoreItems.EngravedLapotronChip,
                            GTCoreItems.CircuitBoardEmpty, GTCoreItems.CircuitBoardProcessorEmpty, GTCoreItems.CircuitGood,
                            GTCoreItems.CircuitComplex, GTCoreItems.CircuitFuturistic,
                            GTCoreItems.Circuit3D, GTCoreItems.CircuitInfinite));
                    l.addAll(Machines.BATTERY_BUFFER_FOUR.getTiers().stream().filter(t -> t != Tier.LV).map(Machines.BATTERY_BUFFER_FOUR::getItem).toList());
                    l.addAll(Machines.BATTERY_BUFFER_EIGHT.getTiers().stream().filter(t -> t != Tier.LV).map(Machines.BATTERY_BUFFER_EIGHT::getItem).toList());
                    l.addAll(Arrays.asList(GT4RItems.AdvancedWrenchAlt, GT4RItems.ElectricWrenchAlt, GTCoreItems.Fertilizer));
                    if (GT4RConfig.GT5_ELECTRIC_TOOLS.get()){
                        l.addAll(Arrays.asList(GT4RItems.Drill, GT4RItems.DiamondDrill, GT4RItems.AdvancedDrill, GT4RItems.Chainsaw, GT4RItems.AdvancedChainsaw,
                                GT4RItems.ElectricWrench, GT4RItems.AdvancedWrench, GT4RItems.ElectricScrewdriver, GT4RItems.RockCutter));
                    } else {
                        if (!AntimatterAPI.isModLoaded("gt5r")){
                            l.addAll(AntimatterAPI.all(IAntimatterTool.class).stream().filter(i -> i.getAntimatterToolType().isPowered()).map(IBasicAntimatterTool::getItem).toList());
                            l.addAll(AntimatterAPI.all(ItemPowerUnit.class));
                            l.addAll(WRENCHBIT.all().stream().map(m -> WRENCHBIT.get(m)).toList());
                            l.addAll(CHAINSAWBIT.all().stream().map(m -> CHAINSAWBIT.get(m)).toList());
                            l.addAll(DRILLBIT.all().stream().map(m -> DRILLBIT.get(m)).toList());
                            l.addAll(BUZZSAW_BLADE.all().stream().filter(m -> m != Steel).map(m -> BUZZSAW_BLADE.get(m)).toList());
                        } else {
                            l.add(GT4RItems.RockCutterPowerUnit);
                            l.add(AntimatterAPI.get(IAntimatterTool.class, "rock_cutter", GT4RRef.ID).getItem());
                        }
                    }
                });
            }
            case DATA_READY -> {
                if (AntimatterAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)) {
                    //GEM.forceOverride(Amethyst, ForgeRegistries.ITEMS.getValue(new ResourceLocation("bluepower", "amethyst_gem")));
                }
                GT4RRemapping.init();
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
