package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.MaterialSpear;

import java.util.function.Supplier;

public class ToolTypes {
    public static AntimatterToolType SPEAR = new AntimatterToolType(Ref.ID, "spear", 2, 1, 10, 3.0F, -2.9F) {
        @Override
        public IAntimatterTool instantiateTools(String domain) {
            return new MaterialSpear(domain, this, prepareInstantiation(domain));
        }

        @Override
        public IAntimatterTool instantiateTools(String domain, Supplier<Item.Properties> properties) {
            return new MaterialSpear(domain, this, properties.get());
        }

        private Item.Properties prepareInstantiation(String domain) {
            if (domain.isEmpty())
                Utils.onInvalidData("An AntimatterToolType was instantiated with an empty domain name!");
            Item.Properties properties = new Item.Properties().group(getItemGroup());
            if (!getRepairability()) properties.setNoRepair();
            return properties;
        }
    };

    public static void init(){
        if (!AntimatterAPI.isModLoaded("gtsp")){

        }
    }
}
