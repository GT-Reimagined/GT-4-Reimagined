package org.gtreimagined.gt4r.blockentity.multi;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.machine.BlockMultiMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.Machines;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityCharcoalPit extends BlockEntityMultiMachine<BlockEntityCharcoalPit> {
    List<BlockPos> logPositions;

    public BlockEntityCharcoalPit(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    private boolean isCovered(BlockPos pos) {
        return level.getBlockState(pos).getMaterial() == Material.DIRT
                || level.getBlockState(pos).getMaterial() == Material.GRASS
                || level.getBlockState(pos).getBlock() == AntimatterAPI.get(BlockMultiMachine.class,Machines.CHARCOAL_PIT.getId() + "_" + Tier.LV.getId(), GT4RRef.ID) || isLog(pos);
    }

    private boolean isLog(BlockPos pos) {
        return level.getBlockState(pos).is(BlockTags.LOGS);
    }

    private List<BlockPos> getLogs() {
        return new ArrayList<>();
        //return AabbUtil.getTargets(world, this.pos, 256, filter, true, false, RotationList.ALL);
    }
}
