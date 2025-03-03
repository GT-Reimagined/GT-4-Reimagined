package org.gtreimagined.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.network.packets.AbstractGuiEventPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.gtreimagined.gt4r.GT4RRef;

public record PlayerGuiHandler(Player player) implements IGuiHandler {
    @Override
    public boolean isRemote() {
        return player.getLevel().isClientSide;
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return null;
    }

    @Override
    public AbstractGuiEventPacket createGuiPacket(IGuiEvent event) {
        return null;
    }

    @Override
    public String handlerDomain() {
        return GT4RRef.ID;
    }

    @Override
    public GuiData getGui() {
        return null;
    }
}
