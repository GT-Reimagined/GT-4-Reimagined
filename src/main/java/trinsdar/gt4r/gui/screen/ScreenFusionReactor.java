package trinsdar.gt4r.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenMultiMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;

public class ScreenFusionReactor<T extends ContainerMultiMachine<TileEntityFusionReactor>> extends ScreenMultiMachine<TileEntityFusionReactor, T> {
    public ScreenFusionReactor(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.imageHeight = 182;
    }

    @Override
    protected void drawTitle(MatrixStack stack, int mouseX, int mouseY) {

    }
}
