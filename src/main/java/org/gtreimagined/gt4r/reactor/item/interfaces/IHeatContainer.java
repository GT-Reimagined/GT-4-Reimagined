package org.gtreimagined.gt4r.reactor.item.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.item.HeatUtils;

public interface IHeatContainer {

    public default int getStoredHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getDamageValue();
    }

    public default int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (!itemStack.isDamageableItem()) {
            return 1;
        }

        return itemStack.getMaxDamage() - itemStack.getDamageValue();
    }

    public default int addHeat(@Nonnull ItemStack itemStack, int heat) {
        if (!itemStack.isDamageableItem()) {
            return heat;
        }

        int stored = itemStack.getDamageValue();

        int consumed = HeatUtils.getConsumableHeat(itemStack.getMaxDamage(), stored, heat);

        itemStack.setDamageValue(stored + consumed);

        return heat - consumed;
    }

    public default int getMaxHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getMaxDamage();
    }

    public boolean isConsumable(@Nonnull ItemStack itemStack);

    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack);
}
