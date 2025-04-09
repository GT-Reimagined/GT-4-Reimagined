package org.gtreimagined.gt4r.gui;

import org.gtreimagined.gtlib.capability.item.TrackedItemHandler;
import org.gtreimagined.gtlib.gui.MenuHandlerMachine;
import org.gtreimagined.gtlib.gui.container.ContainerBasicMachine;
import org.gtreimagined.gtlib.gui.container.ContainerMachine;
import org.gtreimagined.gtlib.gui.slot.AbstractSlot;
import org.gtreimagined.gtlib.gui.slot.SlotFake;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityQuantumChest;

public class ContainerQuantumChest extends ContainerBasicMachine<BlockEntityQuantumChest> {
    public ContainerQuantumChest(BlockEntityQuantumChest tile, Inventory playerInv, MenuHandlerMachine<BlockEntityQuantumChest, ContainerMachine<BlockEntityQuantumChest>> handler, int windowId) {
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
                if (!continueLoop && !itemstack.isEmpty() && ItemStack.isSame(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = slot.getMaxStackSize(stack);
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.setChanged();
                        if (slot instanceof AbstractSlot<?> abstractSlot) {
                            IItemHandler handle = abstractSlot.getContainer();
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
