package org.gtreimagined.gt4r.reactor.item.foreign;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.FuelType;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapterFactory;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.adapters.FuelRodAdapter;
import org.gtreimagined.gt4r.reactor.item.interfaces.IBasicFuelRod;

public class ForeignFuelRodItem implements IBasicFuelRod, IComponentAdapterFactory {

    @Nonnull
    protected final Item item;
    protected final FuelType fuelType;
    protected final int rodCount;
    protected final int maxHealth;

    @Nullable
    protected ItemStack product;

    public ForeignFuelRodItem(@Nonnull Item item, FuelType fuelType, int rodCount,
                              int maxHealth) {
        this.item = item;
        this.fuelType = fuelType;
        this.rodCount = rodCount;
        this.maxHealth = maxHealth;
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == item;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
                                                 int y) {
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public int getRodCount(@Nonnull ItemStack itemStack) {
        return rodCount;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return maxHealth - itemStack.getDamageValue();
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        itemStack.setDamageValue(itemStack.getDamageValue() + damage);
    }

    @Override
    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }
}
