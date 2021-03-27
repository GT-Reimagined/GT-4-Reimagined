package trinsdar.gt4r.tile.single;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.LazyHolder;
import muramasa.antimatter.util.Utils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import trinsdar.gt4r.machine.QuantumChestItemHandler;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.NO_POWER;
import static muramasa.antimatter.machine.MachineState.OUTPUT_FULL;

public class TileEntityQuantumChest extends TileEntityMachine {
    public TileEntityQuantumChest(Machine<?> type) {
        super(type);
        this.itemHandler = LazyHolder.of(() -> new QuantumChestItemHandler(this));
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
