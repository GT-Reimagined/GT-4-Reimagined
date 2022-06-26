package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import tesseract.api.capability.TesseractGTCapability;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine<TileEntityPrimitiveBlastFurnace> {

    public TileEntityPrimitiveBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int maxShares() {
        return 0;
    }

    //    @Override
//    public boolean onStructureFormed() {
//        super.onStructureFormed();
//        int3 controller = new int3(getPos(), getFacing());
//        controller.back(1);
//        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
//        controller.up(1);
//        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
//        return true;
//    }

    @Override
    public <T> LazyOptional<T> getCapabilityFromFake(Capability<T> cap, BlockPos pos, Direction side, ICover coverPresent) {
        if (cap == ITEM_HANDLER_CAPABILITY && itemHandler.isPresent()) return itemHandler.side(side).cast();
        else if (cap == FLUID_HANDLER_CAPABILITY && fluidHandler.isPresent()) return fluidHandler.side(side).cast();
        else if (cap == TesseractGTCapability.ENERGY_HANDLER_CAPABILITY && energyHandler.isPresent()) return energyHandler.side(side).cast();
        return LazyOptional.empty();
    }
}
