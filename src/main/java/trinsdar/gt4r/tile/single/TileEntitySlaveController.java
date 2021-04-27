package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.capability.ComponentHandler;
import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.capability.machine.HatchComponentHandler;
import muramasa.antimatter.structure.IComponent;
import muramasa.antimatter.tile.TileEntityBase;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.tile.TileEntityTypes;

import javax.annotation.Nonnull;

public class TileEntitySlaveController extends TileEntityBase implements IComponent {
    private final LazyOptional<ComponentHandler> componentHandler = LazyOptional.of(() -> new ComponentHandler("slave_controller",this));
    public TileEntitySlaveController() {
        super(TileEntityTypes.SLAVE_CONTROLLER_TYPE);
    }

    @Override
    public LazyOptional<? extends IComponentHandler> getComponentHandler() {
        return componentHandler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == AntimatterCaps.COMPONENT_HANDLER_CAPABILITY) return componentHandler.cast();
        LazyOptional[] ca = new LazyOptional[1];
        ca[0] = super.getCapability(cap, side);
        componentHandler.ifPresent(c -> {
            if (c.hasLinkedController() && !c.getControllers().isEmpty()){
                ca[0] = c.getControllers().iterator().next().getCapability(cap, side);
            }
        });
        return ca[0];
    }
}
