package trinsdar.gt4r.tile.single;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.machine.QuantumChestItemHandler;

public class TileEntityQuantumChest extends TileEntityMachine {
    public TileEntityQuantumChest(Machine<?> type) {
        super(type);
        this.itemHandler = LazyOptional.of(() -> new QuantumChestItemHandler(this));
    }

    @Override
    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        this.itemHandler.ifPresent(m -> {
            if (m instanceof QuantumChestItemHandler){
                QuantumChestItemHandler handler = (QuantumChestItemHandler) m;
                handler.drawInfo(stack, renderer, left, top);
            }
        });
    }
}
