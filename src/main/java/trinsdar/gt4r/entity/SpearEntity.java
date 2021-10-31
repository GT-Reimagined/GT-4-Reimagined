package trinsdar.gt4r.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import trinsdar.gt4r.data.GT4RData;

public class SpearEntity extends AbstractArrowEntity  implements IEntityAdditionalSpawnData {
    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(SpearEntity.class, DataSerializers.BYTE);
    protected ItemStack weapon = ItemStack.EMPTY;
    public int returningTicks;
    private boolean dealtDamage;
    public SpearEntity(EntityType<SpearEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SpearEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(GT4RData.SPEAR_ENTITY_TYPE, thrower, worldIn);
        weapon = thrownStackIn.copy();
        this.dataManager.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyaltyModifier(thrownStackIn));
    }

    @OnlyIn(Dist.CLIENT)
    public SpearEntity(World worldIn, double x, double y, double z) {
        super(GT4RData.SPEAR_ENTITY_TYPE, x, y, z, worldIn);
    }

    public SpearEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(GT4RData.SPEAR_ENTITY_TYPE, world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LOYALTY_LEVEL, (byte)0);
    }

    @Override
    public ItemStack getArrowStack() {
        return weapon;
    }

    public void tick() {
        if (this.timeInGround > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getShooter();
        if ((this.dealtDamage || this.getNoClip()) && entity != null) {
            int i = this.dataManager.get(LOYALTY_LEVEL);
            if (i > 0 && !this.shouldReturnToThrower()) {
                if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.remove();
            } else if (i > 0) {
                this.setNoClip(true);
                Vector3d vector3d = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
                this.setRawPosition(this.getPosX(), this.getPosY() + vector3d.y * 0.015D * (double)i, this.getPosZ());
                if (this.world.isRemote) {
                    this.lastTickPosY = this.getPosY();
                }

                double d0 = 0.05D * (double)i;
                this.setMotion(this.getMotion().scale(0.95D).add(vector3d.normalize().scale(d0)));
                if (this.returningTicks == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returningTicks;
            }
        }

        super.tick();
    }

    private boolean shouldReturnToThrower() {
        Entity entity = this.getShooter();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected void onEntityHit(EntityRayTraceResult result) {
        Entity target = result.getEntity();
        float f = (float) this.getDamage();
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;
            f += EnchantmentHelper.getModifierForCreature(this.weapon, livingentity.getCreatureAttribute());
        }

        Entity shooter = this.getShooter();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = (new IndirectEntityDamageSource("mob", this, this)).setProjectile();
        } else if (shooter instanceof PlayerEntity) {
            damagesource = (new IndirectEntityDamageSource("player", this, shooter)).setProjectile();
        } else {
            damagesource = (new IndirectEntityDamageSource("mob", this, shooter)).setProjectile();
        }
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
        if (target.attackEntityFrom(damagesource, f)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)target;
                if (shooter instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingTarget, shooter);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity)shooter, livingTarget);
                }

                this.arrowHit(livingTarget);
            }
        }

        this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;

        this.playSound(soundevent, f1, 1.0F);
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        super.arrowHit(living);
        if (weapon.getItem().isDamageable()){
            if (weapon.attemptDamageItem(1, this.world.rand, null)){
                weapon.shrink(1);
                this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                this.remove();
            }
        }
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        CompoundNBT weaponNBT = compound.getCompound("Weapon");
        this.weapon = ItemStack.read(weaponNBT);
    }

    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeItemStack(this.weapon, false);
    }

    public void readSpawnData(PacketBuffer additionalData) {
        ItemStack stack = additionalData.readItemStack();
        weapon = stack.copy();
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        CompoundNBT weaponNBT = this.weapon.write(new CompoundNBT());
        compound.put("Weapon", weaponNBT);
    }

    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean isValidThrowingWeapon() {
        return !this.weapon.isEmpty();
    }
}
