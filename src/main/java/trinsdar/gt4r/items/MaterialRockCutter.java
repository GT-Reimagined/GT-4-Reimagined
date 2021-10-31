package trinsdar.gt4r.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static muramasa.antimatter.Data.Diamond;
import static trinsdar.gt4r.data.Materials.Titanium;

public class MaterialRockCutter extends MaterialTool {
    public MaterialRockCutter(String domain, AntimatterToolType type, Properties properties, int energyTier) {
        super(domain, type, properties, energyTier);
    }

    @Override
    public String getId() {
        return type.getId();
    }

    @Override
    public void onGenericFillItemGroup(ItemGroup group, NonNullList<ItemStack> list, long maxEnergy) {
        if (this.isInGroup(group)){
            ItemStack stack = asItemStack(Diamond, Titanium);
            getDataTag(stack).putLong(Ref.KEY_TOOL_DATA_ENERGY, maxEnergy);
            list.add(stack);
        }
    }

    @Override
    public ItemStack resolveStack(Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = super.resolveStack(primary, secondary, startingEnergy, maxEnergy);
        Map<Enchantment, Integer> mainEnchants = primary.getToolEnchantments(), handleEnchants = secondary.getHandleEnchantments();
        if (!mainEnchants.containsKey(Enchantments.SILK_TOUCH) && !handleEnchants.containsKey(Enchantments.SILK_TOUCH)) {
            stack.addEnchantment(Enchantments.SILK_TOUCH, 1);
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (flagIn.isAdvanced()) tooltip.add(new StringTextComponent("Energy: " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state) / 4;
    }
}
