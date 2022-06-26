package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.TileEntityTank;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class TileEntityQuantumTank extends TileEntityTank<TileEntityQuantumTank> {

    public TileEntityQuantumTank(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<TileEntityQuantumTank>(this, Integer.MAX_VALUE, 265000){
            @Nullable
            @Override
            public FluidTanks getOutputTanks() {
                return super.getInputTanks();
            }

            @Override
            protected FluidTank getTank(int tank) {
                return getInputTanks().getTank(tank);
            }

            @Override
            public FluidTanks getTanks(int tank) {
                return getInputTanks();
            }
        });
    }
}
