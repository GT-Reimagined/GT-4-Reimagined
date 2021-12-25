package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.item.ITrackedHandler;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.machine.MaterialMachine;

public class TileEntityDigitalChest extends TileEntityMachine<TileEntityDigitalChest> implements IFilterable{
    public TileEntityDigitalChest(Machine<?> type) {
        super(type);
    }

    @Override
    public boolean accepts(ItemStack stack) {
        return stack.getItem() != GT4RData.StorageDataOrb;
    }

    @Override
    public int guiHeight() {
        return 221;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            final int[] data = ((GuiEvents.GuiEvent)event).data;
            if (data[1] == 0){
                itemHandler.ifPresent(i -> {
                    ItemStack orb = i.getHandler(SlotTypes.DATA).getStackInSlot(0);
                    if (orb.getItem() == GT4RData.StorageDataOrb){
                        CompoundNBT tag = orb.getTag();
                        if (tag != null){
                            if (tag.contains("Data")){
                                CompoundNBT nbt = tag.getCompound("Data");
                                if (nbt.contains("Items")){
                                    ITrackedHandler storage = i.getHandler(SlotTypes.FILTERABLE);
                                    ListNBT tagList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
                                    if (!tagList.isEmpty()){
                                        ItemStack newStack = new ItemStack(GT4RData.CircuitDataOrb);
                                        for (int j = 0; j < tagList.size(); j++)
                                        {
                                            CompoundNBT itemTags = tagList.getCompound(j);
                                            int slot = itemTags.getInt("Slot");

                                            if (slot >= 0 && slot < storage.getSlots())
                                            {
                                                storage.setStackInSlot(slot, ItemStack.of(itemTags));
                                            }
                                        }
                                        i.getHandler(SlotTypes.DATA).setStackInSlot(0, newStack);
                                    }
                                }

                            }

                        }

                    }
                });
            } else if (data[1] == 1){
                itemHandler.ifPresent(i -> {
                    if (i.getHandler(SlotTypes.DATA).getStackInSlot(0).getItem() == GT4RData.CircuitDataOrb){
                        ITrackedHandler storage = i.getHandler(SlotTypes.FILTERABLE);
                        CompoundNBT nbt = ((TrackedItemHandler<?>)storage).serializeNBT();
                        if (!nbt.getList("Items", Constants.NBT.TAG_COMPOUND).isEmpty()){
                            ItemStack newStack = new ItemStack(GT4RData.StorageDataOrb);
                            newStack.getOrCreateTag().put("Data", nbt);
                            i.getHandler(SlotTypes.DATA).setStackInSlot(0, newStack);
                            for (int j = 0; j < storage.getSlots(); j++){
                                storage.setStackInSlot(j, ItemStack.EMPTY);
                            }
                        }
                    }
                });
            }
        }
    }
}
