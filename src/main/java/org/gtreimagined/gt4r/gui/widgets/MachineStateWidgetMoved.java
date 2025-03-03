package org.gtreimagined.gt4r.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.MachineStateWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.util.int2;

public class MachineStateWidgetMoved extends MachineStateWidget {
    final int2 location;

    protected MachineStateWidgetMoved(GuiInstance gui, IGuiElement parent, int2 loc) {
        super(gui, parent);
        this.location = loc;
    }

    @Override
    public void render(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        BlockEntityMachine<?> m = ((BlockEntityMachine<?>) gui.handler);
        MachineState state = m.getMachineState();
        //Draw error.
        if (isRecipe) {
            if (state == MachineState.POWER_LOSS) {
                drawTexture(stack, gui.handler.getGuiTexture(), realX(), realY(), this.location.x, this.location.y, this.getW(), this.getH());
            }
        }
    }

    public static WidgetSupplier build(int xLoc, int yLoc) {
        return builder((screen, handler) -> new MachineStateWidgetMoved(screen, handler, new int2(xLoc, yLoc)));
    }
}
