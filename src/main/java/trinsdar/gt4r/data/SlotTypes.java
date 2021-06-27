package trinsdar.gt4r.data;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.tool.IAntimatterTool;
import tesseract.api.capability.TesseractGTCapability;
import trinsdar.gt4r.gui.slots.SlotCoil;
import trinsdar.gt4r.gui.slots.SlotCrafting;
import trinsdar.gt4r.gui.slots.SlotDisplay;
import trinsdar.gt4r.items.ItemTurbineRotor;

public class SlotTypes {
    public static SlotType<SlotCoil> COIL = new SlotType<>("coil_bf", (type, t, i, d) -> new SlotCoil(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<SlotDisplay> DISPLAY = new SlotType<>("display", (type, t, i, d) -> new SlotDisplay(type, t, t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> false, ContentEvent.ITEM_INPUT_CHANGED, false, false);
    public static SlotType<AbstractSlot> STORAGE = new SlotType<>("storage", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot> TOOLS = new SlotType<>("tools", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> i.getItem() instanceof IAntimatterTool || i.getItem().isDamageable(), ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot> TOOL_CHARGE = new SlotType<>("tool_charge", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> t.energyHandler.map(eh -> i.getCapability(TesseractGTCapability.ENERGY_HANDLER_CAPABILITY).map(inner -> ((inner.getInputVoltage() | inner.getOutputVoltage()) <= (eh.getInputVoltage() | eh.getOutputVoltage()) )).orElse(false)).orElse(false) || i.getItem() instanceof IAntimatterTool || i.getItem().isDamageable(), ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<SlotCrafting> CRAFTING = new SlotType<>("crafting", (type, t, i, d) -> new SlotCrafting(t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot> PARK = new SlotType<>("park", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> true, ContentEvent.ITEM_INPUT_CHANGED);
    public static SlotType<AbstractSlot> ROTOR = new SlotType<>("rotor", (type, t, i, d) -> new AbstractSlot(type, t,t.itemHandler.map(m -> m.getHandler(type)).orElse(null), i, d.getX(), d.getY()), (t, i) -> i.getItem() instanceof ItemTurbineRotor, ContentEvent.ITEM_INPUT_CHANGED);
}
