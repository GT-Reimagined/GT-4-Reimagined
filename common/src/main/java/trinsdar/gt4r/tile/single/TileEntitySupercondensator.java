package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntitySupercondensator extends TileEntityTransformer<TileEntitySupercondensator> {
    public TileEntitySupercondensator(Machine<?> type, BlockPos pos, BlockState state, int amps) {
        super(type, pos, state, amps);
        energyHandler.set(() -> new MachineEnergyHandler<TileEntitySupercondensator>(this, 0L, capFunc.apply(getMachineTier().getVoltage()), getMachineTier().getVoltage() * 64, getMachineTier().getVoltage(), amperage, amperage * 4) {
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
