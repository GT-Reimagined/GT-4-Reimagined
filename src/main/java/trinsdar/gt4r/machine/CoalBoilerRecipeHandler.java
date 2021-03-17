package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.Explosion;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import java.util.concurrent.atomic.AtomicBoolean;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.antimatter.machine.MachineState.INVALID_TIER;
import static muramasa.antimatter.machine.Tier.BRONZE;
import static trinsdar.gt4r.data.Materials.Steam;

public class CoalBoilerRecipeHandler extends MachineRecipeHandler<TileEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;
    public CoalBoilerRecipeHandler(TileEntityCoalBoiler tile) {
        super(tile);
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        AtomicBoolean update = new AtomicBoolean(false);
        if (this.heat <= 20) {
            int oldHeat = heat;
            this.heat = 20;
            if (oldHeat != heat) update.set(true);
            this.lossTimer = 0;
        }
        if (++this.lossTimer > 45) {
            int oldHeat = heat;
            this.heat -= 1;
            if (oldHeat != heat) update.set(true);
            this.lossTimer = 0;
        }
        //Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(facing -> GTUtility.exportFluidFromMachineToSide(this, steam, facing, steam.getFluidAmount()));
        if (tile.getWorld().getGameTime() % 20 == 0){
            tile.fluidHandler.ifPresent(f -> {
                FluidStack[] inputs = f.getInputs();
                if (this.heat > 100){
                    if (inputs[0].getAmount() == 0){
                        hadNoWater = true;
                    } else {
                        if (hadNoWater){
                            tile.getWorld().createExplosion(null, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 4.0F, Explosion.Mode.DESTROY);
                            tile.getWorld().setBlockState(tile.getPos(), Blocks.AIR.getDefaultState());
                            return;
                        }
                        f.drainInput(new FluidStack(Fluids.WATER, 1), IFluidHandler.FluidAction.EXECUTE);
                        int room = 16000 - f.getOutputs()[0].getAmount();
                        int rate = tile.getMachineTier() == BRONZE ? 150 : 300;
                        int fill = Math.min(room, rate);
                        if (room > 0){
                            f.fillOutput(Steam.getGas(fill), IFluidHandler.FluidAction.EXECUTE);
                            update.set(true);
                        }
                        if (fill < rate){
                            //TODO:steam sounds
                            //getNetwork().initiateTileEntityEvent(this, 3, false);
                            f.drain(4000, IFluidHandler.FluidAction.EXECUTE);
                            update.set(true);
                        }
                    }
                } else {
                    this.hadNoWater = false;
                }
            });
        }
        tile.itemHandler.ifPresent(i ->{
            IItemHandlerModifiable outputs = i.getOutputHandler();
            IItemHandlerModifiable inputs = i.getInputHandler();
            ItemStack byproductStack = outputs.getStackInSlot(0);

            if (this.fuel <= 0 && activeRecipe != null){
                this.maxFuel = activeRecipe.getDuration();
                addOutputs();
                this.fuel += maxFuel;
                consumeInputs();
            }

        });
        if ((this.heat < 500) && (this.fuel > 0) && (tile.getWorld().getGameTime() % 12L == 0L)) {
            this.fuel -= 1;
            this.heat += 1;
            update.set(true);
        }
        if (update.get()){
            //tile.onMachineEvent(ContentEvent.FLUID_OUTPUT_CHANGED, "null");
        }
        this.setActive(this.fuel > 0);
    }

    public void setActive(boolean t){
        if (t && tile.getMachineState() != MachineState.ACTIVE){
            tile.setMachineState(MachineState.ACTIVE);
        } else if (!t && tile.getMachineState() == MachineState.ACTIVE){
            tile.setMachineState(MachineState.IDLE);
        }
    }

    @Override
    public void checkRecipe() {
        if (activeRecipe != null) {
            return;
        }
        //First lookup.
        if (!this.tile.hadFirstTick() && hasLoadedInput()) {
            activeRecipe = tile.getMachineType().getRecipeMap().find(itemInputs.toArray(new ItemStack[0]), fluidInputs.toArray(new FluidStack[0]));
            if (activeRecipe == null) return;
            tile.setMachineState(ACTIVE);
            return;
        }
        if (tile.getMachineState().allowRecipeCheck()) {
            if ((activeRecipe = findRecipe()) != null) {
                if (!validateRecipe(activeRecipe)) {
                    tile.setMachineState(INVALID_TIER);
                    activeRecipe = null;
                    return;
                }
                if (!canOutput()) {
                    activeRecipe = null;
                    tile.setMachineState(IDLE);
                    return;
                }
                activateRecipe(true);
                tile.setMachineState(ACTIVE);
            }
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.putInt("heat", heat);
        nbt.putInt("maxHeat", maxHeat);
        nbt.putInt("fuel", fuel);
        nbt.putInt("maxFuel", maxFuel);
        nbt.putInt("lossTimer", lossTimer);
        nbt.putBoolean("hadNoWater", hadNoWater);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        this.heat = nbt.getInt("heat");
        this.maxHeat = nbt.getInt("maxHeat");
        this.fuel = nbt.getInt("fuel");
        this.maxFuel = nbt.getInt("maxFuel");
        this.lossTimer = nbt.getInt("lossTimer");
        this.hadNoWater = nbt.getBoolean("hadNoWater");
    }
}
