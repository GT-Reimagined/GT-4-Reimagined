package trinsdar.gt4r.tile.single;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import trinsdar.gt4r.machine.QuantumChestItemHandler;

public class TileEntityQuantumChest extends TileEntityMachine<TileEntityQuantumChest> {
    public TileEntityQuantumChest(Machine<?> type) {
        super(type);
        this.itemHandler.set(() -> new QuantumChestItemHandler(this));
    }
//TODO
    /*@Override
    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        this.itemHandler.ifPresent(m -> {
            if (m instanceof QuantumChestItemHandler){
                QuantumChestItemHandler handler = (QuantumChestItemHandler) m;
                handler.drawInfo(stack, renderer, left, top);
            }
        });
    }*/
}
