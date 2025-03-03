package org.gtreimagined.gt4r;

import muramasa.antimatter.AntimatterRemapping;

import static org.gtreimagined.gt4r.data.Machines.FLUID_PRESS;
import static org.gtreimagined.gt4r.data.Machines.SOLID_FUEL_BOILER;

public class GT4RRemapping {
    public static void init(){
        AntimatterRemapping.remapMachine("coal_boiler", SOLID_FUEL_BOILER);
        AntimatterRemapping.remapMachine("fluid_extractor", FLUID_PRESS);
    }
}
