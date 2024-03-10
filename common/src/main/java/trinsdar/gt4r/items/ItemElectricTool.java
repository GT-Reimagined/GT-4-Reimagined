package trinsdar.gt4r.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.Ref;
import muramasa.antimatter.behaviour.IBehaviour;
import muramasa.antimatter.behaviour.IDestroySpeed;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.data.AntimatterDefaultTools;
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
import muramasa.antimatter.tool.IBasicAntimatterTool;
import muramasa.antimatter.tool.ToolUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.api.context.TesseractItemContext;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IEnergyItem;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static trinsdar.gt4r.data.Materials.*;

public class ItemElectricTool extends ItemBasic<ItemElectricTool> implements IElectricTool {
    final AntimatterToolType type;
    int energyTier;
    final Tier itemTier;
    final Object2ObjectMap<String, IBehaviour<IBasicAntimatterTool>> behaviours;
    public ItemElectricTool(String id, AntimatterToolType base, float miningSpeed, float attackDamage, int quality, int energyTier, Predicate<IBehaviour<?>> behaviourFilter) {
        super(GT4RRef.ID, id, ToolUtils.getToolProperties(Ref.TAB_ITEMS, false).durability(1));
        type = base;
        this.energyTier = energyTier;
        this.itemTier = new ElectricItemTier(miningSpeed, quality, attackDamage);
        behaviours = new Object2ObjectOpenHashMap<>();
        base.getBehaviours().forEach((s, b) -> {
            if (behaviourFilter.test(b)){
                behaviours.put(s, b);
            }
        });
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
    public Object2ObjectMap<String, IBehaviour<IBasicAntimatterTool>> getBehaviours(){
        return behaviours;
    }

    public float getDefaultMiningSpeed(ItemStack stack) {
        return IElectricTool.super.getDefaultMiningSpeed(stack) * (float)(3 * this.energyTier);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> list) {
        onGenericFillItemGroup(group, list, 100000L * this.energyTier);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        if (this == GT4RData.RockCutter){
            stack.enchant(Enchantments.SILK_TOUCH, 1);
        }
        return stack;
    }

    @Override
    public ItemStack resolveStack(long startingEnergy, long maxEnergy) {
        ItemStack stack = this.getDefaultInstance();
        validateEnergyTag(stack, startingEnergy, maxEnergy);
        return stack;
    }

    public boolean doesSneakBypassUse(ItemStack stack, LevelReader world, BlockPos pos, Player player) {
        return Utils.doesStackHaveToolTypes(stack, AntimatterDefaultTools.WRENCH, AntimatterDefaultTools.SCREWDRIVER, AntimatterDefaultTools.CROWBAR, AntimatterDefaultTools.WIRE_CUTTER); // ???
    }

    //fabric method
    public boolean isSuitableFor(ItemStack stack, BlockState state) {
        return this.genericIsCorrectToolForDrops(stack, state);
    }

    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state){
        return genericIsCorrectToolForDrops(stack, state);
    }

    @Override
    public void onUseTick(Level p_41428_, LivingEntity p_41429_, ItemStack p_41430_, int p_41431_) {
        super.onUseTick(p_41428_, p_41429_, p_41430_, p_41431_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        onGenericAddInformation(stack, tooltip, flag);
        super.appendHoverText(stack, world, tooltip, flag);
    }

    //TODO figure out why I wrote the below todo
    //TODO figure this out
    //@Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return type.getUseAction();
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return type.getUseAction() == UseAnim.NONE ? super.getUseDuration(stack) : 72000;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float destroySpeed = genericIsCorrectToolForDrops(stack, state) ? getDefaultMiningSpeed(stack) : 1.0F;
        if (type.isPowered() && !hasEnoughDurability(stack, 1, true)){
            destroySpeed = 0.0f;
        }
        for (Map.Entry<String, IBehaviour<IBasicAntimatterTool>> e : getBehaviours().entrySet()) {
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
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", itemTier.getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", type.getBaseAttackSpeed(), AttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        return onGenericItemUse(ctx);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        return genericInteractLivingEntity(stack, player, interactionTarget, usedHand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        InteractionResultHolder<ItemStack> result = onGenericRightclick(level, player, usedHand);
        if (result.getResult().shouldAwardStats()){
            return result;
        }
        return super.use(level, player, usedHand);
    }

    public void handleRenderHighlight(Player entity, LevelRenderer levelRenderer, Camera camera, HitResult target, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource) {
        onGenericHighlight(entity, levelRenderer, camera, target, partialTicks, poseStack, multiBufferSource);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return getPoweredBarColor(stack);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return getPoweredBarWidth(stack);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return isPoweredBarVisible(stack);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player player) {
        return type.getBlockBreakability();
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return type.getToolTypes().contains(BlockTags.MINEABLE_WITH_AXE);
    }

    @Override
    public IEnergyHandlerItem createEnergyHandler(TesseractItemContext context) {
        return new ItemEnergyHandler(context, 100000L * energyTier, 8 * (int) Math.pow(4, this.energyTier), 8 * (int) Math.pow(4, this.energyTier), 1, 1);
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
        return getMaxDamage();
    }

    //@Override
    public int getEnchantability(ItemStack stack) {
        return getItemTier().getEnchantmentValue();
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    //@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (type.getBlacklistedEnchantments().contains(enchantment)) return false;
        if (type.getToolTypes().contains(BlockTags.MINEABLE_WITH_AXE) && enchantment.category == EnchantmentCategory.WEAPON) {
            return true;
        }
        return enchantment != Enchantments.UNBREAKING && enchantment != Enchantments.MENDING && enchantment.category.canEnchant(stack.getItem());
    }

    public boolean hasContainerItem(ItemStack stack) {
        return type.hasContainer();
    }

    public ItemStack getContainerItem(ItemStack oldStack) {
        return getGenericContainerItem(oldStack);
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 1){
            return getId().contains("advanced") ? 0x0000ff : 0xff6400;
        }
        if (i == 0) {
            return this == GT4RData.RockCutter ? Diamond.getRGB() : this.getId().contains("advanced") ? TungstenSteel.getRGB() : Steel.getRGB();
        }
        return -1;
    }

    @Override
    public Texture[] getTextures() {
        List<Texture> textures = new ObjectArrayList<>();
        int layers = this == GT4RData.AdvancedDrill || this == GT4RData.DiamondDrill ? 2 : getAntimatterToolType().getOverlayLayers();
        textures.add(new Texture(getTextureDomain(), "item/tool/".concat(getAntimatterToolType().getId())));
        if (layers == 1)
            textures.add(new Texture(getTextureDomain(), "item/tool/overlay/".concat(getAntimatterToolType().getId())));
        if (layers > 1) {
            for (int i = 1; i <= layers; i++) {
                textures.add(new Texture(getTextureDomain(), String.join("", "item/tool/overlay/", getAntimatterToolType().getId(), "_", Integer.toString(i))));
            }
        }
        return textures.toArray(new Texture[textures.size()]);
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
