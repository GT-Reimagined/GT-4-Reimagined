package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Ref;
import muramasa.antimatter.tile.TileEntityTickable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.TileEntityTypes;
import trinsdar.gt4r.tree.BlockRubberLog;
import trinsdar.gt4r.tree.ResinState;
import trinsdar.gt4r.tree.RubberTree;

public class TileEntitySapBag extends TileEntityTickable {
    ItemStack sap = ItemStack.EMPTY;
    Direction facing = Direction.NORTH;
    public TileEntitySapBag() {
        super(TileEntityTypes.SAP_BAG_TYPE);
    }

    @Override
    public void onFirstTick() {
        BlockState state = world.getBlockState(this.getPos().offset(facing));
        if (state.getBlock() == GT4RData.RUBBER_LOG){
            if(state.get(BlockRubberLog.RESIN_STATE) ==  ResinState.FILLED && state.get(BlockRubberLog.RESIN_FACING) == facing.getOpposite()){
                boolean successful = false;
                int amount = (1 + world.rand.nextInt(3));
                if (sap.isEmpty()){
                    setSap(new ItemStack(GT4RData.StickyResin, amount));
                    successful = true;
                } else if (sap.getCount() < 64){
                    growSap(amount);
                    successful = true;
                }
                if (successful){
                    world.setBlockState(this.getPos().offset(facing), state.with(BlockRubberLog.RESIN_STATE, ResinState.EMPTY));
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
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("F", facing.getIndex());
        tag.put("S", sap.write(new CompoundNBT()));
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.facing = Direction.byIndex(nbt.getInt("F"));
        this.sap = ItemStack.read(nbt.getCompound("S"));
    }
}
