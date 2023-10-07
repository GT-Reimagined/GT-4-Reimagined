package trinsdar.gt4r.blockentity.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import tesseract.TesseractCapUtils;
import tesseract.api.item.ExtendedItemContainer;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.gui.ButtonOverlays;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class BlockEntityItemFilter extends BlockEntityMachine<BlockEntityItemFilter> implements IFilterable {
    boolean blacklist = false;
    boolean nbt = true;
    boolean outputRedstone = false;
    boolean invertRedstone = false;
    boolean emitEnergy = false;
    public BlockEntityItemFilter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        if (type.has(ENERGY)) {
            energyHandler.set(() -> new MachineEnergyHandler<BlockEntityItemFilter>(this, 0L, this.getMachineTier().getVoltage() * 66L, this.getMachineTier().getVoltage(), this.getMachineTier().getVoltage(), 1, 1){
                @Override
                public boolean canOutput(Direction direction) {
                    return super.canOutput(direction) && direction == tile.getFacing().getOpposite() && tile.emitEnergy;
                }
            });
        }
    }

    public boolean accepts(ItemStack stack){
        boolean hasItem = itemHandler.map(h -> {
            List<Item> list = new ObjectArrayList<>();
            ExtendedItemContainer outputs = h.getHandler(SlotType.DISPLAY_SETTABLE);
            for (int i = 0; i < outputs.getSlots(); i++) {
                ItemStack slot = outputs.getStackInSlot(i);
                if (!slot.isEmpty()) {
                    if (slot.getItem() == stack.getItem()){
                        if (!nbt || Objects.equals(slot.getTag(), stack.getTag())) {
                            list.add(slot.copy().getItem());
                        }
                    }
                }
            }
            return list.isEmpty() == blacklist;
        }).orElse(false);
        return hasItem;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            int[] data = ((GuiEvents.GuiEvent)event).data;
            switch (data[1]) {
                case 0:
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(new TextComponent( (emitEnergy ? "Emit energy to output side" : "Don't emit energy")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(this.level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                    break;
                case 1:
                    outputRedstone = !outputRedstone;
                    playerEntity.sendMessage(new TextComponent( (outputRedstone ? "Emit redstone if slots contain something" : "Don't emit redstone")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(this.level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                    break;
                case 2:
                    invertRedstone = !invertRedstone;
                    playerEntity.sendMessage(new TextComponent( (invertRedstone ? "I" : "Don't i") + "nvert redstone"), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(this.level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                    break;
                case 3:
                    blacklist = !blacklist;
                    playerEntity.sendMessage(new TextComponent( (blacklist ? "I" : "Don't i") + "nvert filter"), playerEntity.getUUID());
                    break;
                case 4:
                    nbt = !nbt;
                    playerEntity.sendMessage(new TextComponent( (nbt ? "NBT has to match" : "Ignore NBT")), playerEntity.getUUID());
                    break;
            }
        }
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addSwitchButton(8, 63, 16, 16, ButtonOverlays.ENERGY_OFF, ButtonOverlays.ENERGY_ON, h -> ((BlockEntityItemFilter)h).emitEnergy, true);
        instance.addSwitchButton(26, 63, 16, 16, ButtonOverlays.REDSTONE_CONTROL_OFF, ButtonOverlays.REDSTONE_CONTROL_ON, h -> ((BlockEntityItemFilter)h).outputRedstone, true);
        instance.addSwitchButton(44, 63, 16, 16, ButtonOverlays.INVERT_REDSTONE_OFF, ButtonOverlays.INVERT_REDSTONE_ON, h -> ((BlockEntityItemFilter)h).invertRedstone, true);
        instance.addSwitchButton(62, 63, 16, 16, ButtonOverlays.BLACKLIST_OFF, ButtonOverlays.BLACKLIST_ON, h -> ((BlockEntityItemFilter)h).blacklist, true);
        instance.addSwitchButton(80, 63, 16, 16, ButtonOverlays.NBT_OFF, ButtonOverlays.NBT_ON, h -> ((BlockEntityItemFilter)h).nbt, true);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (getCover(this.getFacing().getOpposite()).isEmpty()){
            processItemOutput();
        }
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        super.onMachineEvent(event, data);
        if (event == SlotType.IT_OUT && outputRedstone){
            AntimatterPlatformUtils.markAndNotifyBlock(this.level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
        }
    }

    protected boolean processItemOutput() {
        Direction outputDir = this.getFacing().getOpposite();
        BlockEntity adjTile = Utils.getTile(this.getLevel(), this.getBlockPos().relative(outputDir));
        if (adjTile == null) return false;
        boolean[] booleans = new boolean[1];
        booleans[0] = false;
        TesseractCapUtils.getItemHandler(adjTile, outputDir.getOpposite()).ifPresent(adjHandler -> {
            booleans[0] = this.itemHandler.map(h -> Utils.transferItems(h.getHandler(SlotTypes.FILTERABLE), adjHandler,true)).orElse(false);
        });
        return booleans[0];
    }

    @Override
    public int getWeakRedstonePower(Direction facing) {
        if (outputRedstone){
            int[] redstone = new int[1];
            redstone[0] = this.itemHandler.map(i -> {
                for (int slot = 0; slot < i.getHandler(SlotTypes.FILTERABLE).getSlots(); slot++){
                    ItemStack stack = i.getHandler(SlotTypes.FILTERABLE).getStackInSlot(slot);
                    if (!stack.isEmpty()) return invertRedstone ? 0 : 15;
                }
                return invertRedstone ? 15 : 0;
            }).orElse(0);
            if (redstone[0] > 0){
                return redstone[0];
            }
        }
        return super.getWeakRedstonePower(facing);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        blacklist = tag.getBoolean("blacklist");
        nbt = tag.getBoolean("nbt");
        outputRedstone = tag.getBoolean("outputRedstone");
        invertRedstone = tag.getBoolean("invertRedstone");
        emitEnergy = tag.getBoolean("emitsEnergy");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("blacklist", blacklist);
        tag.putBoolean("nbt", nbt);
        tag.putBoolean("outputRedstone", outputRedstone);
        tag.putBoolean("invertRedstone", invertRedstone);
        tag.putBoolean("emitEnergy", emitEnergy);
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public boolean isEmitEnergy() {
        return emitEnergy;
    }

    public boolean isInvertRedstone() {
        return invertRedstone;
    }

    public boolean isNbt() {
        return nbt;
    }

    public boolean isOutputRedstone() {
        return outputRedstone;
    }

    public void markAndNotifyBlock(BlockPos arg, @Nullable LevelChunk levelchunk, BlockState blockstate, BlockState arg2, int j, int k) {
        Block block = arg2.getBlock();
        BlockState blockstate1 = level.getBlockState(arg);
        if (blockstate1 == arg2) {
            if (blockstate != blockstate1) {
                level.setBlocksDirty(arg, blockstate, blockstate1);
            }

            if ((j & 2) != 0 && (!level.isClientSide || (j & 4) == 0) && (level.isClientSide || levelchunk.getFullStatus() != null && levelchunk.getFullStatus().isOrAfter(ChunkHolder.FullChunkStatus.TICKING))) {
                level.sendBlockUpdated(arg, blockstate, arg2, j);
            }

            if ((j & 1) != 0) {
                level.blockUpdated(arg, blockstate.getBlock());
                if (!level.isClientSide && arg2.hasAnalogOutputSignal()) {
                    level.updateNeighbourForOutputSignal(arg, block);
                }
            }

            if ((j & 16) == 0 && k > 0) {
                int i = j & -34;
                blockstate.updateIndirectNeighbourShapes(level, arg, i, k - 1);
                arg2.updateNeighbourShapes(level, arg, i, k - 1);
                arg2.updateIndirectNeighbourShapes(level, arg, i, k - 1);
            }

            level.onBlockStateChange(arg, blockstate, blockstate1);
        }

    }
}
