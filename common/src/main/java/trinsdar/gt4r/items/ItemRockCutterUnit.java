package trinsdar.gt4r.items;

import io.github.gregtechintergalactical.gtcore.item.ItemPowerUnit;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;

import java.util.ArrayList;
import java.util.List;

import static trinsdar.gt4r.data.GT4RData.RockCutterPowerUnit;

public class ItemRockCutterUnit extends ItemPowerUnit {
    public ItemRockCutterUnit(String domain, String id, Material material) {
        super(domain, id, material);
        AntimatterAPI.register(ItemPowerUnit.class, this);
    }

    @Override
    public Texture[] getTextures() {
        String id = getId().startsWith("power_unit") ? "power_unit" : getId();
        List<Texture> list = new ArrayList<>();
        list.add(new Texture(getDomain(), "item/basic/" + id));
        if (this == RockCutterPowerUnit){
            list.add(new Texture(getDomain(), "item/basic/" + id + "_overlay"));
        }
        return list.toArray(new Texture[0]);
    }
}
