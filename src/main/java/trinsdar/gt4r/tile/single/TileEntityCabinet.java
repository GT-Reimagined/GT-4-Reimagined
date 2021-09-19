package trinsdar.gt4r.tile.single;

import trinsdar.gt4r.machine.MaterialMachine;

public class TileEntityCabinet extends TileEntityMaterial<TileEntityCabinet>{
    final int ySize;
    public TileEntityCabinet(MaterialMachine type, int ySize) {
        super(type);
        this.ySize = ySize;
    }

    @Override
    public int guiHeight() {
        return ySize;
    }

    @Override
    public int guiSize() {
        return 184;
    }
}
