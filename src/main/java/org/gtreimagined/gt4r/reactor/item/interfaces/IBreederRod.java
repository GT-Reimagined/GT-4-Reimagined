package org.gtreimagined.gt4r.reactor.item.interfaces;

import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.FuelType;

import javax.annotation.Nonnull;


public interface IBreederRod {

    ItemStack getProduct(@Nonnull ItemStack itemStack);

    FuelType getFuelType();

    int getMaxNeutrons(@Nonnull ItemStack itemStack);

    int getStoredNeutrons(@Nonnull ItemStack itemStack);

    void setNeutrons(@Nonnull ItemStack itemStack, int neutrons);
}
