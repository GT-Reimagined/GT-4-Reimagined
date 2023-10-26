package trinsdar.gt4r.data;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.capability.item.EmptyContainer;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.gui.slot.SlotFake;
import trinsdar.gt4r.gui.slots.SlotCoil;
import trinsdar.gt4r.gui.slots.SlotData;
import trinsdar.gt4r.gui.slots.SlotFluidDisplaySettable;
import trinsdar.gt4r.gui.slots.SlotQuantum;
import trinsdar.gt4r.items.ItemTurbineRotor;
import trinsdar.gt4r.blockentity.single.IFilterable;

public class SlotTypes {
    public static SlotType<SlotCoil> COIL = new SlotType<>("coil_bf", (type, gui, item, i, d) -> new SlotCoil(type,  gui,item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> true);
    public static SlotType<SlotData> DATA = new SlotType<>("data", (type, gui, item, i, d) -> new SlotData(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> i.getItem() == GTCoreItems.DataOrb || i.getItem() == GT4RData.StorageDataOrb);
    public static SlotType<SlotQuantum> QUANTUM = new SlotType<>("quantum", (type, gui, item, i, d) -> new SlotQuantum(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> true);
}
