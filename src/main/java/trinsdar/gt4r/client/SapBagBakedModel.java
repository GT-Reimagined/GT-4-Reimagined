package trinsdar.gt4r.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import muramasa.antimatter.dynamic.DynamicBakedModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Tuple;

public class SapBagBakedModel extends DynamicBakedModel {
    public SapBagBakedModel(Tuple<IBakedModel, Int2ObjectOpenHashMap<IBakedModel[]>> bakedTuple) {
        super(bakedTuple);
        onlyGeneralQuads();
    }
}
