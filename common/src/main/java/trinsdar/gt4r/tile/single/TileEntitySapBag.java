package trinsdar.gt4r.tile.single;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.github.gregtechintergalactical.gtrubber.tree.ResinState;
import com.github.gregtechintergalactical.gtrubber.tree.block.BlockRubberLog;
import muramasa.antimatter.tile.TileEntityTickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.TileEntityTypes;

public class TileEntitySapBag extends TileEntityTickable<TileEntitySapBag> {
    ItemStack sap = ItemStack.EMPTY;
    Direction facing = Direction.NORTH;
    public TileEntitySapBag(BlockPos pos, BlockState state) {
        super(TileEntityTypes.SAP_BAG_TYPE, pos, state);
    }

    @Override
    public void onFirstTick() {
        BlockState state = level.getBlockState(this.getBlockPos().relative(facing));
        if (state.getBlock() == GTRubberData.RUBBER_LOG){
            if(state.getValue(ResinState.INSTANCE) ==  ResinState.FILLED && state.getValue(BlockRubberLog.RESIN_FACING) == facing.getOpposite()){
                boolean successful = false;
                int amount = (1 + level.random.nextInt(3));
                if (sap.isEmpty()){
                    setSap(new ItemStack(GTRubberData.StickyResin, amount));
                    successful = true;
                } else if (sap.getCount() < 64){
                    growSap(amount);
                    successful = true;
                }
                if (successful){
                    level.setBlockAndUpdate(this.getBlockPos().relative(facing), state.setValue(ResinState.INSTANCE, ResinState.EMPTY));
                }
            }
        }
    }

    public void onBlockUpdate(){
        onFirstTick();
    }

    public void setFacing(Direction facing){
        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    public ItemStack getSap() {
        return sap;
    }

    public void setSap(ItemStack sap) {
        this.sidedSync(true);
        this.sap = sap;
    }

    public void growSap(int amount){
        this.sidedSync(true);
        this.sap.grow(amount);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("F", facing.get3DDataValue());
        tag.put("S", sap.save(new CompoundTag()));
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.facing = Direction.from3DDataValue(nbt.getInt("F"));
        this.sap = ItemStack.of(nbt.getCompound("S"));
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("F", facing.get3DDataValue());
        tag.put("S", sap.save(new CompoundTag()));
        return tag;
    }
}
