package trinsdar.gt4r.blockentity.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityIndustrialSawmill extends BlockEntityBasicMultiMachine<BlockEntityIndustrialSawmill> {
    public BlockEntityIndustrialSawmill(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
