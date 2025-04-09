package org.gtreimagined.gt4r.items;

import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.tool.GTItemTier;
import org.gtreimagined.gtlib.tool.GTToolType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.gtreimagined.gtcore.data.GTCoreTools;
import org.jetbrains.annotations.Nullable;
import tesseract.api.forge.TesseractCaps;
import tesseract.api.gt.IEnergyHandlerItem;

import java.util.List;
import java.util.Map;

import static org.gtreimagined.gtlib.data.GTLibMaterials.Diamond;
import static org.gtreimagined.gt4r.data.Materials.Titanium;

public class MaterialRockCutter extends GTCoreTools.PoweredTool {
    public MaterialRockCutter(String domain, GTToolType type, Properties properties, int energyTier) {
        super(domain, type, GTItemTier.NULL, properties, energyTier);
    }

    @Override
    public int getItemColor(ItemStack stack, @org.jetbrains.annotations.Nullable Block block, int i) {
        if (i == 1) return Titanium.getRGB();
        return super.getItemColor(stack, block, i);
    }

    @Override
    public void onGenericFillItemGroup(CreativeModeTab group, NonNullList<ItemStack> list, long maxEnergy) {
        if (this.allowdedIn(group)){
            ItemStack stack = asItemStack(Diamond, Titanium);
            IEnergyHandlerItem h = stack.getCapability(TesseractCaps.ENERGY_HANDLER_CAPABILITY_ITEM).resolve().orElse(null);
            if (h != null){
                list.add(stack.copy());
                h.setCapacity(maxEnergy);
                h.setEnergy(maxEnergy);
                stack.setTag(h.getContainer().getTag());
                list.add(stack);
            }
        }
    }

    @Override
    public ItemStack resolveStack(Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        ItemStack stack = super.resolveStack(primary, secondary, startingEnergy, maxEnergy);
        if (!primary.has(MaterialTags.TOOLS)){
            return stack;
        }
        Map<Enchantment, Integer> mainEnchants = MaterialTags.TOOLS.get(primary).toolEnchantment();
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
}
