package org.gtreimagined.gt4r.reactor.item.foreign;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapterFactory;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.adapters.HeatAbsorberAdapter;
import org.gtreimagined.gt4r.reactor.item.HeatUtils;
import org.gtreimagined.gt4r.reactor.item.interfaces.IHeatContainer;

public class ForeignHeatAbsorberItem implements IHeatContainer, IComponentAdapterFactory {

    @Nonnull
    protected final Item item;
    protected final int maxHeat;
    protected final boolean consumable;

    @Nullable
    protected ItemStack product;

    public ForeignHeatAbsorberItem(@Nonnull Item item, int maxHeat, boolean consumable) {
        this.item = item;
        this.maxHeat = maxHeat;
        this.consumable = consumable;
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
        return new HeatAbsorberAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return this.maxHeat - itemStack.getDamageValue();
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        int consumed = HeatUtils.getConsumableHeat(this.maxHeat, itemStack.getDamageValue(), heat);

        itemStack.setDamageValue(itemStack.getDamageValue() + consumed);

        return heat - consumed;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return this.maxHeat;
    }

    @Override
    public boolean isConsumable(@Nonnull ItemStack itemStack) {
        return consumable;
    }

    @Override
    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }
}
