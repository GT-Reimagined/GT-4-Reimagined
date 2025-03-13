package org.gtreimagined.gt4r.reactor.components;

import net.minecraft.world.item.ItemStack;

public interface IComponentAdapter {

    int getX();

    int getY();

    ItemStack getItemStack();

    default boolean containsHeat() {
        return false;
    }

    default int getStoredHeat() {
        return 0;
    }

    /**
     * @return The amount of heat rejected
     */
    default int addHeat(int delta) {
        return delta;
    }

    default void onHeatTick(boolean isActive) {

    }

    default void onEnergyTick(boolean isActive) {

    }

    default boolean reflectsNeutrons() {
        return false;
    }

    default int getFuelRodCount() {
        return 0;
    }

    default int getReactorMaxHeatIncrease() {
        return 0;
    }

    default double getExplosionRadiusMultiplier() {
        return 1.0;
    }

    /*public default void onSimulationStarted(SimulationResult result, int componentIndex) {

    }

    public default void setSimulationComponent(SimulationResult result, int componentIndex) {

    }

    public default void onSimulationFinished(SimulationResult result, int componentIndex) {

    }*/
}
