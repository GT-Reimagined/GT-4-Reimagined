package trinsdar.gt4r.events.forge;

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
            String domain = map.key.getNamespace();
            String id = map.key.getPath();

        }
    }

    @SubscribeEvent
    public static void remapMissingItems(final RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings(Ref.ID)) {
            
        }
    }
}
