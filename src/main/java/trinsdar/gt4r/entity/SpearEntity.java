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
    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.defineId(SpearEntity.class, DataSerializers.BYTE);
    protected ItemStack weapon = ItemStack.EMPTY;
    public int returningTicks;
    private boolean dealtDamage;
    public SpearEntity(EntityType<SpearEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SpearEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(GT4RData.SPEAR_ENTITY_TYPE, thrower, worldIn);
        weapon = thrownStackIn.copy();
        this.entityData.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyalty(thrownStackIn));
    }

    @OnlyIn(Dist.CLIENT)
    public SpearEntity(World worldIn, double x, double y, double z) {
        super(GT4RData.SPEAR_ENTITY_TYPE, x, y, z, worldIn);
    }

    public SpearEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(GT4RData.SPEAR_ENTITY_TYPE, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LOYALTY_LEVEL, (byte)0);
    }

    @Override
    public ItemStack getPickupItem() {
        return weapon;
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            int i = this.entityData.get(LOYALTY_LEVEL);
            if (i > 0 && !this.shouldReturnToThrower()) {
                if (!this.level.isClientSide && this.pickup == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.remove();
            } else if (i > 0) {
                this.setNoPhysics(true);
                Vector3d vector3d = new Vector3d(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
                this.setPosRaw(this.getX(), this.getY() + vector3d.y * 0.015D * (double)i, this.getZ());
                if (this.level.isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.05D * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vector3d.normalize().scale(d0)));
                if (this.returningTicks == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returningTicks;
            }
        }

        super.tick();
    }

    private boolean shouldReturnToThrower() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected void onHitEntity(EntityRayTraceResult result) {
        Entity target = result.getEntity();
        float f = (float) this.getBaseDamage();
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;
            f += EnchantmentHelper.getDamageBonus(this.weapon, livingentity.getMobType());
        }

        Entity shooter = this.getOwner();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = (new IndirectEntityDamageSource("mob", this, this)).setProjectile();
        } else if (shooter instanceof PlayerEntity) {
            damagesource = (new IndirectEntityDamageSource("player", this, shooter)).setProjectile();
        } else {
            damagesource = (new IndirectEntityDamageSource("mob", this, shooter)).setProjectile();
        }
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (target.hurt(damagesource, f)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity)target;
                if (shooter instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingTarget, shooter);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)shooter, livingTarget);
                }

                this.doPostHurtEffects(livingTarget);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;

        this.playSound(soundevent, f1, 1.0F);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        if (weapon.getItem().canBeDepleted()){
            if (weapon.hurt(1, this.level.random, null)){
                weapon.shrink(1);
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                this.remove();
            }
        }
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        CompoundNBT weaponNBT = compound.getCompound("Weapon");
        this.weapon = ItemStack.of(weaponNBT);
    }

    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeItemStack(this.weapon, false);
    }

    public void readSpawnData(PacketBuffer additionalData) {
        ItemStack stack = additionalData.readItem();
        weapon = stack.copy();
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        CompoundNBT weaponNBT = this.weapon.save(new CompoundNBT());
        compound.put("Weapon", weaponNBT);
    }

    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean isValidThrowingWeapon() {
        return !this.weapon.isEmpty();
    }
}
