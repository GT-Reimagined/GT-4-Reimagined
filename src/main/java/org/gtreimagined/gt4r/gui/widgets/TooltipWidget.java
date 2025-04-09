package org.gtreimagined.gt4r.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.IGuiElement;
import org.gtreimagined.gtlib.gui.Widget;
import org.gtreimagined.gtlib.gui.widget.WidgetSupplier;
import net.minecraft.network.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TooltipWidget extends Widget {
    final String tooltipKey;
    protected TooltipWidget(@NotNull GuiInstance gui, @Nullable IGuiElement parent, String tooltipKey) {
        super(gui, parent);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public static WidgetSupplier build(String tooltipKey) {
        return builder((gui1, parent1) -> new TooltipWidget(gui1, parent1, tooltipKey));
    }

    @Override
    public void render(PoseStack matrixStack, double mouseX, double mouseY, float partialTicks) {

    }

    @Override
    public void mouseOver(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        super.mouseOver(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, new TranslatableComponent("tooltip.gt4r." + tooltipKey), mouseX, mouseY);
    }
}
