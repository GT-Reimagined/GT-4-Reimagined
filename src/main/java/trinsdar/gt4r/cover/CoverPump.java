package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.util.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import trinsdar.gt4r.data.GT4RData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public <T> boolean blocksInput(CoverStack<?> stack, Capability<T> cap, @Nullable Direction side) {
        int mode = stack.getNbt().getInt("coverMode");
        return mode == 0 || mode == 2 || mode == 4;
    }

    @Override
    public <T> boolean blocksOutput(CoverStack<?> stack, Capability<T> cap, @Nullable Direction side) {
        int mode = stack.getNbt().getInt("coverMode");
        return mode == 1 || mode == 3 || mode == 5;
    }


    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile().getWorld().isRemote) return;
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
        if (canMove(instance, side)) {
            from.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).ifPresent(ih -> finalTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, Integer.MAX_VALUE)));
        }
    }

    protected boolean canMove(CoverStack<?> instance, Direction side){
        String name = getCoverMode(instance).getName();
        if (name.contains("Conditional")){
            boolean powered = instance.getTile().getCapability(AntimatterCaps.COVERABLE_HANDLER_CAPABILITY, side).map(h -> {
                List<CoverStack<?>> list = new ArrayList<>();
                for (Direction dir : Direction.values()){
                    if (h.get(dir).getCover() == GT4RData.COVER_REDSTONE_MACHINE_CONTROLLER){
                        list.add(h.get(dir));
                    }
                }
                int i = 0;
                int j = 0;
                for (CoverStack<?> coverStack : list){
                    j++;
                    if (GT4RData.COVER_REDSTONE_MACHINE_CONTROLLER.isPowered(coverStack)){
                        i++;
                    }
                }
                return i > 0 && i == j;
            }).orElse(false);
            return name.contains("Invert") != powered;
        }
        return true;
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
