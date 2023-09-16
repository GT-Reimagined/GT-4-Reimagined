package trinsdar.gt4r.gui.screen;

import muramasa.antimatter.gui.screen.ScreenMachine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.blockentity.single.BlockEntityDigitalChest;

public class ScreenDigitalChest extends ScreenMachine<BlockEntityDigitalChest, ContainerDigitalChest> {
    public ScreenDigitalChest(ContainerDigitalChest container, Inventory inv, Component name) {
        super(container, inv, name);
        this.imageHeight = 221;
    }
}
