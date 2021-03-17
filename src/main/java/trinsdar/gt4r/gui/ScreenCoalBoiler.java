package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import static muramasa.antimatter.gui.SlotType.FL_IN;
import static muramasa.antimatter.gui.SlotType.FL_OUT;

public class ScreenCoalBoiler<T extends ContainerMachine> extends ScreenMachine<T> {
    public ScreenCoalBoiler(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void drawProgress(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        // empty method to prevent progress bar drawing
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        drawTitle(stack, mouseX, mouseY);
        if (container.getTile().has(MachineFlag.RECIPE)) {
            drawTooltipInArea(stack,"Show Recipes", mouseX, mouseY, 115, 43, 18, 18);
        }
        if (container.getTile().has(MachineFlag.FLUID)) {
            //TODO
            container.getTile().fluidHandler.ifPresent(t -> {
                FluidStack[] inputs = t.getInputs();
                int water = inputs[0].getAmount();
                if (water >= 1) {
                    float per = (float)water / 16000;
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (25 + 54) - lvl;
                    drawTexture(stack, gui, 84, y, xSize + 48, 0, 10, lvl);
                    drawTooltipInArea(stack,"Water: " + water + " MB", mouseX, mouseY, 84, 25, 10, 54);
                }
                FluidStack[] outputs = t.getOutputs();
                int steam = outputs[0].getAmount();
                if (steam >= 1) {
                    float per = (float)steam / 16000;
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (25 + 54) - lvl;
                    drawTexture(stack, gui, 70, y, xSize + 38, 0, 10, lvl);
                    drawTooltipInArea(stack,"Steam: " + steam + " MB", mouseX, mouseY, 70, 25, 10, 54);
                }
            });
            if (container.getTile() instanceof TileEntityCoalBoiler){
                TileEntityCoalBoiler tile = (TileEntityCoalBoiler)container.getTile();
                int heat = tile.getHeat();
                if (heat >= 1) {
                    float per = (float)heat / tile.getMaxHeat();
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (25 + 54) - lvl;
                    drawTexture(stack, gui, 96, y, xSize + 58, 0, 10, lvl);
                    drawTooltipInArea(stack,"Heat: " + tile.getHeat() + "K out of " + tile.getMaxHeat(), mouseX, mouseY, 96, 25, 10, 54);
                }
                int fuel = tile.getFuel();
                if (fuel > 0){
                    float per = (float)fuel / tile.getMaxFuel();
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 18);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (42 + 18) - lvl;
                    drawTexture(stack, gui, 115, y, xSize + 20, 0, 18, lvl);
                }
            }
        }
    }
}
