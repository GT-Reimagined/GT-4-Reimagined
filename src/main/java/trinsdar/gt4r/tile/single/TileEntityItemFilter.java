package trinsdar.gt4r.tile.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.List;
import java.util.Objects;

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class TileEntityItemFilter extends TileEntityMachine<TileEntityItemFilter> {
    boolean blacklist = false;
    boolean nbt = true;
    boolean outputRedstone = false;
    boolean invertRedstone = false;
    boolean emitEnergy = false;
    public TileEntityItemFilter(Machine<?> type) {
        super(type);
        if (type.has(ENERGY)) {
            energyHandler.set(() -> new MachineEnergyHandler<TileEntityItemFilter>(this, 0L, this.getMachineTier().getVoltage() * 66L, this.getMachineTier().getVoltage(), this.getMachineTier().getVoltage(), 1, 1){
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
            IItemHandlerModifiable outputs = h.getHandler(SlotType.DISPLAY_SETTABLE);
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
            return !list.isEmpty();
        }).orElse(false);
        return hasItem != blacklist;
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
                    outputRedstone = !outputRedstone;
                    playerEntity.sendMessage(new StringTextComponent( (outputRedstone ? "Emit redstone if slots contain something" : "Don't emit redstone")), playerEntity.getUniqueID());
                    break;
                case 2:
                    invertRedstone = !invertRedstone;
                    playerEntity.sendMessage(new StringTextComponent( (invertRedstone ? "I" : "Don't i") + "nvert redstone"), playerEntity.getUniqueID());
                    break;
                case 3:
                    blacklist = !blacklist;
                    playerEntity.sendMessage(new StringTextComponent( (blacklist ? "I" : "Don't i") + "nvert filter"), playerEntity.getUniqueID());
                    break;
                case 4:
                    nbt = !nbt;
                    playerEntity.sendMessage(new StringTextComponent( (nbt ? "NBT has to match" : "Ignore NBT")), playerEntity.getUniqueID());
                    break;
            }
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        blacklist = tag.getBoolean("blacklist");
        nbt = tag.getBoolean("nbt");
        outputRedstone = tag.getBoolean("outputRedstone");
        invertRedstone = tag.getBoolean("invertRedstone");
        emitEnergy = tag.getBoolean("emitsEnergy");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putBoolean("blacklist", blacklist);
        tag.putBoolean("nbt", nbt);
        tag.putBoolean("outputRedstone", outputRedstone);
        tag.putBoolean("invertRedstone", invertRedstone);
        tag.putBoolean("emitEnergy", emitEnergy);
        return tag;
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
}
