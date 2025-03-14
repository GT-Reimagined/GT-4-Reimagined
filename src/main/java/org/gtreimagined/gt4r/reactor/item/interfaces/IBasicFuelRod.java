package org.gtreimagined.gt4r.reactor.item.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.Config;
import org.gtreimagined.gt4r.reactor.components.FuelType;

public interface IBasicFuelRod {

    FuelType getFuelType();

    int getRodCount(@Nonnull ItemStack itemStack);

    int getRemainingHealth(@Nonnull ItemStack itemStack);

    void applyDamage(@Nonnull ItemStack itemStack, int damage);

    @Nonnull ItemStack getProduct(@Nonnull ItemStack itemStack);
}
