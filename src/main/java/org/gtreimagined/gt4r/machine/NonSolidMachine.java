package org.gtreimagined.gt4r.machine;

import org.gtreimagined.gtlib.Data;
import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gt4r.block.BlockNonSolidMachine;

import static org.gtreimagined.gtlib.machine.MachineFlag.BASIC;
import static org.gtreimagined.gtlib.machine.MachineFlag.EU;

public class NonSolidMachine extends Machine<NonSolidMachine> {
    public NonSolidMachine(String domain, String id) {
        super(domain, id);
        addFlags(BASIC, EU);
        setBlock(BlockNonSolidMachine::new);
        setItemBlockClass(() -> BlockNonSolidMachine.class);
        setTile(BlockEntityMachine::new);
        setGUI(Data.BASIC_MENU_HANDLER);
    }
}
