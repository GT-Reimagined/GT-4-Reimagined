package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.pipe.TileEntityPipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE;

public class CoverDrain extends BaseCover {
    public static String ID = "drain";

    public CoverDrain(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public void onUpdate() {
        TileEntity tile = handler.getTile();
        if (tile == null) {
            return;
        }
        if (tile.getLevel().isClientSide) return;
        World world = tile.getLevel();
        LazyOptional<IFluidHandler> cap = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        if (tile instanceof TileEntityPipe){
            cap = ((TileEntityPipe<?>)tile).getCoverCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        }
        BlockPos offset = tile.getBlockPos().relative(side);
        if (side == Direction.UP && world.isRainingAt(offset) && world.getGameTime() % 60 == 0){
            cap.ifPresent(f -> {
                for (int i = 0; i < f.getTanks(); i++) {
                    FluidStack toInsert = new FluidStack(Fluids.WATER, 4);
                    int filled = f.fill(toInsert, EXECUTE);
                    if (filled > 0) {
                        break;
                    }
                }
            });
        }
        if (world.getGameTime() % (20) != 0) {
            return;
        }
        BlockState blockState = world.getBlockState(offset);
        FluidState state = world.getFluidState(offset);
        if (state.getType() == Fluids.EMPTY) return;
        Fluid fluid = state.getType();
        cap.ifPresent(f -> {
            for (int i = 0; i < f.getTanks(); i++) {
                FluidStack toInsert = new FluidStack(fluid, 1000);
                int filled = f.fill(toInsert, SIMULATE);
                if (filled > 0) {
                    f.fill(new FluidStack(toInsert.getFluid(), filled), EXECUTE);
                    if (fluid != Fluids.WATER || (!BiomeDictionary.hasType(RegistryKey.create(Registry.BIOME_REGISTRY, world.getBiome(offset).getRegistryName()), BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(RegistryKey.create(Registry.BIOME_REGISTRY, world.getBiome(offset).getRegistryName()), BiomeDictionary.Type.RIVER))){
                        BlockState newState = Blocks.AIR.defaultBlockState();
                        if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
                            newState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
                        }
                        world.setBlockAndUpdate(offset, newState);
                    }
                    break;
                }
            }
        });
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
