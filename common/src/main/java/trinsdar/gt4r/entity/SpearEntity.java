package trinsdar.gt4r.entity;

import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import trinsdar.gt4r.data.GT4RData;

public class SpearEntity extends AbstractArrow  implements IEntityAdditionalSpawnData {
    private static final EntityDataAccessor<Byte> LOYALTY_LEVEL = SynchedEntityData.defineId(SpearEntity.class, EntityDataSerializers.BYTE);
    protected ItemStack weapon = ItemStack.EMPTY;
    public int returningTicks;
    private boolean dealtDamage;
    public SpearEntity(EntityType<SpearEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public SpearEntity(Level worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(GT4RData.SPEAR_ENTITY_TYPE, thrower, worldIn);
        weapon = thrownStackIn.copy();
        this.entityData.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyalty(thrownStackIn));
    }

    @Environment(EnvType.CLIENT)
    public SpearEntity(Level worldIn, double x, double y, double z) {
        super(GT4RData.SPEAR_ENTITY_TYPE, x, y, z, worldIn);
    }

    public SpearEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
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
                if (!this.level.isClientSide && this.pickup == Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.remove(RemovalReason.DISCARDED);
            } else if (i > 0) {
                this.setNoPhysics(true);
                Vec3 vector3d = new Vec3(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
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
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected void onHitEntity(EntityHitResult result) {
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
        } else if (shooter instanceof Player) {
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
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        CompoundTag weaponNBT = compound.getCompound("Weapon");
        this.weapon = ItemStack.of(weaponNBT);
    }

    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeItemStack(this.weapon, false);
    }

    public void readSpawnData(FriendlyByteBuf additionalData) {
        ItemStack stack = additionalData.readItem();
        weapon = stack.copy();
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        CompoundTag weaponNBT = this.weapon.save(new CompoundTag());
        compound.put("Weapon", weaponNBT);
    }

    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean isValidThrowingWeapon() {
        return !this.weapon.isEmpty();
    }
}
