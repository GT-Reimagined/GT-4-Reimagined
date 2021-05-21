package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenBasicMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.antimatter.gui.screen.ScreenMultiMachine;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.MachineState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.tile.multi.TileEntityDistillationTower;

import static muramasa.antimatter.gui.SlotType.FL_IN;
import static muramasa.antimatter.gui.SlotType.FL_OUT;

public class ScreenDistillationTower extends ScreenMachine<TileEntityDistillationTower, ContainerBasicMachine<TileEntityDistillationTower>> {
    public ScreenDistillationTower(ContainerBasicMachine container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void drawProgress(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        int progressTime = (int) (20 * container.getTile().recipeHandler.map(MachineRecipeHandler::getClientProgress).orElse(0F));
        drawTexture(stack, gui, guiLeft + 80, guiTop + 4, xSize, 72 - progressTime, 16, progressTime);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        drawTitle(stack, mouseX, mouseY);
        if (container.getTile().has(MachineFlag.RECIPE)) {
            drawTooltipInArea(stack,"Show Recipes", mouseX, mouseY, 80, 4, 16, 72);
            drawTooltipInArea(stack, container.getTile().getMachineState().getDisplayName(), mouseX, mouseY, 66, 26, 8, 9);
        }
        /*if (container.getTile().has(MachineFlag.FLUID)) {
            //TODO when slotted multis are properly supported
            container.getTile().fluidHandler.ifPresent(t -> {
                int[] index = new int[]{0};
                FluidStack[] inputs = t.getInputs();
                container.getTile().getMachineType().getGui().getSlots(FL_IN, this.container.getTile().getMachineTier()).forEach(sl -> {
                    renderFluid(stack, inputs[index[0]++], sl,mouseX,mouseY);
                });
                index[0] = 0;
                FluidStack[] outputs = t.getOutputs();
                container.getTile().getMachineType().getGui().getSlots(FL_OUT, this.container.getTile().getMachineTier()).forEach(sl -> {
                    renderFluid(stack, outputs[index[0]++], sl, mouseX,mouseY);
                });
            });
        }*/
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        drawProgress(stack, partialTicks, mouseX, mouseY);
        //Draw error.
        if (container.getTile().has(MachineFlag.RECIPE)) {
            if (container.getTile().getMachineState() == MachineState.POWER_LOSS) {
                drawTexture(stack, gui, guiLeft + 66, guiTop + 26, xSize, 108, 8, 8);
            } /*else if (container.getTile().getMachineState() == MachineState.INVALID_STRUCTURE && container.getTile().getWorld().getGameTime() % 4 == 0){
                drawTexture(stack, gui, guiLeft + 66, guiTop + 26, xSize, 108, 8, 8);
            }*/
        }
    }
}
