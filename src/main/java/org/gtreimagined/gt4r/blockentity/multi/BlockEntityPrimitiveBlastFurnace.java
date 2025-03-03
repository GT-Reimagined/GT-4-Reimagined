package org.gtreimagined.gt4r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPrimitiveBlastFurnace extends BlockEntityBasicMultiMachine<BlockEntityPrimitiveBlastFurnace> {

    public BlockEntityPrimitiveBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int maxShares() {
        return 0;
    }

    //    @Override
//    public boolean onStructureFormed() {
//        super.onStructureFormed();
//        int3 controller = new int3(getPos(), getFacing());
//        controller.back(1);
//        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
//        controller.up(1);
//        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
//        return true;
//    }
}
