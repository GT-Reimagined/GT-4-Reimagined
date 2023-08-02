package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine<TileEntityPrimitiveBlastFurnace> {

    public TileEntityPrimitiveBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
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
