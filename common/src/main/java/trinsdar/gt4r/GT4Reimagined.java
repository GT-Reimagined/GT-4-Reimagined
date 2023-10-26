package trinsdar.gt4r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.proxy.IProxyHandler;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
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
            }
            case DATA_READY -> {
                if (AntimatterAPI.isModLoaded(GT4RRef.MOD_BLUEPOWER)) {
                    //GEM.forceOverride(Amethyst, ForgeRegistries.ITEMS.getValue(new ResourceLocation("bluepower", "amethyst_gem")));
                }
                TierMaps.buildTierMaps();
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
