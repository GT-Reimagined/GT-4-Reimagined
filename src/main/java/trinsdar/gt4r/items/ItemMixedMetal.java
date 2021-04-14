package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

import static muramasa.antimatter.Data.NULL;

public class ItemMixedMetal extends ItemBasic<ItemMixedMetal> implements IColorHandler {
    public ItemMixedMetal() {
        super(Ref.ID, "mixed_metal");
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i > 2) return -1;
        CompoundNBT nbt = stack.getOrCreateTag();
        String tagId = i == 0 ? "tm" : i == 1 ? "mm" : "bm";
        Material mat = Material.get(nbt.getString(tagId));
        if (mat == NULL) return -1;
        return mat.getRGB();
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_top"), new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_middle"), new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_bottom")};
    }
}
