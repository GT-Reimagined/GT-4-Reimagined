package org.gtreimagined.gt4r.blockentity.multi;

import org.gtreimagined.gtlib.blockentity.multi.BlockEntityBasicMultiMachine;
import org.gtreimagined.gtlib.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityImplosionCompressor extends BlockEntityBasicMultiMachine<BlockEntityImplosionCompressor> {

    public BlockEntityImplosionCompressor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

}
