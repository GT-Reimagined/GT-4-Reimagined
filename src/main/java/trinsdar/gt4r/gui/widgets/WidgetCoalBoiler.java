package trinsdar.gt4r.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.gui.GuiUtils;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WidgetCoalBoiler extends Widget {
    private int heat = 0, maxHeat = 0, fuel = 0, maxFuel = 0, water = 0, steam = 0;

    protected WidgetCoalBoiler(@Nonnull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getHeat(), i -> heat = i);
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxHeat(), i -> maxHeat = i);
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getFuel(), i -> fuel = i);
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxFuel(), i -> maxFuel = i);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getInputs()[0].getAmount()).orElse(0), i -> water = i);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getOutputs()[0].getAmount()).orElse(0), i -> steam = i);
    }

    @Override
    public void render(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            float per = (float) water / 16000;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (gui.screen.getGuiTop() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), gui.screen.getGuiLeft() + 83, y, gui.screen.getXSize() + 28, 54 - lvl, 10, lvl);

        }
        if (steam >= 1) {
            float per = (float) steam / 16000;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (gui.screen.getGuiTop() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), gui.screen.getGuiLeft() + 70, y, gui.screen.getXSize() + 18, 54 - lvl, 10, lvl);
        }
        if (heat >= 1) {
            float per = (float) heat / maxHeat;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (gui.screen.getGuiTop() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), gui.screen.getGuiLeft() + 96, y, gui.screen.getXSize() + 38, 54 - lvl, 10, lvl);
        }
        if (fuel > 0) {
            float per = (float) fuel / maxFuel;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 18);
            if (lvl < 0) {
                return;
            }
            int y = (gui.screen.getGuiTop() + 42 + 18) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), gui.screen.getGuiLeft() + 115, y, gui.screen.getXSize(), 18 - lvl, 18, lvl);
        }
    }

    @Override
    public void mouseOver(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        renderTooltip(stack,"Show Recipes", mouseX, mouseY, 115, 43, 18, 18);
        if (water >= 1) {
            renderTooltip(stack,"Water: " + water + " MB", mouseX, mouseY, 84, 25, 10, 54);
        }
        if (steam >= 1) {
            renderTooltip(stack,"Steam: " + steam + " MB", mouseX, mouseY, 70, 25, 10, 54);
        }
        renderTooltip(stack,"Heat: " + heat + "K out of " + maxHeat, mouseX, mouseY, 96, 25, 10, 54);
        renderTooltip(stack,"Fuel: " + fuel, mouseX, mouseY + 10, 115, 53, 18, 18);
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderTooltip(MatrixStack matrixStack, String text, double mouseX, double mouseY, int x, int y, int w, int h) {
        if (gui.screen.isInGui(x, y, w, h, mouseX, mouseY)){
            GuiUtils.drawHoveringText(matrixStack, Collections.singletonList(new StringTextComponent(text)), (int)mouseX, (int)mouseY, w, h, -1, Minecraft.getInstance().fontRenderer);
        }

    }
}
