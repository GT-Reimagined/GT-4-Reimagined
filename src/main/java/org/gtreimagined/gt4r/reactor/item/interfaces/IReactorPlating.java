package org.gtreimagined.gt4r.reactor.item.interfaces;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


public interface IReactorPlating {

    public default double getExplosionRadiusMultiplier(@Nonnull ItemStack itemStack) {
        return 1.0;
    }

    public default int getReactorMaxHeatIncrease(@Nonnull ItemStack itemStack) {
        return 0;
    }
}
