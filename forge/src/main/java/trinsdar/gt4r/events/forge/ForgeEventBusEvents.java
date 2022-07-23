package trinsdar.gt4r.events.forge;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.events.CommonEvents;

@Mod.EventBusSubscriber(modid = Ref.ID)
public class ForgeEventBusEvents {

    @SubscribeEvent
    public static void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        boolean server = !event.getWorld().isClientSide;
        CommonEvents.onRightlickBlock(player, hand, server);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.END){
            CommonEvents.onPlayerTick(event.player, event.side.isServer());
        }
    }
}
