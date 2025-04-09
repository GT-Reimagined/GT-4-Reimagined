package org.gtreimagined.gt4r.blockentity.multi;

import org.gtreimagined.gtlib.blockentity.multi.BlockEntityMultiMachine;
import org.gtreimagined.gtlib.capability.machine.MultiMachineEnergyHandler;
import org.gtreimagined.gtlib.gui.event.GuiEvents;
import org.gtreimagined.gtlib.gui.event.IGuiEvent;
import org.gtreimagined.gtlib.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityFusionReactor extends BlockEntityMultiMachine<BlockEntityFusionReactor> {

    Display display = Display.REGULAR;

    public BlockEntityFusionReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.energyHandler.set(() -> new MultiMachineEnergyHandler<BlockEntityFusionReactor>( this){
            public void onStructureBuild() {
                super.onStructureBuild();

            }
        });
    }

    /*@Override
    public void onRecipeFound() {
        consumeEnergy(activeRecipe.getSpecialValue());
        System.out.println("Consumed Starting Energy");
    }*/


    public Display getDisplay() {
        return display;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("display", display.ordinal());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.display = Display.values()[tag.getInt("display")];
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev =(GuiEvents.GuiEvent) event;
            int[] data = ev.data;
            if (data[1] == 0){
                this.display = Display.REGULAR;
            } else if (data[1] == 1){
                this.display = Display.MIDDLE;
            } else if (data[1] == 2){
                this.display = Display.TOP_BOTTOM;
            }
        }
    }

    public enum Display{
        REGULAR,
        MIDDLE,
        TOP_BOTTOM
    }
}
