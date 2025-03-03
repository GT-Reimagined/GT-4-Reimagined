package org.gtreimagined.gt4r.machine;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.util.TagUtils;
import org.gtreimagined.gt4r.data.CustomTags;

import static muramasa.antimatter.machine.MachineFlag.GUI;

public class UpgradeableFluidHandler<T extends BlockEntityMachine<T> & IUpgradeProvider> extends MachineFluidHandler<T> {
    public UpgradeableFluidHandler(T tile, int capacity) {
        this(tile, capacity, tile.has(GUI) ? tile.getMachineType().getSlots(SlotType.FL_IN, tile.getMachineTier()).size() : 0,
                tile.has(GUI) ? tile.getMachineType().getSlots(SlotType.FL_OUT, tile.getMachineTier()).size() : 0);
    }

    public UpgradeableFluidHandler(T tile, int capacity, int inputCount, int outputCount) {
        super(tile, capacity, inputCount + 1, outputCount);
        tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
            for (int i = 0; i < inputCount + 1; i++) {
                if (i == inputCount){
                    b.tank(f -> f.getFluid().is(TagUtils.getForgelikeFluidTag("steam")) && tile.getUpgrades().containsKey(CustomTags.STEAM_UPGRADES), capacity);
                } else {
                    b.tank(f -> !f.getFluid().is(TagUtils.getForgelikeFluidTag("steam")) || !tile.getUpgrades().containsKey(CustomTags.STEAM_UPGRADES), capacity);
                }
            }
            return b;
        }));
    }

    public UpgradeableFluidHandler(T tile) {
        this(tile, 32000);
    }
}
