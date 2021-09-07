package trinsdar.gt4r.tile.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.IItemHandlerModifiable;
import tesseract.api.capability.TesseractGTCapability;
import trinsdar.gt4r.data.SlotTypes;

import java.util.List;
import java.util.Objects;

public class TileEntityItemFilter extends TileEntityMachine<TileEntityItemFilter> {
    boolean blacklist;
    boolean nbt;
    boolean outputRedstone;
    boolean invertRedstone;
    boolean emitEnergy;
    public TileEntityItemFilter(Machine<?> type) {
        super(type);
    }

    public boolean accepts(ItemStack stack){
        boolean hasItem = itemHandler.map(h -> {
            List<Item> list = new ObjectArrayList<>();
            IItemHandlerModifiable outputs = h.getHandler(SlotType.DISPLAY_SETTABLE);
            for (int i = 0; i < outputs.getSlots(); i++) {
                ItemStack slot = outputs.getStackInSlot(i);
                if (!slot.isEmpty()) {
                    if (slot.getItem() == stack.getItem()){
                        boolean add = !nbt || Objects.equals(slot.getTag(), stack.getTag());
                        if (add) {
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
        if (event == GuiEvent.EXTRA_SWITCH) {
            switch (data[0]) {
                case 0:
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(new StringTextComponent( (emitEnergy ? "Don't emit energy" : "Emit energy to output side")), playerEntity.getUniqueID());
                    break;
                case 1:
                    outputRedstone = !outputRedstone;
                    break;
                case 2:
                    invertRedstone = !invertRedstone;
                    break;
                case 3:
                    blacklist = !blacklist;
                    break;
                case 4:
                    nbt = !nbt;
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
}
