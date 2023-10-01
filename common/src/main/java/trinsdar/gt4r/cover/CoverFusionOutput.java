package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RRef;

import javax.annotation.Nullable;

public class CoverFusionOutput extends CoverOutput {
    public CoverFusionOutput(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public String getId() {
        return "fusion_output";
    }

    @Override
    public String getDomain() {
        return GT4RRef.ID;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

}
