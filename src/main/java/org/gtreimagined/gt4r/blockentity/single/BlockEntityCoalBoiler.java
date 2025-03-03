package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.machine.CoalBoilerFluidHandler;
import org.gtreimagined.gt4r.machine.CoalBoilerRecipeHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockEntityCoalBoiler extends BlockEntityMachine<BlockEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;
    public BlockEntityCoalBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new CoalBoilerFluidHandler(this));
        this.recipeHandler.set(() -> new CoalBoilerRecipeHandler(this));
    }

    public int getFuel() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getFuel());
        });
        return v.get();
    }

    public int getHeat() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getHeat());
        });
        return v.get();
    }

    public int getMaxFuel() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getMaxFuel());
        });
        return v.get();
    }

    public int getMaxHeat() {
        AtomicInteger v = new AtomicInteger();
        recipeHandler.ifPresent(r -> {
            v.set(((CoalBoilerRecipeHandler) r).getMaxHeat());
        });
        return v.get();
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (player.getItemInHand(hand).getItem() == GT4RItems.SteelUpgrade){
            CompoundTag nbt = new CompoundTag();
            this.saveAdditional(nbt);
            world.setBlock(pos, this.getMachineType().getBlockState(Tier.STEEL).defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, this.getFacing()), 3);
            world.getBlockEntity(pos).load(nbt);
            if (!player.isCreative()){
                player.getItemInHand(hand).shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }
}
