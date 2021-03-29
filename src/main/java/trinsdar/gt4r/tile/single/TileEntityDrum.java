package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.TileEntityTank;
import muramasa.antimatter.util.LazyHolder;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nullable;

import static trinsdar.gt4r.data.Materials.Netherite;
import static trinsdar.gt4r.data.Materials.Tungsten;
import static trinsdar.gt4r.data.Materials.TungstenSteel;

public class TileEntityDrum extends TileEntityMachine {
    Material material;
    public TileEntityDrum(MaterialMachine type) {
        super(type);
        material = type.getMaterial();
        this.fluidHandler = LazyHolder.of(() -> new DrumFluidHandler(this));
    }

    public static class DrumFluidHandler extends MachineFluidHandler<TileEntityDrum>{
        public DrumFluidHandler(TileEntityDrum tile) {
            super(tile);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                b.tank(getCapacity(tile.material));
                return b;
            }));
        }

        int getCapacity(Material mat){
            if (mat == Netherite) return 128000;
            if (mat == Tungsten || mat == TungstenSteel) return 256000;
            return 64000;
        }

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
    }
}
