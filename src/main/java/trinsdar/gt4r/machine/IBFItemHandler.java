package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;

import static muramasa.antimatter.machine.MachineFlag.ITEM;

public class IBFItemHandler extends MultiMachineItemHandler {
    public IBFItemHandler(TileEntityIndustrialBlastFurnace tile) {
        super(tile);
        inventories.put(ITEM, new TrackedItemHandler<>(tile, tile.getMachineType().getGui().getSlots(SlotTypes.COIL, tile.getMachineTier()).size(), ContentEvent.ITEM_INPUT_CHANGED));
    }

    public IItemHandlerModifiable getCoilHandler() {
        return inventories.get(ITEM);
    }
}
