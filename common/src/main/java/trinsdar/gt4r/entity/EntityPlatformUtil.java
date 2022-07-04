package trinsdar.gt4r.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EntityType;

public class EntityPlatformUtil {
    @ExpectPlatform
    public static EntityType.EntityFactory<SpearEntity> factory(){
        return null;
    }

    @ExpectPlatform
    public static EntityType.Builder<SpearEntity> builder(EntityType.Builder<SpearEntity> builder){
        return builder;
    }
}
