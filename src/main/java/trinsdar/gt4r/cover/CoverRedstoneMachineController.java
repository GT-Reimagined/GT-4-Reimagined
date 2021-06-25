package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import net.minecraft.util.Direction;
import trinsdar.gt4r.Ref;

public class CoverRedstoneMachineController extends BaseCover {
    public CoverRedstoneMachineController(){
        super();
        register();
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public String getId() {
        return "redstone_machine_controller";
    }

    @Override
    public void onBlockUpdate(CoverStack<?> instance, Direction side) {

    }
}
