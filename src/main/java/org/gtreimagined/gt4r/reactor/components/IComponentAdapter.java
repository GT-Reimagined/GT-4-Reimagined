package org.gtreimagined.gt4r.reactor.components;

import net.minecraft.world.item.ItemStack;

public interface IComponentAdapter {

    public int getX();

    public int getY();

    public ItemStack getItemStack();

    public default boolean containsHeat() {
        return false;
    }

    public default int getStoredHeat() {
        return 0;
    }

    /**
     * @return The amount of heat rejected
     */
    public default int addHeat(int delta) {
        return delta;
    }

    public default void onHeatTick() {

    }

    public default void onEnergyTick() {

    }

    public default boolean reflectsNeutrons() {
        return false;
    }

    public default int getFuelRodCount() {
        return 0;
    }

    public default int getReactorMaxHeatIncrease() {
        return 0;
    }

    public default double getExplosionRadiusMultiplier() {
        return 1.0;
    }

    /*public default void onSimulationStarted(SimulationResult result, int componentIndex) {

    }

    public default void setSimulationComponent(SimulationResult result, int componentIndex) {

    }

    public default void onSimulationFinished(SimulationResult result, int componentIndex) {

    }*/
}
