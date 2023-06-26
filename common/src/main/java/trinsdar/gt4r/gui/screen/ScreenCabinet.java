package trinsdar.gt4r.gui.screen;

import io.github.gregtechintergalactical.gtutility.blockentity.BlockEntityMaterial;
import muramasa.antimatter.gui.screen.ScreenMachine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import trinsdar.gt4r.gui.ContainerCabinet;

public abstract class ScreenCabinet<T extends BlockEntityMaterial<T>> extends ScreenMachine<T, ContainerCabinet<T>> {
    int guiY;
    public ScreenCabinet(ContainerCabinet<T> container, Inventory inv, Component name, String suffix, int maxY, int guiY) {
        super(container, inv, name);
        //TODO
        //gui = new ResourceLocation(Ref.ID, "textures/gui/machine/cabinet_" + suffix + ".png");
        this.imageHeight = maxY;
        this.guiY = guiY;
        this.imageWidth = 184;
    }
    //TODO
    /*@Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1, 1, 1, 1);
        //Minecraft.getInstance().textureManager.bindTexture(gui);
        blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize, 256, guiY);
    }*/

    public static class ScreenCabinetSix<T extends BlockEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetSix(ContainerCabinet<T> container, Inventory inv, Component name) {
            super(container, inv, name, "six", 222, 256);
        }
    }

    public static class ScreenCabinetSeven<T extends BlockEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetSeven(ContainerCabinet<T> container, Inventory inv, Component name) {
            super(container, inv, name, "seven", 240, 256);
        }
    }

    public static class ScreenCabinetEight<T extends BlockEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetEight(ContainerCabinet<T> container, Inventory inv, Component name) {
            super(container, inv, name, "eight", 258, 266);
        }
    }

    public static class ScreenCabinetNine<T extends BlockEntityMaterial<T>> extends ScreenCabinet<T> {
        public ScreenCabinetNine(ContainerCabinet<T> container, Inventory inv, Component name) {
            super(container, inv, name, "nine", 276, 276);
        }
    }
}
