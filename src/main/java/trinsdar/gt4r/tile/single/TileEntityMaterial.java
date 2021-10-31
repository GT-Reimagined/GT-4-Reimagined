package trinsdar.gt4r.tile.single;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.tile.TileEntityMachine;
import trinsdar.gt4r.machine.MaterialMachine;

public class TileEntityMaterial<T extends TileEntityMaterial<T>> extends TileEntityMachine<T> {
    protected Material material;
    public TileEntityMaterial(MaterialMachine type) {
        super(type);
        material = type.getMaterial();
    }

    public Material getMaterial() {
        return material;
    }
}
