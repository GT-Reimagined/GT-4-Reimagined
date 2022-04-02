package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityLocker extends TileEntityMaterial<TileEntityLocker> {
    public TileEntityLocker(MaterialMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        coverHandler.set(() -> new MachineCoverHandler<TileEntityLocker>(this){
            @Override
            public boolean placeCover(Player player, Direction side, ItemStack stack, ICover cover) {
                return false;
            }
        });
    }

    @Override
    public InteractionResult onInteract(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (player.getItemInHand(hand).isEmpty() && hit.getDirection() == this.getFacing()){
            this.itemHandler.ifPresent(h -> {
                for (int i = 0; i < 4; i++){
                    ItemStack armorStack = player.getItemBySlot(getSlot(i));
                    ItemStack inventoryStack = h.getHandler(SlotType.STORAGE).getStackInSlot(i);
                    if (!armorStack.isEmpty() && !inventoryStack.isEmpty()){
                        ItemStack copy = armorStack.copy();
                        ItemStack copy1 = inventoryStack.copy();
                        armorStack.shrink(armorStack.getCount());
                        inventoryStack.shrink(inventoryStack.getCount());
                        player.setItemSlot(getSlot(i), copy1);
                        h.getHandler(SlotType.STORAGE).setStackInSlot(i, copy);
                    } else if (!armorStack.isEmpty()){
                        h.getHandler(SlotType.STORAGE).setStackInSlot(i, armorStack.copy());
                        armorStack.shrink(armorStack.getCount());
                    } else if (!inventoryStack.isEmpty()){
                        player.setItemSlot(getSlot(i), inventoryStack.copy());
                        inventoryStack.shrink(inventoryStack.getCount());
                    }
                }
            });
            world.playSound(null, this.getBlockPos(), SoundEvents.UI_BUTTON_CLICK, SoundSource.PLAYERS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return super.onInteract(state, world, pos, player, hand, hit, type);
    }

    @Override
    public boolean canPlayerOpenGui(Player player) {
        return player.isCreative();
    }

    @Override
    public <V> boolean blocksCapability(@Nonnull Capability<V> cap, Direction side) {
        return super.blocksCapability(cap, side) || cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    private EquipmentSlot getSlot(int slot){
        if (slot == 0){
            return EquipmentSlot.HEAD;
        } else if (slot == 1){
            return EquipmentSlot.CHEST;
        } else if (slot == 2){
            return EquipmentSlot.LEGS;
        } else {
            return EquipmentSlot.FEET;
        }
    }
}
