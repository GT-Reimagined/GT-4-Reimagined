package trinsdar.gt4r.data;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;


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
    public static ITag.INamedTag<Item> MACHINE_HULLS_STABILIZED = getTag("machine_hulls/stabilized");
    public static ITag.INamedTag<Item> MACHINE_HULLS_VERY_ADVANCED = getTag("machine_hulls/very_advanced");
    public static ITag.INamedTag<Item> GRINDING_HEAD = getTag("grinding_head");
    public static ITag.INamedTag<Item> PISTONS = getTag("pistons");
    public static ITag.INamedTag<Item> GEARS_STEELS = getTag("gears/steels");
    public static ITag.INamedTag<Item> GEARS_TITAN_TUNGSTEEL = getTag("gears/titantungsteel");
    public static ITag.INamedTag<Item> DUSTS_LAPIS_LAZ = getTag("dusts/lapislaz");
    public static ITag.INamedTag<Item> DUSTS_COALS = getTag("dusts/coals");
    public static ITag.INamedTag<Item> BATTERIES_RE = getTag("batteries/re");
    public static ITag.INamedTag<Item> BATTERIES_SMALL = getTag("batteries/small");
    public static ITag.INamedTag<Item> BATTERIES_MEDIUM = getTag("batteries/medium");
    public static ITag.INamedTag<Item> BATTERIES_LARGE = getTag("batteries/large");
    public static ITag.INamedTag<Item> POWER_UNIT_LV = getTag("power_units/lv");
    public static ITag.INamedTag<Item> POWER_UNIT_MV = getTag("power_units/mv");
    public static ITag.INamedTag<Item> POWER_UNIT_HV = getTag("power_units/hv");
    public static ITag.INamedTag<Item> POWER_UNIT_SMALL = getTag("power_units/small");
    public static ITag.INamedTag<Item> POWER_UNIT_ROCK_CUTTER = getTag("power_units/rock_cutter");

    public static ITag.INamedTag<Item> INGOTS_MIXED_METAL = getTag("ingots/mixed_metal");
    public static ITag.INamedTag<Item> RODS_STEELS = getTag("rods/steels");
    public static ITag.INamedTag<Item> RODS_MAGNETIC = getTag("rods/magnetic");
    public static ITag.INamedTag<Item> CORALS = TagUtils.getItemTag(new ResourceLocation("corals"));
    public static ITag.INamedTag<Item> VINES = TagUtils.getItemTag(new ResourceLocation("vines"));


    public static ITag.INamedTag<Item> getTag(String id){
        return TagUtils.getForgeItemTag(id);
    }
}
