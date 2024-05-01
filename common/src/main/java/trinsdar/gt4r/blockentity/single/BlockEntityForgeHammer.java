package trinsdar.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.machine.UpgradeableMachineRecipeHandler;

public class BlockEntityForgeHammer extends BlockEntityUpgradeableMachine<BlockEntityForgeHammer> {
    public BlockEntityForgeHammer(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new UpgradeableMachineRecipeHandler<BlockEntityForgeHammer>(this){
            @Override
            public float getClientProgress() {
                float percent = (float) currentProgress / ((float) maxProgress / 3);
                if (percent > 2){
                    percent -= 2;
                } else if (percent > 1){
                    percent -=1;
                }
                return percent;
            }
        });
    }
}
