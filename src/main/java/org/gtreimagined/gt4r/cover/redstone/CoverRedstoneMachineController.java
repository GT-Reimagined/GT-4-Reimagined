package org.gtreimagined.gt4r.cover.redstone;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.GT4RRef;

public class CoverRedstoneMachineController extends BaseCover {

    protected boolean inverted;
    protected int redstonePower;
    
    public CoverRedstoneMachineController(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addSwitchButton(79, 34, 16, 16, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, h -> inverted, true, b -> "tooltip.gti.redstone_mode." + (b ? "inverted" : "normal"));
        });
    }

    @Override
    public String getDomain() {
        return GT4RRef.ID;
    }

    @Override
    public String getId() {
        return "redstone_machine_controller";
    }

    @Override
    public void onRemove() {
        if (handler.getTile() instanceof BlockEntityMachine){
            BlockEntityMachine<?> machine = (BlockEntityMachine<?>) handler.getTile();
            if (machine.getMachineState() == MachineState.DISABLED){
                machine.toggleMachine();
            }
        }
    }

    public boolean isPowered(){
        return inverted ? redstonePower == 0 : redstonePower > 0;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine && machine.isServerSide()){
            if (machine.getMachineState() != MachineState.DISABLED){
                if (!isPowered()){
                    machine.toggleMachine();
                }
            } else {
                if (isPowered()){
                    machine.toggleMachine();
                }
            }

        }
    }

    @Override
    public void onBlockUpdate() {
        redstonePower = handler.getTile().getLevel().getSignal(handler.getTile().getBlockPos().relative(side), side);
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 0){
                inverted = !inverted;
                if (handler.getTile() instanceof BlockEntityPipe<?> pipe) pipe.onBlockUpdate(pipe.getBlockPos());
                if (handler.getTile() instanceof BlockEntityMachine<?> machine) machine.onBlockUpdate(machine.getBlockPos());
            }
        }
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }
}
