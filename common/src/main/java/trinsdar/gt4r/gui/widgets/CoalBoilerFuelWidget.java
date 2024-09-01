package trinsdar.gt4r.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.mixin.client.AbstractContainerScreenAccessor;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import trinsdar.gt4r.blockentity.single.BlockEntityCoalBoiler;

public class CoalBoilerFuelWidget extends Widget {
    private int fuel = 0, maxFuel = 0;
    protected CoalBoilerFuelWidget(@NotNull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    public static WidgetSupplier build() {
        return builder(CoalBoilerFuelWidget::new);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((BlockEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getFuel(), i -> fuel = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncInt(() -> ((BlockEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxFuel(), i -> maxFuel = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
    }

    @Override
    public void render(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        if (fuel > 0) {
            float per = (float) fuel / maxFuel;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 18);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 18) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX(), y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth(), 18 - lvl, 18, lvl);
        }
    }

    @Override
    public void mouseOver(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        super.mouseOver(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack,"Show Recipes", mouseX, mouseY, 0, 0, 18, 18);
        renderTooltip(stack,"Fuel: " + fuel, mouseX, mouseY + 10, 0, 10, 18, 18);
    }

    @Environment(EnvType.CLIENT)
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
    public void onClick(double mouseX, double mouseY, int button) {
        super.onClick(mouseX, mouseY, button);
        if (this.gui.handler instanceof BlockEntityMachine<?> machine) {
            AntimatterJEIREIPlugin.showCategory(machine.getMachineType(), machine.getMachineTier());
        }
    }
}
