package trinsdar.gt4r.tile.single;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityTypeFilter extends TileEntityItemFilter {
    public TileEntityTypeFilter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean accepts(ItemStack stack) {
        boolean proceed = itemHandler.map(i -> {
            ItemStack tagStack = i.getHandler(SlotType.DISPLAY_SETTABLE).getStackInSlot(0);
            if (tagStack.isEmpty()) return false;
            boolean hasTag = tagStack.getItem().builtInRegistryHolder().tags().filter(tag -> {
                ResourceLocation loc = tag.location();
                if ((loc.getNamespace().equals("forge") || loc.getNamespace().equals("minecraft")) && !loc.getPath().contains("/")){
                    return true;
                }
                return false;
            }).count() > 0;

            return blacklist != hasTag;
        }).orElse(false);
        return proceed;
    }
}
