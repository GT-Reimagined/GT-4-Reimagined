package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tesseract.TesseractCapUtils;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.antimatter.machine.Tier.BRONZE;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Steam;

public class CoalBoilerRecipeHandler extends MachineRecipeHandler<TileEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;

    public CoalBoilerRecipeHandler(TileEntityCoalBoiler tile) {
        super(tile);
        maxHeat = tile.getMachineTier() == BRONZE ? 500 : 1000;
    }

    public int getFuel() {
        return fuel;
    }

    public int getHeat() {
        return heat;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public int getMaxHeat() {
        return maxHeat;
    }

    @Override
    public void onServerUpdate() {
        if (this.heat <= 20) {
            this.heat = 20;
            this.lossTimer = 0;
        }
        int delay = tile.getMachineTier() == BRONZE ? 45 : 40;
        if (++this.lossTimer > delay) {
            this.heat -= 1;
            this.lossTimer = 0;
        }
        Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(this::exportFluidFromMachineToSide);
        delay = tile.getMachineTier() == BRONZE ? 25 : 10;
        if (tile.getLevel().getGameTime() % delay == 0){
            tile.fluidHandler.ifPresent(f -> {
                FluidStack[] inputs = f.getInputs();
                if (this.heat > 100){
                    if (inputs[0].getAmount() == 0){
                        hadNoWater = true;
                    } else {
                        if (hadNoWater){
                            tile.getLevel().explode(null, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), 4.0F, Explosion.BlockInteraction.DESTROY);
                            tile.getLevel().setBlockAndUpdate(tile.getBlockPos(), Blocks.AIR.defaultBlockState());
                            return;
                        }
                        f.drainInput(new FluidStack(Fluids.WATER, 1), IFluidHandler.FluidAction.EXECUTE);
                        int room = 16000 - f.getOutputs()[0].getAmount();
                        int fill = Math.min(room, 150);
                        if (room > 0){
                            f.fillOutput(Steam.getGas(fill), IFluidHandler.FluidAction.EXECUTE);
                        }
                        if (fill < 150){
                            //TODO:steam sounds
                            tile.getLevel().playSound(null, tile.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                            if (tile.getLevel() instanceof ServerLevel) ((ServerLevel)tile.getLevel()).sendParticles(ParticleTypes.SMOKE, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), tile.getLevel().getRandom().nextInt(8) + 1, 0.0D, 0.2D, 0.0D, 0.0D);
                            f.drain(4000, IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                } else {
                    this.hadNoWater = false;
                }
            });
        }
        super.onServerUpdate();
    }

    public void exportFluidFromMachineToSide(Direction side){
        BlockEntity adjTile = tile.getLevel().getBlockEntity(tile.getBlockPos().relative(side));
        if (adjTile == null) return;
        Optional<IFluidHandler> cap = TesseractCapUtils.getFluidHandler(adjTile, side.getOpposite());
        tile.fluidHandler.ifPresent(f -> cap.ifPresent(other -> Utils.transferFluids(f, other, 1000)));
    }

    @Override
    protected MachineState recipeFinish() {
        if (!canRecipeContinue()) {
            this.resetRecipe();
            return IDLE;
        } else {
            return ACTIVE;
        }
    }

    @Override
    protected MachineState tickRecipe() {
        if (this.activeRecipe == null) {
            System.out.println("Check Recipe when active recipe is null");
            return tile.getMachineState();
        }
        else {
            tile.onRecipePreTick();
            if (fuel <= 0 && canRecipeContinue()){
                if (fuel < 0){
                    fuel = 0;
                }
                this.maxFuel = activeRecipe.getDuration();
                addOutputs();
                this.fuel += maxFuel;
                consumeInputs();
            }
            if ((this.heat < maxHeat) && (this.fuel > 0) && (tile.getLevel().getGameTime() % 12L == 0L)) {
                int fuelSubtract = tile.getMachineTier() == BRONZE ? 1 : 2;
                this.fuel -= fuelSubtract;
                this.heat += 1;
            }
            tile.onRecipePostTick();
            if (fuel == 0){
                return this.recipeFinish();
            }
            return ACTIVE;
        }
    }

    public void setActive(boolean t){
        if (t && tile.getMachineState() != MachineState.ACTIVE){
            tile.setMachineState(MachineState.ACTIVE);
        } else if (!t && tile.getMachineState() == MachineState.ACTIVE){
            tile.setMachineState(MachineState.IDLE);
        }
    }

    @Override
    public boolean accepts(FluidStack fluid) {
        return fluid.isFluidEqual(new FluidStack(Fluids.WATER, 1)) || fluid.isFluidEqual(DistilledWater.getLiquid(1));
    }

    @Override
    public boolean canOutput() {
        return true;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = super.serializeNBT();
        nbt.putInt("heat", heat);
        nbt.putInt("maxHeat", maxHeat);
        nbt.putInt("fuel", fuel);
        nbt.putInt("maxFuel", maxFuel);
        nbt.putInt("lossTimer", lossTimer);
        nbt.putBoolean("hadNoWater", hadNoWater);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        this.heat = nbt.getInt("heat");
        this.maxHeat = nbt.getInt("maxHeat");
        this.fuel = nbt.getInt("fuel");
        this.maxFuel = nbt.getInt("maxFuel");
        this.lossTimer = nbt.getInt("lossTimer");
        this.hadNoWater = nbt.getBoolean("hadNoWater");
    }
}
