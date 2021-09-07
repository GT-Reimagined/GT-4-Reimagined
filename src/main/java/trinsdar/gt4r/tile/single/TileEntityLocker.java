package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityLocker extends TileEntityMaterial<TileEntityLocker> {
    public TileEntityLocker(MaterialMachine type) {
        super(type);
        coverHandler.set(() -> new MachineCoverHandler<TileEntityLocker>(this){
            @Override
            public boolean placeCover(PlayerEntity player, Direction side, ItemStack stack, ICover cover) {
                return false;
            }
        });
    }

    @Override
    public ActionResultType onInteract(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit, @Nullable AntimatterToolType type) {
        if (player.getHeldItem(hand).isEmpty() && hit.getFace() == this.getFacing()){
            this.itemHandler.ifPresent(h -> {
                for (int i = 0; i < 4; i++){
                    ItemStack armorStack = player.getItemStackFromSlot(getSlot(i));
                    ItemStack inventoryStack = h.getHandler(SlotType.STORAGE).getStackInSlot(i);
                    if (!armorStack.isEmpty() && !inventoryStack.isEmpty()){
                        ItemStack copy = armorStack.copy();
                        ItemStack copy1 = inventoryStack.copy();
                        armorStack.shrink(armorStack.getCount());
                        inventoryStack.shrink(inventoryStack.getCount());
                        player.setItemStackToSlot(getSlot(i), copy1);
                        h.getHandler(SlotType.STORAGE).setStackInSlot(i, copy);
                    } else if (!armorStack.isEmpty()){
                        h.getHandler(SlotType.STORAGE).setStackInSlot(i, armorStack.copy());
                        armorStack.shrink(armorStack.getCount());
                    } else if (!inventoryStack.isEmpty()){
                        player.setItemStackToSlot(getSlot(i), inventoryStack.copy());
                        inventoryStack.shrink(inventoryStack.getCount());
                    }
                }
            });
            world.playSound(null, this.getPos(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return ActionResultType.SUCCESS;
        }
        return super.onInteract(state, world, pos, player, hand, hit, type);
    }

    @Override
    public boolean canPlayerOpenGui(PlayerEntity player) {
        return player.isCreative();
    }

    @Override
    public <V> boolean blocksCapability(@Nonnull Capability<V> cap, Direction side) {
        return super.blocksCapability(cap, side) || cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    private EquipmentSlotType getSlot(int slot){
        if (slot == 0){
            return EquipmentSlotType.HEAD;
        } else if (slot == 1){
            return EquipmentSlotType.CHEST;
        } else if (slot == 2){
            return EquipmentSlotType.LEGS;
        } else {
            return EquipmentSlotType.FEET;
        }
    }
}
