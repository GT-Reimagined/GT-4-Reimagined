package org.gtreimagined.gt4r.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.gui.GuiInstance;
import org.gtreimagined.gtlib.gui.IGuiElement;
import org.gtreimagined.gtlib.gui.widget.MachineStateWidget;
import org.gtreimagined.gtlib.gui.widget.WidgetSupplier;
import org.gtreimagined.gtlib.machine.MachineState;
import org.gtreimagined.gtlib.util.int2;

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
