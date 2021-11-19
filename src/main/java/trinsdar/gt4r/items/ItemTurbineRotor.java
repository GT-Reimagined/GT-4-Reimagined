package trinsdar.gt4r.items;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.texture.Texture;
import trinsdar.gt4r.data.Textures;

import static trinsdar.gt4r.data.Materials.*;

import net.minecraft.item.Item.Properties;

public class ItemTurbineRotor extends MaterialItem {

    public ItemTurbineRotor(String domain, MaterialType<?> type, Material material, Properties properties) {
        super(domain, type, material, properties);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{Textures.ROTOR};
    }

    public float getRotorEfficiency(){
        if (this.material == Bronze){
            return 0.6F;
        }
        if (this.material == Steel){
            return 0.8F;
        }
        if (this.material == Magnalium){
            return 1.0F;
        }
        if (this.material == TungstenSteel){
            return 0.9F;
        }
        if (this.material == Carbon){
            return 1.25F;
        }
        if (this.material == Osmium){
            return 1.75F;
        }
        if (this.material == Osmiridium){
            return 1.5F;
        }
        return 0.0F;
    }
}
