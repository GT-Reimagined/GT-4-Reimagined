package org.gtreimagined.gt4r.reactor.item.interfaces;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public interface INeutronReflector {

    public int getRemainingHealth(@Nonnull ItemStack itemStack);

    public void applyDamage(@Nonnull ItemStack itemStack, int damage);

    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack);

    public @Nullable ItemStack getProduct();
}
