package trinsdar.gt4r.machine;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.util.Utils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.tile.single.TileEntityQuantumChest;

import static muramasa.antimatter.machine.MachineFlag.ITEM_INPUT;

public class QuantumChestItemHandler extends MachineItemHandler<TileEntityQuantumChest> {
    int maxSize = Integer.MAX_VALUE;
    int digitalCount;
    ItemStack d = ItemStack.EMPTY;
    public QuantumChestItemHandler(TileEntityQuantumChest tile) {
        super(tile);
        inventories.put(ITEM_INPUT, new TrackedItemHandler<>(tile, tile.getMachineType().getGui().getSlots(SlotTypes.DISPLAY, tile.getMachineTier()).size() + tile.getMachineType().getGui().getSlots(SlotType.IT_IN, tile.getMachineTier()).size(), ContentEvent.ITEM_INPUT_CHANGED));
    }

    /*@Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event instanceof ContentEvent) {
            switch ((ContentEvent) event) {
                case ITEM_INPUT_CHANGED:
                case ITEM_OUTPUT_CHANGED:
                    inputLogic();
                    outputLogic();
                    break;
            }
        }
    }*/

    @Override
    public void onUpdate() {
        inputLogic();
        outputLogic();
    }

    ItemStack input(){
        if (getInputList().isEmpty()){
            return ItemStack.EMPTY;
        }
        return getInputList().get(0);
    }

    ItemStack output(){
        if (getOutputList().isEmpty()){
            return ItemStack.EMPTY;
        }
        return getOutputList().get(0);
    }

    void input(ItemStack stack){
        this.getInputHandler().setStackInSlot(0, stack);
    }

    void output(ItemStack stack){
        this.getOutputHandler().setStackInSlot(0, stack);
    }

    /** Logic for stack input **/
    public void inputLogic() {
        if (input().isEmpty()) {
            return;
        }
        if (canDigitizeStack(input())) {
            display(input());
            this.digitalCount = this.digitalCount + input().getCount();
            input(ItemStack.EMPTY);
        }
    }

    /** Logic for stack output **/
    public void outputLogic() {
        if (!display().isEmpty() && canOutputsFit(new ItemStack[]{display()})) {
            int max = display().getMaxStackSize();
            int amount = Math.min(this.digitalCount, max);
            // if output is empty set the stack
            if (output().isEmpty()) {
                output(Utils.ca(amount, display()));
                this.digitalCount -= amount;
                // if can merge but not empty grow the stack
            } else {
                int difference = output().getMaxStackSize() - output().getCount();
                int count = Math.min(this.digitalCount, difference);
                output().grow(count);
                this.digitalCount -= count;
            }
            emptyCheck();
        }
    }

    public ItemStack getD() {
        return d;
    }

    public ItemStack display() {
        return this.getInputHandler().getStackInSlot(1);
    }

    public void display(ItemStack stack) {
        this.getInputHandler().setStackInSlot(1, stack);
        d = stack.copy();
    }

    public void emptyCheck() {
        if (this.digitalCount <= 0) {
            display(ItemStack.EMPTY);
        }
    }

    /** Checks to see if the given stack can go into the digital storage **/
    public boolean canDigitizeStack(ItemStack stack) {
        if (display().isEmpty() && this.digitalCount == 0) {
            return true;
        }
        return (this.digitalCount + stack.getCount() <= maxSize) && Utils.equals(display(), stack);
    }

    /** Sets the digital count, called when the block is placed for NBT stuff **/
    public void setDigtialCount(int count) {
        this.digitalCount = count;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        return nbtForItem(nbt);
    }

    CompoundNBT nbtForItem(CompoundNBT nbt){
        nbt.putInt("DCount", digitalCount);
        CompoundNBT stack = display().copy().serializeNBT();
        nbt.put("display", stack);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        digitalCount = nbt.getInt("DCount");
        d = ItemStack.read(nbt.getCompound("display"));
    }

    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        renderer.drawString(stack,"Item amount: " + digitalCount, left + 10, top + 19, 16448255);
    }
}
