package trinsdar.gt4r.tile.multi;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.util.LazyHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.item.ItemStack;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.machine.IBFItemHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.machine.MachineFlag.CELL;
import static muramasa.antimatter.machine.MachineFlag.ITEM;

public class TileEntityIndustrialBlastFurnace extends TileEntityBasicMultiMachine {

    private int heatingCapacity;

    public TileEntityIndustrialBlastFurnace(Machine type) {
        super(type);
        this.itemHandler = type.has(ITEM) || type.has(CELL) ? LazyHolder.of(() -> new IBFItemHandler(this)) : LazyHolder.empty();
        this.recipeHandler = LazyHolder.of(() -> new MachineRecipeHandler<TileEntityIndustrialBlastFurnace>(this){
            @Override
            protected boolean validateRecipe(Recipe r) {
                return super.validateRecipe(r) && tile.getHeatingCapacity() >= r.getSpecialValue();
            }
        });
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        heatingCapacity = getAllStates().stream().mapToInt(s -> (getHeatPerCasing(s.getBlock()))).sum();
        return true;
    }

    public List<BlockState> getAllStates() {
        if (result.isPresent()) {
            ObjectCollection<List<BlockState>> collection = result.get().states.values();
            if (collection.isEmpty()){
                return Collections.emptyList();
            }
            List<BlockState> list = new ArrayList<>();
            collection.forEach(list::addAll);
            return list;
        }
        return Collections.emptyList();
    }

    public int getHeatPerCasing(Block block){
        if (block == GT4RData.STANDARD_MACHINE_CASING){
            return 30;
        } else if (block == GT4RData.REINFORCED_MACHINE_CASING){
            return 50;
        } else if (block == GT4RData.ADVANCED_MACHINE_CASING){
            return 70;
        } else if (block == Blocks.LAVA){
            return 250;
        } else {
            return 0;
        }
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event instanceof BFEvent){
            heatingCapacity = getAllStates().stream().mapToInt(s -> (getHeatPerCasing(s.getBlock()))).sum();
            ItemStack stack = (ItemStack) data[0];
            if (!stack.isEmpty()){
                if (stack.getItem() == GT4RData.KanthalHeatingCoil){
                    heatingCapacity += (125 * stack.getCount());
                } else {
                    heatingCapacity += (250 * stack.getCount());
                }
            }
            if (openContainer != null) {
                openContainer.detectAndSendChanges();
            }
        }
        super.onMachineEvent(event, data);
    }

    public enum BFEvent implements IMachineEvent{
        SLOT_COIL_CHANGED
    }

    public int getHeatingCapacity() {
        return heatingCapacity;
    }
}
