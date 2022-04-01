package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraftforge.items.CapabilityItemHandler;

import static net.minecraft.util.Direction.DOWN;

public class TileEntityDustBin extends TileEntityMachine<TileEntityDustBin> {
    public TileEntityDustBin(Machine<?> type) {
        super(type);
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        itemHandler.ifPresent(i -> {
            BlockEntity up = level.getBlockEntity(this.getBlockPos().above(1));
            BlockEntity down = level.getBlockEntity(this.getBlockPos().below(1));
            if (up != null){
                up.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, DOWN).ifPresent(f -> Utils.transferItems(f, i.getInputHandler(), true));
            }
            if (down != null){
                down.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(t -> Utils.transferItems(i.getOutputHandler(), t, true));
            } else if (level.isEmptyBlock(this.getBlockPos().below(1))){
                ItemStack stack = Utils.extractAny(i.getOutputHandler());
                if (stack.isEmpty()) return;
                level.addFreshEntity(new ItemEntity(level,getBlockPos().getX(), getBlockPos().getY() - 1, getBlockPos().getZ(),stack));
            }
        });
    }
}
