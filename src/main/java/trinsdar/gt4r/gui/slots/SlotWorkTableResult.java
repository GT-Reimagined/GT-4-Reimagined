package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.slot.IAntimatterSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.item.ItemStack;
import trinsdar.gt4r.data.SlotTypes;

public class SlotWorkTableResult extends CraftingResultSlot implements IAntimatterSlot {
    private final CraftingInventory craftMatrix;
    private final MachineItemHandler<?> projectTable;
    public SlotWorkTableResult(MachineItemHandler<?> table, PlayerEntity player, CraftingInventory craftingInventory, CraftResultInventory result, int slotIndex, int xPosition, int yPosition) {
        super(player, craftingInventory, result, slotIndex, xPosition, yPosition);
        projectTable = table;
        craftMatrix = craftingInventory;
    }

    @Override
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {

    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        extractedFromTable();
        return super.onTake(thePlayer, stack);
    }

    private boolean extractedFromTable(){
        boolean remaining = true;
        for (int i = 0; i < 10; i++) {
            ItemStack itemStack = craftMatrix.getStackInSlot(i);
            if (itemStack.getCount() == 1) {
                extractFromTable(itemStack);
                craftMatrix.setInventorySlotContents(i, itemStack);
            }
            if (itemStack.getCount() == 1) {
                remaining  =  false;
            }
        }
        return remaining;
    }

    private ItemStack extractFromTable(ItemStack itemStack){
        for (int j = 0; j < projectTable.getHandler(SlotTypes.STORAGE).getSlots(); j++) {
            if (projectTable.getHandler(SlotTypes.STORAGE).getStackInSlot(j).getItem().equals(itemStack.getItem()) && ItemStack.areItemStackTagsEqual(itemStack, projectTable.getHandler(SlotTypes.STORAGE).getStackInSlot(j))) {
                projectTable.getHandler(SlotTypes.STORAGE).extractFromInput(j, 1, false);
                itemStack.setCount(itemStack.getCount() + 1);
            }
        }
        return itemStack;
    }
}
