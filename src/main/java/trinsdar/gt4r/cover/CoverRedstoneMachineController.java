package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;

public class CoverRedstoneMachineController extends BaseCover implements ICoverModeHandler {
    public CoverRedstoneMachineController(){
        super();
        register();
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public String getId() {
        return "redstone_machine_controller";
    }

    @Override
    public void onRemove(CoverStack<?> instance, Direction side) {
        if (instance.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) instance.getTile();
            if (machine.getMachineState() == MachineState.DISABLED){
                machine.toggleMachine();
            }
        }
    }

    public boolean isPowered(CoverStack<?> instance){
        ICoverMode coverMode = getCoverMode(instance);
        int redstone = instance.getNbt().getInt("redstonePower");
        if (coverMode == RedstoneMode.NORMAL){
            return redstone == 0;
        }
        if (coverMode == RedstoneMode.INVERTED){
            return redstone > 0;
        }
        return false;
    }

    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) instance.getTile();
            ICoverMode coverMode = getCoverMode(instance);
            int redstone = instance.getNbt().getInt("redstonePower");
            if (machine.getMachineState() != MachineState.DISABLED){
                if (coverMode == RedstoneMode.NO_WORK){
                    machine.toggleMachine();
                } else if (coverMode == RedstoneMode.NORMAL){
                    if (redstone == 0){
                        machine.toggleMachine();
                    }
                } else {
                    if (redstone > 0){
                        machine.toggleMachine();
                    }
                }
            } else {
                if (coverMode == RedstoneMode.NORMAL){
                    if (redstone > 0){
                        machine.toggleMachine();
                    }
                } else if (coverMode == RedstoneMode.INVERTED){
                    if (redstone == 0){
                        machine.toggleMachine();
                    }
                }
            }

        }
    }

    @Override
    public void onBlockUpdate(CoverStack<?> instance, Direction side) {
        int redstonePower = instance.getTile().getWorld().getRedstonePower(instance.getTile().getPos().offset(side), side);
        instance.getNbt().putInt("redstonePower", redstonePower);
    }

    @Override
    public void onGuiEvent(CoverStack<?> stack, IGuiEvent event, PlayerEntity playerEntity, int... data) {
        if (event == GuiEvent.EXTRA_BUTTON){
            stack.getNbt().putInt("coverMode", Math.min(data[0], 2));
        }
    }

    @Override
    public ICoverMode getCoverMode(CoverStack<?> stack) {
        return RedstoneMode.values()[stack.getNbt().getInt("coverMode")];
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }
}
