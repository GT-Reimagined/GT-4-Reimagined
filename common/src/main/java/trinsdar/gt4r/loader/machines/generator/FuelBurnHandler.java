package trinsdar.gt4r.loader.machines.generator;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import trinsdar.gt4r.data.Materials;

import static muramasa.antimatter.Data.GAS;
import static muramasa.antimatter.Data.LIQUID;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.NitroDiesel;
import static trinsdar.gt4r.data.GT4RMaterialTags.SEMIFLUID;
import static trinsdar.gt4r.data.Materials.Steam;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class FuelBurnHandler {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (mat != Materials.Steam && MaterialTags.FUEL_POWER.getInt(mat) > 0) {
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
                        SEMIFLUID_FUELS.RB().fi(mat.getLiquid(1)).add(ticks, power);
                    } else {
                        DIESEL_FUELS.RB().fi(mat.getLiquid(1)).add(ticks, power);
                    }

                }
                if (mat.has(GAS)) {
                    int power = 16, ticks = MaterialTags.FUEL_POWER.getInt(mat) / 16;
                    if (MaterialTags.FUEL_POWER.getInt(mat) < 16 || (MaterialTags.FUEL_POWER.getInt(mat) < 32 && MaterialTags.FUEL_POWER.getInt(mat) > 16)) {
                        ticks = 1;
                        power = MaterialTags.FUEL_POWER.getInt(mat);
                    }
                    GAS_FUELS.RB().fi(mat.getGas(1)).add(ticks, power);
                    //LARGE_GAS_FUELS.RB().fi(mat.getGas(20)).add(1, MaterialTags.FUEL_POWER.getInt(mat) * 20L);
                }
            }
        });
        STEAM_FUELS.RB().fi(Steam.getGas(32)).add(1,16);
        LARGE_STEAM_FUELS.RB().fi(Steam.getGas(1600)).fo(DistilledWater.getLiquid(10)).add(1, 800);

    }
}
