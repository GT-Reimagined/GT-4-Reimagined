package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.single.BlockEntityBatteryBuffer;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.data.CustomTags;

import static muramasa.antimatter.machine.Tier.*;

public class BlockEntityBatteryBox extends BlockEntityBatteryBuffer<BlockEntityBatteryBox> {
    public BlockEntityBatteryBox(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        Tier newTier = null;
        if (this.getMachineTier() == LV || this.getMachineTier() == MV){
            if (stack.is(CustomTags.TRANSFORMER_UPGRADES)){
                newTier = this.getMachineTier() == LV ? MV : HV;
            }
        }
        if (this.getMachineTier() == HV || this.getMachineTier() == EV){
            if (stack.is(CustomTags.HV_TRANSFORMER_UPGRADES)){
                newTier = this.getMachineTier() == HV ? EV : IV;
            }
        }
        if (newTier != null){
            CompoundTag nbt = new CompoundTag();
            this.saveAdditional(nbt);
            world.setBlock(pos, this.getMachineType().getBlockState(newTier).defaultBlockState().setValue(BlockStateProperties.FACING, this.getFacing()), 3);
            world.getBlockEntity(pos).load(nbt);
            world.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (!player.isCreative()){
                player.getItemInHand(hand).shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }
}
