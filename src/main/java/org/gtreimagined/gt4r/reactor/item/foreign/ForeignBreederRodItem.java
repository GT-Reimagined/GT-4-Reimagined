package org.gtreimagined.gt4r.reactor.item.foreign;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapterFactory;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.adapters.BreederRodAdapter;
import org.gtreimagined.gt4r.reactor.item.interfaces.IBreederRod;

public class ForeignBreederRodItem implements IBreederRod, IComponentAdapterFactory {

    @Nonnull
    private final Item item;

    private final int heatDivisor, heatMultiplier, maxNeutrons;

    @Nullable
    private ItemStack product;

    public ForeignBreederRodItem(@Nonnull Item item, int heatDivisor, int heatMultiplier, int maxNeutrons) {
        this.item = item;
        this.heatDivisor = heatDivisor;
        this.heatMultiplier = heatMultiplier;
        this.maxNeutrons = maxNeutrons;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == item;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
                                                 int y) {
        return new BreederRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }

    @Override
    public int getMaxNeutrons(@Nonnull ItemStack itemStack) {
        return maxNeutrons;
    }

    @Override
    public int getStoredNeutrons(@Nonnull ItemStack itemStack) {
        return itemStack.getDamageValue();
    }

    @Override
    public void setNeutrons(@Nonnull ItemStack itemStack, int neutrons) {
        itemStack.setDamageValue(neutrons);
    }

    @Override
    public int getReactorHeatDivisor(@Nonnull ItemStack itemStack) {
        return heatDivisor;
    }

    @Override
    public int getHeatMultiplier(@Nonnull ItemStack itemStack) {
        return heatMultiplier;
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }
}
