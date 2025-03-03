package org.gtreimagined.gt4r.blockentity.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.recipe.map.IRecipeMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.data.RecipeMaps;
import org.gtreimagined.gt4r.machine.UpgradeableMachine;

import static muramasa.antimatter.machine.Tier.LV;

public class BlockEntityMacerator extends BlockEntityUpgradeableMachine<BlockEntityMacerator> {
    public BlockEntityMacerator(UpgradeableMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipeMap getRecipeMap() {
                if (tile.getMachineTier() == LV) return RecipeMaps.MACERATOR;
                return super.getRecipeMap();
            }
        });
    }
}
