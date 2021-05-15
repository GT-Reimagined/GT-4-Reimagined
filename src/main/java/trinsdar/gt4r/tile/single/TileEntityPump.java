package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.int3;
import muramasa.antimatter.worldgen.WorldGenHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.lwjgl.system.CallbackI;

public class TileEntityPump extends TileEntityMachine {
    int3 pump;
    public TileEntityPump(Machine<?> type) {
        super(type);
        pump = new int3(this.getPos(), this.getFacing()).down(1);
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        if (world.getGameTime() % 20 == 0){
            FluidState fluidState = world.getFluidState(this.pump);
            if (!fluidState.isEmpty()){
                if (fluidState.getFluid() == Fluids.WATER && world.getBlockState(this.pump) != Blocks.WATER.getDefaultState()){
                    this.pump.down(1);
                    return;
                }
                boolean[] fill = new boolean[1];
                FluidStack fluidStack = new FluidStack(fluidState.getFluid(), 1000);
                fill[0] = false;
                fluidHandler.ifPresent(f -> {
                    if (f.canOutputsFit(new FluidStack[]{fluidStack})){
                        f.fillOutput(fluidStack, IFluidHandler.FluidAction.EXECUTE);
                        fill[0] = true;
                    }
                });
                if (fill[0]){
                    WorldGenHelper.setState(world, pump, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    @Override
    public boolean setFacing(Direction side) {
        boolean facing = super.setFacing(side);
        if (facing) this.pump = new int3(pump.getX(), pump.getY(), pump.getZ(), side);
        return facing;
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        CompoundNBT pump = new CompoundNBT();
        pump.putInt("X", this.pump.getX());
        pump.putInt("Y", this.pump.getY());
        pump.putInt("Z", this.pump.getZ());
        tag.put("pump", pump);
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        CompoundNBT pump = tag.getCompound("pump");
        this.pump = new int3(pump.getInt("X"), pump.getInt("Y"), pump.getInt("Z"), this.getFacing());
    }
}
