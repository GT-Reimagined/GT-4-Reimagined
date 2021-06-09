package trinsdar.gt4r.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemPowerUnit extends ItemBasic<ItemPowerUnit> implements IColorHandler {
    Material material;
    public ItemPowerUnit(String domain, String id, Material material) {
        super(domain, id);
        this.material = material;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return material.getRGB();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (flagIn.isAdvanced()) tooltip.add(new StringTextComponent("Energy: " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        long currentEnergy = getCurrentEnergy(stack);
        if (currentEnergy > 0) {
            double maxAmount = getMaxEnergy(stack), difference = maxAmount - currentEnergy;
            return difference / maxAmount;
        }
        return 1.0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0x00BFFF;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ItemEnergyHandler.Provider(() -> {
            ItemEnergyHandler handler = new ItemEnergyHandler(getMaxEnergy(stack), 0, 32, 0, 1);
            handler.setEnergy(getCurrentEnergy(stack));
            return handler;
        });
    }

    public long getCurrentEnergy(ItemStack stack) {
        return getDataTag(stack).getLong(Ref.KEY_TOOL_DATA_ENERGY);
    }

    public CompoundNBT getDataTag(ItemStack stack) {
        CompoundNBT dataTag = stack.getChildTag(Ref.TAG_TOOL_DATA);
        return dataTag != null ? dataTag : validateTag(stack, 0, 10000);
    }

    public CompoundNBT validateTag(ItemStack stack, long startingEnergy, long maxEnergy) {
        CompoundNBT dataTag = stack.getOrCreateChildTag(Ref.TAG_TOOL_DATA);
        dataTag.putLong(Ref.KEY_TOOL_DATA_ENERGY, startingEnergy);
        dataTag.putLong(Ref.KEY_TOOL_DATA_MAX_ENERGY, maxEnergy);
        return dataTag;
    }

    public long getMaxEnergy(ItemStack stack) {
        return getDataTag(stack).getLong(Ref.KEY_TOOL_DATA_MAX_ENERGY);
    }

    @Override
    public Texture[] getTextures() {
        String id = getId().startsWith("power_unit") ? "power_unit" : getId();
        List<Texture> list = new ArrayList<>();
        list.add(new Texture(getDomain(), "item/basic/" + id));
        if (getId().equals("small_power_unit")){
            list.add(new Texture(getDomain(), "item/basic/" + id + "_overlay"));
        }
        return list.toArray(new Texture[0]);
    }
}
