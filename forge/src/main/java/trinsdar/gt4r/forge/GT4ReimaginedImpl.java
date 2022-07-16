package trinsdar.gt4r.forge;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.network.forge.AntimatterNetworkImpl;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.network.MessageCraftingSync;
import trinsdar.gt4r.proxy.ClientHandler;
import trinsdar.gt4r.proxy.CommonHandler;
import trinsdar.gt4r.proxy.ServerHandler;

@Mod(Ref.ID)
public class GT4ReimaginedImpl {
    public GT4ReimaginedImpl(){
        new GT4Reimagined();
        GT4Reimagined.PROXY = DistExecutor.runForDist(() -> ClientHandler::new, () -> ServerHandler::new); // todo: scheduled to change in new Forge
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup();
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup();
        ((AntimatterNetworkImpl)Antimatter.NETWORK).registerMessage(MessageCraftingSync.class, MessageCraftingSync::encodeStatic, MessageCraftingSync::decodeStatic, (msg, ctx) -> {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> msg.handleClient(context.getSender()));
            context.setPacketHandled(true);
        });
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event){
    }
}
