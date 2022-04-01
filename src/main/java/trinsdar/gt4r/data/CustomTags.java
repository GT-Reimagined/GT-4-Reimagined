package trinsdar.gt4r.data;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.resources.ResourceLocation;


public class CustomTags {
    public static Tag.Named<Item> PLATES_STEELS = getTag("plates/steels");
    public static Tag.Named<Item> PLATES_INVAR_ALUMINIUM = getTag("plates/invaraluminium");
    public static Tag.Named<Item> PLATES_IRON_ALUMINIUM = getTag("plates/ironaluminium");
    public static Tag.Named<Item> PLATES_WROUGHT_ALUMINIUM = getTag("plates/wroughtaluminium");
    public static Tag.Named<Item> PLATES_TITAN_TUNGSTEEL = getTag("plates/titantungsteel");
    public static Tag.Named<Item> CIRCUITS_BASIC = getTag("circuits/basic");
    public static Tag.Named<Item> CIRCUITS_ADVANCED = getTag("circuits/advanced");
    public static Tag.Named<Item> CIRCUITS_ELITE = getTag("circuits/elite");
    public static Tag.Named<Item> CIRCUITS_MASTER = getTag("circuits/master");
    public static Tag.Named<Item> CIRCUITS_DATA = getTag("circuits/data");
    public static Tag.Named<Item> CIRCUITS_ULTIMATE = getTag("circuits/ultimate");
    public static Tag.Named<Item> MACHINE_HULLS_CHEAP = getTag("machine_hulls/cheap");
    public static Tag.Named<Item> MACHINE_HULLS_SEMI_CHEAP = getTag("machine_hulls/semi_cheap");
    public static Tag.Named<Item> MACHINE_HULLS_BASIC = getTag("machine_hulls/basic");
    public static Tag.Named<Item> MACHINE_HULLS_ADVANCED = getTag("machine_hulls/advanced");
    public static Tag.Named<Item> MACHINE_HULLS_STABILIZED = getTag("machine_hulls/stabilized");
    public static Tag.Named<Item> MACHINE_HULLS_VERY_ADVANCED = getTag("machine_hulls/very_advanced");
    public static Tag.Named<Item> GRINDING_HEAD = getTag("grinding_head");
    public static Tag.Named<Item> PISTONS = getTag("pistons");
    public static Tag.Named<Item> GEARS_STEELS = getTag("gears/steels");
    public static Tag.Named<Item> GEARS_TITAN_TUNGSTEEL = getTag("gears/titantungsteel");
    public static Tag.Named<Item> DUSTS_LAPIS_LAZ = getTag("dusts/lapislaz");
    public static Tag.Named<Item> DUSTS_COALS = getTag("dusts/coals");
    public static Tag.Named<Item> BATTERIES_RE = getTag("batteries/re");
    public static Tag.Named<Item> BATTERIES_SMALL = getTag("batteries/small");
    public static Tag.Named<Item> BATTERIES_MEDIUM = getTag("batteries/medium");
    public static Tag.Named<Item> BATTERIES_LARGE = getTag("batteries/large");
    public static Tag.Named<Item> POWER_UNIT_LV = getTag("power_units/lv");
    public static Tag.Named<Item> POWER_UNIT_MV = getTag("power_units/mv");
    public static Tag.Named<Item> POWER_UNIT_HV = getTag("power_units/hv");
    public static Tag.Named<Item> POWER_UNIT_SMALL = getTag("power_units/small");
    public static Tag.Named<Item> POWER_UNIT_ROCK_CUTTER = getTag("power_units/rock_cutter");

    public static Tag.Named<Item> INGOTS_MIXED_METAL = getTag("ingots/mixed_metal");
    public static Tag.Named<Item> RODS_STEELS = getTag("rods/steels");
    public static Tag.Named<Item> RODS_MAGNETIC = getTag("rods/magnetic");
    public static Tag.Named<Item> CORALS = TagUtils.getItemTag(new ResourceLocation("corals"));
    public static Tag.Named<Item> VINES = TagUtils.getItemTag(new ResourceLocation("vines"));


    public static Tag.Named<Item> getTag(String id){
        return TagUtils.getForgeItemTag(id);
    }
}
