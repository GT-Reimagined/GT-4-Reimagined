package trinsdar.gt4r.blockentity.single;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.item.ITrackedHandler;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.SlotTypes;

public class BlockEntityDigitalChest extends BlockEntityMachine<BlockEntityDigitalChest> implements IFilterableHandler {
    public BlockEntityDigitalChest(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int guiHeight() {
        return 221;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            final int[] data = ((GuiEvents.GuiEvent)event).data;
            if (data[1] == 0){
                itemHandler.ifPresent(i -> {
                    ItemStack orb = i.getHandler(SlotTypes.DATA).getStackInSlot(0);
                    if (orb.getItem() == GT4RData.StorageDataOrb){
                        CompoundTag tag = orb.getTag();
                        if (tag != null){
                            if (tag.contains("Data")){
                                CompoundTag nbt = tag.getCompound("Data");
                                if (nbt.contains("Items")){
                                    ITrackedHandler storage = i.getHandler(SlotType.STORAGE);
                                    ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
                                    if (!tagList.isEmpty()){
                                        if (!isInventoryEmpty(storage)){
                                            playerEntity.sendMessage(new TranslatableComponent("message.gt4r.digital_chest_inventory"), playerEntity.getUUID());
                                            return;
                                        }
                                        ItemStack newStack = new ItemStack(GTCoreItems.DataOrb);
                                        for (int j = 0; j < tagList.size(); j++)
                                        {
                                            CompoundTag itemTags = tagList.getCompound(j);
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
                    if (i.getHandler(SlotTypes.DATA).getStackInSlot(0).getItem() == GTCoreItems.DataOrb){
                        ITrackedHandler storage = i.getHandler(SlotType.STORAGE);
                        if (!isInventoryEmpty(storage)){
                            CompoundTag nbt = ((TrackedItemHandler<?>)storage).serialize(new CompoundTag());
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

    private boolean isInventoryEmpty(ITrackedHandler handler){
        boolean empty = true;
        for (int i = 0; i < handler.getSlots(); i++){
            if (!handler.getStackInSlot(i).isEmpty()){
                empty = false;
                break;
            }
        }
        return empty;
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            return stack.getItem() != GT4RData.StorageDataOrb;
        }
        return true;
    }
}
