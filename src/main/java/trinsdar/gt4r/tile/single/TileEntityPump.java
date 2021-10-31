package trinsdar.gt4r.tile.single;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;

public class TileEntityPump extends TileEntityMachine<TileEntityPump> {
    Fluid fluid = Fluids.EMPTY;
    public ArrayList<BlockPos> mPumpList = new ArrayList<BlockPos>();
    int pumpHeadY = - 1;
    public TileEntityPump(Machine<?> type) {
        super(type);
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (this.pumpHeadY < 0) {
            this.pumpHeadY = pos.getY() - 1;
        }
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        if (this.isServerSide() && world.getGameTime()%10==0 && this.machineState != MachineState.DISABLED && this.itemHandler.map(i -> {
            ItemStack stack = i.getHandler(SlotType.STORAGE).getStackInSlot(0);
            return !stack.isEmpty() && stack.getItem() instanceof BlockItem;
        }).orElse(false)) {
            if (fluidHandler.map(f -> f.getOutputTanks().getTank(0).getFluidAmount() + 1000 <= f.getOutputTanks().getTank(0).getCapacity()).orElse(false) && energyHandler.map(e -> e.getEnergy() >= 2000).orElse(false)) {
                boolean tMovedOneDown = false;

                if (world.getGameTime()%100==0) {
                    tMovedOneDown = moveOneDown();
                }
                int x = pos.getX();
                int z = pos.getZ();

                if (fluid == Fluids.EMPTY) {
                    getFluidAt(x, getPumpHeadY(), z);
                    if (fluid == Fluids.EMPTY) {
                        getFluidAt(x, getPumpHeadY(), z + 1);
                    }
                    if (fluid == Fluids.EMPTY) {
                        getFluidAt(x, getPumpHeadY(), z - 1);
                    }
                    if (fluid == Fluids.EMPTY) {
                        getFluidAt(x + 1, getPumpHeadY(), z);
                    }
                    if (fluid == Fluids.EMPTY) {
                        getFluidAt(x - 1, getPumpHeadY(), z);
                    }
                } else {
                    if (getPumpHeadY() < pos.getY()) {
                        if (tMovedOneDown || (mPumpList.isEmpty() && world.getGameTime() % 200 == 100) || world.getGameTime() % 72000 == 100) {
                            mPumpList.clear();
                            for (int y = pos.getY() - 1; mPumpList.isEmpty() && y >= pumpHeadY; y--) {
                                scanForFluid(x, y, z, mPumpList, x, z, 64);
                            }
                        }
                        if (!tMovedOneDown && !mPumpList.isEmpty()) {
                            consumeFluid(mPumpList.get(mPumpList.size()-1).getX(), mPumpList.get(mPumpList.size()-1).getY(), mPumpList.get(mPumpList.size()-1).getZ());
                            mPumpList.remove(mPumpList.size()-1);
                        }
                    }
                }
            }
            this.setActive(!mPumpList.isEmpty());
        }
    }

    private void setActive(boolean active){
        if (this.machineState != MachineState.DISABLED){
            this.setMachineState(active ? MachineState.ACTIVE : MachineState.IDLE);
        }
    }

    private boolean moveOneDown() {
        if (pumpHeadY <= 0) return false;
        BlockState state = world.getBlockState(new BlockPos(pos.getX(), pumpHeadY - 1, pos.getZ()));
        if (!(state.getBlock() instanceof FlowingFluidBlock) && !state.getBlock().isAir(state, world, new BlockPos(pos.getX(), pumpHeadY - 1, pos.getZ()))) return false;
        pumpHeadY--;
        return true;
    }

    private int getPumpHeadY() {
        return pumpHeadY;
    }

    private void scanForFluid(int aX, int aY, int aZ, ArrayList<BlockPos> aList, int mX, int mZ, int mDist) {
        boolean pX = addIfFluidAndNotAlreadyAdded(aX + 1, aY, aZ, aList),
                nX = addIfFluidAndNotAlreadyAdded(aX - 1, aY, aZ, aList),
                pZ = addIfFluidAndNotAlreadyAdded(aX, aY, aZ + 1, aList),
                nZ = addIfFluidAndNotAlreadyAdded(aX, aY, aZ - 1, aList);

        if (pX && aX < mX + mDist) {
            scanForFluid(aX + 1, aY, aZ, aList, mX, mZ, mDist);
        }
        if (nX && aX > mX - mDist) {
            scanForFluid(aX - 1, aY, aZ, aList, mX, mZ, mDist);
        }
        if (pZ && aZ < mZ + mDist) {
            scanForFluid(aX, aY, aZ + 1, aList, mX, mZ, mDist);
        }
        if (nZ && aZ > mZ - mDist) {
            scanForFluid(aX, aY, aZ - 1, aList, mX, mZ, mDist);
        }
        if (addIfFluidAndNotAlreadyAdded(aX, aY + 1, aZ, aList) || (aX == mX && aZ == mZ && aY < pos.getY())) {
            scanForFluid(aX, aY + 1, aZ, aList, mX, mZ, mDist);
        }
    }

