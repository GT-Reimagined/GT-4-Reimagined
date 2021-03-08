package trinsdar.gt4r.gui;

import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.ScreenHatch;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.Ref;

public class ScreenHatchCustom<T extends ContainerMachine> extends ScreenHatch<T> {
    public ScreenHatchCustom(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        gui = new ResourceLocation(Ref.ID, "textures/gui/machine/hatch.png");
    }
}
