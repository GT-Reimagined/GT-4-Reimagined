package org.gtreimagined.gt4r.reactor.tile;

import org.gtreimagined.gt4r.reactor.components.IReactorGrid;

import javax.annotation.Nullable;


public interface IReactorBlock {

    public @Nullable TileReactorCore getReactor();

    public void setReactor(TileReactorCore reactor);

    public default void onHeatTick(IReactorGrid reactor) {

    }

    public default void onEnergyTick(IReactorGrid reactor) {

    }

    public default ReactorEnableState getEnableState() {
        return ReactorEnableState.Idle;
    }

    public static enum ReactorEnableState {
        Active,
        Idle,
        Inhibiting,
    }
}
