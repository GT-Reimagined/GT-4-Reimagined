package org.gtreimagined.gt4r.reactor.components;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class ComponentRegistry {

    private static final HashMap<Item, IComponentAdapterFactory> adapterFactories = new HashMap<>();

    public static void registerAdapter(Item item, IComponentAdapterFactory adapterFactory) {
        adapterFactories.put(item, adapterFactory);
    }

    public static boolean isReactorItem(@Nonnull ItemStack itemStack) {
        IComponentAdapterFactory factory = adapterFactories.get(itemStack.getItem());

        return factory != null && factory.canAdaptItem(itemStack);
    }

    public static @Nullable IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
                                                         int x, int y) {
        IComponentAdapterFactory factory = adapterFactories.get(itemStack.getItem());

        if (factory != null && factory.canAdaptItem(itemStack)) {
            return factory.getAdapter(itemStack, reactor, x, y);
        } else {
            return null;
        }
    }
}
