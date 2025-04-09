package org.gtreimagined.gt4r.block;

import org.gtreimagined.gtlib.machine.BlockMachine;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.machine.types.Machine;
import net.minecraft.world.level.block.SoundType;

import static org.gtreimagined.gtlib.Data.WRENCH_MATERIAL;

public class BlockNonSolidMachine extends BlockMachine {
    public BlockNonSolidMachine(Machine<?> type, Tier tier) {
        super(type, tier, Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion());
    }
}
