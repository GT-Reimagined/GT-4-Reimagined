package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

public class CoalBoilerFluidHandler extends MachineFluidHandler<TileEntityCoalBoiler> {

    public CoalBoilerFluidHandler(TileEntityCoalBoiler tile) {
        super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
        tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
            b.tank(16000);
            return b;
        }));
        tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
            b.tank(16000);
            return b;
        }));
    }

    @Override
    protected FluidTanks getCellAccessibleTanks() {
        return getInputTanks();
    }
}
