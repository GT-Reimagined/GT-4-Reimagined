package trinsdar.gt4r.tile.multi;

import com.mojang.blaze3d.matrix.MatrixStack;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.SlotTypes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEntityIndustrialBlastFurnace extends TileEntityBasicMultiMachine<TileEntityIndustrialBlastFurnace> {

    public TileEntityIndustrialBlastFurnace(Machine type) {
        super(type);
        this.recipeHandler.set(() -> new IBFRecipeHandler(this));
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        recipeHandler.ifPresent(r -> {
            ((IBFRecipeHandler)r).heatingCapacity = getAllStates().stream().mapToInt(s -> (getHeatPerCasing(s.getBlock()))).sum();
            this.itemHandler.ifPresent(i -> {
                ItemStack stack = i.getHandler(SlotTypes.COIL).getStackInSlot(0);
                if (!stack.isEmpty()){
                    if (stack.getItem() == GT4RData.KanthalHeatingCoil){
                        ((IBFRecipeHandler)r).heatingCapacity += (125 * stack.getCount());
                    } else {
                        ((IBFRecipeHandler)r).heatingCapacity += (250 * stack.getCount());
                    }
                }
            });

        });

        return true;
    }

    public List<BlockState> getAllStates() {
        if (result != null) {
            ObjectCollection<List<BlockState>> collection = result.states.values();
            if (collection.isEmpty()){
                return Collections.emptyList();
            }
            List<BlockState> list = new ArrayList<>();
            collection.forEach(list::addAll);
            return list;
        }
        return Collections.emptyList();
    }

    public int getHeatPerCasing(Block block){
        if (block == GT4RData.STANDARD_MACHINE_CASING){
            return 30;
        } else if (block == GT4RData.REINFORCED_MACHINE_CASING){
            return 50;
        } else if (block == GT4RData.ADVANCED_MACHINE_CASING){
            return 70;
        } else if (block == Blocks.LAVA){
            return 250;
        } else {
            return 0;
        }
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event instanceof BFEvent){
            recipeHandler.ifPresent(r -> {
                ((IBFRecipeHandler)r).heatingCapacity = getAllStates().stream().mapToInt(s -> (getHeatPerCasing(s.getBlock()))).sum();
                ItemStack stack = (ItemStack) data[0];
                if (!stack.isEmpty()){
                    if (stack.getItem() == GT4RData.KanthalHeatingCoil){
                        ((IBFRecipeHandler)r).heatingCapacity += (125 * stack.getCount());
                    } else {
                        ((IBFRecipeHandler)r).heatingCapacity += (250 * stack.getCount());
                    }
                }
            });

        }
        super.onMachineEvent(event, data);
    }

    public enum BFEvent implements IMachineEvent{
        SLOT_COIL_CHANGED
    }

    public int getHeatingCapacity() {
        AtomicInteger integer = new AtomicInteger(0);
        this.recipeHandler.ifPresent(r -> integer.set(((IBFRecipeHandler)r).heatingCapacity));
        return integer.get();
    }

    @Override
    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        this.recipeHandler.ifPresent(r -> {
            renderer.drawString(stack,"Heat: " + ((IBFRecipeHandler)r).heatingCapacity + "K", 27, 62, Color.BLACK.getRGB());
        });
    }

    public static class IBFRecipeHandler extends MachineRecipeHandler<TileEntityIndustrialBlastFurnace> {
        protected final IIntArray GUI_SYNC_DATA = new IIntArray() {

            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return IBFRecipeHandler.this.currentProgress;
                    case 1:
                        return IBFRecipeHandler.this.maxProgress;
                    case 2:
                        return IBFRecipeHandler.this.heatingCapacity;
                }
                return 0;
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        IBFRecipeHandler.this.currentProgress = value;
                        break;
                    case 1:
                        IBFRecipeHandler.this.maxProgress = value;
                        break;
                    case 2:
                        IBFRecipeHandler.this.heatingCapacity = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
        private int heatingCapacity;
        public IBFRecipeHandler(TileEntityIndustrialBlastFurnace tile) {
            super(tile);
        }

        @Override
        protected boolean validateRecipe(Recipe r) {
            return super.validateRecipe(r) && heatingCapacity >= r.getSpecialValue();
        }

        @Override
        public IIntArray getProgressData() {
            return GUI_SYNC_DATA;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT nbt = super.serializeNBT();
            nbt.putInt("H", heatingCapacity);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            super.deserializeNBT(nbt);
            this.heatingCapacity = nbt.getInt("H");
        }
    }
}
