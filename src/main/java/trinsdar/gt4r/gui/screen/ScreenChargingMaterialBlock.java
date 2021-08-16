package trinsdar.gt4r.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.antimatter.machine.MachineFlag;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

public class ScreenChargingMaterialBlock<T extends TileEntityMaterial<T>> extends ScreenMachine<T, ContainerMachine<T>> {
    public ScreenChargingMaterialBlock(ContainerMachine<T> container, PlayerInventory inv, ITextComponent name, String location) {
        super(container, inv, name);
        boolean charged = container.getTile().has(MachineFlag.ENERGY);
        //gui = new ResourceLocation(Ref.ID, "textures/gui/machine/" + (charged ? "charging_" : "") + location + ".png");
    }

    @Override
    protected void drawTitle(MatrixStack stack, int mouseX, int mouseY) {
    }
}
