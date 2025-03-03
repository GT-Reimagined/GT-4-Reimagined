package org.gtreimagined.gt4r.blockentity.multi;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.data.GT4RItems;

import java.util.Arrays;
import java.util.List;

public class BlockEntityThermalBoiler extends BlockEntityMultiMachine<BlockEntityThermalBoiler> implements IFilterableHandler {
    public BlockEntityThermalBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<BlockEntityThermalBoiler>(this){

            int ticker = 0;

            @Override
            protected void addOutputs() {
                if (itemHandler.map(h -> !h.getHandler(SlotType.STORAGE).getStackInSlot(0).isEmpty()).orElse(false)){
                    if (activeRecipe.hasOutputItems()) {
                        tile.itemHandler.ifPresent(h -> {
                            //Roll the chances here. If they don't fit add flat (no chances).
                            ItemStack[] out = getOutputItems(activeRecipe,true);
                            if (h.canOutputsFit(out)) {
                                h.addOutputs(out);
                                tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
                                if (ticker < 80){
                                    ticker++;
                                } else {
                                    if (!h.getHandler(SlotType.STORAGE).getStackInSlot(0).hurt(1, tile.getLevel().random, null)){
                                        h.getHandler(SlotType.STORAGE).getStackInSlot(0).shrink(1);
                                    }
                                    ticker = 0;
                                }
                            }

                        });
                    }

                }
                if (activeRecipe.hasOutputFluids()) {
                    tile.fluidHandler.ifPresent(h -> {
                        h.addOutputs(activeRecipe.getOutputFluids());
                        tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                    });
                }
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putInt("ticker", ticker);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                ticker = nbt.getInt("ticker");
            }

            public ItemStack[] getOutputItems(IRecipe r, boolean chance) {
                if (r.hasOutputItems()) {
                    ItemStack[] outputs = r.getOutputItems(false).clone();
                    if (r.getOutputChances() != null) {
                        if (Arrays.stream(r.getOutputChances()).sum() != 1.0 && chance){
                            return null;
                        }
                        int rng = tile.level.random.nextInt(10000);
                        List<ItemStack> evaluated = new ObjectArrayList<>();
                        List<Integer> chanceCompare = Arrays.stream(r.getOutputChances()).boxed().toList();
                        for (int i = 0; i < outputs.length; i++) {
                            Integer[] array = chanceCompare.subList(0, i + 1).toArray(new Integer[0]);
                            int sum = Arrays.stream(array).reduce(0, Integer::sum);
                            int compare = i == 0 ? r.getOutputChances()[i] : sum;
                            if (!chance || rng < compare) {
                                evaluated.add(outputs[i].copy());
                                if (chance){
                                    break;
                                }
                            }
                        }
                        outputs = evaluated.toArray(new ItemStack[0]);
                    }
                    return outputs;
                }
                return null;
            }

            @Override
            public boolean canOutput() {
                return !tile.fluidHandler.isPresent() || !activeRecipe.hasOutputFluids() || tile.fluidHandler.map(t -> t.canOutputsFit(activeRecipe.getOutputFluids())).orElse(false);
            }
        });
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        return slotType != SlotType.STORAGE || stack.getItem() == GT4RItems.LavaFilter;
    }
}
