package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.widget.AntimatterWidget;
import muramasa.antimatter.gui.widget.MachineStateWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.AntimatterJEIPlugin;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;

public class FusionButtonWidget<T extends ContainerMultiMachine<TileEntityFusionReactor>> extends AntimatterWidget<T> {
    ResourceLocation middle = new ResourceLocation(Ref.ID, "textures/gui/machine/fusion_computer_middle_overlay.png");
    ResourceLocation top_bottom = new ResourceLocation(Ref.ID, "textures/gui/machine/fusion_computer_top_bottom_overlay.png");
    public FusionButtonWidget(AntimatterContainerScreen<? extends T> screen, IGuiHandler handler) {
        super(screen, handler, 0, 0, 176, 182);
    }

    @Override
    public void renderWidget(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(stack, mouseX, mouseY, partialTicks);
        if (container().getTile() != null){
            TileEntityFusionReactor tile = container().getTile();
            if (tile.getDisplay() == TileEntityFusionReactor.Display.REGULAR){
                drawTexture(stack, screen().sourceGui(), screen().getGuiLeft() + 154, screen().getGuiTop() + 22, 176, 0, 18, 18);
            } else if (tile.getDisplay() == TileEntityFusionReactor.Display.MIDDLE){
                drawTexture(stack, screen().sourceGui(), screen().getGuiLeft() + 154, screen().getGuiTop() + 40, 176, 18, 18, 18);
                drawTexture(stack, middle, screen().getGuiLeft() + 6, screen().getGuiTop() + 6, 0, 0, 145, 145);
            } else {
                drawTexture(stack, screen().sourceGui(), screen().getGuiLeft() + 154, screen().getGuiTop() + 58, 176, 36, 18, 18);
                drawTexture(stack, top_bottom, screen().getGuiLeft() + 6, screen().getGuiTop() + 6, 0, 0, 145, 145);
            }
        }
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.renderToolTip(matrixStack, mouseX, mouseY);
        if (container().getTile().getMachineType().has(MachineFlag.RECIPE))
            screen().drawTooltipInArea(matrixStack, "Show Recipes", mouseX, mouseY, 154, 4, 18, 18);
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return super.clicked(mouseX, mouseY) && screen().isInGui(154, 4, 18, 18, mouseX, mouseY);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        AntimatterJEIPlugin.showCategory(container().getTile().getMachineType());
    }

    public static <T extends ContainerMultiMachine<TileEntityFusionReactor>> WidgetSupplier<T> build() {
        return builder(FusionButtonWidget::new);
    }
}
