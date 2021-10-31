package trinsdar.gt4r.tile.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.capability.item.FakeTrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import trinsdar.gt4r.data.SlotTypes;

import java.util.List;

public abstract class TileEntityTranslocator<T extends TileEntityTranslocator<T>> extends TileEntityMachine<T> {
    Capability<?> cap;
    boolean blacklist = false;
    boolean emitEnergy = false;
    public TileEntityTranslocator(Machine<?> type, Capability<?> cap) {
        super(type);
        this.cap = cap;
        energyHandler.set(() -> new MachineEnergyHandler<T>(((T)this), 0L, 32 * 66, this.getMachineTier().getVoltage(), this.getMachineTier().getVoltage(), 1, 1){
            @Override
            public boolean canOutput(Direction direction) {
                return super.canOutput(direction) && direction == tile.getFacing().getOpposite() && tile.emitEnergy;
            }
        });
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        if (coverHandler.map(c -> !c.get(this.getFacing().getOpposite()).blocksOutput(cap, this.getFacing().getOpposite()) && !c.get(this.getFacing()).blocksInput(cap, this.getFacing())).orElse(true)){
            this.processOutput();
        }
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity, int... data) {
        if (event == GuiEvent.EXTRA_BUTTON) {
            switch (data[0]) {
                case 0:
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(new StringTextComponent( (emitEnergy ? "Emit energy to output side" : "Don't emit energy")), playerEntity.getUniqueID());
                    break;
                case 1:
                    blacklist = !blacklist;
                    playerEntity.sendMessage(new StringTextComponent( (blacklist ? "I" : "Don't i") + "nvert filter"), playerEntity.getUniqueID());
                    break;
            }
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        blacklist = tag.getBoolean("blacklist");
        emitEnergy = tag.getBoolean("emitsEnergy");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putBoolean("blacklist", blacklist);
        tag.putBoolean("emitEnergy", emitEnergy);
        return tag;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public boolean isEmitEnergy() {
        return emitEnergy;
    }

    protected abstract boolean processOutput();

    public static class TileEntityItemTranslocator extends TileEntityTranslocator<TileEntityItemTranslocator>{
        public TileEntityItemTranslocator(Machine<?> type) {
            super(type, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing().getOpposite();
            Direction inputDir = this.getFacing();
            TileEntity outputTile = Utils.getTile(this.getWorld(), this.getPos().offset(outputDir));
            if (outputTile == null) return false;
            TileEntity inputTile = Utils.getTile(this.getWorld(), this.getPos().offset(inputDir));
            if (inputTile == null) return false;
            boolean[] booleans = new boolean[1];
            booleans[0] = false;
            outputTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                inputTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inputDir.getOpposite()).ifPresent(in -> {
                    booleans[0] = Utils.transferItems(in, out,true, this::accepts);
                });
            });
            return booleans[0];
        }

        public boolean accepts(ItemStack stack){
            boolean hasItem = itemHandler.map(h -> {
                List<Item> list = new ObjectArrayList<>();
                IItemHandlerModifiable outputs = h.getHandler(SlotType.DISPLAY_SETTABLE);
                for (int i = 0; i < outputs.getSlots(); i++) {
                    ItemStack slot = outputs.getStackInSlot(i);
                    if (!slot.isEmpty()) {
                        if (slot.getItem() == stack.getItem()){
                            list.add(slot.copy().getItem());
                        }
                    }
                }
                return list.isEmpty() == blacklist;
            }).orElse(false);
            return hasItem;
        }
    }

    public static class TileEntityFluidTranslocator extends TileEntityTranslocator<TileEntityFluidTranslocator> {
        public TileEntityFluidTranslocator(Machine<?> type) {
            super(type, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
            this.itemHandler.set(() -> new FluidTranslocatorItemHandler(this));
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing().getOpposite();
            Direction inputDir = this.getFacing();
            TileEntity outputTile = Utils.getTile(this.getWorld(), this.getPos().offset(outputDir));
            if (outputTile == null) return false;
            TileEntity inputTile = Utils.getTile(this.getWorld(), this.getPos().offset(inputDir));
            if (inputTile == null) {
                FluidState state = world.getFluidState(this.getPos().offset(inputDir));
                if (!state.isEmpty() && this.accepts(new FluidStack(state.getFluid(), 1)) && !((state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER) && world.getBlockState(this.getPos().offset(inputDir)).getBlock() != Blocks.WATER)){
                    boolean[] booleans = new boolean[1];
                    booleans[0] = false;
                    outputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                        int fill = out.fill(new FluidStack(state.getFluid(), 1000), IFluidHandler.FluidAction.SIMULATE);
                        if (fill == 1000){
                            out.fill(new FluidStack(state.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                            booleans[0] = true;
                            world.setBlockState(this.getPos().offset(inputDir), Blocks.AIR.getDefaultState());
                        }
                    });
                    return booleans[0];
                }
                return false;
            }
            boolean[] booleans = new boolean[1];
            booleans[0] = false;
            outputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, outputDir.getOpposite()).ifPresent(out -> {
                inputTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, inputDir.getOpposite()).ifPresent(in -> {
                    booleans[0] = Utils.transferFluids(in, out,1000, this::accepts);
                });
            });
            return booleans[0];
        }

        public boolean accepts(FluidStack stack){
            boolean hasItem = itemHandler.map(h -> {
                List<Item> list = new ObjectArrayList<>();;
                IItemHandlerModifiable outputs = h.getHandler(SlotTypes.FLUID_DISPLAY_SETTABLE);
                for (int i = 0; i < outputs.getSlots(); i++) {
                    ItemStack slot = outputs.getStackInSlot(i);
                    if (!slot.isEmpty()) {
                        if (slot.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).map(f -> f.getFluidInTank(0).getFluid() == stack.getFluid()).orElse(false)){
                            list.add(slot.copy().getItem());
                        }
                    }
                }
                return list.isEmpty() == blacklist;
            }).orElse(false);
            return hasItem;
        }
        public static class FluidTranslocatorItemHandler extends MachineItemHandler<TileEntityFluidTranslocator> {
            public FluidTranslocatorItemHandler(TileEntityFluidTranslocator tile) {
                super(tile);
                int count = tile.getMachineType().getCount(tile.getMachineTier(), SlotTypes.FLUID_DISPLAY_SETTABLE);
                inventories.put(SlotTypes.FLUID_DISPLAY_SETTABLE, new FakeTrackedItemHandler<>(tile, count, SlotTypes.FLUID_DISPLAY_SETTABLE.output, SlotTypes.FLUID_DISPLAY_SETTABLE.input, SlotTypes.FLUID_DISPLAY_SETTABLE.tester, SlotTypes.FLUID_DISPLAY_SETTABLE.ev));
            }
        }
    }
}
