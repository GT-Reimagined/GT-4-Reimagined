package trinsdar.gt4r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.map.IRecipeMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.machine.UpgradeableMachine;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;

public class BlockEntityMacerator extends BlockEntityUpgradeableMachine<BlockEntityMacerator> {
    public BlockEntityMacerator(UpgradeableMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            protected IRecipeMap getRecipeMap() {
                if (tile.getMachineTier() == LV) return RecipeMaps.MACERATING;
                return super.getRecipeMap();
            }
        });
    }
}
