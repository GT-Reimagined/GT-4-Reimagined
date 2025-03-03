package org.gtreimagined.gt4r.machine;


import muramasa.antimatter.capability.machine.MachineItemHandler;
import org.gtreimagined.gtcore.data.SlotTypes;
import org.gtreimagined.gtcore.machine.InfiniteSlotTrackedHandler;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityQuantumChest;

public class QuantumChestItemHandler extends MachineItemHandler<BlockEntityQuantumChest> {
    int maxSize = Integer.MAX_VALUE;
    int digitalCount;
    public QuantumChestItemHandler(BlockEntityQuantumChest tile) {
        super(tile);
        int count = tile.getMachineType().getCount(tile.getMachineTier(), SlotTypes.UNLIMITED);
        inventories.put(SlotTypes.UNLIMITED, new InfiniteSlotTrackedHandler<>(tile, SlotTypes.UNLIMITED, count, SlotTypes.UNLIMITED.output, SlotTypes.UNLIMITED.input, SlotTypes.UNLIMITED.tester, Integer.MAX_VALUE));
    }
}
