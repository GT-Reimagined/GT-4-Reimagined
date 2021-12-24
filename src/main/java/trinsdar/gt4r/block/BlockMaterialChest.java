package trinsdar.gt4r.block;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.tile.single.TileEntityCabinet;
import trinsdar.gt4r.tile.single.TileEntityChest;

import java.util.List;
import java.util.function.BiPredicate;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class BlockMaterialChest extends BlockMachineMaterial implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    public BlockMaterialChest(Machine<?> type, Tier tier) {
        super(type, tier);
    }


    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isPathfindable(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType) {
        return false;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState pState) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return AABB;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pBlockState, World pLevel, BlockPos pPos) {
        TileEntity tile = pLevel.getBlockEntity(pPos);
        if (tile instanceof TileEntityChest){
            return Container.getRedstoneSignalFromContainer((TileEntityChest) tile);
        }
        return 0;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(WATERLOGGED);
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        ItemModelBuilder b = prov.getBuilder(item).parent(prov.existing(Ref.ID, "block/material_chest"));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public boolean triggerEvent(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.triggerEvent(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity != null && tileentity.triggerEvent(id, param);
    }

    @OnlyIn(Dist.CLIENT)
    public static TileEntityMerger.ICallback<TileEntityChest, Float2FloatFunction> getLid(final IChestLid p_226917_0_) {
        return new TileEntityMerger.ICallback<TileEntityChest, Float2FloatFunction>() {
            @Override
            public Float2FloatFunction acceptDouble(TileEntityChest p_225539_1_, TileEntityChest p_225539_2_) {
                return (p_226921_2_) -> {
                    return Math.max(p_225539_1_.getOpenNess(p_226921_2_), p_225539_2_.getOpenNess(p_226921_2_));
                };
            }

            @Override
            public Float2FloatFunction acceptSingle(TileEntityChest p_225538_1_) {
                return p_225538_1_::getOpenNess;
            }

            @Override
            public Float2FloatFunction acceptNone() {
                return p_226917_0_::getOpenNess;
            }
        };
    }

    public TileEntityMerger.ICallbackWrapper<? extends TileEntityChest> getWrapper(BlockState blockState, World world, BlockPos blockPos, boolean p_225536_4_) {
        BiPredicate<IWorld, BlockPos> biPredicate;
        if (p_225536_4_) {
            biPredicate = (p_226918_0_, p_226918_1_) -> false;
        }
        else {
            biPredicate = TileEntityChest::isChestBlockedAt;
        }

        return TileEntityMerger.combineWithNeigbour(((TileEntityType<TileEntityChest>)this.getType().getTileType()), BlockMaterialChest::getMergerType, BlockMaterialChest::getDirectionToAttached, BlockStateProperties.HORIZONTAL_FACING, blockState, world, blockPos, biPredicate);
    }

    public static TileEntityMerger.Type getMergerType(BlockState blockState) {
        return TileEntityMerger.Type.SINGLE;
    }

    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return direction.getCounterClockWise();
    }
}
