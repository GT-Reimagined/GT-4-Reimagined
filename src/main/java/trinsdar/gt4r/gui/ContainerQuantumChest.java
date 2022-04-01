package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.slot.SlotFake;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;

public class ContainerQuantumChest extends ContainerBasicMachine<TileEntityQuantumChest> {
    public ContainerQuantumChest(TileEntityQuantumChest tile, Inventory playerInv, MenuHandlerMachine<TileEntityQuantumChest, ContainerMachine<TileEntityQuantumChest>> handler, int windowId) {
        super(tile, playerInv, handler, windowId);
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;
        if (reverseDirection) {
            i = endIndex - 1;
        }

        if (stack.isStackable()) {
            while (!stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }

                Slot slot = this.slots.get(i);
                boolean continueLoop = false;
                if (slot instanceof SlotFake || !slot.mayPlace(stack)) {
                    continueLoop = true;
                }
                ItemStack itemstack = slot.getItem();
                if (!continueLoop && !itemstack.isEmpty() && consideredTheSameItem(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = slot.getMaxStackSize(stack);
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.setChanged();
                        if (slot instanceof SlotItemHandler) {
                            SlotItemHandler handler = (SlotItemHandler) slot;
                            IItemHandler handle = handler.getItemHandler();
                            if (handle instanceof TrackedItemHandler<?>) {
                                ((TrackedItemHandler<?>) handle).onContentsChanged(slot.index);
                            }
                        }
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.setChanged();
                        flag = true;
                    }
                }

                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }

            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }

                Slot slot1 = this.slots.get(i);
                boolean continueLoop = false;
                if (slot1 instanceof SlotFake) {
                    continueLoop = true;
                }
                ItemStack itemstack1 = slot1.getItem();
                if (!continueLoop && itemstack1.isEmpty() && slot1.mayPlace(stack)) {
                    if (stack.getCount() > slot1.getMaxStackSize()) {
                        slot1.set(stack.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.set(stack.split(stack.getCount()));
                    }

                    slot1.setChanged();
                    flag = true;
                    break;
                }

                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
    }
}
