package org.gtreimagined.gt4r.reactor.components.adapters;


import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.InventoryDirection;
import org.gtreimagined.gt4r.reactor.item.interfaces.INeutronReflector;

public class NeutronReflectorAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final INeutronReflector reflector;

    public NeutronReflectorAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack,
        INeutronReflector reflector) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.reflector = reflector;
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
        if(!heatTick) {
            reactor.addEU(1.0);
            reflector.applyDamage(itemStack, source.getFuelRodCount());

            if (reflector.getRemainingHealth(itemStack) <= 0) {
                reactor.setItem(x, y, ItemStack.EMPTY);
            }
        }
        return true;
    }

    @Override
    public boolean reflectsNeutrons() {
        return reflector.canReflectNeutrons(itemStack);
    }
}
