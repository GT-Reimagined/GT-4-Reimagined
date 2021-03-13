package trinsdar.gt4r.data;

import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import net.minecraftforge.items.IItemHandler;
import trinsdar.gt4r.gui.SlotCoil;
import trinsdar.gt4r.machine.IBFItemHandler;

import java.util.Optional;

public class SlotTypes {
    public static SlotType COIL = new SlotType("coil_bf", (t, i, d) -> Optional.of(new SlotCoil(t, t.itemHandler.map(MachineItemHandler::getInputHandler).orElse(null), i, d.getX(), d.getY())));
}
