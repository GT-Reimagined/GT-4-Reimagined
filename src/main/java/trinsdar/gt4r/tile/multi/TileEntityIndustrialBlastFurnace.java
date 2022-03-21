package trinsdar.gt4r.tile.multi;

import com.mojang.blaze3d.matrix.MatrixStack;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.renderer.IInfoRenderer;
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

public class TileEntityIndustrialBlastFurnace extends TileEntityBasicMultiMachine<TileEntityIndustrialBlastFurnace> implements IInfoRenderer<TileEntityIndustrialBlastFurnace.IBFWidget> {

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
    public int drawInfo(IBFWidget widget, MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        renderer.draw(stack,"Heat: " + widget.heat + "K", widget.realX() + 27, widget.realY() + 62, Color.BLACK.getRGB());
        return 8;
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addWidget(IBFWidget.build());
    }

    public static class IBFWidget extends InfoRenderWidget<IBFWidget> {
        public int heat = 0;
        protected IBFWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<IBFWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            TileEntityIndustrialBlastFurnace m = (TileEntityIndustrialBlastFurnace) gui.handler;
            gui.syncInt(m::getHeatingCapacity, i -> heat = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a,b) -> new IBFWidget(a,b, (IInfoRenderer) a.handler));
        }
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
            public int getCount() {
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

        @Override
        public void onMachineEvent(IMachineEvent event, Object... data) {
            super.onMachineEvent(event, data);
            if (event instanceof BFEvent){
                heatingCapacity = tile.getAllStates().stream().mapToInt(s -> (tile.getHeatPerCasing(s.getBlock()))).sum();
                ItemStack stack = (ItemStack) data[0];
                if (!stack.isEmpty()){
                    if (stack.getItem() == GT4RData.KanthalHeatingCoil){
                        heatingCapacity += (125 * stack.getCount());
                    } else {
                        heatingCapacity += (250 * stack.getCount());
                    }
                }
            }
        }
    }
}
