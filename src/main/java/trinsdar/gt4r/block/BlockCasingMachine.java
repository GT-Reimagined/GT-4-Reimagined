package trinsdar.gt4r.block;

import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;

public abstract class BlockCasingMachine extends BlockCasing {
    // TODO used for special features for machine casings
    public BlockCasingMachine(String domain, String id) {
        super(domain, id);
    }
    // TODO used for special features for machine casings
    public BlockCasingMachine(String domain, String id,Block.Properties properties) {
        super(domain, id,properties);
    }

    protected abstract String getTextureID();

    protected Texture[] getConnectedTextures() {
        Texture[] texes = new Texture[17];
        for (int i = 0; i <= 16; i++) {
            texes[i] = new Texture(getRegistryName().getNamespace(), "block/ct/" + getTextureID() + "/" + getId().replaceAll("_casing", "") + "_"+i);
        }
        return texes;
    }
}
