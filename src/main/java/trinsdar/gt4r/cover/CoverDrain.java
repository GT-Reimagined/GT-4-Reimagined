package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.tile.pipe.TileEntityFluidPipe;
import muramasa.antimatter.tile.pipe.TileEntityPipe;
import muramasa.antimatter.util.Utils;
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

import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE;

public class CoverDrain extends BaseCover {
    public static String ID = "drain";

    public CoverDrain() {
        super();
        register();
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        TileEntity tile = instance.getTile();
        if (tile == null) {
            return;
        }
        if (tile.getWorld().isRemote) return;
        World world = tile.getWorld();
        LazyOptional<IFluidHandler> cap = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        if (tile instanceof TileEntityPipe){
            cap = ((TileEntityPipe<?>)tile).getCoverCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        }
        BlockPos offset = tile.getPos().offset(side);
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
        if (state.getFluid() == Fluids.EMPTY) return;
        Fluid fluid = state.getFluid();
        cap.ifPresent(f -> {
            for (int i = 0; i < f.getTanks(); i++) {
                FluidStack toInsert = new FluidStack(fluid, 1000);
                int filled = f.fill(toInsert, EXECUTE);
                if (filled > 0) {
                    if (fluid != Fluids.WATER || (!BiomeDictionary.hasType(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, world.getBiome(offset).getRegistryName()), BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, world.getBiome(offset).getRegistryName()), BiomeDictionary.Type.RIVER))){
                        BlockState newState = Blocks.AIR.getDefaultState();
                        if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.get(BlockStateProperties.WATERLOGGED)){
                            newState = blockState.with(BlockStateProperties.WATERLOGGED, false);
                        }
                        world.setBlockState(offset, newState);
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
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
