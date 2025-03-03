package org.gtreimagined.gt4r.items;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import org.gtreimagined.gtcore.item.ItemPowerUnit;

import java.util.ArrayList;
import java.util.List;

import static org.gtreimagined.gt4r.data.GT4RItems.RockCutterPowerUnit;

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
