package org.gtreimagined.gt4r.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.ScreenMultiMachine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityFusionReactor;

public class ScreenFusionReactor<T extends ContainerMultiMachine<BlockEntityFusionReactor>> extends ScreenMultiMachine<BlockEntityFusionReactor, T> {
    public ScreenFusionReactor(T container, Inventory inv, Component name) {
        super(container, inv, name);
        this.imageHeight = 182;
    }

    @Override
    protected void drawTitle(PoseStack stack, int mouseX, int mouseY) {

    }
}
