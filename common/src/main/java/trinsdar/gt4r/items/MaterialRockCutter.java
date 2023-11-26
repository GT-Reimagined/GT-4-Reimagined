package trinsdar.gt4r.items;

import io.github.gregtechintergalactical.gtcore.data.GTCoreTools;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.tool.AntimatterItemTier;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractCapUtils;
import tesseract.api.gt.IEnergyHandler;
import tesseract.api.gt.IEnergyHandlerItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static trinsdar.gt4r.data.Materials.Titanium;

public class MaterialRockCutter extends GTCoreTools.PoweredTool {
    public MaterialRockCutter(String domain, AntimatterToolType type, Properties properties, int energyTier) {
        super(domain, type, AntimatterItemTier.NULL, properties, energyTier);
    }

    @Override
    public String getId() {
        return type.getId();
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
            IEnergyHandlerItem h = TesseractCapUtils.getEnergyHandlerItem(stack).orElse(null);
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

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state) / 4;
    }
}
