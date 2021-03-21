package trinsdar.gt4r.tile.multi;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
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

public class TileEntityElectricBlastFurnace extends TileEntityBasicMultiMachine {

    private int heatingCapacity;

    public TileEntityElectricBlastFurnace(Machine type) {
        super(type);
        this.itemHandler = type.has(ITEM) || type.has(CELL) ? LazyHolder.of(() -> new IBFItemHandler(this)) : LazyHolder.empty();
    }

//    @Override
//    public void onRecipeFound() {
//        if (heatingCapacity >= activeRecipe.getSpecialValue()) {
//            //this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
//            //this.mEfficiencyIncrease = 10000;
//            int tier = Utils.getVoltageTier(getMaxInputVoltage());
//            int heatDiv = (heatingCapacity - activeRecipe.getSpecialValue()) / 900;
//            if (activeRecipe.getPower() <= 16) {
//                EUt = (activeRecipe.getPower() * (1 << tier - 1) * (1 << tier - 1));
//                maxProgress = (activeRecipe.getDuration() / (1 << tier - 1));
//            } else {
//                EUt = activeRecipe.getPower();
//                maxProgress = activeRecipe.getDuration();
//                for (int i = 2; i < Ref.V.length; i+= 2) {
//                    if (EUt > Ref.V[tier - 1]) break;
//                    EUt *= 4;
//                    maxProgress /= (heatDiv >= i ? 4 : 2);
//                }
//            }
//            if (heatDiv > 0) EUt = (long)(EUt * (Math.pow(0.95, heatDiv)));
//            maxProgress = Math.max(1, maxProgress);
//
//            System.out.println("max: " + maxProgress + " - rec: " + activeRecipe.getDuration());
//            System.out.println("eu: " + EUt + " - rec: " + activeRecipe.getPower());
//        }
//    }

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
}
