package trinsdar.gt4r.data;

import muramasa.antimatter.machine.Tier;
import net.minecraft.util.text.TextFormatting;

public class Tiers {
    /** Electricity Tiers **/
    public static Tier ULV = new Tier("gt4r", "ulv", 8, TextFormatting.WHITE); //Tier 1
    public static Tier LV = new Tier("gt4r", "lv", 32, TextFormatting.WHITE); //Tier 2
    public static Tier MV = new Tier("gt4r", "mv", 128, TextFormatting.WHITE); //Tier 3
    public static Tier HV = new Tier("gt4r", "hv", 512, TextFormatting.YELLOW); //Tier 4
    public static Tier EV = new Tier("gt4r", "ev", 2048, TextFormatting.AQUA); //Tier 5
    public static Tier IV = new Tier("gt4r", "iv", 8192, TextFormatting.LIGHT_PURPLE); //Tier 6
    public static Tier LUV = new Tier("gt4r", "luv", 32768, TextFormatting.LIGHT_PURPLE); //Tier 7
    public static Tier ZPM = new Tier("gt4r", "zpm", 131072, TextFormatting.LIGHT_PURPLE); //Tier 8
    public static Tier UV = new Tier("gt4r", "uv", 524288, TextFormatting.LIGHT_PURPLE); //Tier 9
    public static Tier MAX = new Tier("gt4r", "max", 2147483647, TextFormatting.LIGHT_PURPLE); //Tier 15

    /** Special Tiers **/
    //TODO make these Tier 0 and 1?
    public static Tier BRONZE = new Tier("gt4r", "bronze", 0, TextFormatting.WHITE);
}
