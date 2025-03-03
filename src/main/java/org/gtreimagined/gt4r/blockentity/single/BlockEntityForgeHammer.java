package org.gtreimagined.gt4r.blockentity.single;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.machine.UpgradeableMachine;
import org.gtreimagined.gt4r.machine.UpgradeableMachineRecipeHandler;

public class BlockEntityForgeHammer extends BlockEntityUpgradeableMachine<BlockEntityForgeHammer> {
    public BlockEntityForgeHammer(UpgradeableMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new UpgradeableMachineRecipeHandler<>(this) {
            @Override
            public float getClientProgress() {
                float percent = (float) currentProgress / ((float) maxProgress / 3);
                if (percent > 2) {
                    percent -= 2;
                } else if (percent > 1) {
                    percent -= 1;
                }
                return percent;
            }
        });
    }
}
