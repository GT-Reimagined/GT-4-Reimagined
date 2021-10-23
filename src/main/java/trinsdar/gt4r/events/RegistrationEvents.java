package trinsdar.gt4r.events;

import muramasa.antimatter.Antimatter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.mixin.PlayerEntityMixin;
import trinsdar.gt4r.tree.RubberFoliagePlacer;

public class RegistrationEvents {
    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> e){
        IForgeRegistry<EntityType<?>> reg = e.getRegistry();
        reg.register(GT4RData.SPEAR_ENTITY_TYPE.setRegistryName(new ResourceLocation(Ref.ID, "spear")));
    }

    @SubscribeEvent
    public static void onRegisterFoilagePlacers(final RegistryEvent.Register<FoliagePlacerType<?>> e){
        e.getRegistry().register(RubberFoliagePlacer.RUBBER.setRegistryName(Ref.ID, "rubber_foilage_placer"));
    }
}
