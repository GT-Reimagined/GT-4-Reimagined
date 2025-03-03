package org.gtreimagined.gt4r.machine;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
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
