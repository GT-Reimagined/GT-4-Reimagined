package trinsdar.gt4r.gui.slots;

import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;

public class SlotWorkTableResult extends ResultSlot {
    private final CraftingContainer craftMatrix;
    private final MachineItemHandler<?> projectTable;
    public SlotWorkTableResult(MachineItemHandler<?> table, Player player, CraftingContainer craftingInventory, ResultContainer result, int slotIndex, int xPosition, int yPosition) {
        super(player, craftingInventory, result, slotIndex, xPosition, yPosition);
        projectTable = table;
        craftMatrix = craftingInventory;
    }

    @Override
    public void onQuickCraft(ItemStack p_75220_1_, ItemStack p_75220_2_) {

    }

    @Override
    public void onTake(Player thePlayer, ItemStack stack) {
        extractedFromTable();
        super.onTake(thePlayer, stack);
    }

    private boolean extractedFromTable(){
        boolean remaining = true;
        for (int i = 0; i < 10; i++) {
            ItemStack itemStack = craftMatrix.getItem(i);
            if (itemStack.getCount() == 1) {
                extractFromTable(itemStack);
                craftMatrix.setItem(i, itemStack);
            }
            if (itemStack.getCount() == 1) {
                remaining  =  false;
            }
        }
        return remaining;
    }

    private ItemStack extractFromTable(ItemStack itemStack){
        for (int j = 0; j < projectTable.getHandler(SlotType.STORAGE).getSlots(); j++) {
            if (projectTable.getHandler(SlotType.STORAGE).getStackInSlot(j).getItem().equals(itemStack.getItem()) && ItemStack.tagMatches(itemStack, projectTable.getHandler(SlotType.STORAGE).getStackInSlot(j))) {
                projectTable.getHandler(SlotType.STORAGE).extractFromInput(j, 1, false);
                itemStack.setCount(itemStack.getCount() + 1);
            }
        }
        return itemStack;
    }
}
