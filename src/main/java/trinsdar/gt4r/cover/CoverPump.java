package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.util.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class CoverPump extends CoverBasicTransport {

    public static String ID = "pump_module";


    public CoverPump() {
        super();
        register();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public <T> boolean blocksCapability(CoverStack<?> stack, Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            String coverModeName = getCoverMode(stack).getName();

        }
        return side == null && cap != CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }


    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() == null) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        TileEntity from = instance.getTile();
        TileEntity to = adjTile;
        if (getCoverMode(instance).getName().startsWith("Input")){
            from = adjTile;
            to = instance.getTile();
        }
        TileEntity finalTo = to;
        from.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).ifPresent(ih -> finalTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, Integer.MAX_VALUE)));
    }

    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicDepthModel();
    }
}
