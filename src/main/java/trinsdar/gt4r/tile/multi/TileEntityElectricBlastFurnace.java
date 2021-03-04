package trinsdar.gt4r.tile.multi;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import trinsdar.gt4r.block.BlockCoil;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import trinsdar.gt4r.data.GregTechData;

public class TileEntityElectricBlastFurnace extends TileEntityMultiMachine {

    private int heatingCapacity;

    public TileEntityElectricBlastFurnace(Machine type) {
        super(type);
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
        heatingCapacity = getStates("coil").stream().mapToInt(s -> (getHeatPerCasing(s.getBlock()))).sum();
        return true;
    }

    public int getHeatPerCasing(Block block){
        if (block == GregTechData.STANDARD_MACHINE_CASING){
            return 30;
        } else if (block == GregTechData.REINFORCED_MACHINE_CASING){
            return 50;
        } else if (block == GregTechData.ADVANCED_MACHINE_CASING){
            return 70;
        } else if (block == Blocks.LAVA){
            return 250;
        } else {
            return 0;
        }
    }
}