    private boolean addIfFluidAndNotAlreadyAdded(int aX, int aY, int aZ, ArrayList<BlockPos> aList) {
        BlockPos tCoordinate = new BlockPos(aX, aY, aZ);
        if (!aList.contains(tCoordinate)) {
            Fluid fluid = world.getFluidState(new BlockPos(aX, aY, aZ)).getFluid();
            if (this.fluid == fluid && fluid != Fluids.EMPTY) {
                aList.add(tCoordinate);
                return true;
            }
        }
        return false;
    }

    private void getFluidAt(int x, int y, int z) {
        fluid = world.getFluidState(new BlockPos(x, y, z)).getFluid();
        if (fluid == Fluids.WATER && world.getBlockState(new BlockPos(x, y, z)) != Blocks.WATER.getDefaultState()) fluid = Fluids.EMPTY;
    }

    private boolean consumeFluid(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        FluidState fluidState = world.getFluidState(pos);
        Fluid fluid = fluidState.getFluid();
        if (!(this.itemHandler.map(i -> {
            ItemStack stack = i.getHandler(SlotType.STORAGE).getStackInSlot(0);
            return !stack.isEmpty() && stack.getItem() instanceof BlockItem;
        })).orElse(false)){
            return false;
        }
        if (fluid == this.fluid && fluid != Fluids.EMPTY) {
            // waterlogged block
            if ((fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER) && world.getBlockState(pos).getBlock() != Blocks.WATER) {
                return false;
            }
            if (fluid.isSource(fluidState)) {
                FluidStack stack = new FluidStack(fluid, 1000);
                if (fluidHandler.map(f -> f.canOutputsFit(new FluidStack[]{stack})).orElse(false) && energyHandler.map(e -> e.getEnergy() >= 1000).orElse(false)){
                    fluidHandler.ifPresent(f -> f.fillOutput(stack, IFluidHandler.FluidAction.EXECUTE));
                    energyHandler.ifPresent(e -> e.extractInternal(1000, false, true));
                } else {
                    return false;
                }
            } else {
                energyHandler.ifPresent(e -> e.extractInternal(250, false, true));
            }
            Block block = this.itemHandler.map(i -> ((BlockItem)i.getHandler(SlotType.STORAGE).getStackInSlot(0).getItem()).getBlock()).orElse(Blocks.AIR);
            if (x == this.pos.getX() && z == this.pos.getZ()) block = Blocks.AIR;
            world.setBlockState(pos, block.getDefaultState());
            if (block != Blocks.AIR) itemHandler.ifPresent(i -> i.getHandler(SlotType.STORAGE).extractFromInput(0, 1, false));
            return true;
        }
        return false;
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.put("Fluid", new FluidStack(fluid, 1).writeToNBT(new CompoundNBT()));
        tag.putInt("pumpHeadY", pumpHeadY);
        ListNBT nbtTagList = new ListNBT();
        for (int i = 0; i < mPumpList.size(); i++) {
            CompoundNBT itemTag = new CompoundNBT();
            BlockPos pos = mPumpList.get(i);
            itemTag.putInt("X", pos.getX());
            itemTag.putInt("Y", pos.getY());
            itemTag.putInt("Z", pos.getZ());
            nbtTagList.add(itemTag);
        }
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("Positions", nbtTagList);
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        mPumpList = new ArrayList<>();
        ListNBT tagList = tag.getList("Positions", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundNBT itemTags = tagList.getCompound(i);
            BlockPos pos = new BlockPos(itemTags.getInt("X"), itemTags.getInt("Y"), itemTags.getInt("Z"));
            mPumpList.add(pos);
        }
        this.fluid = FluidStack.loadFluidStackFromNBT(tag.getCompound("Fluid")).getFluid();
        this.pumpHeadY = tag.getInt("pumpHeadY");
    }
}
