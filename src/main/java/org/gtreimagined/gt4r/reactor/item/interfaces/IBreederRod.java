package org.gtreimagined.gt4r.reactor.item.interfaces;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


public interface IBreederRod {

    public ItemStack getProduct(@Nonnull ItemStack itemStack);

    public int getMaxNeutrons(@Nonnull ItemStack itemStack);

    public int getStoredNeutrons(@Nonnull ItemStack itemStack);

    public void setNeutrons(@Nonnull ItemStack itemStack, int neutrons);

    public int getReactorHeatDivisor(@Nonnull ItemStack itemStack);

    public int getHeatMultiplier(@Nonnull ItemStack itemStack);
}
