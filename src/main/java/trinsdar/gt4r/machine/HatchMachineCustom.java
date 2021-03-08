package trinsdar.gt4r.machine;

import muramasa.antimatter.machine.types.HatchMachine;

import static muramasa.antimatter.Data.HATCH_MENU_HANDLER;
import static trinsdar.gt4r.data.Guis.HATCH_MENU_HANDLER_CUSTOM;

public class HatchMachineCustom extends HatchMachine {
    public HatchMachineCustom(String domain, String id, Object... data) {
        super(domain, id, data);
        setGUI(HATCH_MENU_HANDLER_CUSTOM);
    }
}
