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
    ResourceLocation middle = new ResourceLocation(Ref.ID, "textures/gui/machine/fusion_computer_middle_overlay.png");
    ResourceLocation top_bottom = new ResourceLocation(Ref.ID, "textures/gui/machine/fusion_computer_top_bottom_overlay.png");
    public ScreenFusionReactor(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.ySize = 182;
    }

    @Override
    protected void drawTitle(MatrixStack stack, int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        if (container.getTile().has(MachineFlag.RECIPE)) {
            drawTooltipInArea(stack,"Show Recipes", mouseX, mouseY, 154, 4, 18, 18);
        }
    }

    /*@Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);
        if (container.getTile() != null){
            TileEntityFusionReactor tile = container.getTile();
            if (tile.getDisplay() == TileEntityFusionReactor.Display.REGULAR){
                drawTexture(stack, gui, guiLeft + 154, guiTop + 22, 176, 0, 18, 18);
            } else if (tile.getDisplay() == TileEntityFusionReactor.Display.MIDDLE){
                drawTexture(stack, gui, guiLeft + 154, guiTop + 40, 176, 18, 18, 18);
                drawTexture(stack, middle, guiLeft + 6, guiTop + 6, 0, 0, 145, 145);
            } else {
                drawTexture(stack, gui, guiLeft + 154, guiTop + 58, 176, 36, 18, 18);
                drawTexture(stack, top_bottom, guiLeft + 6, guiTop + 6, 0, 0, 145, 145);
            }
        }
    }*/


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (!ModList.get().isLoaded("jei") || !container.getTile().has(MachineFlag.RECIPE)) return super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInGui((xSize / 2) - 10, 24, 20, 18, mouseX, mouseY)) {
            return false;
        }
        if (isInGui(154, 4, 18, 18, mouseX, mouseY)){
            AntimatterJEIPlugin.showCategory(container.getTile().getMachineType());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
