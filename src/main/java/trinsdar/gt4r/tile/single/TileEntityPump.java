package trinsdar.gt4r.tile.single;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;

public class TileEntityPump extends TileEntityMachine<TileEntityPump> {
    Fluid fluid = Fluids.EMPTY;
    public ArrayList<BlockPos> mPumpList = new ArrayList<BlockPos>();
    int pumpHeadY = - 1;
    public TileEntityPump(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (this.pumpHeadY < 0) {
            this.pumpHeadY = worldPosition.getY() - 1;
        }
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (this.isServerSide() && level.getGameTime()%10==0 && this.machineState != MachineState.DISABLED && this.itemHandler.map(i -> {
            ItemStack stack = i.getHandler(SlotType.STORAGE).getStackInSlot(0);
            return !stack.isEmpty() && stack.getItem() instanceof BlockItem;
        }).orElse(false)) {
            if (fluidHandler.map(f -> f.getOutputTanks().getTank(0).getFluidAmount() + 1000 <= f.getOutputTanks().getTank(0).getCapacity()).orElse(false) && energyHandler.map(e -> e.getEnergy() >= 2000).orElse(false)) {
                boolean tMovedOneDown = false;

                if (level.getGameTime()%100==0) {
                    tMovedOneDown = moveOneDown();
                }
                int x = worldPosition.getX();
                int z = worldPosition.getZ();

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
                    if (getPumpHeadY() < worldPosition.getY()) {
                        if (tMovedOneDown || (mPumpList.isEmpty() && level.getGameTime() % 200 == 100) || level.getGameTime() % 72000 == 100) {
                            mPumpList.clear();
                            for (int y = worldPosition.getY() - 1; mPumpList.isEmpty() && y >= pumpHeadY; y--) {
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
        BlockState state = level.getBlockState(new BlockPos(worldPosition.getX(), pumpHeadY - 1, worldPosition.getZ()));
        if (!(state.getBlock() instanceof LiquidBlock) && !state.isAir()) return false;
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
        if (addIfFluidAndNotAlreadyAdded(aX, aY + 1, aZ, aList) || (aX == mX && aZ == mZ && aY < worldPosition.getY())) {
            scanForFluid(aX, aY + 1, aZ, aList, mX, mZ, mDist);
        }
    }

    private boolean addIfFluidAndNotAlreadyAdded(int aX, int aY, int aZ, ArrayList<BlockPos> aList) {
        BlockPos tCoordinate = new BlockPos(aX, aY, aZ);
        if (!aList.contains(tCoordinate)) {
            Fluid fluid = level.getFluidState(new BlockPos(aX, aY, aZ)).getType();
            if (this.fluid == fluid && fluid != Fluids.EMPTY) {
                aList.add(tCoordinate);
                return true;
            }
        }
        return false;
    }

    private void getFluidAt(int x, int y, int z) {
        fluid = level.getFluidState(new BlockPos(x, y, z)).getType();
        if (fluid == Fluids.WATER && level.getBlockState(new BlockPos(x, y, z)) != Blocks.WATER.defaultBlockState()) fluid = Fluids.EMPTY;
    }

    private boolean consumeFluid(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        FluidState fluidState = level.getFluidState(pos);
        Fluid fluid = fluidState.getType();
        if (!(this.itemHandler.map(i -> {
            ItemStack stack = i.getHandler(SlotType.STORAGE).getStackInSlot(0);
            return !stack.isEmpty() && stack.getItem() instanceof BlockItem;
        })).orElse(false)){
            return false;
        }
        if (fluid == this.fluid && fluid != Fluids.EMPTY) {
            // waterlogged block
            if ((fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER) && level.getBlockState(pos).getBlock() != Blocks.WATER) {
                return false;
            }
            if (fluid.isSource(fluidState)) {
                FluidStack stack = new FluidStack(fluid, 1000);
                if (fluidHandler.map(f -> f.canOutputsFit(new FluidStack[]{stack})).orElse(false) && energyHandler.map(e -> e.getEnergy() >= 1000).orElse(false)){
                    fluidHandler.ifPresent(f -> f.fillOutput(stack, IFluidHandler.FluidAction.EXECUTE));
                    energyHandler.ifPresent(e -> Utils.extractEnergy(e, 1000));
                } else {
                    return false;
                }
            } else {
                energyHandler.ifPresent(e -> Utils.extractEnergy(e, 250));
            }
            Block block = this.itemHandler.map(i -> ((BlockItem)i.getHandler(SlotType.STORAGE).getStackInSlot(0).getItem()).getBlock()).orElse(Blocks.AIR);
            if (x == this.worldPosition.getX() && z == this.worldPosition.getZ()) block = Blocks.AIR;
            level.setBlockAndUpdate(pos, block.defaultBlockState());
            if (block != Blocks.AIR) itemHandler.ifPresent(i -> i.getHandler(SlotType.STORAGE).extractFromInput(0, 1, false));
            return true;
        }
        return false;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Fluid", new FluidStack(fluid, 1).writeToNBT(new CompoundTag()));
        tag.putInt("pumpHeadY", pumpHeadY);
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < mPumpList.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            BlockPos pos = mPumpList.get(i);
            itemTag.putInt("X", pos.getX());
            itemTag.putInt("Y", pos.getY());
            itemTag.putInt("Z", pos.getZ());
            nbtTagList.add(itemTag);
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Positions", nbtTagList);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        mPumpList = new ArrayList<>();
        ListTag tagList = tag.getList("Positions", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundTag itemTags = tagList.getCompound(i);
            BlockPos pos = new BlockPos(itemTags.getInt("X"), itemTags.getInt("Y"), itemTags.getInt("Z"));
            mPumpList.add(pos);
        }
        this.fluid = FluidStack.loadFluidStackFromNBT(tag.getCompound("Fluid")).getFluid();
        this.pumpHeadY = tag.getInt("pumpHeadY");
    }
}
