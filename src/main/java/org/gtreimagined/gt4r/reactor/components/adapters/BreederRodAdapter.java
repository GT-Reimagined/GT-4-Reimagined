package org.gtreimagined.gt4r.reactor.components.adapters;

import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.InventoryDirection;
import org.gtreimagined.gt4r.reactor.item.interfaces.IBreederRod;


public class BreederRodAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IBreederRod breederRod;

    public BreederRodAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IBreederRod breederRod) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.breederRod = breederRod;
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
    public boolean acceptPulse(IComponentAdapter source, boolean heatTick) {
        if (heatTick) {
            int heatMultiplier = 1 + reactor.getHullHeat() / breederRod.getFuelType().breedingHeat();

            int storedNeutrons = breederRod.getStoredNeutrons(itemStack);

            storedNeutrons += heatMultiplier;

            int max = breederRod.getMaxNeutrons(itemStack);

            storedNeutrons = Math.min(storedNeutrons, max);

            breederRod.setNeutrons(itemStack, storedNeutrons);

            if (storedNeutrons >= max) {
                reactor.setItem(x, y, breederRod.getProduct(itemStack).copy());
            }
        }
        return true;
    }

}
