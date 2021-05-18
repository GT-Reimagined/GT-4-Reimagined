package trinsdar.gt4r.block;

import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.block.SoundType;

import static muramasa.antimatter.Data.WRENCH_MATERIAL;

public class BlockNonSolidMachine extends BlockMachine {
    public BlockNonSolidMachine(Machine<?> type, Tier tier) {
        super(type, tier, Properties.create(WRENCH_MATERIAL).hardnessAndResistance(1.0f, 10.0f).sound(SoundType.METAL).setRequiresTool().notSolid());
    }
}
