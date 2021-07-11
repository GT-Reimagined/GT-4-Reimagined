package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.MaterialSpear;

import java.util.Objects;
import java.util.function.Supplier;

public class ToolTypes {
    public static AntimatterToolType SPEAR = new SpearToolType(Ref.ID, "spear_gt", 2, 1, 10, 3.0F, -2.9F).setUseAction(UseAction.SPEAR);

    public static void init(){
        //NOOP
    }

    public static class SpearToolType extends AntimatterToolType{

        private ITag.INamedTag<Item> tag, forgeTag; // Set?
        public SpearToolType(String domain, String id, int useDurability, int attackDurability, int craftingDurability, float baseAttackDamage, float baseAttackSpeed) {
            super(domain, id, useDurability, attackDurability, craftingDurability, baseAttackDamage, baseAttackSpeed);
            this.tag = TagUtils.getItemTag(new ResourceLocation(muramasa.antimatter.Ref.ID, "spear"));
            this.forgeTag = TagUtils.getForgeItemTag("tools/".concat("spear"));
        }

        @Override
        public IAntimatterTool instantiateTools(String domain) {
            return new MaterialSpear(domain, this, prepareInstantiation(domain));
        }

        public ItemStack getToolStack(Material primary, Material secondary) {
            return Objects.requireNonNull(AntimatterAPI.get(IAntimatterTool.class, "spear_gt")).asItemStack(primary, secondary);
        }

        @Override
        public ITag.INamedTag<Item> getTag() {
            return tag;
        }

        @Override
        public ITag.INamedTag<Item> getForgeTag() {
            return forgeTag;
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
    }
}
