package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.CoverDynamo;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;

public class CoverDynamoOld extends CoverDynamo {
    public CoverDynamoOld(String id) {
        super(id);
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicModel();
    }
}
