package trinsdar.gt4r.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TooltipWidget extends Widget {
    final String tooltipKey;
    protected TooltipWidget(@Nonnull GuiInstance gui, @Nullable IGuiElement parent, String tooltipKey) {
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
    public void render(MatrixStack matrixStack, double mouseX, double mouseY, float partialTicks) {

    }

    @Override
    public void mouseOver(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        super.mouseOver(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, new TranslationTextComponent("tooltip.gt4r." + tooltipKey), mouseX, mouseY);
    }
}
