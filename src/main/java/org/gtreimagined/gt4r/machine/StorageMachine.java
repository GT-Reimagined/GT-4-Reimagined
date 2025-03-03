package org.gtreimagined.gt4r.machine;

import muramasa.antimatter.Data;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;

import static muramasa.antimatter.machine.MachineFlag.COVERABLE;

public class StorageMachine extends Machine<StorageMachine> {
    public StorageMachine(String domain, String id) {
        super(domain, id);
        addFlags(COVERABLE);
        setTile(BlockEntityMachine::new);
        setGUI(Data.BASIC_MENU_HANDLER);
    }
}
