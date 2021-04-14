package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class CoverSteamVent extends BaseCover {
    public CoverSteamVent(){
        register();
    }


    @Override
    protected String getRenderId() {
        return "output";
    }

    @Override
    public String getId() {
        return "cover_steam_vent";
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicDepthModel();
    }
}
