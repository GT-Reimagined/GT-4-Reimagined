package trinsdar.gt4r.items;

import com.google.common.collect.Multimap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.client.ClientUtil;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.entity.SpearEntity;

import java.util.List;
import java.util.UUID;

public class MaterialSpear extends MaterialTool {
    public UUID attUUID = UUID.fromString("0fb96bd2-8064-11ea-bc55-0242ac130003");
    private static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("63d316c1-7d6d-41be-81c3-41fc1a216c27");

    public MaterialSpear(String domain, AntimatterToolType type, Properties properties) {
        super(domain, type, properties);
        if (AntimatterPlatformUtils.isClient()) {
            ClientUtil.registerThrowingWeaponPropertyOverrides(this);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        /*if(isSelected && entityIn instanceof PlayerEntity &&  !this.hasReach((PlayerEntity) entityIn)) {
            this.extendReach((PlayerEntity) entityIn);
        }
        else if(!isSelected && entityIn instanceof PlayerEntity && this.hasReach((PlayerEntity) entityIn)) {
            this.removeReach((PlayerEntity) entityIn);
        }*/
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        prov.tex(item, "minecraft:item/handheld", getTextures()).override().predicate(new ResourceLocation("throwing"), 1).model(new ModelFile.UncheckedModelFile(new ResourceLocation(Ref.ID, "item/spear_throwing")));
    }

    @Override
    public Texture[] getTextures() {
        List<Texture> textures = new ObjectArrayList<>();
        int layers = getAntimatterToolType().getOverlayLayers();
        textures.add(new Texture(getDomain(), "item/tool/".concat(getId().replace("_gt", ""))));
        if (layers == 1) textures.add(new Texture(getDomain(), "item/tool/overlay/".concat(getId().replace("_gt", ""))));
        if (layers > 1) {
            for (int i = 1; i <= layers; i++) {
                textures.add(new Texture(getDomain(), String.join("", "item/tool/overlay/", getId().replace("_gt", ""), "_", Integer.toString(i))));
            }
        }
        return textures.toArray(new Texture[textures.size()]);
    }

    public String getId() {
        return "spear";
    }

    public float getReach(){
        return 7.0F;
    };

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.category == EnchantmentCategory.WEAPON || enchantment == Enchantments.LOYALTY){
            return true;
        }
        return type.isPowered() ? enchantment != Enchantments.UNBREAKING : super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slotType, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>modifiers = super.getAttributeModifiers(slotType, stack);
        if (slotType == EquipmentSlot.MAINHAND) {
            modifiers.put(Attributes.ATTACK_REACH, new AttributeModifier(ATTACK_REACH_MODIFIER, "Tool modifier", getReach(), AttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player player = (Player)entityLiving;
            int charge = this.getUseDuration(stack) - timeLeft;
            if (charge >= 5) {
                charge = 5;
            }

            if (!worldIn.isClientSide && charge > 2) {
                ItemStack weaponStack = stack.copy();
                weaponStack.setCount(1);
                SpearEntity thrown = this.createThrowingWeaponEntity(worldIn, player, weaponStack, charge);
                if (thrown == null) {
                    return;
                }
                //thrown.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F * ((float)charge / 10.0F + 0.5F), 0.5F);
                thrown.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F * ((float) charge / 10.0F + 0.5F), 0.5F);
                float damage = (type.getBaseAttackDamage() + getTier(stack).getAttackDamageBonus() + 1.0F);
                thrown.setBaseDamage(damage);

                if (player.getAbilities().instabuild) {
                    thrown.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                } else if (thrown.isValidThrowingWeapon()) {
                    stack.shrink(1);
                    if (stack.getCount() <= 0) {
                        player.getInventory().removeItem(stack);
                    }
                }

                if (thrown.isValidThrowingWeapon()) {
                    worldIn.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));
                    worldIn.addFreshEntity(thrown);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

        super.releaseUsing(stack, worldIn, entityLiving, timeLeft);
    }

    public SpearEntity createThrowingWeaponEntity(Level worldIn, Player player, ItemStack stack, int charge) {
        return new SpearEntity(worldIn, player, stack);
    }

    /*@Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }*/

    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }
}
