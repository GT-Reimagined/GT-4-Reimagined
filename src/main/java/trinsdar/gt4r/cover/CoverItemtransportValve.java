package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import trinsdar.gt4r.data.GT4RData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoverItemtransportValve extends CoverBasicTransport{
    public static String ID = "item_transport_valve";

    public CoverItemtransportValve(){
        super();
        register();
    }

    @Override
    protected String getRenderId() {
        return ID;
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
        GT4RData.COVER_PUMP.onUpdate(instance, side);
        GT4RData.COVER_CONVEYOR.onUpdate(instance, side);
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
            }).orElse(true);
            if (name.contains("Invert")){
                powered = !powered;
            }
            return powered;
        }
        return true;
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
