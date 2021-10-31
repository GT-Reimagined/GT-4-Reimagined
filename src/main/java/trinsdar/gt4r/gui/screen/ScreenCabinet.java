package trinsdar.gt4r.gui.screen;

import muramasa.antimatter.gui.screen.ScreenMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

public abstract class ScreenCabinet<T extends TileEntityMaterial<T>> extends ScreenMachine<T, ContainerCabinet<T>> {
    int guiY;
    public ScreenCabinet(ContainerCabinet<T> container, PlayerInventory inv, ITextComponent name, String suffix, int maxY, int guiY) {
        super(container, inv, name);
        //TODO
        //gui = new ResourceLocation(Ref.ID, "textures/gui/machine/cabinet_" + suffix + ".png");
        this.ySize = maxY;
        this.guiY = guiY;
        this.xSize = 184;
    }
    //TODO
    /*@Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1, 1, 1, 1);
        //Minecraft.getInstance().textureManager.bindTexture(gui);
        blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize, 256, guiY);
    }*/

    public static class ScreenCabinetSix<T extends TileEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetSix(ContainerCabinet<T> container, PlayerInventory inv, ITextComponent name) {
            super(container, inv, name, "six", 222, 256);
        }
    }

    public static class ScreenCabinetSeven<T extends TileEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetSeven(ContainerCabinet<T> container, PlayerInventory inv, ITextComponent name) {
            super(container, inv, name, "seven", 240, 256);
        }
    }

    public static class ScreenCabinetEight<T extends TileEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetEight(ContainerCabinet<T> container, PlayerInventory inv, ITextComponent name) {
            super(container, inv, name, "eight", 258, 266);
        }
    }

    public static class ScreenCabinetNine<T extends TileEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetNine(ContainerCabinet<T> container, PlayerInventory inv, ITextComponent name) {
            super(container, inv, name, "nine", 276, 276);
        }
    }
}
