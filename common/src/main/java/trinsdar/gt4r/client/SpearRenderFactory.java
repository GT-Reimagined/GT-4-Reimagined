package trinsdar.gt4r.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.NotNull;
import trinsdar.gt4r.entity.SpearEntity;

public class SpearRenderFactory implements EntityRendererProvider<SpearEntity> {
    public @NotNull SpearRenderer create(@NotNull Context manager) {
        return new SpearRenderer(manager, Minecraft.getInstance().getItemRenderer());
    }
}
