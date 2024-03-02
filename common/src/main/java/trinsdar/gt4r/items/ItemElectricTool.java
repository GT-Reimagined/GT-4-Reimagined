package trinsdar.gt4r.items;

import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.AntimatterItemTier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAbstractToolMethods;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import tesseract.api.context.TesseractItemContext;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IEnergyItem;

import java.util.function.Consumer;

public class ItemElectricTool extends DiggerItem implements IAntimatterObject, ITextureProvider, IModelProvider, IAbstractToolMethods, IEnergyItem {
    final AntimatterToolType type;
    int energyTier;
    public ItemElectricTool(float attackDamageModifier, float attackSpeedModifier, AntimatterItemTier tier, AntimatterToolType base, Properties properties, int energyTier) {
        super(attackDamageModifier, attackSpeedModifier, tier, base.getToolType(), properties);
        type = base;
        this.energyTier = energyTier;
    }

    @Override
    public IEnergyHandlerItem createEnergyHandler(TesseractItemContext context) {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[0];
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
