package trinsdar.gt4r.tree;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import trinsdar.gt4r.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;
import java.util.Random;

public class BlockRubberLog extends BlockBasic {

    final static EnumProperty<ResinState> RESIN_STATE = EnumProperty.create("resin_state", ResinState.class);
    final static DirectionProperty RESIN_FACING = BlockStateProperties.HORIZONTAL_FACING;
    final static EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public BlockRubberLog(String domain, String id) {
        super(domain, id, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD).tickRandomly());
        setDefaultState(getDefaultState().with(RESIN_STATE, ResinState.NONE).with(RESIN_FACING, Direction.NORTH).with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(RESIN_STATE, RESIN_FACING, AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(AXIS, context.getFace().getAxis()).with(RESIN_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(RESIN_STATE, ResinState.NONE);
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
            ConfiguredModel.builder().modelFile(s.get(RESIN_STATE) == ResinState.NONE ? rubberLog : s.get(RESIN_STATE) == ResinState.EMPTY ? rubberLogEmpty : rubberLogFilled)
                .rotationY((int) s.get(RESIN_FACING).getOpposite().getHorizontalAngle())
                .rotationX(s.get(AXIS) == Direction.Axis.Y ? 0 : 90).build()
        );
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (state.get(RESIN_STATE) == ResinState.FILLED){
            drops.add(new ItemStack(GT4RData.StickyResin));
        }
        return drops;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if (stack.getItem() == GT4RData.Treetap && state.get(RESIN_FACING) == hit.getFace() && state.get(RESIN_STATE) == ResinState.FILLED){
            stack.damageItem(1, player, playerEntity -> {});
            state = state.with(RESIN_STATE, ResinState.EMPTY);
            worldIn.setBlockState(pos, state);
            ItemStack drop = new ItemStack(GT4RData.StickyResin, worldIn.rand.nextInt(3) + 1);
            if (!player.addItemStackToInventory(drop)){
                player.dropItem(drop, true, false);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (state.get(RESIN_STATE) == ResinState.EMPTY && random.nextInt(50) == 0){
            worldIn.setBlockState(pos, state.with(RESIN_STATE, ResinState.FILLED));
        }
    }
}
