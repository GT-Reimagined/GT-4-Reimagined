package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.container.ContainerCover;
import muramasa.antimatter.gui.screen.ScreenCover;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.cover.ICoverMode;
import trinsdar.gt4r.cover.ICoverModeHandler;

public class ScreenButtonBackgroundCover<T extends ContainerCover> extends ScreenCover<T> {
    public ScreenButtonBackgroundCover(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        drawTitle(stack, mouseX, mouseY);
        if (container.getCover().getCover() instanceof ICoverModeHandler){
            ICoverModeHandler basicTransport = (ICoverModeHandler) container.getCover().getCover();
            ICoverMode mode = basicTransport.getCoverMode(container.getCover());
            Minecraft.getInstance().fontRenderer.drawString(stack,"Mode: " + mode.getName(), getCenteredStringX("Mode: " + mode.getName()), 13, 0x404040);
        }

    }

    @Override
    protected void drawTitle(MatrixStack stack, int mouseX, int mouseY) {
        Minecraft.getInstance().fontRenderer.drawString(stack, container.getCover().getDisplayName().getString(), getCenteredStringX(container.getCover().getDisplayName().getString()), 4, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        if (container.getCover().getCover() instanceof ICoverModeHandler){
            ICoverModeHandler basicTransport = (ICoverModeHandler) container.getCover().getCover();
            ICoverMode mode = basicTransport.getCoverMode(container.getCover());
            drawTexture(stack, gui, guiLeft + mode.getX(), guiTop + mode.getY(), 176, 0, 18, 18);
        }
    }
}
