package trinsdar.gt4r.machine;


import dev.architectury.injectables.annotations.ExpectPlatform;
import earth.terrarium.botarium.common.energy.util.EnergyHooks;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import tesseract.TesseractCapUtils;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.blockentity.single.BlockEntityQuantumChest;

import javax.annotation.Nonnull;
import java.util.function.BiPredicate;

public class QuantumChestItemHandler extends MachineItemHandler<BlockEntityQuantumChest> {
    int maxSize = Integer.MAX_VALUE;
    int digitalCount;
    public QuantumChestItemHandler(BlockEntityQuantumChest tile) {
        super(tile);
        inventories.put(SlotType.STORAGE, new QuantumSlotTrackedHandler(tile, SlotType.STORAGE, 1, SlotType.STORAGE.output, SlotType.STORAGE.input, SlotType.STORAGE.tester, SlotType.STORAGE.ev, Integer.MAX_VALUE));
    }

    public static class QuantumSlotTrackedHandler extends TrackedItemHandler<BlockEntityQuantumChest> {
        Item item;
        int count;

        public QuantumSlotTrackedHandler(BlockEntityQuantumChest tile, SlotType<?> type, int size, boolean output, boolean input, BiPredicate<IGuiHandler, ItemStack> validator, ContentEvent contentEvent, int limit) {
            super(tile, type, size, output, input, validator, contentEvent, limit);
        }

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return stack.getMaxStackSize() == 1 ? stack.getMaxStackSize() : getSlotLimit(slot);
        }

        @Override
        public CompoundTag serialize(CompoundTag nbt) {
            if (item != Items.AIR) {
                nbt.putString("Item", AntimatterPlatformUtils.getIdFromItem(item).toString());
            }
            nbt.putInt("count", count);
            return nbt;
        }

        @Override
        public void deserialize(CompoundTag nbt) {
            if (nbt.contains("Item")){
                item = AntimatterPlatformUtils.getItemFromID(new ResourceLocation(nbt.getString("Item")));
            }
            count = nbt.getInt("count");
            onLoad();
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (slot == 0){
                if (stack.isEmpty() || stack.getTag() != null || FluidHooks.safeGetItemFluidManager(stack).isPresent() || TesseractCapUtils.getEnergyHandlerItem(stack).isPresent() || EnergyHooks.isEnergyItem(stack)) return stack;
                if (item == Items.AIR){
                    if (!simulate){
                        item = stack.getItem();
                        count = stack.getCount();
                    }
                    return ItemStack.EMPTY;
                }
                if (item != stack.getItem() || count == Integer.MAX_VALUE) return stack;
                if ((long) count + (long) stack.getCount() <= Integer.MAX_VALUE){
                    if (!simulate) {
                        count += stack.getCount();
                    }
                    return ItemStack.EMPTY;
                } else {
                    int add = Integer.MAX_VALUE - count;
                    if (!simulate){
                        count += add;
                    }
                    return new ItemStack(stack.getItem(), stack.getCount() - add);
                }
            }
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public @NotNull ItemStack getItem(int slot) {
            if (slot == 0){
                return new ItemStack(item, count);
            }
            return super.getItem(slot);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == 0){
                if (item == Items.AIR || count == 0){
                    return ItemStack.EMPTY;
                }
                int toRemove = Math.min(amount, count);
                if (!simulate){
                    count -= toRemove;
                }
                return new ItemStack(item, toRemove);
            }
            return super.extractItem(slot, amount, simulate);
        }
    }

    /*public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        renderer.draw(stack,"Item amount: " + digitalCount, left + 10, top + 19, 16448255);
    }*/
}
