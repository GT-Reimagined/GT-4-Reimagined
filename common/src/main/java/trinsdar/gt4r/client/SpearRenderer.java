package trinsdar.gt4r.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.entity.SpearEntity;

@Environment(EnvType.CLIENT)
public class SpearRenderer extends EntityRenderer<SpearEntity> {
    private final ItemRenderer itemRenderer;
    public SpearRenderer(EntityRendererProvider.Context context, ItemRenderer renderer) {
        super(context);
        this.itemRenderer = renderer;
    }

    @Override
    public void render(SpearEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        //RenderSystem.enableRescaleNormal();
        doRenderTransformations(entityIn, partialTicks, matrixStackIn);
        Vector3f nextRotateAxis = new Vector3f(1.0F, 1.0F, 0.0F);
        nextRotateAxis.normalize();
        matrixStackIn.mulPose(nextRotateAxis.rotationDegrees(180.0F));
        matrixStackIn.translate(-0.1D, -0.2D, 0.0D);
        ItemStack weapon = entityIn.getPickupItem();
        if (!weapon.isEmpty())
            this.itemRenderer.renderStatic(weapon, ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, 0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void doRenderTransformations(SpearEntity entity, float partialTicks, PoseStack matrixStack) {
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, (entity).yRotO, (entity).getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, (entity).xRotO, (entity).getXRot()) - 45.0F));
    }

    @Override
    public ResourceLocation getTextureLocation(SpearEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
