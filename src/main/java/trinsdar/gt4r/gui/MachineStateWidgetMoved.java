package trinsdar.gt4r.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.widget.MachineStateWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.util.int2;

public class MachineStateWidgetMoved extends MachineStateWidget<ContainerMachine<?>> {
    final int2 location;
    protected MachineStateWidgetMoved(AntimatterContainerScreen<? extends ContainerMachine<?>> screen, IGuiHandler handler, int2 location) {
        super(screen, handler);
        this.location = location;
    }

    @Override
    public void renderWidget(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        ContainerMachine<?> c = this.screen().getContainer();
        MachineState state = c.getTile().getMachineState();
        //Draw error.
        if (c.getTile().has(MachineFlag.RECIPE)) {
            if (state == MachineState.POWER_LOSS) {
                drawTexture(stack, this.screen().sourceGui(), screen().getGuiLeft() + this.x, screen().getGuiTop() + this.y, this.location.x, this.location.y, this.getWidth(), this.getHeight());
            }
        }
    }

    public static <T extends ContainerMachine<?>> WidgetSupplier<T> build(int xLoc, int yLoc) {
        return builder((screen, handler) -> new MachineStateWidgetMoved(screen, handler, new int2(xLoc, yLoc)));
    }
}
