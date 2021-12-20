package trinsdar.gt4r.block;

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
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import trinsdar.gt4r.tile.single.TileEntityCabinet;

import java.util.List;

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

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    public static boolean isChestBlockedAt(IWorld p_220108_0_, BlockPos p_220108_1_) {
        return isBlockedChestByBlock(p_220108_0_, p_220108_1_) || isCatSittingOnChest(p_220108_0_, p_220108_1_);
    }

    private static boolean isBlockedChestByBlock(IBlockReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return pLevel.getBlockState(blockpos).isRedstoneConductor(pLevel, blockpos);
    }

    private static boolean isCatSittingOnChest(IWorld pLevel, BlockPos pPos) {
        List<CatEntity> list = pLevel.getEntitiesOfClass(CatEntity.class, new AxisAlignedBB((double)pPos.getX(), (double)(pPos.getY() + 1), (double)pPos.getZ(), (double)(pPos.getX() + 1), (double)(pPos.getY() + 2), (double)(pPos.getZ() + 1)));
        if (!list.isEmpty()) {
            for(CatEntity catentity : list) {
                if (catentity.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState pBlockState, World pLevel, BlockPos pPos) {
        TileEntity tile = pLevel.getBlockEntity(pPos);
        if (tile instanceof TileEntityCabinet){
            return Container.getRedstoneSignalFromContainer(((TileEntityCabinet) tile).getContents());
        }
        return 0;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(WATERLOGGED);
    }
}
