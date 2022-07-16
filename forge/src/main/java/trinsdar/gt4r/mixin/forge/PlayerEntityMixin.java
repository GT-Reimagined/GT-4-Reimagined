package trinsdar.gt4r.mixin.forge;

import muramasa.antimatter.Data;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.ToolTypes;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> arg, Level arg2) {
        super(arg, arg2);
    }

    /*@ModifyConstant(
            method = "attackTargetEntityWithCurrentItem",
            constant = @Constant(doubleValue = 9.0D)
    )
    private double getAttackReachSquared(double value) {
        if (this.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        double attackReachValue = this.getAttributeValue(Attributes.ATTACK_REACH);
        return attackReachValue * attackReachValue;
    }*/
}
