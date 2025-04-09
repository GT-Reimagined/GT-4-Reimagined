package org.gtreimagined.gt4r.machine;

import org.gtreimagined.gtlib.Data;
import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.machine.types.Machine;

import static org.gtreimagined.gtlib.machine.MachineFlag.COVERABLE;

public class StorageMachine extends Machine<StorageMachine> {
    public StorageMachine(String domain, String id) {
        super(domain, id);
        addFlags(COVERABLE);
        setTile(BlockEntityMachine::new);
        setGUI(Data.BASIC_MENU_HANDLER);
    }
}
