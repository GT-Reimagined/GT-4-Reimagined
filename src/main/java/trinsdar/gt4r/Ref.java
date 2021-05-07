package trinsdar.gt4r;

import muramasa.antimatter.datagen.resources.DynamicDataPackFinder;
import trinsdar.gt4r.datagen.GT4RDataPackFinder;

public class Ref {

    /** Mod Data **/
    public static final String ID = "gt4r";
    public static final String NAME = "GT 4 Reimagined";
    public static final String VERSION = "0.0.1";
    public static final String ANTIMATTER = muramasa.antimatter.Ref.ID;
    public static final GT4RDataPackFinder SERVER_PACK_FINDER = new GT4RDataPackFinder();

    /** Mod ids **/
    public static final String MOD_IE = "immersiveengineering";
    public static final String MOD_CREATE = "create";

    /** Config Values **/
    public static boolean mixedOreYieldsTwoThirdsPureOre = false; //TODO 5U remnant, determine if needed
    public static boolean sulfurTorch = true;  //TODO: move to config, this is a placeholder testing thingy atm
}
