package trinsdar.gt4r.machine;

import muramasa.antimatter.Data;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;

import static muramasa.antimatter.machine.MachineFlag.BASIC;
import static muramasa.antimatter.machine.MachineFlag.CONFIGURABLE;
import static muramasa.antimatter.machine.MachineFlag.COVERABLE;
import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class StorageMachine extends Machine<StorageMachine> {
    public StorageMachine(String domain, String id, Object... data) {
        super(domain, id, data);
        addFlags(COVERABLE, CONFIGURABLE);
        setTile(() -> new TileEntityMachine(this));
        setGUI(Data.BASIC_MENU_HANDLER);
    }
}
