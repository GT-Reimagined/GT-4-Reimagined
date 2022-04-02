package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.single.TileEntityGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.MachineFlag.GENERATOR;

public class TileEntityCoveredGenerator extends TileEntityGenerator<TileEntityCoveredGenerator> {
    public TileEntityCoveredGenerator(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        energyHandler.set(() -> new MachineEnergyHandler<TileEntityCoveredGenerator>(this, type.amps(),type.has(GENERATOR)){
            @Override
            public boolean canInput(Direction direction) {
                return false;
            }
            @Override
            public boolean canInput() {
                return false;
            }

            @Override
            public boolean canOutput(Direction direction) {
                return super.canOutput(direction) && direction == tile.getOutputFacing();

            }
        });
    }
}
