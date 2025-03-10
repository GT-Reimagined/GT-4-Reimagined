package org.gtreimagined.gt4r.reactor.components.adapters;


import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.item.interfaces.IReactorPlating;

public class ReactorPlatingAdapter implements IComponentAdapter {

    @SuppressWarnings("unused")
    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IReactorPlating plating;

    public ReactorPlatingAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IReactorPlating plating) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.plating = plating;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public double getExplosionRadiusMultiplier() {
        return plating.getExplosionRadiusMultiplier(itemStack);
    }

    @Override
    public int getReactorMaxHeatIncrease() {
        return plating.getReactorMaxHeatIncrease(itemStack);
    }
}
