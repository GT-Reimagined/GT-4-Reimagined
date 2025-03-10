package org.gtreimagined.gt4r.reactor.item.foreign;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapterFactory;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.adapters.NeutronReflectorAdapter;
import org.gtreimagined.gt4r.reactor.item.interfaces.INeutronReflector;

public class ForeignNeutronReflectorItem implements INeutronReflector, IComponentAdapterFactory {

    @Nonnull
    private final Item item;

    private final Optional<Integer> maxHealth;

    @Nullable
    private ItemStack product;

    public ForeignNeutronReflectorItem(@Nonnull Item item) {
        this.item = item;
        this.maxHealth = Optional.empty();
    }

    public ForeignNeutronReflectorItem(Item item, int maxHealth) {
        this.item = item;
        this.maxHealth = Optional.of(maxHealth);
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
        return new NeutronReflectorAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (this.maxHealth.isPresent()) {
            return this.maxHealth.get() - itemStack.getDamageValue();
        } else {
            return 1;
        }
    }

    @Override
    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack) {
        return this.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        if (this.maxHealth.isPresent()) {
            itemStack.setDamageValue(itemStack.getDamageValue() + damage);
        }
    }

    @Override
    public @Nullable ItemStack getProduct() {
        return product;
    }
}
