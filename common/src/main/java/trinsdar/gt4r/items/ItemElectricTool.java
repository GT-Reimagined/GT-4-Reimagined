package trinsdar.gt4r.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import muramasa.antimatter.Ref;
import muramasa.antimatter.behaviour.IBehaviour;
import muramasa.antimatter.behaviour.IDestroySpeed;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAbstractToolMethods;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import tesseract.api.context.TesseractItemContext;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IEnergyItem;
import trinsdar.gt4r.GT4RRef;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ItemElectricTool extends ItemBasic<ItemElectricTool> implements IElectricTool {
    final AntimatterToolType type;
    int energyTier;
    final Tier itemTier;
    public ItemElectricTool(String id, AntimatterToolType base, float miningSpeed, Properties properties, int energyTier) {
        super(GT4RRef.ID, id, properties.stacksTo(1).tab(Ref.TAB_ITEMS));
        type = base;
        this.energyTier = energyTier;
        this.itemTier = new ElectricItemTier(miningSpeed, base.getBaseQuality(), base.getBaseAttackDamage());
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float destroySpeed = genericIsCorrectToolForDrops(stack, state) ? getDefaultMiningSpeed(stack) : 1.0F;
        if (type.isPowered() && getCurrentEnergy(stack)  == 0){
            destroySpeed = 0.0f;
        }
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IDestroySpeed destroySpeed1)) continue;
            float i = destroySpeed1.getDestroySpeed(this, destroySpeed, stack, state);
            if (i > 0){
                destroySpeed = i;
                break;
            }
        }
        return destroySpeed;
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return onGenericHitEntity(stack, target, attacker, 0.75F, 0.75F);
    }

    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        return onGenericBlockDestroyed(stack, level, state, pos, miningEntity);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", type.getBaseAttackDamage(), AttributeModifier.Operation.ADDITION));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", type.getBaseAttackSpeed(), AttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Override
    public IEnergyHandlerItem createEnergyHandler(TesseractItemContext context) {
        return new ItemEnergyHandler(context, 100000, 8 * (int) Math.pow(4, this.energyTier), 8 * (int) Math.pow(4, this.energyTier), 1, 1);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (entity instanceof Player && ((Player) entity).isCreative()) {
            return 0;
        }
        return damage(stack, amount);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (type.getBlacklistedEnchantments().contains(enchantment)) return false;
        if (type.getToolTypes().contains(BlockTags.MINEABLE_WITH_AXE) && enchantment.category == EnchantmentCategory.WEAPON) {
            return true;
        }
        return (!type.isPowered() || (enchantment != Enchantments.UNBREAKING && enchantment != Enchantments.MENDING)) && enchantment.category.canEnchant(stack.getItem());
    }

    @Override
    public AntimatterToolType getAntimatterToolType() {
        return type;
    }

    @Override
    public Tier getItemTier() {
        return itemTier;
    }

    @Override
    public int getEnergyTier() {
        return energyTier;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return false;
    }

    public static class ElectricItemTier implements Tier {

        private final float speed;
        private final int level;
        private final float attack;

        ElectricItemTier(float speed, int level, float attack) {
            this.speed = speed;
            this.level = level;
            this.attack = attack;
        }

        @Override
        public int getUses() {
            return 0;
        }

        @Override
        public float getSpeed() {
            return speed;
        }

        @Override
        public float getAttackDamageBonus() {
            return attack;
        }

        @Override
        public int getLevel() {
            return level;
        }

        @Override
        public int getEnchantmentValue() {
            return (int) (getLevel() + getSpeed());
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    }
}
