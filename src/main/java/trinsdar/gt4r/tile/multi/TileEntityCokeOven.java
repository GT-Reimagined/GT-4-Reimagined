package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.EnergyHandler;
import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MultiMachineEnergyHandler;
import muramasa.antimatter.capability.machine.MultiMachineFluidHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.cover.CoverDynamo;
import muramasa.antimatter.cover.CoverEnergy;
import muramasa.antimatter.cover.CoverInput;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import tesseract.api.capability.TesseractGTCapability;

import java.util.List;

import static muramasa.antimatter.machine.MachineFlag.*;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class TileEntityCokeOven extends TileEntityMultiMachine {

    public TileEntityCokeOven(Machine<?> type) {
        super(type);
        this.itemHandler = type.has(ITEM) || type.has(CELL) ? LazyOptional.of(() -> new MachineItemHandler<>(this)) : LazyOptional.empty();
        this.fluidHandler = type.has(FLUID) ? LazyOptional.of(() -> new MachineFluidHandler<>(this)) : LazyOptional.empty();
        this.energyHandler = type.has(ENERGY) ? LazyOptional.of(() -> new MachineEnergyHandler<>(this, type.amps(),type.has(GENERATOR))) : LazyOptional.empty();
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.getTier(type.amps()*getMachineTier().getVoltage());
    }

    @Override
    public void afterStructureFormed(){
        this.result.components.forEach((k, v) -> v.forEach(c -> {
            c.onStructureFormed(this);
        }));
    }

    public void onStructureInvalidated() {
        this.result.components.forEach((k, v) -> v.forEach(c -> {
            c.onStructureInvalidated(this);
        }));
    }

    @Override
    public int maxShares() {
        return 0;
    }

    @Override
    public int getMaxInputVoltage() {
        return energyHandler.map(EnergyHandler::getInputVoltage).orElse(0);
    }

    @Override
    public <T> LazyOptional<T> getCapabilityFromFake(Capability<T> cap, BlockPos pos, Direction side, ICover coverPresent) {
        if (cap == ITEM_HANDLER_CAPABILITY && itemHandler.isPresent()) return itemHandler.resolve().orElse(null).getCapability().cast();
        else if (cap == FLUID_HANDLER_CAPABILITY && fluidHandler.isPresent()) return fluidHandler.cast();
        else if (cap == TesseractGTCapability.ENERGY_HANDLER_CAPABILITY && energyHandler.isPresent()) return energyHandler.cast();
        return LazyOptional.empty();
    }
}
