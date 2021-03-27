package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.LazyHolder;
import trinsdar.gt4r.machine.CoalBoilerFluidHandler;

public class TileEntityQuantumTank extends TileEntityMachine {

    public TileEntityQuantumTank(Machine<?> type) {
        super(type);
        this.fluidHandler = LazyHolder.of(() -> new MachineFluidHandler<>(this, Integer.MAX_VALUE, 265000));
    }
}
