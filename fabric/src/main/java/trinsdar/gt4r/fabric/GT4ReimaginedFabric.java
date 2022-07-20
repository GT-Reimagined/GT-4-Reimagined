package trinsdar.gt4r.fabric;

import io.github.fabricators_of_create.porting_lib.event.common.PlayerTickEvents;
import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.LoaderEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import muramasa.antimatter.event.fabric.WorldGenEvents;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.events.AntimatterEvents;
import trinsdar.gt4r.events.CommonEvents;
import trinsdar.gt4r.network.MessageCraftingSync;

public class GT4ReimaginedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WorldGenEvents.WORLD_GEN.register(AntimatterEvents::registerWorldgen);
        LoaderEvents.LOADER.register(AntimatterEvents::registerRecipeLoaders);
        CraftingEvents.CRAFTING.register(AntimatterEvents::registerCraftingLoaders);
        ProviderEvents.PROVIDERS.register(AntimatterEvents::onProviders);
        PlayerTickEvents.END.register(player -> CommonEvents.onPlayerTick(player, AntimatterPlatformUtils.isServer()));
        ModConfigEvent.LOADING.register(GT4RConfig::onModConfigEvent);
        ModConfigEvent.RELOADING.register(GT4RConfig::onModConfigEvent);
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            CommonEvents.onRightlickBlock(player, hand, AntimatterPlatformUtils.isServer());
            return InteractionResult.PASS;
        });
        ServerPlayNetworking.registerGlobalReceiver(Ref.SYNC_ID, ((server, player, handler, buf, responseSender) -> {
            MessageCraftingSync sync = MessageCraftingSync.decodeStatic(buf);
            server.execute(() -> {
                sync.handleClient(player);
            });
        }));
    }
}
