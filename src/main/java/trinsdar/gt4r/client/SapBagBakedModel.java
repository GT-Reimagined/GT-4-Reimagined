package trinsdar.gt4r.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import muramasa.antimatter.dynamic.DynamicBakedModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Tuple;

public class SapBagBakedModel extends DynamicBakedModel {
    public static final Int2ObjectOpenHashMap<IBakedModel[]> CONFIGS = new Int2ObjectOpenHashMap<>();

    public SapBagBakedModel(TextureAtlasSprite particle, Int2ObjectOpenHashMap<IBakedModel[]> map) {
        super(particle, map);
        onlyGeneralQuads();
    }
}
