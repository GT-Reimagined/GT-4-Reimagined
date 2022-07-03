package trinsdar.gt4r.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class MessageCraftingSync {

    public static void handle(MessageCraftingSync msg, ServerPlayer player) {
        if (player != null) {
            AbstractContainerMenu container = player.containerMenu;
            if (container != null) {
                container.slotsChanged(null);
            }
        }
    }

    public static MessageCraftingSync decode(FriendlyByteBuf buffer){
        return new MessageCraftingSync();
    }

    public static void encode(MessageCraftingSync message, FriendlyByteBuf buffer) {
    }
}
