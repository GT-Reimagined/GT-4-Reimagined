package trinsdar.gt4r.data;

import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.gui.slot.SlotInput;
import muramasa.antimatter.machine.event.ContentEvent;
import trinsdar.gt4r.gui.SlotCoil;
import trinsdar.gt4r.gui.SlotDisplay;
import trinsdar.gt4r.machine.IBFItemHandler;
import trinsdar.gt4r.machine.IFakeSlotHandler;
import trinsdar.gt4r.machine.IStorageSlotHandler;

import java.util.Optional;

public class SlotTypes {
    public static SlotType<SlotCoil> COIL = new SlotType<>("coil_bf", (type, t, i, d) -> new SlotCoil(type, t,t.itemHandler.map(m -> ((IBFItemHandler)m).getCoilHandler()).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<SlotDisplay> DISPLAY = new SlotType<>("display", (type, t, i, d) -> new SlotDisplay(type, t, t.itemHandler.map(m -> ((IFakeSlotHandler)m).getFakeHandler()).orElse(null), i, d.getX(), d.getY()), (t, i) -> false, ContentEvent.ITEM_INPUT_CHANGED, false, false);
    public static SlotType<AbstractSlot> STORAGE = new SlotType<>("storage", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> ((IStorageSlotHandler)m).getStorageHandler()).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);

}
