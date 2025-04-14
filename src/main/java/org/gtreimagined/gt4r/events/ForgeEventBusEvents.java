package org.gtreimagined.gt4r.events;

import org.gtreimagined.gtlib.tool.IGTTool;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;

import static org.gtreimagined.gtlib.data.GTTools.PICKAXE;
import static org.gtreimagined.gtcore.data.GTCoreTools.DRILL;

@Mod.EventBusSubscriber(modid = GT4RRef.ID)
public class ForgeEventBusEvents {

    @SubscribeEvent
    public static void onRightlickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        boolean server = !event.getWorld().isClientSide;
        if (hand == InteractionHand.OFF_HAND && server){
            if (player.getMainHandItem().getItem() instanceof IGTTool && (((IGTTool)player.getMainHandItem().getItem()).getGTToolType() == PICKAXE || ((IGTTool)player.getMainHandItem().getItem()).getGTToolType() == DRILL) && (player.getOffhandItem().getItem() == Items.TORCH || player.getOffhandItem().getItem() == Items.SOUL_TORCH)){
                player.sendMessage(new TranslatableComponent("message.gt4r.pickaxe_torch_right_click"), player.getUUID());
            }
        }
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
