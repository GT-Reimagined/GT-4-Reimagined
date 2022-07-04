package trinsdar.gt4r.entity.fabric;

import net.minecraft.world.entity.EntityType;
import trinsdar.gt4r.entity.SpearEntity;

public class EntityPlatformUtilImpl {
    public static EntityType.EntityFactory<SpearEntity> factory(){
        return SpearEntity::new;
    }

    public static EntityType.Builder<SpearEntity> builder(EntityType.Builder<SpearEntity> builder){
        return builder;
    }
}
