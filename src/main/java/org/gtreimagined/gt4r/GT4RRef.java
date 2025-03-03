package org.gtreimagined.gt4r;


import muramasa.antimatter.Ref;
import net.minecraft.resources.ResourceLocation;

public class GT4RRef {

    /** Mod Data **/
    public static final String ID = "gt4r";
    public static final String NAME = "GT 4 Reimagined";
    public static final String VERSION = "0.0.1";
    public static final String ANTIMATTER = Ref.ID;
    public static final String ANTIMATTER_SHARED = Ref.SHARED_ID;

    public static final ResourceLocation SYNC_ID = new ResourceLocation(GT4RRef.ID, "crafting_sync");

    /** Mod ids **/
    public static final String MOD_IE = "immersiveengineering";
    public static final String MOD_CREATE = "create";
    public static final String MOD_TFC = "tfc";
    public static final String MOD_BLUEPOWER = "bluepower";
    public static final String MOD_TERRESTRIA = "terrestria";
    public static final String MOD_CINDERSCAPES = "cinderscapes";

    /** Config Values **/
    public static boolean mixedOreYieldsTwoThirdsPureOre = false; //TODO 5U remnant, determine if needed
    public static boolean sulfurTorch = true;  //TODO: move to config, this is a placeholder testing thingy atm
}
