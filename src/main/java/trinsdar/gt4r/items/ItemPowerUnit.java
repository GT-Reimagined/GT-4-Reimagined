package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemPowerUnit extends ItemBasic<ItemPowerUnit> implements IColorHandler {
    Material material;
    public ItemPowerUnit(String domain, String id, Material material) {
        super(domain, id);
        this.material = material;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return material.getRGB();
    }

    @Override
    public Texture[] getTextures() {
        String id = getId().startsWith("power_unit") ? "power_unit" : getId();
        List<Texture> list = new ArrayList<>();
        list.add(new Texture(getDomain(), "item/basic/" + id));
        if (getId().equals("small_power_unit")){
            list.add(new Texture(getDomain(), "item/basic/" + id + "_overlay"));
        }
        return list.toArray(new Texture[0]);
    }
}
