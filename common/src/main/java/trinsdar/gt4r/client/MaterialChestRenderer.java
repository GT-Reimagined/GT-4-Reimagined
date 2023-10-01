package trinsdar.gt4r.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import muramasa.antimatter.machine.Tier;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.block.BlockMaterialChest;
import trinsdar.gt4r.blockentity.single.BlockEntityChest;

import java.awt.Color;

public class MaterialChestRenderer <T extends BlockEntity> implements BlockEntityRenderer<T> {
    public static final ResourceLocation MATERIAL_CHEST_BASE = new ResourceLocation(GT4RRef.ID, "model/material_chest_base");
    public static final ResourceLocation MATERIAL_CHEST_OVERLAY = new ResourceLocation(GT4RRef.ID, "model/material_chest_overlay");
    private final ModelPart chestLid;
    private final ModelPart chestBottom;
    private final ModelPart chestLock;

    public MaterialChestRenderer(BlockEntityRendererProvider.Context ctx) {

        ModelPart modelpart = ctx.bakeLayer(ModelLayers.CHEST);
        this.chestBottom = modelpart.getChild("bottom");
        this.chestLid = modelpart.getChild("lid");
        this.chestLock = modelpart.getChild("lock");
    }

    private static float getLidOpenness(float delta) {
        delta = 1 - delta;
        delta = 1 - delta * delta * delta;
        return -delta * Mth.HALF_PI;
    }


    @Override
    public void render(T pBlockEntity, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pCombinedLight, int pCombinedOverlay) {
        BlockEntityChest tileEntity = (BlockEntityChest) pBlockEntity;

        Level world = tileEntity.getLevel();
        boolean flag = world != null;

        BlockState blockstate = flag ? tileEntity.getBlockState() : tileEntity.getMachineType().getBlockState(Tier.LV).defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();


        if (block instanceof BlockMaterialChest materialChest) {

            pMatrixStack.pushPose();
            float f = blockstate.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot();
            pMatrixStack.translate(0.5D, 0.5D, 0.5D);
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
            pMatrixStack.translate(-0.5D, -0.5D, -0.5D);

            DoubleBlockCombiner.NeighborCombineResult<? extends BlockEntityChest> iCallbackWrapper;
            if (flag) {
                iCallbackWrapper = materialChest.getWrapper(blockstate, world, tileEntity.getBlockPos(), true);
            } else {
                iCallbackWrapper = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = iCallbackWrapper.apply(BlockMaterialChest.getLid(tileEntity)).get(pPartialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = iCallbackWrapper.apply(new BrightnessCombiner<>()).applyAsInt(pCombinedLight);

            Material material = new Material(Sheets.CHEST_SHEET, MATERIAL_CHEST_BASE);
            VertexConsumer ivertexbuilder = material.buffer(pBuffer, RenderType::entityCutout);
            this.handleModelRender(pMatrixStack, ivertexbuilder, f1, i, pCombinedOverlay, materialChest.getBlockColor(blockstate, world, tileEntity.getBlockPos(), 0), getLidOpenness(((BlockEntityChest) pBlockEntity).getOpenNess(pPartialTicks)));

            material = new Material(Sheets.CHEST_SHEET, MATERIAL_CHEST_OVERLAY);
            ivertexbuilder = material.buffer(pBuffer, RenderType::entityCutout);

            this.handleModelRender(pMatrixStack, ivertexbuilder, f1, i, pCombinedOverlay, materialChest.getBlockColor(blockstate, world, tileEntity.getBlockPos(), 1), getLidOpenness(((BlockEntityChest) pBlockEntity).getOpenNess(pPartialTicks)));

            pMatrixStack.popPose();
        }
    }

    private void handleModelRender(PoseStack matrixStackIn, VertexConsumer iVertexBuilder, float f1, int i, int pCombinedOverlay, int color, float openness) {
        chestLid.xRot = openness;
        chestLock.xRot = chestLid.xRot;
        Color colorValue = new Color(color);
        float[] colorArray = colorValue.getRGBColorComponents(null);
        chestLid.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay, colorArray[0], colorArray[1], colorArray[2], 1.0F);
        chestLock.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay);
        chestBottom.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay, colorArray[0], colorArray[1], colorArray[2], 1.0F);
    }
}
