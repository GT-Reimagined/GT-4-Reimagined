package trinsdar.gt4r.blockentity.multi;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractCapUtils;
import tesseract.api.item.PlatformItemHandler;

public class BlockEntityCokeOven extends BlockEntityBasicMultiMachine<BlockEntityCokeOven> {

    BlockPos[] positions;
    public BlockEntityCokeOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        positions = new BlockPos[9];
        int3 start = new int3(pos, this.getFacing(state)).below(2).right(1);
        positions[0] = start.immutable();
        positions[1] = start.left(1).immutable();
        positions[2] = start.left(1).immutable();
        positions[3] = start.back(1).right(1).immutable();
        positions[4] = start.right(1).immutable();
        positions[5] = start.right(1).immutable();
        positions[6] = start.back(1).left(1).immutable();
        positions[7] = start.left(1).immutable();
        positions[8] = start.left(1).immutable();
    }

    @Override
    public int maxShares() {
        return 0;
    }


    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (level.getGameTime() % 10 == 0) {
            for (BlockPos pos1 : positions) {
                BlockEntity blockEntity = level.getBlockEntity(pos1);
                if (blockEntity != null){
                    PlatformItemHandler itemHandler1 = TesseractCapUtils.INSTANCE.getItemHandler(blockEntity, Direction.UP).orElse(null);
                    if (itemHandler1 != null){
                        itemHandler.ifPresent(i -> Utils.transferItems(i.getHandler(SlotType.IT_OUT), itemHandler1, false));
                    }
                    var fluidHandler1 = FluidHooks.safeGetBlockFluidManager(blockEntity, Direction.UP).orElse(null);
                    if (fluidHandler1 != null){
                        fluidHandler.ifPresent(f -> Utils.transferFluids(f.getOutputTanks(), fluidHandler1));
                    }
                }
            }
        }
    }

    @Override
    public boolean allowsFakeTiles() {
        return true;
    }
}
