package org.gtreimagined.gt4r.reactor.item.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;

public interface IHeatMover extends IHeatContainer {

    public int getTransferFromReactor(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor);

    public int getTransferFromNeighbour(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour);

    public int getTransferNeighbourToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour);

    public int getTransferToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor);

    @Override
    public default boolean isConsumable(@Nonnull ItemStack itemStack) {
        return true;
    }

    @Nullable
    public default ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return null;
    }
}
