package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.recipe.material.MaterialRecipe;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import speiger.src.collections.objects.lists.ObjectArrayList;
import tesseract.api.capability.TesseractGTCapability;
import tesseract.api.gt.IGTNode;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.items.ItemPowerUnit;
import trinsdar.gt4r.items.MaterialRockCutter;
import trinsdar.gt4r.items.MaterialSpear;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static muramasa.antimatter.Data.NULL;
import static net.minecraft.block.material.Material.*;
import static trinsdar.gt4r.data.GT4RData.*;

public class ToolTypes {
    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingInventory inv, MaterialRecipe.Result mats) {
            Material m = (Material) mats.mats.get("secondary");
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'));
            return type.resolveStack((Material) mats.mats.get("primary"), m == null ? NULL : m, battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@Nonnull ItemStack stack) {
            CompoundNBT nbt = stack.getTag().getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
            Material primary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider UNIT_POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool-from-unit", id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingInventory inv, MaterialRecipe.Result mats) {
            Tuple<Long, Tuple<Long, Material>> t = (Tuple<Long, Tuple<Long, Material>>) mats.mats.get("secondary");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'));
            t.getB().getB();
            return type.resolveStack((Material) mats.mats.get("primary"), t.getB().getB(), t.getA(), t.getB().getA());
        }

        @Override
        public Map<String, Object> getFromResult(@Nonnull ItemStack stack) {
            return ImmutableMap.of();
        }
    });

    public static AntimatterToolType SPEAR = new SpearToolType(Ref.ID, "spear_gt", 2, 1, 10, 3.0F, -2.9F).setUseAction(UseAction.SPEAR);
    public static AntimatterToolType ROCK_CUTTER = new RockCutterToolType(Ref.ID, "rock_cutter", 1, 1, 1, -1.0F, -3.0F).setPowered(100000L, 1).setRepairability(false).addToolTypes("pickaxe").setOverlayLayers(2).addEffectiveMaterials(PACKED_ICE, IRON, ROCK, ANVIL, PISTON).setBrokenItems(ImmutableMap.of("rock_cutter", i -> getBrokenItem(i, RockCutterPowerUnit)));

    static {
        PropertyIngredient.addGetter(CustomTags.BATTERIES_RE.getName(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_SMALL.getName(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_MEDIUM.getName(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_LARGE.getName(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_LV.getName(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_MV.getName(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_HV.getName(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_SMALL.getName(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_ROCK_CUTTER.getName(), ToolTypes::getEnergyAndMat);
    }

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

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery){
            long energy = stack.getCapability(TesseractGTCapability.ENERGY_HANDLER_CAPABILITY).map(IGTNode::getEnergy).orElse((long)0);
            return new Tuple<>(energy, ((ItemBattery)stack.getItem()).getCapacity());
        }
        if (stack.getItem() instanceof IAntimatterTool){
            IAntimatterTool tool = (IAntimatterTool) stack.getItem();
            if (tool.getAntimatterToolType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        return null;
    }

    public static Tuple<Long, Tuple<Long, Material>> getEnergyAndMat(ItemStack stack){
        if (stack.getItem() instanceof ItemPowerUnit){
            ItemPowerUnit tool = (ItemPowerUnit) stack.getItem();
            long currentEnergy = tool.getCurrentEnergy(stack);
            long maxEnergy = tool.getMaxEnergy(stack);
            return new Tuple<>(currentEnergy, new Tuple<>(maxEnergy, tool.getMaterial(stack)));
        }
        return null;
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

    public static class RockCutterToolType extends AntimatterToolType{

        public RockCutterToolType(String domain, String id, int useDurability, int attackDurability, int craftingDurability, float baseAttackDamage, float baseAttackSpeed) {
            super(domain, id, useDurability, attackDurability, craftingDurability, baseAttackDamage, baseAttackSpeed);
        }

        @Override
        public List<IAntimatterTool> instantiatePoweredTools(String domain) {
            List<IAntimatterTool> poweredTools = new ObjectArrayList<>();
            Item.Properties properties = prepareInstantiation(domain);
            poweredTools.add(new MaterialRockCutter(domain, this, properties, 1));
            return poweredTools;
        }

        @Override
        public List<IAntimatterTool> instantiatePoweredTools(String domain, Supplier<Item.Properties> properties) {
            List<IAntimatterTool> poweredTools = new ObjectArrayList<>();
            poweredTools.add(new MaterialRockCutter(domain, this, properties.get(), 1));
            return poweredTools;
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
