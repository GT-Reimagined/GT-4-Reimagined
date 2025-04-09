package org.gtreimagined.gt4r.proxy;

import org.gtreimagined.gtlib.proxy.IProxyHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ServerHandler implements IProxyHandler {
    @Override
    public Level getClientWorld() {
        throw new IllegalStateException("Cannot call on server!");
    }

    @Override
    public Player getClientPlayer() {
        throw new IllegalStateException("Cannot call on server!");
    }
}
