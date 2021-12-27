package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TileEntityTeleporter extends TileEntityMachine<TileEntityTeleporter> {

    BlockPos destination;
    boolean redstoneTicked = false;

    public TileEntityTeleporter(Machine<?> type) {
        super(type);
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.redstoneTicked = tag.getBoolean("redstoneTicked");
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        tag.putBoolean("redstoneTicked", redstoneTicked);
        return tag;
    }

    @Override
    public void onBlockUpdate(BlockPos neighbor) {
        super.onBlockUpdate(neighbor);
        boolean flag = this.getLevel().hasNeighborSignal(this.getBlockPos());
        if (flag && !redstoneTicked){
            redstoneTicked = true;
            findEntityToTeleport();
        } else if (!flag && redstoneTicked){
            redstoneTicked = false;
        }

    }

    public void findEntityToTeleport(){
        List<Entity> entites = this.getLevel().getEntities(null, new AxisAlignedBB(this.getBlockPos().offset(-2, -3, -2), this.getBlockPos().offset(2, 3, 2)));
        if (!entites.isEmpty()){
            if (destination == null) return;
            TileEntity teleporter = level.getBlockEntity(destination);
            if (teleporter instanceof TileEntityTeleporter){
                setMachineState(MachineState.ACTIVE);
                double minimumDistance = Double.MAX_VALUE;

            } else {
                destination = null;
            }
        }

    }
}
