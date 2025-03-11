package org.gtreimagined.gt4r.reactor.tile;

import lombok.Getter;
import muramasa.antimatter.blockentity.BlockEntityFakeBlock;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkHooks;
import org.gtreimagined.gt4r.data.Machines;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEntityReactorChamber extends BlockEntityMachine<BlockEntityReactorChamber> {
    @Getter
    private BlockEntityReactorCore reactor;
    private Direction reactorSide;

    public BlockEntityReactorChamber(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void onFirstTickServer(Level level, BlockPos pos, BlockState state) {
        super.onFirstTickServer(level, pos, state);
        if (reactor == null) {
            for (var d : Direction.values()) {
                BlockEntity be = level.getBlockEntity(pos.relative(d));
                if (be instanceof BlockEntityReactorCore core){
                    setReactor(core);
                    reactorSide = d;
                    break;
                }
            }
        }
    }

    @Override
    public void onFirstTickClient(Level level, BlockPos pos, BlockState state) {
        super.onFirstTickClient(level, pos, state);
        if (reactor == null) {
            for (var d : Direction.values()) {
                BlockEntity be = level.getBlockEntity(pos.relative(d));
                if (be instanceof BlockEntityReactorCore core){
                    reactor = core;
                    reactorSide = d;
                    break;
                }
            }
        }
    }

    @Override
    public @NotNull <U> LazyOptional<U> getCapability(@NotNull Capability<U> cap, @Nullable Direction side) {
        if (reactor != null) {
            return reactor.getCapability(cap, side);
        }
        return super.getCapability(cap, side);
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (reactor != null && type == null) {
            if (!world.isClientSide){
                NetworkHooks.openGui((ServerPlayer) player, reactor, extra -> {
                    extra.writeBlockPos(reactor.getBlockPos());
                });
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    public void setReactor(BlockEntityReactorCore reactor) {
        this.reactor = reactor;
        if (this.level != null && isServerSide()) {
            sidedSync(true);
        }
    }
}
