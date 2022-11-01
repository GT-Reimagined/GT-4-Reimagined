package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.network.packets.AbstractGuiEventPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import trinsdar.gt4r.Ref;

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
        return Ref.ID;
    }
}
