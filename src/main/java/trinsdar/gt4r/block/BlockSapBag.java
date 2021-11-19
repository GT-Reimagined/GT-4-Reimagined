package trinsdar.gt4r.block;

import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.builder.DynamicConfigBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.dynamic.BlockDynamic;
import muramasa.antimatter.dynamic.ModelConfig;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.client.BakedModels;
import trinsdar.gt4r.tile.TileEntityTypes;
import trinsdar.gt4r.tile.single.TileEntitySapBag;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;
import static net.minecraft.util.Direction.getNearest;

public class BlockSapBag  extends BlockDynamic implements IWaterLoggable {
    protected ModelConfig config = new ModelConfig();
    final VoxelShape[] SHAPES;
    final Texture[] TEXTURES;
    public BlockSapBag() {
        super(Ref.ID, "sap_bag", Block.Properties.of(Material.GRASS, MaterialColor.TERRACOTTA_RED).noOcclusion().strength(0.5F).sound(SoundType.WOOL));
        SHAPES = setBlockBounds2();
        TEXTURES = new Texture[]{new Texture(Ref.ID, "block/sapbag/bottom"), new Texture(Ref.ID, "block/sapbag/top"), new Texture(Ref.ID, "block/sapbag/side"), new Texture(Ref.ID, "block/sapbag/top_filled")};
    }

    private VoxelShape[] setBlockBounds2() {
        AxisAlignedBB north = new AxisAlignedBB(0.3125F, 0, 0, 0.6875, 0.4375, 0.375);
        AxisAlignedBB south = new AxisAlignedBB(0.3125F, 0, 0.625F, 0.6875, 0.4375, 1);
        AxisAlignedBB west = new AxisAlignedBB(0, 0, 0.3125F, 0.375, 0.4375, 0.6875);
        AxisAlignedBB east = new AxisAlignedBB(0.625F, 0, 0.3125F, 1, 0.4375, 0.6875);
        return new VoxelShape[]{VoxelShapes.create(south), VoxelShapes.create(west), VoxelShapes.create(north), VoxelShapes.create(east)};
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.getValue(HORIZONTAL_FACING).get2DDataValue()];
    }

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        TileEntitySapBag tile = (TileEntitySapBag) world.getBlockEntity(pos);
        int filled = tile != null ? (tile.getSap().isEmpty() || tile.getSap().getCount() == 0 ? 0 : tile.getSap().getCount() < 11 ? 1 : tile.getSap().getCount() < 21 ? 2 : tile.getSap().getCount() < 31 ? 3 : tile.getSap().getCount() < 41 ? 4 : tile.getSap().getCount() < 51 ? 5 : 6 ) : 0;
        return config.set(new int[]{getModelId(state.getValue(HORIZONTAL_FACING), filled)});
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) { //Y = 0 , reduce to xz plane
            Direction dir = getNearest((float) placer.getLookAngle().x, (float) 0, (float) placer.getLookAngle().z);
            world.setBlockAndUpdate(pos, state.setValue(HORIZONTAL_FACING, dir));
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof TileEntitySapBag){
                ((TileEntitySapBag)tile).setFacing(dir);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        TileEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof TileEntitySapBag){
            ((TileEntitySapBag)tile).onBlockUpdate();
        }
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof TileEntitySapBag){
            TileEntitySapBag sapBag = (TileEntitySapBag) tile;
            if (!sapBag.getSap().isEmpty()){
                if (!player.addItem(sapBag.getSap().copy())){
                    player.drop(sapBag.getSap().copy(), false);
                }
                sapBag.setSap(ItemStack.EMPTY);
                sapBag.onFirstTick();
                return ActionResultType.SUCCESS;
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = super.getDrops(state, builder);
        TileEntity tileentity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof TileEntitySapBag){
            ItemStack stack = ((TileEntitySapBag)tileentity).getSap();
            if (!stack.isEmpty()){
                list.add(stack);
            }
        }
        return list;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.SAP_BAG_TYPE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        ItemModelBuilder b = prov.getBuilder(item).parent(prov.existing(Ref.ID, "block/sapbag/north")).texture("side", TEXTURES[2]).texture("bottom", TEXTURES[0]).texture("top", TEXTURES[1]);
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder()
                .modelFile(buildModelsForState(prov.getBuilder(block), s))
                .uvLock(true)
                .build()
        );
    }

    private int getModelId(Direction facing, int filled) {
        return facing.get2DDataValue() +  (filled * 10);
    }

    AntimatterBlockModelBuilder buildModelsForState(AntimatterBlockModelBuilder builder, BlockState state) {
        builder.staticConfigId("sap_bag");
        Direction f = state.getValue(HORIZONTAL_FACING);
        boolean waterlogged = state.getValue(WATERLOGGED);
        if (!waterlogged){
            for (int i = 0; i < 7; i++){
                String addition = i == 0 ? "" : "_filled_" + i;
                int finalI = i;
                builder.config(getModelId(f, i), (b, l) -> {
                    DynamicConfigBuilder build = b.of(new ResourceLocation(domain, "block/sapbag/" + f.getSerializedName() + addition)).tex(of("side", TEXTURES[2], "bottom", TEXTURES[0], "top", TEXTURES[1]));
                    if (finalI != 0){
                        build.tex(of("side", TEXTURES[2], "bottom", TEXTURES[0], "top", TEXTURES[1],"topfilled", TEXTURES[3]));
                    }
                    return l.add(build);
                });
            }
        }
        builder.property("particle", TEXTURES[2].toString());
        return builder.loader(BakedModels.LOADER_SAP_BAG);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
