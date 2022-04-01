package trinsdar.gt4r.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import trinsdar.gt4r.entity.SpearEntity;

public class SpearRenderFactory  implements IRenderFactory<SpearEntity> {
    public SpearRenderer createRenderFor(EntityRenderDispatcher manager) {
        return new SpearRenderer(manager, Minecraft.getInstance().getItemRenderer());
    }
}
