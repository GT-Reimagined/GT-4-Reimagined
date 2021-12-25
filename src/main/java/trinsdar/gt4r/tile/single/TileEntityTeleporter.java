package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;

public class TileEntityTeleporter extends TileEntityMachine<TileEntityTeleporter> {

    public TileEntityTeleporter(Machine<?> type) {
        super(type);
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();

    }
}
