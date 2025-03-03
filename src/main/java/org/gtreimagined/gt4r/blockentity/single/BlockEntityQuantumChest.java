package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.machine.QuantumChestItemHandler;

public class BlockEntityQuantumChest extends BlockEntityMachine<BlockEntityQuantumChest> {
    public BlockEntityQuantumChest(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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
