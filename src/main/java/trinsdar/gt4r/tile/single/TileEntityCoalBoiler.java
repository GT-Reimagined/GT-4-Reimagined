package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.machine.CoalBoilerFluidHandler;
import trinsdar.gt4r.machine.CoalBoilerRecipeHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class TileEntityCoalBoiler extends TileEntityMachine<TileEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;
    public TileEntityCoalBoiler(Machine<?> type) {
        super(type);
        this.fluidHandler.set(() -> new CoalBoilerFluidHandler(this));
        this.recipeHandler.set(() -> new CoalBoilerRecipeHandler(this));
    }

    public int getFuel() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getFuel());
        });
        return v.get();
    }

    public int getHeat() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getHeat());
        });
        return v.get();
    }

    public int getMaxFuel() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getMaxFuel());
        });
        return v.get();
    }

    public int getMaxHeat() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getMaxHeat());
        });
        return v.get();
    }
}
