package trinsdar.gt4r.tile.multi;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.data.SlotTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TileEntityThermalBoiler extends TileEntityMultiMachine<TileEntityThermalBoiler> {
    public TileEntityThermalBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<TileEntityThermalBoiler>(this){

            int ticker = 0;

            @Override
            protected void addOutputs() {
                if (itemHandler.map(h -> !h.getHandler(SlotTypes.FILTER).getStackInSlot(0).isEmpty()).orElse(false)){
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
                                    if (!h.getHandler(SlotTypes.FILTER).getStackInSlot(0).hurt(1, tile.getLevel().random, null)){
                                        h.getHandler(SlotTypes.FILTER).getStackInSlot(0).shrink(1);
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
                    if (r.getChances() != null) {
                        if (Arrays.stream(r.getChances()).sum() != 1.0 && chance){
                            return null;
                        }
                        int rng = tile.level.random.nextInt(10000);
                        List<ItemStack> evaluated = new ObjectArrayList<>();
                        List<Integer> chanceCompare = Arrays.stream(r.getChances()).boxed().toList();
                        for (int i = 0; i < outputs.length; i++) {
                            Integer[] array = chanceCompare.subList(0, i + 1).toArray(new Integer[0]);
                            int sum = Arrays.stream(array).reduce(0, Integer::sum);
                            int compare = i == 0 ? r.getChances()[i] : sum;
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
}
