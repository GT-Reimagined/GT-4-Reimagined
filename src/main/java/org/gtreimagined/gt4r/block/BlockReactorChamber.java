package org.gtreimagined.gt4r.block;

import muramasa.antimatter.block.AntimatterItemBlock;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.data.Machines;

public class BlockReactorChamber extends BlockMachine {
    public BlockReactorChamber(Machine<?> type, Tier tier) {
        super(type, tier);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        int reactors = 0;
        for (Direction dir : Direction.values()) {
            BlockState blockState = level.getBlockState(pos.relative(dir));
            if (blockState.getBlock() == Machines.NUCLEAR_REACTOR_CORE.getBlockState(Tier.NONE)){
                reactors++;
            }
        }
        return reactors == 1;
    }
}
