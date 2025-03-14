package org.gtreimagined.gt4r.reactor.components.adapters;

import java.util.ArrayList;
import java.util.List;

import muramasa.antimatter.util.int2;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt4r.reactor.Config;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.InventoryDirection;
import org.gtreimagined.gt4r.reactor.item.interfaces.IBasicFuelRod;

public class FuelRodAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IBasicFuelRod fuelRod;

    public FuelRodAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IBasicFuelRod fuelRod) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.fuelRod = fuelRod;
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

    protected double getEUMultiplier() {
        double mult = Config.ROD_EU_MULTIPLIER;

        if (fuelRod.getFuelType().isMox()) {
            mult *= 1 + fuelRod.getFuelType().moxEUCoefficient() * reactor.getHeatRatio();
        }

        return mult;
    }

    protected double getHeatMultiplier() {
        double mult = Config.ROD_HU_MULTIPLIER;

        if (fuelRod.getFuelType().isMox() && reactor.isFluid() && reactor.getHeatRatio() >= 0.5) {
            mult *= fuelRod.getFuelType().moxHeatCoefficient();
        }

        return mult;
    }

    @Override
    public void onHeatTick(boolean isActive) {
        if (!isActive) {
            return;
        }
        int rodCount = getFuelRodCount();
        int pulsesPerTick = fuelRod.getFuelType().pulsesPerTick();
        List<int2> pulseArea = fuelRod.getFuelType().pulseArea();
        for(int iteration = 0;iteration<rodCount;iteration++) {
            int pulses = (1 + (rodCount >> 1)) * fuelRod.getFuelType().pulsesPerTick();
            for(int pulse = 0;pulse<pulsesPerTick;pulse++) {
                for (int2 offset : pulseArea) {
                    pulses += pulseNeighbor(x + offset.x, y + offset.y, this, true);
                }
            }
            int heat = (int)(sumUp(pulses) * 4 * fuelRod.getFuelType().heatMult());
            var heatableNeighbours = this.getHeatableNeighbours();

            for (int i = 0; i < heatableNeighbours.size(); i++) {
                int remainingNeighbours = heatableNeighbours.size() - i;

                int heatToTransfer = heat / remainingNeighbours;
                heat -= heatToTransfer;

                int rejected = heatableNeighbours.get(i)
                        .addHeat(heatToTransfer);

                heat += rejected;
            }

            if (heat > 0) {
                reactor.addHullHeat(heat);
            }
        }
    }

    public static int sumUp(int base) {
        int sum = 0;
        for(int i = 1;i <= base;++i) {
            sum += i;
        }
        return sum;
    }

    @Override
    public void onEnergyTick(boolean isActive) {
        if (!isActive) {
            return;
        }

        int rodCount = getFuelRodCount();
        int pulsesPerTick = fuelRod.getFuelType().pulsesPerTick();
        List<int2> pulseArea = fuelRod.getFuelType().pulseArea();
        int pulses = (1 + (rodCount >> 1)) * fuelRod.getFuelType().pulsesPerTick();
        for(int iteration = 0;iteration<rodCount;iteration++) {
            for(int pulse = 0;pulse<pulses;pulse++) {
                acceptPulse(this, false);
            }
            for(int pulse = 0;pulse<pulsesPerTick;pulse++) {
                for(int i = 0;i<pulseArea.size();i++) {
                    int2 offset = pulseArea.get(i);
                    pulseNeighbor(x + offset.x, y + offset.y, this, false);
                }
            }
        }
        fuelRod.applyDamage(itemStack, 1);
        if (fuelRod.getRemainingHealth(itemStack) <= 0) {
            reactor.setItem(x, y, fuelRod.getProduct(itemStack).copy());
        }
    }

    @Override
    public boolean acceptPulse(IComponentAdapter source, boolean heatTick) {
        double energy = fuelRod.getFuelType().energyMult() * getEUMultiplier();
        if(!heatTick) reactor.addEU(energy);
        return true;
    }

    protected int pulseNeighbor(int targetX, int targetY, IComponentAdapter source, boolean heatTick) {
        if (targetX < 0 || targetY < 0 || targetX >= reactor.getWidth() || targetY >= reactor.getHeight()) {
            return 0;
        }
        IComponentAdapter component = reactor.getComponent(targetX, targetY);
        return component != null && component.acceptPulse(source, heatTick) ? fuelRod.getFuelType().connectivityPulses() : 0;
    }

    @Override
    public boolean reflectsNeutrons() {
        return fuelRod.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public int getFuelRodCount() {
        return this.fuelRod.getRodCount(itemStack);
    }

    @Override
    public double getExplosionRadiusMultiplier() {
       return this.fuelRod.getFuelType().explosionMult();
    }

    private int getPulseCount() {
        int pulses = (1 + (this.getFuelRodCount() >> 1)) * this.fuelRod.getFuelType().pulsesPerTick();

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.reflectsNeutrons()) {
                pulses++;
            }
        }

        return pulses;
    }

    private List<IComponentAdapter> getHeatableNeighbours() {
        List<int2> heatArea = fuelRod.getFuelType().heatPulseArea();

        List<IComponentAdapter> neighbours = new ArrayList<>();

        for (var offset : heatArea) {
            int x2 = x + offset.x;
            int y2 = y + offset.y;

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.containsHeat()) {
                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }
}
