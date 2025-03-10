package org.gtreimagined.gt4r.reactor.components.adapters;

import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.InventoryDirection;
import org.gtreimagined.gt4r.reactor.item.interfaces.IHeatMover;

public class HeatMoverAdapter implements IComponentAdapter {

    protected final IReactorGrid reactor;
    protected final int x, y;
    protected final ItemStack itemStack;
    protected final IHeatMover heatMover;

    public HeatMoverAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IHeatMover heatMover) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.heatMover = heatMover;
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
    public boolean containsHeat() {
        return this.heatMover.getMaxHeat(itemStack) > 0;
    }

    @Override
    public int getStoredHeat() {
        return this.heatMover.getStoredHeat(itemStack);
    }

    @Override
    public int addHeat(int delta) {
        int rejected = this.heatMover.addHeat(itemStack, delta);

        if (this.heatMover.getRemainingHealth(itemStack) <= 0) {
            this.reactor.setItem(this.x, this.y, null);
        }

        return rejected;
    }

    protected int getTransferFromReactor() {
        return this.heatMover.getTransferFromReactor(itemStack, reactor);
    }

    protected int getTransferToAir() {
        return this.heatMover.getTransferToAir(itemStack, reactor);
    }

    protected int getTransferNeighbourToAir(IComponentAdapter neighbour) {
        return this.heatMover.getTransferNeighbourToAir(itemStack, reactor, neighbour);
    }

    protected int getTransferFromNeighbour(IComponentAdapter neighbour) {
        return this.heatMover.getTransferFromNeighbour(itemStack, reactor, neighbour);
    }

    @Override
    public void onHeatTick() {
        int fromReactor = getTransferFromReactor();

        if (fromReactor != 0) {
            this.reactor.addHullHeat(-fromReactor);
            this.addHeat(fromReactor);
        }

        int toAir = getTransferToAir();

        if (toAir != 0) {
            this.addHeat(-toAir);
            int rejected = this.reactor.addAirHeat(toAir);

            if (rejected != 0) {
                this.addHeat(rejected);
            }
        }

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.containsHeat()) {
                int fromNeighbourToAir = getTransferNeighbourToAir(neighbour);

                if (fromNeighbourToAir != 0) {
                    neighbour.addHeat(-fromNeighbourToAir);
                    int rejected = this.reactor.addAirHeat(fromNeighbourToAir);

                    if (rejected != 0) {
                        neighbour.addHeat(rejected);
                    }
                }

                int fromNeighbour = getTransferFromNeighbour(neighbour);

                if (fromNeighbour != 0) {
                    neighbour.addHeat(-fromNeighbour);
                    this.addHeat(fromNeighbour);
                }
            }
        }
    }
}
