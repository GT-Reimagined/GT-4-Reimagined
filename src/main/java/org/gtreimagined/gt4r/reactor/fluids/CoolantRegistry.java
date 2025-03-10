package org.gtreimagined.gt4r.reactor.fluids;

import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Objects;


public class CoolantRegistry {

    private static final HashMap<Fluid, Coolant> coolantsByColdFluid = new HashMap<>();
    private static final HashMap<Fluid, Coolant> coolantsByHotFluid = new HashMap<>();
    private static final HashMap<Fluid, Coolant> coolantsByFluid = new HashMap<>();

    private CoolantRegistry() {

    }

    /**
     * Registers a coolant that can be used to cool a nuclear reactor
     * 
     * @param cold                 The cold input coolant
     * @param hot                  The heated output coolant
     * @param specificHeatCapacity The amount of HU that can be stored in one mB of coolant
     */
    public static void registerCoolant(Fluid cold, Fluid hot, int specificHeatCapacity) {
        Objects.requireNonNull(cold);
        Objects.requireNonNull(hot);
        if (specificHeatCapacity <= 0) throw new IllegalArgumentException("specificHeatCapacity");

        Coolant coolant = new Coolant(cold, hot, specificHeatCapacity);

        coolantsByColdFluid.put(cold, coolant);
        coolantsByHotFluid.put(hot, coolant);
        coolantsByFluid.put(cold, coolant);
        coolantsByFluid.put(hot, coolant);
    }

    public static boolean isColdCoolant(Fluid fluid) {
        return coolantsByColdFluid.containsKey(fluid);
    }

    public static boolean isHotCoolant(Fluid fluid) {
        return coolantsByHotFluid.containsKey(fluid);
    }

    public static Coolant getCoolantInfo(Fluid fluid) {
        return coolantsByFluid.get(fluid);
    }

    public static class Coolant {

        public final Fluid cold, hot;
        public final int specificHeatCapacity;

        public Coolant(Fluid cold, Fluid hot, int specificHeatCapacity) {
            this.cold = cold;
            this.hot = hot;
            this.specificHeatCapacity = specificHeatCapacity;
        }
    }
}
