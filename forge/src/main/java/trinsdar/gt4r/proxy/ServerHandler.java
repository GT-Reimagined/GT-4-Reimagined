package trinsdar.gt4r.proxy;

import muramasa.antimatter.proxy.IProxyHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
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
