package trinsdar.gt4r.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import trinsdar.gt4r.entity.SpearEntity;

@OnlyIn(Dist.CLIENT)
public class SpearRenderer extends EntityRenderer<SpearEntity> {
    private final ItemRenderer itemRenderer;
    public SpearRenderer(EntityRendererManager renderManager, ItemRenderer renderer) {
        super(renderManager);
        this.itemRenderer = renderer;
    }

    @Override
    public void render(SpearEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        RenderSystem.enableRescaleNormal();
        doRenderTransformations(entityIn, partialTicks, matrixStackIn);
        Vector3f nextRotateAxis = new Vector3f(1.0F, 1.0F, 0.0F);
        nextRotateAxis.normalize();
        matrixStackIn.mulPose(nextRotateAxis.rotationDegrees(180.0F));
        matrixStackIn.translate(-0.1D, -0.2D, 0.0D);
        ItemStack weapon = entityIn.getPickupItem();
        if (!weapon.isEmpty())
            this.itemRenderer.renderStatic(weapon, ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.popPose();
        RenderSystem.disableRescaleNormal();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void doRenderTransformations(SpearEntity entity, float partialTicks, MatrixStack matrixStack) {
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, (entity).yRotO, (entity).yRot) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, (entity).xRotO, (entity).xRot) - 45.0F));
    }

    @Override
    public ResourceLocation getTextureLocation(SpearEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
