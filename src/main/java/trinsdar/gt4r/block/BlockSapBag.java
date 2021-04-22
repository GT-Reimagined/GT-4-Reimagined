package trinsdar.gt4r.block;

import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.dynamic.BlockDynamic;
import muramasa.antimatter.dynamic.ModelConfig;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.client.BakedModels;

import javax.annotation.Nullable;

import java.util.Arrays;

import static com.google.common.collect.ImmutableMap.of;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;
import static net.minecraft.util.Direction.*;

public class BlockSapBag  extends BlockDynamic implements IWaterLoggable {
    protected ModelConfig config = new ModelConfig();
    final VoxelShape[] SHAPES;
    final Texture[] TEXTURES;
    public BlockSapBag() {
        super(Ref.ID, "sap_bag", Block.Properties.create(Material.ORGANIC, MaterialColor.RED_TERRACOTTA).notSolid());
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
        return config.set(new int[]{getModelId(state.get(HORIZONTAL_FACING), 0)});
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

    /*@Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) { //Y = 0 , reduce to xz plane
            Direction dir = getFacingFromVector((float) placer.getLookVec().x, (float) 0, (float) placer.getLookVec().z);
            world.setBlockState(pos, state.with(HORIZONTAL_FACING, dir));
        }
    }*/

    /*@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEntityMachine machine = (TileEntityMachine)getType().getTileType().create();
        machine.ofState(state);
        return machine;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }*/

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
            builder.config(getModelId(f, 0), (b, l) -> l.add(b.of(new ResourceLocation(domain, "block/sapbag/" + f.getString())).tex(of("side", TEXTURES[2], "bottom", TEXTURES[0], "top", TEXTURES[1]))));
        }
        builder.property("particle", TEXTURES[2].toString());
        return builder.loader(BakedModels.LOADER_SAP_BAG);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
