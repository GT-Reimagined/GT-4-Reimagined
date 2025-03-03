package org.gtreimagined.gt4r.blockentity.single;

import net.minecraft.world.item.ItemStack;

public interface IFilterable {
    boolean accepts(ItemStack stack);
}
