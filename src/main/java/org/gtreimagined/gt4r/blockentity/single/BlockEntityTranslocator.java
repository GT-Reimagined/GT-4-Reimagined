package org.gtreimagined.gt4r.blockentity.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.machine.MachineEnergyHandler;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.IGuiElement;
import org.gtreimagined.gtlib.gui.SlotType;
import org.gtreimagined.gtlib.gui.event.GuiEvents;
import org.gtreimagined.gtlib.gui.event.IGuiEvent;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gtlib.util.FluidUtils;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.gtreimagined.gt4r.gui.ButtonOverlays;

import java.util.List;

public abstract class BlockEntityTranslocator<T extends BlockEntityTranslocator<T>> extends BlockEntityMachine<T> {
    Class<?> cap;
    boolean blacklist = false;
    boolean emitEnergy = false;
    public BlockEntityTranslocator(Machine<?> type, BlockPos pos, BlockState state, Class<?> cap) {
        super(type, pos, state);
        this.cap = cap;
        energyHandler.set(() -> new MachineEnergyHandler<T>(((T)this), 0L, 32 * 66, this.getMachineTier().getVoltage(), this.getMachineTier().getVoltage(), 1, 1){
            @Override
            public boolean canOutput(Direction direction) {
                return super.canOutput(direction) && direction == tile.getFacing().getOpposite() && tile.emitEnergy;
            }
        });
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addSwitchButton(8, 63, 16, 16, ButtonOverlays.ENERGY_OFF, ButtonOverlays.ENERGY_ON, h -> ((BlockEntityTranslocator)h).emitEnergy, true);
        instance.addSwitchButton(26, 63, 16, 16, ButtonOverlays.BLACKLIST_OFF, ButtonOverlays.BLACKLIST_ON, h -> ((BlockEntityTranslocator)h).blacklist, true);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (coverHandler.map(c -> !c.get(this.getFacing().getOpposite()).blocksOutput(cap, this.getFacing().getOpposite()) && !c.get(this.getFacing()).blocksInput(cap, this.getFacing())).orElse(true)){
            this.processOutput();
        }
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            int[] data = ((GuiEvents.GuiEvent)event).data;
            switch (data[0]) {
                case 0:
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(new TextComponent( (emitEnergy ? "Emit energy to output side" : "Don't emit energy")), playerEntity.getUUID());
                    break;
                case 1:
                    blacklist = !blacklist;
                    playerEntity.sendMessage(new TextComponent( (blacklist ? "I" : "Don't i") + "nvert filter"), playerEntity.getUUID());
                    break;
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        blacklist = tag.getBoolean("blacklist");
        emitEnergy = tag.getBoolean("emitsEnergy");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("blacklist", blacklist);
        tag.putBoolean("emitEnergy", emitEnergy);
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public boolean isEmitEnergy() {
        return emitEnergy;
    }

    protected abstract boolean processOutput();

    public static class BlockEntityItemTranslocator extends BlockEntityTranslocator<BlockEntityItemTranslocator> {
        public BlockEntityItemTranslocator(Machine<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state, IItemHandler.class);
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing().getOpposite();
            Direction inputDir = this.getFacing();
            BlockEntity outputTile = Utils.getTile(this.getLevel(), this.getBlockPos().relative(outputDir));
            if (outputTile == null) return false;
            BlockEntity inputTile = Utils.getTile(this.getLevel(), this.getBlockPos().relative(inputDir));
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
                IItemHandler outputs = h.getHandler(SlotType.DISPLAY_SETTABLE);
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

    public static class BlockEntityFluidTranslocator extends BlockEntityTranslocator<BlockEntityFluidTranslocator> {
        public BlockEntityFluidTranslocator(Machine<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state, IFluidHandler.class);
        }

        protected boolean processOutput() {
            Direction outputDir = this.getFacing().getOpposite();
            Direction inputDir = this.getFacing();
            return FluidUtils.getFluidHandler(this.level, this.getBlockPos().relative(outputDir), this.getCachedBlockEntity(outputDir), inputDir).map(out -> {
               IFluidHandler in = FluidUtils.getFluidHandler(this.level, this.getBlockPos().relative(inputDir), this.getCachedBlockEntity(inputDir), outputDir).resolve().orElse(null);
               if (in == null){
                   FluidState state = level.getFluidState(this.getBlockPos().relative(inputDir));
                   if (!state.isEmpty() && this.accepts(new FluidStack(state.getType(), 1)) && !((state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER) && level.getBlockState(this.getBlockPos().relative(inputDir)).getBlock() != Blocks.WATER)){
                       int fill = out.fill(new FluidStack(state.getType(), 1000), FluidAction.SIMULATE);
                       if (fill == 1000){
                           out.fill(new FluidStack(state.getType(), 1000), FluidAction.EXECUTE);
                           level.setBlockAndUpdate(this.getBlockPos().relative(inputDir), Blocks.AIR.defaultBlockState());
                           return true;
                       }
                   }
                   return false;
               } else {
                   return Utils.transferFluids(in, out, 1000, this::accepts);
               }
            }).orElse(false);
        }

        public boolean accepts(FluidStack stack){
            boolean hasItem = itemHandler.map(h -> {
                List<Item> list = new ObjectArrayList<>();;
                IItemHandler outputs = h.getHandler(SlotType.FLUID_DISPLAY_SETTABLE);
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
    }
}
