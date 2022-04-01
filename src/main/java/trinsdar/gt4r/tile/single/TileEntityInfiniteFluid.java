package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.core.Direction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import java.util.List;

import static muramasa.antimatter.Data.COVEROUTPUT;
import static trinsdar.gt4r.data.Materials.Steam;

import muramasa.antimatter.capability.FluidHandler.FluidDirection;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class TileEntityInfiniteFluid extends TileEntityMachine<TileEntityInfiniteFluid> {

    public TileEntityInfiniteFluid(Machine<?> type) {
        super(type);
        this.fluidHandler.set(() -> new InfiniteFluidHandler(this) {

        });
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        coverHandler.ifPresent(c -> {
            ICover stack = c.get(c.getOutputFacing());
            ((CoverOutput)stack).manualOutput();
        });
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        coverHandler.ifPresent(c -> {
            ICover stack = c.get(c.getOutputFacing());
            ((CoverOutput)stack).setEjects(true, false);
        });
    }



    @Override
    public List<String> getInfo() {
        List<String> info = super.getInfo();
        energyHandler.ifPresent(h -> {
            info.add("Amperage Out: " + h.getOutputAmperage());
        });
        return info;
    }

    protected static class InfiniteFluidHandler extends MachineFluidHandler<TileEntityInfiniteFluid> {

        public InfiniteFluidHandler(TileEntityInfiniteFluid tile) {
            super(tile);
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
                b.tank(Integer.MAX_VALUE);
                return b;
            }));
            FluidTank tank = tanks.get(FluidDirection.OUTPUT).getTank(0);
            tank.setFluid(Steam.getGas(Integer.MAX_VALUE-1));
        }

        @Override
        public boolean canInput(FluidStack fluid, Direction direction) {
            return false;
        }

        @Override
        public boolean canInput(Direction direction) {
            return false;
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack stack, FluidAction action) {
            return stack.copy();
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return Steam.getGas(maxDrain);
        }
    }
}