package trinsdar.gt4r.mixin;

import muramasa.antimatter.Data;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.ToolTypes;

@Mixin(Player.class)
public class PlayerEntityMixin {
   @Inject(
            method = "createAttributes",
            at = @At(value = "RETURN")
    )
    private static void initAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> ci) {
       ci.getReturnValue().add(Attributes.ATTACK_REACH);
    }
}
