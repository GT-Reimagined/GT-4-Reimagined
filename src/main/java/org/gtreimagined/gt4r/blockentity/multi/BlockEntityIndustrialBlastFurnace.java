package org.gtreimagined.gt4r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityIndustrialBlastFurnace.IBFWidget;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.ICanSyncData;
import org.gtreimagined.gtlib.gui.IGuiElement;
import org.gtreimagined.gtlib.gui.widget.InfoRenderWidget;
import org.gtreimagined.gtlib.gui.widget.WidgetSupplier;
import org.gtreimagined.gtlib.integration.xei.renderer.IInfoRenderer;
import org.gtreimagined.gtlib.machine.event.IMachineEvent;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gtlib.recipe.IRecipe;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.data.GT4RBlocks;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.SlotTypes;
import org.gtreimagined.gt4r.machine.UpgradeableMachineRecipeHandler;

import java.awt.Color;

public class BlockEntityIndustrialBlastFurnace extends BlockEntityUpgradeableBasicMultiblock<BlockEntityIndustrialBlastFurnace> implements IInfoRenderer<IBFWidget> {

    private int heatingCapacity;
    private int baseHeatingCapacity;

    public BlockEntityIndustrialBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new UpgradeableMachineRecipeHandler<>(this){
            @Override
            protected boolean validateRecipe(IRecipe r) {
                return super.validateRecipe(r) && heatingCapacity >= r.getSpecialValue();
            }
        });
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        this.itemHandler.ifPresent(i -> {
            ItemStack stack = i.getHandler(SlotTypes.COIL).getStackInSlot(0);
            heatingCapacity = baseHeatingCapacity;
            if (!stack.isEmpty()){
                if (stack.getItem() == GT4RItems.KanthalHeatingCoil){
                    heatingCapacity += (125 * stack.getCount());
                } else {
                    heatingCapacity += (250 * stack.getCount());
                }
            }
        });

        return true;
    }

    @Override
    public boolean checkStructure() {
        baseHeatingCapacity = 0;
        return super.checkStructure();
    }

    public void incrementBaseHeatingCapacity(int amount){
        baseHeatingCapacity += amount;
    }

    public int getHeatPerCasing(Block block){
        if (block == GT4RBlocks.STANDARD_MACHINE_CASING){
            return 30;
        } else if (block == GT4RBlocks.REINFORCED_MACHINE_CASING){
            return 50;
        } else if (block == GT4RBlocks.ADVANCED_MACHINE_CASING){
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
            heatingCapacity = baseHeatingCapacity;
            ItemStack stack = (ItemStack) data[0];
            if (!stack.isEmpty()){
                if (stack.getItem() == GT4RItems.KanthalHeatingCoil){
                    heatingCapacity += (125 * stack.getCount());
                } else {
                    heatingCapacity += (250 * stack.getCount());
                }
            }
        }
        super.onMachineEvent(event, data);
    }

    public enum BFEvent implements IMachineEvent{
        SLOT_COIL_CHANGED
    }

    public int getHeatingCapacity() {
        return heatingCapacity;
    }
    @Override
    public int drawInfo(IBFWidget widget, PoseStack stack, Font renderer, int left, int top) {
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
            BlockEntityIndustrialBlastFurnace m = (BlockEntityIndustrialBlastFurnace) gui.handler;
            gui.syncInt(m::getHeatingCapacity, i -> heat = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a,b) -> new IBFWidget(a,b, (IInfoRenderer) a.handler));
        }
    }
}
