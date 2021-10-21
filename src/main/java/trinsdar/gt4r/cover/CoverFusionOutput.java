package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.CoverOutput;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;

public class CoverFusionOutput extends CoverOutput {
    @Override
    public String getId() {
        return "fusion_output";
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
