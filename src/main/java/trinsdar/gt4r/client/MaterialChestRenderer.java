package trinsdar.gt4r.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import muramasa.antimatter.machine.Tier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockMaterialChest;
import trinsdar.gt4r.tile.single.TileEntityChest;

import java.awt.Color;

public class MaterialChestRenderer <T extends TileEntity> extends TileEntityRenderer<T> {
    public static final ResourceLocation MATERIAL_CHEST_BASE = new ResourceLocation(Ref.ID, "model/material_chest_base");
    public static final ResourceLocation MATERIAL_CHEST_OVERLAY = new ResourceLocation(Ref.ID, "model/material_chest_overlay");
    private final ModelRenderer chestLid;
    private final ModelRenderer chestBottom;
    private final ModelRenderer chestLock;

    public MaterialChestRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);

        this.chestBottom = new ModelRenderer(64, 64, 0, 19);
        this.chestBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.chestLid = new ModelRenderer(64, 64, 0, 0);
        this.chestLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.chestLid.y = 9.0F;
        this.chestLid.z = 1.0F;
        this.chestLock = new ModelRenderer(64, 64, 0, 0);
        this.chestLock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.chestLock.y = 8.0F;
    }

    @Override
    public void render(T pBlockEntity, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay) {
        TileEntityChest tileEntity = (TileEntityChest) pBlockEntity;

        World world = tileEntity.getLevel();
        boolean flag = world != null;

        BlockState blockstate = flag ? tileEntity.getBlockState() : tileEntity.getMachineType().getBlockState(Tier.LV).defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();


        if (block instanceof BlockMaterialChest) {
            BlockMaterialChest materialChest = (BlockMaterialChest) block;

            pMatrixStack.pushPose();
            float f = blockstate.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot();
            pMatrixStack.translate(0.5D, 0.5D, 0.5D);
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
            pMatrixStack.translate(-0.5D, -0.5D, -0.5D);

            TileEntityMerger.ICallbackWrapper<? extends TileEntityChest> iCallbackWrapper;
            if (flag) {
                iCallbackWrapper = materialChest.getWrapper(blockstate, world, tileEntity.getBlockPos(), true);
            } else {
                iCallbackWrapper = TileEntityMerger.ICallback::acceptNone;
            }

            float f1 = iCallbackWrapper.apply(BlockMaterialChest.getLid(tileEntity)).get(pPartialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = iCallbackWrapper.apply(new DualBrightnessCallback<>()).applyAsInt(pCombinedLight);

            RenderMaterial material = new RenderMaterial(Atlases.CHEST_SHEET, MATERIAL_CHEST_BASE);
            IVertexBuilder ivertexbuilder = material.buffer(pBuffer, RenderType::entityCutout);

            this.handleModelRender(pMatrixStack, ivertexbuilder, this.chestLid, this.chestLock, this.chestBottom, f1, i, pCombinedOverlay, materialChest.getBlockColor(blockstate, world, tileEntity.getBlockPos(), 0));

            material = new RenderMaterial(Atlases.CHEST_SHEET, MATERIAL_CHEST_OVERLAY);
            ivertexbuilder = material.buffer(pBuffer, RenderType::entityCutout);

            this.handleModelRender(pMatrixStack, ivertexbuilder, this.chestLid, this.chestLock, this.chestBottom, f1, i, pCombinedOverlay, materialChest.getBlockColor(blockstate, world, tileEntity.getBlockPos(), 1));

            pMatrixStack.popPose();
        }
    }

    private void handleModelRender(MatrixStack matrixStackIn, IVertexBuilder iVertexBuilder, ModelRenderer firstModel, ModelRenderer secondModel, ModelRenderer thirdModel, float f1, int i, int pCombinedOverlay, int color) {
        firstModel.xRot = -(f1 * ((float) Math.PI / 2F));
        secondModel.xRot = firstModel.xRot;
        Color colorValue = new Color(color);
        float[] colorArray = colorValue.getRGBColorComponents(null);
        firstModel.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay, colorArray[0], colorArray[1], colorArray[2], 1.0F);
        secondModel.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay);
        thirdModel.render(matrixStackIn, iVertexBuilder, i, pCombinedOverlay, colorArray[0], colorArray[1], colorArray[2], 1.0F);
    }
}
