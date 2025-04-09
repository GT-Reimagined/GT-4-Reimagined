package org.gtreimagined.gt4r.machine;

import org.gtreimagined.gtlib.capability.fluid.FluidTanks;
import org.gtreimagined.gtlib.capability.machine.MachineFluidHandler;
import org.gtreimagined.gtlib.gui.SlotType;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityCoalBoiler;

public class CoalBoilerFluidHandler extends MachineFluidHandler<BlockEntityCoalBoiler> {

    public CoalBoilerFluidHandler(BlockEntityCoalBoiler tile) {
        super(tile);
        tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
            b.tank(16000);
            return b;
        }));
        tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
            b.tank(16000);
            return b;
        }));
    }

    @Override
    protected FluidTanks getCellAccessibleTanks() {
        return getInputTanks();
    }
}
