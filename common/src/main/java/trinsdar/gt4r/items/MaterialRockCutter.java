package trinsdar.gt4r.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractPlatformUtils;
import tesseract.api.gt.IEnergyHandler;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static muramasa.antimatter.Data.Diamond;
import static trinsdar.gt4r.data.Materials.Titanium;

public class MaterialRockCutter extends MaterialTool {

    public MaterialRockCutter(String domain, AntimatterToolType type, Properties properties, Material primary, Material secondary, int energyTier) {
        super(domain, type, properties, primary, secondary, energyTier);
    }

    @Override
    public String getToolID() {
        return type.getId();
    }

    @Override
    public ItemStack resolveStack(long startingEnergy, long maxEnergy) {
        ItemStack stack = super.resolveStack(startingEnergy, maxEnergy);
        if (!primary.has(MaterialTags.TOOLS)){
            return stack;
        }
        Map<Enchantment, Integer> mainEnchants = MaterialTags.TOOLS.getToolData(primary).toolEnchantment();
        if (!mainEnchants.containsKey(Enchantments.SILK_TOUCH)) {
            stack.enchant(Enchantments.SILK_TOUCH, 1);
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (flagIn.isAdvanced()) tooltip.add(new TextComponent("Energy: " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state) / 4;
    }
}
