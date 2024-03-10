package trinsdar.gt4r.blockentity.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractCapUtils;

import static net.minecraft.core.Direction.DOWN;

public class BlockEntityDustBin extends BlockEntityMachine<BlockEntityDustBin> {
    public BlockEntityDustBin(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        itemHandler.ifPresent(i -> {
            BlockEntity up = level.getBlockEntity(this.getBlockPos().above(1));
            BlockEntity down = level.getBlockEntity(this.getBlockPos().below(1));
            if (up != null){
                TesseractCapUtils.INSTANCE.getItemHandler(up, DOWN).ifPresent(f -> Utils.transferItems(f, i.getInputHandler(), true));
            }
            if (down != null){
                TesseractCapUtils.INSTANCE.getItemHandler(down, Direction.UP).ifPresent(t -> Utils.transferItems(i.getOutputHandler(), t, true));
            } else if (level.isEmptyBlock(this.getBlockPos().below(1))){
                ItemStack stack = Utils.extractAny(i.getOutputHandler());
                if (stack.isEmpty()) return;
                level.addFreshEntity(new ItemEntity(level,getBlockPos().getX(), getBlockPos().getY() - 1, getBlockPos().getZ(),stack));
            }
        });
    }
}
