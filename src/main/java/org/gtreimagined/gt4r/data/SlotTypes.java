package org.gtreimagined.gt4r.data;

import org.gtreimagined.gtlib.gui.SlotType;
import net.minecraftforge.items.wrapper.EmptyHandler;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.gui.slots.SlotCoil;
import org.gtreimagined.gt4r.gui.slots.SlotData;
import org.gtreimagined.gt4r.gui.slots.SlotQuantum;

public class SlotTypes {
    public static SlotType<SlotCoil> COIL = new SlotType<>("coil_bf", (type, gui, item, i, d) -> new SlotCoil(type,  gui,item.getOrDefault(type, new EmptyHandler()), i, d.getX(), d.getY()), (t, i) -> true, false, false);
    public static SlotType<SlotData> DATA = new SlotType<>("data", (type, gui, item, i, d) -> new SlotData(type, gui, item.getOrDefault(type, new EmptyHandler()), i, d.getX(), d.getY()), (t, i) -> i.getItem() == GTCoreItems.DataOrb || i.getItem() == GT4RItems.StorageDataOrb);
    public static SlotType<SlotQuantum> QUANTUM = new SlotType<>("quantum", (type, gui, item, i, d) -> new SlotQuantum(type, gui, item.getOrDefault(type, new EmptyHandler()), i, d.getX(), d.getY()), (t, i) -> true);
}
