package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.api.gt.IEnergyHandler;

public class TileEntityCokeOven extends TileEntityBasicMultiMachine<TileEntityCokeOven> {

    public TileEntityCokeOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        /*this.itemHandler = type.has(ITEM) || type.has(CELL) ? LazyOptional.of(() -> new MachineItemHandler<>(this)) : LazyOptional.empty();
        this.fluidHandler = type.has(FLUID) ? LazyOptional.of(() -> new MachineFluidHandler<>(this)) : LazyOptional.empty();
        this.energyHandler = type.has(ENERGY) ? LazyOptional.of(() -> new MachineEnergyHandler<>(this, type.amps(),type.has(GENERATOR))) : LazyOptional.empty();*/
    }

    @Override
    public int maxShares() {
        return 0;
    }

}
