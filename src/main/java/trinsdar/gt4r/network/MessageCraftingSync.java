package trinsdar.gt4r.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageCraftingSync {
    public static void handle(MessageCraftingSync msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                AbstractContainerMenu container = player.containerMenu;
                if (container != null) {
                    container.slotsChanged(null);
                }
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }

    public static MessageCraftingSync decode(FriendlyByteBuf buffer){
        return new MessageCraftingSync();
    }

    public static void encode(MessageCraftingSync message, FriendlyByteBuf buffer) {
    }
}
