package trinsdar.gt4r.data;

import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;



public class CustomTags {
    public static ITag.INamedTag<Item> PLATES_STEELS = getTag("plates/steels");
    public static ITag.INamedTag<Item> PLATES_INVAR_ALUMINIUM = getTag("plates/invaraluminium");
    public static ITag.INamedTag<Item> PLATES_IRON_ALUMINIUM = getTag("plates/ironaluminium");
    public static ITag.INamedTag<Item> PLATES_WROUGHT_ALUMINIUM = getTag("plates/wroughtaluminium");
    public static ITag.INamedTag<Item> PLATES_TITAN_TUNGSTEEL = getTag("plates/titantungsteel");
    public static ITag.INamedTag<Item> CIRCUITS_BASIC = getTag("circuits/basic");
    public static ITag.INamedTag<Item> CIRCUITS_ADVANCED = getTag("circuits/advanced");
    public static ITag.INamedTag<Item> CIRCUITS_ELITE = getTag("circuits/elite");
    public static ITag.INamedTag<Item> CIRCUITS_MASTER = getTag("circuits/master");
    public static ITag.INamedTag<Item> CIRCUITS_DATA = getTag("circuits/data");
    public static ITag.INamedTag<Item> CIRCUITS_ULTIMATE = getTag("circuits/ultimate");
    public static ITag.INamedTag<Item> MACHINE_HULLS_CHEAP = getTag("machine_hulls/cheap");
    public static ITag.INamedTag<Item> MACHINE_HULLS_SEMI_CHEAP = getTag("machine_hulls/semi_cheap");
    public static ITag.INamedTag<Item> MACHINE_HULLS_BASIC = getTag("machine_hulls/basic");
    public static ITag.INamedTag<Item> MACHINE_HULLS_ADVANCED = getTag("machine_hulls/advanced");
    public static ITag.INamedTag<Item> MACHINE_HULLS_VERY_ADVANCED = getTag("machine_hulls/very_advanced");
    public static ITag.INamedTag<Item> GRINDING_HEAD = getTag("grinding_head");
    public static ITag.INamedTag<Item> PISTONS = getTag("pistons");
    public static ITag.INamedTag<Item> GEARS_STEELS = getTag("gears/steels");
    public static ITag.INamedTag<Item> GEARS_TITAN_TUNGSTEEL = getTag("gears/titantungsteel");
    public static ITag.INamedTag<Item> DUSTS_LAPIS_LAZ = getTag("dusts/lapislaz");
    public static ITag.INamedTag<Item> DUSTS_COALS = getTag("dusts/coals");
    public static ITag.INamedTag<Item> BATTERIES_SMALL = getTag("batteries/small");
    public static ITag.INamedTag<Item> BATTERIES_MEDIUM = getTag("batteries/medium");
    public static ITag.INamedTag<Item> BATTERIES_LARGE = getTag("batteries/large");
    public static ITag.INamedTag<Item> INGOTS_MIXED_METAL = getTag("ingots/mixed_metal");


    public static ITag.INamedTag<Item> getTag(String id){
        return TagUtils.getForgeItemTag(id);
    }
}
