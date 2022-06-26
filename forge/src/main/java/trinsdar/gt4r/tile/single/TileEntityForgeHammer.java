package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityForgeHammer extends TileEntityMachine<TileEntityForgeHammer> {
    public TileEntityForgeHammer(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<TileEntityForgeHammer>(this){
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
