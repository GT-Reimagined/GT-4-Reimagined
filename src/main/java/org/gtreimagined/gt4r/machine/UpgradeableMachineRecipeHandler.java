package org.gtreimagined.gt4r.machine;

import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.machine.MachineRecipeHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import org.gtreimagined.gt4r.data.CustomTags;

import static org.gtreimagined.gt4r.data.Materials.Steam;

public class UpgradeableMachineRecipeHandler<T extends BlockEntityMachine<T> & IUpgradeProvider> extends MachineRecipeHandler<T> {
    public UpgradeableMachineRecipeHandler(T tile) {
        super(tile);
    }

    @Override
    public boolean consumePower(boolean simulate) {
        if (tile.getUpgrades().containsKey(CustomTags.STEAM_UPGRADES) && !generator){
            if (activeRecipe.getPower() > 0) {
                if (tile.fluidHandler.isPresent()) {
                    return tile.fluidHandler.map(f -> f.drainInput(Steam.getLiquid((int) (getPower() * 2)), simulate ? FluidAction.SIMULATE : FluidAction.EXECUTE).getAmount() == getPower() * 2).orElse(false);
                } else {
                    return false;
                }
            }
        }
        return super.consumePower(simulate);
    }

    @Override
    public int getOverclock() {
        if (activeRecipe == null) return 0;
        int oc = 0;
        if (activeRecipe.getPower() > 0) {
            if (tile.getUpgrades().containsKey(CustomTags.OVERCLOCKER_UPGRADES)) {
                int upgrade = tile.getUpgrades().get(CustomTags.OVERCLOCKER_UPGRADES);
                if (upgrade < 0) return 0;
                int amount = Math.min(upgrade, 4);
                oc += amount;
            }
        }
        return oc;
    }

    @Override
    public void resetProgress() {
        super.resetProgress();
        if (activeRecipe != null) {
            calculateDurations();
        }
    }
}
