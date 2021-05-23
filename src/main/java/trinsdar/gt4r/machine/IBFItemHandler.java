package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;

import static muramasa.antimatter.machine.MachineFlag.ITEM;

public class IBFItemHandler extends MachineItemHandler<TileEntityIndustrialBlastFurnace> {
    public IBFItemHandler(TileEntityIndustrialBlastFurnace tile) {
        super(tile);
    }

    public IItemHandlerModifiable getCoilHandler() {
        return inventories.get(SlotTypes.COIL);
    }
}
