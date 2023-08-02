package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.GT4RMaterialTags;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.items.ItemTurbineRotor;

import java.util.Collections;
import java.util.List;


public class TileEntityLargeTurbine extends TileEntityMultiMachine<TileEntityLargeTurbine> {

    public TileEntityLargeTurbine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() ->
            new MachineRecipeHandler<TileEntityLargeTurbine>(this) {

                private IRecipe sourceRecipe;
                private int ticker = 0;
                private double efficiency;

                @Override
                protected boolean validateRecipe(IRecipe r) {
                    return true;
                }

                @Override
                public boolean canOutput() {
                    return true;
                }

                @Override
                public IRecipe findRecipe() {
                    IRecipe r = super.findRecipe();
                    if (r == null) return null;
                    sourceRecipe = r;
                    //Source recipe contains fluid amounts, map to turbine
                    List<FluidIngredient> stacks = r.getInputFluids();
                    if (stacks.size() == 0) return null;
                    //ItemStack t = tile.itemHandler.map(tt -> tt.getSpecial()).orElse(ItemStack.EMPTY);
                    //if (!(t.getItem() instanceof ItemTurbine)) return null;
                   // ItemTurbine turbine = (ItemTurbine) t.getItem();
                    long flow = sourceRecipe.getPower();//turbine.optimalEUT;
                    efficiency = getEfficiency();//turbine.efficency;
                    if (efficiency <= 0.0F) return null;
                    long toConsume = calculateGeneratorConsumption((int) flow, sourceRecipe);

                    return Utils.getFluidPoweredRecipe(Collections.singletonList(stacks.get(0).copy((int)toConsume)),
                            r.getOutputFluids(),
                           // new FluidStack[]{new FluidStack(DistilledWater.getLiquid(), stacks[0].getAmount())},// Arrays.stream(sourceRecipe.getOutputFluids()).map(tt -> new FluidStack(tt.getFluid(), (int) (tt.getAmount()*toConsume))).toArray(FluidStack[]::new),
                            1, flow,1);
                }
                @Override
                public boolean consumeInputs() {
                    return false;
                }

                @Override
                protected boolean consumeGeneratorResources(boolean simulate) {
                    if (!activeRecipe.hasInputFluids()) {
                        throw new RuntimeException("Missing fuel in active generator recipe!");
                    }
                    //boolean shouldRun = tile.energyHandler.map(h -> h.insert((long)(tile.getMachineType().getMachineEfficiency()*(double)tile.getMachineTier().getVoltage()),true) > 0).orElse(false);
                    ///if (!shouldRun) return false;
                    long recipeAmount = activeRecipe.getInputFluids().get(0).getAmount();
                    long toConsume = recipeAmount; // calculateGeneratorConsumption(tile.getMachineTier().getVoltage(), activeRecipe);// (long) ((double)tile.getMachineTier().getVoltage() / (activeRecipe.getPower() /(double) Objects.requireNonNull(activeRecipe.getInputFluids())[0].getAmount()));
                    long consumed = tile.fluidHandler.map(h -> {
                        /*
                            How much wiggle room? So, at optimal flow : generate regular. Otherwise, dampen by a factor of 1/(optimal-current) or 1/(current-optimal). Allow
                            consuming up to 1.5x optimal
                         */
                        long amount = activeRecipe.getInputFluids().get(0).drainedAmount(toConsume, h, true, true);

                        if (amount == toConsume) {
                            if (!simulate)
                                activeRecipe.getInputFluids().get(0).drain(toConsume, h, true, false);
                            return amount;
                        }
                        return 0L;
                    }).orElse(0L);
                    if (consumed > 0 && efficiency > 0.0F){
                        if (consumed < recipeAmount) consumed *= Math.pow(1d/(recipeAmount-consumed),0.04);
                        if (consumed > recipeAmount) consumed *= Math.pow(1d/(consumed-recipeAmount),0.04);
                        //Input energy
                        long finalConsumed = consumed;

                        if (!simulate) {
                            if (ticker < 80){
                                ticker++;
                            } else {
                                ticker = 0;
                                tile.itemHandler.ifPresent(h -> {
                                    ItemStack copy = h.getHandler(SlotTypes.ROTOR).getStackInSlot(0).copy();
                                    if (h.getHandler(SlotTypes.ROTOR).getStackInSlot(0).hurt(1, tile.level.random, null)){
                                        if (copy.getItem() instanceof ItemTurbineRotor){
                                            h.getHandler(SlotTypes.ROTOR).setStackInSlot(0, GT4RMaterialTags.BROKEN_TURBINE_ROTOR.get(((ItemTurbineRotor)copy.getItem()).getMaterial(), 1));
                                        }
                                        for (Player player : level.getNearbyPlayers(TargetingConditions.DEFAULT.range(5.0D), null, new AABB(new int3(tile.getBlockPos(), tile.getFacing()).left(2).back(5), new int3(tile.getBlockPos(), tile.getFacing()).right(2)))){
                                            if (EntitySelector.NO_SPECTATORS.test(player) && !player.isCreative()) {
                                                double d0 = player.distanceToSqr(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ());
                                                if (d0 < 5 * 5) {
                                                    player.hurt(DamageSource.GENERIC, 8);
                                                }
                                            }
                                        }
                                    }
                                });
                            }

                            tile.energyHandler.ifPresent(handler -> {
                                Utils.addEnergy(handler, (long) (efficiency*activeRecipe.getPower()/* *finalConsumed/ recipeAmount*/));
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
                public CompoundTag serialize() {
                    CompoundTag nbt = super.serialize();
                    nbt.putInt("ticker", ticker);
                    nbt.putDouble("efficiency", efficiency);
                    return nbt;
                }

                @Override
                public void deserialize(CompoundTag nbt) {
                    super.deserialize(nbt);
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
