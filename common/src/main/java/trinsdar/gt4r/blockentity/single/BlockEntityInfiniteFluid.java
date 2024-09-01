package trinsdar.gt4r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static trinsdar.gt4r.data.Materials.Steam;

public class BlockEntityInfiniteFluid extends BlockEntityMachine<BlockEntityInfiniteFluid> {

    public BlockEntityInfiniteFluid(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new InfiniteFluidHandler(this) {

        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
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
    public List<String> getInfo(boolean simple) {
        List<String> info = super.getInfo(simple);
        energyHandler.ifPresent(h -> {
            info.add("Amperage Out: " + h.getOutputAmperage());
        });
        return info;
    }

    protected static class InfiniteFluidHandler extends MachineFluidHandler<BlockEntityInfiniteFluid> {

        public InfiniteFluidHandler(BlockEntityInfiniteFluid tile) {
            super(tile);
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                b.tank(Integer.MAX_VALUE);
                return b;
            }));
            FluidTank tank = tanks.get(FluidDirection.OUTPUT).getTank(0);
            tank.setFluid(0, Steam.getGas(Integer.MAX_VALUE-1));
        }

        @Override
        public boolean canInput(FluidHolder fluid, Direction direction) {
            return false;
        }

        @Override
        public boolean canInput(Direction direction) {
            return false;
        }

        @NotNull
        @Override
        public FluidHolder extractFluid(FluidHolder stack, boolean action) {
            return stack.copyHolder();
        }
    }
}