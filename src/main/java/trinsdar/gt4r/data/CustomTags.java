package trinsdar.gt4r.data;

import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;



public class CustomTags {
    public static ITag.INamedTag<Item> PLATES_STEELS = getTag("plates/steels");
    public static ITag.INamedTag<Item> PLATES_INVAR_ALUMINIUM = getTag("plates/invaraluminium");
    public static ITag.INamedTag<Item> PLATES_IRON_ALUMINIUM = getTag("plates/ironaluminium");
    public static ITag.INamedTag<Item> PLATES_WROUGHT_ALUMINIUM = getTag("plates/wroughtaluminium");
    public static ITag.INamedTag<Item> PLATES_TITAN_TUNGSTEEL = getTag("plates/titantungsteel");


    public static ITag.INamedTag<Item> getTag(String id){
        return Utils.getForgeItemTag(id);
    }
}
