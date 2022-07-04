package trinsdar.gt4r.entity.forge;

import net.minecraft.world.entity.EntityType;
import trinsdar.gt4r.entity.SpearEntity;

public class EntityPlatformUtilImpl {
    public static EntityType.EntityFactory<SpearEntity> factory(){
        return SpearEntityForge::new;
    }

    public static EntityType.Builder<SpearEntity> builder(EntityType.Builder<SpearEntity> builder){
        return builder.setShouldReceiveVelocityUpdates(true).setCustomClientFactory(SpearEntityForge::new);
    }
}
