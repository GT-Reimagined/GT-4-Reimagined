package trinsdar.gt4r.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import muramasa.antimatter.dynamic.DynamicBakedModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Tuple;

public class SapBagBakedModel extends DynamicBakedModel {
    public static final Int2ObjectOpenHashMap<BakedModel[]> CONFIGS = new Int2ObjectOpenHashMap<>();

    public SapBagBakedModel(TextureAtlasSprite particle, Int2ObjectOpenHashMap<BakedModel[]> map) {
        super(particle, map);
        onlyGeneralQuads();
    }
}
