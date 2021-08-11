package trinsdar.gt4r.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenMultiMachine;
import muramasa.antimatter.integration.jei.AntimatterJEIPlugin;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.ModList;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;

import java.util.ResourceBundle;

public class ScreenFusionReactor<T extends ContainerMultiMachine<TileEntityFusionReactor>> extends ScreenMultiMachine<TileEntityFusionReactor, T> {
    public ScreenFusionReactor(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.ySize = 182;
    }

    @Override
    protected void drawTitle(MatrixStack stack, int mouseX, int mouseY) {

    }
}
