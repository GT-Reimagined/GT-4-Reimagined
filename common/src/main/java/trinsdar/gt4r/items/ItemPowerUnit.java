package trinsdar.gt4r.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static trinsdar.gt4r.data.GT4RData.RockCutterPowerUnit;
import static trinsdar.gt4r.data.GT4RData.SmallPowerUnit;

public class ItemPowerUnit extends ItemBasic<ItemPowerUnit> implements IColorHandler {
    Material material;
    public ItemPowerUnit(String domain, String id, Material material) {
        super(domain, id);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public Material getMaterial(ItemStack stack){
        CompoundTag nbt = stack.getTag();
        if (nbt == null || !nbt.contains("M")) return material;
        return Material.get(nbt.getString("M"));
    }

    public void setMaterial(Material mat, ItemStack stack){
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putString("M", mat.getId());
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return getMaterial(stack).getRGB();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (flagIn.isAdvanced()) tooltip.add(new TextComponent("Energy: " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        long currentEnergy = getCurrentEnergy(stack);
        if (currentEnergy > 0) {
            double maxAmount = getMaxEnergy(stack), difference = maxAmount - currentEnergy;
            return (int)( 13*(difference / maxAmount));
        }
        return super.getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return 0x00BFFF;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ItemEnergyHandler.Provider(() -> {
            ItemEnergyHandler handler = new ItemEnergyHandler(getMaxEnergy(stack), 0, 32, 0, 1);
            handler.setEnergy(getCurrentEnergy(stack));
            return handler;
        });
    }

    public long getCurrentEnergy(ItemStack stack) {
        return getDataTag(stack).getLong(Ref.KEY_TOOL_DATA_ENERGY);
    }

    public CompoundTag getDataTag(ItemStack stack) {
        CompoundTag dataTag = stack.getTagElement(Ref.TAG_TOOL_DATA);
        return dataTag != null ? dataTag : validateTag(stack, 0, 100000);
    }

    public CompoundTag validateTag(ItemStack stack, long startingEnergy, long maxEnergy) {
        CompoundTag dataTag = stack.getOrCreateTagElement(Ref.TAG_TOOL_DATA);
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
        if (this == SmallPowerUnit || this == RockCutterPowerUnit){
            list.add(new Texture(getDomain(), "item/basic/" + id + "_overlay"));
        }
        return list.toArray(new Texture[0]);
    }
}
