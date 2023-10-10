package trinsdar.gt4r.blockentity.multi;

import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
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


public class BlockEntityLargeTurbine extends BlockEntityMultiMachine<BlockEntityLargeTurbine> implements IFilterableHandler {

    public BlockEntityLargeTurbine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        //TODO fix
        this.recipeHandler.set(() ->
            new MachineRecipeHandler<BlockEntityLargeTurbine>(this) {

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
                                    ItemStack copy = h.getHandler(SlotType.STORAGE).getStackInSlot(0).copy();
                                    if (h.getHandler(SlotType.STORAGE).getStackInSlot(0).hurt(1, tile.level.random, null)){
                                        if (copy.getItem() instanceof ItemTurbineRotor){
                                            h.getHandler(SlotType.STORAGE).setStackInSlot(0, GT4RMaterialTags.BROKEN_TURBINE_ROTOR.get(((ItemTurbineRotor)copy.getItem()).getMaterial(), 1));
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

                public float getRotorEfficiency(){
                    ItemStack stack = tile.itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(0)).orElse(ItemStack.EMPTY);
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemTurbineRotor){
                        return ((ItemTurbineRotor)stack.getItem()).getRotorEfficiency();
                    }
                    return 0.0F;
                }

                @Override
                public void onMachineEvent(IMachineEvent event, Object... data) {
                    super.onMachineEvent(event, data);
                    if (event == SlotType.STORAGE) efficiency = getRotorEfficiency();
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

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            return stack.getItem() instanceof ItemTurbineRotor;
        }
        return true;
    }
}
