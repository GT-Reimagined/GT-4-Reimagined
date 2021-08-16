package trinsdar.gt4r.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.screen.ScreenBasicMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;

public class ScreenIBF extends ScreenBasicMachine<TileEntityIndustrialBlastFurnace, ContainerBasicMachine<TileEntityIndustrialBlastFurnace>> {
    public ScreenIBF(ContainerBasicMachine container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}
