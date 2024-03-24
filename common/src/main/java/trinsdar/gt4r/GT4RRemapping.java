package trinsdar.gt4r;

import muramasa.antimatter.AntimatterRemapping;

import static trinsdar.gt4r.data.Machines.SOLID_FUEL_BOILER;

public class GT4RRemapping {
    public static void init(){
        AntimatterRemapping.remapMachine("coal_boiler", SOLID_FUEL_BOILER);
    }
}
