package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.machine.MachineItemHandler;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.network.GT4RNetwork;
import trinsdar.gt4r.network.MessageCraftingSync;

import javax.annotation.Nonnull;

public class InventoryWorkbench extends CraftingInventory {
    private final int length;
    private final MachineItemHandler<?> projectTable;
    private final Container eventHandler;
    public InventoryWorkbench(Container eventHandlerIn, MachineItemHandler<?> table, int width, int height) {
        super(eventHandlerIn, width, height);
        this.length = width * height;
        this.projectTable = table;
        this.eventHandler = eventHandlerIn;
    }

    @Override
    public int getSizeInventory() {
        return this.length;
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        ItemStack itemStack = getStackInSlot(i);
        if (!itemStack.isEmpty()) {
            setInventorySlotContents(i, ItemStack.EMPTY);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.projectTable.getHandler(SlotTypes.CRAFTING).setStackInSlot(slot, stack);
        eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public void markDirty() {
        this.projectTable.getTile().markDirty();
        this.eventHandler.onCraftMatrixChanged(this);
        if(FMLEnvironment.dist == Dist.CLIENT)
            GT4RNetwork.handler.sendToServer(new MessageCraftingSync());
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.projectTable.getHandler(SlotTypes.CRAFTING).getStackInSlot(index);
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(!this.getStackInSlot(index).isEmpty()) {
            ItemStack itemstack;

            if(this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            } else {
                itemstack = this.getStackInSlot(index).split(count);

                if(this.getStackInSlot(index).getCount() == 0) {
                    this.setInventorySlotContents(index, ItemStack.EMPTY);
                }

            }
            this.eventHandler.onCraftMatrixChanged(this);
            return itemstack;
        } else {
            return ItemStack.EMPTY;
        }
    }
}
