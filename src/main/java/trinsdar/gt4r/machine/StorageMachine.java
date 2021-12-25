package trinsdar.gt4r.machine;

import muramasa.antimatter.Data;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;

import static muramasa.antimatter.machine.MachineFlag.COVERABLE;

public class StorageMachine extends Machine<StorageMachine> {
    public StorageMachine(String domain, String id) {
        super(domain, id);
        addFlags(COVERABLE);
        setTile(() -> new TileEntityMachine<>(this));
        setGUI(Data.BASIC_MENU_HANDLER);
    }
}
