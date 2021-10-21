package trinsdar.gt4r.items;

import com.google.common.collect.Multimap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.client.ClientUtil;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.entity.SpearEntity;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MaterialSpear extends MaterialTool {
    public UUID attUUID = UUID.fromString("0fb96bd2-8064-11ea-bc55-0242ac130003");
    private static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("63d316c1-7d6d-41be-81c3-41fc1a216c27");

    public MaterialSpear(String domain, AntimatterToolType type, Properties properties) {
        super(domain, type, properties);
        setRegistryName(Ref.ID, "spear");
        if (FMLEnvironment.dist.isClient()) {
            ClientUtil.registerThrowingWeaponPropertyOverrides(this);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        /*if(isSelected && entityIn instanceof PlayerEntity &&  !this.hasReach((PlayerEntity) entityIn)) {
            this.extendReach((PlayerEntity) entityIn);
        }
        else if(!isSelected && entityIn instanceof PlayerEntity && this.hasReach((PlayerEntity) entityIn)) {
            this.removeReach((PlayerEntity) entityIn);
        }*/
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
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
        return "spear_gt";
    }

    public float getReach(){
        return 7.0F;
    };

    public void extendReach(PlayerEntity player) {
        AttributeModifier extended = new AttributeModifier(attUUID, "extend-reach", getReach(), AttributeModifier.Operation.ADDITION);

        if(!player.getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier(extended))
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).applyPersistentModifier(extended);
    }

    public boolean hasReach(PlayerEntity player) {
        AttributeModifier extended = new AttributeModifier(attUUID, "extend-reach", getReach(), AttributeModifier.Operation.ADDITION);
        return player.getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier(extended);
    }

    public void removeReach(PlayerEntity player) {
        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(attUUID);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.type == EnchantmentType.WEAPON || enchantment == Enchantments.LOYALTY){
            return true;
        }
        return type.isPowered() ? enchantment != Enchantments.UNBREAKING : super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slotType, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>modifiers = super.getAttributeModifiers(slotType, stack);
        if (slotType == EquipmentSlotType.MAINHAND) {
            modifiers.put(Attributes.ATTACK_REACH.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Tool modifier", getReach(), AttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return ActionResult.resultConsume(stack);
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityLiving;
            int charge = this.getUseDuration(stack) - timeLeft;
            if (charge >= 5) {
                charge = 5;
            }

            if (!worldIn.isRemote && charge > 2) {
                ItemStack weaponStack = stack.copy();
                weaponStack.setCount(1);
                SpearEntity thrown = this.createThrowingWeaponEntity(worldIn, player, weaponStack, charge);
                if (thrown == null) {
                    return;
                }
                //thrown.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F * ((float)charge / 10.0F + 0.5F), 0.5F);
                thrown.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F * ((float) charge / 10.0F + 0.5F), 0.5F);
                float damage = (type.getBaseAttackDamage() + getTier(stack).getAttackDamage() + 1.0F);
                thrown.setDamage(damage);

                if (player.abilities.isCreativeMode) {
                    thrown.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                } else if (thrown.isValidThrowingWeapon()) {
                    stack.shrink(1);
                    if (stack.getCount() <= 0) {
                        player.inventory.deleteStack(stack);
                    }
                }

                if (thrown.isValidThrowingWeapon()) {
                    worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                    worldIn.addEntity(thrown);
                }

                player.addStat(Stats.ITEM_USED.get(this));
            }
        }

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    public SpearEntity createThrowingWeaponEntity(World worldIn, PlayerEntity player, ItemStack stack, int charge) {
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

    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }
}
