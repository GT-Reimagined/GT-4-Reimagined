package trinsdar.gt4r.events;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.client.MaterialChestRenderer;

import java.util.function.Consumer;


public class ClientEvents {
    public static void onStitch(TextureAtlas atlas, Consumer<ResourceLocation> spriteFunction) {
        if (!atlas.location().equals(Sheets.CHEST_SHEET)) {
            return;
        }

        spriteFunction.accept(MaterialChestRenderer.MATERIAL_CHEST_BASE);
        spriteFunction.accept(MaterialChestRenderer.MATERIAL_CHEST_OVERLAY);
    }
}
