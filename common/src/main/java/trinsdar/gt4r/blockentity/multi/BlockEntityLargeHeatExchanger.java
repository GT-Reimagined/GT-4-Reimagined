package trinsdar.gt4r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityLargeHeatExchanger extends BlockEntityMultiMachine<BlockEntityLargeHeatExchanger> {

    public BlockEntityLargeHeatExchanger(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
