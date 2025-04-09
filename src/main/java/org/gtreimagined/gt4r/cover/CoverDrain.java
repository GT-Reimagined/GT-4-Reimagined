package org.gtreimagined.gt4r.cover;

import org.gtreimagined.gtlib.blockentity.pipe.BlockEntityFluidPipe;
import org.gtreimagined.gtlib.capability.ICoverHandler;
import org.gtreimagined.gtlib.cover.BaseCover;
import org.gtreimagined.gtlib.cover.CoverFactory;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.util.FluidUtils;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.GT4RRef;

public class CoverDrain extends BaseCover {
    public static String ID = "drain";

    FluidStack contained = FluidStack.EMPTY;
    boolean receivedBlockUpdate = false;

    public CoverDrain(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public String getDomain() {
        return GT4RRef.ID;
    }

    @Override
    public void onUpdate() {
        BlockEntity tile = handler.getTile();
        if (tile == null) {
            return;
        }
        if (tile.getLevel().isClientSide) return;
        Level world = tile.getLevel();
        LazyOptional<IFluidHandler> cap = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        if (tile instanceof BlockEntityFluidPipe pipe){
            cap = pipe.getPipeCapHolder().side(side);
        }
        BlockPos offset = tile.getBlockPos().relative(side);
        if (side == Direction.UP && world.isRainingAt(offset) && world.getGameTime() % 60 == 0){
            cap.ifPresent(f -> {
                FluidStack toInsert = new FluidStack(Fluids.WATER, 4);
                f.fill(toInsert, FluidAction.EXECUTE);
            });
        }
        if (!contained.isEmpty()){
            cap.ifPresent(f ->{
                int filled = f.fill(contained.copy(), FluidAction.SIMULATE);
                if (filled > 0) {
                    f.fill(Utils.ca(filled, contained), FluidAction.EXECUTE);
                    contained.setAmount(contained.getAmount() - filled);
                    if (contained.getAmount() <= 0){
                        contained = FluidStack.EMPTY;
                    }
                }
            });
        }
        if (!(receivedBlockUpdate || world.getGameTime() % (20) == 5)) {
            return;
        }
        if (!contained.isEmpty()){
            if (!receivedBlockUpdate) receivedBlockUpdate = true;
            return;
        }
        BlockState blockState = world.getBlockState(offset);
        FluidState state = world.getFluidState(offset);
        if (state.getType() == Fluids.EMPTY || !state.getType().isSource(state)) return;
        Fluid fluid = state.getType();
        contained = new FluidStack(fluid, 1000);
        Holder<Biome> biome = world.getBiome(offset);
        if (fluid != Fluids.WATER || (!biome.is(BiomeTags.IS_DEEP_OCEAN) && !biome.is(BiomeTags.IS_OCEAN) && !biome.is(BiomeTags.IS_RIVER))){
            BlockState newState = Blocks.AIR.defaultBlockState();
            if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
                newState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
            }
            world.setBlockAndUpdate(offset, newState);
        }
        if (receivedBlockUpdate) receivedBlockUpdate = false;
    }

    @Override
    public void onBlockUpdate() {
        super.onBlockUpdate();
        receivedBlockUpdate = true;
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

    @Override
    public ItemStack getDroppedStack() {
        ItemStack stack = super.getDroppedStack();
        if (!contained.isEmpty()){
            stack.getOrCreateTag().put("containedFluid", contained.writeToNBT(new CompoundTag()));
        }
        return stack;
    }

    @Override
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
        if (stack.getTag() != null && stack.getTag().contains("containedFluid")){
            contained = FluidUtils.fromTag(stack.getTag().getCompound("containedFluid"));
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag tag = super.serialize();
        if (!contained.isEmpty()){
            tag.put("contained", contained.writeToNBT(new CompoundTag()));
        }
        return tag;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("contained")){
            contained = FluidUtils.fromTag(nbt.getCompound("contained"));
        }
    }
}
