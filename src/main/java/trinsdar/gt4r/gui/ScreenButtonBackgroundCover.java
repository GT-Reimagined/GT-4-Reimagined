package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.ButtonData;
import muramasa.antimatter.gui.container.ContainerCover;
import muramasa.antimatter.gui.screen.ScreenCover;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.cover.CoverBasicTransport;
import trinsdar.gt4r.cover.CoverMode;
import trinsdar.gt4r.cover.ICoverMode;
import trinsdar.gt4r.cover.ICoverModeCover;

public class ScreenButtonBackgroundCover<T extends ContainerCover> extends ScreenCover<T> {
    public ScreenButtonBackgroundCover(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        if (container.getCover().getCover() instanceof ICoverModeCover){
            ICoverModeCover basicTransport = (ICoverModeCover) container.getCover().getCover();
            ICoverMode mode = basicTransport.getCoverMode();
            drawTexture(stack, gui, guiLeft + mode.getX(), guiTop + mode.getY(), 176, 0, 18, 18);
        }
    }
}
