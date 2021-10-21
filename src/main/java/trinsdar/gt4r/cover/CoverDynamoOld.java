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
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
