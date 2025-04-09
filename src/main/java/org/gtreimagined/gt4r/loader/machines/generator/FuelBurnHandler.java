package org.gtreimagined.gt4r.loader.machines.generator;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.GAS;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.LIQUID;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Lava;
import static org.gtreimagined.gt4r.data.GT4RMaterialTags.SEMIFLUID;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.*;

public class FuelBurnHandler {
    public static void init() {
        GTAPI.all(Material.class, mat -> {
            if (mat != Steam && mat.has(MaterialTags.FUEL_POWER) && MaterialTags.FUEL_POWER.getInt(mat) > 0) {
                if (mat.has(LIQUID)) {
                    int power = 16, ticks = MaterialTags.FUEL_POWER.getInt(mat) / 16;
                    if (MaterialTags.FUEL_POWER.getInt(mat) < 16) {
                        ticks = 1;
                        power = MaterialTags.FUEL_POWER.getInt(mat);
                    }
                    if (mat == NitroDiesel){
                        ticks = MaterialTags.FUEL_POWER.getInt(mat) / 32;
                        power = 32;
                    }
                    if (mat.has(SEMIFLUID)){
                        SEMIFLUID_FUELS.RB().fi(mat.getLiquid(1)).add(mat.getId(), ticks, power);
                    } else {
                        DIESEL_FUELS.RB().fi(mat.getLiquid(1)).add(mat.getId(), ticks, power);
                    }

                }
                if (mat.has(GAS)) {
                    int power = 16, ticks = MaterialTags.FUEL_POWER.getInt(mat) / 16;
                    if (MaterialTags.FUEL_POWER.getInt(mat) < 16 || (MaterialTags.FUEL_POWER.getInt(mat) < 32 && MaterialTags.FUEL_POWER.getInt(mat) > 16)) {
                        ticks = 1;
                        power = MaterialTags.FUEL_POWER.getInt(mat);
                    }
                    GAS_FUELS.RB().fi(mat.getGas(1)).add(mat.getId(), ticks, power);
                    //LARGE_GAS_FUELS.RB().fi(mat.getGas(20)).add(1, MaterialTags.FUEL_POWER.getInt(mat) * 20L);
                }
            }
        });
        STEAM_FUELS.RB().fi(Steam.getGas(32)).add("steam",1,16);
        LARGE_STEAM_FUELS.RB().fi(Steam.getGas(1600)).fo(DistilledWater.getLiquid(10)).add("steam",1, 800);
        THERMAL_FUELS.RB().fi(Lava.getLiquid(1)).add("lava", 1, 30);
    }
}
