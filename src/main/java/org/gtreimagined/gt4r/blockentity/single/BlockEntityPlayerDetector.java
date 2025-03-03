package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.cover.IHaveCover;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BlockEntityPlayerDetector extends BlockEntityMachine<BlockEntityPlayerDetector> {
    boolean redstone = false;
    PlayerDetectorType detectorType = PlayerDetectorType.ALL;
    private String ownerUdid = "";
    public BlockEntityPlayerDetector(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void setOwner(@Nullable LivingEntity owner) {
        if (owner == null) return;
        this.ownerUdid = owner.getStringUUID();
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (level == null || level.isClientSide) {
            return;
        }

        if (level.getGameTime() % 20 != 0) {
            return;
        }
        MachineEnergyHandler<?> energy =  energyHandler.map(e -> e).orElse(null);
        if (energy == null) return;
        boolean lastRedstone = redstone;
        redstone = false;
        if (energy.getEnergy() >= 3){
            for (Player player : level.players()) {
                if (Mth.sqrt((float)player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) <= 16f ) {
                    if (detectorType == PlayerDetectorType.ALL) {// ALL
                        redstone = true;
                    } else if (detectorType == PlayerDetectorType.OTHERS) {// Others
                        if (!ownerUdid.isEmpty() && !ownerUdid.equals(player.getStringUUID())) {
                            redstone = true;
                        }
                    } else {// You
                        if (!ownerUdid.isEmpty() && ownerUdid.equals(player.getStringUUID())) {
                            redstone = true;
                        }
                    }
                }
            }
            energy.extractEu(3, false);
        }
        if (lastRedstone != redstone) {
            this.setMachineState(redstone ? MachineState.ACTIVE : MachineState.IDLE);
            updateBlock(level, pos);
            level.updateNeighborsAt(pos, level.getBlockState(pos).getBlock());
        }
    }

    private static void updateBlock(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        level.sendBlockUpdated(pos, state, state, 3);
    }

    @SuppressWarnings("NoTranslation")
    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.isEmpty() || !(stack.getItem() instanceof IAntimatterTool || stack.getItem() instanceof IHaveCover)){
            this.detectorType = detectorType.next();
            if (!ownerUdid.isEmpty()) {
                Player owner = world.getPlayerByUUID(UUID.fromString(ownerUdid));
                if (owner != null){
                    switch (detectorType){
                        case YOU -> player.displayClientMessage(new TranslatableComponent("message.gt4r.player_detector.only", owner.getDisplayName()), false);
                        case OTHERS -> player.displayClientMessage(new TranslatableComponent("message.gt4r.player_detector.other", owner.getDisplayName()), false);
                        case ALL -> player.displayClientMessage(new TranslatableComponent("message.gt4r.player_detector.all"), false);
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ownerUdid = tag.getString("ownerID");
        detectorType = PlayerDetectorType.values()[tag.getInt("detectorType")];
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("ownerID", ownerUdid);
        tag.putInt("detectorType", detectorType.ordinal());
    }

    public int getWeakRedstonePower(Direction facing) {
        if (redstone) return 15;
        return super.getWeakRedstonePower(facing);
    }

    public int getStrongRedstonePower(Direction facing) {
        if (redstone) return 15;
        return super.getStrongRedstonePower(facing);
    }

    public enum PlayerDetectorType {
        ALL, OTHERS, YOU;
        public PlayerDetectorType next(){
            return switch (this){
                case ALL -> YOU;
                case YOU -> OTHERS;
                case OTHERS -> ALL;
            };
        }
    }
}
