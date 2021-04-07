package trinsdar.gt4r.events;

import muramasa.antimatter.Antimatter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.mixin.PlayerEntityMixin;

public class CommonEvents {
    @SubscribeEvent
    public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        Antimatter.LOGGER.info("Seting player attributes");
        event.put(EntityType.PLAYER, PlayerEntity.func_234570_el_().createMutableAttribute(Attributes.ATTACK_REACH.get()).create());
    }
}
