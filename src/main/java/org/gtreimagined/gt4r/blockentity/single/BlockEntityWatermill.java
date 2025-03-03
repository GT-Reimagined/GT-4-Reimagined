package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.single.BlockEntityGenerator;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.MachineFlag.GENERATOR;

public class BlockEntityWatermill extends BlockEntityGenerator<BlockEntityWatermill> {
    public BlockEntityWatermill(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        energyHandler.set(() -> new MachineEnergyHandler<>(this, type.amps(), type.has(GENERATOR)) {
            @Override
            public boolean canOutput(Direction direction) {
                return super.canOutput(direction) && (direction == tile.getFacing().getClockWise() || direction == tile.getFacing().getCounterClockWise());

            }
        });
    }
}
