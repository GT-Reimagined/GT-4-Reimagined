package trinsdar.gt4r.loader.machines.generator;

import static trinsdar.gt4r.data.Materials.Steam;
import static trinsdar.gt4r.data.RecipeMaps.STEAM_FUELS;

public class SteamFuels {
    public static void init() {
        STEAM_FUELS.RB().fi(Steam.getGas(2)).add(1,1);
    }
}
