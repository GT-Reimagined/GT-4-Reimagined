package org.gtreimagined.gt4r.machine;

import muramasa.antimatter.Data;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;
import org.gtreimagined.gt4r.block.BlockNonSolidMachine;

import static muramasa.antimatter.machine.MachineFlag.BASIC;
import static muramasa.antimatter.machine.MachineFlag.EU;

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
