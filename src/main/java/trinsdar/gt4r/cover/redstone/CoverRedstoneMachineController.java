package trinsdar.gt4r.cover.redstone;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.cover.RedstoneMode;

import javax.annotation.Nullable;

public class CoverRedstoneMachineController extends BaseCover implements ICoverModeHandler {

    protected RedstoneMode coverMode;
    protected int redstonePower;
    
    public CoverRedstoneMachineController(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.coverMode = RedstoneMode.NORMAL;
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
    public void onRemove() {
        if (handler.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) handler.getTile();
            if (machine.getMachineState() == MachineState.DISABLED){
                machine.toggleMachine();
            }
        }
    }

    public boolean isPowered(){
        if (coverMode == RedstoneMode.NORMAL){
            return redstonePower > 0;
        }
        if (coverMode == RedstoneMode.INVERTED){
            return redstonePower == 0;
        }
        return false;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) handler.getTile();
            if (machine.getMachineState() != MachineState.DISABLED){
                if (coverMode == RedstoneMode.NO_WORK){
                    machine.toggleMachine();
                } else if (coverMode == RedstoneMode.NORMAL){
                    if (redstonePower == 0){
                        machine.toggleMachine();
                    }
                } else {
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                }
            } else {
                if (coverMode == RedstoneMode.NORMAL){
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                } else if (coverMode == RedstoneMode.INVERTED){
                    if (redstonePower == 0){
                        machine.toggleMachine();
                    }
                }
            }

        }
    }

    @Override
    public void onBlockUpdate() {
        redstonePower = handler.getTile().getWorld().getRedstonePower(handler.getTile().getPos().offset(side), side);
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity, int... data) {
        if (event == GuiEvent.EXTRA_BUTTON){
            coverMode = RedstoneMode.values()[Math.min(data[0], 2)];
        }
    }

    @Override
    public ICoverMode getCoverMode() {
        return coverMode;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }
}
