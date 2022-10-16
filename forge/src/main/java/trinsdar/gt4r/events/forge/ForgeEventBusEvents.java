package trinsdar.gt4r.events.forge;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.events.CommonEvents;

import static muramasa.antimatter.Data.NULL;

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

    @SubscribeEvent
    public static void remapMissingBlocks(final RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> map : event.getMappings(Ref.ID)) {
            String id = map.key.getPath();
            if (id.equals("rubber_log")){
                map.remap(GTRubberData.RUBBER_LOG);
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTRubberData.RUBBER_LEAVES);
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTRubberData.RUBBER_SAPLING);
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingItems(final RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings(Ref.ID)) {
            String id = map.key.getPath();
            if (id.equals("rubber_log")){
                map.remap(GTRubberData.RUBBER_LOG.asItem());
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTRubberData.RUBBER_LEAVES.asItem());
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTRubberData.RUBBER_SAPLING.asItem());
            }
            if (id.equals("sticky_resin")){
                map.remap(GTRubberData.StickyResin);
            }
        }
    }
}
