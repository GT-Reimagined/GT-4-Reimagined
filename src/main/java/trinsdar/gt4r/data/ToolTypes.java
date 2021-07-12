package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.MaterialSpear;

import java.util.Objects;
import java.util.function.Supplier;

import static trinsdar.gt4r.data.GT4RData.*;

public class ToolTypes {
    public static AntimatterToolType SPEAR = new SpearToolType(Ref.ID, "spear_gt", 2, 1, 10, 3.0F, -2.9F).setUseAction(UseAction.SPEAR);

    public static void init(){
        //temp method till this behavior has a hotkey
        Data.DRILL.removeBehaviour("aoe_break");
        Data.DRILL.setBrokenItems(ImmutableMap.of("drill_lv", i -> getBrokenItem(i, PowerUnitLV), "drill_mv", i -> getBrokenItem(i, PowerUnitMV), "drill_hv", i -> getBrokenItem(i, PowerUnitHV)));
        Data.CHAINSAW.setBrokenItems(ImmutableMap.of("chainsaw_lv", i -> getBrokenItem(i, PowerUnitLV), "chainsaw_mv", i -> getBrokenItem(i, PowerUnitMV), "chainsaw_hv", i -> getBrokenItem(i, PowerUnitHV)));
        Data.ELECTRIC_WRENCH.setBrokenItems(ImmutableMap.of("electric_wrench_lv", i -> getBrokenItem(i, PowerUnitLV), "electric_wrench_mv", i -> getBrokenItem(i, PowerUnitMV), "electric_wrench_hv", i -> getBrokenItem(i, PowerUnitHV)));
        Data.BUZZSAW.setBrokenItems(ImmutableMap.of("buzzsaw_lv", i -> getBrokenItem(i, PowerUnitLV), "buzzsaw_mv", i -> getBrokenItem(i, PowerUnitMV), "buzzsaw_hv", i -> getBrokenItem(i, PowerUnitHV)));
        Data.ELECTRIC_SCREWDRIVER.setBrokenItems(ImmutableMap.of("electric_screwdriver_lv", i -> getBrokenItem(i, SmallPowerUnit)));
        Data.JACKHAMMER.setBrokenItems(ImmutableMap.of("jackhammer_lv", i -> getBrokenItem(i, SmallPowerUnit)));
        Data.WRENCH.addEffectiveBlocks(Blocks.HOPPER);
        Data.ELECTRIC_WRENCH.addEffectiveBlocks(Blocks.HOPPER);
    }

    private static ItemStack getBrokenItem(ItemStack tool, IItemProvider broken){
        ItemStack powerUnit = new ItemStack(broken);
        Tuple<Long, Long> tuple = getEnergy(tool);
        CompoundNBT dataTag = powerUnit.getOrCreateChildTag(muramasa.antimatter.Ref.TAG_TOOL_DATA);
        dataTag.putLong(muramasa.antimatter.Ref.KEY_TOOL_DATA_ENERGY, tuple.getA());
        dataTag.putLong(muramasa.antimatter.Ref.KEY_TOOL_DATA_MAX_ENERGY, tuple.getB());
        if (broken.asItem() == PowerUnitHV || broken.asItem() == SmallPowerUnit){
            PowerUnitHV.setMaterial(((IAntimatterTool)tool.getItem()).getSecondaryMaterial(tool), powerUnit);
        }
        return powerUnit;
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
