package org.gtreimagined.gt4r;

import org.gtreimagined.gt4r.data.Machines;
import org.gtreimagined.gtlib.GTRemapping;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gtcore.GTCore;

import static org.gtreimagined.gt4r.data.Machines.FLUID_PRESS;
import static org.gtreimagined.gt4r.data.Machines.SOLID_FUEL_BOILER;

public class GT4RRemapping {
    public static void init(){
        GTRemapping.remapMachine("coal_boiler", SOLID_FUEL_BOILER);
        GTRemapping.remapMachine("fluid_extractor", FLUID_PRESS);
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "small_battery_hull"), new ResourceLocation(GT4RRef.ID, "battery_hull"));
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "re_battery"), new ResourceLocation(GT4RRef.ID, "re_battery"));
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "energy_crystal"), new ResourceLocation(GT4RRef.ID, "energy_crystal"));
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "small_lithium_battery"), new ResourceLocation(GT4RRef.ID, "lithium_battery"));
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "lapotron_crystal"), new ResourceLocation(GT4RRef.ID, "lapotron_crystal"));
        GTRemapping.remap(new ResourceLocation(GTCore.ID, "lapotronic_energy_orb"), new ResourceLocation(GT4RRef.ID, "lapotronic_energy_orb"));
        GTRemapping.remapCover(new ResourceLocation(GT4RRef.ID, "steam_vent"), new ResourceLocation(GTCore.ID, "steam_vent"));
        GTRemapping.remapMachine(new ResourceLocation(GT4RRef.ID, "pyrolysis_oven"), Machines.PYROLYSE_OVEN);
    }
}
