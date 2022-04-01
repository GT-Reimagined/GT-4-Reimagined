package trinsdar.gt4r.mixin;

import muramasa.antimatter.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.ToolTypes;


@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private Minecraft minecraft;

    @ModifyConstant(
            method = "pick",
            constant = @Constant(doubleValue = 6.0D)
    )
    private double getExtendedAttackReach(double value) {
        if (minecraft.player == null || minecraft.player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        return minecraft.player.getAttributeValue(Attributes.ATTACK_REACH.get()) * 2.0D;
    }

    @ModifyConstant(
            method = "pick",
            constant = @Constant(doubleValue = 3.0D)
    )
    private double getAttackReach(double value) {
        if (minecraft.player == null || minecraft.player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        return minecraft.player.getAttributeValue(Attributes.ATTACK_REACH.get());
    }

    @ModifyConstant(
            method = "pick",
            constant = @Constant(doubleValue = 9.0D)
    )
    private double getAttackReachSquared(double value) {
        if (minecraft.player == null || minecraft.player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        double attackReachValue = minecraft.player.getAttributeValue(Attributes.ATTACK_REACH.get());
        return attackReachValue * attackReachValue;
    }

    @Redirect(
            method = "pick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerController;getPickRange()F")
    )
    private float getRedirectedAttackReach(MultiPlayerGameMode controller){
        if (minecraft.player == null || minecraft.player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return controller.getPickRange();
        float reach = (float) minecraft.player.getAttributeValue(Attributes.ATTACK_REACH.get());
        //Antimatter.LOGGER.info(controller.getBlockReachDistance());
        //Antimatter.LOGGER.info(reach);
        return controller.getPlayerMode().isCreative() ? reach : reach - 0.5F;
    }
}
