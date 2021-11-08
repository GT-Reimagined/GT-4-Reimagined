package trinsdar.gt4r.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import trinsdar.gt4r.tile.single.TileEntityTranslocator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TranslocatorButtonArrayWidget extends Widget {
    protected ChangingButtonWidget energyWidget;
    protected ChangingButtonWidget blacklistWidget;
    boolean blacklist = false;
    boolean emitEnergy = false;
    private static final ButtonBody energyLoc = new ButtonBody("",176, 32, 0, -32, 16, 16), blacklistLoc = new ButtonBody("",176, 32, 0, -16, 16, 16);
    protected TranslocatorButtonArrayWidget(@Nonnull GuiInstance instance, @Nullable IGuiElement parent) {
        super(instance, parent);
        this.setX(7);
        this.setY(62);
        this.setW(90);
        this.setH(18);
        this.setVisible(false);
        this.energyWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), energyLoc, null, GuiEvent.EXTRA_BUTTON,0).setSize(1, 1, 16, 16).buildAndAdd(instance, this);
        this.energyWidget.setStateHandler(w -> emitEnergy).setDepth(depth() + 1);
        this.blacklistWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), blacklistLoc, null, GuiEvent.EXTRA_BUTTON,1).setSize(19, 1, 16, 16).buildAndAdd(instance, this);
        this.blacklistWidget.setStateHandler(w -> blacklist).setDepth(depth() + 1);
    }

    @Override
    public void render(MatrixStack matrixStack, double mouseX, double mouseY, float partialTicks) {

    }

    @Override
    public void updateSize() {
        super.updateSize();
        if (this.energyWidget != null) this.energyWidget.updateSize();
        if (this.blacklistWidget != null) this.blacklistWidget.updateSize();
    }

    public static WidgetSupplier build() {
        return builder(TranslocatorButtonArrayWidget::new);
    }

    @Override
    public void init() {
        super.init();
        ContainerMachine<?> m = (ContainerMachine<?>) gui.container;
        TileEntityTranslocator filter = (TileEntityTranslocator) m.getTile();
        gui.syncBoolean(filter::isBlacklist, b -> blacklist = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isEmitEnergy, b -> emitEnergy = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}
