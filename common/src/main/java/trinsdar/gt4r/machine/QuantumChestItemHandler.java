package trinsdar.gt4r.machine;


import dev.architectury.injectables.annotations.ExpectPlatform;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.mixin.GTCapabilityProviderAccessor;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;

import javax.annotation.Nonnull;
import java.util.function.BiPredicate;

public class QuantumChestItemHandler extends MachineItemHandler<TileEntityQuantumChest> {
    int maxSize = Integer.MAX_VALUE;
    int digitalCount;
    public QuantumChestItemHandler(TileEntityQuantumChest tile) {
        super(tile);
        int count = tile.getMachineType().getCount(tile.getMachineTier(), SlotTypes.QUANTUM);
        inventories.put(SlotTypes.QUANTUM, new QuantumSlotTrackedHandler(tile, count, SlotTypes.QUANTUM.output, SlotTypes.QUANTUM.input, SlotTypes.QUANTUM.tester, SlotTypes.QUANTUM.ev, Integer.MAX_VALUE));
    }

    public static class QuantumSlotTrackedHandler extends TrackedItemHandler<TileEntityQuantumChest> {

        public QuantumSlotTrackedHandler(TileEntityQuantumChest tile, int size, boolean output, boolean input, BiPredicate<IGuiHandler, ItemStack> validator, ContentEvent contentEvent, int limit) {
            super(tile, size, output, input, validator, contentEvent, limit);
        }

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return stack.getMaxStackSize() == 1 ? stack.getMaxStackSize() : getSlotLimit(slot);
        }

        @Override
        public CompoundTag serializeNBT()
        {
            ListTag nbtTagList = new ListTag();
            for (int i = 0; i < stacks.size(); i++)
            {
                if (!stacks.get(i).isEmpty())
                {
                    CompoundTag itemTag = new CompoundTag();
                    itemTag.putInt("Slot", i);
                    save(itemTag, stacks.get(i));
                    nbtTagList.add(itemTag);
                }
            }
            CompoundTag nbt = new CompoundTag();
            nbt.put("Items", nbtTagList);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt)
        {
            ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
            for (int i = 0; i < tagList.size(); i++)
            {
                CompoundTag itemTags = tagList.getCompound(i);
                int slot = itemTags.getInt("Slot");

                if (slot >= 0 && slot < stacks.size())
                {
                    stacks.set(slot, of(itemTags));
                }
            }
            onLoad();
        }


    }
    @ExpectPlatform
    public static CompoundTag save(CompoundTag pCompoundTag, ItemStack stack) {
        return null;
    }

    @ExpectPlatform
    public static ItemStack of(CompoundTag pCompoundTag) {
        return ItemStack.EMPTY;
    }

    /*public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        renderer.draw(stack,"Item amount: " + digitalCount, left + 10, top + 19, 16448255);
    }*/
}
