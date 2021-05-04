package trinsdar.gt4r.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import trinsdar.gt4r.data.Attributes;


@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private Minecraft mc;

    @ModifyConstant(
            remap = false,
            method = "getMouseOver",
            constant = @Constant(doubleValue = 6.0D)
    )
    private double getExtendedAttackReach(double value) {
        if (mc.player == null) return value;
        return mc.player.getAttributeValue(Attributes.ATTACK_REACH.get()) * 2.0D;
    }

    @ModifyConstant(
            remap = false,
            method = "getMouseOver",
            constant = @Constant(doubleValue = 3.0D)
    )
    private double getAttackReach(double value) {
        if (mc.player == null) return value;
        return mc.player.getAttributeValue(Attributes.ATTACK_REACH.get());
    }

    @ModifyConstant(
            remap = false,
            method = "getMouseOver",
            constant = @Constant(doubleValue = 9.0D)
    )
    private double getAttackReachSquared(double value) {
        if (mc.player == null) return value;
        double attackReachValue = mc.player.getAttributeValue(Attributes.ATTACK_REACH.get());
        return attackReachValue * attackReachValue;
    }
}
