package trinsdar.gt4r.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import trinsdar.gt4r.Ref;

public class GT4RNetwork {
    public static SimpleChannel handler;

    public static void init() {
        handler = NetworkRegistry.newSimpleChannel(new ResourceLocation(Ref.ID, "network"), () -> "1.0", client -> true, server -> true);

        handler.registerMessage(0, MessageCraftingSync.class, MessageCraftingSync::encode, MessageCraftingSync::decode, MessageCraftingSync::handle);
        //wrapper.registerMessage(2, MessageUpdateTextfield.class, MessageUpdateTextfield.class, Dist.DEDICATED_SERVER);
        //wrapper.registerMessage(3, MessageCircuitDatabaseTemplate.class, MessageCircuitDatabaseTemplate.class, Dist.DEDICATED_SERVER);
        //wrapper.registerMessage(4, MessageCircuitDatabaseTemplate.class, MessageCircuitDatabaseTemplate.class, Dist.CLIENT);
        //wrapper.registerMessage(5, MessageDebugBlock.class, MessageDebugBlock.class, Dist.CLIENT);
        //wrapper.registerMessage(6, MessageSendClientServerTemplates.class, MessageSendClientServerTemplates.class, Dist.CLIENT);
        //wrapper.registerMessage(7, MessageRedirectTubeStack.class, MessageRedirectTubeStack.class, Dist.CLIENT);
        //wrapper.registerMessage(8, MessageServerTickTime.class, MessageServerTickTime::encode, MessageServerTickTime::decode, MessageServerTickTime::handle);

        //wrapper.registerMessage(9, MessageWirelessNewFreq.class, MessageWirelessNewFreq.class, Dist.DEDICATED_SERVER);
        //wrapper.registerMessage(10, MessageWirelessSaveFreq.class, MessageWirelessSaveFreq.class, Dist.DEDICATED_SERVER);
        //wrapper.registerMessage(11, MessageWirelessFrequencySync.class, MessageWirelessFrequencySync.class, Dist.CLIENT);
        //wrapper.registerMessage(12, MessageWirelessRemoveFreq.class, MessageWirelessRemoveFreq.class, Dist.DEDICATED_SERVER);

        //wrapper.registerMessage(13, MessageSyncMachineBacklog.class, MessageSyncMachineBacklog.class, Dist.CLIENT);
    }
}
