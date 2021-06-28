package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.command.arguments.EntitySelector;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.items.ItemTurbineRotor;


public class TileEntityLargeTurbine extends TileEntityMultiMachine<TileEntityLargeTurbine> {

    public TileEntityLargeTurbine(Machine type) {
        super(type);
        this.recipeHandler.set(() ->
            new MachineRecipeHandler<TileEntityLargeTurbine>(this) {

                private Recipe sourceRecipe;
                private int ticker = 0;
                private double efficiency;

                @Override
                protected boolean validateRecipe(Recipe r) {
                    return true;
                }

                @Override
                public boolean canOutput() {
                    return true;
                }

                @Override
                public Recipe findRecipe() {
                    Recipe r = super.findRecipe();
                    if (r == null) return null;
                    sourceRecipe = r;
                    //Source recipe contains fluid amounts, map to turbine
                    FluidStack[] stacks = r.getInputFluids();
                    if (stacks == null || stacks.length == 0) return null;
                    //ItemStack t = tile.itemHandler.map(tt -> tt.getSpecial()).orElse(ItemStack.EMPTY);
                    //if (!(t.getItem() instanceof ItemTurbine)) return null;
                   // ItemTurbine turbine = (ItemTurbine) t.getItem();
                    long flow = sourceRecipe.getPower();//turbine.optimalEUT;
                    efficiency = getEfficiency();//turbine.efficency;
                    if (efficiency <= 0.0F) return null;
                    long toConsume = calculateGeneratorConsumption((int) flow, sourceRecipe);

                    return Utils.getFluidPoweredRecipe(new FluidStack[]{new FluidStack(stacks[0].getFluid(),(int) toConsume)},
                            r.getOutputFluids(),
                           // new FluidStack[]{new FluidStack(DistilledWater.getLiquid(), stacks[0].getAmount())},// Arrays.stream(sourceRecipe.getOutputFluids()).map(tt -> new FluidStack(tt.getFluid(), (int) (tt.getAmount()*toConsume))).toArray(FluidStack[]::new),
                            1, flow,1);
                }
                @Override
                public void consumeInputs() {

                }

                @Override
                protected boolean consumeGeneratorResources(boolean simulate) {
                    if (!activeRecipe.hasInputFluids()) {
                        throw new RuntimeException("Missing fuel in active generator recipe!");
                    }
                    //boolean shouldRun = tile.energyHandler.map(h -> h.insert((long)(tile.getMachineType().getMachineEfficiency()*(double)tile.getMachineTier().getVoltage()),true) > 0).orElse(false);
                    ///if (!shouldRun) return false;
                    int recipeAmount = activeRecipe.getInputFluids()[0].getAmount();
                    long toConsume = recipeAmount; // calculateGeneratorConsumption(tile.getMachineTier().getVoltage(), activeRecipe);// (long) ((double)tile.getMachineTier().getVoltage() / (activeRecipe.getPower() /(double) Objects.requireNonNull(activeRecipe.getInputFluids())[0].getAmount()));
                    int consumed = tile.fluidHandler.map(h -> {
                        /*
                            How much wiggle room? So, at optimal flow : generate regular. Otherwise, dampen by a factor of 1/(optimal-current) or 1/(current-optimal). Allow
                            consuming up to 1.5x optimal
                         */
                        int amount = h.getInputTanks().drain(new FluidStack(activeRecipe.getInputFluids()[0],(int)(toConsume)), IFluidHandler.FluidAction.SIMULATE).getAmount();

                        if (amount == toConsume) {
                            if (!simulate)
                                h.getInputTanks().drain(new FluidStack(activeRecipe.getInputFluids()[0], amount), IFluidHandler.FluidAction.EXECUTE);
                            return amount;
                        }
                        return 0;
                    }).orElse(0);
                    if (consumed > 0 && efficiency > 0.0F){
                        if (consumed < recipeAmount) consumed *= Math.pow(1d/(recipeAmount-consumed),0.04);
                        if (consumed > recipeAmount) consumed *= Math.pow(1d/(consumed-recipeAmount),0.04);
                        //Input energy
                        int finalConsumed = consumed;

                        if (!simulate) {
                            if (ticker == 80){
                                ticker = 0;
                                tile.itemHandler.ifPresent(h -> {
                                    ItemStack copy = h.getHandler(SlotTypes.ROTOR).getStackInSlot(0).copy();
                                    if (h.getHandler(SlotTypes.ROTOR).getStackInSlot(0).attemptDamageItem(1, tile.world.rand, null)){
                                        if (copy.getItem() instanceof ItemTurbineRotor){
                                            h.getHandler(SlotTypes.ROTOR).setStackInSlot(0, Materials.BROKEN_TURBINE_ROTOR.get(((ItemTurbineRotor)copy.getItem()).getMaterial(), 1));
                                        }
                                        if (world.isPlayerWithin(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 5)){
                                            for (PlayerEntity player : world.getPlayers()){
                                                if (EntityPredicates.NOT_SPECTATING.test(player) && !player.isCreative()) {
                                                    double d0 = player.getDistanceSq(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());
                                                    if (d0 < 5 * 5) {
                                                        player.attackEntityFrom(DamageSource.GENERIC, 8);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            ticker++;
                            /*if (activeRecipe.hasOutputFluids()){
                                addOutputs();
                            }*/

                            tile.energyHandler.ifPresent(handler -> {
                                handler.insert((long) (efficiency*activeRecipe.getPower()/* *finalConsumed/ recipeAmount*/), false);
                            });
                        }
                        return true;
                    }
                    return false;
                }

                public float getEfficiency(){
                    ItemStack stack = tile.itemHandler.map(i -> i.getHandler(SlotTypes.ROTOR).getStackInSlot(0)).orElse(ItemStack.EMPTY);
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemTurbineRotor){
                        return ((ItemTurbineRotor)stack.getItem()).getRotorEfficiency();
                    }
                    return 0.0F;
                }

                @Override
                public void onMachineEvent(IMachineEvent event, Object... data) {
                    super.onMachineEvent(event, data);
                    if (event == ContentEvent.ITEM_INPUT_CHANGED) efficiency = getEfficiency();
                }

                @Override
                public CompoundNBT serializeNBT() {
                    CompoundNBT nbt = super.serializeNBT();
                    nbt.putInt("ticker", ticker);
                    nbt.putDouble("efficiency", efficiency);
                    return nbt;
                }

                @Override
                public void deserializeNBT(CompoundNBT nbt) {
                    super.deserializeNBT(nbt);
                    ticker = nbt.getInt("ticker");
                    efficiency = nbt.getDouble("efficiency");
                }
            }
        );
    }

    public Block getCasing(){
        if (type == Machines.LARGE_GAS_TURBINE){
            return GT4RData.REINFORCED_MACHINE_CASING;
        }
        return GT4RData.STANDARD_MACHINE_CASING;
    }
}
