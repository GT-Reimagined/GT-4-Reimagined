package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import trinsdar.gt4r.Ref;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class CoverPump extends Cover {

    public static String ID = "pump_module";


    public CoverPump() {
        super();
        register();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (20) != 0) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        Utils.transferFluidsOnCap(instance.getTile(), adjTile, Integer.MAX_VALUE);
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    protected String getRenderId() {
        return ID;
    }
}
