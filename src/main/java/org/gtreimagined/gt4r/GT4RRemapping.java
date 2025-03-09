package org.gtreimagined.gt4r;

import muramasa.antimatter.AntimatterRemapping;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gtcore.GTCore;

import static org.gtreimagined.gt4r.data.Machines.FLUID_PRESS;
import static org.gtreimagined.gt4r.data.Machines.SOLID_FUEL_BOILER;

public class GT4RRemapping {
    public static void init(){
        AntimatterRemapping.remapMachine("coal_boiler", SOLID_FUEL_BOILER);
        AntimatterRemapping.remapMachine("fluid_extractor", FLUID_PRESS);
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "small_battery_hull"), new ResourceLocation(GT4RRef.ID, "battery_hull"));
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "re_battery"), new ResourceLocation(GT4RRef.ID, "re_battery"));
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "energy_crystal"), new ResourceLocation(GT4RRef.ID, "energy_crystal"));
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "small_lithium_battery"), new ResourceLocation(GT4RRef.ID, "lithium_battery"));
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "lapotron_crystal"), new ResourceLocation(GT4RRef.ID, "lapotron_crystal"));
        AntimatterRemapping.remap(new ResourceLocation(GTCore.ID, "lapotronic_energy_orb"), new ResourceLocation(GT4RRef.ID, "lapotronic_energy_orb"));
    }
}
