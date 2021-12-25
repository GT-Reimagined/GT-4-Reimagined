package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.item.ItemStack;

public class ItemStorageOrb extends ItemBasic<ItemStorageOrb> {
    public ItemStorageOrb(String domain, String id) {
        super(domain, id);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(domain, "item/basic/data_orb")};
    }
}
