package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.gui.slot.IClickableSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class SlotQuantum extends AbstractSlot<SlotQuantum> implements IClickableSlot {
    public SlotQuantum(SlotType<SlotQuantum> type, IGuiHandler tile, IItemHandler stackHandler, int index, int x, int y) {
        super(type, tile, stackHandler, index, x, y);
    }

    @Override
    public ItemStack clickSlot(int pDragType, ClickType pClickType, Player pPlayer, AbstractContainerMenu container) {
        ItemStack itemstack = ItemStack.EMPTY;
        Inventory playerinventory = pPlayer.getInventory();
        /*if (pClickType == ClickType.QUICK_CRAFT) {
            int i1 = this.quickcraftStatus;
            this.quickcraftStatus = getQuickcraftHeader(pDragType);
            if ((i1 != 1 || this.quickcraftStatus != 2) && i1 != this.quickcraftStatus) {
                this.resetQuickCraft();
            } else if (playerinventory.getCarried().isEmpty()) {
                this.resetQuickCraft();
            } else if (this.quickcraftStatus == 0) {
                this.quickcraftType = getQuickcraftType(pDragType);
                if (isValidQuickcraftType(this.quickcraftType, pPlayer)) {
                    this.quickcraftStatus = 1;
                    this.quickcraftSlots.clear();
                } else {
                    this.resetQuickCraft();
                }
            } else if (this.quickcraftStatus == 1) {
                Slot slot7 = this.slots.get(pSlotId);
                ItemStack itemstack12 = playerinventory.getCarried();
                if (slot7 != null && canItemQuickReplace(slot7, itemstack12, true) && slot7.mayPlace(itemstack12) && (this.quickcraftType == 2 || itemstack12.getCount() > this.quickcraftSlots.size()) && this.canDragTo(slot7)) {
                    this.quickcraftSlots.add(slot7);
                }
            } else if (this.quickcraftStatus == 2) {
                if (!this.quickcraftSlots.isEmpty()) {
                    ItemStack itemstack10 = playerinventory.getCarried().copy();
                    int k1 = playerinventory.getCarried().getCount();

                    for(Slot slot8 : this.quickcraftSlots) {
                        ItemStack itemstack13 = playerinventory.getCarried();
                        if (slot8 != null && canItemQuickReplace(slot8, itemstack13, true) && slot8.mayPlace(itemstack13) && (this.quickcraftType == 2 || itemstack13.getCount() >= this.quickcraftSlots.size()) && this.canDragTo(slot8)) {
                            ItemStack itemstack14 = itemstack10.copy();
                            int j3 = slot8.hasItem() ? slot8.getItem().getCount() : 0;
                            getQuickCraftSlotCount(this.quickcraftSlots, this.quickcraftType, itemstack14, j3);
                            int k3 = Math.min(itemstack14.getMaxStackSize(), slot8.getMaxStackSize(itemstack14));
                            if (itemstack14.getCount() > k3) {
                                itemstack14.setCount(k3);
                            }

                            k1 -= itemstack14.getCount() - j3;
                            slot8.set(itemstack14);
                        }
                    }

                    itemstack10.setCount(k1);
                    playerinventory.setCarried(itemstack10);
                }

                container.resetQuickCraft();
            } else {
                container.resetQuickCraft();
            }
        } else if (container.quickcraftStatus != 0) {
            container.resetQuickCraft();
        } else*/ if ((pClickType == ClickType.PICKUP || pClickType == ClickType.QUICK_MOVE) && (pDragType == 0 || pDragType == 1)) {
            if (pClickType == ClickType.QUICK_MOVE) {
                if (!this.mayPickup(pPlayer)) {
                    return ItemStack.EMPTY;
                }

                for(ItemStack itemstack8 = container.quickMoveStack(pPlayer, this.index); !itemstack8.isEmpty() && ItemStack.isSame(this.getItem(), itemstack8); itemstack8 = container.quickMoveStack(pPlayer, this.index)) {
                    itemstack = itemstack8.copy();
                }
            } else {
                ItemStack containedStack = this.getItem();
                ItemStack heldStack = container.getCarried();
                if (!containedStack.isEmpty()) {
                    itemstack = containedStack.copy();
                }

                if (containedStack.isEmpty()) {
                    if (!heldStack.isEmpty() && this.mayPlace(heldStack)) {
                        int j2 = pDragType == 0 ? heldStack.getCount() : 1;
                        if (j2 > this.getMaxStackSize(heldStack)) {
                            j2 = this.getMaxStackSize(heldStack);
                        }

                        this.set(heldStack.split(j2));
                    }
                } else if (this.mayPickup(pPlayer)) {
                    if (heldStack.isEmpty()) {
                        if (containedStack.isEmpty()) {
                            this.set(ItemStack.EMPTY);
                            container.setCarried(ItemStack.EMPTY);
                        } else {
                            int k2 = pDragType == 0 ? containedStack.getCount() : (containedStack.getCount() + 1) / 2;
                            container.setCarried(this.remove(k2));
                            if (containedStack.isEmpty()) {
                                this.set(ItemStack.EMPTY);
                            }

                            this.onTake(pPlayer, container.getCarried());
                        }
                    } else if (this.mayPlace(heldStack)) {
                        if (ItemStack.isSame(containedStack, heldStack)) {
                            int l2 = pDragType == 0 ? heldStack.getCount() : 1;
                            if (l2 > this.getMaxStackSize(heldStack) - containedStack.getCount()) {
                                l2 = this.getMaxStackSize(heldStack) - containedStack.getCount();
                            }

                            heldStack.shrink(l2);
                            containedStack.grow(l2);
                        } else if (heldStack.getCount() <= containedStack.getMaxStackSize() && containedStack.getCount() <= containedStack.getMaxStackSize()) {
                            this.set(heldStack);
                            container.setCarried(containedStack);
                        }
                    } else if (heldStack.getMaxStackSize() > 1 && ItemStack.isSame(containedStack, heldStack) && !containedStack.isEmpty()) {
                        int i3 = containedStack.getCount();
                        if (i3 + heldStack.getCount() <= heldStack.getMaxStackSize()) {
                            heldStack.grow(i3);
                            containedStack = this.remove(i3);
                            if (containedStack.isEmpty()) {
                                this.set(ItemStack.EMPTY);
                            }

                            this.onTake(pPlayer, container.getCarried());
                        }
                    }
                }

                this.setChanged();
            }
        } else if (pClickType == ClickType.SWAP) {
            ItemStack itemstack1 = playerinventory.getItem(pDragType);
            ItemStack itemstack2 = this.getItem();
            if (!itemstack1.isEmpty() || !itemstack2.isEmpty()) {
                if (itemstack1.isEmpty()) {
                    if (this.mayPickup(pPlayer)) {
                        playerinventory.setItem(pDragType, itemstack2);
                        this.onSwapCraft(itemstack2.getCount());
                        this.set(ItemStack.EMPTY);
                        this.onTake(pPlayer, itemstack2);
                    }
                } else if (itemstack2.isEmpty()) {
                    if (this.mayPlace(itemstack1)) {
                        int i = this.getMaxStackSize(itemstack1);
                        if (itemstack1.getCount() > i) {
                            this.set(itemstack1.split(i));
                        } else {
                            this.set(itemstack1);
                            playerinventory.setItem(pDragType, ItemStack.EMPTY);
                        }
                    }
                } else if (this.mayPickup(pPlayer) && this.mayPlace(itemstack1)) {
                    int l1 = this.getMaxStackSize(itemstack1);
                    if (itemstack1.getCount() > l1) {
                        this.set(itemstack1.split(l1));
                        this.onTake(pPlayer, itemstack2);
                        if (!playerinventory.add(itemstack2)) {
                            pPlayer.drop(itemstack2, true);
                        }
                    } else {
                        this.set(itemstack1);
                        playerinventory.setItem(pDragType, itemstack2);
                        this.onTake(pPlayer, itemstack2);
                    }
                }
            }
        } else if (pClickType == ClickType.CLONE && pPlayer.getAbilities().instabuild && container.getCarried().isEmpty()) {
            if (this.hasItem()) {
                ItemStack itemstack7 = this.getItem().copy();
                itemstack7.setCount(itemstack7.getMaxStackSize());
                container.setCarried(itemstack7);
            }
        } else if (pClickType == ClickType.THROW && container.getCarried().isEmpty()) {
            if (this.hasItem() && this.mayPickup(pPlayer)) {
                ItemStack itemstack6 = this.remove(pDragType == 0 ? 1 : this.getItem().getCount());
                this.onTake(pPlayer, itemstack6);
                pPlayer.drop(itemstack6, true);
            }
        } else if (pClickType == ClickType.PICKUP_ALL) {
            ItemStack itemstack5 = container.getCarried();
            if (!itemstack5.isEmpty() && (!this.hasItem() || !this.mayPickup(pPlayer))) {
                int j1 = pDragType == 0 ? 0 : container.slots.size() - 1;
                int i2 = pDragType == 0 ? 1 : -1;

                for(int j = 0; j < 2; ++j) {
                    for(int k = j1; k >= 0 && k < container.slots.size() && itemstack5.getCount() < itemstack5.getMaxStackSize(); k += i2) {
                        Slot slot1 = container.slots.get(k);
                        if (slot1.hasItem() && AbstractContainerMenu.canItemQuickReplace(slot1, itemstack5, true) && slot1.mayPickup(pPlayer) && container.canTakeItemForPickAll(itemstack5, slot1)) {
                            ItemStack itemstack3 = slot1.getItem();
                            if (j != 0 || itemstack3.getCount() != itemstack3.getMaxStackSize()) {
                                int l = Math.min(itemstack5.getMaxStackSize() - itemstack5.getCount(), itemstack3.getCount());
                                ItemStack itemstack4 = slot1.remove(l);
                                itemstack5.grow(l);
                                if (itemstack4.isEmpty()) {
                                    slot1.set(ItemStack.EMPTY);
                                }

                                slot1.onTake(pPlayer, itemstack4);
                            }
                        }
                    }
                }
            }

            container.broadcastChanges();
        }

        return itemstack;
    }

    @Override
    public int getMaxStackSize() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxStackSize(@Nonnull ItemStack stack) {
        if (stack.getMaxStackSize() == 1) return 1;
        return Integer.MAX_VALUE;
    }
}
