package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import trinsdar.gt4r.data.Textures;

import javax.annotation.Nullable;

public class ItemTurbineRotor extends ItemBasic<ItemTurbineRotor> implements IColorHandler {
    protected Material material;
    public ItemTurbineRotor(String domain, Material material, Properties properties) {
        super(domain, material.getId() + "_rotor", properties);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{Textures.ROTOR};
    }
}
