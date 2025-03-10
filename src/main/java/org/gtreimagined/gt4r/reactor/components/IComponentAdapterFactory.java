package org.gtreimagined.gt4r.reactor.components;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


public interface IComponentAdapterFactory {

    public boolean canAdaptItem(@Nonnull ItemStack itemStack);

    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y);
}
