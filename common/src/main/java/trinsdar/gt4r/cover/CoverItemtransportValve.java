package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class CoverItemtransportValve extends CoverBasicTransport{
    public static String ID = "item_transport_valve";

    public CoverItemtransportValve(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }


    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 0 || mode == 2 || mode == 4;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 1 || mode == 3 || mode == 5;
    }


    /*@Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        GT4RData.COVER_PUMP.onUpdate(instance, side);
        GT4RData.COVER_CONVEYOR.onUpdate(instance, side);
    }*/

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }
}
