package trinsdar.gt4r.gui.screen;

import muramasa.antimatter.gui.screen.ScreenMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.tile.single.TileEntityDigitalChest;

public class ScreenDigitalChest extends ScreenMachine<TileEntityDigitalChest, ContainerDigitalChest> {
    public ScreenDigitalChest(ContainerDigitalChest container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.imageHeight = 221;
    }
}
