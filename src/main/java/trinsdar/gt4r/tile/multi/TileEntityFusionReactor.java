package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;

public class TileEntityFusionReactor extends TileEntityMultiMachine<TileEntityFusionReactor> {

    Display display = Display.REGULAR;

    public TileEntityFusionReactor(Machine<?> type) {
        super(type);
    }

//    @Override
//    public void onRecipeFound() {
//        consumeEnergy(activeRecipe.getSpecialValue());
//        System.out.println("Consumed Starting Energy");
//    }


    public Display getDisplay() {
        return display;
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("display", display.ordinal());
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.display = Display.values()[tag.getInt("display")];
    }

    @Override
    public void onGuiEvent(IGuiEvent event, int... data) {
        super.onGuiEvent(event, data);
        if (event == GuiEvent.EXTRA_BUTTON){
            if (data[0] == 0){
                this.display = Display.REGULAR;
            } else if (data[0] == 1){
                this.display = Display.MIDDLE;
            } else if (data[0] == 2){
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
