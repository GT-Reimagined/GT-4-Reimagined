package trinsdar.gt4r.machine;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.data.CustomTags;

public class UpgradeableMachineRecipeHandler<T extends TileEntityMachine<T> & IUpgradeProvider> extends MachineRecipeHandler<T> {
    public UpgradeableMachineRecipeHandler(T tile) {
        super(tile);
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
        calculateDurations();
    }
}
