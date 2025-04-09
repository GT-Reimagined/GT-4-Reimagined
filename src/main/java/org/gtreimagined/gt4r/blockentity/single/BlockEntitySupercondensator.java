package org.gtreimagined.gt4r.blockentity.single;

import org.gtreimagined.gtlib.blockentity.single.BlockEntityTransformer;
import org.gtreimagined.gtlib.capability.machine.MachineEnergyHandler;
import org.gtreimagined.gtlib.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntitySupercondensator extends BlockEntityTransformer<BlockEntitySupercondensator> {
    public BlockEntitySupercondensator(Machine<?> type, BlockPos pos, BlockState state, int amps) {
        super(type, pos, state, amps);
        energyHandler.set(() -> new MachineEnergyHandler<BlockEntitySupercondensator>(this, 0L, capFunc.apply(getMachineTier().getVoltage()), getMachineTier().getVoltage() * 64, getMachineTier().getVoltage(), amperage, amperage * 4) {
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
