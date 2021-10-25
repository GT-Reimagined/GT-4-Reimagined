package trinsdar.gt4r.mixin;

import muramasa.antimatter.Antimatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import trinsdar.gt4r.data.Attributes;


@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private Minecraft mc;

    @ModifyConstant(
            method = "getMouseOver",
            constant = @Constant(doubleValue = 6.0D)
    )
    private double getExtendedAttackReach(double value) {
        if (mc.player == null) return value;
        return mc.player.getAttributeValue(Attributes.ATTACK_REACH.get()) * 2.0D;
    }

    @ModifyConstant(
            method = "getMouseOver",
            constant = @Constant(doubleValue = 3.0D)
    )
    private double getAttackReach(double value) {
        if (mc.player == null) return value;
        return mc.player.getAttributeValue(Attributes.ATTACK_REACH.get());
    }

    @ModifyConstant(
            method = "getMouseOver",
            constant = @Constant(doubleValue = 9.0D)
    )
    private double getAttackReachSquared(double value) {
        if (mc.player == null) return value;
        double attackReachValue = mc.player.getAttributeValue(Attributes.ATTACK_REACH.get());
        return attackReachValue * attackReachValue;
    }

    @Redirect(
            method = "getMouseOver",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerController;getBlockReachDistance()F")
    )
    private float getRedirectedAttackReach(PlayerController controller){
        if (mc.player == null) return controller.getBlockReachDistance();
        float reach = (float) mc.player.getAttributeValue(Attributes.ATTACK_REACH.get());
        //Antimatter.LOGGER.info(controller.getBlockReachDistance());
        //Antimatter.LOGGER.info(reach);
        return controller.getCurrentGameType().isCreative() ? reach : reach - 0.5F;
    }
}
