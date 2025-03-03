package org.gtreimagined.gt4r.data;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.gtreimagined.gt4r.GT4RRef;


public class CustomTags {
    public static TagKey<Item> PLATES_STEELS = getTag("plates/steels");
    public static TagKey<Item> PLATES_INVAR_ALUMINIUM = getTag("plates/invaraluminium");
    public static TagKey<Item> PLATES_WROUGHT_ALUMINIUM = getTag("plates/wroughtaluminium");
    public static TagKey<Item> PLATES_TITAN_TUNGSTEEL = getTag("plates/titantungsteel");
    public static TagKey<Item> OVERCLOCKER_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "overclocker_upgrades"));
    public static TagKey<Item> TRANSFORMER_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "transformer_upgrades"));
    public static TagKey<Item> HV_TRANSFORMER_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "hv_transformer_upgrades"));
    public static TagKey<Item> BATTERY_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "battery_upgrades"));
    public static TagKey<Item> MUFFLER_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "muffler_upgrades"));
    public static TagKey<Item> STEAM_UPGRADES = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "steam_upgrades"));
    public static TagKey<Item> CIRCUITS_ULTIMATE = getTag("circuits/ultimate");
    public static TagKey<Item> MACHINE_HULLS_CHEAP = getTag("machine_hulls/cheap");
    public static TagKey<Item> MACHINE_HULLS_BASIC = getTag("machine_hulls/basic");
    public static TagKey<Item> MACHINE_HULLS_ADVANCED = getTag("machine_hulls/advanced");
    public static TagKey<Item> MACHINE_HULLS_STABILIZED = getTag("machine_hulls/stabilized");
    public static TagKey<Item> MACHINE_HULLS_VERY_ADVANCED = getTag("machine_hulls/very_advanced");
    public static TagKey<Item> GRINDING_HEAD = getTag("grinding_head");
    public static TagKey<Item> PISTONS = getTag("pistons");
    public static TagKey<Item> GEARS_STEELS = getTag("gears/steels");
    public static TagKey<Item> GEARS_TITAN_TUNGSTEEL = getTag("gears/titantungsteel");
    public static TagKey<Item> DUSTS_LAPIS_LAZ = getTag("dusts/lapislaz");
    public static TagKey<Item> DUSTS_COALS = getTag("dusts/coals");
    public static TagKey<Item> POWER_UNIT_ROCK_CUTTER = getTag("power_units/rock_cutter");
    public static TagKey<Item> DRILL = TagUtils.getItemTag(new ResourceLocation(GT4RRef.ID, "drill"));

    public static TagKey<Item> INGOTS_MIXED_METAL = getTag("ingots/mixed_metal");
    public static TagKey<Item> RODS_STEELS = getTag("rods/steels");
    public static TagKey<Item> RODS_MAGNETIC = getTag("rods/magnetic");
    public static TagKey<Item> CORALS = TagUtils.getItemTag(new ResourceLocation("corals"));
    public static TagKey<Item> VINES = TagUtils.getItemTag(new ResourceLocation("vines"));


    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }

    public static void init(){}
}
