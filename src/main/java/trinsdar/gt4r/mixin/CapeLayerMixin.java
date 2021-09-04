package trinsdar.gt4r.mixin;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import trinsdar.gt4r.data.GT4RData;

@Mixin(CapeLayer.class)
public abstract class CapeLayerMixin extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    protected CapeLayerMixin(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Redirect(method = "render(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I;Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;getLocationCape()Lnet/minecraft/util/ResourceLocation;"))
    private ResourceLocation getLocationGTCape(AbstractClientPlayerEntity entity){
        if (entity.getDisplayName().equals("Dev")) return GT4RData.CAPE_LOCATIONS[3];
        return entity.getLocationCape();
    }

    private boolean checkPlayerAgainstUUID(){
        return false;
    }
}
