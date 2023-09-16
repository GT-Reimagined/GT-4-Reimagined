package trinsdar.gt4r.data;

import muramasa.antimatter.capability.item.EmptyContainer;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.gui.slot.SlotFake;
import muramasa.antimatter.machine.event.ContentEvent;
import trinsdar.gt4r.gui.slots.SlotCoil;
import trinsdar.gt4r.gui.slots.SlotData;
import trinsdar.gt4r.gui.slots.SlotFluidDisplaySettable;
import trinsdar.gt4r.gui.slots.SlotQuantum;
import trinsdar.gt4r.items.ItemTurbineRotor;
import trinsdar.gt4r.blockentity.single.IFilterable;

public class SlotTypes {
    public static SlotType<SlotCoil> COIL = new SlotType<>("coil_bf", (type, gui, item, i, d) -> new SlotCoil(type,  gui,item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot<?>> FILTERABLE = new SlotType<>("filterable", (type, gui, inv, i, d) -> new AbstractSlot<>(type, gui, inv.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> {
        if (t instanceof IFilterable) {
            return ((IFilterable)t).accepts(i);
        }
        return true;
    },ContentEvent.ITEM_INPUT_CHANGED, true, true);
    public static SlotType<SlotFake> FLUID_DISPLAY_SETTABLE = new SlotType<>("fluid_display_settable", (type, gui, item, i, d) -> new SlotFluidDisplaySettable(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> false, ContentEvent.ITEM_INPUT_CHANGED, true, false);
    public static SlotType<AbstractSlot<?>> ROTOR = new SlotType<>("rotor", ((type, gui, item, i, d) -> new AbstractSlot<>(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY())), (t, i) -> i.getItem() instanceof ItemTurbineRotor, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot<?>> FILTER = new SlotType<>("filter", (type, gui, item, i, d) -> new AbstractSlot<>(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> i.getItem() == GT4RData.LavaFilter, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<SlotData> DATA = new SlotType<>("data", (type, gui, item, i, d) -> new SlotData(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> i.getItem() == GT4RData.CircuitDataOrb || i.getItem() == GT4RData.StorageDataOrb, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<SlotQuantum> QUANTUM = new SlotType<>("quantum", (type, gui, item, i, d) -> new SlotQuantum(type, gui, item.getOrDefault(type, new EmptyContainer()), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
}
