package org.gtreimagined.gt4r.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.ICanSyncData;
import org.gtreimagined.gtlib.gui.IGuiElement;
import org.gtreimagined.gtlib.gui.Widget;
import org.gtreimagined.gtlib.gui.container.ContainerMachine;
import org.gtreimagined.gtlib.gui.widget.WidgetSupplier;
import org.gtreimagined.gtlib.mixin.client.AbstractContainerScreenAccessor;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityCoalBoiler;

public class CoalBoilerWidget extends Widget {
    private int heat = 0, maxHeat = 0;
    int water = 0, steam = 0;

    protected CoalBoilerWidget(@NotNull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    public static WidgetSupplier build() {
        return builder(CoalBoilerWidget::new);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((BlockEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getHeat(), i -> heat = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncInt(() -> ((BlockEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxHeat(), i -> maxHeat = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getInputs()[0].getAmount()).orElse(0), i -> water = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getOutputs()[0].getAmount()).orElse(0), i -> steam = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
    }

    @Override
    public void render(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            float per = (float) water / 16000;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 13, y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 28, 54 - lvl, 10, lvl);

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
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX(), y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 18, 54 - lvl, 10, lvl);
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
            int y = (((AbstractContainerScreenAccessor)gui.screen).getTopPos() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 26, y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 38, 54 - lvl, 10, lvl);
        }
    }

    @Override
    public void mouseOver(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            renderTooltip(stack,"Water: " + water + " MB", mouseX, mouseY, 14, 0, 10, 54);
        }
        if (steam >= 1) {
            renderTooltip(stack,"Steam: " + steam + " MB", mouseX, mouseY, 0, 0, 10, 54);
        }
        renderTooltip(stack,"Heat: " + heat + "K out of " + maxHeat, mouseX, mouseY, 26, 0, 10, 54);
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderTooltip(PoseStack matrixStack, String text, double mouseX, double mouseY, int x, int y, int w, int h) {
        if (isInside(x, y, w, h, mouseX, mouseY)){
            renderTooltip(matrixStack, new TextComponent(text), mouseX, mouseY);
        }

    }

    public boolean isInside(int x, int y, int w, int h, double mouseX, double mouseY) {
        int realX = realX() + x;
        int realY = realY() + y;
        return ((mouseX >= realX && mouseX <= realX + w) && (mouseY >= realY && mouseY <= realY + h));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}
