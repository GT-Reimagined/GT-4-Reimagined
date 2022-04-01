package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import net.minecraft.core.Direction;

public class TileEntitySupercondensator extends TileEntityTransformer<TileEntitySupercondensator> {
    public TileEntitySupercondensator(Machine<?> type, int amps) {
        super(type, amps);
        energyHandler.set(() -> new MachineEnergyHandler<TileEntitySupercondensator>(this, 0L, capFunc.applyAsLong(getMachineTier().getVoltage()), getMachineTier().getVoltage() * 64, getMachineTier().getVoltage(), amperage, amperage * 4) {
            @Override
            public boolean canOutput(Direction direction) {
                return isDefaultMachineState() == (tile.getFacing().get3DDataValue() != direction.get3DDataValue());
            }

            @Override
            public boolean canInput(Direction direction) {
                return !canOutput(direction);
            }
        });
    }
}
