package trinsdar.gt4r.tree;

import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;
import java.util.Random;

public class BlockRubberLog extends BlockBasic {

    public final static EnumProperty<ResinState> RESIN_STATE = EnumProperty.create("resin_state", ResinState.class);
    public final static DirectionProperty RESIN_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public final static EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public BlockRubberLog(String domain, String id) {
        super(domain, id, Block.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).randomTicks());
        registerDefaultState(defaultBlockState().setValue(RESIN_STATE, ResinState.NONE).setValue(RESIN_FACING, Direction.NORTH).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(RESIN_STATE, RESIN_FACING, AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(RESIN_FACING, context.getHorizontalDirection().getOpposite()).setValue(RESIN_STATE, ResinState.NONE);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/tree/rubber_log")};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        ModelFile rubberLog = prov.existing(Ref.ID, "block/rubber_log");
        ModelFile rubberLogEmpty = prov.existing(Ref.ID, "block/rubber_log_empty");
        ModelFile rubberLogFilled = prov.existing(Ref.ID, "block/rubber_log_filled");
        prov.getVariantBuilder(block).forAllStates(s ->
            ConfiguredModel.builder().modelFile(s.getValue(RESIN_STATE) == ResinState.NONE ? rubberLog : s.getValue(RESIN_STATE) == ResinState.EMPTY ? rubberLogEmpty : rubberLogFilled)
                .rotationY((int) s.getValue(RESIN_FACING).getOpposite().toYRot())
                .rotationX(s.getValue(AXIS) == Direction.Axis.Y ? 0 : 90).build()
        );
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (state.getValue(RESIN_STATE) == ResinState.FILLED){
            drops.add(new ItemStack(GT4RData.StickyResin));
        }
        return drops;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getItemInHand(handIn);
        if ((stack.isEmpty() || stack.getItem() == GT4RData.StickyResin) && state.getValue(RESIN_FACING) == hit.getDirection() && state.getValue(RESIN_STATE) == ResinState.FILLED){
            state = state.setValue(RESIN_STATE, ResinState.EMPTY);
            worldIn.setBlockAndUpdate(pos, state);
            ItemStack drop = new ItemStack(GT4RData.StickyResin, 1);
            if (!player.addItem(drop)){
                player.drop(drop, true, false);
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (state.getValue(RESIN_STATE) == ResinState.EMPTY && random.nextInt(25) == 0){
            worldIn.setBlockAndUpdate(pos, state.setValue(RESIN_STATE, ResinState.FILLED));
        }
    }
}
