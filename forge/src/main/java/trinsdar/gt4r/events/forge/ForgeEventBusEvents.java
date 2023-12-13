package trinsdar.gt4r.events.forge;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.events.CommonEvents;

@Mod.EventBusSubscriber(modid = GT4RRef.ID)
public class ForgeEventBusEvents {

    @SubscribeEvent
    public static void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        boolean server = !event.getWorld().isClientSide;
        CommonEvents.onRightlickBlock(player, hand, server);
    }

    @SubscribeEvent
    public static void remapMissingBlocks(final RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> map : event.getMappings(GT4RRef.ID)) {
            String id = map.key.getPath();
            if (id.equals("rubber_log")){
                map.remap(GTCoreBlocks.RUBBER_LOG);
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTCoreBlocks.RUBBER_LEAVES);
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTCoreBlocks.RUBBER_SAPLING);
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingItems(final RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings(GT4RRef.ID)) {
            String id = map.key.getPath();
            if (id.equals("rubber_log")){
                map.remap(GTCoreBlocks.RUBBER_LOG.asItem());
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTCoreBlocks.RUBBER_LEAVES.asItem());
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTCoreBlocks.RUBBER_SAPLING.asItem());
            }
            if (id.equals("sticky_resin")){
                map.remap(GTCoreItems.StickyResin);
            }
        }
    }
}
