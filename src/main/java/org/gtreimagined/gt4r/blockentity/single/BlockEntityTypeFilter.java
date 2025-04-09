package org.gtreimagined.gt4r.blockentity.single;

import org.gtreimagined.gtlib.gui.SlotType;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gtlib.util.TagUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockEntityTypeFilter extends BlockEntityItemFilter {
    public BlockEntityTypeFilter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public boolean test(SlotType<?> slotType, int slotId, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            return itemHandler.map(i -> {
                ItemStack tagStack = i.getHandler(SlotType.DISPLAY_SETTABLE).getStackInSlot(0);
                if (tagStack.isEmpty()) return false;
                List<TagKey<Item>> tags = tagStack.getItem().builtInRegistryHolder().tags().toList();
                String compare = tags.stream().filter(t -> t.location().toString().contains("/") && t.location().getNamespace().equals("forge")).findFirst().map(t -> t.location().toString()).orElse("");
                if (compare.isEmpty()) return false;
                compare = compare.substring(0, compare.lastIndexOf("/"));

                boolean hasTag = stack.is(TagUtils.getItemTag(new ResourceLocation(compare)));
                return blacklist != hasTag;
            }).orElse(false);
        }
        return super.test(slotType, slotId, stack);
    }
}
