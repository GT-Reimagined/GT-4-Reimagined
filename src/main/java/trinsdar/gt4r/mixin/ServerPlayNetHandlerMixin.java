package trinsdar.gt4r.mixin;

import muramasa.antimatter.Antimatter;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.ServerPlayNetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import trinsdar.gt4r.data.Attributes;

@Mixin(ServerPlayNetHandler.class)
public abstract class ServerPlayNetHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @ModifyConstant(
            remap = false,
            method = "processUseEntity",
            constant = {
                    @Constant(doubleValue = 36.0D)
            }
    )
    private double getExtendedAttackReachSquared(double value) {
        double extendedAttackReachValue = player.getAttributeValue(Attributes.ATTACK_REACH.get()) * 2.0D;
        Antimatter.LOGGER.info("Extended: " + extendedAttackReachValue);
        Antimatter.LOGGER.info("Extended Squared: " + (extendedAttackReachValue * extendedAttackReachValue));
        return extendedAttackReachValue * extendedAttackReachValue;
    }
}
