package trinsdar.gt4r.machine;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.MachineState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IIntArray;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.antimatter.machine.Tier.BRONZE;
import static trinsdar.gt4r.data.Materials.Steam;

public class LavaBoilerRecipeHandler extends MachineRecipeHandler<TileEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;

    protected final IIntArray GUI_SYNC_DATA2 = new IIntArray() {

        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return LavaBoilerRecipeHandler.this.heat;
                case 1:
                    return LavaBoilerRecipeHandler.this.maxHeat;
                case 2:
                    return LavaBoilerRecipeHandler.this.fuel;
                case 3:
                    return LavaBoilerRecipeHandler.this.maxFuel;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    LavaBoilerRecipeHandler.this.heat = value;
                    break;
                case 1:
                    LavaBoilerRecipeHandler.this.maxHeat = value;
                    break;
                case 2:
                    LavaBoilerRecipeHandler.this.fuel = value;
                    break;
                case 3:
                    LavaBoilerRecipeHandler.this.maxFuel = value;
                    break;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public LavaBoilerRecipeHandler(TileEntityCoalBoiler tile) {
        super(tile);
        GUI_SYNC_DATA2.set(0, 0);
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
    public IIntArray getProgressData() {
        return GUI_SYNC_DATA2;
    }

    @Override
    public void onServerUpdate() {
        if (this.heat <= 20) {
            this.heat = 20;
            this.lossTimer = 0;
        }
        int delay = tile.getMachineTier() == BRONZE ? 25 : 20;
        if (++this.lossTimer > delay) {
            this.heat -= 1;
            this.lossTimer = 0;
        }
        //Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(facing -> GTUtility.exportFluidFromMachineToSide(this, steam, facing, steam.getFluidAmount()));
        delay = tile.getMachineTier() == BRONZE ? 25 : 10;
        if (tile.getWorld().getGameTime() % delay == 0){
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
                        int fill = Math.min(room, 300);
                        if (room > 0){
                            f.fillOutput(Steam.getGas(fill), IFluidHandler.FluidAction.EXECUTE);
                        }
                        if (fill < 300){
                            //TODO:steam sounds
                            tile.getWorld().playSound(null, tile.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            if (tile.getWorld() instanceof ServerWorld) ((ServerWorld)tile.getWorld()).spawnParticle(ParticleTypes.SMOKE, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), tile.getWorld().getRandom().nextInt(8) + 1, 0.0D, 0.2D, 0.0D, 0.0D);
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
            --fuel;
            if (fuel <= 0) {
                if (fuel < 0) {
                    fuel = 0;
                }
                if (canRecipeContinue()) {
                    this.heat += 1;
                    this.maxFuel = activeRecipe.getDuration();
                    addOutputs();
                    this.fuel += maxFuel;
                    consumeInputs();
                }
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
    public boolean canOutput() {
        return true;
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
