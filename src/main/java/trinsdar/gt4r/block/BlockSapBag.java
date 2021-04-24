package trinsdar.gt4r.block;

import com.google.common.collect.ImmutableList;
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

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;
import static net.minecraft.util.Direction.*;

public class BlockSapBag  extends BlockDynamic implements IWaterLoggable {
    protected ModelConfig config = new ModelConfig();
    final VoxelShape[] SHAPES;
    final Texture[] TEXTURES;
    public BlockSapBag() {
        super(Ref.ID, "sap_bag", Block.Properties.create(Material.ORGANIC, MaterialColor.RED_TERRACOTTA).notSolid().hardnessAndResistance(0.5F).sound(SoundType.CLOTH));
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
        return SHAPES[state.get(HORIZONTAL_FACING).getHorizontalIndex()];
    }

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        TileEntitySapBag tile = (TileEntitySapBag) world.getTileEntity(pos);
        int filled = tile != null ? (tile.getSap().isEmpty() || tile.getSap().getCount() == 0 ? 0 : tile.getSap().getCount() < 11 ? 1 : tile.getSap().getCount() < 21 ? 2 : tile.getSap().getCount() < 31 ? 3 : tile.getSap().getCount() < 41 ? 4 : tile.getSap().getCount() < 51 ? 5 : 6 ) : 0;
        return config.set(new int[]{getModelId(state.get(HORIZONTAL_FACING), filled)});
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) { //Y = 0 , reduce to xz plane
            Direction dir = getFacingFromVector((float) placer.getLookVec().x, (float) 0, (float) placer.getLookVec().z);
            world.setBlockState(pos, state.with(HORIZONTAL_FACING, dir));
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntitySapBag){
                ((TileEntitySapBag)tile).setFacing(dir);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntitySapBag){
            ((TileEntitySapBag)tile).onBlockUpdate();
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntitySapBag){
            TileEntitySapBag sapBag = (TileEntitySapBag) tile;
            if (!sapBag.getSap().isEmpty()){
                if (!player.addItemStackToInventory(sapBag.getSap().copy())){
                    player.dropItem(sapBag.getSap().copy(), false);
                }
                sapBag.setSap(ItemStack.EMPTY);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = super.getDrops(state, builder);
        TileEntity tileentity = builder.get(LootParameters.BLOCK_ENTITY);
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
                .modelFile(buildModelsForState(prov.getBuilder(block)))
                .uvLock(true)
                .build()
        );
    }

    private int getModelId(Direction facing, int filled) {
        return facing.getHorizontalIndex() +  (filled * 10);
    }

    AntimatterBlockModelBuilder buildModelsForState(AntimatterBlockModelBuilder builder) {
        builder.staticConfigId("sap_bag");
        for (Direction f : Arrays.asList(NORTH, WEST, SOUTH, EAST)) {
            for (int i = 0; i < 7; i++){
                String addition = i == 0 ? "" : "_filled_" + i;
                int finalI = i;
                builder.config(getModelId(f, i), (b, l) -> {
                    DynamicConfigBuilder build = b.of(new ResourceLocation(domain, "block/sapbag/" + f.getString() + addition)).tex(of("side", TEXTURES[2], "bottom", TEXTURES[0], "top", TEXTURES[1]));
                    if (finalI != 0){
                        build.tex(TEXTURES[3]);
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
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
