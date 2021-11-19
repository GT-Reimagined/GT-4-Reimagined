package trinsdar.gt4r.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import muramasa.antimatter.client.AntimatterModelLoader.DynamicModelLoader;
import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.client.model.AntimatterModel;
import muramasa.antimatter.dynamic.DynamicModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import trinsdar.gt4r.Ref;

import java.util.function.Function;

public class BakedModels {
    public static final DynamicModelLoader LOADER_SAP_BAG = new DynamicModelLoader(new ResourceLocation(Ref.ID, "sap_bag")) {
        @Override
        public DynamicModel read(JsonDeserializationContext context, JsonObject json) {
            return new DynamicModel((DynamicModel) super.read(context, json)) {
                @Override
                public IBakedModel bakeModel(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> getter, IModelTransform transform, ItemOverrideList overrides, ResourceLocation loc) {
                    return new SapBagBakedModel(getter.apply(new RenderMaterial(PlayerContainer.BLOCK_ATLAS, particle)), getBakedConfigs(owner, bakery, getter, transform, overrides, loc));
                }
            };
        }
    };

    public static void init() {
        AntimatterModelManager.registerStaticConfigMap("sap_bag", () -> SapBagBakedModel.CONFIGS);
    }
}
