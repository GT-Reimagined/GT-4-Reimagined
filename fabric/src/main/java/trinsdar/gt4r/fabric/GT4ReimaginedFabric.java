package trinsdar.gt4r.fabric;

import io.github.fabricators_of_create.porting_lib.event.common.PlayerTickEvents;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.LoaderEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import muramasa.antimatter.event.fabric.WorldGenEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.config.ModConfig;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.events.AntimatterEvents;
import trinsdar.gt4r.events.CommonEvents;

public class GT4ReimaginedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfigEvent.LOADING.register(GT4RConfig::onModConfigEvent);
        ModConfigEvent.RELOADING.register(GT4RConfig::onModConfigEvent);
        ModLoadingContext.registerConfig(Ref.ID, ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
        WorldGenEvents.WORLD_GEN.register(AntimatterEvents::registerWorldgen);
        LoaderEvents.LOADER.register(AntimatterEvents::registerRecipeLoaders);
        CraftingEvents.CRAFTING.register(AntimatterEvents::registerCraftingLoaders);
        ProviderEvents.PROVIDERS.register(AntimatterEvents::onProviders);
        PlayerTickEvents.END.register(player -> CommonEvents.onPlayerTick(player, AntimatterAPI.getSIDE().isServer()));
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            CommonEvents.onRightlickBlock(player, hand, AntimatterAPI.getSIDE().isServer());
            return InteractionResult.PASS;
        });
    }
}
